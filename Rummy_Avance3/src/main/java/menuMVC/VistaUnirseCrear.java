package menuMVC;

import dtos.JuegoDTO;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import mensajes.Mensaje;

/**
 *
 * @author julli
 */
public class VistaUnirseCrear extends javax.swing.JFrame implements Observer {

    private ControladorMenu controladorMenu;

    /**
     * Creates new form VistaUnirse
     */
    public VistaUnirseCrear(ControladorMenu controladorMenu) {
        initComponents();
        this.controladorMenu = controladorMenu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnJoin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        puertoTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnStart1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnJoin.setText("JOIN");
        btnJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinActionPerformed(evt);
            }
        });
        jPanel1.add(btnJoin, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, 110, 40));

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 204, 0));
        jLabel1.setText("Rummy Royale");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        puertoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                puertoTxtActionPerformed(evt);
            }
        });
        jPanel1.add(puertoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 180, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("OR");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, -1, -1));

        btnStart1.setText("START");
        btnStart1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStart1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnStart1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 200, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("PORT:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, -1));

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinActionPerformed


    }//GEN-LAST:event_btnJoinActionPerformed

    private void puertoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_puertoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_puertoTxtActionPerformed

    private void btnStart1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStart1ActionPerformed
        // TODO add your handling code here:
        controladorMenu.crearPartida(Integer.parseInt(puertoTxt.getText()));
    }//GEN-LAST:event_btnStart1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJoin;
    private javax.swing.JButton btnStart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField puertoTxt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {

            String comando = (String) arg;
            if (comando.equals("PARTIDA_CREADA")) {
                this.dispose();
            }
            if (comando.equals("JUEGO_EXISTENTE")) {

            }
        }
    }

}
