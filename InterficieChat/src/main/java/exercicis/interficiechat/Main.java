package exercicis.interficiechat;

/**
 *
 * @author David Boix Sanchez i Oleh Plechiy Tupis Andriyovech
 * @version 1.0
 */
public class Main {

    /**
     * Funcio que executara la vista principal de benvinguda al servei de
     * missatgeria que donara a elegir entre iniciar sessio si el usuari ja esta
     * registrat en el nostre sistema gestor de usuaris o be, registrar-se si es
     * un usuari nou.
     *
     * @param args
     */
    public static void main(String[] args) {
        VistaPrincipal vistaPrincipal= new VistaPrincipal();
        vistaPrincipal.setVisible(true);
    }
}
