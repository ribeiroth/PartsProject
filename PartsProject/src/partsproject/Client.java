package partsproject;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements ClientInterface {

    protected PartRepositoryInterface servicoRemoto;
    protected Part pecaAtual;
    protected String serverName;
    protected int serverPort;
    protected List listPort;

    public Client(String serverName, int serverPort) {
        try {
            this.serverName = serverName;
            this.serverPort = serverPort;
            List list = new ArrayList();
            list.add(serverPort);
            this.listPort = list;
            System.out.printf("Estabelecendo conexão...");

            Registry registry = LocateRegistry.getRegistry(serverPort);
            this.servicoRemoto = (PartRepositoryInterface) registry.lookup(serverName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getRepositoryName() {
        System.out.println(this.serverName);
    }

    public void setRepositoryName(String name) {
        this.serverName = name;
    }

    public void setRepositoryPort(int port) {
        this.serverPort = port;
    }

    public void setRemoteService(String nome, int port) throws RemoteException {
        this.listPort.add(port);
        Registry registry = LocateRegistry.getRegistry(port);
        System.out.println(Arrays.toString(registry.list()));
        try {
            this.servicoRemoto = (PartRepositoryInterface) registry.lookup(nome);
            System.out.println("foi\n");
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countParts() throws RemoteException {
        return this.servicoRemoto.size();
    }

    @Override
    public void bind(Client c, String repositoryName, int port) throws RemoteException, AccessException {
        System.out.println("Repositorio Atual\n");
        c.getRepositoryName();
        c.setRemoteService(repositoryName, port);
        c.setRepositoryName(repositoryName);
        System.out.println("Repositorio novo");
        c.getRepositoryName();
        c.setRepositoryPort(port);

    }

    public void searchPartById(int id) throws RemoteException, NotBoundException {
        boolean achou = false;
        for (int i = 0; i < this.listPort.size(); i++) {
            int port = (int) this.listPort.get(i);
            Registry registry = LocateRegistry.getRegistry(port);
            String[] list = registry.list();
            for (String host : list) {
                PartRepositoryInterface part = (PartRepositoryInterface) registry.lookup(host);
                if (part.getPart(id).nome != null) {
                    System.out.println("A peça está no Repositório: " + host);
                    achou = true;
                    break;
                }
            }
        }
        if(!achou){
            System.out.println("Peça não encontrada!");
        }
    }

    @Override
    public String getn() throws RemoteException {
        return this.servicoRemoto.getName();
    }

    @Override
    public String listp() throws RemoteException {
        return this.servicoRemoto.getList();
    }

    @Override
    public Part getp(int id) throws RemoteException {
        this.pecaAtual = this.servicoRemoto.getPart(id);
        return this.pecaAtual;
    }

    @Override
    public void showp() throws RemoteException {
        if (this.pecaAtual != null) {
            System.out.println("Id: " + this.pecaAtual.getId());
            System.out.println("Nome: " + this.pecaAtual.getNome());
            System.out.println("Descrição: " + this.pecaAtual.getDescricao());
            Vector<Part> componentes = this.pecaAtual.getComponentes();
            System.out.println("Subcomponentes: ");
            for (Part p : componentes) {
                System.out.println("Id: " + p.id + ", Nome da Peça: " + p.getNome());
            }
        } else {
            System.out.println("Selecione uma peça primeiro!");
        }
    }

    @Override
    public void clearlist() throws RemoteException {
        this.servicoRemoto.ClearRepository();
    }

    @Override
    public void addsubpart(int n) throws RemoteException {
        if (this.pecaAtual != null) {
            Part novaPeca = this.pecaAtual;
            novaPeca.quantidade = n;
            this.servicoRemoto.addPart(novaPeca);
        } else {
            System.out.println("Selecione uma peça primeiro!");
        }
    }

    @Override
    public void addp(Part p) throws RemoteException {
        //p.componentes = this.pecaAtual.componentes;
        this.servicoRemoto.addPart(p); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quit(String repositoryName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
