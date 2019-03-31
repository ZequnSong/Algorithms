package Search;

/**
 *  Binary Search
 *  discuss the boundary problem:
 *  >>given an array(ascendant) which has non duplicate number, recursion and non-recursion version
 *  >>given an array(ascendant) which may contains duplicate number
 *    >>find the first position(minimum i) which a[i] = keyValue, return -1 if cannot find keyValue
 *    >>find the first position(minimum i) which a[i] = keyValue, return the position to insert keyValue if cannot find
 *    >>find the first position(minimum i) which a[i] > keyValue, return -1 if doesn't exist
 *    >>find the last position(maximum i) which a[i] = keyValue, return -1 if cannot find keyValue
 *    >>find the last position(maximum i) which a[i] < keyValue, return -1 if doesn't exist
 *  Author : Zequn Song
 *  Email : zsong73@gwu.edu
 */
public class BinarySearch {
    /**
     * binary search with array has non duplicate number
     * non-recursion version
     * @param array
     * @param value
     * @return -1 if cannot find value
     */
    public static int binarySearch11(int[] array, int value){
        int start = 0;
        int end = array.length - 1;
        while(start < end){
            int mid = (end - start) / 2 + start;
            if(value == array[mid]) return mid;
            if(value > array[mid]) start = mid + 1;
            if(value < array[mid]) end = mid - 1;
        }
        return -1;
    }

    /**
     * binary search with array has non duplicate number
     * recursion version
     * @param array
     * @param value
     * @return -1 if cannot find value
     */
    public static int binarySearch12(int[] array, int value, int low, int high){
        int mid = (high - low) / 2 + low;
        if(array[mid] == value)
            return mid;
        else if(low > high)
            return -1;
        else{
            if(array[mid] > value)
                return binarySearch12(array, value, low, mid - 1);
            if(array[mid] < value)
                return binarySearch12(array, value, mid + 1, high);
        }
        return -1;
    }

    /**
     * binary search (in duplicate number space)
     * find the first position(minimum i) which a[i] = keyValue,
     *
     * assume array is 0000001111111222222
     * 0 represents the number smaller than keyValue
     * 1 represents keyValue
     * 2 represents the number bigger than keyValue
     *
     * @param array array(ascendant) which may contains duplicate number
     * @param value keyValue
     * @return  return -1 if cannot find keyValue
     */
    public static int binarySearch21(int[] array, int value){
        int left = 0;
        int right = array.length - 1;
        int mid;
        while(left <  right){
            /* if array[mid] == keyValue
             * we should move on to the left part (because the first keyValue can only occur in left part )
             * because mid is still a possible goal
             * we need to make right = mid and keep the search scale waning at the same time
             * thus we need to use left-mid
             * which can make sure the scale of search (array[left]--array[mid]) is still waning when there is only two elements left
             * i.e. no endless loop when there are only two elements
             */
            mid = left + (right - left)/2; //left-mid
            //if array[mid] < value,  array[mid] cannot be key
            //thus left is left = mid + 1 ---- make sure the goal value is in the search part
            //e.g.   000112
            if(array[mid] < value)
                left = mid + 1;
            //if array[mid] >= value, array[mid] can be the first key
            //thus right = mid instead of mid - 1 ---- make sure the goal value is in the search part
            //if right = mid - 1, {0,1,2} will return -1 instead of the right answer 1
            else
                right = mid;
        }
        if(array[left] == value)
            return left;
        return -1;
    }

    /**
     * binary search (in duplicate number space)
     * find the first position(minimum i) which a[i] = keyValue
     *
     * assume array is 0000001111111222222
     * 0 represents the number smaller than keyValue
     * 1 represents keyValue
     * 2 represents the number bigger than keyValue
     *
     * @param array array(ascendant) which may contains duplicate number
     * @param value keyValue
     * @return  return the position to insert keyValue if cannot find
     */
    public static int binarySearch22(int[] array, int value){
        int left = 0;
        int right = array.length - 1;
        int mid;
        while(left <  right){
            /* if array[mid] == keyValue
             * we should move on to the left part (because the first keyValue can only occur in left part )
             * because mid is still a possible goal
             * we need to make right = mid and keep the search scale waning at the same time
             * thus we need to use left-mid
             * which can make sure the scale of search (array[left]--array[mid]) is still waning when there is only two elements left
             * i.e. no endless loop when there are only two elements
             */
            mid = left + (right - left)/2; //left-mid
            /* if array[mid] < value,  array[mid] cannot be key
             * thus left is left = mid + 1 ---- make sure the goal value is in the search part
             * e.g.   000222 or 000000
             * */
            if(array[mid] < value)
                left = mid + 1;
                /* if array[mid] >= value, array[mid] can be the first key
                 * thus right = mid instead of mid - 1 ---- make sure the goal value is in the search part
                 * e.g. 002222 or 222222
                 * if right = mid - 1, {0,1,2} will return -1 instead of the right answer 1
                 */
            else
                right = mid;
        }
        /* now left = right
         * if array[left] == value
         * array[left] is the first value
         * if array[left] > value
         * array[left] is the position for value to insert
         * if array[left] < value means all elements in array is smaller tha value
         * and value should be insert to array[left + 1]
         */
        if(array[left] >= value)
            return left;
        else
            return left + 1;
    }

    /**
     * binary search (in duplicate number space)
     * find the first position(minimum i) which a[i] > keyValue
     *
     * assume array is 0000001111111222222
     * 0 represents the number smaller than keyValue
     * 1 represents keyValue
     * 2 represents the number bigger than keyValue
     *
     * @param array array(ascendant) which may contains duplicate number
     * @param value keyValue
     * @return  return -1 if doesn't exist
     */
    public static int binarySearch23(int[] array, int value){
        int left = 0;
        int right = array.length - 1;
        int mid;
        while(left <  right){
            /* if array[mid] > keyValue
             * we should move on to the left part (because the first array[i] > keyValue can only occur in left part )
             * because mid is still a possible goal
             * we need to make right = mid and keep the search scale waning at the same time
             * thus we need to use left-mid
             * which can make sure the scale of search (array[left]--array[mid]) is still waning when there is only two elements left
             * i.e. no endless loop when there are only two elements
             */
            mid = left + (right - left)/2; //left-mid
            /* if array[mid] <= value,  array[mid] cannot be key
             * thus left is left = mid + 1;---- make sure the goal value is in the search part
             * e.g.   001122 or 000000
             * */
            if(array[mid] <= value)
                left = mid + 1;
                /* if array[mid] > value, array[mid] can be the first key
                 * thus right = mid instead of mid - 1 ---- make sure the goal value is in the search part
                 * e.g. 122222 or 222222
                 * if right = mid - 1; {012222} will return -1 instead of the right answer 2
                 */
            else
                right = mid;
        }
        /* now left = right
         * if array[left] > value
         * array[left] is the first position(minimum i) which a[i] > keyValue
         * if array[left] =< value means all elements in array is no more than value
         * which means cannot find a[i] > keyValue
         */
        if(array[left] > value)
            return left;
        else
            return -1;
    }

    /**
     * binary search (in duplicate number space)
     * find the last position(maximum i) which a[i] = keyValue
     *
     * assume array is 0000001111111222222
     * 0 represents the number smaller than keyValue
     * 1 represents keyValue
     * 2 represents the number bigger than keyValue
     *
     * @param array array(ascendant) which may contains duplicate number
     * @param keyValue keyValue
     * @return  return -1 if cannot find keyValue
     */
    public static int binarySearch24(int[] array, int keyValue){
        int left = 0;
        int right = array.length - 1;
        int mid;
        while(left < right){
            /* if array[mid] == keyValue
             * we should move on to the right part (because the last array[i] = keyValue can only occur in right part )
             * because mid is still a possible goal
             * we need to make left = mid and keep the search scale waning at the same time
             * thus we need to use right-mid
             * which can make sure the scale of search (array[mid]--array[right]) is still waning when there is only two elements left
             * i.e. no endless loop when there are only two elements, mid will keep moving the right direction
             */
            mid = left + (right - left + 1) / 2; //right-mid
            /* if array[mid] > value,  array[mid] cannot be key
             * thus right is right = mid - 1 ---- make sure the goal value is in the search part
             * e.g.  011222 or 222222
             * */
            if(array[mid] > keyValue)
                right =  mid - 1;
                /* if array[mid] <= value, array[mid] can be the first key
                 * thus left = mid instead of mid + 1 ---- make sure the goal value is in the search part
                 * e.g. 011122 or 000000
                 * if left = mid + 1; {011122} will return -1 instead of the right answer 3
                 */
            else
                left = mid;
        }
        if (array[left] == keyValue)
            return left;
        else
            return -1;

    }

    /**
     * binary search (in duplicate number space)
     * find the last position(maximum i) which a[i] < keyValue
     *
     * assume array is 0000001111111222222
     * 0 represents the number smaller than keyValue
     * 1 represents keyValue
     * 2 represents the number bigger than keyValue
     *
     * @param array array(ascendant) which may contains duplicate number
     * @param keyValue keyValue
     * @return  return -1 if doesn't exist
     */
    public static int binarySearch25(int[] array, int keyValue){
        int left = 0;
        int right = array.length - 1;
        int mid;
        while(left < right){
            /* if array[mid] < keyValue
             * we should move on to the right part (because the last array[i] < keyValue can only occur in right part )
             * because mid is still a possible goal
             * we need to make left = mid and keep the search scale waning at the same time
             * thus we need to use right-mid
             * which can make sure the scale of search (array[mid]--array[right]) is still waning when there is only two elements left
             * i.e. no endless loop when there are only two elements, mid will keep moving the right direction
             */
            mid = left + (right - left + 1) / 2;
            /* if array[mid] < value,  array[mid] can be the last a[i] < keyValue
             * thus left is left = mid instead of mid + 1  ---- make sure the goal value is in the search part
             * e.g.  000012 or 000000
             * if left = mid + 1; {000012} will return -1 instead of the right answer 3
             * */
            if(array[mid] < keyValue)
                left = mid;
            else
                /* if array[mid] >= value, array[mid] cannot be the goal
                 * thus right = mid - 1,  make sure the goal value is in the search part
                 * e.g. 000122 or 111111
                 * if left = mid + 1; {011122} will return -1 instead of the right answer 3
                 */
                right = mid - 1;
        }
        /* now left = right
         * if array[left] < value
         * array[left] is the last position(Maximum i) which a[i] < keyValue
         * if array[left] >= value means no elements in array is smaller than value
         * which means cannot find a[i] < keyValue
         */
        if(array[left] < keyValue)
            return left;
        else
            return -1;
    }

}
