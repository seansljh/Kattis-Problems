import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int width = fio.nextInt();
        int num_pieces = fio.nextInt();
        Piece[] piece_info = new Piece[num_pieces];
        for(int i = 0; i < num_pieces; i++){
            int w = fio.nextInt();
            int l = fio.nextInt();
            piece_info[i] = new Piece(w,l);
        }
        int total = 0;
        for (int j = 0; j<piece_info.length; j++) {
            Piece sub_piece = piece_info[j];
            total += (sub_piece.width * sub_piece.length);
        }
        System.out.println((int)Math.floor((double) total / (double)width));
        fio.flush();

    }
}

class Piece {
    int width, length;

    public Piece(int w, int l){
        this.width = w;
        this.length = l;
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


