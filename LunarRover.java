//package networks;

/*
 *
 * @author      Saurabh Shukla
 *
 * Version:  1.0
 *     
 *     
 * LunarRover.java
 
 This class performs the initiation of Lunar rover taking 3 command line arguments.
 Those will be useful for starting client and server threads for Rover communication.
 
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LunarRover {

	String ipAddress;
	int portNumber;
	int roverID;
	String ipSplit[];
	String dividerSplit[];
	String finalUserIP[] = new String[4];
	int maskingValue;

	LunarRover(String ip, int port, int rover) {
		this.ipSplit = ip.split("\\.");
		this.dividerSplit = ipSplit[3].split("\\/");
		this.finalUserIP[0] = ipSplit[0];
		this.finalUserIP[1] = ipSplit[1];
		this.finalUserIP[2] = ipSplit[2];
		this.finalUserIP[3] = ipSplit[3];
		this.maskingValue = 24;
		this.portNumber = port;
		this.roverID = rover;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		if(args.length==3) {
		System.out.println(" Lunar Rover id : " + args[2] + " is running on port " + args[1] + "  ....");
		LunarRover rover = new LunarRover(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]));
		Thread sender = new Thread(
				new UDPClient(rover.finalUserIP, rover.portNumber, rover.roverID, rover.maskingValue));
		sender.setName("sender");
		sender.start();

		Thread receiver = new Thread(
				new UDPReceiver(rover.finalUserIP, rover.portNumber, rover.roverID, rover.maskingValue));
		receiver.setName("receiver");
		receiver.start();
		Thread.sleep(1000);

	}
		
		else {
			System.out.println("Please enter command line argument in below format !");
			System.out.println(" [i/p address] [portnumber] [roverid]");
			
		}
	}
	

}
