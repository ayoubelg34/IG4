
from animals_abc import EatsBread, feed_bread, Duck

# Une autre classe dérivée :
class Baby(EatsBread):
    def eat_bread(self):
        print('A baby is eating some bread')

    def drink_milk(self):
        print('A baby is drinking some milk')

####### Main pour tester ############
if __name__ == '__main__':
    feed_bread(Duck())  # mypy ???  python3 ??? 
    feed_bread(Baby())  # mypy ???  python3 ???



