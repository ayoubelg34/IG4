from abc import *

# A class that has a metaclass derived from ABCMeta cannot be instantiated 
# unless all of its abstract methods and properties are overridden.
class Animal(metaclass=ABCMeta):
    @abstractmethod
    def walk(self):
        pass  # Needs implementation by subclass

class Duck(Animal):
    def walk(self):
        print('A duck is walking')  
    def __init__(self):
    	pass

####### MAIN PART ####

# Not working:
a = Animal()

# Working:
duck = Duck()
duck.walk()
assert isinstance(duck, Animal)  # <-- True
