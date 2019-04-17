package Sort;

import Sort.BubbleSort.BubbleSort;
import Sort.HeapSort.Heap;
import Sort.HeapSort.Node;
import Sort.InsertionSort.InsertionSort;
import Sort.MergeSort.MergeSort;
import Sort.QuickSort.AdvancedQuickSort;
import Sort.QuickSort.QuickSort;
import Sort.SelectionSort.SelectionSort;
import Sort.ShellSort.ShellSort;
import java.util.Arrays;

/**
 * a test applet for all sort algorithm
 * showing the performance of each algorithm
 */
public class test {


    public static void randomInsert(int[] array){
        int i = 0;
        while(i < array.length){
            array[i++] = (int)(1 + Math.random()*array.length);
        }
    }

    public static void bubble(int[] array){
        long start = System.nanoTime();
        BubbleSort.sort(array);
        long end = System.nanoTime();
       // System.out.println(Arrays.toString(array));
        System.out.println("Bubble Sort Time:" + (end - start));
        System.out.println();
    }

    public static void insertion(int[] array){
        long start = System.nanoTime();
        InsertionSort.sort(array);
        long end = System.nanoTime();
       // System.out.println(Arrays.toString(array));
        System.out.println("Insertion Sort Time:" + (end - start));
        System.out.println();
    }
    public static void merge(int[] array){
        long start = System.nanoTime();
        MergeSort.mergeSort(array,0,array.length-1);
        long end = System.nanoTime();
        //System.out.println(Arrays.toString(array));
        System.out.println("Merge Sort Time:" + (end - start));
        System.out.println();
    }
    public static void select(int[] array){
        long start = System.nanoTime();
        SelectionSort.sort(array);
        long end = System.nanoTime();
       // System.out.println(Arrays.toString(array));
        System.out.println("Selection Sort Time:" + (end - start));
        System.out.println();
    }
    public static void shell(int[] array){
        long start = System.nanoTime();
        ShellSort.shellSort(array);
        long end = System.nanoTime();
      //  System.out.println(Arrays.toString(array));
        System.out.println("Shell Sort Time:" + (end - start));
        System.out.println("h=3h+1");
        System.out.println();
    }

    public static void shell2(int[] array){
        long start = System.nanoTime();
        ShellSort.shellSort2(array);
        long end = System.nanoTime();
        //  System.out.println(Arrays.toString(array));
        System.out.println("Shell Sort Time:" + (end - start));
        System.out.println("h=n/2");
        System.out.println();
    }

    public static void quick(int[] array){
        long start = System.nanoTime();
        AdvancedQuickSort.recQuickSort(array,0,array.length-1);
        long end = System.nanoTime();
      //  System.out.println(Arrays.toString(array));
        System.out.println("Quick Sort Time:" + (end - start));
        System.out.println("Median of three combined with insertion sort");
        System.out.println();
    }

    public static void quick2(int[] array){
        long start = System.nanoTime();
        QuickSort.recQuickSort(array,0,array.length-1);
        long end = System.nanoTime();
        //  System.out.println(Arrays.toString(array));
        System.out.println("Quick Sort Time:" + (end - start));
        System.out.println("Normal version");
        System.out.println();
    }

    public static void heapSort(int[] array){
        long start = System.nanoTime();
        Heap heap = new Heap(array.length);
        for(int i = 0; i < array.length; i++) {
            Node newNode = new Node(array[i]) ;
            heap.insertAt(i,newNode);
            heap.incrementSize();
        }
        heap.heapSort();
        long end = System.nanoTime();
        System.out.println("Heap Sort Time:" + (end - start));
        System.out.println("Advanced version");
        System.out.println();
    }


    public static int[] copy(int[] array){
        int[] a = new int[array.length];
        for(int i=0;i<array.length;i++)
            a[i] = array[i];
        return a;
    }





    public static void main(String[] args){
        int size = 10000000;
        int[] array = new int[size];
        randomInsert(array);

        //bubble();
        //insertion();
        //select();
        
        merge(copy(array));
        shell(copy(array));
        shell2(copy(array));
        quick(copy(array));
        quick2(copy(array));
        heapSort(copy(array));

    }
}
