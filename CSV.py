import requests
from bs4 import BeautifulSoup
import csv

# Define the eBay URL (search page for a product like "laptop")
URL = "https://www.ebay.com/sch/i.html?_nkw=laptop"

# Set headers to mimic a browser (sometimes required to avoid being blocked)
headers = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.3"
}

# Function to fetch page content
def fetch_page_content(url):
    response = requests.get(url, headers=headers)
    return response.text

# Function to parse product data from the page
def parse_product_data(page_content):
    soup = BeautifulSoup(page_content, "html.parser")
    
    products = []

    # Find all product containers (eBay listings)
    product_items = soup.find_all("li", class_="s-item")

    for item in product_items:
        name = item.find("h3", class_="s-item__title")
        price = item.find("span", class_="s-item__price")
        rating = item.find("div", class_="x-star-rating")
        
        # Extract the product data (if available)
        product_name = name.text if name else "No Name"
        product_price = price.text if price else "No Price"
        product_rating = rating["aria-label"] if rating else "No Rating"
        
        products.append([product_name, product_price, product_rating])

    return products

# Function to save the data into a CSV file
def save_to_csv(products, filename="products.csv"):
    with open(filename, mode="w", newline="", encoding="utf-8") as file:
        writer = csv.writer(file)
        writer.writerow(["Product Name", "Price", "Rating"])
        writer.writerows(products)

# Main function to execute the scraping and saving process
def main():
    print("Fetching page content...")
    page_content = fetch_page_content(URL)
    
    print("Parsing product data...")
    products = parse_product_data(page_content)
    
    print("Saving to CSV...")
    save_to_csv(products)
    
    print(f"Data saved to 'products.csv'. {len(products)} products found.")

# Execute the script
if __name__ == "__main__":
    main()
