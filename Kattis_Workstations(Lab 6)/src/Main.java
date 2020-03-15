import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int num = fio.nextInt();
        int unlock_time = fio.nextInt();
        PriorityQueue<Person> pq = new PriorityQueue<>(num, new PersonSort());
        PriorityQueue<Integer> timings = new PriorityQueue<>();
        int count = 0;
        for(int i =0; i< num; i++){
            int arr = fio.nextInt();
            int spent = fio.nextInt();
            Person p1 = new Person(arr, spent);
            pq.add(p1);
        }

        while(!pq.isEmpty()){
            Person p = pq.poll();
            if(timings.peek() != null) {
                if (p.arrival_time >= timings.peek() && p.arrival_time <= timings.peek()+unlock_time){ //the arrival time is within the unlocked time period
                    timings.poll();
                } else if(timings.peek() + unlock_time < p.arrival_time){ //if the arrival time is after
                    timings.poll();
                    while(!timings.isEmpty()){ //want to remove those computers that cannot serve anyone
                        if (timings.peek() + unlock_time < p.arrival_time) {
                            timings.poll();
                        } else {
                            break;
                        }
                    }
                    if (timings.isEmpty()){
                        count++;
                    } else {
                        timings.poll();
                    }
                } else {
                    count++;
                }
            }
            timings.add(p.arrival_time + p.spent_time);
        }
        fio.println(num-count-1);
        fio.flush();
    }
}

class Person{
    int arrival_time, spent_time;

    public Person(int arrival_time, int spent_time) {
        this.arrival_time = arrival_time;
        this.spent_time = spent_time;

    }
}

class PersonSort implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return (p1.arrival_time - p2.arrival_time);
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