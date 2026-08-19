import selenium.webdriver as webdriver
from selenium.webdriver.chrome.service import Service
from bs4 import BeautifulSoup

def scrape_website(website):
    print("Launching chrome browser...")

    chrome_driver_path = "./chromedriver.exe"
    options = webdriver.ChromeOptions()
    driver = webdriver.Chrome(service=Service(chrome_driver_path), options=options)

    try:
        driver.get(website)
        print("Page loaded")
        html = driver.page_source

        return html
    finally:
        driver.quit()

def extract_body_content(html_content):
    soup = BeautifulSoup(html_content, "html.parser")
    body_content = soup.body

    if body_content:
        return str(body_content)
    return ""

def get_job_list(body_content):
    soup = BeautifulSoup(body_content, "html.parser")

    jobs = []

    for li in soup.find_all("li"):
        link = li.select_one("a.base-card__full-link")

        if not link:
            continue

        title = li.select_one("h3.base-search-card__title")
        company = li.select_one("h4.base-search-card__subtitle a")
        date = li.select_one("time.job-search-card__listdate")

        jobs.append({
            "title": title.get_text(strip=True) if title else None,
            "company": company.get_text(strip=True) if company else None,
            "date": date.get("datetime") if date else None,
            "url": link.get("href") if link else None,
        })

    return jobs

def scrape_for_jobs(url):
    html = scrape_website(url)
    body_content = extract_body_content(html)

    return get_job_list(body_content)