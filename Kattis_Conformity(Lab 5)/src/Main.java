import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int num_frosh = fio.nextInt();
        HashMap<String, Integer> hm = new HashMap<>();
        int pop_count = 0;
        for(int i=0; i<num_frosh; i++){
            List<Integer> temp_list = new ArrayList<>();
            for(int j = 0; j<5;j++){
                temp_list.add(fio.nextInt());
            }
            Collections.sort(temp_list);
            StringBuilder sb = new StringBuilder();
            for(int k:temp_list){
                String str = Integer.toString(k);
                sb.append(str);
            }
            String sb_str = sb.toString();
            if (hm.containsKey(sb_str)){
                hm.put(sb_str, hm.get(sb_str) +1);
            } else {
                hm.put(sb_str,1);
            }
        }
        int max_pop = -1;
        Iterator hmIterator = hm.entrySet().iterator();
        while(hmIterator.hasNext()){
            Map.Entry mapped = (Map.Entry)hmIterator.next();
            int value = ((int)mapped.getValue());
            if (value > max_pop){
                max_pop = value;
            }
        }
        Collection<Integer> collection = hm.values();
        for (int i: collection){
            if (i == max_pop){
                pop_count += i;
            }
        }
        fio.println(pop_count);
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





