package userModel;
import java.util.HashMap;
import java.util.Map.Entry;

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
		etudiant.setIdGroupe(id);
		nombre++;
	}
	
	public void removeEtudiant(Etudiant etudiant){
		groupe.remove(etudiant.getId());
		etudiant.setIdGroupe(0);
		nombre--;
	}
	
	public void removeAllEtudiant(){
		for(Entry<Integer, Etudiant> entry : groupe.entrySet()){
			removeEtudiant(entry.getValue());
		}
	}
}
