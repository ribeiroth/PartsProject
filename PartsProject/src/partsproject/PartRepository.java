package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface PartRepository extends Remote{

    public void CreateRepo() throws RemoteException;
    public void InsertPart(Part p) throws RemoteException;
    public void GetPart(int id) throws RemoteException;
    public void ListParts() throws RemoteException;
    
}

//CRIA SERVIDORES QUE CONTÉM UM REPOSITÓRIO DE PEÇAS