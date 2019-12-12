
/**
 * Name: 		Patients Registration System
 * Author:  	Said Houdane
 * Created: 	12/11/2019
 * Course:  	CIS 152 Data Structure
 * Version: 	1.0
 * OS:			Windows 10
 * Copyright: 	This is my original work based on 
 * 			  	specifications issued by our instructor
 * Description: Patients Registration is an application used to register patients 
 * 				and add them into the queue, each patient has a priority based on their
 * 				medical condition. You can register, add, delete, sort and search patients.
 * Academic Honesty: I attest that this is my original work.
 * I have not used unauthorized source code, either modified or
 * unmodified. I have not given other fellow student(s) access to
 * my program.
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * The Class PatientRegisterationGUI is used to create graphics interface for
 * clinic registration
 */
@SuppressWarnings("serial")
public class patientRegistrationGUI extends JFrame {

	// queue counter to assign when adding to queue
	private static int queueCounter = 0;

	// data structures
	private LinkedList<patientInfo> patientsRegistered;
	private PriorityQueue<patientInfo> queue;

	// registration panel
	private RegistrationPanel registrationPanel;

	// patient panel
	private PatientQueuePanel patientQueuePanel;

	/**
	 * Instantiates a new patient registration GUI.
	 */
	public patientRegistrationGUI() {
		setTitle("Patient Registration System");

		// create lists and queue
		patientsRegistered = new LinkedList<>();
		queue = new PriorityQueue<>();

		patientsRegistered.add(new patientInfo("1001", "John Smith", 15, "Male", "California", "9876543210", 2));
		patientsRegistered.add(new patientInfo("1002", "Jane Doe", 25, "Female", "New York", "7896541230", 3));

		// create panels and add to frame

		registrationPanel = new RegistrationPanel();
		add(registrationPanel);

		patientQueuePanel = new PatientQueuePanel();
		add(patientQueuePanel, BorderLayout.SOUTH);

		pack(); // auto set size
		setVisible(true); // show
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	/**
	 * The Class RegistrationPanel is used to represent the panel that contains all
	 * the components and functionality of the registration process
	 */
	private class RegistrationPanel extends JPanel {

		// GUI components
		private JTextField txtId, txtName, txtAge, txtGender, txtAddress, txtPhone;
		private JComboBox<String> cbCondition;
		private JList<patientInfo> lstPatients;
		private DefaultListModel<patientInfo> dlmPatients;
		private JButton btnAdd, btnUpdate, btnClear, btnDelete, btnSort, btnAddToQueue, btnQuit;

		/**
		 * Instantiates a new registration panel.
		 */
		public RegistrationPanel() {
			setLayout(new BorderLayout(10, 10));
			setBorder(new EmptyBorder(10, 10, 10, 10));

			// add form panel
			add(pnlForm(), BorderLayout.CENTER);

			// patients list
			dlmPatients = new DefaultListModel<>();
			lstPatients = new JList<>(dlmPatients);
			JScrollPane sp = new JScrollPane(lstPatients);
			add(sp, BorderLayout.EAST);

			// buttons panel
			JPanel pnlButtons = new JPanel();

			btnAdd = new JButton("Register");
			pnlButtons.add(btnAdd);

			btnUpdate = new JButton("Update");
			pnlButtons.add(btnUpdate);

			btnClear = new JButton("Clear");
			pnlButtons.add(btnClear);

			btnDelete = new JButton("Delete");
			pnlButtons.add(btnDelete);

			btnSort = new JButton("Sort");
			pnlButtons.add(btnSort);

			btnAddToQueue = new JButton("Add to Queue");
			pnlButtons.add(btnAddToQueue);

			btnQuit = new JButton("Quit");
			pnlButtons.add(btnQuit);

			add(pnlButtons, BorderLayout.SOUTH);

			addActionListeners();
			updateList();
		}

		/**
		 * Create form with labels and text fields
		 *
		 * @return the j panel
		 */
		private JPanel pnlForm() {
			JPanel pnl = new JPanel(new GridLayout(0, 2, 10, 10));

			pnl.add(new JLabel("ID"));
			txtId = new JTextField(20);
			pnl.add(txtId);

			pnl.add(new JLabel("Name"));
			txtName = new JTextField(20);
			pnl.add(txtName);

			pnl.add(new JLabel("Age"));
			txtAge = new JTextField(20);
			pnl.add(txtAge);

			pnl.add(new JLabel("Gender"));
			txtGender = new JTextField(20);
			pnl.add(txtGender);

			pnl.add(new JLabel("Address"));
			txtAddress = new JTextField(20);
			pnl.add(txtAddress);

			pnl.add(new JLabel("Phone"));
			txtPhone = new JTextField(20);
			pnl.add(txtPhone);

			pnl.add(new JLabel("Condition"));
			cbCondition = new JComboBox<>(patientInfo.CONDITIONS);
			pnl.add(cbCondition);

			return pnl;
		}

		/**
		 * Adds the action listeners to buttons
		 */
		private void addActionListeners() {
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					patientsRegistered.add(new patientInfo(txtId.getText(), txtName.getText(),
							Integer.parseInt(txtAge.getText()), txtGender.getText(), txtAddress.getText(),
							txtPhone.getText(), cbCondition.getSelectedIndex()));
					updateList();
					clear();
				}
			});

			btnUpdate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					patientInfo p = lstPatients.getSelectedValue();
					if (p == null)
						return;

					p.setPatientID(txtId.getText());
					p.setName(txtName.getText());
					p.setAge(Integer.parseInt(txtAge.getText()));
					p.setGender(txtGender.getText());
					p.setAddress(txtAddress.getText());
					p.setPhoneNumber(txtPhone.getText());
					p.setConditionId(cbCondition.getSelectedIndex());
					clear();
				}
			});

			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();
				}
			});

			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					patientInfo p = lstPatients.getSelectedValue();
					if (p == null)
						return;

					patientsRegistered.remove(p);
					updateList();
					clear();
				}
			});

			btnSort.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Collections.sort(patientsRegistered, new Comparator<patientInfo>() {
						public int compare(patientInfo o1, patientInfo o2) {
							return o1.getName().compareToIgnoreCase(o2.getName());
						}
					});

					updateList();
				}
			});

			btnAddToQueue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					patientInfo p = lstPatients.getSelectedValue();
					if (p == null)
						return;

					p.setQueueNumber(queueCounter++);

					queue.add(p);
					clear();
					patientQueuePanel.updateList();
				}
			});

			lstPatients.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					patientInfo p = lstPatients.getSelectedValue();
					if (p == null)
						return;

					txtId.setText(p.getPatientID());
					txtName.setText(p.getName());
					txtAge.setText(p.getAge() + "");
					txtGender.setText(p.getGender());
					txtAddress.setText(p.getAddress());
					txtPhone.setText(p.getPhoneNumber());
					cbCondition.setSelectedIndex(p.getConditionId());
				}
			});

			btnQuit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});

		}

		/**
		 * Clear the input fields
		 */
		private void clear() {
			txtName.setText("");
			txtId.setText("");
			txtAge.setText("");
			txtGender.setText("");
			txtAddress.setText("");
			txtPhone.setText("");
			cbCondition.setSelectedIndex(0);
			lstPatients.clearSelection();
		}

		/**
		 * Update list.
		 */
		private void updateList() {
			dlmPatients.clear();
			for (patientInfo patient : patientsRegistered) {
				dlmPatients.addElement(patient);
			}
		}
	}

	/**
	 * The Class PatientQueuePanel is used to represent the button panel where
	 * priority queue is displayed
	 */
	class PatientQueuePanel extends JPanel {

		private JList<patientInfo> lstPatients;
		private DefaultListModel<patientInfo> dlmPatients;
		private JButton btnSeeNext;

		/**
		 * Instantiates a new patient queue panel.
		 */
		public PatientQueuePanel() {
			setLayout(new BorderLayout(10, 10));
			setBorder(new EmptyBorder(10, 10, 10, 10));

			// list for priority queue
			dlmPatients = new DefaultListModel<>();
			lstPatients = new JList<>(dlmPatients);
			JScrollPane sp = new JScrollPane(lstPatients);
			sp.setBorder(new TitledBorder("Patient Queue"));
			lstPatients.setEnabled(false);
			add(sp, BorderLayout.CENTER);

			// button to remove the first in queue
			JPanel pnlButtons = new JPanel();
			btnSeeNext = new JButton("See Next Patient");
			pnlButtons.add(btnSeeNext);
			add(pnlButtons, BorderLayout.SOUTH);

			// remove the first patient if present
			btnSeeNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (queue.isEmpty())
						return;

					queue.remove();
					updateList();
				}
			});

		}

		/**
		 * Update list.
		 */
		public void updateList() {
			dlmPatients.clear();
			for (patientInfo patient : queue) {
				dlmPatients.addElement(patient);
			}
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new patientRegistrationGUI();
	}

}
