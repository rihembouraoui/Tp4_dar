package server;



import java.io.*;
import java.net.*;
import java.util.*;

public class Server_Ac3 {
	
	
	    private static List<InetSocketAddress> clients = new ArrayList<>();
	    private static DatagramSocket s;

	    public static void main(String[] args) {
	        try {
	            s = new DatagramSocket(1234);
	            System.out.println("Serveur de chat en UDP démarré sur le port 1234...");

	            while (true) {
	                byte[] receiveData = new byte[1024];
	                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	                s.receive(receivePacket);

	                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
	                InetSocketAddress clientAddress = new InetSocketAddress(receivePacket.getAddress(), receivePacket.getPort());

	                if (!clients.contains(clientAddress)) {
	                    // Nouveau client, ajoutez-le à la liste des clients
	                    clients.add(clientAddress);
	                    System.out.println(clientAddress + " a rejoint le chat.");
	                }

	                broadcast(message, clientAddress);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void broadcast(String message, InetSocketAddress sender) {
	        System.out.println(message);

	        for (InetSocketAddress client : clients) {
	            if (!client.equals(sender)) {
	                try {
	                    byte[] sendData = message.getBytes();
	                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getAddress(), client.getPort());
	                    s.send(sendPacket);
	                    
	                    System.out.println("Message : "+message+"\n"
	                    		+ "client.getAddress() : "+client.getAddress()+"\n"
	                    		+"client.getPort() : "+client.getPort());
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}


	