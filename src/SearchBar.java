import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.stream.Collectors;

public class SearchBar extends JPanel{
    public SearchBar(Vector<Vector<String>> books) {

        // 검색 창 및 버튼 생성
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(50);
        JButton searchButton = new JButton("검색");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);

        searchButton.addActionListener(new MyListener(searchField, books));

        // 검색창을 탭에 추가
        add(searchPanel);
    }
    private class MyListener implements ActionListener{
        Vector<Vector<String>> filteredBooks;
        JTextField text;
        Vector<Vector<String>> books;

        public MyListener(JTextField text, Vector<Vector<String>> books) {
            this.text = text;
            this.books = books;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = text.getText();
            System.out.println(keyword);
            if (!keyword.isEmpty()) {  // Use isEmpty() to check if keyword is empty
                filteredBooks = books.stream()
                        .filter(book -> book != null && book.size() > 1 &&
                                book.get(0) != null && book.get(1) != null &&
                                (book.get(0).contains(keyword) || book.get(1).contains(keyword)))
                        .collect(Collectors.toCollection(Vector::new));

                if (filteredBooks.isEmpty()) {
                    NotfoundBook notFoundBook = new NotfoundBook();
                    notFoundBook.setVisible(true);
                } else {
                    System.out.println(filteredBooks.get(0).get(1));
                    SearchResultDialog searchResultDialog = new SearchResultDialog(filteredBooks);
                    searchResultDialog.setVisible(true);
                }
            } else {
                NeedInput needInput = new NeedInput();
                needInput.setVisible(true);
            }
        }
    }
    public class NotfoundBook extends JDialog {
        public NotfoundBook() {
            setTitle("Can Not Found Book");
            setLayout(new BorderLayout());

            JPanel nullPanel = new JPanel();

            JPanel textPanel = new JPanel();
            JLabel text = new JLabel("찾으시는 책이 목록에 없습니다.");
            textPanel.add(text);

            JPanel buttonPanel = new JPanel();
            JButton button = new JButton("확인");
            button.addActionListener(e->{setVisible(false);});
            buttonPanel.add(button);

            add(nullPanel, BorderLayout.NORTH);
            add(textPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setLocation(650, 300);
            setSize(300,150);
        }
    }

    public class NeedInput extends JDialog {
        public NeedInput() {
            setTitle("Please Enter Book Information");
            setLayout(new BorderLayout());

            JPanel nullPanel = new JPanel();

            JPanel textPanel = new JPanel();
            JLabel text = new JLabel("찾으려는 책 제목이나 작가명을 입력해주세요.");
            textPanel.add(text);

            JPanel buttonPanel = new JPanel();
            JButton button = new JButton("확인");
            button.addActionListener(e->{setVisible(false);});
            buttonPanel.add(button);

            add(nullPanel, BorderLayout.NORTH);
            add(textPanel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setLocation(650, 300);
            setSize(300,150);
        }
    }
}
