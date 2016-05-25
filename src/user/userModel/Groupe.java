package userModel;

import java.util.LinkedList;

public class Groupe {

	private int id;
	private int nombre;
	private LinkedList<Etudiant> groupe;
	
	public Groupe(int id){
		this.id = id;	
		this.nombre = 0;
	}
	
	public int getId(){
		return id;
	}
	
	public int getNombre(){
		return nombre;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public void setNombre(int nombre){
		this.nombre = nombre;
	}
	
	public void addEtudiant(Etudiant etudiant){
		groupe.add(etudiant);
	}
	
	public void removeEtudiant(Etudiant etudiant){
		groupe.remove(etudiant);
	}
}
