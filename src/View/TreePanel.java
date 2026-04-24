package View;
import Model.Tree;
import Model.Node;

import javax.swing.*;
import java.awt.*;
public class TreePanel  extends JPanel {

    private Tree<Integer> tree;

    public TreePanel(Tree<Integer> tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (tree.getRoot() != null) {
            drawTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawTree(Graphics g, Node<Integer> node, int x, int y, int offset) {
        if (node == null) return;

        // dibujar nodo
        g.drawOval(x, y, 30, 30);
        g.drawString(node.getValue().toString(), x + 10, y + 20);

        // izquierda
        if (node.getLeft() != null) {
            g.drawLine(x + 15, y + 30, x - offset + 15, y + 80);
            drawTree(g, node.getLeft(), x - offset, y + 80, offset / 2);
        }

        // derecha
        if (node.getRight() != null) {
            g.drawLine(x + 15, y + 30, x + offset + 15, y + 80);
            drawTree(g, node.getRight(), x + offset, y + 80, offset / 2);
        }
    }

}
