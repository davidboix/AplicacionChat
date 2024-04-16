package componentsPersonalitzats;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JPasswordField;

/**
 *
 * @author Oleh Plechiy Tupis Andriyovech i David Boix Sanchez
 * @version 1.0
 */
public class JPasswordPlaceholder extends JPasswordField implements FocusListener {

    private String placeHolder;

    /**
     *
     */
    private void JPasswordPlaceholder() {
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
     * Setter de la classe JPasswordField que es fara servir per poder
     * inicialitzar un missatge personalitzat
     *
     * @param placeHolder
     */
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    /**
     * Funcio sobrecarregada que ens aporta el event FocusListener que farem
     * servir per fer desapareixer el placeholder quan començem a escriure dins
     * de ell.
     *
     * @param e
     */
    @Override
    public void focusGained(FocusEvent e) {
        // TODO: Investigar alternativa al us del new String.
        String password = new String(this.getPassword());

        if (password.equalsIgnoreCase(getPlaceHolder())) {
            this.setText("");
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }

    }

    /**
     * Funcio sobrecarregada que ens aporta el event FocusListener que farem
     * servir per poder afegir un placeholder dins del input de la contrasenya
     * per a que el usuari tingui clar quin tipus de dada haura de anotar dins
     * del input.
     *
     * @param e
     */
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
