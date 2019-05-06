package Sort.InsertionSort;

/**
 * InsertionSort
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class InsertionSort {

    /**
     * InsertionSort
     * @param array
     * @return
     */
    public static int[] sort(int[] array){
        //start sorting from the element with index 1, cause the first element (index 0) is default sorted
        for(int i = 1; i < array.length; i++){
            //j is the position for array[i] to insert in, start from the tail of the sorted elements
            int j = i - 1;
            int tmp = array[i];
            //from the tail of the sorted, all elements that bigger than array[i] move one step back
            //stop until find one smaller than array[i]
            while(j >= 0 && array[j] > tmp){ // careful: array[j] > tmp not array[i], beacause array[i] will change
                array[j+1] = array[j]; //data move backward
                j--;
            }
            //breaking out the loop means array[j-1] =< tmp
            //therefore array[j] is the position for array[i]
            array[j+1] = tmp;
            //System.out.print("result of "+ i  + "th comparison: ");
            //display(array);
        }
        return array;
    }

    /**
     * iterate to show the array
     * @param array
     */
    public static void display(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] array = {5, 9, 4, 7, 8, 1, 6, 3};
        System.out.println("Un-sorted array: ");
        display(array);
        System.out.println("-----Insertion Sort Process-----");
        array = sort(array);
        System.out.println("-----------------------------");
        System.out.println("Sorted array: ");
        display(array);
    }
}
