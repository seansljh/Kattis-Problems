import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        long inputs = fio.nextInt();
        TreeMap<Long, PriorityQueue<Long>> tm = new TreeMap<>();
        for(long i = 0;  i<inputs; i++){
            String command = fio.next();
            if(command.equals("add")){
                long energy = fio.nextInt();
                long gold = fio.nextInt();
                if(!tm.containsKey(energy)) {
                    PriorityQueue<Long> pq = new PriorityQueue<>(Collections.reverseOrder());
                    pq.offer(gold);
                    tm.put(energy, pq);
                } else{
                    PriorityQueue<Long> cur_pq =  tm.get(energy);
                    cur_pq.offer(gold);
                    tm.remove(energy);
                    tm.put(energy, cur_pq);
                }
            }else if(command.equals("query")) {
                long energy_available = fio.nextInt();
                long gold_earnt = 0;
                while(tm.floorKey(energy_available) != null) {
                    long val = tm.floorKey(energy_available);
                    PriorityQueue<Long> cur_pq =  tm.get(val);
                    gold_earnt += cur_pq.poll();
                    energy_available -= val;
                    if(cur_pq.size()==0){
                        tm.remove(val);
                    }
                }
                fio.println(gold_earnt);
            }
        }
        fio.flush();
    }
}

//Treeset in as value; because there can be more than 1 quest with the same energy and gold rewards
//Pick the one with the highest gold value if there are multiple ones

//class QuestSort implements Comparator<Long> {
//    public Long compare(Long q1, Long q2) {
//        return (q1 - q2);
//    }
//}
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