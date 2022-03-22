// Write a program on datagram socket for client/server to display the messages on client side, typed at the server side.

//Udpclient.Java 
import java.net.*; 
public class udpclient{ 
public static void main(String[] args) throws Exception { 
    DatagramSocket ds = new DatagramSocket(3000); 
    byte[] buf = new byte[1024]; 
    DatagramPacket dp = new DatagramPacket(buf, 1024); 
    ds.receive(dp); 
    String str = new String(dp.getData(), 0, dp.getLength()); 
    System.out.println(str); 
    ds.close(); 
    } 
}

//Udpserver.Java
import java.net.*; 
import java.util.Scanner; 
public class udpserver{ 
public static void main(String[] args) throws Exception { 
    DatagramSocket ds = new DatagramSocket(); 
    String str = ""; 
    Scanner sc=new Scanner(System.in); 
    System.out.println("\n Enter the message : "); 
    str=sc.nextLine(); 
    InetAddress ip = InetAddress.getByName("127.0.0.1"); 
 
    DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000); 
    ds.send(dp); 
    ds.close(); 
    } 
}