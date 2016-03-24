import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import java.awt.CardLayout;

public class GUI {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(100, 100, 638, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane);
		
		JPanel Preferences = new JPanel();
		Preferences.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Preferences.setBackground(new Color(128, 128, 128));
		
		JLabel lblLearnrate = new JLabel("Learnrate:");
		
		JLabel lblPreferences = new JLabel("Preferences");
		GroupLayout gl_Preferences = new GroupLayout(Preferences);
		gl_Preferences.setHorizontalGroup(
			gl_Preferences.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Preferences.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_Preferences.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Preferences.createSequentialGroup()
							.addGap(5)
							.addComponent(lblLearnrate))
						.addComponent(lblPreferences))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_Preferences.setVerticalGroup(
			gl_Preferences.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Preferences.createSequentialGroup()
					.addGap(5)
					.addComponent(lblPreferences)
					.addGap(18)
					.addComponent(lblLearnrate)
					.addContainerGap(279, Short.MAX_VALUE))
		);
		Preferences.setLayout(gl_Preferences);
		
		JLabel lblMainView = new JLabel("Main View");
		lblMainView.setFont(new Font("Tahoma", Font.PLAIN, 21));
		
		JPanel Action_Panel = new JPanel();
		Action_Panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		Action_Panel.setBackground(new Color(128, 128, 128));
		
		JLabel lblActions = new JLabel("Actions:");
		
		JToggleButton tglbtnCreateNet = new JToggleButton("Create Net");
		
		JToggleButton tglbtnTrainNet = new JToggleButton("Train Net");
		
		JToggleButton tglbtnSetTrainingData = new JToggleButton("Set training data");
		GroupLayout gl_Action_Panel = new GroupLayout(Action_Panel);
		gl_Action_Panel.setHorizontalGroup(
			gl_Action_Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Action_Panel.createSequentialGroup()
					.addGroup(gl_Action_Panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_Action_Panel.createSequentialGroup()
							.addGap(13)
							.addComponent(lblActions))
						.addGroup(gl_Action_Panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnCreateNet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_Action_Panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnSetTrainingData, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_Action_Panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(tglbtnTrainNet, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		gl_Action_Panel.setVerticalGroup(
			gl_Action_Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Action_Panel.createSequentialGroup()
					.addGap(9)
					.addComponent(lblActions)
					.addGap(14)
					.addComponent(tglbtnCreateNet)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnSetTrainingData)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tglbtnTrainNet)
					.addGap(34))
		);
		Action_Panel.setLayout(gl_Action_Panel);
		
		JLayeredPane SubMenu = new JLayeredPane();
		layeredPane.setLayer(SubMenu, 0);
		SubMenu.setForeground(new Color(0, 0, 128));
		SubMenu.setBackground(new Color(0, 128, 0));
		GroupLayout gl_layeredPane = new GroupLayout(layeredPane);
		gl_layeredPane.setHorizontalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addGap(3)
							.addComponent(Action_Panel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Preferences, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(SubMenu))
						.addGroup(gl_layeredPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblMainView)))
					.addContainerGap())
		);
		gl_layeredPane.setVerticalGroup(
			gl_layeredPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_layeredPane.createSequentialGroup()
					.addComponent(lblMainView)
					.addGap(7)
					.addGroup(gl_layeredPane.createParallelGroup(Alignment.LEADING)
						.addComponent(Action_Panel, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
						.addComponent(SubMenu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Preferences, GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE))
					.addGap(11))
		);
		
		JPanel Default = new JPanel();
		SubMenu.setLayer(Default, 1);
		Default.setBackground(new Color(255, 255, 255));
		GroupLayout gl_Default = new GroupLayout(Default);
		gl_Default.setHorizontalGroup(
			gl_Default.createParallelGroup(Alignment.LEADING)
				.addGap(0, 339, Short.MAX_VALUE)
		);
		gl_Default.setVerticalGroup(
			gl_Default.createParallelGroup(Alignment.LEADING)
				.addGap(0, 330, Short.MAX_VALUE)
		);
		Default.setLayout(gl_Default);
		
		JPanel NetView = new JPanel();
		SubMenu.setLayer(NetView, 0);
		NetView.setBackground(new Color(255, 255, 255));
		
		JLabel lblConfigureNetParameters = new JLabel("Configure Net Parameters:");
		lblConfigureNetParameters.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Starting bias:", null, "For Random Values: 2"},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Parameter", "Value", "Notes"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Double.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setMaxWidth(40);
		table.setBackground(new Color(192, 192, 192));
		
		JLabel lblLayerSetUp = new JLabel("Layer Set Up:");
		lblLayerSetUp.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JSpinner spinner = new JSpinner();
		
		JButton btnAddLayerWith = new JButton("Add Layer with Neuron Count:");
		GroupLayout gl_NetView = new GroupLayout(NetView);
		gl_NetView.setHorizontalGroup(
			gl_NetView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NetView.createSequentialGroup()
					.addGroup(gl_NetView.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_NetView.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_NetView.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
								.addComponent(lblConfigureNetParameters)
								.addGroup(gl_NetView.createSequentialGroup()
									.addComponent(btnAddLayerWith)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_NetView.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLayerSetUp, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_NetView.setVerticalGroup(
			gl_NetView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_NetView.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblConfigureNetParameters)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblLayerSetUp, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_NetView.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddLayerWith)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(182, Short.MAX_VALUE))
		);
		NetView.setLayout(gl_NetView);
		SubMenu.setLayout(new CardLayout(0, 0));
		SubMenu.add(Default, "name_47226534290044");
		SubMenu.add(NetView, "name_47226543138343");
		
		JPanel DataView = new JPanel();
		SubMenu.add(DataView, "name_47242389343479");
		
		JLabel lblSetUpTraining = new JLabel("Set up training data:");
		lblSetUpTraining.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout gl_DataView = new GroupLayout(DataView);
		gl_DataView.setHorizontalGroup(
			gl_DataView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DataView.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSetUpTraining)
					.addContainerGap(283, Short.MAX_VALUE))
		);
		gl_DataView.setVerticalGroup(
			gl_DataView.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DataView.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblSetUpTraining)
					.addContainerGap(305, Short.MAX_VALUE))
		);
		DataView.setLayout(gl_DataView);
		layeredPane.setLayout(gl_layeredPane);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
	}
}
