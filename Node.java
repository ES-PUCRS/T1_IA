public class Node {

	private int peso;
	private int idNo;
	private Node ant;
	
	public Node(int id){
		this.idNo = id;
		peso= 0;
		ant = null;
	}
	
	public int getId(){
		return idNo;
	}
	
	public void setPeso(int peso){
		this.peso = peso;
	}
	
	public void setAnt(Node n){
		ant = n;
	}
	
	public Node getAnt(){
		return ant;
	}
	
	public int getPeso(){
		return peso;
	}
}