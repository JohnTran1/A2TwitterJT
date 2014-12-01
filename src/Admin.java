import java.util.*;

/*
 @author John Tran
 CS356 A2 & A3
 */
public class Admin extends TreeFunc {

    //Singleton pattern
    private static Admin instance = null;
    //Visitor pattern
    CounterVisitor countVisitor;

    private static final List<TreeItem> nodes = new ArrayList();
    private static final Group treeRoot = new Group("root");

    public static Admin getInstance() {
        // Singleton Pattern implementation
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    double findPositive() {
        countVisitor = new CounterVisitor();
        nodes.stream().forEach((node) -> {
            node.accept(countVisitor);
        });
        return countVisitor.getPositiveMsg();
    }

    public int countGroups() {
        countVisitor = new CounterVisitor();
        nodes.stream().forEach((node) -> {
            node.accept(countVisitor);
        });
        return countVisitor.getGroupCount();
    }

    public int countUsers() {
        countVisitor = new CounterVisitor();
        nodes.stream().forEach((node) -> {
            node.accept(countVisitor);
        });
        return countVisitor.getUserCount();

    }

    public int countTweets() {
        countVisitor = new CounterVisitor();
        nodes.stream().forEach((node) -> {
            node.accept(countVisitor);
        });
        return countVisitor.getMessageCount();
    }

    public static List<TreeItem> getNodes() {
        return nodes;
    }

    public boolean groupExists(String nodeName) {

        return treeRoot.getGroup(nodeName) instanceof Group;
    }

    public boolean userExists(String nodeName) {
        return treeRoot.getUser(nodeName) instanceof User;
    }

    public TreeItem getUser(String name) {
        return treeRoot.getUser(name);

    }

    public static Group getTreeRoot() {
        return treeRoot;
    }

    private Admin() {
        AdminPanel.init();
    }

    public boolean isValid() {

        return nodes.stream().noneMatch((item) -> (item.getName().contains(" ") || isDupe(item)));
    }

    public boolean isDupe(TreeItem checkNode) {

        return nodes.stream().filter((item) -> (!checkNode.equals(item))).anyMatch((item) -> (item.getName().equals(checkNode.getName()) && item.getClass() == checkNode.getClass()));
    }

    public void addUser(String group, String name) {

        User newUser = new User(name);
        nodes.add(newUser);
        treeRoot.getGroup(group).addComponent(newUser);

    }

    public void addGroup(String group, String groupName) {

        Group newGroup = new Group(groupName);
        treeRoot.getGroup(group).addComponent(newGroup);
        nodes.add(newGroup);
    }

    public User getLastUpdatedUser() {

        User maxUser = null;
        long max = 0;

        for (TreeItem item : nodes) {
            if (item instanceof User) {
                User temp = (User) item;
                if (temp.getLastUpdateTime() >= max) {
                    maxUser = temp;
                    max = temp.getLastUpdateTime();
                }
            }
        }

        return maxUser;

    }

    public void addFollower(String user1, String user2) {
        treeRoot.getUser(user1).follow(user2);
    }

    public void addTweet(String user, String message) {
        treeRoot.getUser(user).tweet(message);
    }

    public void generateData() {

        // hard coded data
        this.addGroup("root", "celebrities");
        this.addGroup("celebrities", "male");
        this.addGroup("celebrities", "female");
        this.addUser("male", "MichaelJordan");
        this.addUser("male", "BarackObama");
        this.addUser("female", "Beyonce");
        this.addUser("root", "Kellowyn");
        this.addUser("root", "Blink");
        this.addUser("root", "Whatthedom");
        this.addUser("root", "Alexandra");
        this.addFollower("Beyonce", "Blink");
        this.addFollower("Blink", "Beyonce");
        this.addFollower("Blink", "Kellowyn");
        this.addFollower("Kellowyn", "Alexandra");
        this.addFollower("Alexandra", "Whatthedom");
        this.addFollower("Alexandra", "Kellowyn");
        this.addFollower("MichaelJordan", "BarackObama");
        this.addFollower("Whatthedom", "Blink");
        this.addTweet("Blink", "Muahahahaha");
        this.addTweet("Kellowyn", "Lets play videogames together");
        this.addTweet("Alexandra", "LOVE ME FEED ME!!");
        this.addTweet("Blink", "My code is immaculate!");
        this.addTweet("Kellowyn", "i unfollowed carissa from my newsfeed");
        this.addTweet("Kellowyn", "deuces ac");
        this.addTweet("MichaelJordan", "I like basketball");
        this.addTweet("Whatthedom", "What is this beautiful fake twitter?");
        this.addTweet("BarackObama", "Yes we can!");
        this.addTweet("Beyonce", "Damn I look good");
    }

}
