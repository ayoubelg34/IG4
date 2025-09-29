from bateau import Bateau

class Peinture(Bateau):
    def __init__(self, bateau: Bateau, coeff_bruit: float, coeff_essence: float) -> None:
        self._bateau = bateau
        self._coeff_bruit = coeff_bruit
        self._coeff_essence = coeff_essence

    def description(self) -> str:
        return (f"{self._bateau.description()} avec peinture "
                f"(bruit: -{self._coeff_bruit * 100:.0f}%, essence: -{self._coeff_essence * 100:.0f}%)")

    def cout(self) -> float:
        base = self._bateau.cout()
        surcout = 1000 + (1 - self._coeff_bruit) * 500 + (1 - self._coeff_essence) * 500
        return base + surcout
