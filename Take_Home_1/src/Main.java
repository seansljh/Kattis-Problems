// A0189893B Lee Jun Hui, Sean

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args){
        FastIO fio = new FastIO();
        int max_capacity = fio.nextInt();
        int passengers = 0;
        int stations = fio.nextInt();

        // Initialise a flag which we can set to false whenever the input lines violate our rules
        boolean possible = true;
        for (int i = 1; i <= stations; i++) {
            int left = fio.nextInt();
            int entered = fio.nextInt();
            int stayed = fio.nextInt();

            // check if train starts with 0 passengers
            if (i == 1 && left != 0) {
                possible = false;
            }

            passengers -= left;
            passengers += entered;

            // checks if train finishes with 0 passengers and nobody stays at the last station
            if (i == stations) {
                if (passengers != 0 || stayed != 0) {
                    possible = false;
                }
                break;
            }
            if (passengers > max_capacity || passengers < 0){
                possible = false;
            } else if (passengers == max_capacity) {
                continue;
            } else {
                if (stayed > 0) { // need to check if the number of stayed is ok
                    possible = false;
                }
            }
        }

        if (possible){
            System.out.println("possible");
        } else {
            System.out.println("impossible");
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
            catch (IOException  e)
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

    long nextLong()
    {
        return Long.parseLong(next());
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