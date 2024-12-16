package pruebapracticaclientalvaro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class PruebaPracticaClientAlvaro {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 1234;
        
        
        try {
            //Conectarse al servidor
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Enviarle un mensaje
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            String ip = "192.168.1.8/24&192.168.1.10/24";
            String ip2 = "172.16.1.13/16&172.16.15.13/16";
            String ip3 = "10.16.1.13/8&11.17.1.13/8";
            String ip4 = "0.0.0.0/0&11.17.1.13/8";
            
            String [] ips = {ip, ip2, ip3, ip4};
            String answer;
            
            for(int i = 0; i <= ips.length - 1; i += 1) {
                System.out.println("IP1: " + ips[i].split("&")[0] + " IP2: " + ips[i].split("&")[1]);
                output.println(ips[i]);
                answer = input.readLine();
                System.out.println("La respuesta del servidor es: " + answer); 
            }

            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
