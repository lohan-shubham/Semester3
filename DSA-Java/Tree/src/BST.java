public class BST {
    static Node root;
    static int c = 0;

    public static void insert(int data) {

        root = insert(root, data);

    }
    
    public static Node insert(Node root, int data) {

        if (root == null) {
            root = new Node(data);
            return root;
        }

        if (data < root.data) {
            root.left = insert(root.left, data);
//			System.out.println(root.left.data+" left");
        } else if (data > root.data) {
            root.right = insert(root.right, data);
//			System.out.println(root.right.data+" right");
        }
        return root;

    }

    public static void InOrder() {
        inorder_rec(root);
    }

    public static void inorder_rec(Node root) {
        if (root != null) {
            inorder_rec(root.left);
            System.out.print(root.data + " ");
            inorder_rec(root.right);
        }
    }

    public static void Reverse_InOrder() {
        reverse_inorder_rec(root);
    }

    public static void reverse_inorder_rec(Node root) {
        if (root != null) {
            reverse_inorder_rec(root.right);
            System.out.print(root.data + " ");
            reverse_inorder_rec(root.left);
        }
    }

    public static void PreOrder() {
        preorder_rec(root);
    }

    public static void preorder_rec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder_rec(root.left);
            preorder_rec(root.right);
        }
    }

    public static void PostOrder() {
        postorder_rec(root);
    }

    public static void postorder_rec(Node root) {
        if (root != null) {
            postorder_rec(root.left);
            postorder_rec(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void count() {
        System.out.println(count_rec(root));
    }

    public static int count_rec(Node root) {
        if (root != null) {
            c++;
            count_rec(root.left);
            count_rec(root.right);
        }
        return c;
    }

    public static void height_tree() {
        System.out.println(height_tree(root));
    }

    public static int height_tree(Node root) {
        if (root == null)
            return 0;

        else
            return 1 + Math.max(height_tree(root.left), height_tree(root.right));

    }

    static Node Copy(Node root) {
        if (root == null) {
            return null;

        }
        Node copy = new Node(root.data);
        copy.left = Copy(root.left);
        copy.right = Copy(root.right);
        return copy;
    }

    public static void search(int target) {
        System.out.println(search(root, target));
    }

    public static boolean search(Node root, int target) {
        Node temp = root;
        while (temp != null) {
            if (temp.data == target)
                return true;
            else if (temp.data > target)
                return search(root.left, target);
            else
                return search(root.right, target);
        }
        return false;
    }

    public static Node minNode(Node root) {
        if (root.left == null)
            return root;
        else
            return minNode(root.left);
    }

    public static Node maxNode(Node root) {
        if (root.right == null)
            return root;
        else
            return maxNode(root.right);
    }

    public static Node delete(Node root, int target) {
        if (root == null)
            return null;
        if (root.data > target)
            root.left = delete(root.left, target);
        else if (root.data < target)
            root.right = delete(root.right, target);
        else {

            if (root.left != null && root.right != null) // if node have two children
            {
                Node temp = root;
                Node minRight = minNode(temp.right); // Finding minimum element from right
                root.data = minRight.data;
                delete(root.right, minRight.data);
            } else if (root.left != null)
                root = root.left;

            else if (root.right != null)
                root = root.right;
            else
                root = null;

        }
        return root;
    }

    static void form_BST(int[] preOrder, int[] postOrder) {
        int mid = preOrder.length / 2;
        for (int i = 0; i < mid + 1; i++) {
            insert(preOrder[i]);
            insert(postOrder[postOrder.length - 1 - i]);
        }
    }

    public static void main(String[] args) {
        // insert(25);
        // insert(20);
        // insert(27);
        // insert(15);
        // insert(22);
        // insert(26);
        // insert(30);
        // insert(29);
        // insert(32);
        // insert(25);
        for (int i = 1; i <= 5; i++) {
            insert(i);
        }
//		System.out.println("Inorder");
        InOrder();
//		System.out.println();
        System.out.println("Preorder");
        PreOrder();
        System.out.println("Postorder");
        // Postorder();
//		System.out.println();
//		System.out.println("Postorder");
//		PostOrder();
//		System.out.println();
//		System.out.print("Count ");
//		count();
//		System.out.println("root "+root.data);
//		height_tree();
//		search(15);
//		System.out.println(smallest(root));
//		delete(root, 15);
//		System.out.println();
//		InOrder();
//		System.out.println();
//		System.out.println(maxNode(root).data);
//		int[] A = { 25, 20, 15, 0, 22, 27, 26, 30, 29, 32 };
//		int[] B = { 0, 15, 22, 20, 26, 29, 32, 30, 27, 25 };
//		form_BST(A, B);
//		InOrder();
//		System.out.println();
//		PreOrder();
//		System.out.println();
//		PostOrder();
//		System.out.println();
//		Reverse_InOrder();

    }

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }
}
