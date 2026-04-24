import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GeneralTree {
    Scanner keyboard= new Scanner(System.in);

    TreeNode root;
    public GeneralTree(){
        this.root=null;
    }

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
        System.out.println("Subordinates of "+name+": ");
        TreeNode temp =employee.firstChild;

        while(temp!=null){
            System.out.println(temp.name);

            if(temp.nextSibling!=null)
                System.out.println(",");
            temp=temp.nextSibling;

        }
        System.out.println();
    }
    public void findCommonManager(String name1, String name2){

    }
}
