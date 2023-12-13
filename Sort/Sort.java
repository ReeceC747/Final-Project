package Sort;

public class Sort
{

    /**
     * Sorts an array with a double for loop. while iterating through the array if the current element is bigger than the one
     * infront of it, swap them. If no swaps have occured then break and stop the loop early
     * @param array an unsorted array
     * @return a sorted array
     */
    public int[] bubbleSort(int[] array)
    {
        //Size of the array that excludes any 0's
        int size = getSize(array);

        int temp;
        //Loop through the array excluding the last element
        for(int i = 0; i < size - 1; i++)
        {
            //If left Unchanged the loop will end early
            boolean swapped = false;

            //Nested loop that loops through the whole array excluding the last element
            for(int j = 0; j < size - 1; j++)
            {
                //If the element  infront is smaller than the current one and ignores swapping with zeroes
                if(array[j] > array[j + 1] && array[j] != 0)
                {
                    //swaps two elements
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                
            }
            //If no swaps occured in the nested loop end this loop early
            if(!swapped)
            {
                break;
            }
        }
        return array;
    }

    /**
     * Sorts an array by finding the smallest element and swapping it with a variable position that starts at the first index
     * and increments every time two elements are swapped. The array is split into two parts, sorted and unsorted
     * @param array an unsorted array
     * @return a sorted array
     */
    public int[] selectionSort(int[] array)
    {
        //Size of the array that excludes 0's
        int size = getSize(array);

        //The index that the smallest element found will be swapped with
        int position = 0;
        
        int temp;
        //Loops for each element in the array
        for(int i = 0; i < size; i++)
        {
            //If left unchanged then the array is in order
            boolean swapped = false;
            //The element that is at the start of the unsorted part of the array
            int smallest = array[position];

            //The index of the first element of the unsorted part of the array
            int smallestIndex = position;

            //Nested loop with a start point that incrememnts every run of the outer loop
            for(int j = position; j < size; j++)
            {
                //If the current element is smaller than the smallest element swap them
                if(smallest >= array[j] && array[j] != 0)
                {
                    smallest = array[j];
                    smallestIndex = j;
                    swapped = true;
                }
            }
            //If an element smaller than the first index of the unsorted array is found, swap the smallest element and the
            //first element of the unsorted array and increment the size of the sorted array
            if(swapped)
            {
                temp = array[position];
                array[position] = array[smallestIndex];
                array[smallestIndex] = temp;
                position++;
            }
            //If the array is sorted end the loop early
            else
            {
                break;
            }
        }
        return array;
    }

    /**
     * https://www.geeksforgeeks.org/insertion-sort/ - used for reference
     * Sorts an array by looping through each element of an array and swapping them with the one before it if its smaller
     * @param array an unsorted array
     * @return a sorted array
     */
    public int[] insertionSort(int[] array) // fix for incomplete array
    {
        //Size of the array excluding 0's
        int size = getSize(array);
        
        //For each element in the array
        for(int i = 1; i < size; i++)
        {
            int j = i;
            /**
             * The while loop check throws an out of bounds by when its done sorting the current number as its checking for a number
             * before position 0 which is -1 which does not exist, no catch fix needed as the jobs already done
             */
            try
            {   
                //Keep swapping the element with the one before it if the current element is smaller and follow it through by
                //decrementing
                while(array[j] < array[j - 1])
                {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    j--;
                }
            }
            catch(Exception outOfBoundsException)
            {
            }
        }
        return array;
    }


    /**
     * Sorts an array by splitting them into two arrays recursively until both arrays are of size 1 or 0 and sorts them into a new
     * array
     * @param array unsorted array
     * @return a sorted array
     */
    public int[] mergeSort(int[] array)
    {   
        //size of the array excluding 0's
        int size = getSize(array);

        //left half of the original array, if the array is odd this array takes the bigger half
        int[] left = new int[size/2 + size%2];
        
        //right half of the original array, if the array is odd this takes the smaller half
        int[] right = new int[size/2];
        
        //Fills the left array with the left half of the original array
        for(int i = 0; i < size/2 + size%2; i++)
        {
            left[i] = array[i];
        }

        //fills the right array with the right half of the original array
        for(int i = size/2 + size%2; i < size; i++)
        {
            right[i - (size/2 + size%2)] = array[i];
        }

        //If the left half of the original array is bigger than 1 than recurse with that array
        if(left.length > 1)
        {
            left = mergeSort(left);
        }

        //If the right half of the orginal array is bigger than 1 than recurse with that array
        if(right.length > 1)
        {
            right = mergeSort(right);
        }

        //A new array that is where both sorted halves put their elements into
        int[] sortedArray = new int[array.length];
        //lenght of the array
        int max = size;

        //How many elements have been inserted into the sorted array
        int i = 0;

        //how many elements of the left array have been added to the sorted array
        int l = 0;
        
        //How many elements of the right array have been added to the sorted array
        int r = 0;

        //while the sorted array isnt full
        while(i != max)
        {
            //If there are still unadded elements in both left and right arrays
            if(l != left.length && r != right.length)
            {
                //If the left element of the left array is bigger than the right element of the right array, then add the right
                //element to the sorted array
                if(left[l] >= right[r])
                {
                    sortedArray[i] = right[r];
                    r++;
                    i++;
                }
                //Else the right element of the right array is bigger, than add the left element of the left array to the sorted
                //array
                else
                {
                    sortedArray[i] = left[l];
                    l++;
                    i++;
                }
            }
            //If all the left arrays elements are in the sorted array, then add the right element in the sorted array
            else if(l == left.length)
            {
                sortedArray[i] = right[r];
                r++;
                i++;
            }
            //Else all the elements in the right array are in the sorted array, add the left element in the sorted array
            else
            {
                sortedArray[i] = left[l];
                l++;
                i++;
            }
        }
        return sortedArray;
    }

    /**
     * https://www.tutorialspoint.com/data_structures_algorithms/shell_sort_algorithm.htm for reference
     * Similar to the insertion sort, except this sorts in small chunks of the array until the whole array is sorted
     * @param array unsorted array
     * @return a sorted array
     */
    public int[] shellSort(int[] array)
    {
        //Size of the array excluding 0's
        int length = getSize(array);

        //solve for interval
        int interval = 1;
        while(interval <= length/3)
        {
            interval = interval * 3 + 1;
        }

        //While the interval is greater of 0
        while(interval > 0)
        {
            //Loop through the array starting at the interval, a sub-array
            for(int outer = interval; outer < length; outer++)
            {
                //The first element of the sub-array
                int valueToInsert = array[outer];

                //The starting interval of the inner loop
                int inner = outer;

                while(inner > interval - 1 && array[inner - interval] >= valueToInsert)
                {
                    //swap the values
                    array[inner] = array[inner - interval];
                    inner = inner - interval;
                }
                array[inner] = valueToInsert;
            }
            interval = (interval - 1) / 3;
        }
        return array;
    }


    /**
     * https://www.geeksforgeeks.org/quick-sort/ - for reference
     * Sorts on a pivot that is the last element of the array and creates two arrays with the left array having elements less than
     * the pivot, the right array consists of elements bigger than the pivot
     * @param array unsorted array
     * @return a sorted array
     */
    public int[] quickSort(int[] array)
    {
        int size = getSize(array);
        int pivot = array[size - 1];
        int position = 0;

        //for each element in the array besides the last, elements smaller than the pivot will be in the left half of the array
        for(int i = 0; i < size - 1; i++)
        {
            if(array[i] < pivot)
            {
                int temp = array[position];
                array[position] = array[i];
                array[i] = temp;
                position++;
            }
        }
        //swaps the pivot with an element so that all elements below it are smaller and all elements above it are bigger
        int temp = array[position];
        array[position] = array[size - 1];
        array[size - 1] = temp;
        
        //left and right arrays
        int[] left = new int[position];
        int[] right = new int[size - (position + 1)];

        //Fills left and right arrays with respective halves
        for(int i = 0; i < position; i++)
        {
            left[i] = array[i];
        }
        int r = 0;
        for(int i = position + 1; i < size; i++)
        {
            right[r] = array[i];
            r++;
        }

        //If either half of the array is bigger than one than recurse with that half
        if(left.length > 1)
        {
            left = quickSort(left);
        }
        if(right.length > 1)
        {
            right = quickSort(right);
        }

        //A new sorted array
        int[] sortedArray = new int[size];

        //Puts all the elements of the smaller half into the sorted array
        for(int i = 0; i < left.length; i++)
        {
            sortedArray[i] = left[i];
        }

        //puts the pivot after the elements smaller than it have been added
        sortedArray[position] = pivot;

        //Adds all the elements of the bigger half into the sorted array
        r = 0;
        for(int i = position + 1; i < size; i++)
        {
            sortedArray[i] = right[r];
            r++;
        }
        return sortedArray;
    }

    

    /**
     * https://www.youtube.com/watch?v=HqPJF2L5h9U - for reference
     * 
     * @param array an unsorted array which represents a complete binary tree
     * @return a sorted array
     */
    public int[] heapSort(int[] array) 
    {
        int size = getSize(array);
    
        //Turns the array into a maxHeap
        for (int i = size / 2 - 1; i >= 0; i--) 
        {
            heapify(array, size, i);
        }
    
        // Extract elements from the heap
        for (int i = size - 1; i > 0; i--) 
        {
            // Swap first element with the last element
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
    
            // Heapify the reduced heap
            heapify(array, i, 0);
        }
    
        return array;
    }
    
    /**
     * A heap with its first and last element - interval swapped and needs to be reheaped
     * @param array array that needs the heap element restored
     * @param size size of the array
     * @param root the root of the array
     */
    private void heapify(int[] array, int size, int root) 
    {
        //The current value
        int largest = root;

        //left child
        int left = 2 * root + 1;

        //right child
        int right = 2 * root + 2;
    
        //If left child is bigger than the current value
        if (left < size && array[left] > array[largest]) 
        {
            largest = left;
        }
    
        //If right child is bigger than the current value
        if (right < size && array[right] > array[largest]) 
        {
            largest = right;
        }
    
        //If the largest value has changed
        if (largest != root) 
        {
            // Swap root with the largest element
            int temp = array[root];
            array[root] = array[largest];
            array[largest] = temp;
    
            // Recurse if largest has been swapped
            heapify(array, size, largest);
        }
    }

    /**
     * returns the size of the array excluding 0's
     * @param array an array of values
     * @return the size of the array
     */
    private int getSize(int[] array)
    {
        int size = 0;
        for (int i : array) 
        {
            if(i != 0)
            {
                size++;
            }
        }
        return size;
    }

    /**
     * Iterates through the array, if the element infront of the current is bigger than the array is sorted
     * @param array an array
     * @return true or false if the array is sorted or not
     */
    public boolean validate(int[] array)
    {
        boolean inOrder = true;

        //size of the array
        int size = getSize(array);

        //for each element in the array
        for(int i = 0; i < size - 1; i++)
        {
            //If the value ahead is smaller than the array is unsorted, ignores zeroes as they will be at the back of the array
            if(array[i] > array[i + 1] && array[i] != 0)
            {
                inOrder = false;
            }
        }
        return inOrder;
    }

    
    
}


