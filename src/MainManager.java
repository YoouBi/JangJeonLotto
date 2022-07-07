import java.awt.CardLayout;
import java.awt.Dimension;
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
	BuyPage buyPage = new BuyPage();
	ResultPage resultPage = new ResultPage();

	JPanel mp = mainPage.getPnl();
	JPanel lb = buyPage.getPnl();

	JButton btn1 = mainPage.getNextBtn(); // 여기버튼이거맞아??
	JButton btn2 = buyPage.getNextBtn();
	JButton logoutBtn = buyPage.getLogout();
	JButton myPageBtn = buyPage.getMypage();

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
				buyPage.hardReset();
				layout.next(center);
			}
		};

		ActionListener letsGoResult = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buyLotto = buyPage.getBuyLotto();

				if (buyPage.getLottoNumCount() == 0) {
					JOptionPane.showMessageDialog(null, "로또를 구매하지 않고 결과페이지로 넘어갈 수 없습니다.");
				} else {

					int result = JOptionPane
							.showConfirmDialog(null,
									String.format("복권 수량: %d개\n가격: %d원\n구매 확정하시겠습니까?", buyPage.getLottoNumCount(),
											buyPage.getLottoNumCount() * 1000),
									"로또 값 확인", JOptionPane.OK_CANCEL_OPTION);

					if (result == JOptionPane.OK_OPTION) {
						int accountMoney = ((login) mainPage.getMap().get(mainPage.getId().getText())).getLottoReserve();
						
						if (accountMoney<buyPage.getLottoNumCount() * 1000) {	// 보유금 처리
							JOptionPane.showMessageDialog(null, "보유금이 부족하여 로또를 구매할 수 없습니다.");
						} else {
							int remainingReserve = accountMoney-(buyPage.getLottoNumCount() * 1000);
							((login) mainPage.getMap().get(mainPage.getId().getText())).setLottoReserve(remainingReserve);
							(mainPage.getMypageReserve()).setText("보유금 : " + remainingReserve); // 라벨의 텍스트
					
						// 이후는 배열 넘겨주고 넘겨받는 부분
						// Iterator로 배열 정리
						Iterator<List<Integer>> check0 = buyLotto.iterator();
						while (check0.hasNext()) {
							if (check0.next().contains(0)) {
								check0.remove();
							}
						}

						// 이부분에 넘겨받고 계산하는 작업이 들어감
						resultPage.setBuyLottoNumList(buyLotto);
						resultPage.setTotalMoney(mainPage.getTotalLotteWinnings());
						resultPage.getLottoNum();
						System.out.println(resultPage.getTotalMoney());
						mainPage.setTotalLotteWinnings(resultPage.getTotalMoney());
						mainPage.setTotalLotteWinnings(resultPage.getTotalMoney());
						mainPage.getLottoTotalMoney().setText("당첨금 " + resultPage.getTotalMoney() + "원!!!");
						
						String idStr = mainPage.getId().getText();
						if(mainPage.getId().getText().equals("nonmember")) {
							((login) mainPage.getMap().get(mainPage.getId().getText())).setLottoReserve(5000);
							mainPage.setId("");
						}
						
						JPanel re = resultPage.getPnl();
				
						JButton btn3 = resultPage.getNextBtn();
						btn3.addActionListener(nextBtn);
						System.out.println("여기선 몇 개?" + center.getComponentCount());
						if(center.getComponentCount() == 3) {
							center.remove(2);
							System.out.println("요기는?" + center.getComponentCount());
						}
						center.add(re, "C");
						System.out.println("카드레이아웃 몇개?" + center.getComponentCount());
						
						layout.next(center);
						}
					}
				}
				JOptionPane.showMessageDialog(null, "[임시팝업: 배열 확인용]\n" + buyLotto.toString());
			}
		};
		
		ActionListener letsLogout = new ActionListener() { // 구입페이지 로그아웃 구현
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mainPage.getId().getText().equals("nonmember")) {
					mainPage.setId("");
				}
				layout.previous(center);
				mainPage.getSignout().doClick();
			}
		};
		
		ActionListener letsGoMP = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.previous(center);
			}
		};

		btn1.addActionListener(letsGoBuy);
		btn2.addActionListener(letsGoResult);
		logoutBtn.addActionListener(letsLogout);
		myPageBtn.addActionListener(letsGoMP);

		setSize(900, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new MainManager().setVisible(true);
	}
}