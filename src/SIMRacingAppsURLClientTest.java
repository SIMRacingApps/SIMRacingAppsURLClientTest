import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * This is a test program to see if the server is broadcasting its URL.
 * If it is, it prints it out each time it receives it.
 * By default the server is broadcasting on port 28888.
 * 
 * All client must be listening on the same subnet for this to work.
 * 
 * @author Jeffrey Gilliam
 * @copyright Copyright (C) 2015 Jeffrey Gilliam
 * @since 1.2
 * @license Apache License 2.0
 */
public class SIMRacingAppsURLClientTest {

    public SIMRacingAppsURLClientTest() {
    }

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            //open a socket on the port SRA is broadcasting
            socket = new DatagramSocket(28888, InetAddress.getByName("0.0.0.0"));
            byte[] recvBuf = new byte[256];
            DatagramPacket receivePacket = new DatagramPacket(recvBuf,recvBuf.length);
            
            while (true) {
                //listen for the packet. SRA is broadcast every 2 seconds
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData()).trim();
                System.out.println(message);
                //make sure the message is from SRA
                if (message.startsWith("SRA=")) {
                    String URL = message.substring(4);
                    System.out.println("URL = "+URL);
                    
                    //...take the URL and do something with it.
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (socket != null)
                socket.close();
        }
    }
}
