package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class PartRepository implements PartRepositoryInterface {
  
  private String respositoryName;
  private Vector<Part> partList;
  
  public PartRepository(Part[] parts, String name) {
    respositoryName = name;
    partList = new Vector<Part>(Arrays.asList(parts));
  }

  public PartRepository(String name) {
    respositoryName = name;
    partList = new Vector<Part>();
  }

  public void ClearRepository(){
    this.partList.clear();
}

  public String getName() {
    return respositoryName;
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
  
  public String getList() {
    return partList.toString();
  }

  public int size() {
    return partList.size();
  }

  public String toString() {
      return partList.toString();
  }
  
}