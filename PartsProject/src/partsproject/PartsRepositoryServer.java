package partsproject;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class PartsRepositoryServer {

  public String ServerName;
  public int ServerPort;

  //Cria um server novo;
  //TO DO: MELHORAR MENSAGEM COM NOME DO SERVER
  public PartsRepositoryServer(String name, int port) throws RemoteException, AlreadyBoundException{
    try {
        
        System.out.println("Criando Server Repository...");
        ServerName = name;
        ServerPort = port;

        PartRepositoryInterface newRepo = new PartRepository(name, port);
        PartRepositoryInterface repoDist = (PartRepositoryInterface) UnicastRemoteObject.exportObject(newRepo, 0);

        Registry registry = LocateRegistry.createRegistry(ServerPort);
        registry.bind(ServerName, repoDist);
      
        
        System.out.println("Server "+ name +" disponivel na porta "+ port+"!" );
        
    }catch (RemoteException ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
      }
  }
}