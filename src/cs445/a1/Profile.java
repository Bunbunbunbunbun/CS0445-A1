package cs445.a1;

/**
 * Created by bunbunbunbunbun on 17-05-26.
 */
public class Profile implements ProfileInterface{

    private String name, about;
    SetInterface<ProfileInterface> profilesFollowed = new Set<ProfileInterface>();


    public Profile(){
        name = " ";
        about = " ";
    }

    public Profile(String name, String about){
        if(name == null)
            throw new IllegalArgumentException("Name cannot be null");
        else
            this.name = name;

        if(about == null)
            throw new IllegalArgumentException("About cannot be null");
        else
            this.about = about;
    }

    public void setName(String newName) throws IllegalArgumentException {

        if (newName == null)
            throw new IllegalArgumentException("Name cannot be null");
        this.name = newName;

    }

    public String getName() {
        return name;
    }

    public void setAbout(String newAbout) throws IllegalArgumentException {
        if (newAbout == null)
            throw new IllegalArgumentException("About cannot be nulll");
        this.about = newAbout;

    }


    public String getAbout() {
        return about;
    }

    public boolean follow(ProfileInterface other) {
        if(other == null)
            return false;
        else
            try{
                profilesFollowed.add(other);
            }
            catch (SetFullException e)
            {
                System.out.println("Capacity reached");
            }
        return true;
    }

    public boolean unfollow(ProfileInterface other) {
        if(other == null)
            return false;
        else {
            profilesFollowed.remove(other);
            return true;
        }
    }

    public ProfileInterface[] following(int howMany) {
        ProfileInterface[] followingArray = null;
        int counter = 0;

        if(howMany < profilesFollowed.getCurrentSize())
            followingArray = new ProfileInterface[howMany];
        else
            followingArray = new ProfileInterface[profilesFollowed.getCurrentSize()];

        while (counter < followingArray.length-1) {
            Object[] objArray = profilesFollowed.toArray();
            for (Object profileArray : objArray) {
                followingArray[counter] = (ProfileInterface) profileArray;
                counter++;
            }
        }
        return followingArray;
    }

    public ProfileInterface recommend() {


        ProfileInterface returns = null;
        for(ProfileInterface friend : following(50)){
            for(ProfileInterface friendsFriend : friend.following(50)){
                if(!profilesFollowed.contains(friendsFriend) && !friend.getName().equals(name) && !friendsFriend.getAbout().equals(about)){
                    return friendsFriend;
                }
            }
        }
        return null;
    }
}