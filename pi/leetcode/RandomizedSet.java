/*
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
*/
import java.util.*;

class RandomizedSet {
    private Map<Integer, Integer> e2Idx = new HashMap<>();
    private List<Integer> eList = new ArrayList<> ();
    private Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {

    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(e2Idx.containsKey(val)) return false;

        e2Idx.put(val, eList.size());
        eList.add(val);
        return true;
    }

    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!e2Idx.containsKey(val)) return false;

        int size = eList.size(); // get size
        int idx = e2Idx.remove(val); 
        int lastE = eList.get(size-1); // get last element
        eList.set(idx, lastE); // move the last element to idx position.
        eList.remove(size-1); // remove last element from list

        if(lastE != val)
        {
            e2Idx.put(lastE, idx); // update the index of the element that was previously at lastE    
        }
        // System.out.println(lastE);
        // System.out.println(size);
        // System.out.println(e2Idx);
        // System.out.println(eList);

        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rand.nextInt(eList.size());
        return eList.get(idx);
    }

    public String toString()
    {
        return eList.toString();
    }

    public static void main(String [] args)
    {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.remove(2);
        boolean param_3 = obj.insert(2);
        boolean param_4 = obj.remove(1);
        boolean param_5 = obj.insert(2);
        System.out.println(obj);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */