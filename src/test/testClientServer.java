package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import client.DefaultSocketClient;

class testClientServer {

	@Test
	void test() {
		InetAddress host;
		InputStream stdin = null;
		String data = "2";
		stdin = System.in;
		System.setIn(new ByteArrayInputStream(data.getBytes()));

		try {
			// start client
			host = InetAddress.getLocalHost();
			DefaultSocketClient client = new DefaultSocketClient(host.getHostName(), 6666);
			client.start();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Unknown host exception caught.");
			System.exit(1);
		} finally {
			System.setIn(stdin);
		}
		
	}

}
