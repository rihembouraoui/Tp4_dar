package server;

import java.net.*;
import java.io.*;

public class Server_ac1 {
	
	
	public static void main(String[] args)throws Exception   {

	
		        DatagramSocket socket = null;
		        
		        try {
		            socket = new DatagramSocket(1234);
		            
		            byte[] receiveData = new byte[1024];
		            
		            System.out.println("Le serveur attend des connexions...");
		            
		            while (true) {
		                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		                socket.receive(receivePacket);
		                
		                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
		                InetAddress clientAddress = receivePacket.getAddress();
		                int clientPort = receivePacket.getPort();
		                
		                System.out.println("Message reçu du client: " + message);
		                System.out.println("Adresse du client: http:" + clientAddress);
		                System.out.println("Port du client: " + clientPort);
		                
		                String response = "Bienvenue " + message;
		                byte[] sendData = response.getBytes();
		                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
		                socket.send(sendPacket);
		            }
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (socket != null && !socket.isClosed()) {
		                socket.close();
		            }
		        }
		    }
		

		
	

}
 