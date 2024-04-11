package componentsPersonalitzats;

import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 */
public class JLabelPersonalitzat extends JLabel {

    /**
     *
     */
    public JLabelPersonalitzat() {
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setFont(new Font("Noto Sans", Font.PLAIN, 12));
    }

}
