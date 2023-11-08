package server;



import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class Server_ac2 {

	public static void main(String[] args) throws Exception{
	

		DatagramSocket s = new DatagramSocket(1234);
		
		byte[] receiveData =new byte[1024];
		
		System.out.println("Le server attend des connexions ... ");
		
		while(true) {
			DatagramPacket recievePacket = new DatagramPacket(receiveData,receiveData.length);
			s.receive(recievePacket);
			String message = new String(recievePacket.getData(),0,recievePacket.getLength());
			 InetAddress clientAddress = recievePacket.getAddress();
             int clientPort = recievePacket.getPort();
			
			System.out.println("Message recu du client : "+message);
			Date d = new Date();
			String reponse = " La Date est : "+d;
			
			System.out.println("La reponse qui doit etre envoie au client : "+reponse);
			byte[] sendData = reponse.getBytes();
		  DatagramPacket send = new DatagramPacket(sendData,sendData.length, clientAddress, clientPort);
		  s.send(send);
		}
	}

}