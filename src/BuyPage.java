import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class BuyPage extends JFrame {

	static final int ALL_RANDOM = 2;
	static final int HALF_RANDOM = 1;
	static final int NON_RANDOM = 0;
	// 상수

	////////////////////////
	Random random = new Random(); // 자동 반자동 할 때 쓰는 랜덤 객체
	
	int numcount = 0; // 숫자 세는 변수: 6개만 써야 하는걸로
	int lottoNumCount = 0;	// 산 로또 갯수: 1~5
	List<Integer> inputLottoNum = new ArrayList<>();	// 산 로또 리스트
	JLabel[][] lottoField = new JLabel[5][10];	// 로또 필드(전체가 J라벨)
	int checkOption = NON_RANDOM;
	List<Integer> halfRandomNum = new ArrayList<>();
	List<Integer> copyFunctionList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

	//////////////////////// 이 아래는 넘겨줄 거

	List<List<Integer>> buyLotto = new ArrayList<>(); // 산 로또 목록 리스트:: 1~5개 가변적

	JPanel pnl = new JPanel(); // 넘겨줄 J패널
	JButton nextBtn = new JButton("결과 보기"); // 로또 결과 보는 버튼
	JLabel lottoPrice = new JLabel("금액: 0원"); // 금액 버튼
	JButton hardReset = new JButton("전체 초기화");	// 전체 초기화 버튼
	
	
	//////////////////// image
	Toolkit kit = Toolkit.getDefaultToolkit();
	URL cardBack = BuyPage.class.getClassLoader().getResource("images/card_back.png");
	URL cardClover = BuyPage.class.getClassLoader().getResource("images/card_clover.png");
	URL cardSpade = BuyPage.class.getClassLoader().getResource("images/card_spade.png");
	URL cardHeart = BuyPage.class.getClassLoader().getResource("images/card_heart.png");
	URL cardDiamond = BuyPage.class.getClassLoader().getResource("images/card_diamond.png");
	
	ImageIcon backImg = new ImageIcon(kit.getImage(cardBack));
	ImageIcon spadeImg = new ImageIcon(kit.getImage(cardSpade));
	ImageIcon heartImg = new ImageIcon(kit.getImage(cardHeart));
	ImageIcon cloverImg = new ImageIcon(kit.getImage(cardClover));
	ImageIcon diaImg = new ImageIcon(kit.getImage(cardDiamond));
	
	Font cardFont = new Font("Serif", Font.BOLD, 25);

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
		makeBuyLottoReset();
		//////////// 전체 레이아웃
	
		
		
		
		
		
		
		pnl.setBackground(new Color(239, 230, 214));
		// 나중엔 여기만 갈기로
		BoxLayout pnlLayout = new BoxLayout(pnl, BoxLayout.X_AXIS);
		pnl.setLayout(pnlLayout);
		
		JPanel inputPnl = new JPanel();
		inputPnl.setOpaque(false);
		BoxLayout inputpnlLayout = new BoxLayout(inputPnl, BoxLayout.Y_AXIS);
		inputPnl.setLayout(inputpnlLayout);
		
		JPanel editPnl = new JPanel();
		editPnl.setOpaque(false);
		BoxLayout editPnlLayout = new BoxLayout(editPnl, BoxLayout.Y_AXIS);
		editPnl.setLayout(editPnlLayout);
		
		pnl.add(inputPnl);
		pnl.add(editPnl);

		add(pnl);
		
		// Edit field///////////////////////////////////
		makefield(lottoField);
		for (int i = 0; i < lottoField.length; i++) {
			JPanel a = new JPanel();
			a.setOpaque(false);
			for (int j = 0; j < lottoField[0].length; j++) {
				a.add(lottoField[i][j]);
			}
			editPnl.add(a);
		}
		
		JPanel bottomBox = new JPanel();
		bottomBox.setOpaque(false);
		
		bottomBox.add(lottoPrice);
		bottomBox.add(hardReset);
		bottomBox.add(nextBtn);
		editPnl.add(bottomBox);

		/// inputPnl /////////////////////////////////////////
		
		JButton inputBtn = new JButton("입력");
		inputBtn.setEnabled(false);
		JButton resetBtn = new JButton("다시 입력하기");
		JButton randomBtn = new JButton("자동");
		JPanel optionBtnBox = new JPanel(); // 선택버튼들 감싸는 파일: 위의 JBtn 3개 들어감
		optionBtnBox.setOpaque(false);
		
		
		// inputBtn - btn45box
		JPanel btnBox = new JPanel(); // 1~45 버튼 감싸는 박스
		btnBox.setOpaque(false);
		GridLayout grid = new GridLayout(10, 5);
		btnBox.setLayout(grid);
		btnBox.setPreferredSize(new Dimension(350, 400));

		Map<Integer, JButton> btnMake = new HashMap<>();

		for (int i = 0; i < 45; i++) {
			JButton a = new JButton(String.valueOf(i + 1));
			btnMake.put(i + 1, a);
			a.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					JButton btn = (JButton) e.getSource();
					if (btn.isEnabled()) {
						if (numcount < 6) {
							int num = Integer.valueOf(btn.getText()); // int num은 버튼의 key값.
							inputLottoNum.add(Integer.valueOf(num)); // inputLottoNum에 숫자 하나 추가
							btn.setEnabled(false); // 버튼 비활성화
							numcount++; // 숫자 세기
							if (numcount==6) {
								inputBtn.setEnabled(true);
								randomBtn.setEnabled(false);
							}
						}
					} else {
						btn.setEnabled(true);
						inputLottoNum.remove(inputLottoNum.indexOf(Integer.valueOf(btn.getText())));
						numcount--;
					}
					
					if (numcount>0) {
						randomBtn.setText("반자동");
					}
					
					if (numcount==0) {
						randomBtn.setText("자동");
					}
				}
			});
			btnBox.add(a);
		}
		
		JPanel cardbox = new JPanel();
		cardbox.setPreferredSize(new Dimension(200, 400));
		cardbox.setOpaque(false);
		CardLayout card = new CardLayout();
		cardbox.setLayout(card);
		
		cardbox.add(btnBox, "A");
		cardbox.add(new JButton("자동 발행 숫자는 구매가 끝난 후에만 확인 가능합니다."), "B");
		
		card.show(cardbox,"A");
		
		inputPnl.add(cardbox);
		inputPnl.add(optionBtnBox);
		
		//////////////////// optionBtnBox
		
		optionBtnBox.add(inputBtn);
		optionBtnBox.add(resetBtn);
		optionBtnBox.add(randomBtn);
		
		///////////////////// ActionListener

		inputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (lottoNumCount == 5) { // 로또를 5개 넘게 사려고 할 때 막음
					JOptionPane.showMessageDialog(null, "로또는 한번에 5개까지 구매 가능합니다.", "Warning", JOptionPane.PLAIN_MESSAGE);

				} else { // 아닐 때: 적합할 때 buyLottoNum에 산 배열 넣기

					for (int key : btnMake.keySet()) { // 버튼 다시 활성화
						btnMake.get(key).setEnabled(true);
					}

					// inputLottoNum DeepCopy
					List<Integer> inputList = makeCopyList(inputLottoNum);
					Collections.sort(inputList);
					int indx;

					// buyLotto에 넣는거
//					if (buyLotto.contains(Arrays.asList(0, 0, 0, 0, 0, 0))) {
						indx = buyLotto.indexOf(Arrays.asList(0, 0, 0, 0, 0, 0));
						buyLotto.set(buyLotto.indexOf(Arrays.asList(0, 0, 0, 0, 0, 0)), inputList);
//					} else {
//						buyLotto.add(inputList);
//						indx=buyLotto.size()-1;
//					}
					/////////////
					// 패널 객체에 숫자넣기, 자동반자동 넣기
					
					if (checkOption == NON_RANDOM) {
						lottoField[indx][1].setText("수동");
						for (int i = 2; i < 8; i++) {
							lottoField[indx][i].setIcon(heartImg);
							lottoField[indx][i].setText(" " + String.valueOf(inputList.get(i - 2) + " "));
//							lottoField[indx][i].setHorizontalTextPosition(JLabel.CENTER);
						}
						
					} else if (checkOption == HALF_RANDOM) {
						lottoField[indx][1].setText("반자동");
						// 복사배열
						List<Integer> a = makeCopyList(halfRandomNum);
						for (int i = 2; i < 2 + halfRandomNum.size(); i++) {
							lottoField[indx][i].setIcon(diaImg);
							lottoField[indx][i].setText(" " + String.valueOf(a.get(i - 2) + " "));
						}
						for (int i = 2 + halfRandomNum.size(); i < 8; i++) {
							lottoField[indx][i].setIcon(spadeImg);
							lottoField[indx][i].setText(" ? ");
						}
						
					} else {
						lottoField[indx][1].setText("자동");
						for (int i = 2; i < 8; i++) {
							lottoField[indx][i].setIcon(cloverImg);
							lottoField[indx][i].setText(" ? ");
						}
					}

					////////////
					lottoField[indx][8].setText("복사");
					card.show(cardbox,"A");
					inputBtn.setEnabled(false);
					randomBtn.setEnabled(true);
					randomBtn.setText("자동");
					checkOption = NON_RANDOM;
					halfRandomNum.clear();
					inputLottoNum.clear();
					numcount = 0;
					lottoNumCount++;
					lottoPrice.setText(String.format("금액: %d원", lottoNumCount * 1000));
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
						card.show(cardbox,"B");
					}
				}
				randomBtn.setEnabled(false);
				inputBtn.setEnabled(true);

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
				inputBtn.setEnabled(false);
				card.show(cardbox,"A");
				randomBtn.setText("자동");
			}
		});

		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				resetBtn.doClick();
				
				int result = JOptionPane.showConfirmDialog(null, String.format("복권 수량: %d개\n가격: %d원\n구매 확정하시겠습니까?", lottoNumCount, lottoNumCount*1000), "로또 값 확인",
						JOptionPane.OK_CANCEL_OPTION);
				
				if (result == JOptionPane.OK_OPTION) {
					// Iterator로 배열 정리
					Iterator<List<Integer>> check0 = buyLotto.iterator();
					while (check0.hasNext()) {
						if (check0.next().contains(0)) {
							check0.remove();
						}
					}
					/// 다음페이지로 넘어가기
				} 
				JOptionPane.showMessageDialog(null, "[관리자 페이지: 로또배열 확인용이며 완성할 때 없애야함]\n"+buyLotto.toString());
			}
		});
		
		// 하드리셋 
		hardReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buyLotto.clear();
				lottoNumCount = 0;
				lottoPrice.setText(String.format("금액: %d원", lottoNumCount * 1000));
				
				////////////////////
				// 필드 리셋
				for (int i = 0; i < 5; i++) {
					lottoField[i][1].setText("미지정");
					for (int j = 2; j < 8; j++) {
						lottoField[i][j].setIcon(backImg);
						lottoField[i][j].setText("");
					}
					lottoField[i][8].setText("붙여넣기");
					lottoField[i][9].setText("삭제");
				}
				///////////////
			}
		});

		setTitle("로또 구입 창");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public int getArrsObjY(Object[][] arr, Object obj) {
		int y = 0;	// 몇번째 줄인지 찾는 로직
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (lottoField[i][j].equals(obj)) {
					y = i;
				}
			}
		}
		return y;
	}

	public List<Integer> makeCopyList(List<Integer> list) {
		List<Integer> cloneArray = new ArrayList<>();
		for (Integer a : list) {
			Integer x = new Integer(a);
			cloneArray.add(x);
		}
		return cloneArray;
	}
	
	public void makeBuyLottoReset() {
		for (int i=0; i<5; i++)
			buyLotto.add(Arrays.asList(0, 0, 0, 0, 0, 0));
	}

	public void makefield(JLabel[][] lotto) {
		for (int i = 0; i < 5; i++) {
			lotto[i][0] = new JLabel(String.valueOf(i + 1));
			lotto[i][1] = new JLabel(String.valueOf("미지정"));

			for (int j = 2; j < 8; j++) {
				lotto[i][j] = new JLabel(backImg); // 0*6
				lotto[i][j].setText("");
				lotto[i][j].setFont(cardFont);
				lotto[i][j].setForeground(Color.white);
				lotto[i][j].setHorizontalTextPosition(JLabel.CENTER);
			}

			lotto[i][8] = new JLabel("붙여넣기");
			lotto[i][9] = new JLabel("삭제");
			
			lotto[i][8].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					JLabel copyBtn = (JLabel) e.getSource();
					
					int y = getArrsObjY(lottoField, copyBtn);
					
					if (copyBtn.getText().equals("복사")) {	// 복사일때
						if (!(lottoField[y][1].getText().equals("수동"))) {	// 수동이 아니면 복사 안되게끔
							JOptionPane.showMessageDialog(null, "복사는 수동만 가능합니다.");
						} else {
							copyFunctionList = buyLotto.get(y);
							JOptionPane.showMessageDialog(null, "복사 완료!");
						}
					} else {	// 붙여넣기일때
						if (copyFunctionList.contains(0)) {	// 붙여넣기인데 붙여넣을 배열 없을때
							JOptionPane.showMessageDialog(null, "붙여넣기를 하려면 복사한 값이 있어야 합니다.");
						} else {	// 붙여넣기
							List<Integer> copy = makeCopyList(copyFunctionList);
							
							buyLotto.set(y, copy);
							
							lottoField[y][1].setText("수동");
							for (int i = 2; i < 8; i++) {
								lottoField[y][i].setIcon(diaImg);
								lottoField[y][i].setText(" " + String.valueOf(copy.get(i - 2) + " "));
							}
							lottoField[y][8].setText("복사");
							
							checkOption = NON_RANDOM;
							numcount = 0;
							lottoNumCount++;
							lottoPrice.setText(String.format("금액: %d원", lottoNumCount * 1000));
						}
						
					}
				}
			});

			lotto[i][9].addMouseListener(new MouseAdapter() {
				
				
				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel removeBtn = (JLabel) e.getSource();
					removeBtn.setForeground(Color.blue);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel removeBtn = (JLabel) e.getSource();
					removeBtn.setForeground(Color.black);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					int y = getArrsObjY(lottoField, e.getSource());

					if (!(lottoField[y][1].getText().equals("미지정"))) { // 그 줄이 차있을때만 삭제
						buyLotto.set(y, Arrays.asList(0, 0, 0, 0, 0, 0));
						
						lottoField[y][1].setText("미지정");
						for (int i = 2; i < 8; i++) {
							lottoField[y][i].setIcon(backImg);
							lottoField[y][i].setText("");
						}
						lottoField[y][8].setText("붙여넣기");
						lottoNumCount--;
						lottoPrice.setText(String.format("금액: %d원", lottoNumCount * 1000));
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		new BuyPage().setVisible(true);
	}
}