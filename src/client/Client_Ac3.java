package client;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client_Ac3 {

	public static void main(String[] args) {
		DatagramSocket s = null;
		try {
			s = new DatagramSocket();
			  InetAddress serverAddress = InetAddress.getByName("127.0.0.1");  // Adresse IP du serveur (peut être modifiée)
	            int serverPort = 1234;
			
	            Scanner scanner = new Scanner(System.in);
	            System.out.println("Entrez votre nom d'utilisateur:");
	            String username = scanner.nextLine();

	            while (true) {
	                System.out.print("Moi: ");
	                String message = scanner.nextLine();

	                String formattedMessage = username + ": " + message;
	                byte[] sendData = formattedMessage.getBytes();

	                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
	                s.send(sendPacket);
	                
	                byte[] receiveData = new byte[1024];
		            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		            s.receive(receivePacket);
		            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
		            System.out.println(response);
		            
	            }
	           
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}