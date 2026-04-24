package View;
import Model.Tree;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame  {
    private Tree<Integer> tree;
    private TreePanel treePanel;
    private JTextField input;
    private JTextField input2;
    private JTextArea output;

    public MainFrame() {
        tree = new Tree<>();

        setTitle("Árbol Binario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior (controles)
        JPanel topPanel = new JPanel();

        input = new JTextField(10);
        input2 = new JTextField(10);
        input2.setVisible(false);
        JButton insertButton = new JButton("Insertar");
        JButton deleteButton = new JButton("Eliminar");
        JButton heightButton = new JButton("Altura");
        JButton leafButton = new JButton("Hojas");
        JButton clearButton = new JButton("Limpiar");
        JButton pathButton = new JButton("Camino");

        topPanel.add(new JLabel("Dato 1:"));
        topPanel.add(input);

        JLabel label2 = new JLabel("Dato 2:");
        label2.setVisible(false);
        topPanel.add(label2);
        topPanel.add(input2);

        topPanel.add(insertButton);
        topPanel.add(deleteButton);
        topPanel.add(heightButton);
        topPanel.add(leafButton);
        topPanel.add(clearButton);
        topPanel.add(pathButton);

        add(topPanel, BorderLayout.NORTH);

        // Panel del árbol
        treePanel = new TreePanel(tree);
        add(treePanel, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(output);
        add(scroll, BorderLayout.SOUTH);
        // Área de resultados
        output = new JTextArea(8, 20);
        output.setEditable(false);

        // INSERTAR
        insertButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(input.getText());
                tree.insert(value);
                treePanel.repaint();
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido");
            }
        });
        // ELIMINAR
        deleteButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(input.getText());
                tree.delete(value);
                treePanel.repaint();
                output.append("Eliminado: " + value + "\n");
                input.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido");
            }
        });

        // ALTURA
        heightButton.addActionListener(e -> {
            int height = tree.getHeight(tree.getRoot());
            output.append("Altura del árbol: " + height + "\n");
        });

        // HOJAS
        leafButton.addActionListener(e -> {
            int leaves = tree.countLeaf(tree.getRoot());
            output.append("Cantidad de hojas: " + leaves + "\n");
        });

        // LIMPIAR
        clearButton.addActionListener(e -> {
            tree.deleteTree();
            treePanel.repaint();
            output.append("Árbol eliminado\n");
        });
        // CAMINO ENTRE NODOS
        pathButton.addActionListener(e -> {
            label2.setVisible(true);
            input2.setVisible(true);

            topPanel.revalidate();
            topPanel.repaint();

            output.append("Ingrese el segundo dato para mostrar el camino\n");
        });
    }
}
