// A0189893B Lee Jun Hui, Sean
import java.util.*;
import java.io.*;

// Use Kruskal's Algorithm to Reconstruct the MST

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        int count = 0;

        int villages = fio.nextInt();
        for(int i =0; i<villages; i++) {
            for(int j=0; j<villages; j++) {
                if(j>i) {
                    edges.add(new Edge(fio.nextInt(), i, j));
                } else{
                    fio.nextInt();
                }
            }
        }

        UFDS uf = new UFDS(villages*villages);
        while(count < villages-1){
            Edge e = edges.poll();
            if(!uf.isSameSet(e.source(), e.dest())) {
                count++;
                uf.unionSet(e.source(), e.dest());
                fio.print(e.source() + 1);
                fio.print(" ");
                fio.print(e.dest() +1);
                fio.println();
            }
        }
        fio.flush();
    }
}

class Edge implements Comparable<Edge> {
    public Integer weight, source, dest;

    public Edge(Integer w, Integer s, Integer d) {
        this.weight = w;
        this.source = s;
        this.dest = d;
    }

    public int compareTo(Edge o) {
        if (!this.weight().equals(o.weight()))
            return this.weight() - o.weight();
        else if (!this.source().equals(o.source()))
            return this.source() - o.source();
        else
            return this.dest() - o.dest();
    }

    Integer weight() { return this.weight; }
    Integer source() { return this.source; }
    Integer dest() { return this.dest; }
}

class UFDS {
    public int[] p;
    public int[] rank;
    public int[] setSize;
    public int numSets;

    public UFDS(int N) {
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[i] = 1;
        }
    }

    public int findSet(int i) {
        if (p[i] == i) return i;
        else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                setSize[x] = setSize[x] + setSize[y];
            }
            else {
                p[x] = y;
                setSize[y] = setSize[x] + setSize[y];
                if (rank[x] == rank[y])
                    rank[y] = rank[y]+1;
            }
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
