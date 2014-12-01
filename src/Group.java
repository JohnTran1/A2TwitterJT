import java.util.*;

public class Group implements TreeItem {

    private final String groupID;
    private long lastUpdateTime;

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
    private final long creationTime;
    private final List<TreeItem> components;

    public Group(String name) {

        creationTime = System.currentTimeMillis();
        groupID = name;
        components = new ArrayList();
    }

    public void accept(Visitor visitor) {
        visitor.visitGroup(this);

    }

    public String getGroupID() {
        return groupID;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void addComponent(TreeItem item) {
        components.add(item);
    }

    public String getName() {
        return groupID;
    }

    public List<TreeItem> getComponents() {
        return components;
    }

    public Group getGroup(String groupName) {
        if (groupID.equals(groupName)) {
            return this;
        }

        for (TreeItem item : components) {
            if (item instanceof Group) {
                if (((Group) item).getGroup(groupName) != null) {
                    return ((Group) item).getGroup(groupName);
                }
            }
        }

        return null;
    }

    public User getUser(String user) {

        for (TreeItem item : components) {
            if (item instanceof User || item instanceof Group) {
                if (item.getUser(user) != null) {
                    return item.getUser(user);
                }
            }
        }
        return null;
    }

}
