/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partsproject;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Vector;

public class PartsProject {

    /**
     * @param args the command line arguments
     * 
     */    
    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        
       Scanner ler = new Scanner(System.in);
       int port;
       String name;
       int stop; 
        
       System.out.println("Olá, vamos começar criando um Servidor Repositório");
       
       System.out.println("Primeiro digite o nome que deseja dar ao repositório: ");
       name = ler.next();
       
       System.out.println("Agora digite a porta que deseja usar: ");
       port = ler.nextInt();
       
       PartsRepositoryServer partReposity = new PartsRepositoryServer(name, port);
       
       System.out.println("Deseja Criar um novo servidor? ");
       System.out.println("Digite 1 para continuar e criar, ou 0 para sair");
       stop = ler.nextInt();
       if(stop == 1){
           
           Vector<PartsRepositoryServer> Repositorios = new Vector<PartsRepositoryServer>();
           Repositorios.add(partReposity);
           
           while(stop != 0){
               
               System.out.println("Digite o nome que deseja dar ao repositório: ");
               name = ler.next();

               System.out.println("Digite a porta que deseja usar: ");
               port = ler.nextInt();
               
               Repositorios.add(new PartsRepositoryServer(name, port)); 
               
               System.out.println("Digite 1 para continuar e criar, ou 0 para sair");
               stop = ler.nextInt();
          }
       }
       
       
  
       
    }
    
}
