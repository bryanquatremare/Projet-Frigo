package model;
import gnu.io.*;
import java.util.Enumeration;

public class CommPortTest {

	public static void main(String[] args)
	{
		System.out.println("D�marrage de la p�n�tration vers l'Arduino");
		
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
		
		System.out.println("Fin de la r�ucp�ration des donn�es");
	}
}
