import javax.swing.*;
import java.awt.*;

public class CompleteTaskDialog extends JDialog {
    public CompleteTaskDialog(String task) {
        setTitle("Complete Dialog");

        JPanel panel = new JPanel();

        JPanel completePanel=new JPanel();
        JLabel completeLabel = new JLabel("도서 "+task+" 이(가) 완료되었습니다.");
        completePanel.add(completeLabel);

        JPanel buttonPanel = new JPanel();
        JButton completeButton = new JButton("확인");
        completeButton.addActionListener(e->{
            dispose();
        });
        buttonPanel.add(completeButton);

        panel.add(completePanel);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        setLocation(650, 300);
        setSize(300, 150);

    }
}
