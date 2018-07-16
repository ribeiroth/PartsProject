package partsproject;
import java.rmi.registry.*;

public class PartsRepositoryServer {

  public String ServerName;
  public int ServerPort;

  //Cria um server novo;
  public PartsRepositoryServer(String name, int port){
    try {
        ServerName = name;
        ServerPort = port;

        PartRepositoryInterface newRepo = new PartRepository();
        PartRepositoryInterface repoDist = (PartRepositoryInterface) UnicastRemoteObject.exportObject(newRepo, 0);

        Registry registry = LocateRegistry.createRegistry(porta);
        registry.bind(newRepo, repoDist);
        System.out.printf("Server disponivel: %s%n", newRepo);
    }catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
      }
  }
}