package componentsPersonalitzats;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author David Boix Sanchez
 */
public class JTextFieldPersonalitzat extends JTextField implements FocusListener {

    private String placeHolder;

    /**
     *
     */
    public JTextFieldPersonalitzat() {
        this.setForeground(Color.GRAY);
        this.setText(getPlaceHolder());
        this.addFocusListener(this);
    }

    /**
     *
     * @return
     */
    public String getPlaceHolder() {
        return placeHolder;
    }

    /**
     *
     * @param placeHolder
     */
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    /**
     *
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().equalsIgnoreCase(getPlaceHolder())) {
            this.setText("");
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            this.setText(getPlaceHolder());
            this.setForeground(Color.GRAY);
        }
    }
}
