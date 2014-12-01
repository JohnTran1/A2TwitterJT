/*
 @author John Tran
 Composite pattern requirement.
 Every user or group is a TreeItem.  
 */
public interface TreeItem extends VisitorElement {

    public String getName();

    public long getCreationTime();

    public long getLastUpdateTime();

    public User getUser(String user);

}
