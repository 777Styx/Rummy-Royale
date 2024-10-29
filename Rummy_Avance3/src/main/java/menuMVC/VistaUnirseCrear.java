package menuMVC;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

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

        btnStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(194, Short.MAX_VALUE)
                .addComponent(btnStart)
                .addGap(134, 134, 134))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(btnStart)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed

//        ControladorMenu controladorUnirse = ControladorMenu.getInstancia();
//        controladorUnirse.unirseAPartidaView("Carlitos");
//        this.dispose();
        System.out.println("control");
        controladorMenu.crearJuego();

    }//GEN-LAST:event_btnStartActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStart;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Actualización recibida en VistaUnirse");

        if (o instanceof ModeloMenu) {
            ModeloMenu modeloUnirse = (ModeloMenu) o;
            if (modeloUnirse.getJuego() != null) {
                this.dispose();
            }
        }
    }

}