class Employee:

    def __init__(self, f,l,a,d,s,t):
        self.firstname = f
        self.lastname = l


    def join(self):
        print("…", self.name)
    

    def leave(self):
        print("…", self.name)

c = Employee('Jack', 'London')