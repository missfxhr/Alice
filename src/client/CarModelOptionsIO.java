package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class CarModelOptionsIO {
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private Socket socket;

	public void startConnection(int port) {
		try {
			InetAddress host = InetAddress.getLocalHost();
			socket = new Socket(host.getHostName(), port);
	        //write to socket using ObjectOutputStream
	        oos = new ObjectOutputStream(socket.getOutputStream());
	        //read the server response message
	        ois = new ObjectInputStream(socket.getInputStream());
	        String message = (String) ois.readObject();
	        System.out.println("Message: " + message);
	        //close resources
	        ois.close();
	        oos.close();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
