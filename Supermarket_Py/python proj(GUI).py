import tkinter as tk
from tkinter import messagebox
import tkinter.font as tkFont

class LoginApp: 
    def __init__(self, root):
        #setting title
        root.title("undefined")
        #setting window size
        width=600
        height=500
        screenwidth = root.winfo_screenwidth()
        screenheight = root.winfo_screenheight()
        alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
        root.geometry(alignstr)
        root.resizable(width=False, height=False)

        self.GLabel_240=tk.Label(root)
        ft = tkFont.Font(family='Times',size=28)
        self.GLabel_240["font"] = ft
        self.GLabel_240["fg"] = "#333333"
        self.GLabel_240["justify"] = "center"
        self.GLabel_240["text"] = "Welcome User!"
        self.GLabel_240.place(x=100,y=30,width=414,height=73)

        self.GLabel_464=tk.Label(root)
        ft = tkFont.Font(family='Times',size=15)
        self.GLabel_464["font"] = ft
        self.GLabel_464["fg"] = "#333333"
        self.GLabel_464["justify"] = "center"
        self.GLabel_464["text"] = "Username"
        self.GLabel_464.place(x=110,y=170,width=128,height=52)

        self.GLineEdit_902=tk.Entry(root)
        self.GLineEdit_902["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GLineEdit_902["font"] = ft
        self.GLineEdit_902["fg"] = "#333333"
        self.GLineEdit_902["justify"] = "center"
        self.GLineEdit_902["text"] = "Username"
        self.GLineEdit_902.place(x=230,y=180,width=240,height=48)

        self.GLabel_126=tk.Label(root)
        ft = tkFont.Font(family='Times',size=15)
        self.GLabel_126["font"] = ft
        self.GLabel_126["fg"] = "#333333"
        self.GLabel_126["justify"] = "center"
        self.GLabel_126["text"] = "Password"
        self.GLabel_126.place(x=110,y=250,width=128,height=52)

        self.GLineEdit_125=tk.Entry(root)
        self.GLineEdit_125["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GLineEdit_125["font"] = ft
        self.GLineEdit_125["fg"] = "#333333"
        self.GLineEdit_125["justify"] = "center"
        self.GLineEdit_125["text"] = "Password"
        self.GLineEdit_125.place(x=230,y=250,width=240,height=48)

        self.GButton_53=tk.Button(root)
        self.GButton_53["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_53["font"] = ft
        self.GButton_53["fg"] = "#000000"
        self.GButton_53["justify"] = "center"
        self.GButton_53["text"] = "Login"
        self.GButton_53.place(x=190,y=340,width=126,height=34)
        self.GButton_53["command"] = self.GButton_53_command

        self.GButton_918=tk.Button(root)
        self.GButton_918["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_918["font"] = ft
        self.GButton_918["fg"] = "#000000"
        self.GButton_918["justify"] = "center"
        self.GButton_918["text"] = "Login"
        self.GButton_918.place(x=190,y=340,width=126,height=34)
        self.GButton_918["command"] = self.GButton_918_command

        self.GButton_23=tk.Button(root)
        self.GButton_23["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_23["font"] = ft
        self.GButton_23["fg"] = "#000000"
        self.GButton_23["justify"] = "center"
        self.GButton_23["text"] = "Logout"
        self.GButton_23.place(x=380,y=340,width=126,height=34)
        self.GButton_23["command"] = self.GButton_23_command


    def GButton_53_command(self):
        print("command")


    def GButton_918_command(self): #Login
        print("command")
        self.GLineEdit_902 = self.GLineEdit_902.get().lower()
        self.GLineEdit_125  = self.GLineEdit_125 .get()


        # credentials check
        if self.GLineEdit_902 == "seller" and self.GLineEdit_125 == "password":
            self.open_supermarket()
        elif self.GLineEdit_902 == "customer" and self.GLineEdit_125 == "password":
            print("Customer login successful.")
            # code for customerpage
            self.open_CustomerPage()
        else:
            print("Login failed. Please try again.")
            messagebox.showerror("Login Failed", "Incorrect credentials. Please try again.")

    def GButton_23_command(self): #Logout
        print("command")

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
        #setting title
        root.title("Supermarket")
        #setting window size
        width=750
        height=600
        screenwidth = root.winfo_screenwidth()
        screenheight = root.winfo_screenheight()
        alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
        root.geometry(alignstr)
        root.resizable(width=False, height=False)

        self.product_list = [
            ("Product1", "10.00", "5"),
            ("Product2", "20.00", "3"),
            ("Product3", "15.00", "7"),
            # Add more products as needed
        ]


        self.GButton_618=tk.Button(root)
        self.GButton_618["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_618["font"] = ft
        self.GButton_618["fg"] = "#000000"
        self.GButton_618["justify"] = "center"
        self.GButton_618["text"] = "Add to Supermarket"
        self.GButton_618.place(x=360,y=50,width=160,height=30)
        self.GButton_618["command"] = self.GButton_618_command

        self.GLabel_146=tk.Label(root)
        self.GLabel_146["cursor"] = "watch"
        ft = tkFont.Font(family='Times',size=22)
        self.GLabel_146["font"] = ft
        self.GLabel_146["fg"] = "#333333"
        self.GLabel_146["justify"] = "left"
        self.GLabel_146["text"] = "Supermarket"
        self.GLabel_146.place(x=5,y=10,width=200,height=25)

        self.GLineEdit_565=tk.Entry(root)
        self.GLineEdit_565["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GLineEdit_565["font"] = ft
        self.GLineEdit_565["fg"] = "#333333"
        self.GLineEdit_565["justify"] = "center"
        self.GLineEdit_565["text"] = "Product Name"
        self.GLineEdit_565.place(x=150,y=50,width=200,height=30)

        self.GLineEdit_935=tk.Entry(root)
        self.GLineEdit_935["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GLineEdit_935["font"] = ft
        self.GLineEdit_935["fg"] = "#333333"
        self.GLineEdit_935["justify"] = "center"
        self.GLineEdit_935["text"] = "Php 0.00"
        self.GLineEdit_935.place(x=150,y=90,width=200,height=30)

        self.GLabel_791=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.GLabel_791["font"] = ft
        self.GLabel_791["fg"] = "#333333"
        self.GLabel_791["justify"] = "left"
        self.GLabel_791["text"] = "Product Name"
        self.GLabel_791.place(x=20,y=50,width=130,height=30)

        self.GLabel_227=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.GLabel_227["font"] = ft
        self.GLabel_227["fg"] = "#333333"
        self.GLabel_227["justify"] = "left"
        self.GLabel_227["text"] = "Product Price"
        self.GLabel_227.place(x=20,y=90,width=130,height=30)

        self.GLabel_691=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.GLabel_691["font"] = ft
        self.GLabel_691["fg"] = "#333333"
        self.GLabel_691["justify"] = "left"
        self.GLabel_691["text"] = "Product Quantity"
        self.GLabel_691.place(x=20,y=130,width=130,height=30)

        self.GLineEdit_386=tk.Entry(root)
        self.GLineEdit_386["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GLineEdit_386["font"] = ft
        self.GLineEdit_386["fg"] = "#333333"
        self.GLineEdit_386["justify"] = "center"
        self.GLineEdit_386["text"] = "0 pc(s)"
        self.GLineEdit_386.place(x=150,y=130,width=200,height=30)

        self.GButton_530=tk.Button(root)
        self.GButton_530["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_530["font"] = ft
        self.GButton_530["fg"] = "#000000"
        self.GButton_530["justify"] = "center"
        self.GButton_530["text"] = "Remove Product"
        self.GButton_530.place(x=520,y=50,width=160,height=30)
        self.GButton_530["command"] = self.GButton_530_command

        self.GTextBox_268=tk.Text(root) #output
        self.GTextBox_268.bind("<Key>", lambda e: "break")
        self.GTextBox_268["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GTextBox_268["font"] = ft
        self.GTextBox_268["fg"] = "#333333"
        #self.GTextBox_268["justify"] = "left"
        self.GTextBox_268.place(x=20,y=210,width=713,height=200)
       # Create a vertical scrollbar
        self.scrollbar = tk.Scrollbar(root, command=self.GTextBox_268.yview)
        self.scrollbar.place(x=733, y=210, height=200)

        self.updateProductListGUI(self.product_list)

        self.GLabel_195=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.GLabel_195["font"] = ft
        self.GLabel_195["fg"] = "#333333"
        self.GLabel_195["justify"] = "left"
        self.GLabel_195["text"] = "Product List"
        self.GLabel_195.place(x=20,y=170,width=150,height=30)

        self.GButton_965=tk.Button(root)
        self.GButton_965["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_965["font"] = ft
        self.GButton_965["fg"] = "#000000"
        self.GButton_965["justify"] = "center"
        self.GButton_965["text"] = "Sort"
        self.GButton_965.place(x=680,y=50,width=70,height=30)
        self.GButton_965["command"] = self.GButton_965_command

        self.GTextBox_123 = tk.Text(root)  # transaction textbox
        self.GTextBox_123.bind("<Key>", lambda e: "break")
        self.GTextBox_123["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times', size=10)
        self.GTextBox_123["font"] = ft
        self.GTextBox_123["fg"] = "#333333"
        self.GTextBox_123.place(x=20, y=430, width=713, height=80)

        self.GButton_456 = tk.Button(root)
        self.GButton_456["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times', size=10)
        self.GButton_456["font"] = ft
        self.GButton_456["fg"] = "#000000"
        self.GButton_456["justify"] = "center"
        self.GButton_456["text"] = "Perform Transaction"
        self.GButton_456.place(x=20, y=520, width=160, height=30)
        self.GButton_456["command"] = self.GButton_456_command

        self.Glabellogin_app_instance = login_app_instance

        # ... (unchanged)

    def GButton_456_command(self): # transaction command
        print("perform transaction")
        transaction_text = self.GTextBox_123.get(1.0, tk.END)
        # Process the transaction_text as needed
        print("Transaction:", transaction_text)
    
    def GButton_618_command(self): #add
        print("add")
        userInput = self.GlabelgetInputs()
        self.product_list = self.GlabeladdProduct(self.product_list, userInput)
        self.updateProductListGUI(self.product_list)

    def GButton_530_command(self): #remove
        print("remove")
        userInput = self.GlabelgetInputs()
        self.product_list = self.GlabelremoveProduct(self.product_list, userInput)
        self.updateProductListGUI(self.product_list)

    def GButton_965_command(self):
        print("sort")
        userInput = self.GlabelgetInputs()
        self.product_list = self.Glabelsort_products(self.product_list)
        self.updateProductListGUI(self.product_list)

    def getInputs(self):
        prodName = self.GLineEdit_565.get()
        prodPrice = self.GLineEdit_935.get()
        prodQuan = self.GLineEdit_386.get()
        return [prodName, prodPrice, prodQuan]
    
    def sort_products(self, products):
        return sorted(products, key=lambda item: item[1])

    def updateProductListGUI(self, product_list):
        self.GTextBox_268.delete(1.0, tk.END)
        for idx, p in enumerate(product_list):
            self.GTextBox_268.insert(
                tk.END, str(p[0] + " - Php " + p[1] + " @ " + p[2] + "pc(s).\n")
            )

    def addProduct(self, products, userInput): #backend
        product_info = userInput
        products.append(product_info)
        return products
    
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
    

class CustomerApp:
    def __init__(self, root, customer_app_instance):
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

        self.product_list = [
            ("Product1", "10.00", "5"),
            ("Product2", "20.00", "3"),
            ("Product3", "15.00", "7"),
            # Add more products as needed
        ]

        self.Glabel_168=tk.Label(root)
        ft = tkFont.Font(family='Times',size=28)
        self.Glabel_168["font"] = ft
        self.Glabel_168["fg"] = "#333333"
        self.Glabel_168["justify"] = "center"
        self.Glabel_168["text"] = "Welcome Customer!"
        self.Glabel_168.place(x=0,y=0,width=292,height=67)

        self.Glabel_475=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.Glabel_475["font"] = ft
        self.Glabel_475["fg"] = "#333333"
        self.Glabel_475["justify"] = "center"
        self.Glabel_475["text"] = "Product Name"
        self.Glabel_475.place(x=10,y=90,width=128,height=34)

        self.Glabel_39=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.Glabel_39["font"] = ft
        self.Glabel_39["fg"] = "#333333"
        self.Glabel_39["justify"] = "center"
        self.Glabel_39["text"] = "Product Price"
        self.Glabel_39.place(x=10,y=140,width=128,height=34)

        self.Glabel_540=tk.Label(root)
        ft = tkFont.Font(family='Times',size=16)
        self.Glabel_540["font"] = ft
        self.Glabel_540["fg"] = "#333333"
        self.Glabel_540["justify"] = "center"
        self.Glabel_540["text"] = "Enter Quantity"
        self.Glabel_540.place(x=10,y=190,width=128,height=34)

        self.GlineEdit_980=tk.Entry(root)
        self.GlineEdit_980["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GlineEdit_980["font"] = ft
        self.GlineEdit_980["fg"] = "#333333"
        self.GlineEdit_980["justify"] = "center"
        self.GlineEdit_980["text"] = "prodname"
        self.GlineEdit_980.place(x=160,y=90,width=395,height=35)

        self.GlineEdit_942=tk.Entry(root)
        self.GlineEdit_942["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GlineEdit_942["font"] = ft
        self.GlineEdit_942["fg"] = "#333333"
        self.GlineEdit_942["justify"] = "center"
        self.GlineEdit_942["text"] = "EnterQuantity"
        self.GlineEdit_942.place(x=160,y=190,width=400,height=35)

        self.GMessage_996=tk.Message(root)
        ft = tkFont.Font(family='Times',size=10)
        self.GMessage_996["font"] = ft
        self.GMessage_996["fg"] = "#333333"
        self.GMessage_996["justify"] = "center"
        self.GMessage_996["text"] = "Message"
        self.GMessage_996.place(x=160,y=140,width=395,height=30)

        self.GButton_754=tk.Button(root)
        self.GButton_754["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_754["font"] = ft
        self.GButton_754["fg"] = "#000000"
        self.GButton_754["justify"] = "center"
        self.GButton_754["text"] = "Add"
        self.GButton_754.place(x=590,y=90,width=124,height=34)
        self.GButton_754["command"] = self.GButton_754_command

        self.GButton_435=tk.Button(root)
        self.GButton_435["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_435["font"] = ft
        self.GButton_435["fg"] = "#000000"
        self.GButton_435["justify"] = "center"
        self.GButton_435["text"] = "Remove"
        self.GButton_435.place(x=590,y=140,width=124,height=34)
        self.GButton_435["command"] = self.GButton_435_command

        self.GButton_871=tk.Button(root)
        self.GButton_871["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_871["font"] = ft
        self.GButton_871["fg"] = "#000000"
        self.GButton_871["justify"] = "center"
        self.GButton_871["text"] = "Search"
        self.GButton_871.place(x=590,y=190,width=124,height=34)
        self.GButton_871["command"] = self.GButton_871_command
        
        self.GTextBox_268=tk.Text(root) #output
        self.GTextBox_268.bind("<Key>", lambda e: "break")
        self.GTextBox_268["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GTextBox_268["font"] = ft
        self.GTextBox_268["fg"] = "#333333"
        self.GTextBox_268.place(x=160, y=240, width=395, height=120)
        self.scrollbar = tk.Scrollbar(root, command=self.GTextBox_268.yview)
        self.scrollbar.place(x=733, y=210, height=200)
        # Configure the Text widget to use the scrollbar
        self.GTextBox_268.config(yscrollcommand=self.scrollbar.set)

        self.updateProductListGUI(self.product_list)
        self.Glabellogin_app_instance = customer_app_instance

    def updateProductListGUI(self, product_list):
        self.GTextBox_268.delete(1.0, tk.END)
        for idx, p in enumerate(product_list):
            self.GTextBox_268.insert(
                tk.END, str(p[0] + " - Php " + p[1] + " @ " + p[2] + "pc(s).\n")
            )

    def GButton_754_command(self):
        print("Add to Cart")

    def GButton_435_command(self):
        print("Remove From Cart")

    def GButton_871_command(self):
        print("Search Product")

if __name__ == "__main__":
    root = tk.Tk()
    login_app = LoginApp(root)
    root.mainloop()