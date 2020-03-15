//Lee Jun Hui, Sean A0189893B

import java.util.*;

public class Main {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_restaurants = sc.nextInt();
        String[] data = new String[no_restaurants];
        String pan = "pancakes";
        String pea = "pea soup";
        ArrayList<Integer> index = new ArrayList<>();
        for(int i = 0; i < no_restaurants;i++){
            int items = sc.nextInt();
            sc.nextLine();
            data[i] = sc.nextLine();
            boolean is_pea = false;
            boolean is_pan = false;
            for (int j = 0; j < items; j++) {
                String item = sc.nextLine();
                if (item.equals(pan)) {
                    is_pan = true;
                }
                if (item.equals(pea)) {
                    is_pea = true;
                }
            }
            if (is_pea && is_pan) {
                index.add(i);
            }
        }
        if (index.size() != 0) {
            int in = index.get(0);
            System.out.println(data[in]);
        } else {
            System.out.println("Anywhere is fine I guess");
        }
    }
}