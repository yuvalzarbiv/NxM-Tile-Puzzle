public class Vertex{

    private String matrix[][];
    private String path;
    private int[] empty_block1 = new int[2];
    private int[] empty_block2 = new int[2];
    private int num_of_empty_block = 0;
    private int depth_cost;
    private int vertex_cost;
    private String[] previous_move= new String[2];
    private String string_of_matrix = "";
    private double h_cost = 0;
    private double total_heuristic_cost = 0;
    private int id = 0;
    private String[][] parent;
    private boolean out;
    private int cutoff = 0;


    /**
     * constructor: vertex from string matrix
     * @param mat- a string matrix
     */
    public Vertex(String[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        this.parent = new String[n][m];
        this.matrix = new String[n][m];                                     //create new matrix of the same size as the input matrix
        for (int i = 0 ; i < n; i++){                                           //find the location of the empty block
            for (int j = 0; j < m; j++){
                matrix[i][j] = mat[i][j];
                parent[i][j] = "0";
                this.string_of_matrix += matrix[i][j];
                if(matrix[i][j].equals( "_" )){                                             //initialize the index for empty block
                    this.num_of_empty_block++; //count number of empty blocks
                    if(num_of_empty_block == 1) {
                        this.empty_block1[0] = i;
                        this.empty_block1[1] = j;
                    }
                    if(num_of_empty_block == 2) {
                        this.empty_block2[0] = i;
                        this.empty_block2[1] = j;
                    }
                }
            }
        }
        this.path = "";
        this.depth_cost = 0;
        this.vertex_cost =0;

    }
    /**
     * constructor: new vertex from existing vertex
     * @param ver- a string matrix
     */
    public Vertex(Vertex ver){
        boolean flag =false;
        int n = ver.getMatrix().length;
        int m =  ver.getMatrix()[0].length;
        this.matrix = new String[n][m]; //create new matrix of the same size as the input matrix
        this.num_of_empty_block = ver.getNum_of_empty_block();
        for (int i = 0 ; i < n; i++){     //find the location of the empty block
            for (int j = 0; j < m; j++){
                matrix[i][j] =  ver.getMatrix()[i][j];
                this.string_of_matrix += matrix[i][j];
                if(matrix[i][j].equals("_" )){
                    if(!flag) {
                        this.empty_block1[0] = i;
                        this.empty_block1[1] = j;
                        flag = true;
                    }
                    if(num_of_empty_block == 2) {
                        this.empty_block2[0] = i;
                        this.empty_block2[1] = j;
                    }
                }
            }
        }
        this.path = ver.getPath();
        this.depth_cost = ver.getDepth_cost();
        this.vertex_cost = 0;

    }

    /**
     * update the string of matrix and the empty block index
     */
    public void Update_ver (){
        boolean flag =false;
        this.string_of_matrix="";
        for (int i = 0 ; i < this.getMatrix().length; i++){     //find the location of the empty block
            for (int j = 0; j < this.getMatrix()[0].length; j++){
                this.string_of_matrix += this.getMatrix()[i][j];
                if(this.getMatrix()[i][j].equals("_" )){
                    if(!flag) {
                        this.empty_block1[0] = i;
                        this.empty_block1[1] = j;
                        flag = true;
                    }
                    if(num_of_empty_block ==2) {
                        this.empty_block2[0] = i;
                        this.empty_block2[1] = j;
                    }
                }
            }
        }
    }

    /**
     * turns a vertex to a string matrix
     * @return
     */
    public String[][] VertexToString() {
        String str_mat[][] = new String[this.matrix.length][this.matrix[0].length];
        for (int i = 0; i <= this.matrix.length-1; i++) {     //find the location of the empty block
            for (int j = 0; j <= this.matrix[0].length-1; j++) {
                str_mat[i][j] = String.valueOf(this.matrix[i][j]);
            }
        }
        return str_mat;
    }

    public String[][] getMatrix() { return matrix; }

    public void setMatrix(String[][] matrix) { this.matrix = matrix; }

    public String getPath() { return path; }

    public void setPath(String path) { this.path = path; }

    public int[] getEmpty_block1() { return empty_block1; }

    public void setEmpty_block1(int[] empty_block1) { this.empty_block1 = empty_block1; }

    public int[] getEmpty_block2() { return empty_block2; }

    public void setEmpty_block2(int[] empty_block2) { this.empty_block2 = empty_block2; }

    public int getNum_of_empty_block() { return num_of_empty_block; }

    public void setNum_of_empty_block(int num_of_empty_block) { this.num_of_empty_block = num_of_empty_block; }

    public int getDepth_cost() { return depth_cost; }

    public void setDepth_cost(int depth_cost) { this.depth_cost = depth_cost; }
    public int getVertex_cost() { return vertex_cost; }

    public void setVertex_cost(int vertex_cost) { this.vertex_cost = vertex_cost; }

    public String getString_of_matrix() { return string_of_matrix; }

    public void setString_of_matrix(String string_of_matrix) { this.string_of_matrix = string_of_matrix; }

    public String[] getPrevious_move() { return previous_move; }

    public void setPrevious_move(String[] previous_move1) { this.previous_move = previous_move1; }

    public double getH_cost() { return h_cost; }

    public void setH_cost(double h_cost) { this.h_cost = h_cost; }

    public double getTotal_heuristic_cost() { return total_heuristic_cost; }

    public void setTotal_heuristic_cost() { this.total_heuristic_cost = this.getDepth_cost() + this.getH_cost(); }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String[][] getParent() { return parent; }

    public void setParent(String[][] parent) { this.parent = parent; }

    public boolean isOut() { return out; }

    public void setOut(boolean out) { this.out = out; }

    public int getCutoff() { return cutoff; }

    public void setCutoff(int cutoff) { this.cutoff = cutoff; }


}


