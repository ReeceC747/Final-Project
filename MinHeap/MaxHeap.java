package MinHeap;

public class MaxHeap 
{
    private int[] array = new int[10];

    private int size = 0;

    public void add(int element)
    {
        if(size == array.length - 1)
        {
            int[] newArray = new int[size * 2];
            for(int i = 0; i < array.length; i++)
            {
                newArray[i] = array[i];
            }
            array = newArray;
        }

        if(size == 0)
        {
            array[size] = element;
            size++;
        }
        else
        {
            array[size] = element;
            int index = size;
            boolean inPlace = false;
            while(!inPlace)
            {
                if(array[index] > array[index/2])
                {
                    int temp = array[index/2];
                    array[index/2] = array[index];
                    array[index] = temp;
                    index = index / 2;
                }
                else
                {
                    inPlace = true;
                }
            }
            size++;
        }
    }

    public void print()
    {
        for (int i : array) {
            if(i != 0)
            {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public int[] getHeap()
    {
        return array;
    }
}
