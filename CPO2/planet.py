class Planet:
    def __init__(self, coordonnees, temperature, unite= "Kelvin", nom="",):
        self.coordonnees = coordonnees
        self.temperature = temperature
        self.unite = unite
        self.nom = nom

    
    # getter cordonnées
    def get_cordonnees(self):
        return self.__cordonnees

    # setter cordonnées
    def set_coordonnes(self, coordonnes):
        self.__coordonnees = coordonnes
    
    # getter temperature
    def get_temperature(self):
        return self.__temperature

    # setter temperature
    def set_temperature(self, temperature):
        self.__temperature = temperature
    
    # getter pour nom
    def get_nom(self):
        return self.__nom

    # setter pour nom
    def set_nom(self, nom):
        self.__nom = nom

    
    
    # Méthode d'instance 
    def positionner_temp(self, temperature, unite):
        if unite == "Kelvin":
            self.__temperature = temperature  #Unité par défault
        elif unite == "Celsius":
            self.__temperature = temperature + 273.15
        elif unite == "Fahrenheit":
            self.__temperature = (temperature - 32) * 5/9 + 273.15
        else:
            raise ValueError("Unité inconnue. Utilisez 'Kelvin', 'Celsius' ou 'Fahrenheit'.")

    
    
    def afficher(self):
        print(f"Planète {self.nom} aux coordonnées {self.coordonnees} avec une température de {self.temperature} {self.unite}")


X8120 : Planet = Planet (242, 0, "Kelvin")
Mars : Planet = Planet(54, 2, "Celsius", "Mars")

X8120.afficher()
Mars.afficher()

Mars.positionner_temp()