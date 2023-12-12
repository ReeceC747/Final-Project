package Sort;
import java.util.Random;

import MinHeap.MaxHeap;

public class SortTester 
{
    public static void main(String[] args) 
    {
        int size = 10000;
        Random generator = new Random();
        MaxHeap mh = new MaxHeap();
        int[] arr = new int[size];
        Sort sort = new Sort();

        /**
         * Search at 10k size to 200k
         * entries will be size/10
         */
        for(int i = 0; i < size; i++)
        {
            //comment uncomment which data structure to use (array/maxHeap)
            // mh.add(generator.nextInt(size) + 1);
            arr[i] = generator.nextInt(size/10) + 1;
        }

        Long startTime = System.currentTimeMillis();

        arr = sort.bubbleSort(arr);

        Long endTime = System.currentTimeMillis();

        //A second is a 1000 milliseconds
        Long time = endTime - startTime;
        if(sort.validate(arr))
        {
            System.out.println("The array is sorted, it took " + time + " milliseconds to sort the array");
            // for (int i : arr) 
            // {
            //     System.out.print(i + " ");
            // }
            // System.out.println();
        }
        else
        {
            System.out.println("The array is not sorted");
        }
        
        /**
         * / X = works but doesnt work with unfinished array
         * Do searches for at 10k Size
         * Bubble Sort / /
         * Selection Sort / /
         * Insertion Sort / /
         * merge Sort / /
         * Shell sort / /
         * Quick Sort / /
         * Heap Sort / /
         */
    }    
}
