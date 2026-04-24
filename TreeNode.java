
public class TreeNode {
    String name;
    int salary;
    TreeNode firstChild;
    TreeNode nextSibling;

    public TreeNode(String name, int salary){
        this.name=name;
        this.salary=salary;
        this.firstChild=null;
        this.nextSibling=null;
    }

}
