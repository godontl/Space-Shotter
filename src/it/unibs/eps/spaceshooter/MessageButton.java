package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MessageButton {

    private static Font customFont;

    // Blocco statico per caricare il font una sola volta
    static {
        try {
            // Caricamento del font personalizzato
            customFont = Font.createFont(Font.TRUETYPE_FONT, MessageButton.class.getResourceAsStream("/font/1up.ttf"));
            customFont = customFont.deriveFont(Font.PLAIN, 18); // Imposta la dimensione del font

            // Registra il font con il GraphicsEnvironment (opzionale)
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Errore durante il caricamento del font");
        }
    }

    // Wrapper per contenere un componente e i suoi vincoli
    public static class ComponentWithConstraints {
        public JComponent component;
        public GridBagConstraints constraints;

        public ComponentWithConstraints(JComponent component, GridBagConstraints constraints) {
            this.component = component;
            this.constraints = constraints;
        }
    }

    // Metodo per creare un pulsante con vincoli
    public static ComponentWithConstraints createButton(String text, int width, int height, ActionListener actionListener, String fontName, int fontStyle, int fontSize, int gridx, int gridy, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));

        // Usa il font personalizzato, se disponibile
        if (fontName != null) {
            button.setFont(new Font(fontName, fontStyle, fontSize));
        } else {
            button.setFont(new Font("Arial", fontStyle, fontSize)); // Fallback a un font standard
        }

        button.setBackground(background);
        button.setForeground(foreground);
        button.addActionListener(actionListener);

        // Impostazione dei vincoli
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10);

        return new ComponentWithConstraints(button, gbc);
    }

    // Metodo per creare un testo con vincoli
    public static ComponentWithConstraints createText(String text, String fontName, int fontStyle, int fontSize, int gridx, int gridy,  Color foreground) {
        JLabel label = new JLabel(text);

        // Usa il font personalizzato, se disponibile
        if (fontName != null) {
            label.setFont(new Font(fontName, fontStyle, fontSize));
        } else {
            label.setFont(new Font("Arial", fontStyle, fontSize)); // Fallback a un font standard
        }

        label.setForeground(foreground);

        // Impostazione dei vincoli
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10);

        return new ComponentWithConstraints(label, gbc);
    }
}
