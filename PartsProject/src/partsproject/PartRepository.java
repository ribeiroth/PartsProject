package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

/**
 *
 * @author ufabc
 */
public class PartRepository implements PartRepositoryInterface {
  
  private Vector<Part> partList; //Usar vector que é sincronizado?
  
  public PartRepository(Part[] parts) {
    partList = new Vector<Part>(Arrays.asList(parts));
  }

  public PartRepository() {
    partList = new Vector<Part>();
  }
  
  public void addPart(Part part) {
      partList.add(part);
  }
  
  public synchronized Part getPart(int id) { 
    Iterator<Part> parts = partList.iterator();
    while (parts.hasNext()) {
      Part part = parts.next();
      if (part.id == id)
        return part;
    }
    return null;
  }
  
  public Part[] getList() {
    return (Part[]) partList.toArray();
  }

  public String toString() {
      return partList.toString();
  }
  
  
}