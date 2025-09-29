from abc import *

class EatsBread(metaclass=ABCMeta):
    @abstractmethod
    def eat_bread(self):
        pass

class Duck(EatsBread):
    def eat_bread(self):
        print('le canard est en train de manger du pain')

class Pig(EatsBread):
    def eat_bread(self):
        print('le cochon est en train de manger du pain')

def feed_bread(animal: EatsBread):
    animal.eat_bread()


pig = Pig()
duck = Duck()
feed_bread(pig)
feed_bread(duck)

if __name__ == '__main__':
    Zoo = [ Pig(), Duck(), Duck()]
    for animal in Zoo:
        feed_bread(animal)