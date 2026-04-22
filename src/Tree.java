public class Tree <T extends Comparable<T>> {
    private Node<T> root;
    private int size;

    public Tree() {
        this.root = null;
    }
    public boolean isEmpty() {
        return root == null;
    }
    public void insert(T data) {
        if (isEmpty()) {
            root = new Node<>(data);
            size++;
        } else {
            if (root.insert(root, data)) {
                size++;
            }
        }
    }
    public int getHeight(Node<T> node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = getHeight(node.getLeft());
        int rightHeight = getHeight(node.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int getSize() {
        return size;
    }

    public void runInOrder(Node<T> node) {
        if (node != null) {
            runInOrder(node.getLeft());
            System.out.print(node.getValue() + " ");
            runInOrder(node.getRight());
        }
    }

    public void runPreOrder(Node<T> node) {
        if (node != null) {
            System.out.print(node.getValue() + " ");
            runPreOrder(node.getLeft());
            runPreOrder(node.getRight());
        }
    }

    public void runPostOrder(Node<T> node) {
        if (node != null) {
            runPostOrder(node.getLeft());
            runPostOrder(node.getRight());
            System.out.print(node.getValue() + " ");
        }
    }

    public int getLevel(Node<T> node, T data, int level) {
        if (node == null) {
            return -1;
        }
        if (node.getValue().equals(data)) {
            return level;
        }
        int leftLevel = getLevel(node.getLeft(), data, level + 1);
        if (leftLevel != -1) {
            return leftLevel;
        }
        return getLevel(node.getRight(), data, level + 1);
    }

}
