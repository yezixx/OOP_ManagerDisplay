import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class ManagerDisplay {
    BookCSVController bookCSVController = new BookCSVController(); // bookList를 읽어오기 위한 객체
    public ManagerDisplay() {
        JFrame frame = new JFrame("관리자 화면"); // 프레임 생성
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 닫히면 프로그램 종료
        frame.setSize(800, 600); // 프레임 크기 설정
        frame.setLocation(400, 100); // 프레임 위치 설정

        Vector<Vector<String>> bookList = bookCSVController.readCSV(); // csv파일로부터 bookList를 읽어옴

        JPanel searchBarPanel = new SearchBar(bookList); // 검색창
        JPanel bookTablePanel = new BookTable(bookList); // 도서 목록 테이블

        frame.add(searchBarPanel, BorderLayout.NORTH);
        frame.add(bookTablePanel, BorderLayout.CENTER);

        // 프레임을 보이도록 설정
        frame.setVisible(true);
    }
}
