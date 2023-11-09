import tkinter as tk

product_list = []
product_list = add_product(product_list)
product_list = remove_product(product_list)

def add_product(products):
    product_name = input("Enter product name: ")
    product_price = float(input("Enter product price: "))
    product_stock = int(input("Enter product stock: "))  # Add stock input
    product_info = [product_name, product_price, product_stock]
    products.append(product_info)
    print("Product added successfully.")
    return products # Returns updated products list

def remove_product(products):
    product_name = input("Enter the name of the product to remove: ")
    found = False
    for product in products:
        if product[0] == product_name:
            products.remove(product)
            found = True
            print("Product removed successfully.")
    if not found:
        print("Product not found.")
    return products #Returns updated products list

def display_products(products):
    print("Products:")
    print("Name ------ Price ------ Stock")
    for product in products:
        print(product[0], "------", product[1], "------", product[2])
        
def view_stocks(products):
    print("Available Stocks:")
    print("Product Name ------ Stock")
    for product in products:
        print(product[0], "------", product[2])
        
def add_stock(products, product_name, additional_stock):
    for product in products:
        if product[0] == product_name:
            product[2] += additional_stock
            break
    return products

def sort_products(products):
    sorted_products = sorted(products, key=lambda item: item[1])
    print("Products sorted by price:")
    display_products(sorted_products)

def search_product(products):
    product_name = input("Enter the name of the product to search: ")
    found = False
    for product in products:
        if product[0] == product_name:
            found = True
            print("\nProduct found:")
            print("Name ------ Price ------ Stock\n")
            print(product[0], "---", product[1], "---", product[2])
            break
    if not found:
        print("Product not found.")

def display_orders(transactions):
    for transaction in transactions:
        print("Orders:")
        print("Buyer:", transaction['buyer'])
        print("Address:", transaction['address'])
        print("Phone Number:", transaction['phonenumber'])
        print("Item:", transaction['item'])
        print("Quantity:", transaction['quantity'])
        print("Total:", transaction['total'])
        print("==============================================================================================\n")

def seller_menu(products, transactions):
    while True:
        print("==============================================================================================")
        print("\t\t\tSimplified Online")
        print("\t\t\t  Buying System")
        print("==============================================================================================\n")
        print("Product Menu")
        print("1. Add product")
        print("2. Remove product")
        print("3. Display all products")
        print("4. Sort products by price")
        print("5. View order list")
        print("6. View stocks")
        print("7. Update stocks")
        print("8. Exit\n")
        choice = int(input("Enter your choice: "))

        if choice == 1:
            print("==============================================================================================")
            print("\t\t\tOnline Buying System")
            print("\t\t\tAdd Product")
            print("==============================================================================================\n")
            add_product(products)
            print()
        elif choice == 2:
            print("==============================================================================================")
            print("\t\t\tOnline Buying System")
            print("\t\t\tRemove Product")
            print("==============================================================================================\n")
            display_products(products)
            remove_product(products)
            print()
        elif choice == 3:
            print("==============================================================================================")
            print("\t\t\tOnline Buying System")
            print("\t\t\tDisplay Product")
            print("==============================================================================================\n")
            display_products(products)
            print()
        elif choice == 4:
            print("==============================================================================================")
            print("\t\t\tOnline Buying System")
            print("\t\t\tSort Product")
            print("==============================================================================================\n")
            sort_products(products)
            print()
        elif choice == 5:
            print("==============================================================================================")