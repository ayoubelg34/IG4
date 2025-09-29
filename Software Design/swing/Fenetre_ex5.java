package swing;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Fenetre_ex5 extends JFrame {
    private final JButton[] buttons;

    public Fenetre_ex5(int nButtons) {
        // borne n entre 1 et 9 (exigence de l'énoncé)
        nButtons = Math.max(1, Math.min(nButtons, 9));

        setTitle("Exemple Swing - KeyAdapter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Conteneur principal et panneau de boutons
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout (cours)
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        // Création des boutons
        buttons = new JButton[nButtons];
        for (int i = 0; i < nButtons; i++) {
            JButton b = new JButton("Bouton " + (i + 1));
            b.setBorder(new LineBorder(Color.YELLOW, 2));
            b.setBackground(new Color(102, 102, 0));
            
            b.setFocusable(true);

            // Mise en évidence visuelle quand le bouton a le focus (via FocusAdapter, vu dans le cours)
            Color normalBg = b.getBackground();
            b.addFocusListener(new FocusAdapter() {
                @Override public void focusGained(FocusEvent e) {
                    b.setBorder(new LineBorder(Color.RED, 2));
                    b.setBackground(new Color(255, 0, 0));
                }
                @Override public void focusLost(FocusEvent e) {
                    b.setBorder(UIManager.getBorder("Button.border"));
                    b.setBackground(normalBg);
                }
            });

            buttons[i] = b;
            panel.add(b);
        }

        // --- KeyAdapter : frappe '1'..'n' -> focus sur le bouton i ---
        KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char ch = e.getKeyChar();
                if (ch >= '1' && ch <= '9') {
                    int idx = ch - '1';
                    if (idx >= 0 && idx < buttons.length) {
                        buttons[idx].requestFocusInWindow(); // donne le focus au bouton i
                    }
                }
            }
        };

        // On enregistre le même écouteur sur plusieurs composants (relation composant/écouteur du cours)
        // ainsi, que le focus soit sur la fenêtre, le panel ou un bouton, les touches sont prises en compte.
        addKeyListener(keyAdapter);
        panel.addKeyListener(keyAdapter);
        for (JButton b : buttons) {
            b.addKeyListener(keyAdapter);
        }

        pack();                       // calcule la taille idéale (cf. cours)
        setSize(Math.max(420, getWidth()), Math.max(200, getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);

        // Donner le focus initial au panel pour que les premières frappes soient captées
        panel.setFocusable(true);
        panel.requestFocusInWindow();
    }

    public static void main(String[] args) {
        // Lecture du nombre de boutons en console (Scanner) — exigence conservée
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nombre de boutons (1..9) : ");
        int nButtons = scanner.hasNextInt() ? scanner.nextInt() : 5;
        scanner.close();

        final int n = nButtons;
        SwingUtilities.invokeLater(() -> new Fenetre_ex5(n));
    }
}
