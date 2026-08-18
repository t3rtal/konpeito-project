package com.tertal.konpeito.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Aspect
@Component
public class TenantHibernateFilter {

    @PersistenceContext
    private EntityManager entityManager;

    @Before("execution(* com.tertal.konpeito.service.impl.*.*(..))")
    public void activateFilter() {
        Long tenantId = TenantContext.getCurrentTenant();

        if (tenantId != null) {
            Session session = this.entityManager.unwrap(Session.class);

            session.enableFilter("tenantFilter")
                    .setParameter("tenantId", tenantId);
        }
    }

}
