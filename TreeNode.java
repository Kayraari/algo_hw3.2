
// Title: TreeNode class for General Tree
// Author: Kayra Arı-Elçin Karagül
// ID: 10001507-10885319050
// Section: 4
// Assignment: 3
/* Description: This class represents a node in the general tree.
                Each node stores name and salary info, and uses
                firstChild/nextSibling linked list structure.
*/

public class TreeNode {
    String name;
    int salary;
    TreeNode firstChild;   // first child of this node
    TreeNode nextSibling;  // next sibling in linked list

    public TreeNode(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.firstChild = null;
        this.nextSibling = null;
    }
}