package partsproject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class Client implements ClientInterface {

    public Client(String serverName, int serverPort) {
        try {
            String nomeServico = serverName;
            int porta = serverPort;

            ClientInterface client = new Client(serverName, serverPort);
            ClientInterface clientDistribuido = (ClientInterface) UnicastRemoteObject.exportObject(client, 0);

            Registry registry = LocateRegistry.getRegistry(porta);
            PartRepositoryInterface servicoRemoto = (PartRepositoryInterface) registry.lookup(nomeServico);
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
