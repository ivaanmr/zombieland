package com.rzg.zombieland.cliente.interfazInicioSesion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JMenuBar;

/**
 * Interfaz completa de inicio de sesi�n.
 * @author Manuel
 */

public class InterfazInicioSesion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frmZombielandV;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField userField;
	private JPasswordField passwordField_1;
	private static final int intentos = 0; // Se incrementar� cuando el password sea incorrecto

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazInicioSesion window = new InterfazInicioSesion();
					window.frmZombielandV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazInicioSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZombielandV = new JFrame();
		frmZombielandV.setTitle("Zombieland v1.0 - Inicio de sesi\u00F3n");
		frmZombielandV.setBounds(100, 100, 450, 325);
		frmZombielandV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZombielandV.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 161, 92, 14);
		frmZombielandV.getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 192, 92, 14);
		frmZombielandV.getContentPane().add(lblPassword);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(252, 192, 172, 14);
		frmZombielandV.getContentPane().add(lblError);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aqu� se validar�n los datos (Usuario - Contrase�a)
				// Si son correctos, se enviar� al lobby de partidas.
				// Si son incorrectos, se enviar� un mensaje de error
				// en lblError, y se incrementar� en 1 el contador de 'intentos'.
				// Si 'intentos' = 3, se tomar�n las medidas propuestas
				// de la pregunta y respuesta de seguridad.
			}
		});
		btnIngresar.setBounds(60, 217, 139, 34);
		frmZombielandV.getContentPane().add(btnIngresar);
		
		userField = new JTextField();
		userField.setBounds(112, 158, 115, 20);
		frmZombielandV.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(112, 189, 115, 20);
		frmZombielandV.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InterfazInicioSesion.class.getResource("/com/rzg/zombieland/cliente/interfazInicioSesion/Zombieland.png")));
		lblNewLabel.setBounds(252, 0, 159, 179);
		frmZombielandV.getContentPane().add(lblNewLabel);
		
		JLabel lblMsg = new JLabel("No tenes un usuario?");
		lblMsg.setBounds(27, 11, 200, 37);
		frmZombielandV.getContentPane().add(lblMsg);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Aqu� se enviar� a la p�gina de creaci�n de un usuario nuevo.
			}
		});
		btnRegistrarse.setBounds(98, 69, 116, 30);
		frmZombielandV.getContentPane().add(btnRegistrarse);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
		lblRzg.setForeground(SystemColor.controlShadow);
		lblRzg.setBounds(369, 248, 110, 14);
		frmZombielandV.getContentPane().add(lblRzg);
		
		JLabel lblUniteAZombieland = new JLabel("Unite a Zombieland!");
		lblUniteAZombieland.setBounds(27, 30, 154, 37);
		frmZombielandV.getContentPane().add(lblUniteAZombieland);
		
		JButton btnO = new JButton("Olvid\u00F3 su clave?");
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Se direccionar� a la pantalla de recuperaci�n de clave
				// haciendo uso de la pregunta de seguridad.
			}
		});
		btnO.setBounds(209, 217, 126, 34);
		frmZombielandV.getContentPane().add(btnO);
		
		JMenuBar menuBar = new JMenuBar();
		frmZombielandV.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
		mnArchivo.add(mntmIniciarSesion);
		
		JMenuItem mntmRegistrarse = new JMenuItem("Registrarse");
		mnArchivo.add(mntmRegistrarse);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnPartida = new JMenu("Partida");
		menuBar.add(mnPartida);
		
		JMenuItem mntmJugar = new JMenuItem("Jugar");
		mnPartida.add(mntmJugar);
		
		JMenuItem mntmVerPartidas = new JMenuItem("Ver Partidas");
		mnPartida.add(mntmVerPartidas);
		
		JMenu mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);
		
		JMenuItem mntmDatos = new JMenuItem("Datos");
		mnCuenta.add(mntmDatos);
		
		JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mnCuenta.add(mntmEstadsticas);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
		mnAyuda.add(mntmCmoJugar);
		
		JMenuItem mntmGoogleame = new JMenuItem("Googleame");
		mnAyuda.add(mntmGoogleame);
	}
}
