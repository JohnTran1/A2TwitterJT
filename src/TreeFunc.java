import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/*
 @author John Tran
 This class uses recursion functions for building the tree.
 */
public class TreeFunc {

    public DefaultTreeModel getTree() {
        return getTreeFromItem(Admin.getInstance().getTreeRoot());
    }

    public DefaultTreeModel getTreeFromItem(TreeItem curNode) {
        DefaultMutableTreeNode rebuild = new DefaultMutableTreeNode("root");
        recursiveBuildTree(rebuild, ((Group) curNode).getComponents());
        return new DefaultTreeModel(rebuild);
    }

    public void recursiveBuildTree(DefaultMutableTreeNode parentNode, List<TreeItem> childNode) {
        childNode.stream().forEach((curItem) -> {
            DefaultMutableTreeNode item = new DefaultMutableTreeNode(((TreeItem) curItem).getName());
            parentNode.add(item);
            if (curItem instanceof Group) {
                recursiveBuildTree(item, ((Group) curItem).getComponents());
            }
        });
    }

}
