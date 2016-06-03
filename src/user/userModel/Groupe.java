package userModel;
import java.util.HashSet;

public class Groupe {

	private int id;
	private int nombre;
	private HashSet<String> groupe;
	
	public Groupe(int id){
		this.id = id;	
		this.nombre = 0;
		this.groupe = new HashSet<String>();
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
		groupe.add(etudiant.getLogin());
		etudiant.setIdGroupe(id);
		nombre++;
	}
	
	public void removeEtudiant(Etudiant etudiant){
		groupe.remove(etudiant.getLogin());
		etudiant.setIdGroupe(-1);
		nombre--;
	}
	
	public void removeAllEtudiant(){
		groupe.clear();
	}
	
	public HashSet<String> getEtudiantsGroupe(){
		return groupe;
	}
}
