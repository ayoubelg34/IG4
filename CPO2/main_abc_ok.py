
from animals_abc import EatsBread, feed_bread, Duck

class Baby(EatsBread):
    def eat_bread(self):
        print('un bébé est en train de manger du pain')

    def drink_milk(self):
        print('un bébé est en train de boire du lait')

if __name__ == '__main__':
    feed_bread(Duck())
    feed_bread(Baby())  



