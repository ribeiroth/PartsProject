package partsproject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class Client implements ClientInterface {

    public PartRepositoryInterface servicoRemoto;
    public Part pecaAtual;

    public Client(String serverName, int serverPort) {
        try {

            System.out.printf("Estabelecendo conexão...");
     
            Registry registry = LocateRegistry.getRegistry(serverPort);
            this.servicoRemoto = (PartRepositoryInterface) registry.lookup(serverName);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void menu() {
        System.out.println("Digite o código da operação que deseja realizar:");
        Scanner ler = new Scanner(System.in);
        int cod;
        do {
            System.out.println("\n1 - Exibir nome do repositório\n"
            + "2 - Exibir número de peças no repositório\n"
            + "3 - Listar peças no repositório\n"
            + "4 - Buscar uma peça por código\n"
            + "5 - Adicionar nova peça ao repositório\n"
            + "0 - Sair\n");
            cod = ler.nextInt();

            try {
                switch(cod) {
                    case 1: System.out.println("Repositório: " + getn());
                            break;
                    case 2: System.out.println("Peças no repositório:\n" + this.servicoRemoto.size());
                            break;
                    case 3: System.out.println("Lista de peças no repositório:\n" + listp());
                            break;
                    case 4: {
                        System.out.println("Digite o código da peça:");
                        getp(ler.nextInt());
                        if (this.pecaAtual != null)
                            showp();
                        else
                            System.out.println("Não há peça com esse id no repostitório.");
                        break;
                    }
                    case 5: {
                        System.out.println("Digite um nome para peça:");
                        String name = ler.next();
                        System.out.println("Digite uma decrição para a peça:");
                        String desc = ler.next();
                        this.pecaAtual = new Part(name, desc);
                        addp(pecaAtual);
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
        } while(cod != 0);
    }

    @Override
    public void bind(String repositoryName) throws RemoteException {
       
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
