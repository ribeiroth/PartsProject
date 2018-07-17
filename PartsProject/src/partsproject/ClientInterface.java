
package partsproject;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author thasr
 */
public interface ClientInterface extends Remote{
    
    public void bind (String repositoryName) throws RemoteException;
    
    public Part[] listp () throws RemoteException; //Lista as peças do repositório atual.
    
    public Part getp (int id) throws RemoteException; //Busca uma peça por código
    
    public void showp () throws RemoteException; //Mostra atributos da peça atual.
    
    public void clearlist () throws RemoteException; //Esvazia a lista de subpeças atual.
    
    public void addsubpart (int n) throws RemoteException; //Adiciona à lista de subpeças atual n unidades da peça atual
    
    public void addp (String repositoryName) throws RemoteException; //Adiciona uma peça ao repositório atual. A lista de subpeças atual é usada com lista de subcomponentes diretos da nova peça. (É só para isto que existe a lista de subpeças atual.)
    
    public void quit (String repositoryName) throws RemoteException; //
      
}