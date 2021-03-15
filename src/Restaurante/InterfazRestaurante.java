package Restaurante;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Excepciones.ENoHayMesasDisponibles;
import Excepciones.EOrdenExiste;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.LayoutManager;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.sound.sampled.DataLine;
import javax.swing.AbstractListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.StreamCorruptedException;
import java.util.Arrays;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazRestaurante {

	private JFrame frame;
	private JFrame frameMesero;
	private JList list;
	private JLabel lblOrdenes;
	private JLabel lblCantidadComensales;
	private JTextField cantidadComensales;
	private JButton btnAsignar;
	private JTextField txtMesaNoDisponible;
	private JComboBox MeserosTotal;
	private JLabel lblOcupados;
	private JLabel lblMeseros;
	private JLabel lblDisponibles;
	private JLabel lblInfoOrden;
	private JButton CerrarOrden;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazRestaurante window = new InterfazRestaurante();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazRestaurante() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		//Creamos restaurante
		Restaurante r = new Restaurante();
		Orden[] o = r.getOrden();
		String[] values = new String[o.length];
		for(int i = 0; i < o.length; i++) {
			values[i] = "Orden de " + o[i].clientePpal.getNombre();
		}
		ordenIndex ordenIndex = new ordenIndex(0);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1050, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblOrdenes = new JLabel("Ordenes");
		lblOrdenes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOrdenes.setBounds(46, 161, 143, 39);
		frame.getContentPane().add(lblOrdenes);
		
		
		list = new JList();
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.addListSelectionListener(new ListSelectionListener() {
		
			@Override
			public void valueChanged(ListSelectionEvent e) {
				CerrarOrden.setEnabled(true);
				btnAsignar.setEnabled(true);
				int i = 0;
				boolean isDone = false;
				while (i < values.length && !isDone) {
					if(list.getSelectedValue().toString().equalsIgnoreCase(values[i])) {
						lblInfoOrden.setText(o[i].getClientePpal().getNombre() + "\n Mesa numero:" + o[i].getNroMesa()
								+ "\n Mesero:" + o[i].getMesero().getNombre() + "Platos:");
						for(int j = 0; j < o[i].getPlatos().length; j++) {
							lblInfoOrden.setText(lblInfoOrden.getText() + "\n" + o[i].getPlatos()[j].getNombre());
						}
						isDone = true;
					}
				}
				ordenIndex.setIndex(i);
			}
		});
		
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(41, 202, 405, 179);
		frame.getContentPane().add(list);
		
		lblCantidadComensales = new JLabel("Cantidad de comensales:");
		lblCantidadComensales.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidadComensales.setBounds(41, 38, 197, 39);
		frame.getContentPane().add(lblCantidadComensales);
		
		cantidadComensales = new JTextField();
		cantidadComensales.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cantidadComensales.setBounds(248, 44, 35, 32);
		frame.getContentPane().add(cantidadComensales);
		cantidadComensales.setColumns(10);
		
		btnAsignar = new JButton("Asignar");
		btnAsignar.setEnabled(false);
		btnAsignar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtMesaNoDisponible.setText(String.valueOf(r.asignarMesa(Integer.parseInt(cantidadComensales.getText()))));
					for (int i = 0; i < o.length; i++) {
						if(list.getSelectedValue().toString().equalsIgnoreCase(values[i])) {
							o[i].setNroMesa(Integer.parseInt(txtMesaNoDisponible.getText()));
						}
					}
				} catch (ENoHayMesasDisponibles e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),null, 0);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Por favor ingrese un numero valido.",null, 0);
				}
			}
		});
		btnAsignar.setForeground(Color.BLACK);
		btnAsignar.setBounds(297, 44, 85, 32);
		frame.getContentPane().add(btnAsignar);
		
		JLabel lblMesaAsignada = new JLabel("Mesa asignada:");
		lblMesaAsignada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMesaAsignada.setBounds(42, 88, 197, 39);
		frame.getContentPane().add(lblMesaAsignada);
		
		txtMesaNoDisponible = new JTextField();
		txtMesaNoDisponible.setEditable(false);
		txtMesaNoDisponible.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMesaNoDisponible.setText("mesa no disponible");
		txtMesaNoDisponible.setColumns(10);
		txtMesaNoDisponible.setBounds(180, 93, 186, 32);
		frame.getContentPane().add(txtMesaNoDisponible);
		
		MeserosTotal = new JComboBox();
		MeserosTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MeserosTotal.getSelectedItem().equals("Ricardo")) {
					System.out.println("Informacion de ricardo");
				}
				if(MeserosTotal.getSelectedItem().equals("Juan")) {
					System.out.println("Informacion de juan");
				}
				if(MeserosTotal.getSelectedItem().equals("Andres")) {
					System.out.println("Informacion de andres");
				}
				if(MeserosTotal.getSelectedItem().equals("Jose")) {
					System.out.println("Informacion de jose");
				}
				
			}
		});
		
		MeserosTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		String[] meserosTotales = new String[r.getMeseros().length];
		for (int i = 0; i < meserosTotales.length; i++) {
			meserosTotales[i] = r.getMeseros()[i].getNombre();
		}
		MeserosTotal.setModel(new DefaultComboBoxModel(meserosTotales));
		MeserosTotal.setBounds(10, 536, 105, 25);
		frame.getContentPane().add(MeserosTotal);
		
		lblMeseros = new JLabel("Meseros");
		lblMeseros.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMeseros.setBounds(12, 492, 103, 39);
		frame.getContentPane().add(lblMeseros);
		
		lblDisponibles = new JLabel("Disponibles");
		lblDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDisponibles.setBounds(168, 492, 157, 39);
		frame.getContentPane().add(lblDisponibles);
		
		JComboBox MeserosDisponibles = new JComboBox();
		String[] meserosDisponibles = new String[0];
		for(int i = 0; i < r.getMeseros().length; i++) {
			if(r.getMeseros()[i].getMesasDisponibles() != 0) {
				meserosDisponibles = Arrays.copyOf(meserosDisponibles, meserosDisponibles.length + 1);
				meserosDisponibles[meserosDisponibles.length - 1] = r.getMeseros()[i].getNombre();
			}
		}
		MeserosDisponibles.setModel(new DefaultComboBoxModel(meserosDisponibles));
		MeserosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MeserosDisponibles.setBounds(155, 536, 105, 25);
		frame.getContentPane().add(MeserosDisponibles);
		
		lblOcupados = new JLabel("Ocupados");
		lblOcupados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOcupados.setBounds(335, 492, 157, 39);
		frame.getContentPane().add(lblOcupados);
		
		JComboBox MeserosOcupados = new JComboBox();
		String[] meserosOcupados = new String[0];
		for(int i = 0; i < r.getMeseros().length; i++) {
			if(r.getMeseros()[i].getMesasDisponibles() != 0) {
				meserosOcupados = Arrays.copyOf(meserosOcupados, meserosOcupados.length + 1);
				meserosOcupados[meserosOcupados.length - 1] = r.getMeseros()[i].getNombre();
			}
		}
		MeserosOcupados.setModel(new DefaultComboBoxModel(meserosOcupados));
		MeserosOcupados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		MeserosOcupados.setBounds(319, 536, 105, 25);
		frame.getContentPane().add(MeserosOcupados);
		
		lblInfoOrden = new JLabel("");
		lblInfoOrden.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblInfoOrden.setBounds(504, 161, 344, 412);
		frame.getContentPane().add(lblInfoOrden);
		
		CerrarOrden = new JButton("Cerrar Orden");
		CerrarOrden.setEnabled(false);
		CerrarOrden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					r.eliminarOrden(o[ordenIndex.getIndex()]);
				} catch (EOrdenExiste e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, 0);
				}
			}
		});
		CerrarOrden.setBounds(743, 584, 105, 23);
		frame.getContentPane().add(CerrarOrden);
		
		btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(r, o);
			}
		});
		btnNewButton.setBounds(10, 584, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	void actualizar(Restaurante r, Orden[] o) {
		r.setOrden(o);
		frame.dispose();
		frame.setVisible(true);
		System.out.println("Actualizando...");
	}
	
}


class ordenIndex{
	private int i;
	public ordenIndex(int i) {
		this.i = i;
	}
	public int getIndex() {
		return this.i;
	}
	public void setIndex(int i) {
		this.i = i;
	}
}

