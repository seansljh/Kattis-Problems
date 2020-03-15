import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int num_names = fio.nextInt();
        boolean flag = false;
        while (num_names > 0) {
            if (flag) {
                fio.println();
            }
            String[] names = new String[num_names];
            for(int i = 0; i< num_names; i++) {
                names[i] = fio.next();
            }
            Arrays.sort(names, new sortByTwo());
            for(String name:names){
                fio.println(name);
            }
            num_names = fio.nextInt();
            flag = true;
        }
        fio.flush();
    }
}

class sortByTwo implements Comparator<String> {
    public int compare(String a, String b) {
        String sub_a = a.substring(0,2);
        String sub_b = b.substring(0,2);
        return sub_a.compareTo(sub_b);
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



