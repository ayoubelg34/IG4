class Pingouin:
    def __init__(self, name : str = "Linuxou"):
        self.name = name

def say_hello( qui : Pingouin) -> str:
    return 'Hello '+ qui.name

# Programme principal :
p1 = Pingouin("Arthur")
p2 = Pingouin()
print(say_hello(p1))
print(say_hello(p2))

