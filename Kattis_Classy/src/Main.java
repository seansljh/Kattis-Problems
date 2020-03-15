import java.util.*;
import java.io.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int cases = fio.nextInt();
        String end = "==============================";
        while(cases > 0) {
            int sub_cases = fio.nextInt();
            Person[] data =  new Person[sub_cases];
            for(int i = 0; i<sub_cases;i++) {
                String input = fio.next();
                String name = input.substring(0, input.length() - 1);
                ArrayList<String> classes  = new ArrayList<>();
                String[] splitted = fio.next().split("-");
                for(int j = splitted.length-1; j >=0;j--) {
                    classes.add(splitted[j]);
                }
                int countToAdd = 10 - splitted.length;
                while(countToAdd > 0) {
                    classes.add("middle");
                    countToAdd--;
                }
//                for(String s:classes){
//                    fio.println(s);
//                }
//                fio.println(end);
                fio.next();
                data[i] = new Person(name, classes);
            }
            Arrays.sort(data, new classySort());
            for(Person a:data){
                fio.println(a.name);
            }
            fio.println(end);
            cases--;
        }
        fio.flush();
    }
}

class Person {
    String name;
    ArrayList<String> splitClasses;

    public Person(String name, ArrayList<String> classes){
        this.name = name;
        this.splitClasses = classes;
    }
}

class classySort implements Comparator<Person> {
    public int compare(Person a, Person b) {
        ArrayList<String> aSc = a.splitClasses;
        ArrayList<String> bSc = b.splitClasses;
        int it = 0;
        int output = 0;
        if (aSc.equals(bSc)) {
            return a.name.compareTo(b.name);
        } else {
            while (it < 10) {
                if (aSc.get(it).equals(bSc.get(it))) {
                    it++;
                } else {
                    output = -1 * aSc.get(it).compareTo(bSc.get(it));
                    break;
                }
            }
            return output;
        }
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




