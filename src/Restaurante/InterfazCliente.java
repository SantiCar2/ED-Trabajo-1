package Restaurante;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Excepciones.EClienteExiste;
import Excepciones.ENoHayMeseros;
import Excepciones.EPlatoExiste;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfazCliente extends JFrame {

	private JPanel contentPane;
	private JList listaPlatosF;
	private JList listaBebidas;
	private JList listaEntradas;
	private JLabel lblPlatosF;
	private JLabel lblBebidas;
	private JLabel lblEntradas;
	private JTextArea txtAreaFactura;
	private String textoFactura;
	private JLabel lblFactura;
	private JLabel lblPrecioT;
	private JTextField txtCalculoPrecio;
	private Plato[] platosAOrdenar = new Plato[0];
	private JButton btnActualizar;

	
//	private JScrollPane scrollEntradas;
//	private JScrollPane scrollPlatosF;
//	private JScrollPane scrollBebidas;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCliente frame = new InterfazCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazCliente() {
		setTitle("Interfaz men\u00FA y factura");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Para que aparezca en el centro de la pantalla
		setLocationRelativeTo(null);
		String[] Entradas;
		Entradas = new String[0];
		for(int i = 0; i < Test.r.getPlatos().length; i++) {
			if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Entrada")) {
				Entradas = Arrays.copyOf(Entradas, Entradas.length + 1);
				Entradas[Entradas.length - 1] = Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio();
			}
		}
		
		listaEntradas = new JList(Entradas);
		listaEntradas.setBounds(15, 81, 187, 104);
		contentPane.add(listaEntradas);
		listaEntradas.setVisible(true);
		
		listaEntradas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					textoFactura = listaEntradas.getSelectedValue().toString();
					txtAreaFactura.append(textoFactura + "\n");
					
					platosAOrdenar = Arrays.copyOf(platosAOrdenar, platosAOrdenar.length + 1);
					for (int i = 0; i < Test.r.platos.length; i++) {
						if(Test.r.platos[i].getNombre().equalsIgnoreCase(listaEntradas.getSelectedValue().toString().split(":")[0])) {
							platosAOrdenar[platosAOrdenar.length - 1] = Test.r.platos[i];
							
							txtCalculoPrecio.setText(String.valueOf((int)Test.r.getPlatos()[i].getPrecio() + Integer.parseInt(txtCalculoPrecio.getText())));
						}
					}
					
					
				}}		
				});
		String[] Fuertes;
		Fuertes = new String[0];
		for(int i = 0; i < Test.r.getPlatos().length; i++) {
			if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Fuerte")) {
				Fuertes = Arrays.copyOf(Fuertes, Fuertes.length + 1);
				Fuertes[Fuertes.length - 1] = Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio();
			}
		}
		listaPlatosF = new JList(Fuertes);
		listaPlatosF.setBounds(15, 217, 187, 149);
		contentPane.add(listaPlatosF);
		listaPlatosF.setVisible(true);
		
		listaPlatosF.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					textoFactura = listaPlatosF.getSelectedValue().toString();
					txtAreaFactura.append(textoFactura + "\n");
					
					platosAOrdenar = Arrays.copyOf(platosAOrdenar, platosAOrdenar.length + 1);
					for (int i = 0; i < Test.r.platos.length; i++) {
						if(Test.r.platos[i].getNombre().equalsIgnoreCase(listaPlatosF.getSelectedValue().toString().split(":")[0])) {
							platosAOrdenar[platosAOrdenar.length - 1] = Test.r.platos[i];
							
							txtCalculoPrecio.setText(String.valueOf((int)Test.r.getPlatos()[i].getPrecio() + Integer.parseInt(txtCalculoPrecio.getText())));
						}
					}
				}}		
				});
		String[] Bebidas;
		Bebidas = new String[0];
		for(int i = 0; i < Test.r.getPlatos().length; i++) {
			if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Bebida")) {
				Bebidas = Arrays.copyOf(Bebidas, Bebidas.length + 1);
				Bebidas[Bebidas.length - 1] = Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio();
			}
		}
		
		listaBebidas = new JList(Bebidas);
		listaBebidas.setBounds(15, 396, 187, 120);
		contentPane.add(listaBebidas);
		listaBebidas.setVisible(true);

		listaBebidas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()==2) {
					textoFactura = listaBebidas.getSelectedValue().toString();
					txtAreaFactura.append(textoFactura + "\n");
					
					platosAOrdenar = Arrays.copyOf(platosAOrdenar, platosAOrdenar.length + 1);
					for (int i = 0; i < Test.r.platos.length; i++) {
						if(Test.r.platos[i].getNombre().equalsIgnoreCase(listaBebidas.getSelectedValue().toString().split(":")[0])) {
							platosAOrdenar[platosAOrdenar.length - 1] = Test.r.platos[i];
							
							txtCalculoPrecio.setText(String.valueOf((int)Test.r.getPlatos()[i].getPrecio() + Integer.parseInt(txtCalculoPrecio.getText())));
						}
					}
				}}		
			});
		
		lblPlatosF = new JLabel("Platos Fuertes");
		lblPlatosF.setBounds(15, 189, 109, 31);
		lblPlatosF.setVisible(true);
		contentPane.add(lblPlatosF);
		
		lblBebidas = new JLabel("Bebidas");
		lblBebidas.setBounds(15, 372, 69, 20);
		lblBebidas.setVisible(true);
		contentPane.add(lblBebidas);
		
		lblEntradas = new JLabel("Entradas");
		lblEntradas.setBounds(15, 60, 69, 20);
		lblEntradas.setVisible(true);
		contentPane.add(lblEntradas);
		
		
		txtAreaFactura = new JTextArea();
		txtAreaFactura.setEditable(false);
		txtAreaFactura.setBounds(463, 48, 220, 430);
		contentPane.add(txtAreaFactura);
		
		lblFactura = new JLabel("Factura");
		lblFactura.setBounds(471, 25, 69, 20);
		contentPane.add(lblFactura);
		
		lblPrecioT = new JLabel("Precio total: ");
		lblPrecioT.setBounds(423, 492, 99, 20);
		contentPane.add(lblPrecioT);
		
		txtCalculoPrecio = new JTextField();
		txtCalculoPrecio.setText("0");
		txtCalculoPrecio.setEditable(false);
		txtCalculoPrecio.setBounds(513, 489, 146, 26);
		contentPane.add(txtCalculoPrecio);
		txtCalculoPrecio.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Men\u00FA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(15, 25, 69, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Hacer orden");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(platosAOrdenar.length != 0) {
					String nombre = JOptionPane.showInputDialog("Ingrese su nombre.");
					if(nombre != null) {
						String id = JOptionPane.showInputDialog("Ingrese su identificación.");
						if(id != null) {
							String tel = JOptionPane.showInputDialog("Ingrese su número de teléfono.");
							if(tel != null) {
								Orden or = null;
								try {
									or = new Orden(new Cliente(nombre, id, tel), Test.r.asignarMesero());
									or.setPlatos(platosAOrdenar);
									Test.r.hacerOrden(or);
									listaBebidas.clearSelection();
									listaEntradas.clearSelection();
									listaPlatosF.clearSelection();
									txtAreaFactura.setText("");
									txtCalculoPrecio.setText("0");
								} catch (ENoHayMeseros e1) {
									JOptionPane.showMessageDialog(null, "No hay meseros disponibles, intentalo otra vez más tarde.",null, 0);
								}finally {
									Test.admin.actualizar(Test.r);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Operación cancelada.",null, 0);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Operación cancelada.",null, 0);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Operación cancelada.",null, 0);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Seleccione por lo menos un plato.",null, 0);
				}
			}
		});
		btnNewButton.setBounds(513, 526, 146, 23);
		contentPane.add(btnNewButton);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel dlmEntradas = new DefaultListModel();
				for(int i = 0; i < Test.r.getPlatos().length; i++) {
					if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Entrada")) {
						dlmEntradas.addElement(Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio());
					}
				}
				listaEntradas.setModel(dlmEntradas);
				DefaultListModel dlmFuerte = new DefaultListModel();
				for(int i = 0; i < Test.r.getPlatos().length; i++) {
					if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Fuerte")) {
						dlmFuerte.addElement(Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio());
					}
				}
				listaPlatosF.setModel(dlmFuerte);
				DefaultListModel dlmBebidas = new DefaultListModel();
				for(int i = 0; i < Test.r.getPlatos().length; i++) {
					if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Bebida")) {
						dlmBebidas.addElement(Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio());
					}
				}
				listaBebidas.setModel(dlmBebidas);
			}
		});
		btnActualizar.setBounds(15, 526, 109, 23);
		contentPane.add(btnActualizar);
	}
}
