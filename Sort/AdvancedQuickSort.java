package Sort.QuickSort;
/**
 *  Quick Sort
 *  Recursion & Partition Median of Three
 *  Combined with Insertion Sort
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class AdvancedQuickSort {

    public static void  recQuickSort(int[] array,int left, int right){
        //if array is null or only have one element
        if(right-left<=0)
            return;
        else if((right-left) < 10)
            insertionSort(array,left,right);
        else {
            //partition is the position where to divide the array into half
            int partition = partitionIt(array,left,right);
            //recursively using quickSort in both half
            recQuickSort(array,left,partition-1);
            recQuickSort(array,partition+1,right);
        }
    }

    public static int partitionIt(int[] array, int left, int right){
        int i = left;
        int j = right;
        //select the first (left-most) element as the partition element
        medianOfThree(array,left,right);
        int piovt = array[left];
        //if i == j means all elements in array have been visited
        while(i < j){
            //j moves left, stop until meet an element smaller than partition element
            while(i < j  && array[j] >= piovt){j--;}
            //i moves right, stop until meet an element bigger than partition element
            while(i<j && piovt>=array[i]){i++;}
            //if i == j means end of the partition all elements in array have been visited
            //swap the current element (array[i] ot array[j]) with piovt (array(left))
            if(j==i)
                swap(array,left,i);
            else
                //during the partition progress, swap i and j
                swap(array,j,i);
        }
        return j;
    }

    /**
     * get the median value of array[left] array[center] array[right]
     * and swap that value with array[left]
     * @param array
     * @param left
     * @param right
     */
    public static void medianOfThree(int[] array,int left, int right){
        int center = (right - left)/2 + left;
        if(array[left] > array[center])
            swap(array,left,center);
        if(array[left] > array[right])
            swap(array,left,right);
        if(array[center] > array[right])
            swap(array,left,right);
        //now array[left] is the median large of three elements
    }

    public static void insertionSort(int[]array ,int left, int right){
        //start sorting from the element with index left+1, cause the first element (index left) is default sorted
        for(int i  = left + 1; i <= right; i++){
            int temp = array[i];
            //j is the position for array[i] to insert in, start from the tail of the sorted elements
            int j = i - 1;
            while(j >= 0 && temp < array[j]){
                if(temp<array[j]){
                    array[j+1] = array[j];//data move backward
                    j--;
                }
            }
            //breaking out the loop means array[j-1] =< tmp
            //therefore array[j+1] (make up the last while loop j--) is the position for array[i]
            array[j+1] = temp;
        }
    }

    public static void swap(int[] array, int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        //int[] array = {7,3,5,2,9,8,6,1,4,7};
        int[] array = {9, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        recQuickSort(array, 0, array.length - 1);
        //quickSort_2(array,0,array.length-1);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

}
