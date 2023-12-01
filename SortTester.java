import java.util.Random;

public class SortTester 
{
    public static void main(String[] args) 
    {
        Random generator = new Random();
        BinaryTree bt = new BinaryTree();
        
        /**
         * Search at 10k size
         * entries will be size/10
         */
        int size = 10000;
        for(int i = 0; i < 10000; i++)
        {
            bt.add(generator.nextInt(size/10) + 1);
        }
        /**
         * Do searches for at 10k Size
         * Bubble Sort X
         * Selection Sort X
         * Insertion Sort X
         * merge Sort X
         * Quick Sort X
         * Heap Sort X
         */
        

    }    
}
