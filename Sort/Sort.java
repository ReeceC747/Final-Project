package Sort;

public class Sort
{

    // private boolean validate(int[] array)
    // {

    // }

    /**
     * prints out a sorted array using a bubble sort
     * Uses a for loop to traverse through the array minus the last value. If the current value is bigger than the one ahead of it
     * then both values will swap places and swaps will incrememnt. when the loop is finished if the method 
     */
    public int[] bubbleSort(int[] array)
    {
        int size = getSize(array);
        int temp;
        for(int i = 0; i < size - 1; i++)
        {
            for(int j = 0; j < size - 1; j++)
            {
                boolean swapped = false;
                if(array[j] > array[j + 1] && array[j] != 0)
                {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
                if(!swapped)
                {
                    break;
                }
            }
        }
        return array;
    }

    private int position = 0;
    public int[] selectionSort(int[] array) //fix for incomplete array
    {
        boolean swapped = false;
        // if(position == 0) swapped = true;
        int smallestIndex = position;
        for(int i = position; i < array.length; i++)
        {
            if(array[smallestIndex] >= array[i] && array[i] != 0)
            {
                smallestIndex = i;
                swapped = true;
            }
        }
        if(swapped)
        {
            int temp = array[smallestIndex];
            array[smallestIndex] = array[position];
            array[position] = temp;
            position++;
            selectionSort(array);
        }
        position = 0;
        return array;
    }

    /**
     * https://www.geeksforgeeks.org/insertion-sort/ - used for reference
     */
    public int[] insertionSort(int[] array) // fix for incomplete array
    {
        for(int i = 1; i < array.length; i++)
        {
            int j = i;
            /**
             * The while loop check throws an out of bounds by when its done sorting the current number as its checking for a number
             * before position 0 which is -1 which does not exist, no catch fix needed as the jobs already done
             */
            try
            {   
                while(array[j] < array[j - 1] && array[i] != 0)
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

    public int[] mergeSort(int[] array)
    {   
        int size = getSize(array);
        int[] left = new int[size/2 + size%2];
        int[] right = new int[size/2];
        
        for(int i = 0; i < size/2 + size%2; i++)
        {
            left[i] = array[i];
        }
        for(int i = size/2 + size%2; i < size; i++)
        {
            right[i - (size/2 + size%2)] = array[i];
        }

        if(left.length != 1)
        {
            left = mergeSort(left);
        }
        if(right.length != 1)
        {
            right = mergeSort(right);
        }

        int[] temp = new int[array.length];
        int max = size;
        int i = 0;
        int l = 0;
        int r = 0;
        while(i != max)
        {
            //skip if l is at max
            if(l != left.length && r != right.length)
            {
                if(left[l] >= right[r])
                {
                    temp[i] = right[r];
                    r++;
                    i++;
                }
                else
                {
                    temp[i] = left[l];
                    l++;
                    i++;
                }
            }
            else if(l == left.length)
            {
                temp[i] = right[r];
                r++;
                i++;
            }
            else
            {
                temp[i] = left[l];
                l++;
                i++;
            }
        }
        return temp;
    }

    /**
     * https://www.tutorialspoint.com/data_structures_algorithms/shell_sort_algorithm.htm for reference
     * @param array
     */
    public int[] shellSort(int[] array)
    {
        int interval = 1;
        int length = array.length;
        //solve for interval
        while(interval <= length/3)
        {
            interval = interval * 3 + 1;
        }

        while(interval > 0)
        {
            for(int outer = interval; outer < length; outer++)
            {
                int valueToInsert = array[outer];
                int inner = outer;
                while(inner > interval - 1 && array[inner - interval] >= valueToInsert && array[inner] != 0)
                {
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
     * @param array
     * @return
     */
    public int[] quickSort(int[] array)     // fix for incomplete array
    {
        int size = getSize(array);
        int pivot = array[size - 1];
        int position = 0;
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
        int temp = array[position];
        array[position] = array[size - 1];
        array[size - 1] = temp;
        
        int[] left = new int[position];
        int[] right = new int[size - (position + 1)];

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

        if(left.length > 1)
        {
            left = quickSort(left);
        }
        if(right.length > 1)
        {
            right = quickSort(right);
        }

        int[] sortedArray = new int[size];
        for(int i = 0; i < left.length; i++)
        {
            sortedArray[i] = left[i];
        }
        sortedArray[position] = pivot;
        r = 0;
        for(int i = position + 1; i < size; i++)
        {
            sortedArray[i] = right[r];
            r++;
        }
        return sortedArray;
    }

    public int[] heapSort(int[] array)
    {
        int interval = 0;
        int index = 0;
        int size = getSize(array);
        for(int i = 0; i < size; i++)
        {
            int temp = array[0];
            array[0] = array[size - (1 + interval)];
            array[size - (1 + interval)] = temp;
            array = sortElement(array, index, interval);
            interval++;

        }
        return array;
    }

    private int[] sortElement(int[] array, int index, int interval)
    {
        int temp;
        boolean sorted = false;
        while(!sorted)
        {
            if(hasLeft(array, index, interval) && hasRight(array, index, interval))
            {
                if(array[index * 2 + 1] >= array[index * 2 + 2])
                {
                    if(array[index] < array[index * 2 + 1])
                    {
                        temp = array[index];
                        array[index] = array[index * 2 + 1];
                        array[index * 2 + 1] = temp;
                        index = index * 2 + 1;
                    }
                    else
                    {
                        //sorted
                        sorted = true;
                    }
                    
                }
                else if(array[index * 2 + 1] < array[index * 2 + 2])
                {
                    if(array[index] < array[index * 2 + 2])
                    {
                        temp = array[index];
                        array[index] = array[index * 2 + 2];
                        array[index * 2 + 2] = temp;
                        index = index * 2 + 2;
                    }
                    else
                    {
                        //sorted
                        sorted = true;
                    }
                }
            }
            else if(hasLeft(array, index, interval))
            {
                if(array[index] < array[index * 2 + 1])
                {
                    temp = array[index];
                    array[index] = array[index * 2 + 1];
                    array[index * 2 + 1] = temp;
                    index = index * 2 + 1;
                }
                else
                {
                    //sorted
                    sorted = true;
                }
            }
            else
            {
                //sorted
                sorted = true;
            }
        }
        return array;
    }

    private int getSize(int[] array)
    {
        int size = 0;
        for (int i : array) {
            if(i != 0)
            {
                size++;
            }
        }
        return size;
    }

    private boolean hasLeft(int[] array, int index, int interval)
    {
        boolean hasChild = false;
        try
        {
            if(array[index * 2 + 1] != 0 && (index * 2 + 1) < (getSize(array) - (interval + 1)))
            {
                hasChild = true;
            }
        }
        catch(Exception outOfBoundsException)
        {
            hasChild = false;
        }
        return hasChild;
    }

    private boolean hasRight(int[] array, int index, int interval)
    {
        boolean hasChild = false;
        try
        {                                      
            if(array[index * 2 + 2] != 0 && (index * 2 + 2) < (getSize(array) - (interval + 1)))
            {
                hasChild = true;
            }
        }
        catch(Exception outOfBoundsException)
        {
            hasChild = false;
        }
        return hasChild;
    }

    public boolean validate(int[] array)
    {
        boolean inOrder = true;
        int size = getSize(array);
        for(int i = 0; i < size - 1; i++)
        {
            if(array[i] > array[i + 1])
            {
                inOrder = false;
            }
        }
        return inOrder;
    }
}
