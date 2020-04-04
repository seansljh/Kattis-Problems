import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int vertices  = fio.nextInt();
        while (vertices != -1) {
            int[][] matrix  = new int[vertices][vertices];
            for(int i =0; i<vertices; i++){ //store values
                for(int j = 0; j <vertices; j++) {
                    matrix[i][j] =  fio.nextInt();
                }
            }
            boolean[] marker = new boolean[vertices];
            for(int j=0; j< vertices; j++) { //loop row
                for (int k = 0 ; k < vertices; k++) { //loop to find those 1s
                    int to_check = matrix[j][k];
                    if(vertices ==1 && to_check == 0) {
                        marker[j] = false;
                    } else if (to_check ==  1) {
                        for (int l = k+1; l < vertices; l++) { // find the other 1s after finding a 1
                            int inner_check = matrix[j][l];
                            if (inner_check == 1){
                                if(matrix[k][l] == 1) {
                                    marker[j] = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for(int b=0; b<vertices; b++) {
                if(!marker[b]){
                    fio.print(b);
                    fio.print(" ");
                }
            }
            fio.println();
            vertices = fio.nextInt();
        }
        fio.flush();
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