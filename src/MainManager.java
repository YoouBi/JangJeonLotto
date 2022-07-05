import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainManager extends JFrame {

	List<List<Integer>> buyLotto = new ArrayList<>();

	MainPage mainPage = new MainPage();
	BuyPage lottoBuy = new BuyPage();
	ResultPage results = new ResultPage();

	JPanel mp = mainPage.getPnl();
	JPanel lb = lottoBuy.getPnl();

	JButton btn1 = mainPage.getStart(); // 여기버튼이거맞아??
	JButton btn2 = lottoBuy.getNextBtn();

	CardLayout layout = new CardLayout();
	JPanel center = new JPanel(layout);

	MainManager() {

		center.add(mp, "A");
		center.add(lb, "B");

		add(center);

		layout.show(center, "A");

		ActionListener nextBtn = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.next(center);
			}
		};

		ActionListener letsGoBuy = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lottoBuy.hardReset();
				layout.next(center);
			}
		};

		ActionListener letsGoResult = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyLotto = lottoBuy.getBuyLotto();

				if (lottoBuy.getLottoNumCount() == 0) {
					JOptionPane.showMessageDialog(null, "로또를 구매하지 않고 결과페이지로 넘어갈 수 없습니다.");
				} else {

					int result = JOptionPane
							.showConfirmDialog(null,
									String.format("복권 수량: %d개\n가격: %d원\n구매 확정하시겠습니까?", lottoBuy.getLottoNumCount(),
											lottoBuy.getLottoNumCount() * 1000),
									"로또 값 확인", JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						int accountMoney = mainPage.getMylottoReserve();
						
						if (accountMoney<lottoBuy.getLottoNumCount() * 1000) {	// 보유금 처리
						JOptionPane.showMessageDialog(null, "보유금이 부족하여 로또를 구매할 수 없습니다.");
						} else {
						mainPage.setMylottoReserve(accountMoney-(lottoBuy.getLottoNumCount() * 1000));
					
						// 이후는 배열 넘겨주고 넘겨받는 부분
						// Iterator로 배열 정리
						Iterator<List<Integer>> check0 = buyLotto.iterator();
						while (check0.hasNext()) {
							if (check0.next().contains(0)) {
								check0.remove();
							}
						}

						// 이부분에 넘겨받고 계산하는 작업이 들어감
						results.setBuyLottoNumList(buyLotto);
						System.out.println("제발 들어가라 = " + results.getBuyLottoNumList());
						results.getLottoNum();

						results.setPanel();

						JPanel re = results.getPnl();
						JButton btn3 = results.getNextBtn();
						btn3.addActionListener(nextBtn);
						center.add(re, "C");
						layout.next(center);
						}
					}
				}
				JOptionPane.showMessageDialog(null, "[관리자 페이지: 로또배열 확인용이며 완성할 때 없애야함]\n" + buyLotto.toString());
			}
		};

		btn1.addActionListener(letsGoBuy);
		btn2.addActionListener(letsGoResult);

		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new MainManager().setVisible(true);
	}
}