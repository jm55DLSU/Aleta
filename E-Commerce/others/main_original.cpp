#include <iostream>
#include <string>
#include <conio.h>

using namespace std;

void test(){
    string userType;
   
    cout << "Are you a seller? (yes/no): ";
    cin >> userType;

    if (userType == "yes") {
        string sellerUsername = "user123";
        string sellerPassword = "pass456";

        // Ask the seller to enter their credentials
        string enteredUsername, enteredPassword;
        cout << "Enter your username: ";
        cin >> enteredUsername;
        cout << "Enter your password: ";
        cin >> enteredPassword;

        if (enteredUsername == sellerUsername && enteredPassword == sellerPassword) {
            // The seller is logged in
            cout << "Login successful. You are now logged in as a seller." << endl; 

            // Code to display seller menu
            cout << "Seller menu" << endl;
            cout << "1. View products" << endl;
            cout << "2. Add product" << endl;
            cout << "3. View Ordered List" << endl;
            cout << endl;

            int sellerChoice;
            cout << "Enter your choice: ";
            cin >> sellerChoice;

            switch (sellerChoice) {
                case 1:
                    // Code to view products
                    cout << "[View Products]";
                    break;
                case 2:{
                	// Code to add product
                    string productName = ""; //Nag-error kasi since class- or method-wide yung scope, dapat sariling block lang.
                    int productPrice, productQuantity;
                    cout << "Enter product name: ";
                    cin >> productName;
                    cout << "Enter product price: ";
                    cin >> productPrice;
                    cout << "Enter product quantity: ";
                    cin >> productQuantity;
                    cout << "Product added successfully!" << endl;
					break;
				}
                case 3:
                    // Code to view ordered list
                    cout << "[Order List]";
                    break;
                default:
                    cout << "Invalid choice." << endl;
                    break;
            }
        } else {
            cout << "Invalid username or password. Please try again." << endl;
        }   
    } else {
        // Code for customer
        string customerName;
        string productName;
        int quantity;
        double price;
        double totalCost;
        int choice;

        while (true) {
            system("cls");

            cout << "1. Place an order" << endl;
            cout << "2. View order history" << endl;
            cout << "3. Exit" << endl;
            cout << endl;
            cout << "Enter your choice: ";
            cin >> choice;

            switch (choice) {
                case 1:
                    system("cls");
                    cout << "Enter your name: ";
                    cin.ignore();
                    getline(cin, customerName);

                    cout << "Enter the product name: ";
                    getline(cin, productName);

                    cout << "Enter the quantity: ";
                    cin >> quantity;

                    cout << "Enter the price per item: ";
                    cin >> price;

                    totalCost = quantity * price;

                    cout << endl;
                    cout << "Order Summary" << endl;
                    cout << "------------------------" << endl;
                    cout << "Customer name: " << customerName << endl;
                    cout << "Product name: " << productName << endl;
                    cout << "Quantity: " << quantity << endl;
                    cout << "Price per item: " << price << endl;
                    cout << "Total cost: " << totalCost << endl;

                    getch();
                    break;
                    case 2:
                    system("cls");
                    cout << "Order History" << endl;
                    cout << "------------------------" << endl;
                    // Code to view order history
                    getch();
                    break;

                case 3:
                    exit(0);

                default:
                    cout << "Invalid choice. Please enter 1, 2 or 3." << endl;
                    getch();
                    break;
            }
        }
    }

    //return 0;
}
