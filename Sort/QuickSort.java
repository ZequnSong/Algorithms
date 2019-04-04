package Sort.QuickSort;
/**
 *  Quick Sort
 *  Recursion & Partition
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class QuickSort {

    public static void  recQuickSort(int[] array,int left, int right){
        //if array is null or only have one element
        if(right-left<=0)
            return;
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
