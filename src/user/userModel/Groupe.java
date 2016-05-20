package userModel;

public class Groupe {

	private int id;
	private int nombre;
	
	Groupe(int id){
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
}
