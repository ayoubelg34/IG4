

# Supposons que c'est une classe proposée par un gros package
# dont nous ne sommes pas l'auteur :
class Llama:
    def eat_bread(self):
        print('a Llama is eating some bread while knowing nothing')

# Main pour tester :
if __name__ == '__main__':
    Jinko = Llama()
    print('Jinko, young llama is alive')
    Jinko.eat_bread()