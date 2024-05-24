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
            if (!keyword.isEmpty()) { // 검색어가 비어있지 않을 때
                filteredBooks = books.stream()
                        .filter(book -> book != null && book.size() > 1 &&
                                book.get(0) != null && book.get(1) != null &&
                                (book.get(0).contains(keyword) || book.get(1).contains(keyword)))
                        .collect(Collectors.toCollection(Vector::new));

                if (filteredBooks.isEmpty()) { // 검색 결과가 없을 때 -> 오류 메시지 출력
                    JOptionPane.showMessageDialog(null, "찾으시는 책이 목록에 없습니다.", "검색 결과 오류", JOptionPane.ERROR_MESSAGE);
                } else { // 검색 결과가 있을 때 -> 검색 결과 다이얼로그 띄움
                    SearchResultDialog searchResultDialog = new SearchResultDialog(filteredBooks);
                    searchResultDialog.setVisible(true);
                }
            } else { // 검색어가 비어있을 때 -> 오류 메시지 출력
                JOptionPane.showMessageDialog(null, "찾으려는 책 제목이나 작가명을 입력해주세요.", "도서 입력 오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
