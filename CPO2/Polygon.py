from abc import ABC, abstractmethod

class Polygon(ABC):
    @abstractmethod
    def no_of_sides(self):
        pass

class Triangle(Polygon):
    def no_of_sides(self):
        print("I have 3 sides")

class Pentagon(Polygon):
    def no_of_sides(self):
        print("I have 5 sides")

R = Triangle()
R.no_of_sides()
R = Pentagon()
R.no_of_sides()