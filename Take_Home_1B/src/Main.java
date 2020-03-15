// A0189893B Lee Jun Hui, Sean

import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    public static void main(String[] args){
        FastIO fio = new FastIO();
        int num_runners = fio.nextInt();
        double total_time = 0;
        Athlete[] first = new Athlete[num_runners];
        Athlete[] second = new Athlete[num_runners];
        String[] final_roster = new String[4];
        for(int i =0; i < num_runners ;i++){
            String name = fio.next();
            double first_leg = fio.nextDouble();
            double second_leg  = fio.nextDouble();
            Athlete person = new Athlete(name, first_leg, second_leg);
            first[i] = person;
            second[i] = person;
        }

        Arrays.sort(first, new SortByFirst());
        Arrays.sort(second, new SortBySecond());

        for(int j =0; j<=3; j++){
            String[] sub_fin_roster = new String[4];
            if (j == 0) { //this is to initialise the total_time with a value so we can compare if the subsequent combinations are better
                Athlete selected_first = first[j];
                final_roster[0] = selected_first.name;
                total_time += selected_first.first_leg;
                int counter = 0; //initialise counter to keep track of number of athletes after this first chosen athlete
                for(int k=0; k<=3; k++){
                    Athlete selected_second = second[k];
                    if(selected_second.name.equals(selected_first.name)) { // check if it is the same person
                        continue;
                    } else {
                        if (counter <3) {
                            counter += 1;
                            total_time += selected_second.second_leg;
                            final_roster[counter] = selected_second.name;
                        }
                    }

                }
            } else {
                double sub_time = 0; // keeps track of time for this combination
                Athlete selected_first = first[j];
                sub_fin_roster[0] = selected_first.name;
                sub_time += selected_first.first_leg;
                int counter = 0; //initialise counter to keep track of number of athletes after this first chosen athlete
                for(int k=0; k<=3; k++){
                    Athlete selected_second = second[k];
                    if(selected_second.name.equals(selected_first.name)) {
                        continue;
                    } else {
                        if (counter <3) {
                            counter += 1;
                            sub_time+= selected_second.second_leg;
                            sub_fin_roster[counter] = selected_second.name;
                        }
                    }
                }
                if (sub_time < total_time) { //if this combination is better than the previous one, update it
                    total_time = sub_time;
                    final_roster = sub_fin_roster;
                }
            }
        }
        System.out.println(total_time);
        for(String selected_name: final_roster){
            System.out.println(selected_name);
        }
        fio.flush();
    }
}

class Athlete {
    String name;
    double first_leg, second_leg;

    public Athlete(String name, double first_leg, double second_leg) {
        this.name = name;
        this.first_leg = first_leg;
        this.second_leg = second_leg;
    }
}

class SortByFirst implements Comparator<Athlete> {
    public int compare(Athlete a, Athlete b) {
        if (a.first_leg - b.first_leg > 0) {
            return 1;
        } else if (a.first_leg - b.first_leg <0) {
            return -1;
        } else {
            return 0;
        }
    }
}

class SortBySecond implements Comparator<Athlete> {
    public int compare(Athlete a, Athlete b) {
        if (a.second_leg - b.second_leg > 0) {
            return 1;
        } else if (a.second_leg - b.second_leg <0) {
            return -1;
        } else {
            return 0;
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