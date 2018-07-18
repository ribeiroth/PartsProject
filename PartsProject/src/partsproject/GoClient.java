/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partsproject;

import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author thasr
 */
public class GoClient {
    
    public static void main(String[] args) throws RemoteException {
        
        Scanner ler = new Scanner(System.in);
        String serverName;
        int port, option;
        
        System.out.println("Olá, vamos começar com a nossa conexão!");
        
        System.out.println("Primeiro digite o nome do servidor que deseja se conectar");
        serverName = ler.next();
        
        System.out.println("Boa! Agora digite a porta do servidor");
        port = ler.nextInt();
        
        Client cliente = new Client(serverName, port);
        
        cliente.menu();
          
    }
    
}
