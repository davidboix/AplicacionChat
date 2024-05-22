package exercicis.interficiechat;

import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import metatron.CrudMONGO;

/**
 *
 * @author David Boix Sanchez
 * @version 1.0
 *
 */
public class proves extends javax.swing.JFrame {

    String dato;

    /**
     * Creates new form proves
     */
    public proves() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldPersonalitzat1 = new componentsPersonalitzats.JTextFieldPersonalitzat();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        inputData = new com.toedter.calendar.JDateChooser();

        jDialog1.setTitle("Introduir IP");
        jDialog1.setPreferredSize(new java.awt.Dimension(500, 400));

        jPanel7.setLayout(new java.awt.GridBagLayout());

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton3, new java.awt.GridBagConstraints());

        jButton4.setText("jButton4");
        jPanel7.add(jButton4, new java.awt.GridBagConstraints());

        jDialog1.getContentPane().add(jPanel7, java.awt.BorderLayout.PAGE_END);

        jPanel8.setLayout(new java.awt.GridBagLayout());
        jPanel8.add(jTextFieldPersonalitzat1, new java.awt.GridBagConstraints());

        jDialog1.getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel6.setLayout(new java.awt.CardLayout());
        jPanel6.add(inputData, "card2");

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Funcio que farem servir per utilitzar com guardarem el dia que s'ha
     * enviat un missatge
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        Date data = this.inputData.getDate();

        Calendar dataActual = Calendar.getInstance();
        int diaActual = dataActual.get(Calendar.DAY_OF_MONTH);
        int mesActual = dataActual.get(Calendar.MONTH);
        int anyActual = dataActual.get(Calendar.YEAR);

        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        int diaSemana = cal.get(Calendar.DAY_OF_WEEK) - 1;
        int diaCalendari = cal.get(Calendar.DAY_OF_MONTH);
        int mesCalendari = cal.get(Calendar.MONTH);
        int anyCalendari = cal.get(Calendar.YEAR);

        String diaSetmana = "";
        String mesSeleccionat = "";
        String mesActuals = "";
        String[] dies = {"Diumenge", "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte"};

        String[] mesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};

        for (int i = 0; i < dies.length; i++) {
            if (diaSemana == i) {
                diaSetmana = dies[i];
            }
        }

        for (int i = 0; i < mesos.length; i++) {
            if (mesCalendari == i) {
                mesSeleccionat = mesos[i];
            }
            if (mesActual == i) {
                mesActuals = mesos[i];
            }
        }

        /**
         * TODO:Investigar i revisar que si el usuari, introdueix una data menor
         * que la de avui, la seva edat sera de 23 anys, si la data que
         * introdueix el usuari es major o igual que la data de avui, tindra 24
         *
         * Exemple cumpleanys: 28/04/2000
         */
        System.out.println("Data actual: " + diaActual + "/" + (mesActual + 1) + "/" + anyActual);
        System.out.println("Data calendari: " + diaCalendari + "/" + (mesCalendari + 1) + "/" + anyCalendari);

        if (diaCalendari >= diaActual && (mesCalendari + 1) >= (mesActual + 1) && anyCalendari <= anyActual) {
            /**
             * TODO: Introduim que el usuari ja ha fet el cumpleanys i per tant,
             * tindra la edat que li pertoca.
             */
            setEdat(anyCalendari, anyActual);
        } else if (anyCalendari > anyActual && (diaCalendari >= diaActual || diaCalendari <= diaActual) && ((mesCalendari + 1) >= (mesActual + 1) || (mesCalendari + 1) <= (mesActual + 1))) {
            System.out.println("No pots haver nascut en el futur");
        } else {
            /**
             * TODO: Realitzem aquesta operacio degut a que el cumpleanys no
             * coincideix amb la data de avui i per tant la seva edat es menor
             */
            System.out.println("Encara no ha complert els anys!");
            setEdat(anyCalendari, anyActual);
        }

        System.out.println(diaSetmana + ", " + diaCalendari + " de " + mesSeleccionat + " de " + anyCalendari);

    }//GEN-LAST:event_jButton1ActionPerformed
    private CrudMONGO inicialitzarMongo() {
        final String DB_SRV_USR = "grup1";
        final String DB_SRV_PWD = "gat123";
        final String DB_URL = "57.129.5.24";
        final int DB_PORT = 27017;
        final String nomColeccio = "comptes";
        CrudMONGO cm = new CrudMONGO();

        cm.setUsuariServidor(DB_SRV_USR);
        cm.setPasswordServidor(DB_SRV_PWD);
        cm.setIpServidor(DB_URL);
        cm.setPortServidor(DB_PORT);
        cm.setNomColeccio(nomColeccio);
        return cm;
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//        CrudMONGO cm = this.inicialitzarMongo();
//        cm.setNomColeccio("missatges");
//        System.out.println("Aquest es el nom de la coleccio: " + cm.getNomColeccio());
//        String data = cm.tractarData();
        //System.out.println("Data de avui: " + data);
        //System.out.println(formatter.format(date));
        //cm.setDadesMsg("david", "aquest es un missatge", data);
        //cm.getUsers("david");
        //this.jDialog1.setPreferredSize(new Dimension(500, 300));
        this.jTextFieldPersonalitzat1.setPlaceHolder("Introdueix el nom de l'usuari");
        this.jTextFieldPersonalitzat1.setText(this.jTextFieldPersonalitzat1.getPlaceHolder());
        this.jDialog1.setSize(500, 600);
        this.jDialog1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dato = this.jTextFieldPersonalitzat1.getText();
        System.out.println(dato);
        this.jDialog1.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * TODO: Funcio inacabada Funcio que farem servir per poder calcular la edat
     * del usuari, on per parametres li passarem el any de naixement i
     * realitzarem el calcul per obtenir la seva edat actual per quan es
     * registri en la aplicacio de missatgeria de xat
     *
     * @param anyActual : Any de naixement en format enter en el qual el usuari
     * va neixer per poder calcular la edat
     */
    private void setEdat(int anyCalendari, int anyActual) {
//        Date data = this.inputData.getDate();
//        Calendar cal = Calendar.getInstance();
//        int any = data.getYear() + 1900;
        System.out.println("Any calendari: " + anyCalendari);
        System.out.println("Any actual: " + anyActual);
        int edatUser = anyActual - anyCalendari;
        System.out.println("Edat: " + edatUser);
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
            java.util.logging.Logger.getLogger(proves.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(proves.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(proves.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(proves.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new proves().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser inputData;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private componentsPersonalitzats.JTextFieldPersonalitzat jTextFieldPersonalitzat1;
    // End of variables declaration//GEN-END:variables
}
