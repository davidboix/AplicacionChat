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
     * Constructor creat per inicialitzar el JPassword amb uns atributs en
     * especific.
     */
    private void JPasswordPlaceholder() {
        this.setForeground(Color.GRAY);
        this.setText(getPlaceHolder());
        String cadena = "u0000";
        this.setEchoChar(cadenaACaracter(cadena));
        this.addFocusListener(this);
    }

    /**
     * Setter de la classe JPasswordField que es fara servir per poder
     * inicialitzar un missatge personalitzat
     *
     * @param placeHolder Placeholder que assignarem a l'atribut.
     */
    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    /**
     * Getter desenvolupat per poder agafar el valor del placeholder en questio.
     *
     * @return Retornar el valor assignat del placeholder.
     */
    public String getPlaceHolder() {
        return placeHolder;
    }

    /**
     * Funcio sobrecarregada que ens aporta el event FocusListener que farem
     * servir per fer desapareixer el placeholder quan començem a escriure dins
     * de ell.
     *
     * @param e Event que se li passara per poder activar la funcio ja sigui per
     * teclat o bé, utilitzant el ratolí.
     */
    @Override
    public void focusGained(FocusEvent e) {
        String password = new String(this.getPassword());

        if (password.equalsIgnoreCase(getPlaceHolder())) {
            this.setText("");
            this.setEchoChar('@');
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
     * @param e Event que se li passara per poder activar la funcio ja sigui per
     * teclat o bé, utilitzant el ratolí.
     */
    @Override
    public void focusLost(FocusEvent e) {
        String password = new String(this.getPassword());

        if (password.isEmpty()) {
            this.setText(getPlaceHolder());
            this.setForeground(Color.GRAY);
        }
    }

    /**
     * Tractament de la cadena per poder assignar-ho tot a una variable de tipus
     * char.
     *
     * @param cadena Cadena que tractarem.
     * @return Retornara la variable caracter inicialitzada.
     */
    private char cadenaACaracter(String cadena) {
        char retorn = '\\';
        for (int i = 0; i < cadena.length(); i++) {
            retorn += cadena.charAt(i);
        }

        return retorn;
    }

}
