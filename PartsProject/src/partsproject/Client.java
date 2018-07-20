package partsproject;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements ClientInterface {

    protected PartRepositoryInterface servicoRemoto;
    protected Part pecaAtual;
    protected String serverName;
    protected int serverPort;

    public Client(String serverName, int serverPort) {
        try {
            this.serverName = serverName;
            this.serverPort = serverPort;

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
        try {
            /*System.out.println("Repositorio Atual\n");
            c.getRepositoryName();
            c.setRemoteService(repositoryName, port);
            c.setRepositoryName(repositoryName);
            System.out.println("Repositorio novo");
            c.getRepositoryName();
            c.setRepositoryPort(port);*/
            
            this.servicoRemoto = (PartRepositoryInterface) Naming.lookup(repositoryName);
        } catch (NotBoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void searchPartById(int id) throws RemoteException, AccessException {
        PartRepositoryInterface newRepo = new PartRepository("tmp");
        for (String host : this.servicoRemoto.getHosts()) {
            //GET registry

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
        this.pecaAtual.componentes.clear();
    }

    @Override
    public void addsubpart(int n) throws RemoteException {
        if (this.pecaAtual != null) {
            Part novaPeca = this.pecaAtual;
            novaPeca.quantidade = n;
            this.pecaAtual.insertComponent(novaPeca);
        } else {
            System.out.println("Selecione uma peça primeiro!");
        }
    }

    @Override
    public void addp(Part p) throws RemoteException {
        if (this.pecaAtual != null) {
            for (Part pe : this.pecaAtual.componentes) {
                p.insertComponent(pe);
            }
        }
        this.servicoRemoto.addPart(p); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void quit(String repositoryName) throws RemoteException {
        this.servicoRemoto.ClearRepository();
    }

}
