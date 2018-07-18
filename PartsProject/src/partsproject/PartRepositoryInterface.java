package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author ufabc
 */
public interface PartRepositoryInterface extends Remote {

  public String getName() throws RemoteException;
  
  public void addPart(Part part) throws RemoteException;
  
  public Part getPart(int id) throws RemoteException;
  
  public String getList() throws RemoteException;

  public int size()  throws RemoteException;
  
  public void ClearRepository() throws RemoteException;
    
}