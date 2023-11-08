package client;


import java.net.*;
import java.io.*;

public class Client_ac1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		        DatagramSocket socket = null;
		        
		        try {
		            socket = new DatagramSocket();
		            InetAddress serverAddress = InetAddress.getByName("127.0.0.1");  // Adresse IP du serveur (peut être modifiée)
		            int serverPort = 1234;
		            
		            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		            
		            System.out.print("Entrez votre prénom : ");
		            String prenom = reader.readLine();
		            System.out.print("Entrez votre nom : ");
		            String nom = reader.readLine();
		            
		            String message = prenom + " " + nom;
		            byte[] sendData = message.getBytes();
		            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
		            socket.send(sendPacket);
		            
		            byte[] receiveData = new byte[1024];
		            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		            socket.receive(receivePacket);
		            
		            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
		            InetAddress serverResponseAddress = receivePacket.getAddress();
		            int serverResponsePort = receivePacket.getPort();
		            
		            System.out.println("Message reçu du serveur : " + response);
		            System.out.println("Adresse du serveur : http:"+ serverResponseAddress);
		            System.out.println("Port du serveur : " + serverResponsePort);
		        } catch (IOException e) {
		            e.printStackTrace();
		        } finally {
		            if (socket != null && !socket.isClosed()) {
		                socket.close();
		            }
		        }
		    }
		}