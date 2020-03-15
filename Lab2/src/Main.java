// Lee Jun Hui, Sean A0189893B

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        //initialise array with all the letters and the respective button presses
        String[] arr = new String[256];
        arr['a'] = "2";
        arr['b'] = "22";
        arr['c'] = "222";
        arr['d'] = "3";
        arr['e'] = "33";
        arr['f'] = "333";
        arr['g'] = "4";
        arr['h'] = "44";
        arr['i'] = "444";
        arr['j'] = "5";
        arr['k'] = "55";
        arr['l'] = "555";
        arr['m'] = "6";
        arr['n'] = "66";
        arr['o'] = "666";
        arr['p'] = "7";
        arr['q'] = "77";
        arr['r'] = "777";
        arr['s'] = "7777";
        arr['t'] = "8";
        arr['u'] = "88";
        arr['v'] = "888";
        arr['w'] = "9";
        arr['x'] = "99";
        arr['y'] = "999";
        arr['z'] = "9999";
        arr[' '] = "0";
        int n = fio.nextInt();
        String[] outputs =  new String[n];
        for(int i = 0; i<n;i++){
            String output = "";
            String input = fio.nextLine();
            String previous = "";
            for (int index = 0; index < input.length(); index++) {
                char character = input.charAt(index);
                if (previous.isEmpty()) {
                    output += arr[character];
                    previous = arr[character];
                } else {
                    if (arr[character].charAt(0) == (previous.charAt(0))) {
                        output += " ";
                        output += arr[character];
                        previous = arr[character];
                    } else {
                        output += arr[character];
                        previous = arr[character];
                    }
                }
            }
            outputs[i] = output;
        }
        for(int j = 1; j<outputs.length+1; j++){
            System.out.println("Case #" + j + ": " + outputs[j-1]);
        }
        fio.close();
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
