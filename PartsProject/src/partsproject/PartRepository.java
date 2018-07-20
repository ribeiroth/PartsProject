package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class PartRepository implements PartRepositoryInterface {
  
  private String respositoryName;
  private Vector<Part> partList;
  private static Vector<String> Hosts = new Vector<String>();
  
  public PartRepository(Part[] parts, String name) {
    respositoryName = name;
    partList = new Vector<Part>(Arrays.asList(parts));
    Hosts.add(name);
  }
  
  @Override
  public Vector<String> getHosts(){
      return PartRepository.Hosts;
  }

  public PartRepository(String name) {
    respositoryName = name;
    partList = new Vector<Part>();
    Hosts.add(name);
    System.out.println(PartRepository.Hosts.get(0));
  }
  
  public void ClearRepository(){
    this.partList.clear();
  }

  public String getName() {
    return respositoryName;
  }
  
  public void addPart(Part part) {
      System.out.println(part.nome);
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