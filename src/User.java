import java.util.*;

public class User extends Observable implements Observer, TreeItem, VisitorElement {

    private final List<String> followers;
    private final List<String> following;
    private final List<String> newsFeed;
    private final List<String> tweetList;
    private final String userID;
    private int tweetCount = 0;
    private final long creationTime;
    private long lastUpdateTime;

    // Project 3 requirement
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void accept(Visitor visitor) {
        visitor.visitUser(this);
        visitor.visitMsg(this);
        visitor.visitPositiveMsg(this);
    }

    public User(String userID) {
        creationTime = lastUpdateTime = System.currentTimeMillis();
        this.userID = userID;
        this.followers = new ArrayList();
        this.following = new ArrayList();
        this.newsFeed = new ArrayList();
        this.tweetList = new ArrayList();
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void follow(String user) {
        following.add(user);
        User findUser = (User) Admin.getInstance().getUser(user);
        findUser.addObserver(this);
        findUser.addFollower(this.userID);
    }

    public void tweet(String tweetMsg) {
        
        lastUpdateTime = System.currentTimeMillis();
        tweetList.add(tweetMsg);
        setChanged();
        notifyObservers(tweetMsg);
        
        newsFeed.add("[" + userID + "] - " + tweetMsg);
        tweetCount++;
        setChanged();
        notifyObservers(newsFeed);

    }

    public void addFollower(String userName) {
        followers.add(userName);
    }

    public String getName() {
        return userID;
    }

    public List<String> getNewsFeed() {
        return newsFeed;
    }

    public List<String> getMyTweets() {
        return tweetList;
    }

    public int getTweetCount() {
        return tweetCount;
    }

    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            newsFeed.add("[" + ((User) o).getName() + "] - " + (String) arg);
        }
    }

    public User getUser(String user) {
        if (userID.equals(user)) {
            return this;
        }
        return null;

    }

    public String toString() {
        return userID;
    }

    public List<String> getFollowing() {
        List<String> getFollowing = new ArrayList();

        following.stream().forEach((s) -> {
            getFollowing.add(s + "- last updated:" + Admin.getInstance().getUser(s).getLastUpdateTime());
        });
        return getFollowing;
    }

}
