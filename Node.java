public class Node {
    String name;
    int salary;

    TreeNode firstChild; //head of linked list of children

    Node(String name, int salary){
        this.name=name;
        this.salary=salary;
        this.firstChild=null;
    }

}
