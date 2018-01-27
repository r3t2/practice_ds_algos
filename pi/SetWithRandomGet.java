import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class SetWithRandomGet
{
    private List<Integer> list = new ArrayList<Integer> ();
    private Map<Integer, Integer> map = new HashMap<> ();
    private int size = 0;
    private Random random = new Random();

    public void insert(int x)
    {
        map.put(x, size);
        list.add(x);
        size+=1;
    }

    public boolean remove(int x)
    {
        if(size == 0) return false;

        int i = -1;
        if(map.containsKey(x)) i = map.get(x);
        if(i == -1) return false;
        

        // remove the key at index i in O(1) by repalcing key at i with last key
        int lastKey = removeFromList(i);
        // update the new location of lastKey
        map.put(lastKey, i);

        map.remove(x);
        size -= 1;

        return true;

    }

    private int removeFromList(int i)
    {
        // remove last key and set the key at index i to last key
        int lastKey = list.remove(size-1);

        if(i != (size-1)) list.set(i, lastKey);
        
        return lastKey;
    }

    public int getRandomElement()
    {
        int i = random.nextInt(size);
        return list.get(i);
    }
    public String toString()
    {
        return "size = " + size + ", " + list.toString();
    }

    public static void main(String [] args)
    {
        SetWithRandomGet set = new SetWithRandomGet();
        set.insert(5);
        set.insert(4);
        set.insert(3);
        set.insert(2);
        set.remove(5);
        set.remove(2);
        set.remove(3);
        set.insert(1);
        System.out.println(set);
    }

}