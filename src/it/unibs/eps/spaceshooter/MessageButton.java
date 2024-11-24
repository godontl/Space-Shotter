package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MessageButton {

    static Font customFont, littlecustomFont, smallcustomFont;

    // Blocco statico per caricare il font una sola volta
    static {
        try {
            // Caricamento del font personalizzato
            customFont = Font.createFont(Font.TRUETYPE_FONT, MessageButton.class.getResourceAsStream("/font/1up.ttf"));
            customFont = customFont.deriveFont(Font.PLAIN, 18); // Imposta la dimensione del font

            littlecustomFont = Font.createFont(Font.TRUETYPE_FONT, MessageButton.class.getResourceAsStream("/font/VCR_OSD_MONO_1.001.ttf"));
            littlecustomFont = littlecustomFont.deriveFont(Font.PLAIN, 15);

            smallcustomFont = Font.createFont(Font.TRUETYPE_FONT, MessageButton.class.getResourceAsStream("/font/VCRosdNEUE.ttf"));
            smallcustomFont = smallcustomFont.deriveFont(Font.PLAIN, 15);

            // Registra il font con il GraphicsEnvironment (opzionale)
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsEnvironment gi = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsEnvironment go = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            gi.registerFont(littlecustomFont);
            go.registerFont(smallcustomFont);

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
    public static ComponentWithConstraints createButton(String text, Runnable action, String fontName, int fontStyle, int fontSize, int gridx, int gridy, Color foreground, Color foregroundOnHover) {
        JLabel textButton = new JLabel(text);

        // Imposta il font personalizzato o uno di fallback
        if (fontName != null) {
            textButton.setFont(new Font(fontName, fontStyle, fontSize));
        } else {
            textButton.setFont(new Font("Arial", fontStyle, fontSize));
        }

        textButton.setForeground(foreground);
        textButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Cambia colore quando il mouse passa sopra
        textButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textButton.setForeground(foregroundOnHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textButton.setForeground(foreground);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (action != null) {
                    action.run();
                }
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10);

        return new ComponentWithConstraints(textButton, gbc);
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
