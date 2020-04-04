import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int rows  = fio.nextInt();
        int cols = fio.nextInt();
        int count = 0;
        //Initialise Picture
        Picture p = new Picture(rows, cols);

        // Store information
        for(int i =0; i<rows; i++){ //store values
            String row_input = fio.nextLine();
            for(int j = 0; j <cols; j++) {
                p.matrix[i][j] =  row_input.charAt(j);
                p.visited[i][j] = 0;
            }
        }

        for(int k = 0 ; k<rows; k++) { //ensures we loop even the disjointed graphs
            for(int l = 0; l < cols; l++) {
                if(p.matrix[k][l] == 'L' && p.visited[k][l] == 0) {
                    p.DFSrec(k, l);
                    count++;
                }
            }
        }
        fio.println(count);
        fio.flush();
    }
}

class Picture {
    int rows, cols,min;
    int[][] visited;
    char[][] matrix;

    public Picture(int rows, int cols){
        this.rows = rows-1;
        this.cols = cols-1;
        this.visited = new int[rows][cols];
        this.matrix = new char[rows][cols];
    }

    public void DFSrec(int r, int c) {
        this.visited[r][c] = 1; // set vertex as visited
        int up = r-1;
        int down = r+1;
        int left = c-1;
        int right =  c+1;
        if(up>=0 && this.visited[up][c] == 0 && ((this.matrix[up][c]=='L') || (this.matrix[up][c]=='C'))){
            this.DFSrec(up,c);
        }
        if(left>=0 && this.visited[r][left] == 0 && ((this.matrix[r][left]=='L') || (this.matrix[r][left]=='C'))){
            this.DFSrec(r, left);
        }
        if(right <= this.cols && this.visited[r][right] == 0 && ((this.matrix[r][right]=='L') || (this.matrix[r][right]=='C'))){
            this.DFSrec(r, right);
        }
        if(down <= this.rows && this.visited[down][c] == 0 && ((this.matrix[down][c]=='L') || (this.matrix[down][c]=='C'))){
            this.DFSrec(down, c);
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