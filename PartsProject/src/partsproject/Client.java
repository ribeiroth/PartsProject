package partsproject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Client implements ClientInterface {
    
 public static void main(String[] args)  {
          System.out.printf("começou Cliente");
      Client cliente  = new Client("alynne",4200);
    }
    
    
    public Client(String serverName, int serverPort) {
        try {
              System.out.printf("Entrou");
            String nomeServico = serverName;
            int porta = serverPort;

           // ClientInterface client = new Client(serverName, serverPort);
           // ClientInterface clientDistribuido = (ClientInterface) UnicastRemoteObject.exportObject(client, 0);

            Registry registry = LocateRegistry.getRegistry(porta);
            PartRepositoryInterface servicoRemoto = (PartRepositoryInterface) registry.lookup(nomeServico);
            Part part = new Part("teste1","testeteste",1,true);
          //  servicoRemoto.addPart(part); // precisa implementar isso
            
          //Part teste = servicoRemoto.getPart(1);
           //System.out.printf(teste.getNome());
             
            //servicoRemoto.addListener(clientDistribuido);

            System.out.println("Cliente Online");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bind(String repositoryName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Part[] listp() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Part getp(int id) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void showp() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clearlist() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addsubpart(int n) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addp(String repositoryName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quit(String repositoryName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
