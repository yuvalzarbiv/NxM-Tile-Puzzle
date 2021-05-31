import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Operators {

    /**
     * given a string matrix outputs a string of the matrix values
     */

    public String matrix_toString(String[][] mat) {
        String matrix_str = "";

        for (int i = 0; i < mat.length; i++) {     //find the location of the empty block
            for (int j = 0; j < mat[0].length; j++) {
                matrix_str += mat[i][j];
            }
        }
        return matrix_str;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////check for 2 empty blocks ///////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * checks if a specific move is allowed for a given vertex
     * @param ver
     * @return boolean
     */

    public boolean  Check_LL(Vertex ver){
        if(ver.getEmpty_block1()[1] == ver.getEmpty_block2()[1]) {                            //             []
            if ((ver.getMatrix()[0].length-1 - ver.getEmpty_block1()[1]) >= 1) {           //if the blocks are [], check for a gap of at least 1 block
                return true;
            } else return false;
        }else return false;
    }

    public boolean  Check_UU(Vertex ver) {
        if (ver.getEmpty_block1()[0] == ver.getEmpty_block2()[0]) {                        // if the blocks are [][]
            if (ver.getEmpty_block1()[0] != ver.getMatrix().length-1) {
                return true;
            } else return false;
        }else return false;
    }

    public boolean  Check_RR(Vertex ver){
        if(ver.getEmpty_block1()[1] == ver.getEmpty_block2()[1]) {                           //             []
            if (ver.getEmpty_block1()[1] >= 1){                                         //if the blocks are [], check for a gap of at least 1 block
                return true;
            }else return false;
        }else return false;
    }

    public boolean  Check_DD(Vertex ver){
        if (ver.getEmpty_block1()[0] == ver.getEmpty_block2()[0]) {                        // if the blocks are [][]
            if (ver.getEmpty_block1()[0] >= 1) {
                return true;
            } else return false;
        }else return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////// check for 1 empty blocks //////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * checks if a specific move is allowed for a given vertex
     * @param ver
     * @return boolean
     */

    public boolean  Check_L(Vertex ver,  int [] empty_block){
        if((empty_block[1] !=ver.getMatrix()[0].length-1) && !ver.getMatrix()[empty_block[0]][empty_block[1]+1].equals("_")) {
            return true;
        }
        else return false;
    }

    public boolean  Check_U(Vertex ver, int [] empty_block ){
        if(empty_block[0] != ver.getMatrix().length-1 && !ver.getMatrix()[empty_block[0]+1][empty_block[1]].equals("_")) return true;
        else return false;
    }

    public boolean  Check_R(Vertex ver, int [] empty_block){
        if(empty_block[1] != 0 && !ver.getMatrix()[empty_block[0]][empty_block[1]-1].equals("_")) return true;
        else return false;
    }

    public boolean  Check_D(Vertex ver, int [] empty_block){
        if(empty_block[0] != 0 && !ver.getMatrix()[empty_block[0]-1][empty_block[1]].equals("_")) return true;
        else return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////// vertex operators ///////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * given a vertex, returns a list of vertexes after preforming allowed operators
     * @param n
     * @return   list of allowed operator on vertex n
     */

    public List<Vertex> allowed_operators(Vertex n) {

        List<Vertex> op_arr = new ArrayList<Vertex>();
        Operators operator = new Operators();

        if (n.getNum_of_empty_block() == 2 && Adjacent_blocks(n)) {                                                     //checks if there are 2 empty blocks and if they are adjacent -for double steps
            if (operator.Check_LL(n) && n.getPrevious_move()[0] != "R" && n.getPrevious_move()[1] != "R") {             //check that the move is possible and for previous move
                int i1 = n.getEmpty_block1()[0];                                                                        //get the indexes of the empty blocks
                int j1 = n.getEmpty_block1()[1];
                int i2 = n.getEmpty_block2()[0];
                int j2 = n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();                                                           //create a new string matrix and initialize it to the given vertex matrix
                String value1 = String.valueOf(n.getMatrix()[i1][j1+1]);                                                //get the values of the blocks to be moved
                String value2 = String.valueOf(n.getMatrix()[i2][j2+1]);
                changed_mat[i1][j1] = value1;                                                                           //switch between the values and the empty blocks
                changed_mat[i2][j2] = value2;
                changed_mat[i1][j1+1] = "_";
                changed_mat[i2][j2+1] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {                                                    //check that the new matrix isn't equal to the previous one (checks that we are not going back)
                    Vertex new_ver = new Vertex(n);                                                                     //creates a new vertex
                    new_ver.setMatrix(changed_mat);                                                                     //set the new vertexes matrix to the changed matrix
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value1 + "&" + value2 + "L");
                    new_ver.setPath(new_path);                                                                          //update path
                    new_ver.setDepth_cost(n.getDepth_cost() + 6);                                                       //update path cost
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[0] = "L";
                    new_prev[1] = "L";
                    new_ver.setPrevious_move(new_prev);                                                                 // set previous move
                    new_ver.Update_ver();                                                                               //update the empty blocks index
                    new_ver.setParent(n.getMatrix());                                                                   //set parent
                    op_arr.add(new_ver);                                                                                //add to the returned list
                }
            }


            if (operator.Check_UU(n) && n.getPrevious_move()[0] != "D" && n.getPrevious_move()[1] != "D") {             //check that the move is possible and for previous move
                int i1 = n.getEmpty_block1()[0];                                                                        //get the indexes of the empty blocks
                int j1 = n.getEmpty_block1()[1];
                int i2 = n.getEmpty_block2()[0];
                int j2 = n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();                                                           //create a new string matrix and initialize it to the given vertex matrix
                String value1 = String.valueOf(n.getMatrix()[i1+1][j1]);                                                //get the values of the blocks to be moved
                String value2 = String.valueOf(n.getMatrix()[i2+1][j2]);
                changed_mat[i1][j1] = value1;                                                                           //switch between the values and the empty blocks
                changed_mat[i2][j2] = value2;
                changed_mat[i1+1][j1] = "_";
                changed_mat[i2+1][j2] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {                                                    //check that the new matrix isn't equal to the previous one (checks that we are not going back)
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value1 + "&" + value2 + "U");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 7);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[0] = "U";
                    new_prev[1] = "U";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
            if (operator.Check_RR(n) && n.getPrevious_move()[0] != "L" && n.getPrevious_move()[1] != "L") {             //check that the move is possible and for previous move
                int i1 = n.getEmpty_block1()[0];                                                                        //get the indexes of the empty blocks
                int j1 = n.getEmpty_block1()[1];
                int i2 = n.getEmpty_block2()[0];
                int j2 = n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();                                                           //create a new string matrix and initialize it to the given vertex matrix
                String value1 = String.valueOf(n.getMatrix()[i1][j1-1]);                                                //get the values of the blocks to be moved
                String value2 = String.valueOf(n.getMatrix()[i2][j2-1]);
                changed_mat[i1][j1] = value1;                                                                           //switch between the values and the empty blocks
                changed_mat[i2][j2] = value2;
                changed_mat[i1][j1-1] = "_";
                changed_mat[i2][j2-1] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {                                                    //check that the new matrix isn't equal to the previous one (checks that we are not going back)
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value1 + "&" + value2 + "R");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 6);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[0] = "R";
                    new_prev[1] = "R";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
            if (operator.Check_DD(n) && n.getPrevious_move()[0] != "U" && n.getPrevious_move()[1] != "U") {             //check that the move is possible and for previous move
                int i1 = n.getEmpty_block1()[0];                                                                        //get the indexes of the empty blocks
                int j1 = n.getEmpty_block1()[1];
                int i2 = n.getEmpty_block2()[0];
                int j2 = n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();                                                           //create a new string matrix and initialize it to the given vertex matrix
                String value1 = String.valueOf(n.getMatrix()[i1-1][j1]);                                                //get the values of the blocks to be moved
                String value2 = String.valueOf(n.getMatrix()[i2-1][j2]);
                changed_mat[i1][j1] = value1;                                                                           //switch between the values and the empty blocks
                changed_mat[i2][j2] = value2;
                changed_mat[i1-1][j1] = "_";
                changed_mat[i2-1][j2] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {                                                    //check that the new matrix isn't equal to the previous one (checks that we are not going back)
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value1 + "&" + value2 + "D");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 7);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[0] = "D";
                    new_prev[1] = "D";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
        }
                                                                                                                        //if there is only one empty block
        if (operator.Check_L(n, n.getEmpty_block1()) && n.getPrevious_move()[0] != "R") {
            int i =  n.getEmpty_block1()[0];                                                                            //get the index of the empty block
            int j =  n.getEmpty_block1()[1];
            String [][] changed_mat = n.VertexToString();                                                               //create a new string matrix and initialize it to the given vertex matrix
            String value = String.valueOf(n.getMatrix()[i][j+1]);                                                       //get the value of the block to be moved
            changed_mat[i][j] = value;                                                                                  //switch between the value and the empty block
            changed_mat[i][j+1] = "_";
            if(!Arrays.deepEquals(n.getParent(), changed_mat)) {                                                        //check that the new matrix isn't equal to the previous one (checks that we are not going back)
                Vertex new_ver = new Vertex(n);                                                                         //creates a new vertex
                new_ver.setMatrix(changed_mat);                                                                         //set the new vertexes matrix to the changed matrix
                String new_path = new_ver.getPath();
                new_path += ("-" + value + "L");
                new_ver.setPath(new_path);                                                                              //update path
                new_ver.setDepth_cost(n.getDepth_cost() + 5);                                                           //update path cost
                String[] new_prev = new_ver.getPrevious_move();
                new_prev[0] = "L";
                new_ver.setPrevious_move(new_prev);                                                                     // set previous move
                new_ver.Update_ver();                                                                                   //update the empty blocks index
                new_ver.setParent(n.getMatrix());                                                                       //set parent
                op_arr.add(new_ver);                                                                                    //add to the returned list
            }
        }
        if (operator.Check_U(n, n.getEmpty_block1()) && n.getPrevious_move()[0] != "D") {
            int i =  n.getEmpty_block1()[0];
            int j =  n.getEmpty_block1()[1];
            String [][] changed_mat = n.VertexToString();
            String value = String.valueOf(n.getMatrix()[i+1][j]);
            changed_mat[i][j] = value;
            changed_mat[i+1][j] = "_";
            if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                Vertex new_ver = new Vertex(n);
                new_ver.setMatrix(changed_mat);
                String new_path = new_ver.getPath();
                new_path += ("-" + value + "U");
                new_ver.setPath(new_path);
                new_ver.setDepth_cost(n.getDepth_cost() + 5);
                String[] new_prev = new_ver.getPrevious_move();
                new_prev[0] = "U";
                new_ver.setPrevious_move(new_prev);
                new_ver.Update_ver();
                new_ver.setParent(n.getMatrix());
                op_arr.add(new_ver);
            }
        }
        if (operator.Check_R(n, n.getEmpty_block1()) && n.getPrevious_move()[0] != "L") {
            int i =  n.getEmpty_block1()[0];
            int j =  n.getEmpty_block1()[1];
            String [][] changed_mat = n.VertexToString();
            String value = String.valueOf(n.getMatrix()[i][j-1]);
            changed_mat[i][j] = value;
            changed_mat[i][j-1] = "_";
            if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                Vertex new_ver = new Vertex(n);
                new_ver.setMatrix(changed_mat);
                String new_path = new_ver.getPath();
                new_path += ("-" + value + "R");
                new_ver.setPath(new_path);
                new_ver.setDepth_cost(n.getDepth_cost() + 5);
                String[] new_prev = new_ver.getPrevious_move();
                new_prev[0] = "R";
                new_ver.setPrevious_move(new_prev);
                new_ver.Update_ver();
                new_ver.setParent(n.getMatrix());
                op_arr.add(new_ver);
            }
        }
        if (operator.Check_D(n, n.getEmpty_block1()) && n.getPrevious_move()[0] != "U") {
            int i =  n.getEmpty_block1()[0];
            int j =  n.getEmpty_block1()[1];
            String [][] changed_mat = n.VertexToString();
            String value = String.valueOf(n.getMatrix()[i-1][j]);
            changed_mat[i][j] = value;
            changed_mat[i-1][j] = "_";
            if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                Vertex new_ver = new Vertex(n);
                new_ver.setMatrix(changed_mat);
                String new_path = new_ver.getPath();
                new_path += ("-" + value + "D");
                new_ver.setPath(new_path);
                new_ver.setDepth_cost(n.getDepth_cost() + 5);
                String[] new_prev = new_ver.getPrevious_move();
                new_prev[0] = "D";
                new_ver.setPrevious_move(new_prev);
                new_ver.Update_ver();
                new_ver.setParent(n.getMatrix());
                op_arr.add(new_ver);
            }
        }

        //if we have 2 blocks that are not adjacent we need to: first preform the operators for the block closest to the left up side and second preform the same actions for operators for the farthest block
        if (n.getNum_of_empty_block() == 2) {
            //use operators for the second empty block
            if (operator.Check_L(n, n.getEmpty_block2()) && n.getPrevious_move()[1] != "R") {
                int i =  n.getEmpty_block2()[0];
                int j =  n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();
                String value = String.valueOf(n.getMatrix()[i][j+1]);
                changed_mat[i][j] = value;
                changed_mat[i][j+1] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value + "L");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 5);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[1] = "L";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
            if (operator.Check_U(n, n.getEmpty_block2()) && n.getPrevious_move()[1] != "D") {
                int i =  n.getEmpty_block2()[0];
                int j =  n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();
                String value = String.valueOf(n.getMatrix()[i+1][j]);
                changed_mat[i][j] = value;
                changed_mat[i+1][j] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value + "U");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 5);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[1] = "U";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
            if (operator.Check_R(n, n.getEmpty_block2()) && n.getPrevious_move()[1] != "L") {
                int i =  n.getEmpty_block2()[0];
                int j =  n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();
                String value = String.valueOf(n.getMatrix()[i][j-1]);
                changed_mat[i][j] = value;
                changed_mat[i][j-1] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value + "R");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 5);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[1] = "R";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
            if (operator.Check_D(n, n.getEmpty_block2()) && n.getPrevious_move()[1] != "U") {
                int i =  n.getEmpty_block2()[0];
                int j =  n.getEmpty_block2()[1];
                String [][] changed_mat = n.VertexToString();
                String value = String.valueOf(n.getMatrix()[i-1][j]);
                changed_mat[i][j] = value;
                changed_mat[i-1][j] = "_";
                if(!Arrays.deepEquals(n.getParent(), changed_mat)) {
                    Vertex new_ver = new Vertex(n);
                    new_ver.setMatrix(changed_mat);
                    String new_path = new_ver.getPath();
                    new_path += ("-" + value + "D");
                    new_ver.setPath(new_path);
                    new_ver.setDepth_cost(n.getDepth_cost() + 5);
                    String[] new_prev = new_ver.getPrevious_move();
                    new_prev[1] = "D";
                    new_ver.setPrevious_move(new_prev);
                    new_ver.Update_ver();
                    new_ver.setParent(n.getMatrix());
                    op_arr.add(new_ver);
                }
            }
        }
        return op_arr;                                                                          //returns the allowed operators
    }

    /**
     * goven a vertex returns true if the two empty blocks are adjacent. otherwise returns false
     * @param ver
     */
    public boolean Adjacent_blocks(Vertex ver) {
        if (ver.getEmpty_block1()[0] == ver.getEmpty_block2()[0] && (Math.abs(ver.getEmpty_block1()[1] - ver.getEmpty_block2()[1]) == 1)) {  // the empty blocks are [][]
            return true;
        } else if (ver.getEmpty_block1()[1] == ver.getEmpty_block2()[1] && (Math.abs(ver.getEmpty_block1()[0] - ver.getEmpty_block2()[0]) == 1)) {   // the empty blocks are []
            return true;                                                                                                                         //                      []
        } else return false;
    }




}
