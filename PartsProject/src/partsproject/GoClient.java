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
        
        System.out.println("Digite o código da operação que deseja realizar:");
        
        int cod, crt;
        do {
            System.out.println("\n1 - Exibir nome do repositório\n"
            + "2 - Exibir número de peças no repositório\n"
            + "3 - Listar peças no repositório\n"
            + "4 - Buscar uma peça por código (no repositório atual)\n"
            + "5 - Adicionar nova peça ao repositório\n"
            + "6 - Buscar uma peça por código (em todos repositórios)\n"
            + "7 - Trocar de Repositório\n"
            + "0 - Sair\n");
            cod = ler.nextInt();

            try {
                switch(cod) {
                    case 1: cliente.getRepositoryName(); 
                            break;
                    case 2: System.out.println("Peças no repositório:\n" + cliente.countParts());
                            break;
                    case 3: System.out.println("Lista de peças no repositório:\n" + cliente.listp());
                            break;
                    case 4: {
                        System.out.println("Digite o código da peça:");
                        Part p = cliente.getp(ler.nextInt());
                        if(p != null){ 
                            System.out.println(p.nome);
                            System.out.println("1 - Adicionar essa peça a lista subpeças");
                            System.out.println("2 - Exibir atributos");
                            System.out.println("3 - Limpar SubComponentes");
                            System.out.println("0 - Sair");
                            do{
                                crt = ler.nextInt();
                                switch(crt) {
                                    case 1: {
                                        System.out.println("Digite a quantidade:");
                                        int qtde = ler.nextInt();
                                        cliente.addsubpart(qtde);
                                    };
                                    case 2: {
                                        cliente.showp();
                                    } 
                                    case 3: {
                                        cliente.clearlist();
                                    } 
                                }
                            }while(crt != 0);
                            
                            
                        }else{ 
                            System.out.println("Peça não encontrada!");
                        };
                        break;
                    }
                    case 5: {
                        System.out.println("Digite um nome para peça:");
                        String name = ler.next();
                        System.out.println("Digite uma decrição para a peça:");
                        String desc = ler.next();
                        Part pecaAtual = new Part(name, desc);
                        cliente.addp(pecaAtual);
                        break;
                    }
                    case 6: {
                        System.out.println("Digite o código da peça:");
                        cliente.searchPartById(ler.nextInt());
                        break;
                        
                    }
                    case 7: {
                        System.out.println("Digite o nome do Repositório:");
                        String nome = ler.next();
                         System.out.println("Digite 22o número da porta:\n");
                        int porta = ler.nextInt();
                      cliente.bind(cliente, nome, porta);
                      break;
                    }
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
        } while(cod != 0);
          
    }
    
}

