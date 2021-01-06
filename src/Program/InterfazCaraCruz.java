package Program;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Program.InterfazPPT.ButtonClickListener;

public class InterfazCaraCruz {

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel clockLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private String eleccion;
	private static CyclicBarrier barrera = new CyclicBarrier(2);//El objetivo de esta barrera es que el programa principal no continue hasta que el cliente pulse un bot�n

	public InterfazCaraCruz() {
		mainFrame = new JFrame("Cara-O-Cruz");
		mainFrame.setSize(400, 200);
		mainFrame.setLayout(new GridLayout(3, 1));

		headerLabel = new JLabel("", JLabel.CENTER);
		clockLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		clockLabel.setFont(new Font(clockLabel.getFont().getName(), Font.BOLD, 16));
		statusLabel.setSize(350, 100);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());

		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		showEventDemo();
		try {
			barrera.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showEventDemo() {
		headerLabel.setText("Dale al bot�n para lanzar la moneda");

		JButton lanzarButton = new JButton("Lanzar");

		lanzarButton.addActionListener(new ButtonClickListener());

		controlPanel.add(lanzarButton);

		mainFrame.setVisible(true);
	}

	public String getEleccion() {
		return this.eleccion;
	}
	
	/*MOSTRAR SOLUCI�N EN UN LABEL
	 * Pre: Recibe un String el cual es la soluci�n
	 * Post: Se muestra la soluci�n en un label
	 */
	public void result(String res) {
		statusLabel.setText(res);
	}

	/*
	 * Pre: El m�todo es referenciado cuando se pulsa un bot�n
	 * Post: Define la elecci�n respecto del bot�n pulsado
	 */
	public class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				barrera.await();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BrokenBarrierException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
