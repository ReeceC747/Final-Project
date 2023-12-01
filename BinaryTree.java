import java.util.Stack;

public class BinaryTree 
{
    //head of the tree, used to traverse the tree aswell
    private DataContainer root;

    //how many values in the tree(dupes included)
    private int size = 0;

    //adds a new node to the tree
    public void add(int valueParam)         //WORKS
    {
        //if this is the first addition this will be the root or head of the tree
        if(size == 0)
        {
            DataContainer container = new DataContainer(valueParam);
            root = container;
            size++;
            root.increment();
        }
        //if the new addition equals the head of the tree just increase size of the tree and how many dupes of this node there are
        else if(valueParam == root.getData())
        {
            size++;
            root.increment();
        }
        //if the added value is less than the current root node
        else if(valueParam < root.getData())
        {
            //if the root has an open node to its left
            if(root.getLeft() == null)
            {
                DataContainer container = new DataContainer(valueParam);
                root.setLeft(container);
                size++;
                container.increment();
                container.setParent(root);
            }
            //if the node to the left of this root is occupied
            else if(root.getLeft() != null)
            {
                DataContainer tempRoot = root;
                root = root.getLeft();
                //recurse this method until it reaches an open node to the left/right
                add(valueParam);
                root = tempRoot;
            }
        }
        //if the added value is greater than the current root
        else if(valueParam > root.getData())
        {
            //if the node to the right of the current root is empty
            if(root.getRight() == null)
            {
                DataContainer container = new DataContainer(valueParam);
                root.setRight(container);
                size++;
                container.increment();
                container.setParent(root);
            }
            //if the node to the right of the current root is occupied
            else if(root.getRight() != null)
            {
                DataContainer tempRoot = root;
                root = root.getRight();
                //recurse this method until it reaches an open node to the left/right
                add(valueParam);
                root = tempRoot;
            }
        }
    }

    /**
     * 
     * @param valueParam the value being searched for
     * @return found returns true if the value is in the tree, false if the value isnt in the tree
     */
    public boolean hasValue(int value)            //WORKS
    {
        //If the value has been found or not
        boolean found = true;
        //If the value is in the local root
        if(value == root.getData())
        {
            found = true;
            return true;
        }
        //If the value is less than the local root
        else if(value < root.getData())
        {
            //If there is no left child
            if(root.getLeft() == null)
            {
                //Dead end
                found = false;
                return found;
            }
            //If there is a left child
            else if(root.getLeft() != null)
            {
                //Traverse further down left
                DataContainer tempRoot = root;
                root = root.getLeft();
                found = hasValue(value);
                root = tempRoot;
            }
        }
        //If the value is greater than the local root
        else if(value > root.getData())
        {
            //if there is not right child
            if(root.getRight() == null)
            {
                //dead end
                found = false;
                return found;
            }
            //If there is a right child
            else if(root.getRight() != null)
            {
                //Traverse further down right
                DataContainer tempRoot = root;
                root = root.getRight();
                found = hasValue(value);
                root = tempRoot;
            }
        }

        //if the value has been found
        if(found)
        {
            //return true if the value exists
            return true;
        }
        else
        {
            //Return false if the value does not exist
            return false;
        }
    }

    /**
     * Finds the element in the paramenter and returns it if it exists, otherwise returns null
     * @param element
     * @return element that was searched for if it exists
     */
    private DataContainer findNode(int element)
    {
        //If the root is null the value does not exist
        if(root == null)
        {
            System.out.println("Element does not exist");
            return null;
        }
        //if the element is smaller than the local root, traverse left
        else if(element < root.getData())
        {
            root = root.getLeft();
            findNode(element);
        }
        //If the element is bigger than the local root, traverse right
        else if(element > root.getData())
        {
            root = root.getRight();
            findNode(element);
        }
        return root;
    }

    /**
     * Finds a specified value and returns the Node, fixes the root to be at the head of the tree
     * @param element The Node being searched for
     * @return The searched for element or null if it doesnt exist
     */
    public DataContainer findValue(int element)
    {
        DataContainer tempRoot = root;
        DataContainer found = findNode(element);
        root = tempRoot;
        return found;
    }

    /**
     * The method for printing out a binary tree in pre oder, uses a helper method to make a newline and fix the root position after
     * running
     */
    private void preOrder()  
    {
        System.out.print(root.getData() + " ");
        DataContainer tempRoot = root;
        if(root.getLeft() != null)
        {
            root = root.getLeft();
            preOrder();
            if(root.getRight() != null)
            { 
                root = root.getRight();
                preOrder();
            }
        }
        root = tempRoot;
        if(root.getRight() != null)
        {
            root = root.getRight();
            preOrder();
        }

    }

    /**
     * prints out the binary tree in pre order
     */
    public void printPreOrder()
    {
        DataContainer treeHead = root;
        preOrder();
        System.out.println();
        root = treeHead;
    }

    /**
     * method used to traverse tree in order and print out values, uses a helper method to make a newline and put the root back
     * at the head of the tree
     */
    private void inOrder()
    {
        DataContainer tempRoot = root;
        if(root.getLeft() != null)
        {
            root = root.getLeft();
            inOrder();
            root = tempRoot;
        }
        System.out.print(root.getData() + " ");
        if(root.getRight() != null)
        {
            root = root.getRight();
            inOrder();
        }
    }

    /**
     * prints the binary tree in order
     */
    public void printInOrder()
    {
        DataContainer tempRoot = root;
        inOrder();
        System.out.println();
        root = tempRoot;
    }

    /**
     * method used to traverse the tree in post order and print out the values
     */
    private void postOrder()
    {
        DataContainer tempRoot = root;
        if(root.getLeft() != null)
        {
            root = root.getLeft();
            postOrder();
            root = tempRoot;
        }
        if(root.getRight() != null)
        {
            root = root.getRight();
            postOrder();
            root = tempRoot;
        }

        System.out.print(root.getData() + " ");
    }

    /**
     * prints out the binary tree in post order
     */
    public void printPostOrder()
    {
        //Fixes the placement of Root to be at the head of the binary tree
        DataContainer tempRoot = root;
        postOrder();
        System.out.println();
        root = tempRoot;
    }

    /**
     * 
     * @return the largest Node
     */
    private DataContainer largest()
    {
        if(root.getRight() != null)
        {
            root = root.getRight();
            largest();
            return root;
        }
        return null;
    }

    public DataContainer getLargest()
    {
        DataContainer tempRoot = root;
        DataContainer largest = largest();
        root = tempRoot;
        return largest;
    }

    /**
     * Removes a specified element from the binary tree and refixes the references of all objects that were affected
     * @param element The element to be removed
     */
    public void remove(int element)
    {
        DataContainer node = findValue(element);
        if(node == null){}
        //The element has no children
        else if(node.getLeft() == null && node.getRight() == null)
        {
            removeElement(null, node);
        }
        //If the element has one child on the left itll set the parent to refernce the child
        else if(node.getLeft() != null && node.getRight() == null)
        {
            removeElement(node.getLeft(), node);
        }
        //If the element has one child on the right itll set the parent to refernce the child
        else if(node.getLeft() == null && node.getRight() != null)
        {
            removeElement(node.getRight(), node);
        }
        //If the element has two children
        else
        {
            DataContainer leftChild = node.getLeft();
            DataContainer rightChild = node.getRight();
            DataContainer parent = node.getParent();
            //If the left child has no right subtree
            if(leftChild.getRight() == null)
            {
                leftChild.setRight(rightChild);
                leftChild.setParent(node.getParent());
                parent.setLeft(leftChild);
                
            }
            //If the left child has a right subtree
            else
            {
                DataContainer largest = leftChild.getRight();
                while(largest.getRight() != null)
                {
                    largest = largest.getRight();
                }
                //If the largest value has a left subtree
                if(largest.getLeft() != null)
                {
                    DataContainer largestParent = largest.getParent();
                    DataContainer largestLeft = largest.getLeft();
                    largestParent.setRight(largestLeft);
                    largestLeft.setParent(largestParent);
                }
                else
                {
                    DataContainer largestParent = largest.getParent();
                    largestParent.setRight(null);
                }
                if(largest.getData() > parent.getData())
                {
                    parent.setRight(largest);
                    largest.setParent(parent);
                }
                else
                {
                    parent.setLeft(largest);
                    largest.setParent(parent);
                }
                leftChild.setParent(largest);
                largest.setLeft(leftChild);
                rightChild.setParent(largest);
                largest.setRight(rightChild);
            }
        }

    }

    /**
     * If the element has no children or 1 child this will set the parent to point to the child of the removed element
     * @param newReference the child of the removed element
     * @param node The removed element
     */
    private void removeElement(DataContainer newReference, DataContainer node)
    {
        DataContainer parent = node.getParent();
        //if the parent has one child itll find which left or right is null and set the opposite path to null
        if(parent.getLeft() == null)
        {
            parent.setRight(newReference);
        }
        else if(parent.getRight() == null)
        {
            parent.setLeft(newReference);
        }
        //If the parent has two children itll compare the objects of both left and right to see which is the element
        else
        {
            if(parent.getLeft().equals(node))
            {
                parent.setLeft(newReference);
            }
            else
            {
                parent.setRight(newReference);
            }
        }
    }

    /**
     * 
     * @return the root node of the binary tree
     */
    public DataContainer getRoot()
    {
        return root;
    }

    /**
     * Iterates through the binary tree in order, makes an Iterator object from the private nested Iterator class
     */
    public void iterator()
    {
        Iterator iterator = new Iterator();
        while(iterator.hasNext())
        {
            DataContainer data = iterator.next();
            System.out.print(data.getData() + " ");
        }
        System.out.println();
    }

    public class Iterator implements java.util.Iterator<DataContainer>
    {
        Stack<DataContainer> stack = new Stack<DataContainer>();
        DataContainer current;

        public Iterator()
        {
            current = root;
            while(current.getLeft() != null)
            {
                stack.push(current);
                current = current.getLeft();
            }
        }

        /**
         * The method used to determine if we are done traversing the binary tree or not
         */
        @Override
        public boolean hasNext() 
        {
            if(current == null)
            {
                return false;
            }
            //current != root so the loop doesnt end when reaching the root from the root's left subtree
            if(stack.empty() && current.getData() != root.getData()) 
            {
                //The stack is empty
                return false;
            }
            else
            {
                //The stack isnt empty
                return true;
            }
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        /**
         * Next method, traverses the binray tree 1 node at a time. If current is at the bottom right of a right chain, it will
         * return to the start of that chain.
         */
        @Override
        public DataContainer next() 
        {
            //The node to be printed out
            DataContainer returnData = current;
            if(current.getRight() != null) //If current has a right child
            {
                stack.push(current);
                current = current.getRight();
                
                while(current.getLeft() != null) //while current has a left child
                {
                    stack.push(current);
                    current = current.getLeft();
                }
            }
            else // no right children
            {
                //The right child of the top of the stack node
                DataContainer stackRight = stack.peek().getRight();
                if(stackRight != null) //if that node exists so we dont get an error for comparing a node to a null one
                {
                    //Backtracks until the stacks right child is no longer the current node
                    while(!stack.isEmpty () && stack.peek().getRight().equals(current)) 
                    {
                        current = stack.pop();
                    }
                }
                //Terminating feature
                if(stack.isEmpty())
                {
                    current = null;
                }
                else
                {
                    //backtracks from left nodes
                    current = stack.pop();
                }
            }
            return returnData;
        }
    }
}

