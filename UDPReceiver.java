//package networks;

/*
 *
 * @author      Saurabh Shukla
 *
 * Version:  1.0
 *     
 *     
 * UDPReceiver.java
 
 This class performs the initiation of UDP server that will receive RIP packet
 over network
 
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;

class UDPReceiver extends Thread {

	int portNumber;
	int roverID;
	String finalUserIP[];
	int maskingValue;

	public UDPReceiver(String[] finalUserIP, int portNumber, int roverID, int maskingValue) throws IOException {

		this.finalUserIP = finalUserIP;
		this.portNumber = portNumber;
		this.roverID = roverID;
		this.maskingValue = maskingValue;
	}

	public void run() {
		//System.out.println("hi server");
		DatagramSocket serversocket = null;
		try {
			serversocket = new DatagramSocket(520);
			// System.out.println(serversocket.getPort());

		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			try {
				byte[] serverData = new byte[504];
				DatagramPacket dpwdsocket = new DatagramPacket(serverData, serverData.length);
				// System.out.println("hello server 3");
				serversocket.receive(dpwdsocket);
				// System.out.println(" Received Data");
				byte[] clientDatapwd = (dpwdsocket.getData());
				//byte[] clientDatapwd = new byte[] {1,2,0,0,0,2,0,2,10,1,2,0,-1,-1,-1,0,10,1,2,0,0,0,0,0,0,2,0,3,10,1,3,0,-1,-1,-1,0,10,1,3,0,0,0,0,16};
				
				
				DistanceVector dv = new DistanceVector();
				
				dv.calculateDistance(clientDatapwd, maskingValue);
				
				ArrayList ipData = new ArrayList();
				ArrayList nextHOP = new ArrayList();
				ArrayList Matrics = new ArrayList();
				ArrayList IPData = new ArrayList();	

				ipData = dv.showIP();	
				IPData=dv.timerOffline(ipData);
				
				nextHOP = dv.showHOP();
				Matrics = dv.showCost();

				RouterTableGeneration rr = new RouterTableGeneration();
				rr.createRouterTableSecond(IPData, nextHOP, Matrics);

				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
