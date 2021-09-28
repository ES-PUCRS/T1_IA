import java.util.ArrayList;
import java.awt.Color;

public class PathFinderAStar {
	// private ArrayList<Node> conexoes;
 //    private ArrayList<Node> visitados;
 //    private ArrayList<Node> desconhecidos;
 //    private int matrizDistancias[][];
 //    private int matrizCoordenadas[][];
 //    private Maze maze;

 //    private int quantNos;
 //    private Node noInicial;
 //    private Node noFinal;
 //    private Node noCorrente;
    private Maze maze;

    public PathFinderAStar(Maze maze) {
 //        desconhecidos = new ArrayList<Node>();
 //        visitados = new ArrayList<Node>();
 //        conexoes = new ArrayList<Node>();
        this.maze = maze;
    }


    public void findPath() {
    	try {
	    	maze.setSpot(Color.BLUE, 0, 0);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
 //    public void carregaDados(Array road, int idNoInicial, int idNoFinal) {
 //        quantNos = arqControl.getQuantidade();
 //        matrizDistancias = arqControl.getDistancias();
 //        matrizCoordenadas = arqControl.getCoordenadas();
        
 //        for(int i = 0; i<quantNos;i++) desconhecidos.add(new Node(i));
        
 //        noInicial = containsNo(desconhecidos, idNoInicial);
 //        noFinal = containsNo(desconhecidos, idNoFinal);
 //        desconhecidos.remove(idNoInicial);
 //        noCorrente = noInicial;
 //    }
    
    
 //    public String encontraCaminho() {
 //        Node aux = null;
 //        String caminho = "";
 //        int distancia = 0;
 //        while(noFinal != noCorrente){ 
 //            visitados.add(noCorrente);
 //            conexoes.remove(noCorrente);
 //            insereconexoes(noCorrente.getId());
 //            noCorrente = proximoNo();
 //            if(noCorrente == null) return "Nao Existe caminho.";
 //        }
        
 //        aux = noFinal;
 //        distancia = noFinal.getPeso();
 //        while(aux != null){
 //            caminho = (aux.getId()+1)+" "+caminho;
 //            aux = aux.getAnt();
 //        }
 //        return "Caminho: "+ caminho + "  Distancia: "+ distancia;
 //    }
    

 //    private void insereconexoes(int id){
 //        Node aux;
        
 //        for(int i =0 ; i<quantNos;i++){
 //            if(matrizDistancias[id][i] > 0){
 //                aux = containsNo(desconhecidos,i);
 //                int novoPeso = matrizDistancias[id][i]+noCorrente.getPeso(); 
 //                if(aux != null){
 //                    if(!visitados.contains(aux)){
 //                        aux.setPeso(novoPeso);
 //                        aux.setAnt(noCorrente); 
 //                        conexoes.add(aux);
 //                        desconhecidos.remove(aux);
 //                    }
 //                }
 //                else{
 //                    aux = containsNo(conexoes,i);
 //                    if(aux != null){
 //                        if(aux.getPeso() > novoPeso){ 
 //                            aux.setPeso(novoPeso);
 //                            aux.setAnt(noCorrente);
 //                        }
 //                    }
 //                }
 //            }
 //        }
 //    }
    

 //    private Node proximoNo(){
 //        Node prox;
 //        int menor;
 //        int aux = 0;
 //        if(conexoes.isEmpty())return null;
        
 //        prox = conexoes.get(0);
 //        int[] coord1 = matrizCoordenadas[prox.getId()]; 
 //        int[] coord2 = matrizCoordenadas[noFinal.getId()]; 
 //        menor = prox.getPeso() + heuristica(coord1[0],coord1[1],coord2[0],coord2[1]);
        
 //        for(Node cur: conexoes){
 //            coord1 = matrizCoordenadas[cur.getId()]; 
 //            aux = heuristica(coord1[0],coord1[1],coord2[0],coord2[1]);
 //            if((cur.getPeso() + aux) < menor){//se atual melhor que menor
 //                menor = cur.getPeso() + aux;
 //                prox = cur;
 //            }
 //        }
 //        return prox;
 //    }
    
 //    private int heuristica(int latitudeCidade1,int longitudeCidade1, int latitudeCidade2, int longitudeCidade2) {
 //        return Math.abs(latitudeCidade1-latitudeCidade2) + Math.abs(longitudeCidade1-longitudeCidade2);
 //    }

 //    private Node containsNo(ArrayList<Node> list, int id){
 //        for(Node cur: list){
 //            if(cur.getId() == id){
 //                return cur;
 //            }
 //        }
 //        return null;
 //    }
    
 //    public static void main(String args[]) {
 //                AlgoritmoAStar algoritmo = new AlgoritmoAStar();
	// 	algoritmo.carregaDados(args[0], (Integer.parseInt(args[1])-1), (Integer.parseInt(args[2])-1));
	// 	System.out.println(algoritmo.encontraCaminho());
	// }	

}