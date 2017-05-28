package cs445.a1;

/**
 * Created by bunbunbunbunbun on 17-05-26.
 */
public class Set<E> implements ProfileInterface {

    private E[] setCapacity;


    public Set(int setSize){
        setCapacity = ((E[]) new Object[setSize]);
    }

    public Set(){
        // median number of Facebook feirnds, according to http://www.pewresearch.org/fact-tank/2014/02/03/6-new-facts-about-facebook/
        setCapacity = ((E[]) new Object[200]);
    }

    public Set(E[] entries) {
        int size = entries.length;
        setCapacity = ((E[]) new Object[size]);
    }


    public void setName(String newName) throws IllegalArgumentException {

    }

    public String getName() {
        return null;
    }

    public void setAbout(String newAbout) throws IllegalArgumentException {

    }

    public String getAbout() {
        return null;
    }

    public boolean follow(ProfileInterface other) {
        return false;
    }

    public boolean unfollow(ProfileInterface other) {
        return false;
    }

    public ProfileInterface[] following(int howMany) {
        return new ProfileInterface[0];
    }

    public ProfileInterface recommend() {
        return null;
    }
}
