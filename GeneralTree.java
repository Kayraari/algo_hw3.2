
// Title: GeneralTree class - N-ary Tree implementation
// Author: Kayra Arı-Elçin Karagül
// ID: 10001507-10885319050
// Section: 4
// Assignment: 3
/* Description: This class implements a general (n-ary) tree using
              a linked list representation (firstChild/nextSibling).
              It supports operations: print subordinates, find common
              manager (LCA), calculate total salary, check manager,
              and find path from root to a node.
*/

import java.io.BufferedReader;
import java.io.FileReader;

public class GeneralTree {

    TreeNode root;

    public GeneralTree(){
        this.root=null;
    }


    // Summary: Finds a node with the given name starting from 'current'.
    // Precondition: current is a valid TreeNode or null; name is a non-null String.
    // Postcondition: Returns the TreeNode with matching name, or null if not found.
    public TreeNode findNode(TreeNode current, String name){
        if(current==null)
            return null;
        if (current.name.equals(name))
            return current;
        //search in children
        TreeNode child= current.firstChild;

        while(child!=null){
            TreeNode found = findNode(child, name);
            if(found!= null)
                return found;
            child= child.nextSibling;
        }
        return null;
    }


    // Summary: Adds a child node under the given parent node.
    // Precondition: parentName exists in the tree; childName and childSalary are valid.
    // Postcondition: A new child node is appended to the end of parent's children list.

    public void addChild(String parentName, String childName, int childSalary){
        TreeNode parent = findNode(root, parentName);

        if(parent==null){
            System.out.println("Parent is not found.");
            return;
        }
        TreeNode newNode = new TreeNode(childName, childSalary);
        if(parent.firstChild==null)
            parent.firstChild=newNode;
        else {
            TreeNode temp = parent.firstChild;
            while(temp.nextSibling!= null)
                temp= temp.nextSibling;
            temp.nextSibling= newNode;
        }
    }


    // Summary: Reads a file and builds the tree from parent-child relationships.
    // Precondition: fileName is a valid path to a file with correct format.
    // Postcondition: The tree is fully constructed from the file data.
    public void buildTreeFromFile(String fileName){
        try {
            BufferedReader br= new BufferedReader(new FileReader(fileName));
            String line;
            while((line=br.readLine())!=null){

                String[] parts = line.split(",");//split the parts to parent and child

                String[] parentPart = parts[0].trim().split(" ");//split the parent part with space
                String parentName = parentPart[0];//first part is the parentname
                int parentSalary= Integer.parseInt(parentPart[1]);//second part is the salary

                String[] childPart = parts[1].trim().split(" ");//split the child part with space
                String childName = childPart[0];//first part is the child name
                int childSalary= Integer.parseInt(childPart[1]);//second part is the child salary

                //first line -> create root
                if(root==null)
                    root = new TreeNode (parentName, parentSalary);
                addChild(parentName, childName, childSalary);
            }
            br.close();
        }
        catch (Exception e){
            System.out.println("File not found."); //so that program does not crash
        }
    }

    // Summary: Prints all direct children (subordinates) of the given employee.
    // Precondition: name is a non-null String.
    // Postcondition: Prints the direct children separated by commas, or an appropriate message.
    public void printSubordinates(String name){
        TreeNode employee = findNode(root, name);
        if(employee==null){
            System.out.println("Employee not found.");
            return;
        }
        if(employee.firstChild==null){
            System.out.println(name + " has no subordinates.");
            return;
        }
        System.out.print("Subordinates of "+name+": ");
        TreeNode temp =employee.firstChild;

        while(temp!=null){
            System.out.print(temp.name);
            if(temp.nextSibling!=null)
                System.out.print(", "); //comma between siblings, not after the last one
            temp=temp.nextSibling;
        }
        System.out.println();
    }


    // Summary: Recursively finds the Lowest Common Ancestor (LCA) of two nodes.
    // Precondition: name1 and name2 are names of nodes in the tree.
    // Postcondition: Returns the LCA TreeNode of the two given names.
    public TreeNode findLCA(TreeNode current, String name1, String name2){
        if(current==null)
            return null;
        if((current.name.equals(name1)) || (current.name.equals(name2)))
            return current;
        int count =0;
        TreeNode tempResult= null;

        TreeNode child = current.firstChild;
        while(child!=null){
            TreeNode res = findLCA(child, name1, name2);
            if(res!=null){
                count++;
                tempResult=res;
            }
            child=child.nextSibling;
        }
        if(count >=2)
            return current;
        return tempResult;
    }


    // Summary: Finds and prints the common manager (LCA) of two employees.
    // Precondition: name1 and name2 are non-null Strings.
    // Postcondition: Prints the name of the common manager, or an error message.
    public void findCommonManager(String name1, String name2){
        TreeNode emp1 = findNode(root, name1);
        TreeNode emp2 = findNode(root, name2);

        if(emp1==null || emp2 ==null) {
            System.out.println("Employee not found.");
            return;
        }
        TreeNode lca=findLCA(root, name1, name2);
        System.out.println("Common Manager: " +lca.name);
    }


    // Summary: Recursively calculates the total salary of a node and all its descendants.
    // Precondition: node is a valid non-null TreeNode.
    // Postcondition: Returns the sum of salaries in the subtree rooted at node.
    public int calculateSalary(TreeNode node){
        if(node==null)
            return 0; //fixed: return 0 so that recursive calls do not crash

        TreeNode child= node.firstChild;
        int sum= node.salary;

        while(child!=null){
            sum += calculateSalary(child);
            child=child.nextSibling;
        }
        return sum;
    }


    // Summary: Prints the total salary of the subtree rooted at the given employee.
    // Precondition: name is a non-null String.
    // Postcondition: Prints the total salary or an error message if not found.
    public void printTotalSalary(String name){
        TreeNode emp = findNode(root, name);
        if(emp==null){
            System.out.println("Employee not found.");
            return;
        }
        int total = calculateSalary(emp);
        System.out.println("Total salary under " + name + ": " + total);
    }


    // Summary: Recursively checks if node A is an ancestor of node B.
    // Precondition: current is a valid TreeNode or null; targetB is the name to search for.
    // Postcondition: Returns true if targetB is found in the subtree of current.
    private boolean isAncestor(TreeNode current, String targetB){
        if(current==null)
            return false;
        if(current.name.equals(targetB))
            return true;
        //check all children
        TreeNode child = current.firstChild;
        while(child!=null){
            if(isAncestor(child, targetB))
                return true;
            child=child.nextSibling;
        }
        return false;
    }

    // Summary: Checks if employee A is a manager (ancestor) of employee B and prints result.
    // Precondition: A and B are non-null Strings representing employee names.
    // Postcondition: Prints whether A is a manager of B or not, or error if not found.

    public void checkManager(String A, String B){
        TreeNode emp1= findNode(root, A);
        TreeNode emp2= findNode(root, B);
        if(emp1==null|| emp2==null){
            System.out.println("Employee not found.");
            return;
        }
        if(A.equals(B)){
            System.out.println(A + " is not a manager of " + B + ".");
            return;
        }
        //A is manager of B if B is reachable from A's subtree
        if(isAncestor(emp1, B))
            System.out.println(A + " is a manager of " + B + ".");
        else
            System.out.println(A + " is not a manager of " + B + ".");
    }


    // Summary: Recursively finds the path from 'current' node to the node with 'targetName'.
    // Precondition: current is a valid TreeNode or null; targetName is a non-null String.
    // Postcondition: Returns a String representing the path, or null if not found.

    private String findPathHelper(TreeNode current, String targetName){
        if(current==null)
            return null;
        //found the target, return its name as start of path
        if(current.name.equals(targetName))
            return current.name;
        //search in children
        TreeNode child = current.firstChild;
        while(child!=null){
            String result = findPathHelper(child, targetName);
            if(result!=null)
                return current.name + " -> " + result; //prepend current node to path
            child=child.nextSibling;
        }
        return null; //target not found in this subtree
    }


    // Summary: Finds and prints the path from root to the given employee.
    // Precondition: name is a non-null String.
    // Postcondition: Prints the path in format: A -> B -> C, or an error message.
    public void findPath(String name){
        TreeNode emp = findNode(root, name);
        if(emp==null){
            System.out.println("Employee not found.");
            return;
        }
        String path = findPathHelper(root, name);
        System.out.println("Path: " + path);
    }
}