package pruebapracticaserveralvaro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PruebaPracticaServerAlvaro {

    public static void main(String[] args) {
        // Puerto del servidor
        int port = 1234;
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor para calculo de direcciones IPv4 iniciado en el puerto " + port + ".");
            
            // Aceptar una conexion
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());
            while (true) {
                
                // Leer y responder al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                
                String message = input.readLine();
                
                String ip1 = message.split("&")[0];
                String ip2 = message.split("&")[1];
                
                
                System.out.println("Se ha recibido como primera direccion IPv4 la " + ip1);
                System.out.println("Se ha recibido como segunda direccion IPv4 la " + ip2);
                
                Ipv4Address dir1 = new Ipv4Address(ip1.split("/")[0], ip1.split("/")[1]);
                String direccionRed1 = dir1.getDecimalNetwork();
                
                Ipv4Address dir2 = new Ipv4Address(ip2.split("/")[0], ip2.split("/")[1]);
                String direccionRed2 = dir2.getDecimalNetwork();
                
                if ("0.0.0.0/0".equals(ip1)) {
                    String answer = "Fin de la conexión";
                    output.println(answer);
                    System.out.println(answer);
                    System.out.println("------------------------------------------------------");
                    break;
                } if (direccionRed1.equals(direccionRed2)) {
                    String answer = "Las dos direcciones pertenecen a la misma red";
                    output.println(answer);
                    System.out.println(answer);
                    System.out.println("------------------------------------------------------");
                } else {
                    String answer = "Las dos direcciones NO pertenecen a la misma red";
                    output.println(answer);
                    System.out.println(answer);
                    System.out.println("------------------------------------------------------");
                }
            }
            client.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
