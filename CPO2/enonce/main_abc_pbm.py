# 1) Pas génial d'avoir ces imports nécessaires:
from animals_abc import EatsBread, feed_bread

# Une autre classe client dont on ne maitrise pas le code :
from client import Llama

class Baby(EatsBread):
    def eat_bread(self):
        print('A baby is eating some bread')

    def drink_milk(self):
        print('A baby is drinking some milk')

###### Main prog ##############
feed_bread(Baby())  
llama = Llama()
feed_bread(llama) 