import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int no_inputs = fio.nextInt();
        int i = 0;
        Integer[] inputs = new Integer[no_inputs];
        while(i < no_inputs){
            int number = fio.nextInt();
            int N = fio.nextInt();
            inputs[i] = N;
            i++;
        }
        for(int j =0; j< inputs.length;j++){
            int s1 = 0;
            for (int k = 1; k<=inputs[j];k++){
                s1 += k;
            }
            int s2 =  2*s1 - inputs[j];
            int s3 = 2*s1;
            System.out.println((j+1) + " " + s1 + " " + s2 + " " + s3);
        }

//        for (int i = 1; i <= no_inputs; i++){
//
//        }
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

