package partsproject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Client implements ClientInterface {
    
    public PartRepositoryInterface servicoRemoto;
    public Part pecaAtual;
    
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
            this.servicoRemoto = (PartRepositoryInterface) registry.lookup(nomeServico);
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
        return this.servicoRemoto.getList();
    }

    @Override
    public Part getp(int id) throws RemoteException {
        this.pecaAtual = this.servicoRemoto.getPart(id); 
        return this.pecaAtual;
    }

    @Override
    public void showp() throws RemoteException {
        if(this.pecaAtual != null){
            System.out.println("Id: "+this.pecaAtual.getId());
            System.out.println("Nome: "+this.pecaAtual.getNome());
            System.out.println("Descrição: "+this.pecaAtual.getDescricao());
            Vector<Part> componentes = this.pecaAtual.getComponentes();
            System.out.println("Subcomponentes: ");
            for(Part p : componentes){
                System.out.println("Id: "+p.id+", Nome da Peça: "+p.getNome());
            }
        }
        else{
            System.out.println("Selecione uma peça primeiro!");
        }
    }

    @Override
    public void clearlist() throws RemoteException {
        this.servicoRemoto.ClearRepository();
    }
    
    @Override
    public void addsubpart(int n) throws RemoteException {
        if(this.pecaAtual != null){
            Part novaPeca = this.pecaAtual;
            novaPeca.quantidade = n;
            this.servicoRemoto.addPart(novaPeca);
        }
        else{
            System.out.println("Selecione uma peça primeiro!");
        }
    }

    @Override
    public void addp(Part p) throws RemoteException {
        p.componentes = this.pecaAtual.componentes;
        this.servicoRemoto.addPart(p); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quit(String repositoryName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
