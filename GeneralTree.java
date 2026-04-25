import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GeneralTree {


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
    public int calculateSalary(TreeNode node){
        if(node==null)
            System.out.println("Employee not found.");

        TreeNode child= node.firstChild;
        int sum= node.salary;

        while(child!=null){
        sum += calculateSalary(child);
        child=child.nextSibling;
        }
        return sum;
    }

    public void checkManager(TreeNode current, String A, String B){
        TreeNode emp1= findNode(root, A);
        TreeNode emp2= findNode(root, B);
        if(emp1==null|| emp2==null)
            System.out.println("Employee not found.");

        while(current.firstChild!= null){



        }
    }
}
