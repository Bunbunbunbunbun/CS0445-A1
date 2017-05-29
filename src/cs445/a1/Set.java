package cs445.a1;

/**
 * Created by bunbunbunbunbun on 17-05-26.
 */
public class Set<E> implements SetInterface {

    private E[] profileSet, tempArr; //array for profile and for dynamically sizing
    private int size; //number of items
    private boolean isEmpty, isFull; //flags for an empty and full array


    public Set(int setSize) {
        profileSet = ((E[]) new Object[setSize]);
        size = 0;
    }

    public Set() {
        // median number of Facebook friends, according to http://www.pewresearch.org/fact-tank/2014/02/03/6-new-facts-about-facebook/
        profileSet = ((E[]) new Object[200]);
        size = 0;
    }

    public Set(E[] entries) {
        size = entries.length;
        profileSet = ((E[]) new Object[size]);
    }

    /**
     * Determines the current number of entries in this set.
     *
     * @return The integer number of entries currently in this set
     */
    public int getCurrentSize() {
        int totalEntries = 0;

        if (isEmpty == true || size == 0)
            return 0;

        while (true) {
            if (profileSet[totalEntries] == null) {
                size = totalEntries;
                return totalEntries;
            } else {
                totalEntries++;
            }
        }
    }

    /**
     * Determines whether this set is empty.
     *
     * @return true if this set is empty; false if not
     */
    public boolean isEmpty() {
        if(profileSet[0] == null || size == 0){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a new entry to this set, avoiding duplicates.
     * <p>
     * <p> If newEntry is not null, this set does not contain newEntry, and this
     * set has available capacity (if fixed), then add modifies the set so that
     * it contains newEntry. All other entries remain unmodified. Duplicates are
     * determined using the .equals() method.
     * <p>
     * <p> If newEntry is null, then add throws IllegalArgumentException without
     * modifying the set. If this set already contains newEntry, then add
     * returns false without modifying the set. If this set has a capacity
     * limit, and does not have available capacity, then add throws
     * SetFullException without modifying the set.
     *
     * @param newEntry The object to be added as a new entry
     * @return true if the addition is successful; false if the item already is
     * in this set
     * @throws SetFullException         If this set has a fixed capacity and does not
     *                                  have the capacity to store an additional entry
     * @throws IllegalArgumentException If newEntry is null
     */
    public boolean add(Object newEntry) throws SetFullException, IllegalArgumentException {
        if (newEntry == null){ //null entry check
            throw new IllegalArgumentException("Entry cannot be null");
        } else {
            for (int i = 0; i < profileSet.length; i++){
                if (newEntry.equals(profileSet[i])){ //duplicate entry check
                    throw new IllegalArgumentException("Profile already exists in the set");
                } else {
                    if (profileSet.length == size)
                        System.arraycopy(profileSet, 0, profileSet, 0, profileSet.length + 5); //dynamically resize array in such a way that it's not running every time the arrya is full
                    try {
                        profileSet[size] = (E) newEntry;
                    } catch (ArrayIndexOutOfBoundsException e){
                        throw new SetFullException();
                    }
                    size++;
                }
            }

        }
        return false;
    }

    /**
     * Removes a specific entry from this set, if possible.
     * <p>
     * <p> If this set contains the entry, remove will modify the set so that it
     * no longer contains entry. All other entries remain unmodified.
     * Identifying this entry is accomplished using the .equals() method.
     * <p>
     * <p> If this set does not contain entry, remove will return false without
     * modifying the set. If entry is null, then remove throws
     * IllegalArgumentException without modifying the set.
     *
     * @param entry The entry to be removed
     * @return true if the removal was successful; false if not
     * @throws IllegalArgumentException If entry is null
     */
    public boolean remove(Object entry) throws IllegalArgumentException {

        for (int i = 0; i < profileSet.length; i++){
            if (profileSet[i].equals(entry)){
                profileSet[i] = null;
                System.arraycopy(profileSet,i+1, profileSet, i, profileSet.length-i-1); //dynamically resize array
                return true;
            } else {
                throw new IllegalArgumentException("Entry does not exist in set");
            }
        }
        return false;
    }

    /**
     * Removes an arbitrary entry from this set, if possible.
     * <p>
     * <p> If this set contains at least one entry, remove will modify the set
     * so that it no longer contains one of its entries. All other entries
     * remain unmodified. The removed entry will be returned.
     * <p>
     * <p> If this set is empty, remove will return null without modifying the
     * set. Because null cannot be added, a return value of null will never
     * indicate a successful removal.
     *
     * @return The removed entry if the removal was successful; null otherwise
     */
    public Object remove() { //This makes no sense. Needs some serious work.

        int rand = (int) Math.random() * profileSet.length;

        if (isEmpty == true){
            return null;
        } else {
            remove(profileSet[rand]);
            return profileSet[rand];
        }
    }

    /**
     * Removes all entries from this set.
     * <p>
     * <p> If this set is already empty, clear will not modify the set.
     * Otherwise, the set will be modified so that it contains no entries.
     */
    public void clear() {
        for (int i = 0; i < profileSet.length; i++) {
            profileSet[i] = null;
            isEmpty = true;
        }
    }

    /**
     * Tests whether this set contains a given entry. Equality is determined
     * using the .equals() method.
     * <p>
     * <p> If this set contains entry, then contains returns true. Otherwise
     * (including if this set is empty), contains returns false. If entry is
     * null, then remove throws IllegalArgumentException. The method never
     * modifies this set.
     *
     * @param entry The entry to locate
     * @return true if this set contains entry; false if not
     * @throws IllegalArgumentException If entry is null
     */
    public boolean contains(Object entry) throws IllegalArgumentException {

        if (entry == null) {
            throw new IllegalArgumentException("Entry must not be null");
        } else {
            for (int i = 0; i < profileSet.length; i++){
                if(profileSet[i].equals(entry))
                    return true;
            }
            return false;
        }
    }

    /**
     * Retrieves all entries that are in this set.
     * <p>
     * <p> An array is returned that contains a reference to each of the entries
     * in this set. The returned array's length will be equal to the number of
     * elements in this set, and thus the array will contain no null values.
     * <p>
     * <p> If the implementation of set is array-backed, toArray will not return
     * the private backing array. Instead, a new array will be allocated with
     * the appropriate capacity.
     *
     * @return A newly-allocated array of all the entries in this set
     */
    public Object[] toArray() {
        int step = 0;
        Object[] tempArr = new Object[profileSet.length];

        for (int i = 0; i < profileSet.length; i++){
            if (!(profileSet[i] == null)){
                tempArr[step] = profileSet[i];
                step++;
            }

            System.arraycopy(tempArr, 0, tempArr, 0, step);
        }
        System.arraycopy(tempArr, 0, tempArr, 0, step);
        return tempArr;
    }





}
