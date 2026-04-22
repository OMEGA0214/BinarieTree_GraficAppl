
    public class Node<T extends Comparable<T>> {
        private T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T dato) {
            this.value = dato;
            this.left = null;
            this.right = null;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getLeft() {
            return left;
        }

        public void setLeft(Node<T> left) {
            this.left = left;
        }

        public Node<T> getRight() {
            return right;
        }

        public void setRight(Node<T> right) {
            this.right = right;
        }

        public boolean insert(Node<T> node, T data) {
            int comparison = data.compareTo(node.getValue());

            if (comparison < 0) {
                if (node.getLeft() == null) {
                    node.setLeft(new Node<>(data));
                    return true;
                } else {
                    return insert(node.getLeft(), data);
                }
            } else if (comparison > 0) {
                if (node.getRight() == null) {
                    node.setRight(new Node<>(data));
                    return true;
                } else {
                    return insert(node.getRight(), data);
                }
            } else {
                System.out.println("Data already exists");
                return false;
            }
        }

        public boolean search(Node<T> nod, T data){
            int comparison = data.compareTo(nod.getValue());
            if(comparison < 0) {
                if(nod.getLeft() != null) {
                    return search(nod.getLeft(), data);
                }
                else {
                    return false;
                }
            }
            else if(comparison > 0) {
                if(nod.getRight() != null) {
                    return search(nod.getRight(), data);
                }
                else {
                    return false;
                }
            }
            else if(comparison == 0) {
                return true;
            }
            return false;
        }


    }
