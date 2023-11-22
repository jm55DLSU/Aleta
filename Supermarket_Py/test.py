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
    
    def addProduct(self, userInput): #who will call this: supermarket
        Server.product_list.append([userInput[0], userInput[1], userInput[2]])
        return 1
    
    def searchProduct(self, name:str):
        for p in Server.product_list:
            if p[0] == name:
                return p
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
        for item in cart:
            if item != '':
                for stock in range(len(Server.product_list)):
                    if Server.product_list[stock][0] == item[0]:
                        print(Server.product_list[stock], item)
                        Server.product_list[stock][2] -= item[2]
                        print(Server.product_list[stock], item)
        #Server.transaction_list.append(new_transaction)

s = Server()

a = []
a.append(['Product1', 10.0, 1])

s.addTransaction(['a','b','c'], a)