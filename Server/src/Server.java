import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        new Server();
    }

    double sum1 = 0, sum2 = 0;
    DatagramSocket socket;
    byte[] buffer = new byte[100];
    DatagramPacket datagramPacket;

    Server() throws IOException, InterruptedException {
        System.out.println("Server started");
        socket = new DatagramSocket(12345);
        while (true) {
            datagramPacket = new DatagramPacket(buffer, 100);
            listen();
        }
    }

    private void listen() throws IOException, InterruptedException {
        int valueA, valueB, valueC;

        socket.receive(datagramPacket);
        String str = new String(datagramPacket.getData());
        String[] values = str.split(" ");

        System.out.println("number " + values[0] + " recieved as a");
        valueA = Integer.parseInt(values[0]);

        System.out.println("number " + values[1] + " recieved as b");
        valueB = Integer.parseInt(values[1]);

        System.out.println("number " + values[2] + " recieved as c");
        valueC = Integer.parseInt(values[2]);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                sum1 = 0;
                for (int i = valueA; i <= valueB; i++) {
                    sum1 += (i + 1) * (i + 1);
                }
            }
        }), t2 = new Thread(new Runnable() {
            public void run() {
                sum2 = 0;
                for (int i = valueB; i <= valueC; i++) {
                    if (i != 5) {
                        sum2 += 3 * i / (5 - i);
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        sendBack();
    }

    private void sendBack() throws IOException {
        String str = String.valueOf(sum1 + sum2);
        byte[] send = str.getBytes();
        datagramPacket = new DatagramPacket(send, send.length, InetAddress.getByName("localhost"), 12346);
        socket.send(datagramPacket);
    }
}
