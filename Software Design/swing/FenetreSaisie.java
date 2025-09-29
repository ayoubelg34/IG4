package swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FenetreSaisie extends JFrame {
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField telField;
    private JButton resumeButton;
    private JButton quitButton;
    private JTextArea resumeArea;

    public FenetreSaisie() {
        setTitle("Fenêtre de saisie de données");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout(8, 8));
        if (cp instanceof JComponent jc) {
            jc.setBorder(new EmptyBorder(10, 10, 10, 10));
        }

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 8, 8));
        JLabel nomLabel = new JLabel("Nom :");
        JLabel prenomLabel = new JLabel("Prénom :");
        JLabel telLabel = new JLabel("Tel :");

        nomField = new JTextField(20);
        prenomField = new JTextField(20);
        telField = new JTextField(20);

        formPanel.add(nomLabel);
        formPanel.add(nomField);
        formPanel.add(prenomLabel);
        formPanel.add(prenomField);
        formPanel.add(telLabel);
        formPanel.add(telField);

        resumeArea = new JTextArea(6, 30);
        resumeArea.setEditable(false);
        resumeArea.setLineWrap(true);
        resumeArea.setWrapStyleWord(true);
        JScrollPane resumeScroll = new JScrollPane(resumeArea);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        resumeButton = new JButton("Résumé");
        quitButton = new JButton("Quitter");
        buttonsPanel.add(resumeButton);
        buttonsPanel.add(quitButton);

        cp.add(formPanel, BorderLayout.NORTH);
        cp.add(resumeScroll, BorderLayout.CENTER);
        cp.add(buttonsPanel, BorderLayout.SOUTH);

        /*Listeners
        resumeButton.addActionListener(e -> {
            String nom = nomField.getText().trim();
            String prenom = prenomField.getText().trim();
            String tel = telField.getText().trim();

            resumeArea.setText(String.format(
                "Nom : %s%nPrénom : %s%nTéléphone : %s",
                nom, prenom, tel
            ));
        });
        
        quitButton.addActionListener(e -> dispose()); // ou System.exit(0);
        */


        pack();                     
        setLocationRelativeTo(null); 
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FenetreSaisie::new);
    }
}
