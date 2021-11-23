/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduludp;

import java.net.*;
import java.io.*;

/**
 *
 * @author Sella
 */
public class JavaSenderUDP {

    public static DatagramSocket sock;
    public static byte buffer[] = new byte[1024];
//    public static int clientport = 800, serverport = 900;
    public static int port = 7777;

    public static void main(String[] args) {
        try {
            sock = new DatagramSocket();
            InetAddress host = InetAddress.getByName("localhost");
            while (true) {
//take input and send the packet
                String s;
                BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Enter message to send : ");
                s = (String) cin.readLine();
                byte[] b = s.getBytes();
                DatagramPacket dp = new DatagramPacket(b, b.length, host, port);
                sock.send(dp);//now receive reply
//buffer to receive incoming data
                byte[] buffer = new byte[65536];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                sock.receive(reply);
                byte[] data = reply.getData();
                s = new String(data, 0, reply.getLength());
//echo the details of incoming data - client ip : client port -    client message
                System.out.println(reply.getAddress().getHostAddress() + " : " + reply.getPort() + " - " + s);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
