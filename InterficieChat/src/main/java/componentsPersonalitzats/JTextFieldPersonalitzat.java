package componentsPersonalitzats;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class JTextFieldPersonalitzat extends JTextField implements FocusListener {

    private String placeHolder;

    /**
     * Constructor creat per inicialitzar el JTextField amb uns atributs en
     * especific.
     */
    public JTextFieldPersonalitzat() {
        this.setForeground(Color.GRAY);
        this.setText(getPlaceHolder());
        this.setFont(new Font("Noto Sans",Font.PLAIN,12));
        this.addFocusListener(this);
    }

    /**
     * Getter desenvolupat per poder retornar el valor del placeholder en concret.
     * @return 
     */
    public String getPlaceHolder() {
        return placeHolder;
    }

    /**
     * Setter parametritzat per poder inicialitzar el atribut anomenat placeHolder.
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
     * @param e Event que se li passara per poder activar la funcio ja sigui per
     * teclat o bé, utilitzant el ratolí.
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
        if (this.getText().isEmpty()) {
            this.setText(getPlaceHolder());
            this.setForeground(Color.GRAY);
        }
    }
}
