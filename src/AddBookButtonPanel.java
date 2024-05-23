import javax.swing.*;
import java.util.Vector;

public class AddBookButtonPanel extends JPanel {
    public AddBookButtonPanel(Vector<Vector<String>> bookList) {
        JButton addBook = new JButton("도서 추가");
        AddBookDialog addBookDialog = new AddBookDialog(bookList);
        addBook.addActionListener(e->{
            addBookDialog.setVisible(true);
        });
        add(addBook);
    }
}
