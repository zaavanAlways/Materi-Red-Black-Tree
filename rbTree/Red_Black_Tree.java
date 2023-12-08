package rbTree;

class RBNode<AnyType> {
    AnyType element;
    RBNode<AnyType> parent;
    RBNode<AnyType> left, right;
    int color; // 1 = red dan 0 = black
}

class RBTree<AnyType extends Comparable<? super AnyType>> {
    private RBNode<AnyType> root;
    private RBNode<AnyType> TNULL;

    public RBTree() {
        TNULL = new RBNode<AnyType>();
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    private void leftRotate(RBNode<AnyType> x) {
        RBNode<AnyType> y = x.right;
        x.right = y.left;

        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(RBNode<AnyType> x) {
        RBNode<AnyType> y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    private void balanceInsert(RBNode<AnyType> N) { // N = node baru
        RBNode<AnyType> U; // Node U = Uncle dari N
        while (N.parent.color == 1) { // parent = red
            if (N.parent == N.parent.parent.right) { // parent = right child dari GP
                U = N.parent.parent.left; // uncle = left child dari GP
                if (U.color == 1) {// uncle = red
                    // case 2
                    // flip color : parent = uncle = black dan GP = red
                    U.color = 0;
                    N.parent.color = 0;
                    N.parent.parent.color = 1;
                    N = N.parent.parent;
                } else { // uncle = black
                    // case 3b
                    if (N == N.parent.right) { // N = right child dari parent
                        N.parent.color = 0;
                        N.parent.parent.color = 1;
                        leftRotate(N.parent.parent); // rotasi GP kearah kiri
                    }
                    // case 3c
                    else if (N == N.parent.left) { // N = left child dari parent
                        N = N.parent;
                        // rotasi parent ke arah kanan
                        rightRotate(N);
                        // flip color
                        N.parent.color = 0;
                        N.parent.parent.color = 1;
                        // rotasi GP kearah kiri
                        leftRotate(N.parent.parent);
                    }
                }
            } else {// parent = left child dari GP
                U = N.parent.parent.right; // uncle = right dari GP
                if (U.color == 1) { // uncle = red
                    // case 2: flip color
                    U.color = 0;
                    N.parent.color = 0;
                    N.parent.parent.color = 1;
                    N = N.parent.parent;
                } else { // uncle = black or null
                    // case 3a
                    if (N == N.parent.left) { // N = left child dari parent
                        // flip color
                        N.parent.color = 0;
                        N.parent.parent.color = 1;
                        // Rotasi GP kearah kanan
                        rightRotate(N.parent.parent);
                    }
                    // case 3d
                    else if (N == N.parent.right) { // N = right child dari parent
                        N = N.parent;
                        // Rotasi parent kearah kiri
                        leftRotate(N);
                        // flip color
                        N.parent.color = 0;
                        N.parent.parent.color = 1;
                        // ROtasi GP kearah kanan
                        rightRotate(N.parent.parent);
                    }
                }
            }
            if (N == root) {
                break;
            }
        }
        root.color = 0; // root harus berwarna black
    }

    public void insert(AnyType N) {
        RBNode<AnyType> node = new RBNode<AnyType>();
        node.parent = null;
        node.element = N;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1; // node baru = red

        RBNode<AnyType> x = root;
        RBNode<AnyType> y = null;

        while (x != TNULL) {
            y = x;
            if (node.element.compareTo(x.element) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.element.compareTo(y.element) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }
        // jika node baru adalah root
        if (node.parent == null) {
            node.color = 0;
            return;
        }
        balanceInsert(node);
    }

    private void colorNode(RBNode<AnyType> root) {
        if (root != TNULL) {
            String nodeColor = root.color == 1 ? "Red" : "Black";
            System.out.println("Node " + root.element + " : " + nodeColor);
            colorNode(root.left);
            colorNode(root.right);
        }
    }

    public void colorNode() {
        colorNode(root);
    }
}

public class Red_Black_Tree {

    public static void main(String[] args) {
        RBTree<Integer> rb = new RBTree<Integer>();
        rb.insert(10);
        rb.insert(2);
        rb.insert(8);
        rb.insert(9);
        rb.insert(3);
        rb.insert(12);
        rb.insert(11);
        rb.insert(15);
        rb.insert(20);
        rb.insert(5);
        rb.colorNode();
    }
}