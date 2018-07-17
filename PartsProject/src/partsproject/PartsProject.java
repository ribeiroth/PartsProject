/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partsproject;

import java.rmi.AlreadyBoundException;

public class PartsProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AlreadyBoundException {
          System.out.printf("começou");
       PartsRepositoryServer partReposity = new PartsRepositoryServer("alynne",4200);
    }
    
}
