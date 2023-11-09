import tkinter as tk
import tkinter.font as tkFont

class App:
    product_list = []

    def __init__(self, root):
        #setting title
        root.title("Supermarket")
        #setting window size
        width=800
        height=600
        screenwidth = root.winfo_screenwidth()
        screenheight = root.winfo_screenheight()
        alignstr = '%dx%d+%d+%d' % (width, height, (screenwidth - width) / 2, (screenheight - height) / 2)
        root.geometry(alignstr)
        root.resizable(width=False, height=False)

        self.GButton_618=tk.Button(root)
        self.GButton_618["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        self.GButton_618["font"] = ft
        self.GButton_618["fg"] = "#000000"
        self.GButton_618["justify"] = "center"
        self.GButton_618["text"] = "Add to Supermarket"
        self.GButton_618.place(x=360,y=50,width=150,height=30)
        self.GButton_618["command"] = self.GButton_618_command

        self.GLabel_146=tk.Label(root)
        self.GLabel_146["cursor"] = "watch"
        ft = tkFont.Font(family='Times',size=22)
        self.GLabel_146["font"] = ft
        self.GLabel_146["fg"] = "#333333"
        self.GLabel_146["justify"] = "center"
        self.GLabel_146["text"] = "Supermarket"
        self.GLabel_146.place(x=10,y=10,width=200,height=25)

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
        self.GButton_530["text"] = "Remove to Supermarket"
        self.GButton_530.place(x=520,y=50,width=150,height=30)
        self.GButton_530["command"] = self.GButton_530_command

        self.GTextBox_268=tk.Text(root) #output
        self.GTextBox_268.bind("<Key>", lambda e: "break")
        self.GTextBox_268["borderwidth"] = "1px"
        ft = tkFont.Font(family='Times',size=10)
        self.GTextBox_268["font"] = ft
        self.GTextBox_268["fg"] = "#333333"
        #self.GTextBox_268["justify"] = "left"
        self.GTextBox_268.place(x=20,y=210,width=750,height=200)

        self.GLabel_195=tk.Label(root)
        ft = tkFont.Font(family='Times',size=10)
        self.GLabel_195["font"] = ft
        self.GLabel_195["fg"] = "#333333"
        self.GLabel_195["justify"] = "left"
        self.GLabel_195["text"] = "Product List"
        self.GLabel_195.place(x=20,y=170,width=200,height=30)

        GButton_965=tk.Button(root)
        GButton_965["bg"] = "#f0f0f0"
        ft = tkFont.Font(family='Times',size=10)
        GButton_965["font"] = ft
        GButton_965["fg"] = "#000000"
        GButton_965["justify"] = "center"
        GButton_965["text"] = "Sort"
        GButton_965.place(x=680,y=50,width=70,height=30)
        GButton_965["command"] = self.GButton_965_command

    def GButton_618_command(self): #add
        print("add")
        userInput = self.getInputs()
        self.product_list = self.addProduct(self.product_list, userInput)
        self.updateProductListGUI(self.product_list)

    def GButton_530_command(self): #remove
        print("remove")
        userInput = self.getInputs()
        self.product_list = self.removeProduct(self.product_list, userInput)
        self.updateProductListGUI(self.product_list)

    def GButton_965_command(self):
        print("remove")
        userInput = self.getInputs()
        self.product_list = self.sort_products(self.product_list)
        self.updateProductListGUI(self.product_list)

    def getInputs(self):
        prodName = self.GLineEdit_565.get()
        prodPrice = self.GLineEdit_935.get()
        prodQuan = self.GLineEdit_386.get()
        return [prodName, prodPrice, prodQuan]
    
    def sort_products(self, products):
        return sorted(products, key=lambda item: item[1])

    def updateProductListGUI(self, product_list): #via update/refresh
        self.GTextBox_268.insert(tk.INSERT,"Hello world")
        self.GTextBox_268.delete(1.0, tk.END)
        for idx, p in enumerate(product_list):
            self.GTextBox_268.insert(tk.END, str(p[0] + " - Php " + p[1] + " @ " + p[2] + "pc(s).\n"))
        return

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


if __name__ == "__main__":
    root = tk.Tk()
    app = App(root)
    root.mainloop()
