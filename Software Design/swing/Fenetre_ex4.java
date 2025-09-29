package swing;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Fenetre_ex4 extends JFrame {

    private JButton[] buttons;

    public Fenetre_ex4(int nButtons) {
        nButtons = Math.max(1, Math.min(nButtons, 9)); // Limite entre 1 et 9

        // Initialisation de la fenêtre
        setTitle("Exemple Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);

        // Création des boutons
        buttons = new JButton[nButtons];
        for (int i = 0; i < nButtons; i++) {
            final int idx = i;
            JButton b = new JButton("Bouton " + (i + 1));
            b.setFocusable(true); // doit pouvoir recevoir le focus

            // Effet visuel au focus
            Border normalBorder = b.getBorder();
            Color normalBg = b.getBackground();
            b.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    b.setBorder(new LineBorder(Color.BLUE, 2));
                    b.setBackground(new Color(200, 220, 255));
                }
                @Override
                public void focusLost(FocusEvent e) {
                    b.setBorder(normalBorder);
                    b.setBackground(normalBg);
                }
            });

            // Action au clic
            b.addActionListener(ev ->
                    JOptionPane.showMessageDialog(Fenetre_ex4.this,
                            "Vous avez cliqué sur le bouton " + (idx + 1)));

            buttons[i] = b;
            panel.add(b);
        }

        // Raccourcis clavier pour donner le focus aux boutons
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        for (int i = 1; i <= nButtons; i++) {
            final int idx = i - 1;
            String actionKey = "focus-" + i;
            im.put(KeyStroke.getKeyStroke(Character.forDigit(i, 10)), actionKey);
            am.put(actionKey, new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttons[idx].requestFocusInWindow(); // donne le focus
                    buttons[idx].scrollRectToVisible(new Rectangle(0, 0, 1, 1)); // au cas où il y aurait du scroll
                }
            });
        }

        pack();
        setSize(Math.max(420, getWidth()), Math.max(200, getHeight()));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nombre de boutons (1..9) : ");
        int nButtons = 5;
        if (scanner.hasNextInt()) {
            nButtons = scanner.nextInt();
        }
        scanner.close();

        final int n = nButtons;
        SwingUtilities.invokeLater(() -> new Fenetre_ex4(n));
    }
}
