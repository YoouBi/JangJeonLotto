import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class BuyPage extends JFrame {

	static final int ALL_RANDOM = 2;
	static final int HALF_RANDOM = 1;
	static final int NON_RANDOM = 0;

	////////////////////////
	Random random = new Random(); // 자동 반자동 할 때 쓰는 랜덤 객체
	int numcount = 0; // 숫자 세는 변수: 6개만 써야 하는걸로
	int LottoNumCount = 0;
	List<Integer> inputLottoNum = new ArrayList<>();
	JLabel[][] lottoField = new JLabel[5][10];
	int checkOption = NON_RANDOM;
	List<Integer> halfRandomNum = new ArrayList<>();

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
		// Edit field

		makefield(lottoField);
		for (int i = 0; i < lottoField.length; i++) {
			JPanel a = new JPanel();
			a.setOpaque(false);
			for (int j = 0; j < lottoField[0].length; j++) {
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
			a.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JButton btn = (JButton) e.getSource();
					if (numcount < 6) {
						if (btn.isEnabled()) {
							int num = Integer.valueOf(btn.getText()); // int num은 버튼의 key값.
							inputLottoNum.add(Integer.valueOf(num)); // inputLottoNum에 숫자 하나 추가
							btn.setEnabled(false); // 버튼 비활성화
							numcount++; // 숫자 세기
						} else {
							btn.setEnabled(true);
							inputLottoNum.remove(inputLottoNum.indexOf(Integer.valueOf(btn.getText())));
							numcount--;
						}
					}
				}
			});
			btnBox.add(a);
		}

		inputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (LottoNumCount == 5) { // 로또를 5개 넘게 사려고 할 때 막음
					JOptionPane.showMessageDialog(null, "로또는 한번에 5개까지 구매 가능합니다.", "Warning", JOptionPane.PLAIN_MESSAGE);

				} else if (inputLottoNum.size() != 6) { // 로또 숫자를 6개 넣지 않으려고 했을 때 막음
					JOptionPane.showMessageDialog(null, "여섯 숫자를 입력해야 합니다.", "Warning", JOptionPane.PLAIN_MESSAGE);

				} else { // 아닐 때: 적합할 때 buyLottoNum에 산 배열 넣기

					for (int key : btnMake.keySet()) { // 버튼 다시 활성화
						btnMake.get(key).setEnabled(true);
					}

					// inputLottoNum DeepCopy
					List<Integer> inputList = new ArrayList<>();
					for (int a : inputLottoNum) {
						inputList.add(a);
					}
					Collections.sort(inputList);

					// buyLotto에 넣는거
					if (buyLotto.contains(null)) {
						buyLotto.set(buyLotto.indexOf(null), inputList);
					} else {
						buyLotto.add(inputList);
					}
					/////////////

					// 패널 객체에 숫자넣는 로직
					int indx = buyLotto.indexOf(inputList);

					////// 자동 반자동 수동 넣는 로직
					if (checkOption == NON_RANDOM) {
						lottoField[indx][1].setText("수동");
						for (int i = 2; i < 8; i++) {
							lottoField[indx][i].setText(" " + String.valueOf(inputList.get(i - 2) + " "));
						}
					} else if (checkOption == HALF_RANDOM) {
						lottoField[indx][1].setText("반자동");

						// 복사배열
						List<Integer> a = new ArrayList<>();
						for (int x : halfRandomNum) {
							a.add(x);
						}

						for (int i = 2; i < 2 + halfRandomNum.size(); i++) {
							lottoField[indx][i].setText(" " + String.valueOf(a.get(i - 2) + " "));
						}
						for (int i = 2 + halfRandomNum.size(); i < 8; i++) {
							lottoField[indx][i].setText(" ? ");
						}
					} else {
						lottoField[indx][1].setText("자동");
						for (int i = 2; i < 8; i++) {
							lottoField[indx][i].setText(" ? ");
						}
					}

					////////////

					randomBtn.setEnabled(true);
					checkOption = NON_RANDOM;
					halfRandomNum.clear();
					inputLottoNum.clear();
					numcount = 0;
					LottoNumCount++;
					lottoPrice.setText(String.format("금액: %d원", LottoNumCount * 1000));
				}
			}
		});

		randomBtn.addActionListener(new ActionListener() { // 랜덤 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				// check option method
				if (inputLottoNum.size() < 1) // All 자동 체크
					checkOption = ALL_RANDOM;

				else if (inputLottoNum.size() < 6) { // 반자동 체크
					checkOption = HALF_RANDOM;
					for (int a : inputLottoNum) {
						halfRandomNum.add(a);
					}
					Collections.sort(halfRandomNum);
				}

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
				randomBtn.setEnabled(false);

			}
		});

		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int key : btnMake.keySet()) {
					btnMake.get(key).setEnabled(true);
				}
				randomBtn.setEnabled(true);
				inputLottoNum.clear();
				numcount = 0;
			}
		});

		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Iterator로 배열 정리
				Iterator<List<Integer>> checkNull = buyLotto.iterator();
				while (checkNull.hasNext()) {
					if (checkNull.next() == null) {
						checkNull.remove();
					}
				}

				// TODO 다이얼로그 메시지 정리. 확인했을 때 동작 만들기
				// 다이얼로그로 배열 출력
				JOptionPane.showConfirmDialog(null, buyLotto.toString() + "\n수량: " + buyLotto.size(), "로또 값 확인",
						JOptionPane.OK_CANCEL_OPTION);
			}
		});

		setTitle("로또 구입 창");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public List makeCopyList(List list) {
		List cloneArray = new ArrayList<>();
		for (Object a : list) {
			cloneArray.add(a);
		}
		return cloneArray;
	}

	public void makefield(JLabel[][] lotto) {
		for (int i = 0; i < 5; i++) {
			lotto[i][0] = new JLabel(String.valueOf(i + 1));
			lotto[i][1] = new JLabel(String.valueOf("미지정"));

			for (int j = 2; j < 8; j++) {
				lotto[i][j] = new JLabel(" ■ "); // NULL
			}

			lotto[i][8] = new JLabel("복사");
			lotto[i][9] = new JLabel("삭제");

			lotto[i][9].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int y = 0;
					for (int i = 0; i < lottoField.length; i++) { // 몇번째 줄인지 찾는 로직
						for (int j = 0; j < lottoField[0].length; j++) {
							if (lottoField[i][j].equals(e.getSource())) {
								y = i;
							}
						}
					}

					if (!(lottoField[y][1].getText().equals("미지정"))) { // 그 줄이 차있을때만 삭제
						buyLotto.set(y, null);
						for (int i = 2; i < 8; i++) {
							lottoField[y][i].setText(" ■ ");
						}
						lottoField[y][1].setText("미지정");

						LottoNumCount--;
						lottoPrice.setText(String.format("금액: %d원", LottoNumCount * 1000));
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		new BuyPage().setVisible(true);
	}
}