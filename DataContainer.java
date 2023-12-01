public class DataContainer 
{
    private int data;
    private DataContainer left;
    private DataContainer right;
    private DataContainer parent;
    private int size = 0;

    public DataContainer(int dataParam)
    {
        data = dataParam;
        left = null;
        right = null;
    }

    public void setLeft(DataContainer containerParam)
    {
        left = containerParam;
    }

    public DataContainer getLeft()
    {
        return left;
    }

    public void setRight(DataContainer containerParam)
    {
        right = containerParam;
    }

    public DataContainer getRight()
    {
        return right;
    }

    public int getData()
    {
        return data;
    }

    public int getSize()
    {
        return size;
    }

    public void increment()
    {
        size++;
    }

    public DataContainer getParent()
    {
        return parent;
    }

    public void setParent(DataContainer newParent)
    {
        parent = newParent;
    }
}
