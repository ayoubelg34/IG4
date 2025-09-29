class Player:
    def __init__(self):
        # convention pour déclarer un att interne :
        self.__score = 10

p = Player()
p.score # erreur : pas d’accès ainsi
p.__score # erreur : pas d’accès ainsi
p._Player.__score # pas d’erreur ! Accès possible


