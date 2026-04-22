import java.util.ArrayList;


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

    public int countLeaf(Node<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        return countLeaf(node.getLeft()) + countLeaf(node.getRight());
    }

    public Node<T> getMinor(Node<T> node) {
        if (node.getLeft() == null) {
            return node;
        }
        return getMinor(node.getLeft());
    }

    public void printBreadth(T data1, T data2) {
        if (data1.equals(data2)) {
            System.out.print(data1);
        }
        int comparision = data1.compareTo(root.getValue());

        Node<T> aux = search(data2);
        if (aux != null) {
            if (comparision == 0) {
                int cmparision2 = data2.compareTo(root.getValue());
                if (cmparision2 < 0) {
                    findPath(root.getLeft(), data2);
                } else if (cmparision2 > 0) {
                    findPath(root.getRight(), data2);
                }
            } else if (comparision < 0) {
                findPath(root.getLeft(), data1);
                findPath(root, data2);
            } else if (comparision > 0) {
                findPath(root.getRight(), data1);
                findPath(root, data2);
            }
        }
    }

    public Node<T> search(T data) {
        return root.search(root, data);
    }

    public void findPath(Node<T> node, T data) {
        if (node != null) {
            if (node.getValue().equals(data)) {
                System.out.print(node.getValue() + " ");
                return;
            }
            if (data.compareTo(node.getValue()) < 0) {
                findPathReverse(node.getLeft(), data);
                System.out.print(node.getValue() + " ");
            } else if (data.compareTo(node.getValue()) > 0) {
                System.out.print(node.getValue() + " ");
                findPath(node.getRight(), data);
            }
        }
    }

    public void findPathReverse(Node<T> node, T data) {
        if (node != null) {
            if (node.getValue().equals(data)) {
                System.out.print(node.getValue() + " ");
                return;
            }

            if (data.compareTo(node.getValue()) < 0) {
                findPathReverse(node.getLeft(), data);
            } else if (data.compareTo(node.getValue()) > 0) {
                findPathReverse(node.getRight(), data);
            }

            System.out.print(node.getValue() + " ");
        }
    }

    public void delete(T date) {
        if(root.delete(root, date)){
            size--;
            System.out.println("Date eliminated: " + date);
        }
    }

}



