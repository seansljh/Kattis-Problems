import java.util.*;
import java.io.*;

public class Main {
    static int curSetNum;
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int rows  = fio.nextInt();
        int cols = fio.nextInt();

        //Initialise World Object
        World w = new World(rows, cols);

        // Store information
        for(int i =0; i<rows; i++){ //store values
            String row_input = fio.nextLine();
            for(int j = 0; j <cols; j++) {
                w.matrix[i][j] =  row_input.charAt(j);
                w.visited[i][j] = 0;
                w.belongs[i][j] = -1;
            }
        }

        // Preprocess World
        Main.curSetNum = -1;
        for(int k = 0 ; k<rows; k++) { //ensures we loop even the disjointed graphs
            for(int l = 0; l < cols; l++) {
                if(w.visited[k][l] == 0) { //if unvisited
                    curSetNum++; //increment the curSetNum as it means that a new set is now being traversed
                    w.DFSrec(k, l, curSetNum);
                }
            }
        }

        // Take in Queries and return output
        int num_queries = fio.nextInt();
        for(int q = 0; q<num_queries; q++) {
            int r1 = fio.nextInt() -1;
            int c1 = fio.nextInt() -1;
            int r2 = fio.nextInt() -1;
            int c2 = fio.nextInt() -1;
            if(w.matrix[r1][c1] == '1') { //decimal
                if(w.belongs[r1][c1] == w.belongs[r2][c2]) { //if same set
                    fio.println("decimal");
                    continue;
                }
            } else { //binary
                if(w.belongs[r1][c1] == w.belongs[r2][c2]) { //if same set
                    fio.println("binary");
                    continue;
                }
            }
            fio.println("neither");
        }
        fio.flush();
    }
}

class World {
    int rows, cols,min;
    int[][] visited;
    int[][] belongs;
    int[][] matrix;

    public World(int rows, int cols){
        this.rows = rows-1;
        this.cols = cols-1;
        this.belongs = new int[rows][cols];
        this.visited = new int[rows][cols];
        this.matrix = new int[rows][cols];
    }

    public void DFSrec(int r, int c, int curSetNum) {
        this.visited[r][c] = 1; // set vertex as visited
        this.belongs[r][c] = curSetNum; // set belongs to the number of the current set
        int value = this.matrix[r][c];
        int up = r-1;
        int down = r+1;
        int left = c-1;
        int right =  c+1;
        if(up>=0 && this.visited[up][c] == 0 && (this.matrix[up][c] == value)){
            this.DFSrec(up,c, curSetNum);
        }
        if(left>=0 && this.visited[r][left] == 0 && (this.matrix[r][left]==value)){
            this.DFSrec(r, left,curSetNum);
        }
        if(right <= this.cols && this.visited[r][right] == 0 && (this.matrix[r][right]== value)){
            this.DFSrec(r, right, curSetNum);
        }
        if(down <= this.rows && this.visited[down][c] == 0 && (this.matrix[down][c]== value)){
            this.DFSrec(down, c,curSetNum);
        }
    }
}

class FastIO extends PrintWriter
{
    BufferedReader br;
    StringTokenizer st;

    public FastIO()
    {
        super(new BufferedOutputStream(System.out));
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}