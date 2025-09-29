from abc import ABC, abstractmethod
from datetime import datetime

class Bateau(ABC):
    @abstractmethod
    def description(self) -> str:
        pass

    @abstractmethod
    def cout(self) -> str:
        pass

    
    
class BateauSimple(Bateau):
    def description(self) -> str:
        return "Bateau simple"

    def cout(self) -> float:
        return 10000.0


# Ecrivez un décorateur @horaireOuvrable qui n’exécute une fonction
# que si on se situe dans le heures ouvrables
