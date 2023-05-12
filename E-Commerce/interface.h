#ifndef INTERFACE_H_
#define INTERFACE_H_

#include <iostream>
#include <string>
#include <conio.h>
#include <vector>
#include <limits>

#include "structs.h"

namespace interface{
	void cls(){
		if(system("cls"))
			system("clear");
	}
	
	void title(){
		cls();
		std::cout << "==============" << std::endl;
		std::cout << "  E-Commerce" << std::endl;
		std::cout << "==============" << std::endl;
		std::cout << std::endl;
	}
	
	//For string inputs that will contain spaces (e.g., name, title, description, etc.)
	std::string getStringWSpace(std::string inputMessage){
		std::string output = "";
		
		std::cout << inputMessage;
		while (!std::getline(std::cin, output) || output.empty());
		
		return output;
	}
	
	//Will serve as the "improved" version of getch() (i.e., one with a message "Press Enter to continue..."
	void anykey(){
		std::cout << std::endl << std::endl << "Press Enter to continue...";
		getch();
		std::cin.clear();
	}
	
	int loginScreen(){
		title();
		
		std::cout << "1 - Login" << std::endl;
		std::cout << "2 - New Account <<UNDER CONSTRUCTION>>" << std::endl;
		std::cout << "0 - Exit" << std::endl << std::endl;
		
		int choice;
		std::cout << "Enter choice: ";
		std::cin >> choice;
		
		return choice;	
	}
	
	std::string login(std::vector<structs::User> users){
		title();
		
		std::string uname, pword;
		std::cout << "Username: ";
		std::cin >> uname;
		std::cout << "Password: ";
		std::cin >> pword;
		std::cout << std::endl;
		
		for(int i = 0; i < users.size(); i++){
			if(users[i].username == uname && users[i].password == pword)
				return users[i].username;
		}	
		return "";
	}
	
	int sellerMainMenu(structs::User loggedIn){
		bool valid = false;
		int choice = -1;
		do{
			title();
			std::cout << "Logged in as: " << loggedIn.username << std::endl << std::endl;
			std::cout << "Seller Menu" << std::endl;
	        std::cout << "1 - View Products" << std::endl;
	        std::cout << "2 - Add Product" << std::endl;
	        std::cout << "3 - View Order List" << std::endl;
	        std::cout << "4 - Edit Order List" << std::endl;
	        std::cout << "5 - Logout" << std::endl << std::endl;
	        
			std::cout << "Enter choice: ";
			std::cin >> choice;
	        
	        //Check if valid input (i.e., 1-5)
	        if(choice >= 1 && choice <= 5)
	        	valid = true;
		}while(!valid);
		return choice;
	}

	int buyerMainMenu(structs::User loggedIn){
		bool valid = false;
		int choice = -1;
		do{
			title();
			std::cout << "Logged in as: " << loggedIn.username << std::endl << std::endl;
			std::cout << "Buyer Menu" << std::endl;
	        std::cout << "1 - Buy Product(s) <<UNDER CONSTRUCTION>>" << std::endl;
	        std::cout << "2 - View Order List" << std::endl;
	        std::cout << "3 - Logout" << std::endl << std::endl;
	        
			std::cout << "Enter choice: ";
			std::cin >> choice;
	        
	        //Check if valid input (i.e., 1-3)
	        if(choice >= 1 && choice <= 3)
	        	valid = true;
		}while(!valid);
		return choice;
	}
	
	void buyerViewOrders(std::vector<structs::Transaction> transactions, structs::User buyer){
		title();
		std::cout << "Buyer Account: " << buyer.username << std::endl << std::endl;
		
		float orderTotal = 0.00; //will only count those completed orders
		float pendingOrderTotal = 0.00; //will only count those uncompleted orders
		for(int i = 0; i < transactions.size(); i++){
			if(transactions[i].buyer.username == buyer.username){
				std::cout << "[Order No. " << transactions[i].orderNo << "] - " << transactions[i].productName << " (Quantity: "  << transactions[i].quantity << "):  PHP " << transactions[i].subtotal << std::endl;
				if(transactions[i].completed)
					orderTotal += transactions[i].subtotal;
				else
					pendingOrderTotal += transactions[i].subtotal;
			}
		}
		std::cout << std::endl;
		std::cout << "Completed Order Total: PHP" << orderTotal << std::endl;
		std::cout << "Pending Order Total: PHP" << pendingOrderTotal << std::endl;
		anykey();
	}
	
	structs::Transaction buyerBuyProduct(float orderNo, structs::User buyer, std::vector<structs::Product> products){
		title();
		std::cout << "Buyer Account: " << buyer.username << std::endl << std::endl;
		
		structs::Transaction newOrder = structs::Transaction();
		
		bool valid = false;
		do{
			title();
			for(int i = 0; i < products.size(); i++){
				if(products[i].quantity > 0)
					std::cout << i+1 << " - " << products[i].productName << " (Quantity Left: " << products[i].quantity << ") = PHP " << products[i].price << std::endl;
			}
			std::cout << std::endl;
			
			int choice = -1;
			std::cout << "Enter item no. (Enter 0 to cancel): ";
			std::cin >> choice;
			
			if(choice == 0){ //Cancel order attempt
				return newOrder;
			}else if(choice > products.size() || choice <= 0){ //Invalid
				std::cout << std::endl << "Invalid entry, try again!" << std::endl;
				anykey();
			}else{ //Valid
				newOrder.buyer = buyer;
				newOrder.orderNo = orderNo;
				newOrder.productName = products[choice-1].productName;
				
				title();
				std::cout << "Product: " << products[choice-1].productName << "(Quantity: " << products[choice-1].quantity << ")" << std::endl;
				std::cout << "Enter quantity: ";
				std::cin >> newOrder.quantity;
				
				newOrder.subtotal = newOrder.quantity * products[choice-1].price;
				
				bool validTender = false;
				do{
					title();
					std::cout << "Product: " << products[choice-1].productName << std::endl;
					std::cout << "Quantity: " << newOrder.quantity << std::endl;
					std::cout << "Subtotal: PHP" << newOrder.subtotal << std::endl;
					std::cout << "Enter amount tender: PHP ";
					std::cin >> newOrder.tender;
					
					if(newOrder.tender < newOrder.subtotal){
						std::cout << "Invalid amount tender!";
						anykey();
					}else
						validTender = true;
				}while(!validTender);
				
				newOrder.change = newOrder.tender - newOrder.subtotal;
				newOrder.completed = false;
				
				title();
				std::cout << "==Order Details==" << std::endl;
				std::cout << "Product: " << products[choice-1].productName << std::endl;
				std::cout << "Quantity: " << newOrder.quantity << std::endl;
				std::cout << "Subtotal: PHP" << newOrder.subtotal << std::endl;
				std::cout << "Tender: PHP" << newOrder.tender << std::endl;
				std::cout << "Change: PHP" << newOrder.change << std::endl;
				anykey();
				
				valid = true;
				return newOrder;
			}
		}while(!valid);	
		return newOrder;
	}
		
	void sellerViewProducts(structs::User seller, std::vector<structs::Product> products){
		title();
		std::cout << "Seller Account: " << seller.username << std::endl << std::endl;
		
		int itemCtr = 1;
		float stockTotal = 0.00;
		std::cout << "Products List: " << std::endl;
		for(int i = 0; i < products.size(); i++){
			if(products[i].seller.username == seller.username){
				std::cout << itemCtr << "    " << products[i].productName << " - PHP" << products[i].price << " (" << products[i].quantity << " left)" << std::endl;
				stockTotal += products[i].price * products[i].quantity;
				itemCtr++;
			}
		}
		std::cout << std::endl << std::endl;
		std::cout << "Total Amount of Products in Stock/Unsold: PHP " << stockTotal;
		
		anykey();
	}
	
	structs::Product createNewProduct(structs::User seller){
		title();
		std::cout << "Create New Product" << std::endl << std::endl;
		
		structs::Product p = structs::Product();
		
		p.productName = getStringWSpace("Enter new product name: ");
		std::cout << "Enter new product price (PHP): ";
		std::cin >> p.price;
		std::cout << "Enter new product quantity: ";
		std::cin >> p.quantity;
		p.seller = seller;
		
		return p;
	}
	
	void sellerViewOrders(structs::User seller, std::vector<structs::Transaction> transactions){
		title();
		std::cout << "Seller Account: " << seller.username << std::endl << std::endl;
		
		float salesTotal = 0.00; //will only count those completed orders
		float pendingSalesTotal = 0.00; //will only count those uncompleted orders
		for(int i = 0; i < transactions.size(); i++){
			if(transactions[i].seller.username == seller.username){
				std::cout << "[Order No. " << transactions[i].orderNo << "] - " << transactions[i].productName << " (Quantity: "  << transactions[i].quantity << "):  PHP " << transactions[i].subtotal << std::endl;
				if(transactions[i].completed)
					salesTotal += transactions[i].subtotal;
				else
					pendingSalesTotal += transactions[i].subtotal;
			}
		}
		std::cout << std::endl;
		std::cout << "Completed Sales Total: PHP" << salesTotal << std::endl;
		std::cout << "Pending Sales Total: PHP" << pendingSalesTotal << std::endl;
		anykey();
	}
	
	//The only change that can be done really is the 'completed' part of the transaction.
	//Transactions aren't meant to be modified in any other way nor cannot be deleted.
	//It does not actually conduct the modification, rather it collects information on which orders will be modified (i.e., set complete or not).
	structs::ModifyOrder sellerEditOrder(structs::User seller, std::vector<structs::Transaction> transactions){
		structs::ModifyOrder modification = structs::ModifyOrder();
		
		std::string completion = "";
		bool valid = false;
		do{
			title();
			std::cout << "Seller Account: " << seller.username << std::endl << std::endl;
			
			//List Items
			float pendingSalesTotal = 0.00;
			for(int i = 0; i < transactions.size(); i++){
				if(transactions[i].seller.username == seller.username && !transactions[i].completed){
					std::cout << "[Order No. " << transactions[i].orderNo << "] - " << transactions[i].productName << " (Quantity: "  << transactions[i].quantity << "):  PHP " << transactions[i].subtotal << std::endl;
					pendingSalesTotal += transactions[i].subtotal;
				}
			}
			std::cout << std::endl;
			std::cout << "Pending Sales Total: PHP" << pendingSalesTotal << std::endl;
			
			std::cout << std::endl;
			std::cout << "Enter Order No. (Enter -1 to cancel): ";
			std::cin >> modification.orderNo;
			
			if(modification.orderNo == -1){
				valid = true;
			}else{
				std::cout << "Order Completion (Y/N): ";
				std::cin >> completion;
				if(completion == "Y" || completion == "y")
					modification.completed = true;
				for(int i = 0; i < transactions.size(); i++){
					if(transactions[i].seller.username == seller.username && transactions[i].orderNo == modification.orderNo){
						valid = true;
						return modification;
					}
				}
			}
		}while(!valid);
		modification.orderNo = -1;
		return modification;
	}
}

#endif
