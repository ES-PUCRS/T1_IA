import java.lang.InterruptedException;
import java.lang.NullPointerException;

import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.awt.Color;

/*
 *  f(n) = g(n) + h(n)
 *
 *  g(n) -> The exact cost of the path from starting point to any vertex n;
 *  h(n) -> Heuristic estimated cost from vertex n to the goal;
 *
 *  https://qiao.github.io/PathFinding.js/visual/
 */
public class PathFinderAStar {
    private final Color PREVIOUS_SPOTS  = Color.GRAY;
    private final Color UNKNOWN_SPOT    = Color.CYAN;
    private final Color CURRENT_SPOT    = Color.BLUE;
    private final Color FINISH_PATH     = Color.GREEN;

    public static final int G_WEIGHT = 1;

    private ArrayList<Node> connections;
    private ArrayList<Node> unknown;
    private ArrayList<Node> known;

    private Node begin;
    private Node finish;
    private Node current;


    private Maze maze;

    private final int roadStones;
    private final int xExitCoord;
    private final int yExitCoord;

    public PathFinderAStar(Maze maze) {
        this.connections = new ArrayList<Node>();
        this.unknown = new ArrayList<Node>();
        this.known = new ArrayList<Node>();
        this.maze = maze;

        this.xExitCoord = maze.getExit() % maze.X_LENGTH;
        this.yExitCoord = maze.getExit() / maze.X_LENGTH;
        this.roadStones = maze.getRoad().size();
    }


    public void findPath() throws InterruptedException {
        unknown.addAll(
            Arrays.stream(
                        maze.getRoad()
                            .toArray()
                    ).map(i -> new Node((Integer) i))
                     .collect(Collectors.toList())
        );

        try {
            current = begin = findById(unknown, maze.getEntrance());
            finish = findById(unknown, maze.getExit());
            maze.setSpot(Color.BLUE, current.getId());
            unknown.remove(current);
        } catch (NullPointerException npe) {
            throw new
                InterruptedException("There is no entrance or exit.");
        } catch (NoSuchFieldException nsfe) {
            nsfe.printStackTrace();
        }

        maze.setMessage(
            search()
        );
    }


    private String search() {
        Node aux = null;
        String path = "";
        int distance = 0;

        try {
            while(finish != current) {
                Thread.sleep(App.delay);
                acknowledge(current);
                insertConnection(current.getId());

                Thread.sleep(App.delay);
                current = moveTo(current, nextNode());
                if(current == null) return "There is no wat out.";
            }
        } catch (InterruptedException ignored) { }
        
        aux = finish;
        distance = finish.getWeight();
        while(aux != null){
            try {
                maze.setSpot(FINISH_PATH, aux.getId());
            } catch (NoSuchFieldException ignored) {}

            path = (aux.getId()+1)+" "+path;
            aux = aux.getPrev();
        }
        return "Distance: " + distance;
    }



    private void acknowledge(Node node) {
        known.add(node);
        connections.remove(node);
    }


    private Node moveTo(Node prev, Node node) {
        try {
            maze.setSpot(PREVIOUS_SPOTS, prev.getId());
            maze.setSpot(CURRENT_SPOT, node.getId());
            unknown.remove(node);
        } catch (NoSuchFieldException nsfe) {
            nsfe.printStackTrace();
        }

        return node;
    }


    private Node findById(List<Node> list, Integer nodeId) {
        if (nodeId == null) return null;
        return
            list.stream()
                .filter(node -> node.getId() == nodeId)
                .findAny()
                .orElse(null);
    }

    private void insertConnection(int id) {
        int newWeight = current.getWeight() + G_WEIGHT;
        wire(maze.getUpPos(id), newWeight);
        wire(maze.getDownPos(id), newWeight);
        wire(maze.getLeftPos(id), newWeight);
        wire(maze.getRightPos(id), newWeight);
    }

    private void wire(Integer id, int newWeight) {
        if(id == null) return;

        Node aux = findById(unknown, id);
        if(aux != null){
            if(!known.contains(aux)){
                try {
                    aux.setWeight(newWeight);
                    aux.setPrev(current); 
                    connections.add(aux);
                    unknown.remove(aux);
                    maze.setSpot(UNKNOWN_SPOT, aux.getId());
                } catch (NoSuchFieldException nsfe) {
                    nsfe.printStackTrace();
                }
            }
        } else {
            aux = findById(connections, id);
            if(aux != null){
                if(aux.getWeight() > newWeight){ 
                    aux.setWeight(newWeight);
                    aux.setPrev(current);
                }
            }
        }
    }
    
    private Node nextNode() {
        int lighter, aux = 0;
        Node next;

        if(connections.isEmpty())
            return null;

        next = connections.get(0);
        lighter = next.getWeight() + heuristic(next);

        for(Node connection: connections) {
            aux = heuristic(connection);
            if(connection.getWeight() + aux < lighter) {
                lighter = connection.getWeight() + aux;
                next = connection;
            }
        }

        return next;
    }

    private int heuristic(Node n) {
        return
            Math.abs((n.getId() % maze.X_LENGTH) - xExitCoord) +
            Math.abs((n.getId() / maze.X_LENGTH) - yExitCoord);
    }
}