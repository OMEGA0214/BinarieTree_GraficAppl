
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

        public Node<T> search(Node<T> nod, T data){
            int comparison = data.compareTo(nod.getValue());
            if(comparison < 0) {
                if(nod.getLeft() != null) {
                    return search(nod.getLeft(), data);
                }
            }
            else if(comparison > 0) {
                if(nod.getRight() != null) {
                    return search(nod.getRight(), data);
                }
            }
            else if(comparison == 0) {
                return nod;
            }
            return null;

        }

        public boolean delete(Node<T> node, T date) {
            if (node == null) {
                System.out.println("Dato no encontrado");
                return false;
            }
            int comparation = date.compareTo(node.getValue());

            if (comparation < 0) {
                if (node.getLeft() != null && date.compareTo(node.getLeft().getValue()) == 0) {
                    Node<T> aux = node.getLeft();
                    if (aux.getLeft() == null && aux.getRight() == null) {
                        node.setLeft(null);
                    } else if (aux.getLeft() == null) {
                        node.setLeft(aux.getRight());
                    } else if (aux.getRight() == null) {
                        node.setLeft(aux.getLeft());
                    } else {
                        Node<T> next = findNext(aux.getRight());
                        T nextValue = next.getValue();
                        delete(aux, nextValue);
                        aux.setValue(nextValue);
                    }
                    return true;
                }
                return delete(node.getLeft(), date);

            } else if (comparation > 0) {
                if (node.getRight() != null && date.compareTo(node.getRight().getValue()) == 0) {
                    Node<T> aux = node.getRight();
                    if (aux.getLeft() == null && aux.getRight() == null) {
                        node.setRight(null);
                    } else if (aux.getLeft() == null) {
                        node.setRight(aux.getRight());
                    } else if (aux.getRight() == null) {
                        node.setRight(aux.getLeft());
                    } else {
                        Node<T> next = findNext(aux.getRight());
                        T nextValue = next.getValue();
                        delete(aux, nextValue);
                        aux.setValue(nextValue);
                    }
                    return true;
                }
                return delete(node.getRight(), date);
            } else if (comparation == 0) {
                if (node.getLeft() != null && node.getRight() == null) {
                    node = null;
                } else if (node.getLeft() == null) {
                    node = node.getRight();
                } else if (node.getRight() == null) {
                    node = node.getLeft();
                } else {
                    Node<T> next = findNext(node.getRight());
                    T nextValue = next.getValue();
                    delete(node, nextValue);
                    node.setValue(nextValue);
                }
            }
            return true;
        }

        public Node<T> findNext(Node<T> node) {
            if (node.getLeft() != null && node.getLeft().getLeft() != null) {
                return findNext(node.getLeft());
            }

            if (node.getLeft() != null) {
                Node<T> temp = node.getLeft();
                node.setLeft(null);
                return temp;
            }
            Node<T> temp = node;
            return temp;
        }



}
