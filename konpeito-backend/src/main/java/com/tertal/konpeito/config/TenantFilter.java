package com.tertal.konpeito.config;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tertal.konpeito.entity.User;
import com.tertal.konpeito.repository.UserRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TenantFilter implements Filter {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Long tenantId = extractUserId();

        if (tenantId == null) {
            // JwtFilter request never passed?

//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            response.setContentType("application/json");
//            response.getWriter().write("{\"error\": \"Tenant ID is missing\"}");
//            return;
        }

        try {
            TenantContext.setCurrentTenant(tenantId);
            chain.doFilter(servletRequest, servletResponse);
        } finally {
            TenantContext.clear();
        }
    }

    private Long extractUserId() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Optional<User> user = this.userRepository.findByUsername(authentication.getName());

        return user.map(User::getId).orElse(null);
    }

}
