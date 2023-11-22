import tkinter as tk
from tkinter import messagebox, simpledialog
import tkinter.font as tkFont

class LoginApp: 
    def __init__(self, root):

        #setting title
        root.title("Online Buying System")
        #setting window size
        width=650
        height=500
        screenwidth = root.winfo_screenwidth()
        screenheight = root.winfo_screenheight()
        alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
        root.geometry(alignstr)
        root.resizable(width=False, height=False)

        self.WelcomeMsg=tk.Label(root)
        ft = tkFont.Font(family='Times',size=28)
        self.WelcomeMsg["font"] = ft
        self.WelcomeMsg["fg"] = "#333333"
        self.WelcomeMsg["justify"] = "center"
        self.WelcomeMsg["text"] = "Welcome User!" 
        self.WelcomeMsg.place(x=130,y=30,width=414,height=73)

        self.Username=tk.Label(root)
        ft = tkFont.Font(family='Times',size=15)
        self.Username["font"] = ft
        self.Username["fg"] = "#333333"
        self.Username["justify"] = "center"
        self.Username["text"] = "Username"
        self.Username.place(x=110,y=170,width=128,height=52)

        self.Username_Entry=tk.Entry(root)
        self.Username_Entry["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.Username_Entry["font"] = ft
        self.Username_Entry["fg"] = "#333333"
        self.Username_Entry["justify"] = "center"
        self.Username_Entry["text"] = "Username"
        self.Username_Entry.place(x=230,y=180,width=240,height=48)

        self.Password=tk.Label(root)
        ft = tkFont.Font(family='Times',size=15)
        self.Password["font"] = ft
        self.Password["fg"] = "#333333"
        self.Password["justify"] = "center"
        self.Password["text"] = "Password"
        self.Password.place(x=110,y=250,width=128,height=52)

        self.Password_Entry=tk.Entry(root)
        self.Password_Entry["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.Password_Entry["font"] = ft
        self.Password_Entry["fg"] = "#333333"
        self.Password_Entry["justify"] = "center"
        self.Password_Entry["text"] = "Password"
        self.Password_Entry.config(show="*")
        self.Password_Entry.place(x=230,y=250,width=240,height=48)

        self.Login_Button=tk.Button(root)
        self.Login_Button["bg"] = "#f0f0f0"
        self.Login_Button["activebackground"] = "#393d49"
        self.Login_Button["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.Login_Button["font"] = ft
        self.Login_Button["fg"] = "#000000"
        self.Login_Button["justify"] = "center"
        self.Login_Button["text"] = "Login"
        self.Login_Button.place(x=190,y=340,width=126,height=34)
        self.Login_Button["command"] = self.Login_Button_command

        self.Logout_Button=tk.Button(root)
        self.Logout_Button["bg"] = "#f0f0f0"
        self.Logout_Button["activebackground"] = "#393d49"
        self.Logout_Button["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.Logout_Button["font"] = ft
        self.Logout_Button["fg"] = "#000000"
        self.Logout_Button["justify"] = "center"
        self.Logout_Button["text"] = "Logout"
        self.Logout_Button.place(x=380,y=340,width=126,height=34)
        self.Logout_Button["command"] = self.Logout_Button_command

    def Login_Button_command(self): #Login
        print("Login")
        username = self.Username_Entry.get().lower()
        password  = self.Password_Entry .get()

        # credentials check
        if username == "seller" and password == "password":
            self.open_supermarket()
        elif username== "customer" and password == "password":
            print("Customer login successful.")
            # code for customerpage
            self.open_CustomerPage()
        else:
            print("Login failed. Please try again.")
            messagebox.showerror("Login Failed", "Incorrect credentials. Please try again.")

    def Logout_Button_command(self): #Logout
        print("Logout")
        root.deiconify()

    def open_supermarket(self):
        root.withdraw()  # Hide the login window
        supermarket_root = tk.Tk()
        app = SupermarketApp(supermarket_root, self)  # Pass a reference to LoginApp
        supermarket_root.mainloop()

    def open_CustomerPage(self):
        root.withdraw()  # Hide the login window
        customer_root = tk.Tk()
        app = CustomerApp(customer_root, self)  # Pass a reference to LoginApp
        customer_root.mainloop()

    def show_login(self):
        root.deiconify()  # Show the login window again
        # Additional cleanup or setup code can be added here
        
class SupermarketApp:
    def __init__(self, root, login_app_instance):
        self.server = Server()

        #setting title
        root.title("Online Buying System")
        #setting window size
        width=900
        height=600
        screenwidth = root.winfo_screenwidth()
        screenheight = root.winfo_screenheight()
        alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
        root.geometry(alignstr)
        root.resizable(width=False, height=False)
        
        self.AddProduct_SellerBtn=tk.Button(root)  #BUTTON OF ADD PRODUCT FOR SELLER
        self.AddProduct_SellerBtn["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.AddProduct_SellerBtn["font"] = ft
        self.AddProduct_SellerBtn["fg"] = "#000000"      
        self.AddProduct_SellerBtn["justify"] = "center"
        self.AddProduct_SellerBtn["text"] = "Add to Supermarket"
        self.AddProduct_SellerBtn.place(x=360,y=50,width=160,height=30)
        self.AddProduct_SellerBtn["command"] = self.AddProduct_SellerBtn_command

        self.Title=tk.Label(root)
        self.Title["cursor"] = "watch"
        ft = tkFont.Font(family='Times',size=22)
        self.Title["font"] = ft  #TITLE DESIGN OF GUI SELLER INTER
        self.Title["fg"] = "#333333"
        self.Title["justify"] = "left"
        self.Title["text"] = "Supermarket"
        self.Title.place(x=0,y=10,width=200,height=25)

        self.ProductName=tk.Entry(root)
        self.ProductName["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.ProductName["font"] = ft
        self.ProductName["fg"] = "#333333"
        self.ProductName["justify"] = "center"
        self.ProductName["text"] = "Product Name"
        self.ProductName.place(x=150,y=50,width=200,height=30)

        self.Php=tk.Entry(root)
        self.Php["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.Php["font"] = ft
        self.Php["fg"] = "#333333"
        self.Php["justify"] = "center"
        self.Php["text"] = "Php 0.00"
        self.Php.place(x=150,y=90,width=200,height=30)

        self.ProductName_Entry=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.ProductName_Entry["font"] = ft
        self.ProductName_Entry["fg"] = "#333333"
        self.ProductName_Entry["justify"] = "left"
        self.ProductName_Entry["text"] = "Product Name"
        self.ProductName_Entry.place(x=20,y=50,width=130,height=30)

        self.ProductPrice=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.ProductPrice["font"] = ft
        self.ProductPrice["fg"] = "#333333"
        self.ProductPrice["justify"] = "left"
        self.ProductPrice["text"] = "Product Price"
        self.ProductPrice.place(x=20,y=90,width=130,height=30)

        self.ProductQuantity=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.ProductQuantity["font"] = ft
        self.ProductQuantity["fg"] = "#333333"
        self.ProductQuantity["justify"] = "left"
        self.ProductQuantity["text"] = "Product Quantity"
        self.ProductQuantity.place(x=20,y=130,width=130,height=30)

        self.Pcs=tk.Entry(root)
        self.Pcs["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.Pcs["font"] = ft
        self.Pcs["fg"] = "#333333"
        self.Pcs["justify"] = "center"
        self.Pcs["text"] = "0 pc(s)"
        self.Pcs.place(x=150,y=130,width=200,height=30)

        self.RemoveBtn_Seller=tk.Button(root)
        self.RemoveBtn_Seller["bg"] = "#f0f0f0"
        self.RemoveBtn_Seller["activebackground"] = "#393d49"
        self.RemoveBtn_Seller["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.RemoveBtn_Seller["font"] = ft
        self.RemoveBtn_Seller["fg"] = "#000000"
        self.RemoveBtn_Seller["justify"] = "center"
        self.RemoveBtn_Seller["text"] = "Remove Product"
        self.RemoveBtn_Seller.place(x=520,y=50,width=160,height=30)
        self.RemoveBtn_Seller["command"] = self.RemoveBtn_Seller_command

        self.ProductList_Box=tk.Text(root) #output
        self.ProductList_Box.bind("<Key>", lambda e: "break")
        self.ProductList_Box["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.ProductList_Box["font"] = ft
        self.ProductList_Box["fg"] = "#333333"
        #self.ProductList_Box["justify"] = "left"
        self.ProductList_Box.place(x=20,y=210,width=713,height=200)
       # Create a vertical scrollbar
        self.scrollbar = tk.Scrollbar(root, command=self.ProductList_Box.yview)
        self.scrollbar.place(x=733, y=210, height=200)

        self.updateProductList_BoxGUI(self.server.getProductList())

        self.ProductList=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.ProductList["font"] = ft
        self.ProductList["fg"] = "#333333"
        self.ProductList["justify"] = "left"
        self.ProductList["text"] = "Product List"
        self.ProductList.place(x=20,y=170,width=150,height=30)

        self.SortButton=tk.Button(root)
        self.SortButton["bg"] = "#f0f0f0"
        self.SortButton["activebackground"] = "#393d49"
        self.SortButton["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.SortButton["font"] = ft
        self.SortButton["fg"] = "#000000"
        self.SortButton["justify"] = "center"
        self.SortButton["text"] = "Sort"
        self.SortButton.place(x=680,y=50,width=70,height=30)
        self.SortButton["command"] = self.SortButton_command

        self.LogoutBtn_Seller = tk.Button(root)
        self.LogoutBtn_Seller["bg"] = "#f0f0f0"
        self.LogoutBtn_Seller["activebackground"] = "#393d49"
        self.LogoutBtn_Seller["activeforeground"] = "#f4edf1"
        self.LogoutBtn_Seller["font"] = ft
        self.LogoutBtn_Seller["fg"] = "#000000"
        self.LogoutBtn_Seller["justify"] = "center"
        self.LogoutBtn_Seller["text"] = "Logout"
        self.LogoutBtn_Seller.place(x=750, y=50, width=150, height=30)
        self.LogoutBtn_Seller["command"] = self.logout


        self.TransactionBox = tk.Text(root)  # transaction textbox
        self.TransactionBox.bind("<Key>", lambda e: "break")
        self.TransactionBox["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times', size=10)
        self.TransactionBox["font"] = ft
        self.TransactionBox["fg"] = "#333333"
        self.TransactionBox.place(x=20, y=430, width=713, height=80)

        self.TransactionButton = tk.Button(root)
        self.TransactionButton["bg"] = "#f0f0f0"
        self.TransactionButton["activebackground"] = "#393d49"
        self.TransactionButton["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times', size=10)
        self.TransactionButton["font"] = ft
        self.TransactionButton["fg"] = "#000000"
        self.TransactionButton["justify"] = "center"
        self.TransactionButton["text"] = "Perform Transaction"
        self.TransactionButton.place(x=20, y=520, width=160, height=30)
        self.TransactionButton["command"] = self.TransactionButton_command

        self.login_app_instance = login_app_instance
        self.supermarket_root = root

        # ... (unchanged)

    def TransactionButton_command(self): # transaction command
        print("perform transaction")
        transaction_text = self.TransactionBox.get(1.0, tk.END)
        # Process the transaction_text as needed
        print("Transaction:", transaction_text)
    
    def AddProduct_SellerBtn_command(self): #add
        print("add")
        userInput = self.getInputs()
        self.server.addProduct(userInput)
        self.updateProductList_BoxGUI(self.server.getProductList())

    def RemoveBtn_Seller_command(self): #remove
        print("remove")
        userInput = self.getInputs()
        self.server.removeProduct(userInput[0])
        self.updateProductList_BoxGUI(self.server.getProductList())

    def SortButton_command(self):
        print("sort")
        self.server.sortProduct()
        self.updateProductList_BoxGUI(self.server.getProductList())

    def logout(self):
        # Call the logout method in the LoginApp instance
        self.login_app_instance.show_login()

        # Close the supermarket window
        self.supermarket_root.destroy()

    def getInputs(self):
        prodName = self.ProductName.get()
        prodPrice = self.Php.get()
        prodQuan = self.Pcs.get()
        return [prodName, prodPrice, prodQuan]
    
    def updateProductList_BoxGUI(self, product_list):
        self.ProductList_Box.delete(1.0, tk.END)
        for idx, p in enumerate(product_list):
            self.ProductList_Box.insert(
                tk.END, str(p[0] + " - Php " + p[1] + " @ " + p[2] + "pc(s).\n")
            )
    
class CustomerApp:
    def __init__(self, root, customer_app_instance):
        self.cart = []
        self.server = Server()

        #setting title
        root.title("Online Buying System")
        #setting window size
        width=750
        height=600
        screenwidth = root.winfo_screenwidth()
        screenheight = root.winfo_screenheight()
        alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
        root.geometry(alignstr)
        root.resizable(width=False, height=False)

        self.WelcomeCustomer=tk.Label(root)
        ft = tkFont.Font(family='Times',size=28)
        self.WelcomeCustomer["font"] = ft
        self.WelcomeCustomer["fg"] = "#333333"                    
        self.WelcomeCustomer["justify"] = "center"
        self.WelcomeCustomer["text"] = "Welcome Customer!"
        self.WelcomeCustomer.place(x=0,y=0,width=292,height=67)

        self.ProductName_Customer=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.ProductName_Customer["font"] = ft
        self.ProductName_Customer["fg"] = "#333333"
        self.ProductName_Customer["justify"] = "center"
        self.ProductName_Customer["text"] = "Product Name"
        self.ProductName_Customer.place(x=10,y=90,width=128,height=34)

        self.ProductPrice_Customer=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.ProductPrice_Customer["font"] = ft
        self.ProductPrice_Customer["fg"] = "#333333"
        self.ProductPrice_Customer["justify"] = "center"
        self.ProductPrice_Customer["text"] = "Product Price"
        self.ProductPrice_Customer.place(x=10,y=140,width=128,height=34)

        self.Quantity_Customer=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.Quantity_Customer["font"] = ft
        self.Quantity_Customer["fg"] = "#333333"
        self.Quantity_Customer["justify"] = "center"
        self.Quantity_Customer["text"] = "Enter Quantity"
        self.Quantity_Customer.place(x=10,y=190,width=128,height=34)

        self.GlineEdit_980=tk.Entry(root)
        self.GlineEdit_980["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GlineEdit_980["font"] = ft
        self.GlineEdit_980["fg"] = "#333333"
        self.GlineEdit_980["justify"] = "center"
        self.GlineEdit_980["text"] = "prodname"
        self.GlineEdit_980.place(x=160,y=90,width=400,height=35)

        self.EnterQuantity_Customer=tk.Entry(root)
        self.EnterQuantity_Customer["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.EnterQuantity_Customer["font"] = ft
        self.EnterQuantity_Customer["fg"] = "#333333"
        self.EnterQuantity_Customer["justify"] = "center"
        self.EnterQuantity_Customer["text"] = "EnterQuantity"
        self.EnterQuantity_Customer.place(x=160,y=190,width=400,height=35)

        self.QuantityEntry_Customer=tk.Entry(root, width=30)
        self.QuantityEntry_Customer.bind("<Key>", lambda e: "break")
        ft = tkFont.Font(family='Times',size=10)
        self.QuantityEntry_Customer["font"] = ft
        self.QuantityEntry_Customer["fg"] = "#333333"
        self.QuantityEntry_Customer["justify"] = "center"
        self.QuantityEntry_Customer.place(x=160,y=140,width=400,height=30)

        self.AddButton_Customer=tk.Button(root)
        self.AddButton_Customer["bg"] = "#f0f0f0"
        self.AddButton_Customer["activebackground"] = "#393d49"
        self.AddButton_Customer["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.AddButton_Customer["font"] = ft
        self.AddButton_Customer["fg"] = "#000000"
        self.AddButton_Customer["justify"] = "center"
        self.AddButton_Customer["text"] = "Add"
        self.AddButton_Customer.place(x=590,y=90,width=124,height=34)
        self.AddButton_Customer["command"] = self.AddButton_Customer_command

        self.RemoveButton_Customer=tk.Button(root)
        self.RemoveButton_Customer["bg"] = "#f0f0f0"
        self.RemoveButton_Customer["activebackground"] = "#393d49"
        self.RemoveButton_Customer["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.RemoveButton_Customer["font"] = ft
        self.RemoveButton_Customer["fg"] = "#000000"
        self.RemoveButton_Customer["justify"] = "center"
        self.RemoveButton_Customer["text"] = "Remove"
        self.RemoveButton_Customer.place(x=590,y=140,width=124,height=34)
        self.RemoveButton_Customer["command"] = self.RemoveButton_Customer_command

        self.SearchButton_Customer=tk.Button(root)
        self.SearchButton_Customer["bg"] = "#f0f0f0"
        self.SearchButton_Customer["activebackground"] = "#393d49"
        self.SearchButton_Customer["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.SearchButton_Customer["font"] = ft
        self.SearchButton_Customer["fg"] = "#000000"
        self.SearchButton_Customer["justify"] = "center"
        self.SearchButton_Customer["text"] = "Search"
        self.SearchButton_Customer.place(x=590,y=190,width=124,height=34)
        self.SearchButton_Customer["command"] = self.SearchButton_Customer_command

         # Add Logout Button
        self.Logout_Button=tk.Button(root)
        self.Logout_Button["bg"] = "#f0f0f0"
        self.Logout_Button["activebackground"] = "#393d49"
        self.Logout_Button["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.Logout_Button["font"] = ft
        self.Logout_Button["fg"] = "#000000"
        self.Logout_Button["justify"] = "center"
        self.Logout_Button["text"] = "Logout"
        self.Logout_Button.place(x=590,y=240,width=126,height=34)
        self.Logout_Button["command"] = self.logout

        self.Checkout_Button=tk.Button(root)
        self.Checkout_Button["bg"] = "#f0f0f0"
        self.Checkout_Button["activebackground"] = "#393d49"
        self.Checkout_Button["activeforeground"] = "#f4edf1"
        ft = tkFont.Font(family='Times',size=10)
        self.Checkout_Button["font"] = ft
        self.Checkout_Button["fg"] = "#000000"
        self.Checkout_Button["justify"] = "center"
        self.Checkout_Button["text"] = "Checkout"
        self.Checkout_Button.place(x=590,y=290,width=126,height=34)
        self.Checkout_Button["command"] = self.checkout_command

        self.ProductList_Customer=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.ProductList_Customer["font"] = ft
        self.ProductList_Customer["fg"] = "#333333"
        self.ProductList_Customer["justify"] = "center"
        self.ProductList_Customer["text"] = "Product List:"
        self.ProductList_Customer.place(x=0,y=260,width=128,height=40)
 
        self.ProductList_Box=tk.Text(root) #output
        self.ProductList_Box.bind("<Key>", lambda e: "break")
        self.ProductList_Box["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.ProductList_Box["font"] = ft
        self.ProductList_Box["fg"] = "#333333"
        self.ProductList_Box.place(x=20, y=300, width=500, height=120)
        self.scrollbar = tk.Scrollbar(root, command=self.ProductList_Box.yview, troughcolor= "#FFC0CB")
        self.scrollbar.place(x=510, y=300, height=120)
        # Configure the Text widget to use the scrollbar
        self.ProductList_Box.config(yscrollcommand=self.scrollbar.set)

        self.updateProductList_BoxGUI(self.server.getProductList())
        self.Glabellogin_app_instance = customer_app_instance

        self.Cart=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.Cart["font"] = ft
        self.Cart["fg"] = "#333333"
        self.Cart["justify"] = "center"
        self.Cart["text"] = "Cart"
        self.Cart.place(x=-30,y=420,width=128,height=40)

        self.CartBox=tk.Text(root) #output
        self.CartBox.bind("<Key>", lambda e: "break")
        self.CartBox["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.CartBox["font"] = ft
        self.CartBox["fg"] = "#333333"
        #self. CartBox["justify"] = "left"
        self.CartBox.place(x=20,y=450,width=500,height=145)
       # Create a vertical scrollbar
        self.scrollbar = tk.Scrollbar(root, command=self.ProductList_Box.yview)
        self.scrollbar.place(x=520, y=455, height=140)

        self.login_app_instance = login_app
        self.customer_root = root

    def getInputs(self):
     prodName = self.GlineEdit_980.get()
     prodQuan = self.EnterQuantity_Customer.get()
     return [prodName, prodQuan]

    def updateProductList_BoxGUI(self, product_list):
        self.ProductList_Box.delete(1.0, tk.END) 
        for idx, p in enumerate(product_list):
            self.ProductList_Box.insert(tk.END, str(p[0] + " - Php " + str(p[1]) + " @ " + str(p[2]) + "pc(s).\n"))

    def getProductName(self):
        return str(self.GlineEdit_980.get())

    def getQuantity(self):
        return int(self.EnterQuantity_Customer.get())
        
    def addtoCart(self, product):
        if product != None:
            self.cart.append(product)
            return True
        return False #not added, probably due to product is a None

    def removeProduct(self, products, userInput): #backend
        product_name = userInput[0]
        found = False
        for product in products:
            if product[0] == product_name:
                products.remove(product)
                found = True
                print("Product removed successfully.")
        if not found:
            print("Product not found.")
        return products #Returns updated products list
    
    def get_personal_info(self):
        name = simpledialog.askstring("Personal Information", "Enter your name:")
        if name is not None:
            address = simpledialog.askstring("Personal Information", "Enter your address:")
            if address is not None:
                phone_number = simpledialog.askstring("Personal Information", "Enter your phone number:")
                if phone_number is not None:
                    return [name, address, phone_number]
        return None
    
    def AddButton_Customer_command(self):
     print("Add to Cart")
     product = self.server.searchProduct(self.getProductName())
     if product is not None:
        # Assuming the product_info contains the product name, price, and quantity
        product[2] = int(self.getQuantity())
        product[1] *= float(self.getQuantity())
        cart_info = f"Product: {product[0]}, Price: {product[1]:0.2f}, Quantity: {product[2]}"
        self.CartBox.insert(tk.END, cart_info + "\n")
        self.CartBox.see(tk.END)
        if self.addtoCart(product):
            print("Added to cart")
        else:
            print("Not added to cart")
     else:
        print("Error: Product not Found.")
        messagebox.showerror("Online Buying", "Product not Found.")
        
    def RemoveButton_Customer_command(self):
      print("Remove From Cart")
      userInput = self.getInputs()
      #self.removeProduct(userInput[0])

    def SearchButton_Customer_command(self):
     print("Search Product")
     p = self.server.searchProduct(self.getProductName())
     if p is not None and len(p) >= 2:
        self.QuantityEntry_Customer.delete(0, tk.END) #this is price
        self.QuantityEntry_Customer.insert(0, f"{float(p[1]):0.2f}")

    def checkout_command(self):
        #Call the function to get personal info
        self.server.addTransaction(self.get_personal_info(), self.cart)
        print(self.server.getProductList()) #replace with update the supermarketboxGUI

    def logout(self):
     self.login_app_instance.show_login()
     self.customer_root.destroy()


    def show_login(self):
        root.deiconify()

class Server:
    product_list = [
            ["Product1", 10.00, 5],
            ["Product2", 20.00, 3],
            ["Product3", 15.00, 7],
            ["aProduct3", 18.25, 9],
            # Add more products as needed
    ]
    transaction_list = []
    def __init__(self):
        a = 0 #fill-in
        print(Server.product_list)
    
    def addProduct(self, userInput): #who will call this: supermarket
        Server.product_list.append([userInput[0], float(userInput[1]), int(userInput[2])])
        return 1
    
    def searchProduct(self, name:str):
        for p in Server.product_list:
            if p[0] == name:
                return [p[0] + "", p[1] + 0, p[2] + 0] 
        return None
    
    def removeProduct(self, name:str):
        found = False
        for product in Server.product_list:
            if product[0] == name:
                Server.product_list.remove(product)
                found = True
                return 1
        if not found:
            print("Product not found.")
        return 0
    
    def sortProduct(self):
        Server.product_list = sorted(Server.product_list, key=lambda item: item[1])

    def getProductList(self):
        return Server.product_list

    def addTransaction(self, info, cart):
        print(info)
        print(cart)
        print(self.product_list)
        print("")
        #update the server's product list
        for item in cart: 
            for stock in range(len(Server.product_list)):
                if Server.product_list[stock][0] == item[0]:
                    print(Server.product_list[stock], item)
                    Server.product_list[stock][2] -= item[2]
                    print(Server.product_list[stock], item)
        #update the server's transaction list

def on_closing():
    if messagebox.askokcancel("Quit", "Do you want to quit?"):
        root.destroy()

if __name__ == "__main__":
    root = tk.Tk()
    root.protocol("WM_DELETE_WINDOW", on_closing)
    login_app = LoginApp(root)
    root.mainloop()
    