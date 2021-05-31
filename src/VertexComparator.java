import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex> {

    /**
     * compare method for priorityQueue
     *      sort the vertexes by:
     *          - f value
     *          -if is equal then sort by vertex id (iteration number)
     *          - if also equal sort by step hierarchy => LL, UU, RR, DD, L , U, R , D
     */
    @Override
    public int compare(Vertex v1, Vertex v2) {
        if (v1.getTotal_heuristic_cost() < v2.getTotal_heuristic_cost()) {
            return -1;
        }
        else if (v1.getTotal_heuristic_cost() > v2.getTotal_heuristic_cost()) {
            return 1;
        }
        else if(v1.getTotal_heuristic_cost() == v2.getTotal_heuristic_cost()){
            if(v1.getId() < v2.getId()){
                return -1;
            }else if(v1.getId() > v2.getId()){
                return 1;
            }else {                     //check for hierarchy of  2 moves
                if(v1.getPrevious_move().equals("LL")) return 1;
                else if(v2.getPrevious_move().equals("LL")) return 1;
                else if(v1.getPrevious_move().equals("UU")) return -1;
                else if(v2.getPrevious_move().equals("UU")) return 1;
                else if(v1.getPrevious_move().equals("RR")) return -1;
                else if(v2.getPrevious_move().equals("RR")) return 1;
                else if(v1.getPrevious_move().equals("DD")) return -1;
                else if(v2.getPrevious_move().equals("DD")) return 1;
                                                                         //check for hierarchy of  1 moves
                else if(v1.getPrevious_move().equals("L")) return -1;
                else if(v2.getPrevious_move().equals("L")) return 1;
                else if(v1.getPrevious_move().equals("U")) return -1;
                else if(v2.getPrevious_move().equals("U")) return 1;
                else if(v1.getPrevious_move().equals("R")) return -1;
                else if(v2.getPrevious_move().equals("R")) return 1;
                else if(v1.getPrevious_move().equals("D")) return -1;
                else if(v2.getPrevious_move().equals("D")) return 1;

            }
        }
        return 0;
    }
}
