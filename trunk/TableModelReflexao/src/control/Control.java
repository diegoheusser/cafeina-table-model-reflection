//Control.java
package control;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import model.Author;
import model.Book;
import view.GUIScreen;
import view.TableModel;

/**
 * @author Diego Heusser
 */
public class Control {

    private GUIScreen frame;

    //Builder
    public Control() {
        frame = new GUIScreen();
        data();
    }

    public void execute() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((screen.width - frame.getSize().width) / 2,
                        (screen.height - frame.getSize().height) / 2);
                frame.setVisible(true);
            }
        });
    }

    private void data() {
        List<Object> liAuthor = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setCode(i);
            author.setName("Author_"+i);
            liAuthor.add(author);
        }
        
        List<Object> liBook = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setCode(i);
            book.setAuthor((Author)liAuthor.get(i));
            book.setTitle("Title_"+i);
            liBook.add(book);
        }
        
        TableModel tmAuthor = new TableModel(liAuthor);
        TableModel tmBook = new TableModel(liBook);
        
        frame.tbAuthor.setModel(tmAuthor);
        frame.tbBook.setModel(tmBook);
    }
}
