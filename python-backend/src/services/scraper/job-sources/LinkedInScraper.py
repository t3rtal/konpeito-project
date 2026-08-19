from ..scrape import scrape_for_jobs

def scrape_LinkedIn(position, location):
    URL = f"https://www.linkedin.com/jobs/search?keywords={position}&location={location}&geoId=102454443&trk=public_jobs_jobs-search-bar_search-submit&position=1&pageNum=0"

    return scrape_for_jobs(URL)

print(scrape_LinkedIn("Software Engineer", "Singapore"))