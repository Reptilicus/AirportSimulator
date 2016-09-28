import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/** UI class
*
* User interface to collect inputs, run simulation and report output.
* 
* Uses swing
* 
* @author 	Copyright (c) Sue Fitzgerald All rights reserved
* 			9/26/16
* 
* Note to students - you may use this example and adapt it for your programs in this class
 */

public class UI extends JFrame {

	//input panel
	private JPanel inputPane;	
	
	// REPLACE NAMES OF TEXT FIELDS WITH NAMES OF YOUR ITEMS
	private JTextField arrivalRateTextField;	
	private JTextField timeToLandTextField;
	private JTextField fuelRemainingTextField;
	private JTextField departureRateTextField;	
	private JTextField timeToTakeOffTextField;
	private JTextField simulationRuntimeTextField;
	private JTextField msgTextField;
	
	private JButton runSimulationButton;	
	private JButton clearButton;	

	// output panel
	private JPanel contentPane;  
	private JTextArea displayResults;			
	
/** UI - manages interaction with user
 * 
 * Collects inputs when button is pressed 
 * Invokes simulation
 * Displays a copy of the inputs in TextArea
 * Displays simulation results in TextArea	
 */
	public UI() {
		// REPLACE title to window WITH YOUR TITLE
		super("Sue's Simulation");
		
		// Initialize data structure WITH YOUR COLLECTION

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(112, 12, 102), 4, true));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Default font for the user interface
		Font defaultFont = new Font("Arial", Font.PLAIN, 12);
		
		// Input related fields for add/updating an item
		inputPane = new JPanel();		
		inputPane.setBorder(new LineBorder(new Color(112, 12, 102), 1, true));
		inputPane.setBounds(10, 32, 974, 220);
		contentPane.add(inputPane);
		inputPane.setLayout(null);
		
		// Labels for simulation inputs
		JLabel lblArrivalRate = new JLabel("Arrival Rate (0.0 - 1.0)");
		lblArrivalRate.setFont(defaultFont);
		lblArrivalRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArrivalRate.setBounds(16, 11, 140, 14);
		inputPane.add(lblArrivalRate);
				
		JLabel lblTimeToLand = new JLabel("Time to Land");
		lblTimeToLand.setFont(defaultFont);
		lblTimeToLand.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeToLand.setBounds(16, 36, 140, 14);
		inputPane.add(lblTimeToLand);
		
		JLabel lblFuelRemaining = new JLabel("Fuel Remaining");
		lblFuelRemaining.setFont(defaultFont);
		lblFuelRemaining.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFuelRemaining.setBounds(16, 61, 140, 14);
		inputPane.add(lblFuelRemaining);

		JLabel lblDepartureRate = new JLabel("Departure Rate");
		lblDepartureRate.setFont(defaultFont);
		lblDepartureRate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartureRate.setBounds(16, 86, 140, 14);
		inputPane.add(lblDepartureRate);

		JLabel lblTimeToTakeOff = new JLabel("Time to Takeoff");
		lblTimeToTakeOff.setFont(defaultFont);
		lblTimeToTakeOff.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeToTakeOff.setBounds(16, 111, 140, 14);
		inputPane.add(lblTimeToTakeOff);
		
		JLabel lblSimulationRuntime = new JLabel("Simulation Run Time");
		lblSimulationRuntime.setFont(defaultFont);
		lblSimulationRuntime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSimulationRuntime.setBounds(16, 136, 140, 14);
		inputPane.add(lblSimulationRuntime);
		
		JLabel errMsg = new JLabel("Messages");
		errMsg.setFont(defaultFont);
		errMsg.setHorizontalAlignment(SwingConstants.RIGHT);
		errMsg.setBounds(16, 180, 140, 14);
		inputPane.add(errMsg);
		
		arrivalRateTextField = new JTextField();
		arrivalRateTextField.setFont(defaultFont);
		arrivalRateTextField.setBounds(160, 8, 300, 20);
		inputPane.add(arrivalRateTextField);
		arrivalRateTextField.setColumns(25);		
		
		timeToLandTextField = new JTextField();
		timeToLandTextField.setFont(defaultFont);
		timeToLandTextField.setBounds(160, 33, 300, 20);
		inputPane.add(timeToLandTextField);
		timeToLandTextField.setColumns(15);
				
		fuelRemainingTextField = new JTextField();
		fuelRemainingTextField.setFont(defaultFont);
		fuelRemainingTextField.setBounds(160, 58, 300, 20);
		inputPane.add(fuelRemainingTextField);
		fuelRemainingTextField.setColumns(15);
			
		departureRateTextField = new JTextField();
		departureRateTextField.setFont(defaultFont);
		departureRateTextField.setBounds(160, 83, 300, 20);
		inputPane.add(departureRateTextField);
		departureRateTextField.setColumns(15);	
		
		timeToTakeOffTextField = new JTextField();
		timeToTakeOffTextField.setFont(defaultFont);
		timeToTakeOffTextField.setBounds(160, 108, 300, 20);
		inputPane.add(timeToTakeOffTextField);
		timeToTakeOffTextField.setColumns(15);		
		
		simulationRuntimeTextField = new JTextField();
		simulationRuntimeTextField.setFont(defaultFont);
		simulationRuntimeTextField.setBounds(160, 133, 300, 20);
		inputPane.add(simulationRuntimeTextField);
		simulationRuntimeTextField.setColumns(15);	
		
		// Text field for error messages
		msgTextField = new JTextField();
		msgTextField.setFont(defaultFont);
		msgTextField.setBounds(160, 180, 600, 20);
		msgTextField.setEditable(false);
		inputPane.add(msgTextField);
		msgTextField.setColumns(15);	
		
		runSimulationButton = new JButton("Run");
		// Event trigger for the Run Simulation button click
		runSimulationButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				runSimulation();
			}
		});
		
		runSimulationButton.setFont(defaultFont);
		runSimulationButton.setBounds(540, 11, 90, 28);
		inputPane.add(runSimulationButton);

		// Clear input fields
		clearButton = new JButton("Clear");
		// Event trigger for the Clear button click
		clearButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				clearFields();
			}
		});
		clearButton.setFont(defaultFont);
		clearButton.setBounds(540, 51, 90, 28);
		inputPane.add(clearButton);
		
		// output
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 250, 974, 200);
		contentPane.add(scrollPane);
		
		// output table
		displayResults = new JTextArea();
		displayResults.setFont(defaultFont);	
		scrollPane.setViewportView(displayResults);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
		
	/** runSimulation ()
	 * 
	 * Collects data from UI, instantiates a new LandingStrip simulation object, runs the simulation and displays the results
	 */
	public void runSimulation ()	{
		
		// Check for input errors
		double arrivalRate = 0.0;
		try	{
			arrivalRate = Double.parseDouble (arrivalRateTextField.getText());
		} catch (Exception e)	{
			msgTextField.setText("Enter digits in arrival rate field");
			return;
		} 
		
		if ((arrivalRate < 0.0) || (arrivalRate > 1.0))	{
			msgTextField.setText("Arrival rate must be between 0.0 and 1.0");
			return;
		}
		
		int timeToLand = 0;
		try	{
			timeToLand = Integer.parseInt (timeToLandTextField.getText());
		} catch (Exception e)	{
			msgTextField.setText("Enter digits in Time To Land field");
			return;
		} 
		
		if (timeToLand <= 0)	{
			msgTextField.setText("Time to land must be greater than 0");
			return;
		}
		
		int fuelRemaining = 0;
		try	{
			fuelRemaining = Integer.parseInt (fuelRemainingTextField.getText());
		} catch (Exception e)	{
			msgTextField.setText("Enter digits in fuelRemaining field");
			return;
		} 
		
		if (fuelRemaining < 0)	{
			msgTextField.setText("Fuel Remaining cannot be negative");
			return;
		}
		
		double departureRate = 0.0;
		try	{
			departureRate = Double.parseDouble (departureRateTextField.getText());
		} catch (Exception e)	{
			msgTextField.setText("Enter digits in departure rate field");
			return;
		} 
		
		if ((departureRate < 0.0) || (departureRate > 1.0))	{
			msgTextField.setText("Departure rate must be between 0.0 and 1.0");
			return;
		}
		
		int timeToTakeOff = 0;
		try	{
			timeToTakeOff = Integer.parseInt (timeToTakeOffTextField.getText());
		} catch (Exception e)	{
			msgTextField.setText("Enter digits in Time To Take Off field");
			return;
		} 
		
		if (timeToTakeOff <= 0)	{
			msgTextField.setText("Time to takeoff must be greater than 0");
			return;
		}
		
		int simulationRuntime = 0;
		try	{
			simulationRuntime = Integer.parseInt (simulationRuntimeTextField.getText());
		} catch (Exception e)	{
			msgTextField.setText("Enter digits in the Simulation Runtime field");
			return;
		} 
		
		if (simulationRuntime < 0)	{
			msgTextField.setText("Simulation Runtime must be greater than 0");
			return;
		}
		AirportSimulate airport = new AirportSimulate(arrivalRate, timeToLand, fuelRemaining, departureRate, timeToTakeOff, simulationRuntime);
		String s = airport.runSimulation();
		displayResults.setText(s);
		// ADD YOUR CODE HERE
		// RETRIEVAL DATA FROM TEXTFIELDS AND ERROR CHECK
		// CALL SIMULATION, RETURN AND DISPLAY RESULTS
		
		// String s = CarWash.carWashSimulate (arrivalRate, etc.)
		// displayResults.setText(s);
	}
	
	public void clearFields()	{
		
		// reset input fields to empty
		arrivalRateTextField.setText("");
		timeToLandTextField.setText("");
		fuelRemainingTextField.setText("");
		departureRateTextField.setText("");
		timeToTakeOffTextField.setText("");
		simulationRuntimeTextField.setText("");
		msgTextField.setText("");
	}
}
