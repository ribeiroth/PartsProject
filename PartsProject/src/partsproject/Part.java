/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partsproject;

import java.util.*;

/**
 *
 * @author ufabc
 */
public class Part {
    
    private static int Codigo = 0; 
    
    public int Id;
    public String Nome;
    public String Descricao;
    public ArrayList<SubPart> Componentes;
    public boolean Primitive;
    public int Quantidade;
    
    public Part(String Nome, String Descricao, int Quantidade, boolean Primitive){       
        this.Id = Codigo++;
        this.Nome = Nome;
        this.Descricao = Descricao;   
        Componentes = new ArrayList<SubPart>();
    }
    
    public void InsertComponents(SubPart p){ 
        if(!this.Primitive){
            Componentes.add(p);
        }
    }
    
    
}
