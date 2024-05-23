import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SelectTaskDialog extends JDialog {
    public SelectTaskDialog(Vector<Vector<String>> books, BookInfo book){
        setTitle("Update Or Delete");

        JPanel confirmPanel=new JPanel();
        JLabel confirmLabel = new JLabel("원하시는 업무를 선택해주세요.");
        confirmPanel.add(confirmLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton updateButton = new JButton("수정");
        JButton deleteButton = new JButton("삭제");

        updateButton.addActionListener(e -> {
            UpdateBookDialog ubd = new UpdateBookDialog(books, book);
            ubd.setVisible(true);
            setVisible(false);
        });

        deleteButton.addActionListener(new MyListener(books, book));

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(confirmPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocation(650, 300);
        setSize(300, 150);
    }

    public class MyListener implements ActionListener {
        Vector<Vector<String>> books;
        BookInfo book;
        public MyListener(Vector<Vector<String>> books, BookInfo book) {
            this.books = books;
            this.book = book;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 완료 버튼 -> dialog 띄움
            ConfirmTaskDialog dialog = new ConfirmTaskDialog("삭제");
            dialog.setVisible(true);

            // 대기 스레드 실행
            SelectTaskDialog.MyListener.WaitingThread waitingThread = new SelectTaskDialog.MyListener.WaitingThread(dialog);
            waitingThread.start();
        }

        private class WaitingThread extends Thread {
            private ConfirmTaskDialog confirmTaskDialog;

            public WaitingThread(ConfirmTaskDialog dialog) {
                this.confirmTaskDialog = dialog;
            }

            @Override
            public void run() {
                // dialog가 닫힐 때까지 기다림
                while (confirmTaskDialog.isVisible()) {
                    try {
                        Thread.sleep(100); // 다이얼로그가 닫힐 때까지 대기
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // dialog에서 완료 버튼 눌렀을 때만 수행
                if (confirmTaskDialog.isConfirmed()) {
                    BookCSVController bookCsvController = new BookCSVController();
                    bookCsvController.deleteBook(books, book);
                    dispose();
                    CompleteTaskDialog complete = new CompleteTaskDialog("삭제");
                    complete.setVisible(true);
                }
            }
        }
    }
}
