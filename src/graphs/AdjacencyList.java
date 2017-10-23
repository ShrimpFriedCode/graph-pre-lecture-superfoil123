package graphs;
import org.omg.PortableInterceptor.INACTIVE;
import sequences.*;

import java.util.ArrayList;

/**
 * TODO
 *
 * Complete the AdjacencyList class, implementing all of the
 * methods needed for the Graph interface and
 * finish the constructor.
 *
 */
public class AdjacencyList implements Graph<Integer> {

    class Node {
        Node(int d) { data = d;}
        int data;
        ArrayList<Integer> adj = new ArrayList<Integer>();
    }

    private ArrayList<Node> adjacentNums = new ArrayList<>(); //list of objects with data and subtype of list of data

    private int num_Vertices;

    private boolean in(Integer source){

        boolean is = false;
        if(adjacentNums.size() != 0) {
            for (Node x : adjacentNums) {
                if (x.data == (source)) {
                    is = true;
                }
            }
        }
        return is;
    }

    private int find(Integer source){
        int location = 0;

        for(int i = 0; i < adjacentNums.size(); i++){
            if(adjacentNums.get(i).data == (source)){
                location = i;
                break;
            }
        }
        return location;
    }

    public AdjacencyList(int num_vertices) {

        this.num_Vertices = num_vertices;

    }

    public int numVertices(){

        return this.num_Vertices;
    }

    public void addEdge(Integer source, Integer target){//adds int source to adjacent if it doesnt exist, then adds target to its sublist. If source already exists, add target to sublist

        if(!in(source)){
            Node n = new Node(source);
            n.adj.add(target);
            adjacentNums.add(n);
        }
        else {
            adjacentNums.get(find(source)).adj.add(target);
        }

    }


    public Sequence<Integer> adjacent(Integer source) {//returns array of all data in the sub list of the index of adjacent

        //need to get size of adjacent int in source's adj list
        //initialize ret to n=adj.size()
        //add the adj values to ret

        Node sou = adjacentNums.get(find(source));
        int sizeOfAdj = sou.adj.size();

        Array<Integer> ret = new Array<Integer>(sizeOfAdj, 0);
        for(int i = 0; i < sizeOfAdj; i++){
            ret.set(i, sou.adj.get(i));
        }

        return ret;
    }

    public Sequence<Integer> vertices(){//returns array of integers in adjacent

        int sizeOfvert = adjacentNums.size();

        Array<Integer> ret = new Array<Integer>(sizeOfvert, 0);

        for(int i = 0; i < sizeOfvert; i++){

            ret.set(i, adjacentNums.get(i).data);

        }
        return ret;
    }
}
