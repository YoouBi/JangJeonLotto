import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class BuyPage extends JFrame {
	////////////////////////
	Random random = new Random(); // 자동 반자동 할 때 쓰는 랜덤 객체
	int numcount = 0; // 숫자 세는 변수: 6개만 써야 하는걸로
	int LottoNumCount = 0;
	List<Integer> inputLottoNum = new ArrayList<>();
	JLabel[][] lottoField = new JLabel[5][10];

	//////////////////////// 이 아래는 넘겨줄 거

	List<List<Integer>> buyLotto = new ArrayList<>(); // 산 로또 목록 리스트:: 1~5개 가변적

	JPanel pnl = new JPanel(); // 넘겨줄 J패널
	JButton nextBtn = new JButton("결과 보기"); // 로또 결과 보는 버튼
	JLabel lottoPrice = new JLabel("금액: 0원"); //

	/////////////////// getter

	public JPanel getPnl() { // J패널 넘겨주는 게터
		return pnl;
	}

	public JButton getNextBtn() { // 카드뉴스 순환버튼 넘겨주는 버튼
		return nextBtn;
	}

	public List<List<Integer>> getBuyLotto() { // 산 로또 목록 넘겨주기
		return buyLotto;
	}

	/////////////////// 생성자

	BuyPage() {

		//////////// 전체 레이아웃
		pnl.setBackground(new Color(248, 202, 204));
		JPanel inputPnl = new JPanel();
		inputPnl.setOpaque(false);
		BoxLayout inputpnlLayout = new BoxLayout(inputPnl, BoxLayout.Y_AXIS);
		JPanel editPnl = new JPanel();
		BoxLayout editPnlLayout = new BoxLayout(editPnl, BoxLayout.Y_AXIS);
		editPnl.setLayout(editPnlLayout);
		editPnl.setOpaque(false);
		BoxLayout pnlLayout = new BoxLayout(pnl, BoxLayout.X_AXIS);
		pnl.setLayout(pnlLayout);
		inputPnl.setLayout(inputpnlLayout);

		pnl.add(inputPnl);
		pnl.add(editPnl);

		add(pnl);
		////////////////////////////////////
		// Edit
		/////// field
		
		makefield(lottoField);
		for (int i=0; i<lottoField.length; i++) {
			JPanel a = new JPanel();
			a.setOpaque(false);
			for (int j=0; j<lottoField[0].length; j++) {
				 a.add(lottoField[i][j]);
			}
			editPnl.add(a);
		}
		
		editPnl.add(lottoPrice);
		editPnl.add(nextBtn);
		

		/// inputPnl
		////////////////////////////////////////////

		JPanel btnBox = new JPanel(); // 1~45 버튼 감싸는 박스
		btnBox.setOpaque(false);
		JButton inputBtn = new JButton("입력");
		JButton resetBtn = new JButton("reset");
		JButton randomBtn = new JButton("Random");
		JPanel optionBtnBox = new JPanel(); // 선택버튼들 감싸는 파일: 위의 JBtn 3개 들어감
		optionBtnBox.setOpaque(false);
		inputPnl.add(btnBox);
		inputPnl.add(optionBtnBox);
		optionBtnBox.add(inputBtn);
		optionBtnBox.add(resetBtn);
		optionBtnBox.add(randomBtn);

		GridLayout grid = new GridLayout(10, 5);
		btnBox.setLayout(grid);

		Map<Integer, JButton> btnMake = new HashMap<>();

		for (int i = 0; i < 45; i++) {
			JButton a = new JButton(String.valueOf(i + 1));
			btnMake.put(i + 1, a);
			a.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (numcount < 6) {
						int num = Integer.valueOf(((JButton) e.getSource()).getText()); // int num은 버튼의 key값.
						inputLottoNum.add(Integer.valueOf(num)); // inputLottoNum에 숫자 하나 추가
						((JButton) e.getSource()).setEnabled(false); // 버튼 비활성화
						numcount++; // 숫자 세기
					}
				}
			});
			btnBox.add(a);
		}

		inputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (buyLotto.size() == 5) { // 로또를 5개 넘게 사려고 할 때 막음
					JOptionPane.showMessageDialog(null, "로또는 한번에 5개까지 구매 가능합니다.", "Warning", JOptionPane.PLAIN_MESSAGE);

				} else if (inputLottoNum.size() != 6) { // 로또 숫자를 6개 넣지 않으려고 했을 때 막음
					JOptionPane.showMessageDialog(null, "여섯 숫자를 입력해야 합니다.", "Warning", JOptionPane.PLAIN_MESSAGE);

				} else { // 아닐 때: 적합할 때 buyLottoNum에 산 배열 넣기
					for (int key : btnMake.keySet()) {
						btnMake.get(key).setEnabled(true);
					}

					// deep복사 하기 위해
					List<Integer> inputList = new ArrayList<>();
					for (int a : inputLottoNum) {
						inputList.add(a);
					}
					Collections.sort(inputList);
					buyLotto.add(inputList);
					/////////////
					int indx = buyLotto.indexOf(inputList);
					for (int i=2; i<8; i++) {
						lottoField[indx][i].setText(" "+String.valueOf(inputList.get(i-2)+" "));
					}
					////////////
					inputLottoNum.clear();
					numcount = 0;
					LottoNumCount++;
					lottoPrice.setText(String.format("금액: %d원", LottoNumCount*1000));
				}
			}
		});

		randomBtn.addActionListener(new ActionListener() { // 랜덤 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				while (inputLottoNum.size() != 6) {
					int randomNum = random.nextInt(45) + 1;
					if (inputLottoNum.contains(randomNum)) {
						continue;
					} else {
						inputLottoNum.add(randomNum);
						btnMake.get(randomNum).setEnabled(false);
						numcount++;
					}
				}
			}
		});

		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int key : btnMake.keySet()) {
					btnMake.get(key).setEnabled(true);
				}
				inputLottoNum.clear();
				numcount = 0;
			}
		});

		setTitle("로또 구입 창");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void makefield(JLabel[][] lotto) {
		for (int i=0; i < 5; i++) {
			lotto[i][0] = new JLabel(String.valueOf(i+1));
			lotto[i][1] = new JLabel(String.valueOf("분류"));
			
			for (int j=2; j<8; j++) {
				lotto[i][j] = new JLabel(" ■ "); // NULL
			}
			lotto[i][8] = new JLabel("복사");
			lotto[i][9] = new JLabel("삭제");
			lotto[i][9].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int y = 0;
					for (int i=0; i<lottoField.length; i++) {
						for (int j=0; j<lottoField[0].length; j++) {
							if (lottoField[i][j].equals(e.getSource())) {
								y = i;
							}
						}
					}
					buyLotto.set(y, null);
					for (int i=2; i<8; i++) {
						lottoField[y][i].setText(" ■ ");
					}
					LottoNumCount--;
					lottoPrice.setText(String.format("금액: %d원", LottoNumCount*1000));
				}
			});
		}
	}
	
	public void printBuyLotto_1row() {
	}

	public static void main(String[] args) {
		new BuyPage().setVisible(true);
	}
}