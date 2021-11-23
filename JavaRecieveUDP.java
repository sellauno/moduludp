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
public class JavaRecieveUDP {
    public static DatagramSocket sock;
    public static byte buffer[] = new byte[1024];
    public static int port = 900;
    
    public static void main(String[] args) {
        try{
            sock = new DatagramSocket(7777);
            
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);
            System.out.println("Server socket created. Waiting for incoming data...");
            
            while(true){
                sock.receive(incoming);
                byte[] data = incoming.getData();
                String s = new String(data, 0, incoming.getLength());
                
                System.out.println(incoming.getAddress().getHostAddress() + " : " + incoming.getPort() + " - "+s);
                
                s = "OK : " + s;
                DatagramPacket dp = new DatagramPacket(s.getBytes(), s.getBytes().length, incoming.getAddress(), incoming.getPort());
                sock.send(dp);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
