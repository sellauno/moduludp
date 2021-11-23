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
public class ClientDatagram {

    public static DatagramSocket d;
    public static byte buffer[] = new byte[1024];
    public static int clientport = 800, serverport = 900;

    public static void main(String args[]) throws Exception {
        d = new DatagramSocket(clientport);
        System.out.println("Client sedang menunggu server mengirimkan data ");
        System.out.println("tekan Ctrl + C untuk mengakhiri ");
        while (true) {
            DatagramPacket p = new DatagramPacket(buffer,
                    buffer.length);
            d.receive(p);
            String ps = new String(p.getData(), 0, p.getLength());
            System.out.println("From Server: " + ps);
        }
    }
}
