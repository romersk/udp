package com.example.client;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

    DatagramSocket socket;
    DatagramPacket datagramPacket;
    InetAddress loc;

    public Client(String ip, int port) {
        try {
            this.socket = new DatagramSocket(port);
            this.datagramPacket = null;
            this.loc = InetAddress.getByName("localhost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeLine(String message) throws IOException {
        byte[] buffer = message.getBytes();
        datagramPacket = new DatagramPacket(buffer, buffer.length, loc, 12345);
        socket.send(datagramPacket);
    }

    public String readLine() throws IOException {
        byte[] buffer = new byte[100];
        datagramPacket = new DatagramPacket(buffer, 100);
        socket.receive(datagramPacket);
        return new String(datagramPacket.getData());
    }

}
