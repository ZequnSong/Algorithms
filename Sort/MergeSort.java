package Sort.MergeSort;

/**
 *  Merge Sort
 *  for loop and recursion version
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class MergeSort {
    public static void display(int[] array,int start,int end){
        for(int i = start; i <= end; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    public static void display(int[] array){
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
    /**
     * Merge Sort
     * recursive
     * @param array
     * @param start 0
     * @param last array.length - 1
     * @return
     */
    public static int[] mergeSort(int[] array,int start,int last){
        //if more than one value in the array
        if(last > start){
            //find midpoint
            int mid = start + (last - start)/2;
            //sort low half
            mergeSort(array,start,mid);
           // display(array,start,mid);

            //sort high half
            mergeSort(array,mid + 1,last);
          //  display(array,mid+1,last);
            //merge the two part
            merge(array,start,mid,last);
            //display(array);
        }
        return array;
    }

    /**
     * merge operation, similar to mergeSortFor()
     * array[start] --- array[mid] is sorted array a
     * array[mid+1] --- array[last] is sorted array b
     * @param array
     * @param start
     * @param mid
     * @param last
     */
    private static void merge(int[] array, int start, int mid, int last){
        int[] temp = new int[last - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        //if both a and b have values
        while(i <= mid && j <= last){
            //compare the value in a and value in b
            //push the smaller one into temp[]
            if(array[i] <  array[j]){
                temp[k++] = array[i++];
            }else{
                temp[k++] = array[j++];
            }
        }
        //if all values in b are already in temp[], then push all rest values in a into temp[]
        while(i <= mid && j > last)
            temp[k++] = array[i++];
        //if all values in a are already in temp[], then push all rest values in b into temp[]
        while(j <= last && i > mid)
            temp[k++] = array[j++];
        //copy the values in new array temp[] into array
        for(int k2 = 0 ; k2 < temp.length ; k2++){
            array[k2 + start] = temp[k2];
        }
    }

    /**
     * merge sort un-recursive version
     * is a necessary part in recursive merge sort
     * @param a sorted array a
     * @param b sorted array b
     * @return sorted array c
     */
    public static int[] mergeSortFor(int[] a,int[] b){
        int[] c = new int[a.length + b.length];
        int aNow = 0, bNow = 0, cNow = 0;
        //if both a and b have values
        while (aNow < a.length && bNow < b.length){
            //compare the value in a and value in b
            //push the smaller one into c
            if(a[aNow] > b[bNow]){
                c[cNow++] = b[bNow++];
            }else{
                c[cNow++] = a[aNow++];
            }
        }
        //if all values in a are already in c, then push all rest values in b into c
        while(aNow == a.length && bNow < b.length){
            c[cNow++] = b[bNow++];
        }
        //if all values in b are already in c, then push all rest values in a into c
        while(bNow == b.length && aNow < a.length){
            c[cNow++] = a[aNow++];
        }
        return c;
    }
}
