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
}
