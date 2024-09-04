package task1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("No data provided");
        } else {
            int[] arr = new int[args.length];

            for(int i = 0; i < args.length; i++){
                arr[i] = Integer.parseInt(args[i]);
            }

            for (int i = 0; i < arr.length - 1; i++) {
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        int tmp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = tmp;
                    }
                }
            }

            System.out.println("The array has been sorted");
            for(int i : arr) {
                System.out.print(i + " ");
            }
        }
    }

}
