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
    
    private static int codigo = 0; 
    
    public int id;
    public String nome;
    public String descricao;
    public Vector<Part> componentes;
    public boolean primitive;
    public int quantidade;
    
    /* Criar peças (primitiva ou não) sem especificar componentes */
    public Part(String nome, String descricao, int quantidade, boolean primitive){       
        this.id = codigo++;
        this.nome = nome;
        this.descricao = descricao;
        this.primitive = primitive;
        this.componentes = new Vector<Part>();
    }

    /* Criar peças composta a partir de componentes */
    public Part(String nome, String descricao, int quantidade, Part[] componentes){
        this(nome, descricao, quantidade, false);
        this.componentes = new Vector<Part>(Arrays.asList(componentes));
    }
    /* Criar peças primitivas */
    public Part(String nome, String descricao, int quantidade){
        this(nome, descricao, quantidade, true);
    }
    /* Criar uma única peça primitiva */
    public Part(String nome, String descricao){
        this(nome, descricao, 1, true);
    }
    
    public synchronized void insertComponent(Part p){ 
        if(!this.primitive){
            componentes.add(p);
        }
    }

    public String toString() {
      return nome;
  }
    
    //get set
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
        }
    
}
