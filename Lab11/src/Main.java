// A0189893B Lee Jun Hui, Sean
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        double sourceX  = fio.nextDouble();
        double sourceY = fio.nextDouble();
        double destX = fio.nextDouble();
        double destY = fio.nextDouble();
        int cannons = fio.nextInt();

        // Initialise DataStructure to store coordinates of cannons, source, and dest
        CannonGrid cg = new CannonGrid(cannons+2);
        cg.coords[0][0] = sourceX;
        cg.coords[0][1] = sourceY;
        cg.coords[1][0] = destX;
        cg.coords[1][1] = destY;

        // Store Coordinates of Cannons (Vertex)
        for(int i = 2; i<=cannons+1; i++){ // O(V)
            cg.coords[i][0] = fio.nextDouble();
            cg.coords[i][1] = fio.nextDouble();
        }

        // Model the Graph. Vertices Refer to Source, Destination, and Cannons
        // Edges refer to the time taken to travel from one vertex to another
        // Problem is a SSSP Problem - Use Bellman Ford O(VE) since n = 100 is relatively small;

        for(int j = 0; j<cannons+2; j++) { // O(V^2 / 2)
            double curX = cg.coords[j][0];
            double curY = cg.coords[j][1];
            for(int k=0; k< cannons+2; k++) {
                double Xdiff = cg.coords[k][0] - curX;
                double Ydiff = cg.coords[k][1] - curY;
                double dist = Math.sqrt(Math.pow(Xdiff,2) + Math.pow(Ydiff,2));

                if(j == 0 | j ==  1) {
                    cg.matrix[j][k] = dist / 5;
                } else {
                    if(dist <=30) {
                        cg.matrix[j][k] = dist/5;
                    } else {
                        double time = 2;
                        dist -= 50;
                        time += (Math.max(-1*dist, dist))/ 5;
                        cg.matrix[j][k] = time;
                    }
                }
            }
        }

        //Bellman Ford Algorithm
        for(int i = 0; i< cannons +1; i++) {
            for(int j = 0; j <cannons+2; j++) { // loop through edges
                for (int k =0; k<cannons+2; k++){
                    int u = j;
                    int v = k;
                    double timeTaken = cg.matrix[u][v];
                    if(cg.timeTaken[u] != cg.INF && cg.timeTaken[u] + timeTaken < cg.timeTaken[v]) {
                        cg.timeTaken[v] = cg.timeTaken[u] + timeTaken;
                    }
                }
            }
        }
        
        fio.println(cg.timeTaken[1]);
        fio.flush();
    }
}

class CannonGrid {
    public static int INF = 1000000000;
    int V;
    double[][] matrix,coords;
    double[] timeTaken;

    public CannonGrid(int M){
        this.V = M;
        this.matrix = new double[M][M];
        this.coords = new double[M][2];
        this.timeTaken = new double[M];
        timeTaken[0] = 0;
        for(int i = 1; i < M; i++) {
            timeTaken[i] = CannonGrid.INF;
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