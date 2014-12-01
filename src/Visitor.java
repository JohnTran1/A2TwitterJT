/**
 *
 * @author John Tran
 * Visitor pattern application as per lecture slides
 * Visitor will be used to count messages/groups/users
 */

public interface Visitor {

    public void visitMsg(User user);

    public void visitUser(User user);

    public void visitGroup(Group group);

    public void visitPositiveMsg(User user);

}
