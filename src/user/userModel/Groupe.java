package userModel;
import java.util.HashMap;

public class Groupe {

	private int id;
	private int nombre;
	private HashMap<Integer, Etudiant> groupe;
	
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
		groupe.put(etudiant.getId(), etudiant);
		nombre++;
	}
	
	public void removeEtudiant(Etudiant etudiant){
		groupe.remove(etudiant.getId());
		nombre--;
	}
}
