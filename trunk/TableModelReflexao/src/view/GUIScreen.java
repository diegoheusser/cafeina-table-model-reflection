//Screen.java
package view;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Diego Heusser
 */
public class GUIScreen extends JFrame {

    public JScrollPane spAuthor;
    public JScrollPane spBook;
    public JTable tbAuthor;
    public JTable tbBook;

    //builder
    public GUIScreen() {
        initComponents();
    }

    private void initComponents() {
        spAuthor = new JScrollPane();
        tbAuthor = new JTable();
        spBook = new JScrollPane();
        tbBook = new JTable();

        setTitle("TableModel");
        setResizable(false);
        getContentPane().setLayout(null);
        setSize(new Dimension(417, 147));
        
        spAuthor.setViewportView(tbAuthor);

        getContentPane().add(spAuthor);
        spAuthor.setBounds(10, 11, 192, 100);

        spBook.setViewportView(tbBook);

        getContentPane().add(spBook);
        spBook.setBounds(208, 11, 192, 100);

    }

}
