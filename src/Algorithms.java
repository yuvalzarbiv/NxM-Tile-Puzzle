import java.io.File;
import java.io.FileWriter;
import java.util.*;

public class Algorithms {

    int num_of_vertex_created = 0;
    static int iter =1;
    static int ID=0;

    /**
     * print the output of a function calculated from goal to start
     */
    private void PrintReversedOutput(String path, int num_vertex, int cost, boolean if_time, long time) {
        try  {
            FileWriter outfile = new FileWriter("output.txt");
            String _path = path.substring(1);
            String reversed_path = ReversePath(_path);
            outfile.write(reversed_path + "\n");
            outfile.write("Num: " + num_vertex + "\n");
            outfile.write("Cost: " + cost);
            if (if_time) {
                outfile.write("\n" + (double) time / 1000.0 + " seconds");
            }
            outfile.flush();
           outfile.close();
        } catch (Exception e) {
            System.out.println("Error while creating output file");
            e.printStackTrace();
        }
    }

    /**
     * print the output of a function calculated from start to goal
     */
    private void PrintOutput(String path, int num_vertex, int cost, boolean if_time, long time) {
        try {
            File file = new File("output.txt");
            FileWriter outfile = new FileWriter("output.txt");
            String _path = path.substring(1);
            outfile.write(_path + "\n");
            outfile.write("Num: " + num_vertex + "\n");
            outfile.write("Cost: " + cost);
            if (if_time) {
                outfile.write("\n" + (double) time / 1000.0 + " seconds");
            }
            outfile.flush();
            outfile.close();
        } catch (Exception e) {
            System.out.println("Error while creating output file");
            e.printStackTrace();
        }
    }

    /**
     * reverse the path from start to end for a function calculated from goal to start

     */
    private String ReversePath(String path){
        String[] new_path = path.split("((?<=-)|(?=-))");
        Collections.reverse(Arrays.asList(new_path));
        String str_new_path = String.join("", new_path);
        return str_new_path;
    }

    /**
     * compare the values of 2 string matrix
     * @return boolean
     */
    private boolean Compere_Matrix(String[][] mat1, String[][] mat2) {
        for (int i = 0; i < mat1.length; i++) {
            for (int j = 0; j < mat1[0].length; j++) {
                if (!mat1[i][j].equals(mat2[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * print the output when no path found
     * @param num - total num of vertexes created
     */
    private void PrintOutputNoPath(int num) {
        try (FileWriter outfile = new FileWriter("output.txt")) {
            outfile.write("no path" + "\n");
            outfile.write("Num: " + num);
        } catch (Exception e) {
            System.out.println("Error while creating output file");
            e.printStackTrace();
        }
    }

    /**
     * prints the open list content for a given iteration
     */
    public void printOpen(Hashtable<String, Vertex> openList, int iteration) {
        System.out.println("Open list in iteration No. " + iteration +":");
        Enumeration<Vertex> s = openList.elements();
        while (s.hasMoreElements()) {
            Vertex v = s.nextElement();
            String temp[][] = v.getMatrix();
            for (int i = 0; i < v.getMatrix().length; i++) {
                for (int j = 0; j < v.getMatrix()[0].length; j++) {

                    System.out.print(temp[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////// BFS ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////



//
//
//    public void BFS(String start[][], String goal[][], boolean open, boolean time) throws InterruptedException {
//
//        long startTime = System.currentTimeMillis();
//        Vertex startV = new Vertex(start);
//        Vertex goalV = new Vertex(goal);
//        Queue<Vertex> L = new LinkedList<Vertex>();
//        L.add(startV);
//        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
//        Hashtable<String, Vertex> closed_list = new Hashtable<String, Vertex>();
//        open_list.put(startV.getString_of_matrix(), startV);//insert the first element to the queue
//        int iter = 0;
//
//        while (!L.isEmpty()) {                                //while the queue isnt empty
//
//            if (open) {
//                printOpen(open_list, iter);
//                iter++;
//            }
//            Vertex n = L.poll();                  // take the first element from the queue
//            open_list.remove(n.getMatrix());      //remove element from open list
//            closed_list.put(n.getString_of_matrix(), n);     //insert n to the closed lost
//            Operators operator = new Operators();
//            List<Vertex> ver_list = operator.allowed_operators(n);
//            num_of_vertex_created += ver_list.size();
//            for (Vertex n_op : ver_list) {
//                String vertex = operator.matrix_toString(n_op.getMatrix());
//                if (!closed_list.containsKey(vertex) && !open_list.containsKey(vertex)) {
//                    if (Compere_Matrix(n_op.getMatrix(), goalV.getMatrix())) {               //if we got to the goal
//                        long endTime = System.currentTimeMillis();
//                        long timeElapsed = endTime - startTime;
//                        PrintOutput(n_op.getPath(), num_of_vertex_created, n_op.getDepth_cost(), time, timeElapsed);
//                        return;
//                    } else {
//                        L.add(n_op);
//                        open_list.put(n_op.getString_of_matrix(), n_op);                       //add to the open list
//                    }
//                }
//            }
//        }
//        PrintOutputNoPath(num_of_vertex_created);                                        //write to file- no path found
//    }
//
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    ///////////////////////////////////////////////////// DFID ///////////////////////////////////////////////////////////////
//    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    static boolean is_cutoff;
//
//    public void DFID(String start[][], String goal[][], boolean open, boolean time) {
//        long startTime = System.currentTimeMillis();
//
//        Vertex startV = new Vertex(start);
//        Vertex goalV = new Vertex(goal);
//        int num_of_vertex_created = 1;
//
//        for (int depth = 1; depth < Integer.MAX_VALUE; depth++) {
//            Hashtable<String, Vertex> branch_vertex = new Hashtable<String, Vertex>();
//            Vertex answer = new Vertex(Limited_DFS(startV, goalV, depth, branch_vertex, startTime, time, open));
//            if (open) {
//                System.out.println("iteration " + ++iter);
//                for (Map.Entry<String, Vertex> mapElement : branch_vertex.entrySet()) {
//                    String[][] mat = mapElement.getValue().getMatrix();
//                    System.out.println();
//                    for (String[] row : mat) {
//                        System.out.println(Arrays.toString(row));
//                    }
//                }
//            }
//            if (!is_cutoff) {
//                break;
//            } else if (answer.getDepth_cost() == -1) {
//                PrintOutputNoPath(num_of_vertex_created);
//                break;
//            }
//        }
//    }
//
//
//    private Vertex Limited_DFS(Vertex n, Vertex goalV, int limit, Hashtable<String, Vertex> branch_vertex, long startTime, boolean time, boolean open) {
//        if (Compere_Matrix(n.getMatrix(), goalV.getMatrix())) {
//
//            long endTime = System.currentTimeMillis();
//            long timeElapsed = endTime - startTime;
//            PrintOutput(n.getPath(), num_of_vertex_created, n.getDepth_cost(), time, timeElapsed);
//            return n;
//        } else if (limit == 0) {
//            Vertex ver = new Vertex(n);//if the limit = 0
//            ver.setCutoff(-2);
//            return ver;
//        } else {                                                                            //start checking for all operators
//
//            if (open) {
//                printOpen(branch_vertex, iter);
//                iter++;
//            }
//            branch_vertex.put(n.getString_of_matrix(), n);
//            is_cutoff = false;
//            Operators operator = new Operators();
//            List<Vertex> ver_list = operator.allowed_operators(n);
//            for (Vertex n_op : ver_list) {
//                num_of_vertex_created++;
//                String ver_string = operator.matrix_toString(n_op.getMatrix());
//                if (!branch_vertex.containsKey(ver_string)) {
//                    Vertex result = (Limited_DFS(n_op, goalV, limit - 1, branch_vertex, startTime, time, open));
//                    if (result.getCutoff() == -2) {
//                        is_cutoff = true;
//                    } else if (result.getDepth_cost() != -1) {
//                        return result;
//                    }
//                }
//            }
//            branch_vertex.remove(n.getString_of_matrix());
//            if (is_cutoff) {
//                // Vertex ver = new Vertex(n);
//                //ver.setDepth_cost(-2);
//                n.setCutoff(-2);
//                return n;
//            } else {
//                // Vertex ver = new Vertex(n);
//                // ver.setDepth_cost(-1);
//                n.setDepth_cost(-1);
//                return n;
//            }
//        }
//    }
//
//
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////A*///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//    static int i = 0;
//
//    public void ASTAR(String start[][], String goal[][], boolean open, boolean time) {
//        long startTime = System.currentTimeMillis();
//
//        Vertex startV = new Vertex(start);
//        Vertex goalV = new Vertex(goal);
//        VertexComparator priority_f = new VertexComparator();
//        PriorityQueue<Vertex> L = new PriorityQueue<>(5, priority_f);
//        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
//        Hashtable<String, Vertex> closed_list = new Hashtable<String, Vertex>();
//        open_list.put(startV.getString_of_matrix(), startV);//insert the first element to the queue
//        L.add(startV);
//        startV.setId(++i);
//
//        while (!L.isEmpty()) {
//
//            if (open) {
//                printOpen(open_list, iter);
//                iter++;
//            }
//            ID++;
//            Vertex n = L.poll();
//            open_list.remove(n.getString_of_matrix());
//            if (Compere_Matrix(n.getMatrix(), goalV.getMatrix())) {
//                long endTime = System.currentTimeMillis();
//                long timeElapsed = endTime - startTime;
//                PrintOutput(n.getPath(), num_of_vertex_created, n.getDepth_cost(), time, timeElapsed);
//                return;
//            } else {
//                closed_list.put(n.getString_of_matrix(), n);
//                Operators operator = new Operators();
//
//                List<Vertex> ver_list = operator.allowed_operators(n);
//                num_of_vertex_created += ver_list.size();
//
//                for (Vertex n_op : ver_list) {
//                   n_op.setId(ID);
//                    //Vertex tmp = in_queue(L, n_op);
//                    if (!closed_list.containsKey(n_op.getString_of_matrix()) && !open_list.containsKey(n_op.getString_of_matrix())) {
//                        n_op.setH_cost(calculateManhattanDistance(n_op, goalV, n_op.getNum_of_empty_block()));
//                        n_op.setTotal_heuristic_cost();
//                        L.add(n_op);
//                        open_list.put(n_op.getString_of_matrix(), n_op);
//                    } else {
//
//                        if (open_list.containsKey(n_op.getString_of_matrix())) {//tmp != null) {
//                            Vertex tmp = in_queue(L, n_op);
//                            n_op.setH_cost(calculateManhattanDistance(n_op, goalV, n_op.getNum_of_empty_block()));
//                            n_op.setTotal_heuristic_cost();
//                            if (n_op.getTotal_heuristic_cost() < tmp.getTotal_heuristic_cost()) {
//                                open_list.remove(tmp);
//                                L.remove(tmp);
//                                L.add(n_op);
//                                open_list.put(n_op.getString_of_matrix(), n_op);
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        PrintOutputNoPath(num_of_vertex_created);                                        //write to file- no path found
//    }
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////ManhattanDistance///////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//    private int calculateManhattanDistance(Vertex v, Vertex goal, int num_of_empty) {
//        double manhattanDistanceSum = 0;
//        int[] empty1 = v.getEmpty_block1();
//        int[] empty2 = v.getEmpty_block2();
//        int row1 = empty1[0];
//        int col1 = empty1[1];
//        int row2 = empty2[0];
//        int col2 = empty2[1];
//        for (int i = 0; i < goal.getMatrix().length; i++)
//            for (int j = 0; j < goal.getMatrix()[0].length; j++) {
//                String value = goal.getMatrix()[i][j];
//                if (!v.getMatrix()[i][j].equals(goal.getMatrix()[i][j])) {
//                    outerloop:
//                    for (int k = 0; k < v.getMatrix().length; k++) {
//                        for (int l = 0; l < v.getMatrix()[0].length; l++) {
//                            if (v.getMatrix()[k][l].equals(value)) {
//                                if (value.equals("_")) {
//                                    break outerloop;
//                                }
//                                if (v.getNum_of_empty_block() == 2) {
//                                    if ((row1 == row2 && (col1+1) == col2) || ((row1+1) == row2 && col1 == col2)) {
//                                        manhattanDistanceSum += ((Math.abs(i - k)*(3.5)) + (Math.abs(j - l)*3)) ;
//                                        break outerloop;
//                                    }
//                                    else {
//                                        manhattanDistanceSum += (Math.abs(i - k) + Math.abs(j - l)) * 4.8;
//                                        break outerloop;
//                                    }
//                                }
//                                else {
//                                    manhattanDistanceSum += (Math.abs(i - k) + Math.abs(j - l)) * 5;
//                                    break outerloop;
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        return (int) manhattanDistanceSum;
//    }
//
//    private Vertex in_queue(PriorityQueue<Vertex> p, Vertex ver) {
//        for (Vertex v : p) {
//            if (v.getString_of_matrix().equals(ver.getString_of_matrix())) {
//                return v;
//            }
//        }
//        return null;
//    }
//
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //////////////////////////////////////////////////////////////IDAstar///////////////////////////////////////////////////////////////////
//    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    public void IDAstar(String start[][], String goal[][], boolean open, boolean time) {
//        long startTime = System.currentTimeMillis();
//
//        Vertex startV = new Vertex(start);
//        Vertex goalV = new Vertex(goal);
//        Stack<Vertex> L = new Stack<Vertex>();
//        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
//        startV.setH_cost(calculateManhattanDistance(startV, goalV, startV.getNum_of_empty_block()));
//        double t = startV.getH_cost();
//        while (t != Double.MAX_VALUE) {
//            double min_f = Double.MAX_VALUE;
//            startV.setOut(false);
//            L.push(startV);
//            open_list.put(startV.getString_of_matrix(), startV);
//
//            while (!L.isEmpty()) {
//                if (open) {
//                    printOpen(open_list, iter);
//                    iter++;
//                }
//
//                ID++;
//                Vertex n = L.pop();
//                if (n.isOut()) {
//                    open_list.remove(n.getString_of_matrix(), n);
//                } else {
//                    n.setOut(true);
//                    L.push(n);
//                    Operators operator = new Operators();
//
//                    List<Vertex> ver_list = operator.allowed_operators(n);
//                    num_of_vertex_created += ver_list.size();
//
//                    for (Vertex n_op : ver_list) {
//                        n_op.setId(ID);
//                        n_op.setH_cost(calculateManhattanDistance(n_op, goalV, n_op.getNum_of_empty_block()));
//                        n_op.setTotal_heuristic_cost();
//
//                        //print open
//
//                        if (n_op.getTotal_heuristic_cost() > t) {
//                            min_f = Math.min(min_f, n_op.getTotal_heuristic_cost());
//                            continue;
//                        }
//                        Vertex tmp = open_list.get(n_op.getString_of_matrix());
//
//                        if (open_list.containsKey(n_op.getString_of_matrix()) && tmp.isOut() == true) {
//                            continue;
//                        }
//                        if (open_list.containsKey(n_op.getString_of_matrix()) && tmp.isOut() == false) {
//                            tmp.setH_cost(calculateManhattanDistance(tmp, goalV, tmp.getNum_of_empty_block()));
//                            tmp.setTotal_heuristic_cost();
//                            if (tmp.getTotal_heuristic_cost() > n_op.getTotal_heuristic_cost()) {
//                                L.remove(tmp);
//                                open_list.remove(tmp.getString_of_matrix(), tmp);
//                            } else {
//                                continue;
//                            }
//                        }
//                        if (Compere_Matrix(n_op.getMatrix(), goalV.getMatrix())) {
//                            long endTime = System.currentTimeMillis();
//                            long timeElapsed = endTime - startTime;
//                            PrintOutput(n_op.getPath(), num_of_vertex_created, n_op.getDepth_cost(), time, timeElapsed);
//                            return;
//                        }
//                        L.push(n_op);
//                        open_list.put(n_op.getString_of_matrix(), n_op);
//                    }
//                }
//            }
//            startV.setOut(false);
//            t = min_f;
//        }
//        PrintOutputNoPath(num_of_vertex_created);
//    }

    //    public void DFBnB(String start[][], String goal[][], boolean open, boolean time) {
//        long startTime = System.currentTimeMillis();
//
//        Vertex startV = new Vertex(start);
//        Vertex goalV = new Vertex(goal);
//        Stack<Vertex> L = new Stack<Vertex>();
//        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
//        VertexComparator priority_f = new VertexComparator();
//        Vertex result = null;
//
//        double t = Double.MAX_VALUE;
//        L.push(startV);
//        open_list.put(startV.getString_of_matrix(), startV);
//
//        while (!L.isEmpty()) {
//            if (open) {
//                printOpen(open_list, iter);
//                iter++;
//            }
//
//            Vertex n = L.pop();
//            if (n.isOut()) {
//                open_list.remove(n.getString_of_matrix());
//            } else {
//                n.setOut(true);
//                L.push(n);
//                Operators operator = new Operators();
//                List<Vertex> ver_list = operator.allowed_operators(n);
//                num_of_vertex_created += ver_list.size();
//
//                for (Vertex n_op : ver_list) {
//                    n_op.setH_cost(calculateManhattanDistance(n_op, goalV, n_op.getNum_of_empty_block()));
//                    n_op.setTotal_heuristic_cost();
//                }
//
//                Collections.sort(ver_list, priority_f);
//                ListIterator<Vertex> itr = ver_list.listIterator();
//
//                while (itr.hasNext()){
//                    Vertex g = itr.next();
//                    if (g.getTotal_heuristic_cost() >= t) {
//                        itr.remove();
//                        while(itr.hasNext() && ver_list.contains(itr.next())){
//                            itr.remove();
//                        }
//                    } else if (open_list.containsKey(g.getString_of_matrix()) && open_list.get(g.getString_of_matrix()).isOut()) {
//                        itr.remove();
//                    } else if (open_list.containsKey(g.getString_of_matrix()) && open_list.get(g.getString_of_matrix()).isOut() == false) {
//                        // g_tag.setH_cost(calculateManhattanDistance(g_tag, goal, g_tag.getNum_of_empty_block()));
//                        //g_tag.setTotal_heuristic_cost();
//                        if (open_list.get(g.getString_of_matrix()).getTotal_heuristic_cost() <= g.getTotal_heuristic_cost()) {
//                            itr.remove();
//                        } else {
//                            L.remove(g);
//                            open_list.remove(g.getString_of_matrix());
//                        }
//                    } else if (Compere_Matrix(g.getMatrix(), goalV.getMatrix())) {
//                        t = (int) g.getTotal_heuristic_cost();
//                        result = g;
//                        while(itr.hasNext() && ver_list.contains(itr.next())){
//                            itr.remove();
//                        }
//                    }
//                }
//                //insert N in a reverse order to L and H
//                for (int i = ver_list.size() -1 ; i >=0 ; i--) {
//                    L.push(ver_list.get(i));
//                    open_list.put(ver_list.get(i).getString_of_matrix(), ver_list.get(i));
//                    ver_list.remove(ver_list.get(i));
//                }
//            }
//        }
//        if (result != null) {
//            long endTime = System.currentTimeMillis();
//            long timeElapsed = endTime - startTime;
//            PrintOutput(result.getPath(), num_of_vertex_created, result.getDepth_cost(), time, timeElapsed);
//
//        }else{
//            PrintOutputNoPath(num_of_vertex_created);
//        }
//
//    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////// BFS ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * BFS algorithm calculated from start to goal
     * @param start - start state set to the given start matrix
     * @param goal - goal state st to the given goal matrix
     */

    public void BFS(String start[][], String goal[][], boolean open, boolean time) throws InterruptedException {

        long startTime = System.currentTimeMillis();
        Vertex startV = new Vertex(start);
        Vertex goalV = new Vertex(goal);
        Queue<Vertex> L = new LinkedList<Vertex>();
        L.add(startV);
        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
        Hashtable<String, Vertex> closed_list = new Hashtable<String, Vertex>();
        open_list.put(startV.getString_of_matrix(), startV);                            //insert the first element to the queue
        int iter = 0;

        while (!L.isEmpty()) {                                                          //while the queue isnt empty

            if (open) {                                                                 // if open set to true print the vertexes on the given iteration
                printOpen(open_list, iter);
                iter++;
            }
            Vertex n = L.poll();                                                        // take the first element from the queue
            open_list.remove(n.getMatrix());                                            //remove element from open list
            closed_list.put(n.getString_of_matrix(), n);                                 //insert n to the closed lost
            Operators operator = new Operators();
            List<Vertex>  ver_list = new ArrayList<Vertex>();
            ver_list = operator.allowed_operators(n);
            num_of_vertex_created += ver_list.size();                                           //add the amount of vertexes created to the total amount
            for (Vertex n_op : ver_list) {                                                     //For each allowed operator on n
                String vertex = operator.matrix_toString(n_op.getMatrix());
                if (!closed_list.containsKey(vertex) && !open_list.containsKey(vertex)) {
                    if (Compere_Matrix(n_op.getMatrix(), goalV.getMatrix())) {               //if we got to the goal
                        long endTime = System.currentTimeMillis();
                        long timeElapsed = endTime - startTime;
                        PrintOutput(n_op.getPath(), num_of_vertex_created, n_op.getDepth_cost(), time, timeElapsed);
                        return;
                    } else {
                        L.add(n_op);
                        open_list.put(n_op.getString_of_matrix(), n_op);                       //add to the open list
                    }
                }
            }
        }
        PrintOutputNoPath(num_of_vertex_created);                                        //write to file- no path found
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////// DFID ///////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static boolean is_cutoff;

    /**
     * DFID algorithm calculated from start to goal
     * @param start - start state set to the given start matrix
     * @param goal - goal state st to the given goal matrix
     */

    public void DFID(String start[][], String goal[][], boolean open, boolean time) {
        long startTime = System.currentTimeMillis();

        Vertex startV = new Vertex(start);
        Vertex goalV = new Vertex(goal);
        int num_of_vertex_created = 1;

        for (int depth = 1; depth < Integer.MAX_VALUE; depth++) {                                            //start at depth = 1
            Hashtable<String, Vertex> branch_vertex = new Hashtable<String, Vertex>();
            Vertex answer = new Vertex(Limited_DFS(startV, goalV, depth, branch_vertex, startTime, time, open));
            if (open) {                                                                                     // if open set to true print the vertexes on the given iteration
                printOpen(branch_vertex, iter);
                iter++;
            }
            if (!is_cutoff) {
                break;
            } else if (answer.getDepth_cost() == -1) {                                                    // depth (-1) => got fail
                PrintOutputNoPath(num_of_vertex_created);
                break;
            }
        }
    }


    private Vertex Limited_DFS(Vertex n, Vertex goalV, int limit, Hashtable<String, Vertex> branch_vertex, long startTime, boolean time, boolean open) {

        if (Compere_Matrix(n.getMatrix(), goalV.getMatrix())) {
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            PrintOutput(n.getPath(), num_of_vertex_created, n.getDepth_cost(), time, timeElapsed);
            return n;
        }
        else if (limit == 0) {
            //Vertex ver = new Vertex(n);                                             //if the limit = 0
            //ver.setCutoff(-2);
            n.setCutoff(-2);
            return n;
        } else {                                                                            //start checking for all operators

            if (open) {                                                                     // if open set to true print the vertexes on the given iteration
                printOpen(branch_vertex, iter);
                iter++;
            }
            branch_vertex.put(n.getString_of_matrix(), n);
            is_cutoff = false;
            Operators operator = new Operators();
            List<Vertex>  ver_list = new ArrayList<Vertex>();
            ver_list = operator.allowed_operators(n);
            num_of_vertex_created += ver_list.size();                                              //add the amount of vertexes created to the total amount
            for (Vertex n_op : ver_list) {                                                       // For each allowed operator on n
                //num_of_vertex_created++;                                                         //add the amount of vertexes created to the total amount
                String ver_string = operator.matrix_toString(n_op.getMatrix());
                if (!branch_vertex.containsKey(ver_string)) {
                    Vertex result = (Limited_DFS(n_op, goalV, limit - 1, branch_vertex, startTime, time, open));
                    if (result.getCutoff() == -2) {
                        is_cutoff = true;
                    } else if (result.getDepth_cost() != -1) {
                        return result;
                    }
                }
            }
            branch_vertex.remove(n.getString_of_matrix());
            if (is_cutoff) {
                n.setCutoff(-2);
                return n;
            } else {
                n.setDepth_cost(-1);
                return n;
            }
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////A*///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    static int i = 0;

    /**
     * an A* algorithm calculated in reverse - from goal to start
     * @param start - start state set to the given goal matrix
     * @param goal - goal state set to the given start matrix
     */

    public void ASTAR(String start[][], String goal[][], boolean open, boolean time) {
        long startTime = System.currentTimeMillis();

        Vertex startV = new Vertex(goal);                                        //set the start vertex to the end goal
        Vertex goalV = new Vertex(start);                                        //set the goal vertex to the start
        VertexComparator priority_f = new VertexComparator();
        PriorityQueue<Vertex> L = new PriorityQueue<>(5, priority_f);      //PriorityQueue uses costume compare method that compares by f(n) value or creation order or by steps hierarchy
        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
        Hashtable<String, Vertex> closed_list = new Hashtable<String, Vertex>();
        open_list.put(startV.getString_of_matrix(), startV);//insert the first element to the queue
        L.add(startV);
        startV.setId(++i);

        while (!L.isEmpty()) {

            if (open) {                                                             // if open set to true print the vertexes on the given iteration
                printOpen(open_list, iter);
                iter++;
            }
            ID++;
            Vertex n = L.poll();
            open_list.remove(n.getString_of_matrix());
            if (Compere_Matrix(n.getMatrix(), goalV.getMatrix())) {
                long endTime = System.currentTimeMillis();
                long timeElapsed = endTime - startTime;
                PrintReversedOutput(n.getPath(), num_of_vertex_created, n.getDepth_cost(), time, timeElapsed);
                return;
            } else {
                closed_list.put(n.getString_of_matrix(), n);
                Operators operator = new Operators();

                List<Vertex>  ver_list = new ArrayList<Vertex>();
                ver_list = operator.allowed_operators(n);
                num_of_vertex_created += ver_list.size();                       //add the amount of vertexes created to the total amount

                for (Vertex n_op : ver_list) {                                  // For each allowed operator on n
                   n_op.setId(ID);
                    if (!closed_list.containsKey(n_op.getString_of_matrix()) && !open_list.containsKey(n_op.getString_of_matrix())) {
                        n_op.setH_cost(calculateManhattanDistance(n_op, goalV));
                        n_op.setTotal_heuristic_cost();
                        L.add(n_op);
                        open_list.put(n_op.getString_of_matrix(), n_op);
                    } else {
                        if (open_list.containsKey(n_op.getString_of_matrix())) {                // if the open list contains a vertex that his matrix is equal
                            Vertex tmp = in_queue(L, n_op);                                   // find the vertex in L that has the same matrix
                            n_op.setH_cost(calculateManhattanDistance(n_op, goalV));
                            n_op.setTotal_heuristic_cost();
                            if (n_op.getTotal_heuristic_cost() < tmp.getTotal_heuristic_cost()) {
                                open_list.remove(tmp);
                                L.remove(tmp);
                                L.add(n_op);
                                open_list.put(n_op.getString_of_matrix(), n_op);
                            }
                        }
                    }
                }
            }
        }
        PrintOutputNoPath(num_of_vertex_created);                                        //write to file- no path found
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////ManhattanDistance///////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * calculate the h value of a given vertex
     * @param v - current vertex
     * @param goal - goal vertex state
     */
    private int calculateManhattanDistance(Vertex v, Vertex goal) {
        double manhattanDistanceSum = 0;
        int[] empty1 = v.getEmpty_block1();                                     //get the indexes of the first empty block
        int[] empty2 = v.getEmpty_block2();                                     //get the indexes of the second empty block
        int row1 = empty1[0];
        int col1 = empty1[1];
        int row2 = empty2[0];
        int col2 = empty2[1];
        for (int i = 0; i < goal.getMatrix().length; i++)                                        //go throw the goal matrix
            for (int j = 0; j < goal.getMatrix()[0].length; j++) {
                String value = goal.getMatrix()[i][j];                                     //get the value of the goal state at i,j
                if (!v.getMatrix()[i][j].equals(goal.getMatrix()[i][j])) {              // if the value in current vertex at [i][j] is different from the value in  goal[i][j]
                    outerloop:
                    for (int k = 0; k < v.getMatrix().length; k++) {                   //go throw the current vertex block by block until you find "value"
                        for (int l = 0; l < v.getMatrix()[0].length; l++) {
                            if (v.getMatrix()[k][l].equals(value)) {
                                if (value.equals("_")) {                              //skeep the "_"
                                    break outerloop;
                                }
                                if (v.getNum_of_empty_block() == 2) {                    //if there are 2 empty block
                                    if ((row1 == row2 && (col1+1) == col2) || ((row1+1) == row2 && col1 == col2)) {               //check if they are Adjacent
                                        manhattanDistanceSum += ((Math.abs(i - k)*(3)) + (Math.abs(j - l)*3.5)) ;                 //if [][] => (i-k) =0 so multiply by cost =  6/2 =3
                                        break outerloop;
                                    }
                                    else {
                                        manhattanDistanceSum += (Math.abs(i - k) + Math.abs(j - l)) * 5;                     //if [] => (j-l) =0 so multiply by cost = 7/2 =3.5
                                        break outerloop;                                                                     //   []
                                    }
                                }
                                else {                                                                                      // if only one empty block multiply by the cost =5
                                    manhattanDistanceSum += (Math.abs(i - k) + Math.abs(j - l)) * 5;
                                    break outerloop;
                                }
                            }
                        }
                    }
                }
            }
        return (int) manhattanDistanceSum;
    }

    /**
     * given a PriorityQueue and a vertex. checks if the queue contains a vertex with the same matrix values.
     * @return if there is a vertex returns that vertex otherwise return null
     */
    private Vertex in_queue(PriorityQueue<Vertex> p, Vertex ver) {
        for (Vertex v : p) {
            if (v.getString_of_matrix().equals(ver.getString_of_matrix())) {
                return v;
            }
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////IDAstar///////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * an IDAstar algorithm calculated in reverse - from goal to start
     * @param start - start state set to the given goal matrix
     * @param goal - goal state set to the given start matrix
     */

    public void IDAstar(String start[][], String goal[][], boolean open, boolean time) {
        long startTime = System.currentTimeMillis();

        Vertex startV = new Vertex(goal);
        Vertex goalV = new Vertex(start);
        Stack<Vertex> L = new Stack<Vertex>();
        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
        startV.setH_cost(calculateManhattanDistance(startV, goalV));
        double t = startV.getH_cost();                                                                          // set threshold to the start vertex h cost
        while (t != Double.MAX_VALUE) {
            double min_f = Double.MAX_VALUE;                                                                    // set threshold to max value
            startV.setOut(false);
            L.push(startV);
            open_list.put(startV.getString_of_matrix(), startV);

            while (!L.isEmpty()) {
                if (open) {                                                                                         // if open set to true print the vertexes on the given iteration
                    printOpen(open_list, iter);
                    iter++;
                }

                ID++;
                Vertex n = L.pop();
                if (n.isOut()) {
                    open_list.remove(n.getString_of_matrix(), n);
                } else {
                    n.setOut(true);
                    L.push(n);
                    Operators operator = new Operators();

                    List<Vertex>  ver_list = new ArrayList<Vertex>();
                    ver_list = operator.allowed_operators(n);
                    num_of_vertex_created += ver_list.size();                                                       //add the amount of vertexes created to the total amount

                    for (Vertex n_op : ver_list) {                                                                  // For each allowed operator on n
                        n_op.setId(ID);
                        n_op.setH_cost(calculateManhattanDistance(n_op, goalV));
                        n_op.setTotal_heuristic_cost();

                        if (n_op.getTotal_heuristic_cost() > t) {
                            min_f = Math.min(min_f, n_op.getTotal_heuristic_cost());
                            continue;
                        }
                        Vertex tmp = open_list.get(n_op.getString_of_matrix());

                        if (open_list.containsKey(n_op.getString_of_matrix()) && tmp.isOut() == true) {
                            continue;
                        }
                        if (open_list.containsKey(n_op.getString_of_matrix()) && tmp.isOut() == false) {
                            tmp.setH_cost(calculateManhattanDistance(tmp, goalV));
                            tmp.setTotal_heuristic_cost();
                            if (tmp.getTotal_heuristic_cost() > n_op.getTotal_heuristic_cost()) {
                                L.remove(tmp);
                                open_list.remove(tmp.getString_of_matrix(), tmp);
                            } else {
                                continue;
                            }
                        }
                        if (Compere_Matrix(n_op.getMatrix(), goalV.getMatrix())) {
                            long endTime = System.currentTimeMillis();
                            long timeElapsed = endTime - startTime;
                            PrintReversedOutput(n_op.getPath(), num_of_vertex_created, n_op.getDepth_cost(), time, timeElapsed);
                            return;
                        }
                        L.push(n_op);
                        open_list.put(n_op.getString_of_matrix(), n_op);
                    }
                }
            }
            startV.setOut(false);
            t = min_f;
        }
        PrintOutputNoPath(num_of_vertex_created);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////DFBNB///////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * an DFBnB algorithm calculated in reverse - from goal to start
     * @param start - start state set to the given goal matrix
     * @param goal - goal state set to the given start matrix
     */

    public void DFBnB(String start[][], String goal[][], boolean open, boolean time) {
        long startTime = System.currentTimeMillis();

        Vertex startV = new Vertex(goal);
        Vertex goalV = new Vertex(start);
        Stack<Vertex> L = new Stack<Vertex>();
        Hashtable<String, Vertex> open_list = new Hashtable<String, Vertex>();
        VertexComparator priority_f = new VertexComparator();
        Vertex result = null;

        double t = Double.MAX_VALUE;
        L.push(startV);
        open_list.put(startV.getString_of_matrix(), startV);

        while (!L.isEmpty()) {
            if (open) {                                                                                         // if open set to true print the vertexes on the given iteration
                printOpen(open_list, iter);
                iter++;
            }

            Vertex n = L.pop();
            if (n.isOut()) {
                open_list.remove(n.getString_of_matrix());
            } else {
                n.setOut(true);
                L.push(n);
                Operators operator = new Operators();
                List<Vertex>  ver_list = new ArrayList<Vertex>();
                ver_list = operator.allowed_operators(n);
                num_of_vertex_created += ver_list.size();                                                            //add the amount of vertexes created to the total amount

                for (Vertex n_op : ver_list) {                                                                      // For each allowed operator on n set the f cost
                    n_op.setH_cost(calculateManhattanDistance(n_op, goalV));
                    n_op.setTotal_heuristic_cost();
                }

                Collections.sort(ver_list, priority_f);                                                             //sort the vertexes according to their f value
                ListIterator<Vertex> itr = ver_list.listIterator();

                while (itr.hasNext()){                                                                              //For each node g from ver_list according to the order of in ver_list
                    Vertex g = itr.next();
                    if (g.getTotal_heuristic_cost() >= t) {
                        itr.remove();
                        while(itr.hasNext() && ver_list.contains(itr.next())){                                     //remove g and all the nodes after it from the sorted list
                            itr.remove();
                        }
                    } else if (open_list.containsKey(g.getString_of_matrix()) && open_list.get(g.getString_of_matrix()).isOut()) {
                        itr.remove();
                    } else if (open_list.containsKey(g.getString_of_matrix()) && open_list.get(g.getString_of_matrix()).isOut() == false) {
                        if (open_list.get(g.getString_of_matrix()).getTotal_heuristic_cost() <= g.getTotal_heuristic_cost()) {
                            itr.remove();
                        } else {
                            L.remove(g);
                            open_list.remove(g.getString_of_matrix());
                        }
                    } else if (Compere_Matrix(g.getMatrix(), goalV.getMatrix())) {
                        t = (int) g.getTotal_heuristic_cost();
                        result = g;
                        while(itr.hasNext() && ver_list.contains(itr.next())){
                            itr.remove();
                        }
                    }
                }
                                                                                            //insert ver_list in a reverse order to L and H
                for (int i = ver_list.size() -1 ; i >=0 ; i--) {
                    L.push(ver_list.get(i));
                    open_list.put(ver_list.get(i).getString_of_matrix(), ver_list.get(i));
                    ver_list.remove(ver_list.get(i));
                }
            }
        }
        if (result != null) {
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;
            PrintReversedOutput(result.getPath(), num_of_vertex_created, result.getDepth_cost(), time, timeElapsed);

        }else{
            PrintOutputNoPath(num_of_vertex_created);
        }

    }


}