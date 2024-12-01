package partidaMVC;

import actualizaciones.Actualizacion;
import actualizaciones.VistaJugadores;
import dtos.JugadorDTO;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.SwingUtilities;
import utils.PanelRound;

/**
 *
 * @author carlo
 */
public class VistaJuego extends javax.swing.JFrame implements Observer, VistaJugadores {

    private static ControladorJuego controlador;
    private static VistaJuego instance;

    public static VistaJuego getInstancia() {
        if (instance == null) {
            return new VistaJuego(controlador);
        }
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Actualizacion) {
            Actualizacion actualizacion = (Actualizacion) arg;
            actualizar(actualizacion);
        }
    }

    @Override
    public void actualizar(Actualizacion actualizacion) {
        actualizacion.aplicar(this);
    }

    @Override
    public void mostrarJugadores(List<JugadorDTO> jugadores) {

        if (!SwingUtilities.isEventDispatchThread()) {
            System.err.println("¡El código no está corriendo en el EDT!");
        }

        String j1 = jugadores.size() > 0 && jugadores.get(0) != null ? jugadores.get(0).getNombre() : "- - -";
        jugador1.setText(j1);
        jugador1.revalidate();
        jugador1.repaint();

        String j2 = jugadores.size() > 1 && jugadores.get(1) != null ? jugadores.get(1).getNombre() : "- - -";
        jugador2.setText(j2);

        String j3 = jugadores.size() > 2 && jugadores.get(2) != null ? jugadores.get(2).getNombre() : "- - -";
        jugador3.setText(j3);

        String j4 = jugadores.size() > 3 && jugadores.get(3) != null ? jugadores.get(3).getNombre() : "- - -";
        jugador4.setText(j4);

        this.revalidate();
        this.repaint();

        if (ventanaPrincipal != null) {
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
            panelJugadores.revalidate();
            panelJugadores.repaint();
        }

        System.out.println("Esto es lo que hay en j1: " + j1);
        System.out.println("Esto es lo que hay en jugador1: " + jugador1.getText());
    }

    /**
     * Creates new form View
     */
    public VistaJuego(ControladorJuego controlador) {
        initComponents();
        this.controlador = controlador;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventanaPrincipal = new javax.swing.JPanel();
        btnCombinacion = new utils.Btn();
        panelRound1 = new utils.PanelRound();
        jLabel11 = new javax.swing.JLabel();
        tomarFichaBtn = new utils.Btn();
        contenedorFichas = new javax.swing.JPanel();
        panelRound13 = new utils.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        panelRound21 = new utils.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        panelRound22 = new utils.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        panelRound23 = new utils.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        panelRound24 = new utils.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        panelRound25 = new utils.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        panelJugadores = new javax.swing.JPanel();
        jugador1 = new javax.swing.JLabel();
        jugador2 = new javax.swing.JLabel();
        jugador3 = new javax.swing.JLabel();
        jugador4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ventanaPrincipal.setBackground(new java.awt.Color(33, 142, 64));

        btnCombinacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnCombinacion.setText("Hacer combinación");
        btnCombinacion.setColorClick(new java.awt.Color(51, 153, 255));
        btnCombinacion.setColorOver(new java.awt.Color(0, 153, 255));
        btnCombinacion.setRadius(10);
        btnCombinacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCombinacionActionPerformed(evt);
            }
        });

        panelRound1.setRoundBottomLeft(10);
        panelRound1.setRoundBottomRight(10);
        panelRound1.setRoundTopLeft(10);
        panelRound1.setRoundTopRight(10);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Tablero");

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap(335, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(258, 258, 258))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        tomarFichaBtn.setBackground(new java.awt.Color(51, 153, 255));
        tomarFichaBtn.setForeground(new java.awt.Color(255, 255, 255));
        tomarFichaBtn.setText("Tomar ficha");
        tomarFichaBtn.setColor(new java.awt.Color(51, 153, 255));
        tomarFichaBtn.setColorClick(new java.awt.Color(0, 102, 204));
        tomarFichaBtn.setColorOver(new java.awt.Color(0, 102, 255));
        tomarFichaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tomarFichaBtnActionPerformed(evt);
            }
        });

        contenedorFichas.setBackground(new java.awt.Color(33, 142, 64));
        contenedorFichas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panelRound13.setBackground(new java.awt.Color(0, 51, 204));
        panelRound13.setRoundBottomLeft(10);
        panelRound13.setRoundBottomRight(10);
        panelRound13.setRoundTopLeft(10);
        panelRound13.setRoundTopRight(10);
        panelRound13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound13MouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("5");

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelRound21.setBackground(new java.awt.Color(51, 255, 51));
        panelRound21.setRoundBottomLeft(10);
        panelRound21.setRoundBottomRight(10);
        panelRound21.setRoundTopLeft(10);
        panelRound21.setRoundTopRight(10);
        panelRound21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound21MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("2");

        javax.swing.GroupLayout panelRound21Layout = new javax.swing.GroupLayout(panelRound21);
        panelRound21.setLayout(panelRound21Layout);
        panelRound21Layout.setHorizontalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound21Layout.setVerticalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelRound22.setBackground(new java.awt.Color(255, 0, 0));
        panelRound22.setRoundBottomLeft(10);
        panelRound22.setRoundBottomRight(10);
        panelRound22.setRoundTopLeft(10);
        panelRound22.setRoundTopRight(10);
        panelRound22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound22MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("5");

        javax.swing.GroupLayout panelRound22Layout = new javax.swing.GroupLayout(panelRound22);
        panelRound22.setLayout(panelRound22Layout);
        panelRound22Layout.setHorizontalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound22Layout.setVerticalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelRound23.setBackground(new java.awt.Color(255, 0, 0));
        panelRound23.setRoundBottomLeft(10);
        panelRound23.setRoundBottomRight(10);
        panelRound23.setRoundTopLeft(10);
        panelRound23.setRoundTopRight(10);
        panelRound23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelRound23MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("6");

        javax.swing.GroupLayout panelRound23Layout = new javax.swing.GroupLayout(panelRound23);
        panelRound23.setLayout(panelRound23Layout);
        panelRound23Layout.setHorizontalGroup(
            panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound23Layout.setVerticalGroup(
            panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound23Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelRound24.setBackground(new java.awt.Color(255, 204, 0));
        panelRound24.setRoundBottomLeft(10);
        panelRound24.setRoundBottomRight(10);
        panelRound24.setRoundTopLeft(10);
        panelRound24.setRoundTopRight(10);
        panelRound24.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                panelRound24KeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("8");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound24Layout = new javax.swing.GroupLayout(panelRound24);
        panelRound24.setLayout(panelRound24Layout);
        panelRound24Layout.setHorizontalGroup(
            panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound24Layout.setVerticalGroup(
            panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound24Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        panelRound25.setBackground(new java.awt.Color(0, 51, 204));
        panelRound25.setRoundBottomLeft(10);
        panelRound25.setRoundBottomRight(10);
        panelRound25.setRoundTopLeft(10);
        panelRound25.setRoundTopRight(10);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("8");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelRound25Layout = new javax.swing.GroupLayout(panelRound25);
        panelRound25.setLayout(panelRound25Layout);
        panelRound25Layout.setHorizontalGroup(
            panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelRound25Layout.setVerticalGroup(
            panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound25Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contenedorFichasLayout = new javax.swing.GroupLayout(contenedorFichas);
        contenedorFichas.setLayout(contenedorFichasLayout);
        contenedorFichasLayout.setHorizontalGroup(
            contenedorFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorFichasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelRound25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(365, Short.MAX_VALUE))
        );
        contenedorFichasLayout.setVerticalGroup(
            contenedorFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorFichasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contenedorFichasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jugadores");

        jugador1.setText("q");
        panelJugadores.add(jugador1);

        jugador2.setText("k");
        panelJugadores.add(jugador2);

        jugador3.setText("l");
        panelJugadores.add(jugador3);

        jugador4.setText("p");
        panelJugadores.add(jugador4);

        javax.swing.GroupLayout ventanaPrincipalLayout = new javax.swing.GroupLayout(ventanaPrincipal);
        ventanaPrincipal.setLayout(ventanaPrincipalLayout);
        ventanaPrincipalLayout.setHorizontalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jLabel1))
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(panelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(btnCombinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tomarFichaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contenedorFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        ventanaPrincipalLayout.setVerticalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addGap(124, 124, 124)
                        .addComponent(panelJugadores, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(btnCombinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(tomarFichaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(contenedorFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCombinacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCombinacionActionPerformed

    }//GEN-LAST:event_btnCombinacionActionPerformed

    private void tomarFichaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomarFichaBtnActionPerformed
        // hola?
        // controlDeck controlChip = new controlDeck();
        // controlChip.generateChip();
        //controlador.tomarFicha();

    }//GEN-LAST:event_tomarFichaBtnActionPerformed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        System.out.println("Selecciono carta ");
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        System.out.println("Selecciono carta");
    }//GEN-LAST:event_jLabel10MouseClicked

    private void panelRound13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound13MouseClicked
        // TODO add your handling code here:
        System.out.println("Selecciono carta");
    }//GEN-LAST:event_panelRound13MouseClicked

    private void panelRound21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound21MouseClicked
        // TODO add your handling code here:
        System.out.println("Selecciono carta");
    }//GEN-LAST:event_panelRound21MouseClicked

    private void panelRound22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound22MouseClicked
        // TODO add your handling code here:
        System.out.println("Selecciono carta");
    }//GEN-LAST:event_panelRound22MouseClicked

    private void panelRound23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound23MouseClicked
        // TODO add your handling code here:
        System.out.println("Selecciono carta");
    }//GEN-LAST:event_panelRound23MouseClicked

    private void panelRound24KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_panelRound24KeyPressed
        // TODO add your handling code here:
        System.out.println("Selecciono carta");
    }//GEN-LAST:event_panelRound24KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.Btn btnCombinacion;
    private javax.swing.JPanel contenedorFichas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jugador1;
    private javax.swing.JLabel jugador2;
    private javax.swing.JLabel jugador3;
    private javax.swing.JLabel jugador4;
    private javax.swing.JPanel panelJugadores;
    private utils.PanelRound panelRound1;
    private utils.PanelRound panelRound13;
    private utils.PanelRound panelRound21;
    private utils.PanelRound panelRound22;
    private utils.PanelRound panelRound23;
    private utils.PanelRound panelRound24;
    private utils.PanelRound panelRound25;
    private utils.Btn tomarFichaBtn;
    private javax.swing.JPanel ventanaPrincipal;
    // End of variables declaration//GEN-END:variables

}
