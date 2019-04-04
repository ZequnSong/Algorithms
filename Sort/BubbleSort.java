package Sort.BubbleSort;

/**
 * BubbleSort
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class BubbleSort {

    /**
     * BubbleSort
     * @param array
     * @return
     */
    public static int []  sort(int[] array){
        //for loop here means how many loops in all do we need to compare (N-1)
        for(int i = 1; i < array.length; i++){
            //flag true means no exchange in current loop (only used for showing the middle-result)
            // boolean flag = true;

            //for loop here means the index of elements in each round of comparison
            //careful about the range of j is dynamic
            for(int j = 0; j < array.length - i; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                  //  flag = false;
                }
            }
            /*
            if(flag)
                break;
            System.out.print("result of "+ i + "th comparison: ");
            display(array);
            */
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

    public static void main(String[] ars) {
        int[] array = {5, 9, 4, 7, 8, 1, 6, 3};
        System.out.println("Un-sorted array: ");
        display(array);
        System.out.println("-----Bubble Sort Process-----");
        array = sort(array);
        System.out.println("-----------------------------");
        System.out.println("Sorted array: ");
        display(array);
    }
}
