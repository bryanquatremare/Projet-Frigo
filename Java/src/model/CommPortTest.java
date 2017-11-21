package model;

import gnu.io.*;
import java.util.Enumeration;

public class CommPortTest {

	public CommPortIdentifier serialPort;

	@SuppressWarnings("rawtypes")
	public Enumeration enumComm;

	public CommPortTest() {
		System.out.println("Démarrage de la pénétration vers l'Arduino");

		this.enumComm = CommPortIdentifier.getPortIdentifiers();

		while (enumComm.hasMoreElements()) {

			this.serialPort = (CommPortIdentifier) this.enumComm.nextElement();

			if (this.serialPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("L'Arduino est sur le port " + this.serialPort.getName());
			}
		}

		System.out.println("Fin de la réucpération des données");
	}
}
