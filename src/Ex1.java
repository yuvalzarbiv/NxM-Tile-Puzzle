import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ex1 {

    static String use_algo = "";
    static boolean with_time = false;
    static boolean with_open = false;
    static int n;
    static int m;
    static String string_input_mat[][];
    static  String goal_mat [][];


    public static void ReadFile (File input_file) throws FileNotFoundException {

        Scanner scnr = new Scanner(input_file);
        int lineNumber = 1;
        use_algo = scnr.nextLine();
        String str_with_time = scnr.nextLine();
        if (str_with_time.equals("with time")) {
            with_time = true;
        }
        String str_with_open = scnr.nextLine();
        if(str_with_open.equals("with open")){
            with_open = true;
        }
        String nxm = scnr.nextLine();
        String[] length_width = nxm.split("x");
        n = Integer.parseInt(length_width[0]);
        m = Integer.parseInt(length_width[1]);

        //fill the string input matrix
        string_input_mat = new String [n][m];
        for(int i = 0; i < n; i++){
            String line = scnr.nextLine();              //read next line
            String[] split_line = line.split(",");   //split input by ,
            for (int j = 0; j < m ; j++){
                string_input_mat[i][j] = split_line[j];        //fill the line in the mat
            }
        }

        scnr.nextLine(); //skeep the "Goal state" line in the input file

        //fill the string goal matrix
        goal_mat = new String[n][m];
        for(int i = 0; i < n; i++){
            String line = scnr.nextLine();              //read next line
            String[] split_line = line.split(",");   //split input by ,
            for (int j = 0; j < m ; j++){
                goal_mat[i][j] = split_line[j];        //fill the line in the mat
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        File file = new File("input.txt"); // file name
        ReadFile(file);
        Algorithms algo = new Algorithms();
        switch (use_algo) {

            case "BFS":
                algo.BFS(string_input_mat, goal_mat, with_open, with_time);
                break;

            case "DFID":
                algo.DFID(string_input_mat, goal_mat, with_open, with_time);
                break;

            case "A*":
                algo.ASTAR (string_input_mat, goal_mat, with_open, with_time);
                break;

            case "IDA*":
                algo.IDAstar(string_input_mat, goal_mat, with_open, with_time);
                break;

            case "DFBnB":
                algo.DFBnB(string_input_mat, goal_mat, with_open, with_time);
                break;
        }
    }



}






