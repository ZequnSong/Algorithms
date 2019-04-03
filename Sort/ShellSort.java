package Sort.ShellSort;

import java.util.Arrays;

/**
 *  Shell Sort
 *  h = h*3 + 1
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class ShellSort {
    public static void shellSort(int[] array){
        System.out.println("Original array is : " + Arrays.toString(array));
        //find the initial value of h (increment)
        int h = 1;
        while(h*3+1 < array.length){
            h = h*3 + 1;//1,4,13,40......
        }
        //decreasing h, until h = 1
        while(h > 0){
            //h-sort, when h=1, equal to insertion sort
            for(int i = h ; i < array.length ; i++){
                int temp = array[i];
                int j = i - h;
                // array[j] (array[i-h]) means left neighbor of array[i]
                while(j >= 0 &&  array[j] >= temp){
                    array[j + h] = array[j];
                    j -= h;
                }
                //
                array[j + h] = temp;
            }//end for
            System.out.println("The result of h="+h+" sort: "+ Arrays.toString(array));
            //decrease h
            h = h/3;
        }
        System.out.println("Final result："+ Arrays.toString(array));
    }

    public static void randomInsert(int[] array){
        int i = 0;
        while(i < array.length){
            array[i++] = (int)(1 + Math.random()*100);
        }
    }

    public static void main(String[] args){
        int[] array = new int[20];
        randomInsert(array);
        shellSort(array);
    }
}
