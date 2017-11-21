package model;
import gnu.io.*;
import java.util.Enumeration;

public class CommPortTest {

	public static void main(String[] args)
	{
		System.out.println("Démarrage de la pénétration vers l'Arduino");
		
		CommPortIdentifier serialPort;
		
		@SuppressWarnings("rawtypes")
		Enumeration enumComm;
		
		enumComm = CommPortIdentifier.getPortIdentifiers();
		
		while(enumComm.hasMoreElements())
		{
			
			serialPort=(CommPortIdentifier)enumComm.nextElement();
			
			if(serialPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
			{
				System.out.println("L'Arduino est sur le port " + serialPort.getName());
			}
		}
		
		System.out.println("Fin de la réucpération des données");
	}
}
