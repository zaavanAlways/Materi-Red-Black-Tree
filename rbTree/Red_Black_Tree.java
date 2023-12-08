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
}

public class Red_Black_Tree {

    public static void main(String[] args) {

    }
}