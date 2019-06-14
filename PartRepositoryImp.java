package partsproject;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PartRepositoryImp implements PartRepository{
    
    public ArrayList<Part> PartsRepository;
    
    public PartRepositoryImp() throws RemoteException{
        PartsRepository = new ArrayList<Part>();
    }
    
    @Override
    public void CreateRepo(){
        
    }

    @Override
    public void InsertPart(Part p) throws RemoteException {
        
    }

    @Override
    public void GetPart(int id) throws RemoteException {
        
    }

    @Override
    public void ListParts() throws RemoteException {
        
    }
    
}
