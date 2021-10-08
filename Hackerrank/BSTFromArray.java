import java.util.ArrayList;

public class BSTFromArray {

    public static BinaryTreeNode<Integer> SortedArrayToBSTHelper (int[] arr, int start, int end){
        if(start > end) {
            return null;
        }
            int mid = (start + end) / 2;
            BinaryTreeNode<Integer> root = new BinaryTreeNode(arr[mid]);

            root.left = SortedArrayToBSTHelper(arr, start, mid - 1);
            root.right = SortedArrayToBSTHelper(arr, mid + 1, end);

            return root;

    }

    public static Pair<Node<Integer>, Node<Integer>> BSTtoLL(BinaryTreeNode<Integer> root){
        if(root == null){
            Pair<Node<Integer>, Node<Integer>> out = new Pair<>();
            out.first = null;
            out.second = null;
            return out;
        }

        Pair<Node<Integer>, Node<Integer>> p1 = BSTtoLL(root.left);
        Pair<Node<Integer>, Node<Integer>> p2 = BSTtoLL(root.right);

        Node<Integer> node = new Node<>(root.data);

        if(p1.second != null)
            p1.second.next = node;
        else
            p1.first = node;

        if(p2.first != null)
            node.next = p2.first;
        else
            p2.second = node;

        Pair<Node<Integer>, Node<Integer>> out = new Pair<>();
        out.first = p1.first;
        out.second = p2.second;
        return out;
    }

    public static ArrayList<Integer> getRootToNodePath(BinaryTreeNode<Integer> root, int data){
        if(root == null){
            return null;
        }

        if(root.data == data){
            ArrayList<Integer> path = new ArrayList<>();
            path.add(root.data);
            return path;
        }

        if(root.data > data){
            ArrayList<Integer> path = getRootToNodePath(root.left, data);
            path.add(root.data);
            return path;
        }

        if (root.data < data){
            ArrayList<Integer> path = getRootToNodePath(root.right, data);
            path.add(root.data);
            return path;
        }else {
            return null;
        }
    }

    public static void printLL(Node<Integer> head){
        Node<Integer> temp = head;

        while(temp.next != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
    }

    public static BinaryTreeNode<Integer> SortedArrayToBST(int[] arr){

        return SortedArrayToBSTHelper(arr,0,arr.length-1);

    }

    public static void print(BinaryTreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        String toBePrinted = root.data + "-> ";
        if (root.left != null) {
            toBePrinted += "L:" + root.left.data + " ,";
        }
        if (root.right != null) {
            toBePrinted += "R:" + root.right.data;
        }
        System.out.println(toBePrinted);
        print(root.left);
        print(root.right);
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        BinaryTreeNode<Integer> root = SortedArrayToBST(array);
        print(root);
        Pair<Node<Integer>, Node<Integer>> head = BSTtoLL(root);
        System.out.println(head.second);
        printLL(head.first);
        System.out.println(getRootToNodePath(root,5));

    }
}
