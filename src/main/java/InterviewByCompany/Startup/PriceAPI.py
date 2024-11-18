import requests

def getDiscountedPrice(barcode):
    # Construct the API URL with the given barcode
    url = f"https://jsonmock.hackerrank.com/api/inventory?barcode={barcode}"

    # Make a GET request to fetch the data
    response = requests.get(url)

    # Parse the JSON response
    if response.status_code == 200:
        json_data = response.json()
        data = json_data.get("data", [])

        # If the data array is empty, return -1
        if not data:
            return -1

        # Get the first (and only) record in the data array
        item_data = data[0]

        # Extract price and discount from the item data
        price = item_data.get("price", 0)
        discount = item_data.get("discount", 0)

        # Calculate the discounted price
        discounted_price = price - ((discount / 100) * price)

        # Return the discounted price rounded to the nearest integer
        return round(discounted_price)
    else:
        # Return -1 if the API request fails
        return -1

# Example usage
barcode = "74001755"
print(getDiscountedPrice(barcode))  # Example output