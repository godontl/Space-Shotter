package it.unibs.eps.spaceshooter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MessageButton {

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
    public static ComponentWithConstraints createButton(String text, int width, int height, ActionListener actionListener, String fontName, int fontStyle, int fontSize, int gridx, int gridy) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font(fontName, fontStyle, fontSize));
        button.addActionListener(actionListener);

        // Impostazione dei vincoli
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10);

        return new ComponentWithConstraints(button, gbc);
    }

    // Metodo per creare un testo con vincoli
    public static ComponentWithConstraints createText(String text, String fontName, int fontStyle, int fontSize, int gridx, int gridy) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(fontName, fontStyle, fontSize));

        // Impostazione dei vincoli
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.insets = new Insets(10, 10, 10, 10);

        return new ComponentWithConstraints(label, gbc);
    }
}