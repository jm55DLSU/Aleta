#ifndef STRUCTS_H_
#define STRUCTS_H_

#include <iostream>
#include <string>
#include <conio.h>

namespace structs{
	struct User{
		std::string username;
		std::string password;
		std::string name; //Formal Name
		bool isSeller;
	};
	
	struct Product{
		std::string productName;
		float price;
		int quantity;
		struct User seller;
	};
	
	struct Transaction{
		std::string productName;
		int quantity;
		float subtotal;
		float tender;
		float change;
		struct User buyer;
		struct User seller;
		bool completed;
		float orderNo;
	};
	
	struct ModifyOrder{
		float orderNo = -1;
		bool completed = false;
	};
}

#endif
