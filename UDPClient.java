//package networks;


/*
 *
 * @author      Saurabh Shukla
 *
 * Version:  1.0
 *     
 *     
 * UDPClient.java
 
 This class performs the initiation of UDP client that will send RIP packet
 over network
 
 */



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



class UDPClient extends Thread {

	int portNumber;
	int roverID;
	String finalUserIP[];
	int maskingValue;

	public UDPClient(String[] finalUserIP, int portNumber, int roverID, int maskingValue) throws IOException {

		this.finalUserIP = finalUserIP;
		this.portNumber = portNumber;
		this.roverID = roverID;
		this.maskingValue = maskingValue;

	}

	public void run() {
		//System.out.println("hi client");
		while (true) {
			try {
				RIPencode rip = new RIPencode();
				byte dataUDP[] = rip.encodingRIP(finalUserIP, finalUserIP.length, maskingValue, roverID);
				
				

		for (int i = 1; i < 255; i++) {
					
					byte[] destinationAddress = new byte[4];
					destinationAddress[0] = (byte) Integer.parseInt(finalUserIP[0]);
					destinationAddress[1] = (byte) Integer.parseInt(finalUserIP[1]);
					destinationAddress[2] = (byte) i;
					destinationAddress[3] = (byte) Integer.parseInt(finalUserIP[3]);

					 InetAddress iplocalhost = InetAddress.getByAddress(destinationAddress);
					//InetAddress iplocalhost = InetAddress.getLocalHost();
					DatagramPacket RoverData = new DatagramPacket(dataUDP, dataUDP.length, iplocalhost, 520);
					DatagramSocket ds = new DatagramSocket();
					ds.send(RoverData);

				}
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}

