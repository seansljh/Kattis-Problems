// A0189893B Lee Jun Hui, Sean
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int M  = fio.nextInt();
        int N = fio.nextInt();

        //Initialise CoinGrid
        CoinGrid cg = new CoinGrid(M,N);
        boolean[][] visited = new boolean[M][N];

        // Store information
        for(int i =0; i<M; i++) { //store values
            for (int j = 0; j < N; j++) {
                cg.matrix[i][j] = fio.nextInt();
                cg.distance[i][j] = CoinGrid.INF;
            }
        }

        // Set distance from source to itself to be 0
        cg.distance[0][0] = 0;

        // Use Modified Dijkstra's Algorithm
        PriorityQueue<IntegerTriplet> pq = new PriorityQueue<>();
        pq.offer(new IntegerTriplet(0,0,0));

        while(!pq.isEmpty()) { //E
            IntegerTriplet cur = pq.poll();
            int row = cur.row();
            int col = cur.col();

            if (row == M - 1 && col == N - 1) {
                break;
            }

            if (visited[row][col] == false) {
                visited[row][col] = true;
                int value = cg.matrix[row][col];
                int up = row - 1;
                int down = row + 1;
                int left = col - 1;
                int right = col + 1; //want the height of that coin mountain - the current height of the mountain, check if it is smaller than ladder
                // update ladder if all 4 directions, the difference is greater than the current ladder int
                // anything below the threshold, enqueue. Else, if all above the threshold, update threshold and enqueue the smallest

                // Get the Edge Weights
                int up_val = cg.INF;
                int down_val = cg.INF;
                int left_val = cg.INF;
                int right_val = cg.INF;

                // Update possible moves with the "extra" height needed to go to the surrounding mountains
                if (up >= 0) {
                    up_val =cg.matrix[up][col] - value;
                    if (up_val < 0) {
                        cg.distance[up][col] = cg.distance[row][col];
                        pq.offer(new IntegerTriplet(cg.distance[up][col],up, col));
                    } else {
                        int height = Math.max(up_val, cg.distance[row][col]);
                        if (cg.distance[up][col] > height) {
                            cg.distance[up][col] = height;
                            pq.offer(new IntegerTriplet(cg.distance[up][col], up, col));
                        }
                    }
                }
                if (down < M) {
                    down_val = cg.matrix[down][col] - value;
                    if (down_val < 0) {
                        cg.distance[down][col] = cg.distance[row][col];
                        pq.offer(new IntegerTriplet(cg.distance[down][col],down, col));
                    } else {
                        int height = Math.max(down_val, cg.distance[row][col]);
                        if (cg.distance[down][col] > height) {
                            cg.distance[down][col] = height;
                            pq.offer(new IntegerTriplet(cg.distance[down][col], down, col));
                        }
                    }
                }
                if (left >= 0) {
                    left_val = cg.matrix[row][left] - value;
                    if (left_val < 0) {
                        cg.distance[row][left] = cg.distance[row][col];
                        pq.offer(new IntegerTriplet(cg.distance[row][left],row, left));
                    } else {
                        int height = Math.max(left_val, cg.distance[row][col]);
                        if (cg.distance[row][left] > height) {
                            cg.distance[row][left] = height;
                            pq.offer(new IntegerTriplet(cg.distance[row][left], row, left));
                        }
                    }
                }
                if (right < N) {
                    right_val = cg.matrix[row][right] - value;
                    if (right_val < 0) {
                        cg.distance[row][right] = cg.distance[row][col];
                        pq.offer(new IntegerTriplet(cg.distance[row][right],row, right));
                    } else {
                        int height = Math.max(right_val, cg.distance[row][col]);
                        if (cg.distance[row][right] > height) {
                            cg.distance[row][right] = height;
                            pq.offer(new IntegerTriplet(cg.distance[row][right], row, right));
                        }
                    }
                }
            }
        }
        fio.println(cg.distance[M-1][N-1]);
        fio.flush();
    }
}

class CoinGrid {
    public static int INF = 1000000000;
    int M,N;
    int[][] matrix,distance;

    public CoinGrid(int M, int N){
        this.M = M;
        this.N = N;
        this.matrix = new int[M][N];
        this.distance = new int[M][N];
    }
}

class IntegerTriplet implements Comparable<IntegerTriplet> {
    Integer dist, row, col;

    public IntegerTriplet(Integer dist, Integer row, Integer col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }

    public int compareTo(IntegerTriplet o) {
        if (!this.dist().equals(o.dist())) {
            return this.dist() - o.dist();
        }else {
            if (!this.row().equals(o.row())) {
                return this.row() - o.row();
            } else {
                return this.col() - o.col();
            }
        }
    }

    Integer dist() { return dist; }
    Integer row() { return row; }
    Integer col() {return col; }
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