package exercicis.interficiechat;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author David Boix Sanchez i Oleh Plechiy Andriyovech Tupis
 * @version 1.0
 */
public class VistaPrincipal extends javax.swing.JFrame {
    private String ipServidor;
    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        this.initComponents();
        this.inicialitzarIconos();
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public VistaPrincipal(String ipServidor) {
        this.initComponents();
        this.inicialitzarIconos();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.ipServidor = ipServidor;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        vistaGeneral = new javax.swing.JPanel();
        headerVista = new javax.swing.JPanel();
        titolVista = new javax.swing.JLabel();
        mainVista = new javax.swing.JPanel();
        iconoImatge = new javax.swing.JLabel();
        footerVista = new javax.swing.JPanel();
        botoIniciSessio = new javax.swing.JButton();
        botoRegistre = new javax.swing.JButton();
        menuSeleccio1 = new javax.swing.JMenuBar();
        menuDesplegableOpcions = new javax.swing.JMenu();
        menuOpcioLogin = new javax.swing.JMenuItem();
        menuOpcioRegistre = new javax.swing.JMenuItem();
        menuDesplegableSortir = new javax.swing.JMenu();
        menuOpcioSortir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vista principal");

        vistaGeneral.setToolTipText("");
        vistaGeneral.setLayout(new java.awt.BorderLayout());

        headerVista.setBackground(new java.awt.Color(203, 219, 242));
        headerVista.setToolTipText("");
        headerVista.setLayout(new java.awt.GridLayout(1, 0));

        titolVista.setBackground(new java.awt.Color(203, 219, 242));
        titolVista.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        titolVista.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titolVista.setText("PRINCIPAL");
        titolVista.setToolTipText("Titol de la vista");
        headerVista.add(titolVista);

        vistaGeneral.add(headerVista, java.awt.BorderLayout.PAGE_START);

        mainVista.setBackground(new java.awt.Color(203, 219, 242));
        mainVista.setLayout(new java.awt.GridLayout(1, 0));

        iconoImatge.setBackground(new java.awt.Color(203, 219, 242));
        iconoImatge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainVista.add(iconoImatge);

        vistaGeneral.add(mainVista, java.awt.BorderLayout.CENTER);

        footerVista.setBackground(new java.awt.Color(203, 219, 242));
        java.awt.GridBagLayout footerVistaLayout = new java.awt.GridBagLayout();
        footerVistaLayout.columnWidths = new int[] {0, 50, 0};
        footerVistaLayout.rowHeights = new int[] {0};
        footerVista.setLayout(footerVistaLayout);

        botoIniciSessio.setBackground(new java.awt.Color(125, 165, 221));
        botoIniciSessio.setText("Login");
        botoIniciSessio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoIniciSessioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        footerVista.add(botoIniciSessio, gridBagConstraints);

        botoRegistre.setBackground(new java.awt.Color(125, 165, 221));
        botoRegistre.setText("Registre");
        botoRegistre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botoRegistreActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        footerVista.add(botoRegistre, gridBagConstraints);

        vistaGeneral.add(footerVista, java.awt.BorderLayout.PAGE_END);

        menuSeleccio1.setBackground(new java.awt.Color(203, 219, 242));
        menuSeleccio1.setBorder(null);
        menuSeleccio1.setOpaque(true);

        menuDesplegableOpcions.setText("Navegació");

        menuOpcioLogin.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpcioLogin.setBackground(new java.awt.Color(203, 219, 242));
        menuOpcioLogin.setText("Login");
        menuOpcioLogin.setOpaque(true);
        menuOpcioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcioLoginActionPerformed(evt);
            }
        });
        menuDesplegableOpcions.add(menuOpcioLogin);

        menuOpcioRegistre.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpcioRegistre.setBackground(new java.awt.Color(203, 219, 242));
        menuOpcioRegistre.setText("Registre");
        menuOpcioRegistre.setOpaque(true);
        menuOpcioRegistre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcioRegistreActionPerformed(evt);
            }
        });
        menuDesplegableOpcions.add(menuOpcioRegistre);

        menuSeleccio1.add(menuDesplegableOpcions);

        menuDesplegableSortir.setText("Sortir");
        menuDesplegableSortir.setToolTipText("");

        menuOpcioSortir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        menuOpcioSortir.setBackground(new java.awt.Color(203, 219, 242));
        menuOpcioSortir.setText("Sortir");
        menuOpcioSortir.setOpaque(true);
        menuOpcioSortir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpcioSortirActionPerformed(evt);
            }
        });
        menuDesplegableSortir.add(menuOpcioSortir);

        menuSeleccio1.add(menuDesplegableSortir);

        setJMenuBar(menuSeleccio1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botoIniciSessioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoIniciSessioActionPerformed
        Login login = new Login();
        this.obrirFinestra(login);
        this.tancarFinestra();
    }//GEN-LAST:event_botoIniciSessioActionPerformed

    private void botoRegistreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botoRegistreActionPerformed
        Registre registre = new Registre();
        this.obrirFinestra(registre);
        this.tancarFinestra();
    }//GEN-LAST:event_botoRegistreActionPerformed
    /**
     * Event creat per poder navegar a la interficie grafica Login per poder
     * iniciar sessio amb aquell usuari.
     *
     * @param evt Event que se li passa per activar la funcio.
     */
    private void menuOpcioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcioLoginActionPerformed
        Login login = new Login();
        this.obrirFinestra(login);
        this.tancarFinestra();
    }//GEN-LAST:event_menuOpcioLoginActionPerformed
    /**
     * Funcio creada per poder navegar a la interficie grafica del Registre per
     * poder registrar-se en el nostre sistema de usuaris.
     *
     * @param evt Event que se li passa per activar la funcio.
     */
    private void menuOpcioRegistreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcioRegistreActionPerformed
        Registre registre = new Registre();
        this.obrirFinestra(registre);
        this.tancarFinestra();
    }//GEN-LAST:event_menuOpcioRegistreActionPerformed
    /**
     * Funcio creada per poder tancar la finestra la qual ens trobem en aquell
     * moment (Finestra actual).
     *
     * @param evt Event que se li passa per poder tancar la finestra.
     */
    private void menuOpcioSortirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpcioSortirActionPerformed
        this.dispose();
    }//GEN-LAST:event_menuOpcioSortirActionPerformed
    /**
     * Funcio desenvolupada per poder inicialitzar icones personalitzades un cop
     * s'inicia la aplicació.
     */
    private void inicialitzarIconos() {
        ImageIcon logoXatAModificar = new ImageIcon(getClass().getResource("/logoXat.png"));
        ImageIcon iconoNavegarAModificar = new ImageIcon(getClass().getResource("/navegar.png"));
        ImageIcon iconoLogoutAModificar = new ImageIcon(getClass().getResource("/iconoLogout.png"));
        
        Image iconoNavegarModificat = iconoNavegarAModificar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image iconoLogoutModificat = iconoLogoutAModificar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        Image logoXatModificat = logoXatAModificar.getImage().getScaledInstance(512, 512, Image.SCALE_SMOOTH);
        
        ImageIcon logoXat = new ImageIcon(logoXatModificat);
        ImageIcon iconoNavegar = new ImageIcon(iconoNavegarModificat);
        ImageIcon iconoLogout = new ImageIcon(iconoLogoutModificat);
        
        this.iconoImatge.setIcon(logoXat);
        this.menuDesplegableOpcions.setIcon(iconoNavegar);
        this.menuDesplegableSortir.setIcon(iconoLogout);
    }

    /**
     * Funcio desenvolupada per poder tancar la finestra que tenim oberta en
     * aquell moment
     */
    private void tancarFinestra() {
        this.setVisible(false);
    }

    /**
     * Funcio creada per poder obrir la interficie grafica que tenim allotjada
     * en el projecte i que li passarem per parametres
     *
     * @param jf JFrame que li passarem al usuari per poder obrir una interficie
     * en concret.
     */
    private void obrirFinestra(JFrame jf) {
        //Login login = new Login();
        Login login = new Login();
        jf.setVisible(true);
        jf.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botoIniciSessio;
    private javax.swing.JButton botoRegistre;
    private javax.swing.JPanel footerVista;
    private javax.swing.JPanel headerVista;
    private javax.swing.JLabel iconoImatge;
    private javax.swing.JPanel mainVista;
    private javax.swing.JMenu menuDesplegableOpcions;
    private javax.swing.JMenu menuDesplegableSortir;
    private javax.swing.JMenuItem menuOpcioLogin;
    private javax.swing.JMenuItem menuOpcioRegistre;
    private javax.swing.JMenuItem menuOpcioSortir;
    private javax.swing.JMenuBar menuSeleccio1;
    private javax.swing.JLabel titolVista;
    private javax.swing.JPanel vistaGeneral;
    // End of variables declaration//GEN-END:variables
}
