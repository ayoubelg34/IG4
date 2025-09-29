from abc import *

class Animal(metaclass=ABCMeta):
    @abstractmethod
    def walk(self):
        pass  

class Duck(Animal):
    def walk(self):
        print('A duck is walking')  
    def __init__(self):
    	pass


duck = Duck()
duck.walk()
assert isinstance(duck, Animal)  # <-- True