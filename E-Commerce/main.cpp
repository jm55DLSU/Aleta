/**
=======================
DEVELOPED AND EXECUTED ON DEV-C++ 5.11

USING TDM-GCC 4.9.2 64-BIT RELEASE

NOTICE, KINDLY DO THE FOLLOWING FIRST 
BEFORE ATTEMPTING TO COMPILE AND RUN:

1. CLICK TOOLS > COMPILER OPTIONS
2. CLICK GENERAL
3. CHECK "Add the following commands when calling the compiler"
4. ADD: -std=c++11
5. CLICK "OK"

METHODS OF COMPILING: USE F9
METHODS OF COMPILING AND RUNNING: USE F11 OR F12 (USE F12 IF ANY CHANGES MADE AREN'T SEEN WHEN RUNNING)

ESCALONA, J.M. 
=======================
**/

#include <iostream>
#include <string>
#include <conio.h>
#include <vector>
#include <array>

#include "interface.h"
#include "structs.h"

using namespace std;
using namespace structs;

std::vector<User> generateUsers(int size){
	//Create vector for users(kinda mix of arrays and lists)
	std::vector<User> users;
	
	//Create "placeholders" for users
	for(int i = 0; i < size; i++)
		users.push_back(User());
		
	//Create user pool
	//Format: {username, password, name, isSeller}
	users[0] = {"nenechi_holo", "abcdefg", "Momosuzu Nene", false};
	users[1] = {"milli_niji", "aBcDeFg", "Millie Parfait", true};
	users[2] = {"ouroo_kronii", "holos_chronos", "Ouro Kronii", false};
	users[3] = {"sora_tokino", "13knights", "Tokino Sora", true};
	users[4] = {"rawr_gura", "doumosamedesu", "Gawr Gura", false};
	
	//Return generated users.
	return users;
}

std::vector<Product> generateProducts(int size, std::vector<User> users){
	//Create vector for products
	std::vector<Product> products;
	
	//Create "placeholders" for products
	for(int i = 0; i < size; i++)
		products.push_back(Product());
	
	//Create product pool
	//Format: {productName, price, quantity, seller (User)}
	products[0] = {"Millie's Hat", 250.00, 83, users[1]}; //sold by Millie Parfait
	products[1] = {"Nijisanji T-Shirt", 450.00, 100, users[1]}; //sold by Millie Parfait
	products[2] = {"Sora Lightsticks", 300.50, 28, users[3]}; //sold by Tokino Sora
	products[3] = {"Hololive Pin", 120.00, 1000, users[3]}; //sold by Tokino Sora
	
	//Return generated products
	return products;
}

std::vector<Transaction> generateTransactions(int size, std::vector<User> users, std::vector<Product> products){
	std::vector<Transaction> transactions;
	
	for(int i = 0; i < size; i++)
		transactions.push_back(Transaction());
	
	//Ideally, this can be in a form of a list rather than an array/vector.
	
	//Format: {productName, quantity, subtotal, buyer (User), seller (User), completed, orderNo}
	transactions[0] = {products[0].productName, 7, products[0].price * 7, 2000.00, 250.00, users[2], users[1], true, 1};
	transactions[1] = {products[1].productName, 10, products[1].price * 10, 4500.00, 0, users[0], users[1], false, 2};
	transactions[2] = {products[2].productName, 2, products[2].price * 2, 605.00, 4.00, users[4], users[3], true, 3};
	
	return transactions;
}

bool isSeller(string loggedInUsername, std::vector<User> users){
	for(int i = 0; i < users.size(); i++)
		if(users[i].username == loggedInUsername && users[i].isSeller)
			return true;
	return false;
}

User getUser(std::vector<User> users, string username){
	for(int i = 0; i < users.size(); i++)
		if(users[i].username == username)
			return users[i];
	return User();
}

int getNewOrderNo(std::vector<Transaction> transactions){
	int highestOrderNo = -1;
	for(int i = 0; i < transactions.size(); i++){
		if(transactions[i].orderNo > highestOrderNo)
			highestOrderNo = transactions[i].orderNo;
	}
	return highestOrderNo + 1; //Indicate the next order no.
}

int main() {
	string loggedIn = "";
	std::vector<User> users = generateUsers(5);
	std::vector<Product> products = generateProducts(4, users);
	std::vector<Transaction> transactions = generateTransactions(3, users, products);
	
	bool run = true;
	bool loggedin = true;
	do{
		//Login Menu Controls (1st Page; Will only ask if not logged-in)
		if(loggedIn == ""){
			int c = interface::loginScreen();
			switch(c){
				case 0:{ //Exit
					run = false;
					break;
				}
				case 1:{ //Login
					loggedIn = interface::login(users);
					interface::title();
					if(loggedIn == "")
						cout << "Invalid login credentials!" << endl;
					else
						cout << "Welcome " << loggedIn << "!";
					interface::anykey();
					break;
				}
				case 2:{
					interface::title();
					cout << "<<OPTIONAL FEATURE>>" << endl << "CREATE NEW ACCOUNT" << endl;
					interface::anykey();
					break;
				}
				default:{ //Invalid hence do nothing, renew login menu cycle
					break;
				}
			}
		}else{
			if(isSeller(loggedIn, users)){ //SELLER
				int sellerAction = interface::sellerMainMenu(getUser(users, loggedIn));
				if(sellerAction == 1){ //View Products listed to logged in seller
					interface::sellerViewProducts(getUser(users, loggedIn), products);
				}else if(sellerAction == 2){ //Add Product
					//Create a new product object with its details
					Product newProduct = interface::createNewProduct(getUser(users, loggedIn));
					
					products.push_back(Product()); //Add a "placeholder" for the new product
					//Add its resulting details from newProduct
					products[products.size()-1].productName = newProduct.productName;
					products[products.size()-1].price = newProduct.price;
					products[products.size()-1].quantity = newProduct.quantity;
					products[products.size()-1].seller = getUser(users, loggedIn);
					
					//Notify user for new product
					interface::title();
					cout << "Product Added!" << endl;
					interface::anykey();
					
					//Show user of product
					interface::sellerViewProducts(getUser(users, loggedIn), products);
				}else if(sellerAction == 3){ //View Order List
					interface::sellerViewOrders(getUser(users, loggedIn), transactions);
					
				}else if(sellerAction == 4){ //Edit Order List
					ModifyOrder modify = interface::sellerEditOrder(getUser(users, loggedIn), transactions);
					
					interface::title();
					cout << "Seller Account: " << loggedIn << endl;
					for(int i = 0; i < transactions.size(); i++){
						if(transactions[i].orderNo == modify.orderNo){
							transactions[i].completed = modify.completed;
							cout << "Transaction Modification Set: " << endl << "Order No.: " << transactions[i].orderNo << endl << "Completed: ";
							if(transactions[i].completed){
								cout << "True";
								//Subtract the quantity to the product in products
								for(int j = 0; j < products.size(); j++){
									if(transactions[i].productName == products[j].productName)
										products[j].quantity -= transactions[i].quantity;
								}
							}else{
								cout << "False";
								//Add the quantity to the product in products
								for(int j = 0; j < products.size(); j++){
									if(transactions[i].productName == products[j].productName)
										products[j].quantity += transactions[i].quantity;
								}
							}
						}
					}
					interface::anykey();
				}else if(sellerAction == 5){ //Logout
					loggedIn = ""; //equates to logout
				}
			}else{ //BUYER
				int buyerAction = interface::buyerMainMenu(getUser(users, loggedIn));
				if(buyerAction == 1){ //Buy Product(s)
					Transaction order = interface::buyerBuyProduct(getNewOrderNo(transactions), getUser(users, loggedIn), products);
					if(order.orderNo != -1){						
						//New order on memory
						transactions.push_back(Transaction());
						transactions[transactions.size()-1] = order;
						
						interface::title();
						cout << "Order Submitted!" << endl;
						
						interface::anykey();
					}
				}else if(buyerAction == 2){ //View Order List
					interface::buyerViewOrders(transactions, getUser(users, loggedIn));
				}else if(buyerAction == 3){ //Logout
					loggedIn = "";
				}
			}	
		}
	}while(run);
	
	return 0;
}
