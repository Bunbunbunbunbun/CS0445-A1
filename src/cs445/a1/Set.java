package cs445.a1;

import java.util.Arrays;

/**
 * Created by bunbunbunbunbun on 17-05-26.
 */
public class Set<E> implements SetInterface {

    private E[] profileSet, tempArr; //array for profile and for dynamically sizing
    private int size; //number of items


    public Set(int setSize) {
        profileSet = ((E[]) new Object[setSize]);
        size = 0;
    }

    public Set() {
        profileSet = ((E[]) new Object[25]);
        size = 0;
    }

    public Set(E[] entries) {
        size = entries.length;
        profileSet = ((E[]) new Object[size]);

        for (int i = 0; i < size; i++)
            profileSet[i] = entries[i];

    }

    public int getCurrentSize() {

        return size;
    }

    public boolean isEmpty() {
        if(profileSet[0] == null || size == 0)
            return true;
        return false;
    }

    public boolean add(Object newEntry) throws SetFullException, IllegalArgumentException {
        boolean success = false;
        if (newEntry == null){ //null entry check
            throw new IllegalArgumentException("Entry cannot be null");
        } else if (contains(newEntry)){
            throw new IllegalArgumentException("Profile already exists in the set");
        } else {
            if (profileSet.length == size)
                profileSet = Arrays.copyOf(profileSet, profileSet.length + 5); //dynamically resize array in such a way that it's not running every time the arrya is full
            try {
                profileSet[size] = (E) newEntry;
                size++;
                success = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new SetFullException();
            }
        }
        return success;
    }


    public boolean remove(Object entry) throws IllegalArgumentException {
        int index = 0;
        if (entry == null)
            throw new IllegalArgumentException("Entry cannot be null");
        else {
            if (contains(entry)){
                profileSet[index] = profileSet[size-1];
                profileSet[size-1] = null;
                size--;
                return true;
            }
            return false;
        }
    }
    public Object remove() {
        E removed = null;

        if (size > 0){
            removed = profileSet[size-1];
            profileSet[size-1] = null;
            size--;
            return removed;
        }
        return removed;
    }

    public void clear() {
        if (size != 0) {
            for (int i = 0; i < profileSet.length; i++) {
                profileSet[i] = null;
                size = 0;
            }
        }
    }

    public boolean contains(Object entry) throws IllegalArgumentException {

        if (entry == null) {
            throw new IllegalArgumentException("Entry must not be null");
        } else {
            for (int i = 0; i < size; i++){
                if(profileSet.equals(entry))
                    return true;
            }
            return false;
        }
    }

    public Object[] toArray() {
        int step = 0;
        Object[] tempArr = new Object[size];

        for (int i = 0; i < profileSet.length; i++){
            if (profileSet[i] != null){
                tempArr[step] = profileSet[i];
                step++;
            }
        }
        return tempArr;
    }





}
