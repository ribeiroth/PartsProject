package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ufabc
 */
public interface PartRepositoryInterface extends Remote {
  
  public void addPart(Part part) throws RemoteException;
  
  public Part getPart(int id) throws RemoteException;
  
  public Part[] getList() throws RemoteException;
    
}