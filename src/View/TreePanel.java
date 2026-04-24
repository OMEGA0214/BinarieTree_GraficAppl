package View;
import Model.Tree;
import Model.Node;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class TreePanel  extends JPanel {
    private List<Integer> highlightedPath = new ArrayList<>();
    private Tree<Integer> tree;

    public TreePanel(Tree<Integer> tree) {
        this.tree = tree;
    }
    public void setHighlightedPath(List<Integer> path) {
        highlightedPath = path;
        repaint();
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

        // COLOR SOLO DEL NODO
        if (highlightedPath.contains(node.getValue())) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLACK);
        }

        g.drawOval(x, y, 30, 30);
        g.drawString(node.getValue().toString(), x + 10, y + 20);

        // IZQUIERDA
        if (node.getLeft() != null) {

            if (highlightedPath.contains(node.getValue()) &&
                    highlightedPath.contains(node.getLeft().getValue())) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawLine(x + 15, y + 30, x - offset + 15, y + 80);

            drawTree(g, node.getLeft(), x - offset, y + 80, offset / 2);
        }

        // DERECHA
        if (node.getRight() != null) {

            if (highlightedPath.contains(node.getValue()) &&
                    highlightedPath.contains(node.getRight().getValue())) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLACK);
            }

            g.drawLine(x + 15, y + 30, x + offset + 15, y + 80);

            drawTree(g, node.getRight(), x + offset, y + 80, offset / 2);
        }
    }

}
