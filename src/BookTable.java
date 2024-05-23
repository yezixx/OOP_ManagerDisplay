import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

public class BookTable extends JPanel{
    DefaultTableModel model;
    JTable bookTable;
    Vector<Vector<String>> bookList;
    public BookTable(Vector<Vector<String>> bookList) {
        this.bookList = bookList;
        JPanel tablePanel = new JPanel(new BorderLayout());

        Vector<String> columnName =  new Vector<>(Arrays.asList("제목", "작가", "ISBN"));
        // 내용 수정 불가 시작 //
        model = new DefaultTableModel(columnName, 0) {
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        bookTable = new JTable(model);
        for(Vector<String> book : bookList){
            model.addRow(book);
        }

        bookTable.addMouseListener(new MyListener());

        bookTable.getColumn("제목").setPreferredWidth(250);
        bookTable.getColumn("작가").setPreferredWidth(250);
        bookTable.getColumn("ISBN").setPreferredWidth(150);

        JScrollPane sp = new JScrollPane(bookTable);
        sp.setPreferredSize(new Dimension(650,450));

        tablePanel.add(sp, BorderLayout.CENTER);

        add(tablePanel);
    }

    public void updateTable() {
        model.setRowCount(0); // 기존 데이터 삭제
        for (Vector<String> book : bookList) {
            model.addRow(book);
        }
    }


    public class MyListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getClickCount() == 2){
                int row = bookTable.getSelectedRow();

                String title = (String) model.getValueAt(row,0);
                String author = (String) model.getValueAt(row,1);
                String ISBN = (String) model.getValueAt(row,2);

                BookInfo selectedBook = new BookInfo(title, author, ISBN);

                SelectTaskDialog selectTaskDialog = new SelectTaskDialog(bookList, selectedBook);
                selectTaskDialog.setVisible(true);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
