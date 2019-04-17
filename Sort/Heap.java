package Sort.HeapSort;
/**
*for Heap Sort
**/
public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    /**
     * deletion the maximum node (root)
     * @return
     */
    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        //do trickDown to keep the heap correct
        trickleDown(0);
        return root;
    }

    /**
     * adjust downward
     * @param index the index of node we delete
     */
    public void trickleDown(int index) {
        //save the node which need to go down find a right place
        Node top = heapArray[index];
        int largeChildIndex;
        //while node has at least one child
        while(index < currentSize/2) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            //find larger child
            if(rightChildIndex < currentSize &&  //rightChild exists?
                    heapArray[leftChildIndex].getKey() < heapArray[rightChildIndex].getKey()) {
                largeChildIndex = rightChildIndex;
            }
            else {
                largeChildIndex = leftChildIndex;
            }
            //if data of node bigger than largeChildNode, then Heap is correct
            if(top.getKey() >= heapArray[largeChildIndex].getKey()) {
                break;
            }
            //move the largeNode up
            heapArray[index] = heapArray[largeChildIndex];
            //let index go down
            index = largeChildIndex;
        }
        //now index is the first index that top.getKey() bigger than its children
        heapArray[index] = top;
    }

    /**
     * data insertion
     */
    public void insertAt(int index, Node newNode){
        heapArray[index] = newNode;
    }

    //used for heapSort
    public void incrementSize(){currentSize++;}

    public void displayArray(int dataSize){
        for (int i = 0; i < dataSize; i++) {
            System.out.print(heapArray[i].getKey() + " ");
        }
        System.out.println();
    }
    public void heapSort(){
        //used for output, currentSize changes when remove( ), so we need to save it as dataSize
        int dataSize = currentSize;

        // System.out.print("Array in Heap before sort: " );
        // displayArray(dataSize);

        /*
         * add all data to heapArray then do trickleDown( ) for n/2-1 Nodes to get form a Heap.
         * make random array into heap
         */
        for(int i = currentSize/2 - 1; i >= 0; i--){
            trickleDown(i);
        }
        // System.out.print("Array in heap after adjust: " );
        // displayArray(dataSize);

        //using the same array, output the sort array.
        for(int i = currentSize - 1; i >= 0; i--){
            Node biggestNode = remove();
            insertAt(i,biggestNode);
        }
        //System.out.print("Array in Heap after sort: " );
        // displayArray(dataSize);
    }
}
