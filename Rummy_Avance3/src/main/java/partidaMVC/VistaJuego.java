package partidaMVC;

import actualizaciones.Actualizacion;
import actualizaciones.ViewJuego;
import dtos.FichaDTO;
import dtos.JugadorDTO;
import dtos.ManejadorColorDTO;
import dtos.TipoFichaDTO;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import utils.PanelRound;

/**
 *
 * @author carlo
 */
public class VistaJuego extends javax.swing.JFrame implements Observer, ViewJuego {

    private static VistaJuego instance;

    private static ControladorJuego controlador;

    public VistaJuego(ControladorJuego controlador) {
        initComponents();
        this.controlador = controlador;
        esTuTurnoLabel.setVisible(false);
        
        btnCombinacion.setEnabled(false);
        tomarFichaBtn.setEnabled(false);
        pasarTurnoBtn.setVisible(false);
        pasarTurnoBtn.setEnabled(false);
    }

    public static VistaJuego getInstance(ControladorJuego controlador) {
        if (instance == null) {
            instance = new VistaJuego(controlador);
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
            SwingUtilities.invokeLater(() -> mostrarJugadores(jugadores));
            return;
        }

        String j1 = jugadores.size() > 0 && jugadores.get(0) != null ? jugadores.get(0).getNombre() : "- - -";
        jugador1.setText(j1);
        if (jugadores.size() > 0 && jugadores.get(0) != null) {
            ImageIcon avatarIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/" + jugadores.get(0).getAvatar() + ".png"));
            avatarJ1.setIcon(avatarIcon);
            cartasJ1.setText(jugadores.get(0).getMano() != null ? String.valueOf(jugadores.get(0).getMano().size()) : "");
        } else {
            cartasJ1.setText("");
        }

        String j2 = jugadores.size() > 1 && jugadores.get(1) != null ? jugadores.get(1).getNombre() : "- - -";
        jugador2.setText(j2);
        if (jugadores.size() > 1 && jugadores.get(1) != null) {
            ImageIcon avatarIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/" + jugadores.get(1).getAvatar() + ".png"));
            avatarJ2.setIcon(avatarIcon);
            cartasJ2.setText(jugadores.get(1).getMano() != null ? String.valueOf(jugadores.get(1).getMano().size()) : "");
        } else {
            cartasJ2.setText("");
        }

        String j3 = jugadores.size() > 2 && jugadores.get(2) != null ? jugadores.get(2).getNombre() : "- - -";
        jugador3.setText(j3);
        if (jugadores.size() > 2 && jugadores.get(2) != null) {
            ImageIcon avatarIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/" + jugadores.get(2).getAvatar() + ".png"));
            avatarJ3.setIcon(avatarIcon);
            cartasJ3.setText(jugadores.get(2).getMano() != null ? String.valueOf(jugadores.get(2).getMano().size()) : "");
        } else {
            cartasJ3.setText("");
        }

        String j4 = jugadores.size() > 3 && jugadores.get(3) != null ? jugadores.get(3).getNombre() : "- - -";
        jugador4.setText(j4);
        if (jugadores.size() > 3 && jugadores.get(3) != null) {
            ImageIcon avatarIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/" + jugadores.get(3).getAvatar() + ".png"));
            avatarJ4.setIcon(avatarIcon);
            cartasJ4.setText(jugadores.get(3).getMano() != null ? String.valueOf(jugadores.get(3).getMano().size()) : "");
        } else {
            cartasJ4.setText("");
        }

        if (ventanaPrincipal != null) {
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        }
    }

    @Override
    public void mostrarJugadorData(JugadorDTO jugador) {
        ImageIcon avatarIcon = new ImageIcon(getClass().getClassLoader().getResource("imgs/" + jugador.getAvatar() + ".png"));
        avatarLabel.setIcon(avatarIcon);
        jugadorLabel.setText(jugador.getNombre());
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(
                this,
                mensaje,
                "Mensaje",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    @Override
    public void mostrarSolicitudInicio(String solicitante) {
        int respuesta = JOptionPane.showConfirmDialog(
                this, solicitante + " ha solicitado iniciar el juego, Estas de acuerdo?",
                "Solicitud Inicio de Juego",
                JOptionPane.YES_NO_OPTION);

        boolean aceptar = (respuesta == JOptionPane.YES_OPTION);
        controlador.responderSolicitudInicio(aceptar);
    }

    @Override
    public void mostrarMano(JugadorDTO jugador) {

        List<FichaDTO> fichas = jugador.getMano();
        List<ManejadorColorDTO> preferenciasColor = jugador.getPreferenciasColor();

        contenedorFichas.removeAll();

        for (FichaDTO ficha : fichas) {
            JLabel fichaLabel = new JLabel();
            fichaLabel.setOpaque(true);
            fichaLabel.setHorizontalAlignment(SwingConstants.CENTER);
            fichaLabel.setPreferredSize(new Dimension(50, 70));
            

            if (ficha.isComodin()) {
                fichaLabel.setBackground(Color.WHITE);
                fichaLabel.setText("C");
                //System.out.println("[C]");
            } else {
                Color colorFicha = obtenerColorFicha(ficha.getTipo(), preferenciasColor);
                fichaLabel.setBackground(colorFicha);
                fichaLabel.setText(String.valueOf(ficha.getNumero()));
                //System.out.println("[" + ficha.getTipo() + ":" + ficha.getNumero() + "]");
            }

            contenedorFichas.add(fichaLabel);
        }

        contenedorFichas.revalidate();
        contenedorFichas.repaint();
    }

    private Color obtenerColorFicha(TipoFichaDTO tipo, List<ManejadorColorDTO> preferenciasColor) {
        for (ManejadorColorDTO manejadorColor : preferenciasColor) {
            if (manejadorColor.getTipoFicha().equals(tipo)) {
                return new Color(manejadorColor.getColor().getColor());
            }
        }
        return Color.GRAY;
    }

    @Override
    public void actualizarDarTurno(JugadorDTO jugador) {
        btnCombinacion.setEnabled(true);
        tomarFichaBtn.setEnabled(true);
        esTuTurnoLabel.setVisible(true);
        pasarTurnoBtn.setEnabled(true);
        pasarTurnoBtn.setVisible(true);
        turnoLabel.setText(jugador.getNombre());
    }
    
     @Override
    public void actualizarQuitarTurno() {
        btnCombinacion.setEnabled(false);
        tomarFichaBtn.setEnabled(false);
        esTuTurnoLabel.setVisible(false);
        pasarTurnoBtn.setEnabled(false);
        pasarTurnoBtn.setVisible(false);
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
        jLabel1 = new javax.swing.JLabel();
        jugador1 = new javax.swing.JLabel();
        jugador2 = new javax.swing.JLabel();
        jugador3 = new javax.swing.JLabel();
        jugador4 = new javax.swing.JLabel();
        solicitarInicioBtn = new utils.Btn();
        jugadorLabel = new javax.swing.JLabel();
        avatarLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        avatarJ1 = new javax.swing.JLabel();
        avatarJ2 = new javax.swing.JLabel();
        avatarJ3 = new javax.swing.JLabel();
        avatarJ4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        turnoLabel = new javax.swing.JLabel();
        cartasJ1 = new javax.swing.JLabel();
        cartasJ2 = new javax.swing.JLabel();
        cartasJ3 = new javax.swing.JLabel();
        cartasJ4 = new javax.swing.JLabel();
        esTuTurnoLabel = new javax.swing.JLabel();
        pasarTurnoBtn = new javax.swing.JButton();

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
                .addContainerGap(245, Short.MAX_VALUE)
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

        contenedorFichas.setBackground(new java.awt.Color(204, 255, 204));
        contenedorFichas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Jugadores");

        jugador1.setForeground(new java.awt.Color(255, 255, 255));
        jugador1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador1.setText("q");

        jugador2.setForeground(new java.awt.Color(255, 255, 255));
        jugador2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador2.setText("k");

        jugador3.setForeground(new java.awt.Color(255, 255, 255));
        jugador3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador3.setText("l");

        jugador4.setForeground(new java.awt.Color(255, 255, 255));
        jugador4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jugador4.setText("p");

        solicitarInicioBtn.setText("Estoy listo");
        solicitarInicioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solicitarInicioBtnActionPerformed(evt);
            }
        });

        jugadorLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jugadorLabel.setForeground(new java.awt.Color(255, 255, 255));
        jugadorLabel.setText(".");

        avatarLabel.setText(".");

        jLabel2.setText(".");

        avatarJ1.setForeground(new java.awt.Color(255, 255, 255));
        avatarJ1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        avatarJ2.setForeground(new java.awt.Color(255, 255, 255));
        avatarJ2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        avatarJ3.setForeground(new java.awt.Color(255, 255, 255));
        avatarJ3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        avatarJ4.setForeground(new java.awt.Color(255, 255, 255));
        avatarJ4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Turno: ");

        turnoLabel.setForeground(new java.awt.Color(255, 255, 255));

        cartasJ1.setForeground(new java.awt.Color(255, 255, 255));
        cartasJ1.setText("-");

        cartasJ2.setForeground(new java.awt.Color(255, 255, 255));
        cartasJ2.setText("-");

        cartasJ3.setForeground(new java.awt.Color(255, 255, 255));
        cartasJ3.setText("-");

        cartasJ4.setForeground(new java.awt.Color(255, 255, 255));
        cartasJ4.setText("-");

        esTuTurnoLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        esTuTurnoLabel.setForeground(new java.awt.Color(255, 255, 255));
        esTuTurnoLabel.setText("Es tu turno! haz algo jaja");

        pasarTurnoBtn.setText("Pasar turno");
        pasarTurnoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarTurnoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ventanaPrincipalLayout = new javax.swing.GroupLayout(ventanaPrincipal);
        ventanaPrincipal.setLayout(ventanaPrincipalLayout);
        ventanaPrincipalLayout.setHorizontalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(15, 15, Short.MAX_VALUE)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addComponent(btnCombinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tomarFichaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(408, 408, 408)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jugador2)
                                            .addComponent(jugador1)
                                            .addComponent(jugador3)
                                            .addComponent(jugador4))
                                        .addGap(23, 23, 23)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cartasJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartasJ2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartasJ3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cartasJ4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24))
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(pasarTurnoBtn)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contenedorFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(avatarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jugadorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(esTuTurnoLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(turnoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(66, 66, 66)
                                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(avatarJ1)
                                    .addComponent(avatarJ2)
                                    .addComponent(avatarJ3)
                                    .addComponent(avatarJ4))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ventanaPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(solicitarInicioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        ventanaPrincipalLayout.setVerticalGroup(
            ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jugador1)
                            .addComponent(avatarJ1)
                            .addComponent(cartasJ1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jugador2)
                            .addComponent(avatarJ2)
                            .addComponent(cartasJ2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jugador3)
                            .addComponent(avatarJ3)
                            .addComponent(cartasJ3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jugador4)
                            .addComponent(avatarJ4)
                            .addComponent(cartasJ4))))
                .addGap(18, 18, 18)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(turnoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCombinacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tomarFichaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pasarTurnoBtn))
                .addGap(18, 18, 18)
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorFichas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solicitarInicioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(esTuTurnoLabel))
                        .addGap(16, 16, 16))
                    .addGroup(ventanaPrincipalLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventanaPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(avatarLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jugadorLabel))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ventanaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCombinacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCombinacionActionPerformed
        System.out.println("hare combinacion");
    }//GEN-LAST:event_btnCombinacionActionPerformed

    private void tomarFichaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tomarFichaBtnActionPerformed
        System.out.println("tomare ficha");

    }//GEN-LAST:event_tomarFichaBtnActionPerformed

    private void solicitarInicioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solicitarInicioBtnActionPerformed
        // TODO add your handling code here:
        controlador.solicitarInicio();
    }//GEN-LAST:event_solicitarInicioBtnActionPerformed

    private void pasarTurnoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarTurnoBtnActionPerformed
        // TODO add your handling code here:
        System.out.println("pasando turno");
        controlador.pasarTurno();
    }//GEN-LAST:event_pasarTurnoBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatarJ1;
    private javax.swing.JLabel avatarJ2;
    private javax.swing.JLabel avatarJ3;
    private javax.swing.JLabel avatarJ4;
    private javax.swing.JLabel avatarLabel;
    private utils.Btn btnCombinacion;
    private javax.swing.JLabel cartasJ1;
    private javax.swing.JLabel cartasJ2;
    private javax.swing.JLabel cartasJ3;
    private javax.swing.JLabel cartasJ4;
    private javax.swing.JPanel contenedorFichas;
    private javax.swing.JLabel esTuTurnoLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jugador1;
    private javax.swing.JLabel jugador2;
    private javax.swing.JLabel jugador3;
    private javax.swing.JLabel jugador4;
    private javax.swing.JLabel jugadorLabel;
    private utils.PanelRound panelRound1;
    private javax.swing.JButton pasarTurnoBtn;
    private utils.Btn solicitarInicioBtn;
    private utils.Btn tomarFichaBtn;
    private javax.swing.JLabel turnoLabel;
    private javax.swing.JPanel ventanaPrincipal;
    // End of variables declaration//GEN-END:variables
 
}
