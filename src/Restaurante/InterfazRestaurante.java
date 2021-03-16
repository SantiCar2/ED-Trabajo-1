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
import Excepciones.EPlatoExiste;

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
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
	private JComboBox MeserosDisponibles;
	private JComboBox MeserosOcupados;
	private JButton btnAddPlato;
	private JButton btnEliminarPlato;
	private JComboBox listaPlatos;
	private String[] values;
	private String[] listaPlatosSTR;
	
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
		values = new String[Test.r.getOrden().length];
		for(int i = 0; i < Test.r.getOrden().length; i++) {
			values[i] = "Orden de " + Test.r.getOrden()[i].clientePpal.getNombre();
			System.out.println(values[i]);
		}
		ordenIndex ordenIndex = new ordenIndex(0);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1221, 700);
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
				if(!list.isSelectionEmpty()) {
					CerrarOrden.setEnabled(true);
					btnAsignar.setEnabled(true);
					int i = 0;
					boolean isDone = false;
					while (i < values.length && !isDone) {
						try {
						if(list.getSelectedValue().toString().equalsIgnoreCase(values[i])) {
								lblInfoOrden.setText("<html><body>" + Test.r.getOrden()[i].getClientePpal().getNombre()
										+ "<br> Mesero:" + Test.r.getOrden()[i].getMesero().getNombre() + "<br> Platos:");
								for(int j = 0; j < Test.r.getOrden()[i].getPlatos().length; j++) {
									lblInfoOrden.setText(lblInfoOrden.getText() + "<br>" + Test.r.getOrden()[i].getPlatos()[j].getNombre());
								}
								lblInfoOrden.setText(lblInfoOrden.getText() + "</body></html>");
								isDone = true;
							}
						}catch (Exception e1) {
	
						}
					}
					ordenIndex.setIndex(i);
				}
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
					if(Test.r.getOrden()[ordenIndex.getIndex()].getNroMesa() == 0) {
						txtMesaNoDisponible.setText(String.valueOf(Test.r.asignarMesa(Integer.parseInt(cantidadComensales.getText()))));
						if(Integer.parseInt(cantidadComensales.getText()) <= 6) {
							for (int i = 0; i < Test.r.getOrden().length; i++) {
								if(list.getSelectedValue().toString().equalsIgnoreCase(values[i])) {
									Test.r.getOrden()[i].setNroMesa(Integer.parseInt(txtMesaNoDisponible.getText()));
									Test.r.setOrden(Test.r.getOrden());
								}
							}
						}else {
							JOptionPane.showMessageDialog(null, "La cantidad maxima de comensales es de 6",null, 1);
						}
					}else {
						JOptionPane.showMessageDialog(null, "La mesa ya esta asignada",null, 1);
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
		String[] meserosTotales = new String[Test.r.getMeseros().length];
		for (int i = 0; i < meserosTotales.length; i++) {
			meserosTotales[i] = Test.r.getMeseros()[i].getNombre();
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
		
		MeserosDisponibles = new JComboBox();
		String[] meserosDisponibles = new String[0];
		for(int i = 0; i < Test.r.getMeseros().length; i++) {
			if(Test.r.getMeseros()[i].getMesasDisponibles() != 0) {
				meserosDisponibles = Arrays.copyOf(meserosDisponibles, meserosDisponibles.length + 1);
				meserosDisponibles[meserosDisponibles.length - 1] = Test.r.getMeseros()[i].getNombre();
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
		
		MeserosOcupados = new JComboBox();
		String[] meserosOcupados = new String[0];
		for(int i = 0; i < Test.r.getMeseros().length; i++) {
			if(Test.r.getMeseros()[i].getMesasDisponibles() == 0) {
				meserosOcupados = Arrays.copyOf(meserosOcupados, meserosOcupados.length + 1);
				meserosOcupados[meserosOcupados.length - 1] = Test.r.getMeseros()[i].getNombre();
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
					Test.r.eliminarOrden(Test.r.getOrden()[ordenIndex.getIndex()]);
					actualizarOrden(Test.r, Test.r.getOrden());
				} catch (EOrdenExiste e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), null, 0);
				}
			}
		});
		CerrarOrden.setBounds(705, 584, 143, 23);
		frame.getContentPane().add(CerrarOrden);
		
		btnNewButton = new JButton("Actualizar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar(Test.r);
			}
		});
		btnNewButton.setBounds(10, 584, 125, 23);
		frame.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(901, 55, 2, 564);
		frame.getContentPane().add(separator);
		
		JLabel lblNewLabel = new JLabel("Gestion de platos:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(932, 53, 174, 32);
		frame.getContentPane().add(lblNewLabel);
		
		btnAddPlato = new JButton("Agregar plato");
		btnAddPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = JOptionPane.showInputDialog("Ingrese nombre del plato");
				if(nombre == null) {
					JOptionPane.showMessageDialog(null, "Operación cancelada.",null, 0);
				}else {
					String[] tipo = {"Entrada", "Fuerte", "Bebida"};
					JComboBox jcb = new JComboBox(tipo);
					jcb.setEditable(true);
					JOptionPane.showMessageDialog( null, jcb, "Seleccione el tipo de plato", JOptionPane.QUESTION_MESSAGE);
					if(jcb.getSelectedIndex() == -1) {
						JOptionPane.showMessageDialog(null, "Operación cancelada.",null, 0);
					} else {
						String tipoPlato = jcb.getSelectedItem().toString();
						int precio = -1;
						while (precio == -1) {
							try {
								precio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el precio del plato"));
							} catch (Exception e2) {
								precio = -1;
							}
						}
						String id = JOptionPane.showInputDialog("Ingrese el id del plato");
						Ingrediente[] ingredientes = new Ingrediente[2];
						String[] ing = new String[Test.r.getIngredientes().length]; 
						for(int i = 0; i < Test.r.getIngredientes().length; i++) {
							ing[i] = Test.r.getIngredientes()[i].getNombre();
						}
						jcb = new JComboBox(ing);
						jcb.setEditable(true);
						JOptionPane.showMessageDialog(null, jcb, "Seleccione el ingrediente principal", JOptionPane.QUESTION_MESSAGE);
						ingredientes[0] = Test.r.getIngredientes()[jcb.getSelectedIndex()];
						jcb = new JComboBox(ing);
						jcb.setEditable(true);
						JOptionPane.showMessageDialog(null, jcb, "Seleccione el ingrediente secundario", JOptionPane.QUESTION_MESSAGE);
						ingredientes[1] = Test.r.getIngredientes()[jcb.getSelectedIndex()];
						Plato p = new Plato(nombre, tipoPlato, precio, id, ingredientes);
						try {
							Test.r.crearPlato(p);
							listaPlatosSTR = new String[Test.r.getPlatos().length];
							for (int i = 0; i < Test.r.getPlatos().length; i++) {
								listaPlatosSTR[i] = Test.r.getPlatos()[i].getNombre();
							}
							listaPlatos.setModel(new DefaultComboBoxModel(listaPlatosSTR));
							
							String[] Entradas = new String[0];
							for(int i = 0; i < Test.r.getPlatos().length; i++) {
								if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Entrada")) {
									Entradas = Arrays.copyOf(Entradas, Entradas.length + 1);
									Entradas[Entradas.length - 1] = Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio();
								}
							}
							String[] Fuertes = new String[0];
							for(int i = 0; i < Test.r.getPlatos().length; i++) {
								if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Fuerte")) {
									Fuertes = Arrays.copyOf(Fuertes, Fuertes.length + 1);
									Fuertes[Fuertes.length - 1] = Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio();
								}
							}
							String[] Bebidas = new String[0];
							for(int i = 0; i < Test.r.getPlatos().length; i++) {
								if(Test.r.getPlatos()[i].getTipoPlato().equalsIgnoreCase("Bebida")) {
									Bebidas = Arrays.copyOf(Bebidas, Bebidas.length + 1);
									Bebidas[Bebidas.length - 1] = Test.r.getPlatos()[i].getNombre() + ": $" + Test.r.getPlatos()[i].getPrecio();
								}
							}
						} catch (EPlatoExiste e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(),null, 0);
						}
					}
				}
			}
		});
		btnAddPlato.setBounds(930, 109, 176, 23);
		frame.getContentPane().add(btnAddPlato);
		
		btnEliminarPlato = new JButton("Eliminar plato seleccionado");
		btnEliminarPlato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Plato[] pl = Test.r.getPlatos();
					
					Ingrediente i1 = new Ingrediente("tomate", 3, "120");
					Ingrediente i2 = new Ingrediente("lechuga", 4, "1202");
					Ingrediente ing[] = new Ingrediente[2];
					ing[0]=i1;
					ing[1]=i2;
					Plato p2 = new Plato("Pizza", "Fuerte",27000, "121201", ing);
					Test.r.eliminarPlato(p2);
				} catch (EPlatoExiste e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),null, 0);
				}
			}
		});
		btnEliminarPlato.setBounds(932, 239, 176, 23);
		frame.getContentPane().add(btnEliminarPlato);
		
		listaPlatosSTR = new String[Test.r.getPlatos().length];
		for (int i = 0; i < Test.r.getPlatos().length; i++) {
			listaPlatosSTR[i] = Test.r.getPlatos()[i].getNombre();
		}
		listaPlatos = new JComboBox(listaPlatosSTR);
		listaPlatos.setBounds(932, 180, 174, 20);
		frame.getContentPane().add(listaPlatos);

		JLabel lblNewLabel_1 = new JLabel("Buscqar y/o eliminar:");
		lblNewLabel_1.setBounds(932, 161, 125, 14);
		frame.getContentPane().add(lblNewLabel_1);
	}
	
	void actualizarOrden(Restaurante r, Orden[] o) {
		r.setOrden(o);
		Test.r = r;
		
		System.out.println("Actualizando...");
		
		String[] meserosOcupados = new String[0];
		for(int i = 0; i < r.getMeseros().length; i++) {
			if(r.getMeseros()[i].getMesasDisponibles() == 0) {
				meserosOcupados = Arrays.copyOf(meserosOcupados, meserosOcupados.length + 1);
				meserosOcupados[meserosOcupados.length - 1] = r.getMeseros()[i].getNombre();
			}
		}
		MeserosOcupados.setModel(new DefaultComboBoxModel(meserosOcupados));
		
		String[] meserosTotales = new String[r.getMeseros().length];
		for (int i = 0; i < meserosTotales.length; i++) {
			meserosTotales[i] = r.getMeseros()[i].getNombre();
		}
		MeserosTotal.setModel(new DefaultComboBoxModel(meserosTotales));
		
		lblInfoOrden.setText("");
		
		CerrarOrden.setEnabled(false);
		btnAsignar.setEnabled(false);
		
		o = r.getOrden();
		String[] values = new String[o.length];
		for(int i = 0; i < o.length; i++) {
			values[i] = "Orden de " + o[i].clientePpal.getNombre();
			System.out.println(values[i]);
		}
		list.clearSelection();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});

	}
	
	public void actualizar(Restaurante r) {
		System.out.println("Actualizando...");
		
		String[] meserosOcupados = new String[0];
		for(int i = 0; i < r.getMeseros().length; i++) {
			if(r.getMeseros()[i].getMesasDisponibles() == 0) {
				meserosOcupados = Arrays.copyOf(meserosOcupados, meserosOcupados.length + 1);
				meserosOcupados[meserosOcupados.length - 1] = r.getMeseros()[i].getNombre();
			}
		}
		MeserosOcupados.setModel(new DefaultComboBoxModel(meserosOcupados));
		
		String[] meserosTotales = new String[r.getMeseros().length];
		for (int i = 0; i < meserosTotales.length; i++) {
			meserosTotales[i] = r.getMeseros()[i].getNombre();
		}
		MeserosTotal.setModel(new DefaultComboBoxModel(meserosTotales));
		
		lblInfoOrden.setText("");
		
		CerrarOrden.setEnabled(false);
		btnAsignar.setEnabled(false);
		
		Orden[] o = r.getOrden();
		values = new String[o.length];
		for(int i = 0; i < o.length; i++) {
			values[i] = "Orden de " + o[i].clientePpal.getNombre();
			System.out.println(values[i]);
		}
		list.clearSelection();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});

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

