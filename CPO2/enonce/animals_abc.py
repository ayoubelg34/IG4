from abc import ABCMeta, abstractmethod

# Une classe abstraite de mangeur de pain :
class EatsBread(metaclass=ABCMeta):
    # les classes dérivées devront avoir la méthode eat_bread()
    @abstractmethod
    def eat_bread(self):
        pass

# Exemple de classe dérivée
class Duck(EatsBread):
    def eat_bread(self):
        print('A duck is currently eating some bread')

# Autre exemple
class Pig(EatsBread):
    def eat_bread(self):
        print('A pig is currently eating some bread')

# Une fonction prenant en entrée un objet qui sait manger du pain : 
def feed_bread(animal: EatsBread):
    animal.eat_bread()

################ Main pour tester ##################

if __name__ == '__main__':
    myZoo = [ Pig(), Duck(), Duck()]
    for animal in myZoo:
        feed_bread(animal)
