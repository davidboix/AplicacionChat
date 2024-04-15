/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentsPersonalitzats;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author dam
 */
public class JPasswordPlaceholder extends JPasswordField implements FocusListener {

    private String placeHolder;

    private void JPasswordPlaceholder() {
        this.setForeground(Color.GRAY);
        this.setText(getPlaceHolder());
        this.addFocusListener(this);
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    @Override
    public void focusGained(FocusEvent e) {
        // TODO: Investigar alternativa al us del new String.
        String password = new String(this.getPassword());

        if (password.equalsIgnoreCase(getPlaceHolder())) {
            this.setText("mamasita");
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

    }

    @Override
    public void focusLost(FocusEvent e) {
        // TODO: Investigar alternativa al us del new String.
        String password = new String(this.getPassword());

        if (password.isEmpty()) {
            this.setText(getPlaceHolder());
            this.setForeground(Color.GRAY);
        }
    }
}
