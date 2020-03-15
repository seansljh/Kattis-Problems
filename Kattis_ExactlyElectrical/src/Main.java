import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int dest_x = fio.nextInt();
        int dest_y = fio.nextInt();
        int start_x = fio.nextInt();
        int start_y = fio.nextInt();
        int charges = fio.nextInt();
        int min_steps_needed = Math.abs(dest_x - start_x) + Math.abs(dest_y - start_y);
        if (min_steps_needed > charges) {
            System.out.println('N');
        } else {
            int diff = charges - min_steps_needed;
            if (diff % 2 == 0){
                System.out.println('Y');
            } else {
                System.out.println('N');
            }
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
