import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int num_operations = fio.nextInt();
        QueueArr front_data = new QueueArr(num_operations);
        QueueArr back_data = new QueueArr(num_operations);
        int total_inputs = 0;
        int front_inputs = 0;
        int back_inputs = 0;
        for (int i = 1; i <= num_operations; i++) {
            String operation = fio.next();
            int input = fio.nextInt();
            switch (operation) {
                case "push_back":
                    back_data.offer(input);
                    total_inputs++;
                    back_inputs++;
                    if (back_inputs > front_inputs) {
                        int trans = back_data.poll();
                        front_data.offer(trans);
                        front_inputs++;
                        back_inputs--;
                    }
//                    System.out.print("front: ");
//                    front_data.print();
//                    System.out.print("back: ");
//                    back_data.print();
                    break;
                case "push_front":
                    front_data.offerFront(input);
                    total_inputs++;
                    front_inputs++;
                    if (front_inputs > back_inputs+1) {
                        int trans = front_data.pollEnd();
                        back_data.offerFront(trans);
                        front_inputs--;
                        back_inputs++;
                    }
//                    System.out.print("front: ");
//                    front_data.print();
//                    System.out.print("back: ");
//                    back_data.print();
                    break;
                case "push_middle":
                    if(front_inputs > back_inputs) {
                        back_data.offerFront(input);
                        back_inputs++;
                    } else if(front_inputs == back_inputs) {
                        front_data.offer(input);
                        front_inputs++;
                    }
                    total_inputs++;
//                    System.out.print("front: ");
//                    front_data.print();
//                    System.out.print("back: ");
//                    back_data.print();
                    break;
                case "get":
                    if (input < front_inputs) {
                        int retrieve_at = ((front_data.front + input) % front_data.maxSize);
                        if (retrieve_at < 0) {
                            retrieve_at += front_data.maxSize;
                        }
                        fio.println(front_data.arr[retrieve_at]);
                    } else {
                        int retrieve_At = ((back_data.front + input - front_inputs) % back_data.maxSize);
                        if (retrieve_At < 0) {
                            retrieve_At += back_data.maxSize;
                        }
                        fio.println(back_data.arr[retrieve_At]);
                    }
//                    System.out.print("front: ");
//                    front_data.print();
//                    System.out.print("back: ");
//                    back_data.print();
                    break;
            }
        }
        fio.flush();
    }
}

class QueueArr implements QueueADT {
    public int[] arr;
    public int front, back; //gives the index that any new entries should be at if we offer
    public int maxSize;


    public QueueArr(int size){
        arr = new int[size];
        front = 0;
        back = 0;
        maxSize = size;
    }

    public boolean empty() {
        return front==back;
    }

    public Integer poll() {
        if(empty()){
            return null;
        }
        Integer item = arr[front];
        arr[front]=0;
        front =  (front+1) % maxSize;
        return item;
    }

    public Integer pollEnd() {
        if(empty()){
            return null;
        }
        back =  (back-1) % maxSize;
        if(back<0){
            back += maxSize;
        }
        Integer item = arr[back];
        arr[back] = 0;
        return item;
    }

    public void offer(Integer item) {
        if(((back+1)%maxSize) == front){
            enlargeArr();
        }
        arr[back] = item;
        back = (back+1) % maxSize;
    }

    public void offerFront(Integer item) {
        if(((front-1)%maxSize) == back){
            enlargeArr();
        }
        front = (front-1) % maxSize;
        if(front<0) {
            front += maxSize;
        }
        arr[front] = item;
    }

    public void enlargeArr() {
        int newSize = maxSize * 2;
        int[] temp = new int[newSize];

        if (temp == null) {
            System.out.println("run out of memory");
            System.exit(1);
        }

        for (int j = 0; j < maxSize; j++) {
            temp[j] = arr[(front+j)%maxSize];
        }
        front = 0;
        back = maxSize -1;
        arr = temp;
        maxSize = newSize;
    }

    public void print() {
    	System.out.println(Arrays.toString(arr));
    }
}

interface QueueADT {
    public boolean empty();
    public Integer poll();
    public Integer pollEnd();
    public void offer(Integer item);
    public void offerFront(Integer item);
}

class FastIO extends PrintWriter {
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






