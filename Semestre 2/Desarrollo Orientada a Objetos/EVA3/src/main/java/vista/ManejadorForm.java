/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import javax.swing.Timer;

import alumnoduoc.eva3.EVA3;
import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author Gabo's
 */
public class ManejadorForm extends javax.swing.JFrame {
	int lastSelectedMazo;
	
	/**
	 * Creates new form Vista
	 */
	public ManejadorForm() {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cartaNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        elementoBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        ataqueField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        defensaField = new javax.swing.JTextField();
        registrarCartaButton = new javax.swing.JButton();
        cartaEstadoLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        mostrarMazoButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        idUsuarioMostrarMazoField = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        mazoListaTextView = new javax.swing.JTextArea();
        jLabel11 = new javax.swing.JLabel();
        nombreMazoField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        idUsuarioAgregarMazoField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        idCartaAgregarMazoField = new javax.swing.JTextField();
        agregarCartaMazoButton = new javax.swing.JButton();
        mazoEstadoLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        usuarioNameField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        usuarioCorreoField = new javax.swing.JTextField();
        agregarUsuarioButton = new javax.swing.JButton();
        usuarioEstadoLabel = new javax.swing.JLabel();
        usuarioRangoLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        usuarioListaTextView = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        cartaListaTextView = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Nombre:");

        jLabel4.setText("Elemento:");

        elementoBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Tierra", "Fuego", "Aire", "Agua" }));

        jLabel5.setText("Ataque:");

        jLabel6.setText("Defensa:");

        registrarCartaButton.setText("Registrar Carta");
        registrarCartaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarCartaButtonActionPerformed(evt);
            }
        });

        cartaEstadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cartaEstadoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(elementoBox, 0, 158, Short.MAX_VALUE)
                            .addComponent(cartaNameField))
                        .addContainerGap(66, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(registrarCartaButton)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ataqueField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(defensaField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cartaEstadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cartaNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(elementoBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ataqueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(defensaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registrarCartaButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartaEstadoLabel)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Registrar Carta", jPanel1);

        jLabel9.setText("Mazo");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        mostrarMazoButton.setText("Mostrar Mazo");
        mostrarMazoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarMazoButtonActionPerformed(evt);
            }
        });

        jLabel10.setText("ID Usuario");

        mazoListaTextView.setEditable(false);
        mazoListaTextView.setColumns(20);
        mazoListaTextView.setRows(5);
        mazoListaTextView.setTabSize(4);
        mazoListaTextView.setText("A\nB\nC\nD\nE\nF\nG");
        jScrollPane5.setViewportView(mazoListaTextView);

        jLabel11.setText("Nombre del Mazo:");

        jLabel12.setText("ID Usuario:");

        jLabel13.setText("ID Carta:");

        agregarCartaMazoButton.setText("Agregar Cartas al Mazo");
        agregarCartaMazoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarCartaMazoButtonActionPerformed(evt);
            }
        });

        mazoEstadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mazoEstadoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(mostrarMazoButton)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idUsuarioMostrarMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(17, 17, 17))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idUsuarioAgregarMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idCartaAgregarMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(agregarCartaMazoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(mazoEstadoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nombreMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(idUsuarioAgregarMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(idCartaAgregarMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(agregarCartaMazoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mazoEstadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mostrarMazoButton)
                    .addComponent(jLabel10)
                    .addComponent(idUsuarioMostrarMazoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Mazo", jPanel3);

        jLabel7.setText("Nombre:");

        jLabel8.setText("Correo:");

        agregarUsuarioButton.setText("Agregar Usuario");
        agregarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarUsuarioButtonActionPerformed(evt);
            }
        });

        usuarioEstadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usuarioEstadoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usuarioRangoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usuarioRangoLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 98, Short.MAX_VALUE)
                        .addComponent(agregarUsuarioButton)
                        .addGap(70, 70, 70))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(4, 4, 4)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(usuarioNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                            .addComponent(usuarioCorreoField))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(usuarioEstadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(usuarioRangoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(usuarioNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(usuarioCorreoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(agregarUsuarioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioEstadoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioRangoLabel)
                .addContainerGap(204, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Registrar Usuario", jPanel2);

        jLabel1.setText("Lista de Cartas");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Lista de Usuarios");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        usuarioListaTextView.setEditable(false);
        usuarioListaTextView.setColumns(20);
        usuarioListaTextView.setRows(5);
        usuarioListaTextView.setTabSize(4);
        usuarioListaTextView.setText("A\nB\nC\nD\nE\nF\nG");
        jScrollPane3.setViewportView(usuarioListaTextView);

        cartaListaTextView.setEditable(false);
        cartaListaTextView.setColumns(20);
        cartaListaTextView.setRows(5);
        cartaListaTextView.setTabSize(4);
        cartaListaTextView.setText("A\nB\nC\nD\nE\nF\nG");
        jScrollPane4.setViewportView(cartaListaTextView);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void agregarUsuarioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarUsuarioButtonActionPerformed
		try {
			String nombre = usuarioNameField.getText();
			String correo = usuarioCorreoField.getText();
			if (nombre.length() < 2 || nombre.length() > 15) {
				usuarioEstadoLabel.setForeground(Color.RED);
				usuarioRangoLabel.setForeground(Color.RED);
				usuarioEstadoLabel.setText("Nombre de Usario fuera de Rango!");
				usuarioRangoLabel.setText("Rango es entre 2 y 15");
			} else {
				EVA3.addUsuario(nombre, correo);
				usuarioEstadoLabel.setForeground(Color.GREEN);
				usuarioEstadoLabel.setText("Usuario Registrado Exitosamente!");
				updateRegistrarUsuario();
			}
		} catch (Exception e) {
			usuarioEstadoLabel.setForeground(Color.RED);
			usuarioEstadoLabel.setText("Error al Registrar Usuario...");
		} finally {
			Timer timer = new Timer(2000, event -> {
				usuarioEstadoLabel.setForeground(Color.BLACK);
				usuarioEstadoLabel.setText("");
				usuarioRangoLabel.setText("");
			});
			timer.setRepeats(false);
			timer.start();
		}
	}//GEN-LAST:event_agregarUsuarioButtonActionPerformed

	private void agregarCartaMazoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarCartaMazoButtonActionPerformed
		try {
			String nombre = nombreMazoField.getText();
			int idUser = Integer.parseInt(idUsuarioAgregarMazoField.getText());
			int idCard = Integer.parseInt(idCartaAgregarMazoField.getText());
			ArrayList mazoUsuario = EVA3.getMazoData(idUser);
			int count = 0;
			for (Object data : mazoUsuario) {
				if ((int)data == idCard) {
					count += 1;
				}
			}
			if (count >= 3) {
				mazoEstadoLabel.setForeground(Color.RED);
				mazoEstadoLabel.setText("No se puedo agregar la Carta al Mazo...");
			} else {
				EVA3.addMazo(nombre, idUser, idCard);
				mazoEstadoLabel.setForeground(Color.GREEN);
				mazoEstadoLabel.setText("Carta agregada al Mazo Exitosamente!");
			}
			if (idUser == lastSelectedMazo) {
				updateAgregarMazo();
			}
		} catch (Exception e) {
			mazoEstadoLabel.setForeground(Color.RED);
			mazoEstadoLabel.setText("No se puedo agregar la Carta al Mazo...");
		} finally {
			Timer timer = new Timer(2000, event -> {
				mazoEstadoLabel.setForeground(Color.BLACK);
				mazoEstadoLabel.setText("");
			});
			timer.setRepeats(false);
			timer.start();
		}
	}//GEN-LAST:event_agregarCartaMazoButtonActionPerformed

	private void mostrarMazoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarMazoButtonActionPerformed
		//idUsuarioMostrarMazoField.setText("");
		lastSelectedMazo = Integer.parseInt(idUsuarioMostrarMazoField.getText());
		updateAgregarMazo();
	}//GEN-LAST:event_mostrarMazoButtonActionPerformed

	private void registrarCartaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarCartaButtonActionPerformed
		try {
			String nombre = cartaNameField.getText();
			String elemento = elementoBox.getSelectedItem().toString();
			int atq = Integer.parseInt(ataqueField.getText());
			int def = Integer.parseInt(defensaField.getText());
			if (elementoBox.getSelectedIndex() == 0) {
				cartaEstadoLabel.setForeground(Color.RED);
				cartaEstadoLabel.setText("Seleccione un Elemento...");
			} else {
				EVA3.addCarta(nombre, elemento, atq, def);
				cartaEstadoLabel.setForeground(Color.GREEN);
				cartaEstadoLabel.setText("Carta Registrada Exitosamente!");
				updateRegistrarCarta();
			}
		} catch (Exception e) {
			cartaEstadoLabel.setForeground(Color.RED);
			cartaEstadoLabel.setText("Error al Registrar Carta...");
		} finally {
			Timer timer = new Timer(2000, event -> {
				cartaEstadoLabel.setForeground(Color.BLACK);
				cartaEstadoLabel.setText("");
			});
			timer.setRepeats(false);
			timer.start();
		}
	}//GEN-LAST:event_registrarCartaButtonActionPerformed

	public void updateRegistrarCarta() {
		EVA3.getCartas();
		cartaNameField.setText("");
		elementoBox.setSelectedIndex(0);
		ataqueField.setText("");
		defensaField.setText("");
		
		String cartaListaText = "ID - Nombre - Elemento - Ataque - Defensa";
		for (String text : EVA3.cartasLista) {
			cartaListaText += "\n" + text;
		}
		cartaListaTextView.setText(cartaListaText);
	}
	public void updateRegistrarUsuario() {
		EVA3.getUsuarios();
		usuarioNameField.setText("");
		usuarioCorreoField.setText("");
		
		String usuarioListaText = "ID - Nombre - Correo";
		for (String text : EVA3.usuariosLista) {
			usuarioListaText += "\n" + text;
		}
		usuarioListaTextView.setText(usuarioListaText);
	}
	public void updateAgregarMazo() {
		EVA3.getMazoString(lastSelectedMazo);
		String mazoListaText = idUsuarioMostrarMazoField.getText() + " ID - Mazo" + "\n    N° - Nombre - Elemento - Ataque - Defensa";
//		for (String text : EVA3.mazoLista) {
//			mazoListaText += "\n    " + text;
//		}
		int count = 1;
		ArrayList mazoUsuario = EVA3.getMazoData(lastSelectedMazo);
		for (Object data : mazoUsuario) {
			mazoListaText += "\n    " + count + " - " + EVA3.getCartaSingle((int)data);
			count += 1;
		}
		
		mazoListaTextView.setText(mazoListaText);
	}
	public void startUpdate(){
		updateRegistrarCarta();
		updateRegistrarUsuario();
		mazoListaTextView.setText("");
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
			java.util.logging.Logger.getLogger(ManejadorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ManejadorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ManejadorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ManejadorForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ManejadorForm().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarCartaMazoButton;
    private javax.swing.JButton agregarUsuarioButton;
    private javax.swing.JTextField ataqueField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel cartaEstadoLabel;
    private javax.swing.JTextArea cartaListaTextView;
    private javax.swing.JTextField cartaNameField;
    private javax.swing.JTextField defensaField;
    private javax.swing.JComboBox<String> elementoBox;
    private javax.swing.JTextField idCartaAgregarMazoField;
    private javax.swing.JTextField idUsuarioAgregarMazoField;
    private javax.swing.JTextField idUsuarioMostrarMazoField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel mazoEstadoLabel;
    private javax.swing.JTextArea mazoListaTextView;
    private javax.swing.JButton mostrarMazoButton;
    private javax.swing.JTextField nombreMazoField;
    private javax.swing.JButton registrarCartaButton;
    private javax.swing.JTextField usuarioCorreoField;
    private javax.swing.JLabel usuarioEstadoLabel;
    private javax.swing.JTextArea usuarioListaTextView;
    private javax.swing.JTextField usuarioNameField;
    private javax.swing.JLabel usuarioRangoLabel;
    // End of variables declaration//GEN-END:variables
}
