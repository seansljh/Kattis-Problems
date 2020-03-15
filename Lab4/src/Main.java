import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int syllables = fio.nextInt();
        int players = fio.nextInt();
        LinkedList<Person> states = new LinkedList<>();
        for(int i = 0; i<players; i++) {
            states.add(new Person(i+1, 1));
        }
        while(states.size() > 1) {
            for(int j =0; j<syllables-1;j++) {
                Person a = states.poll();
                states.offer(a);
            }
            Person actOn = states.peek();
            switch(actOn.state) {
                case 1:
                    actOn.state++;
                    states.offerFirst(new Person(actOn.num, 2));
                    break;
                case 2:
                    actOn.state++;
                    states.poll();
                    states.offerLast(actOn);
                    break;
                case 3:
                    states.poll();
                    break;
            }
        }
        fio.println(states.get(0).num);
        fio.flush();
    }
}

class Person {
    public int state, num;

    public Person(int num, int state){
        this.num = num;
        this.state = state; // state 1 = hand in full; state 2 = opened hand; state 3 = palm faced down
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





