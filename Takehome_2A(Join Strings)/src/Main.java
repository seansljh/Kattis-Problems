import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {
    public static void main (String[] args) {
        FastIO fio = new FastIO();
        int num_strings = fio.nextInt();
        String[] inputs = new String[num_strings];
        number[] order = new number[num_strings];
        for(int i = 0; i<num_strings;i++){
            inputs[i] = fio.nextLine();
            order[i] = (new number(i));
        }
        int ops = 0;
        int starting_index = -1;
        int last_index = -1;
        while (ops < num_strings - 1) {
            ops++;
            int first_string_ind = (fio.nextInt() - 1);
            int second_string_ind = (fio.nextInt() - 1);
            if (ops != 1) {
                number head = order[starting_index];
                number tail = order[last_index];
                if (head.getCurrent_num() == second_string_ind) {
                    order[first_string_ind].setNext(head);
                    starting_index = first_string_ind;
                } else if (head.getCurrent_num() == first_string_ind || tail.getCurrent_num() == first_string_ind) {
                    number new_tail_node = order[second_string_ind];
                    tail.setNext(new_tail_node);
                    last_index = second_string_ind;
                    while(new_tail_node.getNext() != null){
                        last_index = new_tail_node.getNext().getCurrent_num();
                        new_tail_node = new_tail_node.getNext();
                    }
                } else if (first_string_ind != head.getCurrent_num() && first_string_ind != tail.getCurrent_num() && second_string_ind != head.getCurrent_num()){
                    number first = order[first_string_ind];
                    while(first.getNext() != null){
                        first = first.getNext();
                    }
                    first.setNext(order[second_string_ind]);
                }
            } else {
                order[first_string_ind].setNext(order[second_string_ind]);
                starting_index = first_string_ind;
                last_index = second_string_ind;
            }
        }
        if(order.length != 1) {
            number head = order[starting_index];
            while (head.getNext() != null) {
                fio.print(inputs[head.getCurrent_num()]);
                head = head.getNext();
            }
            fio.print(inputs[head.getCurrent_num()]);
        } else {
            fio.print(inputs[0]);
        }
        fio.flush();
    }
}

class number {
    public number next;
    public int current_num;

    public number(int current_num){
        this.current_num = current_num;
        this.next = null;
    }

    public number getNext(){
        return next;
    }

    public int getCurrent_num(){
        return current_num;
    }

    public void setNext(number n){
        this.next = n;
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





