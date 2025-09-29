from bateau import Bateau

class Moteur(Bateau):
    def __init__(self, bateau: Bateau, type_moteur: str, puissance_cv: int) -> None:
        self._bateau = bateau
        self._type_moteur = type_moteur
        self._puissance_cv = puissance_cv

    def description(self) -> str:
        return f"{self._bateau.description()} avec moteur {self._type_moteur} ({self._puissance_cv} CV)"

    def cout(self) -> float:
        base = self._bateau.cout()
        if self._type_moteur == "hélice de surface":
            surcout = 5000
        elif self._type_moteur == "hors-bord":
            surcout = 4000
        elif self._type_moteur == "arbre":
            surcout = 3000
        else:
            surcout = 2000
        return base + surcout + self._puissance_cv * 10  # ex : 10€/CV
