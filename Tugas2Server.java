/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduludp;
import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
/**
 *
 * @author Sella
 */
public class Tugas2Server {
    public static DatagramSocket ds;
    public static int clientport = 800, serverport = 900;

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        byte buffer[] = new byte[1024];
        ds = new DatagramSocket(serverport);
        BufferedReader dis = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Server menunggu input");
        InetAddress i = InetAddress.getByName("192.168.1.10");
        while (true) {
            System.out.print("1. Text \n2. File \nPilih jenis inputan (1/2) : ");
            String a = sc.next();
            if (a.equalsIgnoreCase("1")) {
                System.out.print("Inputan Server: ");
                String str = dis.readLine();
                if ((str == null || str.equals("end"))) {
                    break;
                }
                buffer = str.getBytes();
                ds.send(new DatagramPacket(buffer, str.length(), i,
                        clientport));
            } else if (a.equalsIgnoreCase("2")) {
                String str = "fileaccept";
                buffer = str.getBytes();
                ds.send(new DatagramPacket(buffer, str.length(), i,
                        clientport));
                JFrame frame = new JFrame();
                frame.toFront();
                frame.setVisible(true);
                JFileChooser fc = new JFileChooser();
                if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(frame.getContentPane())) {
                    FileInputStream f = new FileInputStream(fc.getSelectedFile());
                    
                    int x = 0;
                    while (f.available() != 0) {
                        buffer[x] = (byte) f.read();
                        x++;
                    }
                    f.close();
                    ds.send(new DatagramPacket(buffer, x, i,
                            clientport));
                }
            }
        }
    }
}
