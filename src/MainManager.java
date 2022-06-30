import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainManager extends JFrame {
	
	
	
	MainPage mainPage = new MainPage();
	BuyPage lottoBuy = new BuyPage();
	ResultPage results = new ResultPage();
	
	JPanel mp = mainPage.getPnl();
	JPanel lb = lottoBuy.getPnl();
	JPanel re = results.getPnl();
	
	JButton btn1 = mainPage.getStart();	// 여기버튼이거맞아??
	JButton btn2 = lottoBuy.getNextBtn();
	JButton btn3 = results.getNextBtn();
	
	CardLayout layout = new CardLayout();
	JPanel center = new JPanel(layout);
	
	MainManager() {
	
	center.add(mp, "A");
	center.add(lb, "B");
	center.add(re, "C");
	
	add(center);
	
	layout.show(center, "A");
	
	ActionListener nextBtn = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			layout.next(center);
		}
	};
	
	btn1.addActionListener(nextBtn);
	btn2.addActionListener(nextBtn);
	btn3.addActionListener(nextBtn);
	
	setSize(800,500);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	}
	
	
	public static void main(String[] args) {
		new MainManager().setVisible(true);
	}
}
