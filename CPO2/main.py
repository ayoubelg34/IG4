from bateau import BateauSimple
from moteur import Moteur
from peinture import Peinture

def main() -> None:

    bateau = BateauSimple()

    bateau_avec_moteur = Moteur(bateau, type_moteur="hélice de surface", puissance_cv=150)

    bateau_complet = Peinture(bateau_avec_moteur, coeff_bruit=0.8, coeff_essence=0.85)

    print("Description:", bateau_complet.description())
    print("Coût total: {:.2f} €".format(bateau_complet.cout()))

if __name__ == "__main__":
    main()
