package swing;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Fenetre extends JFrame {
    public Fenetre(int nButtons) {
        this.setTitle("Exemple Swing");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton[] buttons = new JButton[nButtons];
        for (int i = 0; i < nButtons; i++) {
            buttons[i] = new JButton("Bouton " + (i + 1));
            this.add(buttons[i]);
        }
        this.setLayout(new FlowLayout());
        this.setVisible(true);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nombre de boutons : ");
        int nButtons = scanner.nextInt();

        SwingUtilities.invokeLater(() -> new Fenetre(nButtons));
        scanner.close();
    }
}

