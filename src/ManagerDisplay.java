import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ManagerDisplay {
    BookCSVController csvReader = new BookCSVController();
    public ManagerDisplay() {
        JFrame frame = new JFrame("Manager Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocation(400, 100);

        Vector<Vector<String>> bookList = csvReader.readCSV();

        JPanel searchBarPanel = new SearchBar(bookList); // search -> lambda stream filter로?
        JPanel bookTablePanel = new BookTable(bookList);
        JPanel addBookPanel = new AddBookButtonPanel(bookList);

        frame.add(searchBarPanel, BorderLayout.NORTH);
        frame.add(bookTablePanel, BorderLayout.CENTER);
        frame.add(addBookPanel, BorderLayout.SOUTH);

        // 프레임을 보이도록 설정
        frame.setVisible(true);
    }
}
