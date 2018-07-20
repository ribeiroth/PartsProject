package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public class PartRepository implements PartRepositoryInterface {
  
  private String respositoryName;
  private Vector<Part> partList;
  private static Map<String,Integer> Hosts = new HashMap<String,Integer>();
  
  public PartRepository(Part[] parts, String name, int port) {
    respositoryName = name;
    partList = new Vector<Part>(Arrays.asList(parts));
    this.Hosts.put(name, port);
  }

  public PartRepository(String name, int port) {
    respositoryName = name;
    partList = new Vector<Part>();
    this.Hosts.put(name, port);
    System.out.print("Deu certo?: " + this.Hosts.get(name));
  }
  
  public Map<String,Integer> GetHosts(){
      return PartRepository.Hosts;
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