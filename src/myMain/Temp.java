package myMain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Temp {
	
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
        		arr[i] = sc.nextInt();
        }
//        List<Integer> list = new ArrayList<>();
//        while (sc.hasNextInt()) {
//        		list.add(sc.nextInt());
//        }
        sc.close();
        int ans = 0;
        System.out.println(ans);
    }
}


//Scanner in = new Scanner(System.in);
//while (in.hasNextInt()) {//注意while处理多个case
//    int a = in.nextInt();
//    int b = in.nextInt();
//    System.out.println(a + b);
//}

//int ans = 0, x;
//for(int i = 0; i < n; i++){
//  for(int j = 0; j < n; j++){
//      x = sc.nextInt();
//      ans += x;
//  }
//} 