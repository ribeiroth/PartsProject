package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class PartRepository implements PartRepositoryInterface {
  
  private Vector<Part> partList; //Usar vector que é sincronizado?
  
 
  public PartRepository(Part[] parts) {
    partList = new Vector<Part>(Arrays.asList(parts));
  }
  
  @Override
  public void ClearRepository(){
      this.partList.clear();
  }

 
  public PartRepository() {
    partList = new Vector<Part>();
  }
  
  @Override
  public void addPart(Part part) {
      partList.add(part);
  }
  
  @Override
  public synchronized Part getPart(int id) { 
    Iterator<Part> parts = partList.iterator();
    while (parts.hasNext()) {
      Part part = parts.next();
      if (part.id == id)
        return part;
    }
    return null;
  }
  
  @Override
  public Part[] getList() {
    return (Part[]) partList.toArray();
  }

  public String toString() {
      return partList.toString();
  }
  
}