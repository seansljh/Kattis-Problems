import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int books = fio.nextInt();
        Integer[] prices = new Integer[books];
        for(int i =0; i<books; i++) {
            prices[i] = fio.nextInt();
        }
        Arrays.sort(prices, Collections.reverseOrder());
        int total_price = 0;
        for(int j = 0; j<prices.length; j++){
            if((j+1)%3 == 0){
                continue;
            } else {
                total_price += prices[j];
            }
        }
        fio.print(total_price);
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




