/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduludp;
import java.net.*;
import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Sella
 */
public class Tugas2Client {
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
            if (ps.equalsIgnoreCase("fileaccept")) {
                d.receive(p);
                ps = new String(p.getData(), 0, p.getLength());
                JFrame frame = new JFrame();
                frame.toFront();
                frame.setVisible(true);
                JFileChooser fc = new JFileChooser();
                if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(frame.getContentPane())) {
                    BufferedOutputStream writer = null;
                    writer = new BufferedOutputStream(new FileOutputStream(fc.getSelectedFile()));
                    writer.write(ps.getBytes());
                    writer.flush();
                    writer.close();
                }
                System.out.println("Server mengirimkan file");
            } else {
                System.out.println("From Server: " + ps);
            }
        }
    }
}
