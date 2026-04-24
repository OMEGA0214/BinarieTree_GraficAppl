package Model;


import java.util.ArrayList;
import java.util.List;

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

    public String runInOrder(Node<T> node) {
        if (node == null) {
            return "";
        }

        return runInOrder(node.getLeft()) +
                node.getValue() + " " +
                runInOrder(node.getRight());
    }

    public String runPreOrder(Node<T> node) {
        if (node == null) {
            return "";
        }

        return node.getValue() + " " +
                runPreOrder(node.getLeft()) +
                runPreOrder(node.getRight());
    }

    public String runPostOrder(Node<T> node) {
        if (node == null) {
            return "";
        }

        return runPostOrder(node.getLeft()) +
                runPostOrder(node.getRight()) +
                node.getValue() + " ";
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
            }
            Node <T> common = commonNode(root, data1, data2);

            findPathReverse(common.getLeft(), data1);
            findPath(common, data2);
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

    public Node<T> getMayor(Node<T> node) {
        if (node.getRight() == null) {
            return node;
        }
        return getMayor(node.getRight());
    }

    public void deleteTree(){
        root = null;
        size = 0;
    }

    public Node<T> getRoot() {
        return root;
    }

    public int getWidth(Node<T> node) {
        int height = getHeight(node);
        int maxWidth = 0;

        for (int i = 0; i <= height; i++) {
            int width = getWidthLevel(node, i);

            if (width > maxWidth) {
                maxWidth = width;
            }
        }

        return maxWidth;
    }

    private int getWidthLevel(Node<T> node, int level) {
        if (node == null) {
            return 0;
        }

        if (level == 0) {
            return 1;
        }

        return getWidthLevel(node.getLeft(), level - 1) +
                getWidthLevel(node.getRight(), level - 1);
    }

    public void printTree(Node<T> node, int level) {
        if (node != null) {

            printTree(node.getRight(), level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }

            System.out.println(node.getValue());

            printTree(node.getLeft(), level + 1);
        }
    }

    public Node<T> commonNode(Node<T> node, T data1, T data2) {
        if (node == null) {
            return null;
        }
        if (data1.compareTo(node.getValue()) < 0 && data2.compareTo(node.getValue()) < 0) {
            return commonNode(node.getLeft(), data1, data2);
        }
        if (data1.compareTo(node.getValue()) > 0 && data2.compareTo(node.getValue()) > 0) {
            return commonNode(node.getRight(), data1, data2);
        }
        return node;
    }

    public List<Integer> getBreadthPath(Integer data1, Integer data2) {
        List<Integer> path = new ArrayList<>();

        Node<T> common = commonNode(root, (T) data1, (T) data2);

        if (common != null) {
            collectReversePath(common.getLeft(), (T) data1, path);
            collectPath(common, (T) data2, path);
        }
        return path;
    }

    private void collectPath(Node<T> node, T data, List<Integer> path) {
        if (node == null) return;

        path.add((Integer) node.getValue());

        if (node.getValue().equals(data)) {
            return;
        }

        if (data.compareTo(node.getValue()) < 0) {
            collectPath(node.getLeft(), data, path);
        } else {
            collectPath(node.getRight(), data, path);
        }
    }

    private boolean collectReversePath(Node<T> node, T data, List<Integer> path) {
        if (node == null) return false;

        if (node.getValue().equals(data)) {
            path.add((Integer) node.getValue());
            return true;
        }
        boolean found = false;
        if (data.compareTo(node.getValue()) < 0) {
            found = collectReversePath(node.getLeft(), data, path);
        } else {
            found = collectReversePath(node.getRight(), data, path);
        }
        if (found) {
            path.add((Integer) node.getValue());
        }
        return found;
    }

}



