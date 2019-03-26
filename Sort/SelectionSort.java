package Sort.SelectionSort;

/**
 * SelectionSort
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class SelectionSort {
    /**
     * SelectionSort
     * @param array
     * @return
     */
    public static int[] sort(int []array){
        //for loop here means how many loops in all do we need to compare (N-1)
        for(int i = 0; i < array.length - 1; i++){
            //assume the i-th element is the mini-value in current loop
            int min = array[i];
            int minIndex = i;
            //j start after i, loop to find the current mini-value
            for(int j = i + 1; j< array.length; j++){
                if(array[j] < min){
                    min = array[j];
                    minIndex = j;
                }
            }
            //swap position between minValue and array[i]
            if(minIndex != i){
                array[minIndex] = array[i];
                array[i] = min;
            }

            System.out.print("result of "+ (i + 1) + "th comparison: ");
            display(array);
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
        System.out.println("-----Selection Sort Process-----");
        array = sort(array);
        System.out.println("-----------------------------");
        System.out.println("Sorted array: ");
        display(array);
    }
}
