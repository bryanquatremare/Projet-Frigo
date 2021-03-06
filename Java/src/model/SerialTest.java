package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import view.MaFrame;

import java.util.Enumeration;

public class SerialTest implements SerialPortEventListener {

	private String[] inputLines;
	private MaFrame frame;
	SerialPort serialPort; // port de connexion
	private static final String PORT_NAMES[] = { getPort() }; // nom du port

	private BufferedReader input;
	private OutputStream output;
	private Consigne consign;
	public String temperature;
	public String temperatureext;
	public String pointrosee;
	public String alerte;
	public String affichageconsigne;
	private static final int TIME_OUT = 2000;
	private static final int DATA_RATE = 9600;

	public void run(MaFrame frame) { // lance la frame
		this.frame = frame;
		SerialPortEvent serialevent = new SerialPortEvent(this.serialPort, 0, true, false);
		this.serialEvent(serialevent);
		System.out.println(this.temperatureext);
		frame.setInputArduino(this.temperature);
		frame.setInputArduinodeux(this.temperatureext);
		frame.setInputArduinotrois(this.pointrosee);
		frame.affichelaJFrame(this);
	}

	public static String getPort() {
		CommPortIdentifier serialPort;

		@SuppressWarnings("rawtypes")
		Enumeration enumComm;

		enumComm = CommPortIdentifier.getPortIdentifiers();

		while (enumComm.hasMoreElements()) {

			serialPort = (CommPortIdentifier) enumComm.nextElement();

			if (serialPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				return serialPort.getName().toString();
			}
		}
		return "Aucun port detecte, essayez de rebrancher votre arduino";

	}

	public void initialize() { // initialisation de la connexion
		CommPortIdentifier portId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}

		if (portId == null) {
			System.out.println("Erreur de port");
			return;
		}
		try {
			serialPort = (SerialPort) portId.open(this.getClass().getName(), TIME_OUT);
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			// open stream
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}

	public synchronized void close() throws IOException { // fermer la connexion
		if (serialPort != null) {
			serialPort.removeEventListener();
			serialPort.close();
			this.input.close();
		}
	}

	public synchronized void serialEvent(SerialPortEvent oEvent) { // ajouter un
																	// eventlistener
																	// sur le
																	// serialport
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				if (input.ready()) {
					this.inputLines = new String[5];
					for (int i = 0; i < 5; i++) {
						this.setInputLine(input.readLine(), i);
						if (i == 0) {
							this.temperature = this.inputLines[i];
						} else if (i == 1) {
							this.temperatureext = this.inputLines[i];
						} else if (i == 2) {
							this.pointrosee = this.inputLines[i];
						} else if (i == 3) {
							this.alerte = this.inputLines[i];
						} else {
							this.affichageconsigne = this.inputLines[i];
						}
					}
					System.out.println(this.temperature + "\n" + this.temperatureext + "\n" + this.pointrosee + "\n"
							+ this.alerte + "\n" + this.affichageconsigne + "\n");
					this.frame.setTextLabel(this.temperature);
					this.frame.setTextLabeldeux(this.temperatureext);
					this.frame.setTextLabelTrois(this.pointrosee);
					this.frame.setInputArduinoquatre(Boolean.parseBoolean(this.alerte));
					this.frame.i += 1;
					System.out.println(this.temperature.substring(23, 25));
					System.out.println(this.frame.i);
					this.frame.inttemps[this.frame.i] = Integer.parseInt(this.temperature.substring(23, 25));
					this.frame.exttemps[this.frame.i] = Integer.parseInt(this.temperatureext.substring(23, 25));
					this.frame.updateCanva(this.frame.i, this.frame.exttemps, this.frame.inttemps);
					System.out.println(this.frame.inttemps[this.frame.i]);
					this.deleteValuesInputLines();
					this.writeData(this.consign.getConsign());
				}

			} catch (Exception e) {
			}
		}
	}

	public void setInputLine(String inputLine, int i) { // changer la ligne
														// d'input
		this.inputLines[i] = inputLine;
	}

	public void deleteValuesInputLines() {
		this.inputLines = new String[5];
	}

	public void writeData(int consigne) throws IOException {

		System.out.print("consigne = " + consigne + " ; ");
		String output1 = Integer.toString(consigne);
		int a = output1.length();
		char[] output2 = new char[a];

		for (int i = 0; i < a; i++) {
			output2[i] = output1.charAt(i);
			int ascii = (int) output2[i];

			output.write(ascii);
			System.out.print("byte = " + ascii + "\n");
			output.close();
		}
	}
}
