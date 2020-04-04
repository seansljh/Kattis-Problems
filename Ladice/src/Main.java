import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int items = fio.nextInt();
        int drawers = fio.nextInt();
        UnionFind uf = new UnionFind(drawers);
        for (int j = 0; j < items; j++) {
            int A = fio.nextInt();
            int B = fio.nextInt();
            uf.unionSet(A-1, B-1);
            if (uf.setCount[uf.findSet(A-1)] > 0) {
                uf.setCount[uf.findSet(A-1)]--;
                fio.println("LADICA");
                continue;
            }
            if (uf.setCount[uf.findSet(B-1)] > 0) {
                uf.setCount[uf.findSet(B-1)]--;
                fio.println("LADICA");
                continue;
            }
            fio.println("SMECE");
        }
        fio.flush();
    }
}

class UnionFind {
    public int[] parent;
    public int[] rank;
    public int[] setCount;
    public int numSets;

    public UnionFind(int N) {
        parent = new int[N];
        rank = new int[N];
        setCount = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            rank[i] = 0;
            setCount[i] = 1;
        }
    }

    public int findSet(int i) { //must feed index input i
        if (parent[i] == i) return i;
        else {
            parent[i] = findSet(parent[i]);
            return parent[i];
        }
    }

    public Boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i), y = findSet(j);
            if (rank[x] > rank[y]) {
                parent[y] = x;
                setCount[x] = setCount[x] + setCount[y];
            } else {
                parent[x] = y;
                if (rank[x] == rank[y]) {
                    rank[y] = rank[y] + 1;
                }
                setCount[y] = setCount[x] + setCount[y];
            }
        }
    }

    public int numDisjointSets() { return numSets; }
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