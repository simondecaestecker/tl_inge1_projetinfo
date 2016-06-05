package userModel;
import java.util.HashSet;

/**
 * Cette classe permet la création d'un Groupe
 * Elle contient deux attributs : son id et le nombre d'étudiant contenu dans le groupe.
 * Elle contient les fonctions permettant la bonne gestion du groupe : ajout/suppression d'etudiant, changer le nombre d'etudiant
 * 
 * @author Simon Decaestecker et Arthur Louchart
 * @version 06/2016
 */
public class Groupe {

	private int id;
	private int nombre;
	private HashSet<String> groupe;

	/**
	 * Constructeur de Groupe
	 * 
	 * @param id
	 * 		ID du groupe
	 */
	public Groupe(int id){
		this.id = id;	
		this.nombre = 0;
		this.groupe = new HashSet<String>();
	}

	/**
	 * Getter de l'ID du groupe
	 * @return int contenant l'ID du groupe
	 */
	public int getId(){
		return id;
	}

	/**
	 * Getter permettant d'obtenir le nombre d'étudiants dans le groupe
	 * @return int contenant le nombre d'étudiants dans le groupe
	 */
	public int getNombre(){
		return nombre;
	}

	/**
	 * Setter de l'ID du groupe
	 * 
	 * @param id
	 * 		ID du groupe
	 */
	public void setId(int id){
		this.id = id;
	}

	/**
	 * Setter du nombre d'étudiants dans le groupe
	 * 
	 * @param nombre
	 * 		Nombre d'étudiants dans le groupe
	 */
	public void setNombre(int nombre){
		this.nombre = nombre;
	}

	/**
	 * Ajout d'un étudiant au groupe
	 * Cette fonction ajoute l'etudiant à la HashMap puis incrémente le nombre d'etudiant du groupe.
	 * @param etudiant
	 * 		Etudiant à ajouter au groupe
	 */
	public void addEtudiant(Etudiant etudiant){
		groupe.add(etudiant.getLogin());
		etudiant.setIdGroupe(id);
		nombre++;
	}

	/**
	 * Suppression d'un etudiant du groupe
	 * Cette fonction supprime l'etudiant à la HashMap puis décrémente le nombre d'etudiant du groupe.
	 * @param etudiant
	 * 		Etudiant à supprimer du groupe
	 */
	public void removeEtudiant(Etudiant etudiant){
		groupe.remove(etudiant.getLogin());
		etudiant.setIdGroupe(-1);
		nombre--;
	}

	/**
	 * Suppression de tous les étudiants du groupe
	 * Cette fonction vide la HashMap d'etudiant
	 */
	public void removeAllEtudiant(){
		groupe.clear();
	}

	/**
	 * Getter de la liste des étudiants appartenant au groupe
	 * 
	 * @return HashSet<String> contenant la liste des étudiants appartenant au groupe
	 */
	public HashSet<String> getEtudiantsGroupe(){
		return groupe;
	}
}
