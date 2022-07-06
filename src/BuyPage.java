import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.LayerUI;

class MyEmphasizeButton extends JButton {
	Color red = new Color(191, 36, 40);
	Color blue = new Color(71, 150, 169);
	Color yellow = new Color(221, 168, 81);
	Color brown = new Color(60, 38, 24);
	Color bage = new Color(221, 197, 158);
	Color darkRed= new Color(161, 6, 10);
	
	public MyEmphasizeButton(String text) {
		super(text);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

		if (getModel().isEnabled()) { // 활성화되었을때
			graphics.setColor(red);
			setForeground(Color.white);

		} else if (!(getModel().isEnabled())) { // 비활성화 되었을때
			graphics.setColor(bage);
			setForeground(Color.gray);
		}

		if (getModel().isRollover()) { // 마우스 올렸을 때
			graphics.setColor(darkRed);
		}
		
		graphics.fillRect(0, 0, width, height);

		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		
		if (!(getModel().isEnabled())) {
			graphics.setColor(Color.GRAY);
		} else {
			graphics.setColor(darkRed);
		}

		
		graphics.drawRect(0, 0, width - 1, height - 1);
		graphics.setColor(getForeground());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		// https://the-illusionist.me/42
		
		super.paintComponent(g);
	}
}

class MyButton extends JButton {
	Color red = new Color(191, 36, 40);
	Color blue = new Color(71, 150, 169);
	Color yellow = new Color(221, 168, 81);
	Color brown = new Color(60, 38, 24);
	Color bage = new Color(221, 197, 158);
	Color darkBlue = new Color(41, 120, 139);
	
	public MyButton(String text) {
		super(text);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

		if (getModel().isEnabled()) { // 활성화되었을때
			graphics.setColor(blue);
			setForeground(Color.white);

		} else if (!(getModel().isEnabled())) { // 비활성화 되었을때
			graphics.setColor(bage);
			setForeground(Color.gray);
		}

		if (getModel().isRollover()) { // 마우스 올렸을 때
			graphics.setColor(darkBlue);
		}
		
		graphics.fillRect(0, 0, width, height);

		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		
		if (!(getModel().isEnabled())) {
			graphics.setColor(Color.GRAY);
		} else {
			graphics.setColor(darkBlue);
		}
		
		graphics.drawRect(0, 0, width - 1, height - 1);
		graphics.setColor(getForeground());
		
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		// https://the-illusionist.me/42
		
		super.paintComponent(g);
	}
}


class RoundButton extends JButton {
	Font cardFont = new Font("Serif", Font.BOLD, 25);
	Font btnFont = new Font("굴림", Font.BOLD, 15);

	Color red = new Color(191, 36, 40);
	Color blue = new Color(71, 150, 169);
	Color yellow = new Color(221, 168, 81);
	Color brown = new Color(60, 38, 24);
	Color bage = new Color(221, 197, 158);
	Color darkYellow = new Color(201, 148, 61);

	public RoundButton(String text) {
		super(text);
		decorate();
	}

	protected void decorate() {
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		int width = getWidth();
		int height = getHeight();

		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

		FontMetrics fontMetrics = graphics.getFontMetrics(btnFont);
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

		if (getModel().isEnabled()) { // 활성화되었을때
			graphics.setColor(yellow);
			setForeground(brown);

		} else if (!(getModel().isEnabled())) { // 비활성화 되었을때
			graphics.setColor(red);
			setForeground(yellow);
		}

		if (getModel().isRollover()) { // 마우스 올렸을 때
			graphics.setColor(darkYellow);
		}

		graphics.fillRect(0, 0, width, height);

		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

		graphics.setColor(getForeground());
		graphics.drawString(getText(), textX, textY);
		
		graphics.setColor(darkYellow);
		graphics.drawRect(0, 0, width - 1, height - 1);
		
		graphics.dispose();
		// https://the-illusionist.me/42
		
		super.paintComponent(g);
	}
}

///// 밀자 .................

public class BuyPage extends JFrame {

	static final int ALL_RANDOM = 2;
	static final int HALF_RANDOM = 1;
	static final int NON_RANDOM = 0;
	// 상수

	////////////////////////
	Random random = new Random(); // 자동 반자동 할 때 쓰는 랜덤 객체

	int numcount = 0; // 숫자 세는 변수: 6개만 써야 하는걸로
	int lottoNumCount = 0; // 산 로또 갯수: 1~5
	List<Integer> inputLottoNum = new ArrayList<>(); // 산 로또 리스트
	JLabel[][] lottoField = new JLabel[5][10]; // 로또 필드(전체가 J라벨)
	int checkOption = NON_RANDOM;
	List<Integer> halfRandomNum = new ArrayList<>();
	List<Integer> copyFunctionList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));
	JButton resetBtn = new MyButton("다시 입력하기");

	//////////////////////// 이 아래는 넘겨줄 거

	List<List<Integer>> buyLotto = new ArrayList<>(); // 산 로또 목록 리스트:: 1~5개 가변적

	JPanel pnl = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(bgImg, 0, 0, null);
			setOpaque(false);
		}
	}; // 넘겨줄 J패널

	JButton logout = new MyButton("로그아웃");
	JButton mypage = new MyButton("마이페이지");

	JButton nextBtn = new MyEmphasizeButton("결과 보기"); // 로또 결과 보는 버튼
	JLabel lottoPrice = new JLabel("금액: 0원"); // 금액 버튼
	JButton hardReset = new MyButton("전체 초기화"); // 전체 초기화 버튼

	//////////////////// image
	Toolkit kit = Toolkit.getDefaultToolkit();
	URL cardBack = BuyPage.class.getClassLoader().getResource("images/card_back.png");
	URL cardClover = BuyPage.class.getClassLoader().getResource("images/card_clover.png");
	URL cardSpade = BuyPage.class.getClassLoader().getResource("images/card_spade.png");
	URL cardHeart = BuyPage.class.getClassLoader().getResource("images/card_heart.png");
	URL cardDiamond = BuyPage.class.getClassLoader().getResource("images/card_diamond.png");
	URL background = BuyPage.class.getClassLoader().getResource("images/backgroundimg.png");
	URL random_blind_bg = BuyPage.class.getClassLoader().getResource("images/random_blind.png");

	URL btnDel = BuyPage.class.getClassLoader().getResource("images/delete_Btn.png");
	URL btnCP = BuyPage.class.getClassLoader().getResource("images/copypaste_Btn.png");
	URL btnDelNon = BuyPage.class.getClassLoader().getResource("images/delete_Btn_nonuse.png");
	URL btnCPNon = BuyPage.class.getClassLoader().getResource("images/copypaste_Btn_nonuse.png");
	URL opUB = BuyPage.class.getClassLoader().getResource("images/Option_UnderBar.png");
	URL opNone = BuyPage.class.getClassLoader().getResource("images/Option_None.png");

	ImageIcon backImg = new ImageIcon(kit.getImage(cardBack));
	ImageIcon spadeImg = new ImageIcon(kit.getImage(cardSpade));
	ImageIcon heartImg = new ImageIcon(kit.getImage(cardHeart));
	ImageIcon cloverImg = new ImageIcon(kit.getImage(cardClover));
	ImageIcon diaImg = new ImageIcon(kit.getImage(cardDiamond));
	Image bgImg = new ImageIcon(background).getImage();
	Image rdBlindIng = new ImageIcon(random_blind_bg).getImage();

	ImageIcon btnDelImg = new ImageIcon(kit.getImage(btnDel));
	ImageIcon btnCPImg = new ImageIcon(kit.getImage(btnCP));
	ImageIcon btnDelNUImg = new ImageIcon(kit.getImage(btnDelNon));
	ImageIcon btnCPNUImg = new ImageIcon(kit.getImage(btnCPNon));
	ImageIcon opUBImg = new ImageIcon(kit.getImage(opUB));
	ImageIcon opNoneImg = new ImageIcon(kit.getImage(opNone));

	Font cardFont = new Font("Serif", Font.BOLD, 25);
	Font btnFont = new Font("굴림", Font.BOLD, 15);

	Color red = new Color(191, 36, 40);
	Color blue = new Color(71, 150, 169);
	Color yellow = new Color(221, 168, 81);
	Color brown = new Color(60, 38, 24);
	Color bage = new Color(221, 197, 158);

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

	public int getLottoNumCount() {
		return lottoNumCount;
	}

	public JButton getLogout() {
		return logout;
	}

	public JButton getMypage() {
		return mypage;
	}

	/////////////////// 생성자

	BuyPage() {
		makeBuyLottoReset();
		ToolTipManager m = ToolTipManager.sharedInstance(); // 툴팁 여는 시간 조정 위해 객체 생성
		m.setInitialDelay(0); // 초기 툴팁 출력 지연시간 0초 설정
		//////////// 전체 레이아웃

		JPanel all = new JPanel();
		all.setOpaque(false);
		JPanel topBox = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topBox.getLayout();
		flowLayout.setAlignment(FlowLayout.LEADING);
		topBox.setOpaque(false);

		BoxLayout pnlLayout = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(pnlLayout);

		JLabel topInfoInput = new JLabel("     ::입력부::                                                                                               ");
		topInfoInput.setToolTipText("<html><p>" + "버튼을 눌러 번호 선택. 1회 로또 게임은 번호 6개로 이루어짐.<br>"
				+ "[자동]: 6개의 숫자를 자동으로 선택해줌<br>" + "[반자동]: 6개 중 자신이 선택한 것 외의 숫자를 자동으로 선택해줌.<br>"
				+ "[입력]: 선택한 번호를 게임에 추가<br>" + "[다시 선택하기]: 선택한 번호 초기화<br>" + "</p></html>");

		JLabel topInfoEdit = new JLabel("::편집부::                                                                           ");
		topInfoEdit.setToolTipText(
				"<html><p>" + "자신의 게임 정보를 보고 수정할 수 있음.<br>" + "[복사]: 게임 복사는 수동만 가능<br>" + "[붙여넣기]: 복사한 번호를 게임에 추가<br>"
						+ "[삭제]: 선택한 번호 초기화<br>" + "[전체 초기화]: 여태 선택한 번호를 전부 삭제<br>" + "</p></html>");

		BoxLayout allLayout = new BoxLayout(all, BoxLayout.X_AXIS);
		all.setLayout(allLayout);

		JPanel inputPnl = new JPanel();
		inputPnl.setOpaque(false);
		BoxLayout inputpnlLayout = new BoxLayout(inputPnl, BoxLayout.Y_AXIS);
		inputPnl.setLayout(inputpnlLayout);

		JPanel editPnl = new JPanel();
		editPnl.setOpaque(false);
		BoxLayout editPnlLayout = new BoxLayout(editPnl, BoxLayout.Y_AXIS);
		editPnl.setLayout(editPnlLayout);

		JPanel east = new JPanel();
		east.setOpaque(false);
		JPanel west = new JPanel();
		west.setOpaque(false);
		JPanel center = new JPanel();
		center.setOpaque(false);

		west.add(topInfoInput);
		center.add(topInfoEdit);
		east.add(mypage);
		east.add(logout);
		
		topBox.add(west);
		topBox.add(center);
		topBox.add(east);
		
		

		pnl.add(topBox);
		all.add(inputPnl);
		all.add(editPnl);
		pnl.add(all);

		getContentPane().add(pnl);

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

		JButton inputBtn = new MyEmphasizeButton("입력");
		inputBtn.setEnabled(false);

		JButton randomBtn = new MyButton("자동");
		JPanel optionBtnBox = new JPanel(); // 선택버튼들 감싸는 파일: 위의 JBtn 3개 들어감
		optionBtnBox.setOpaque(false);

		// inputBtn - btn45box
		JPanel btnBox = new JPanel(); // 1~45 버튼 감싸는 박스
		btnBox.setOpaque(false);
		GridLayout grid = new GridLayout(10, 5);
		grid.setHgap(2);
		grid.setVgap(2);
		btnBox.setLayout(grid);
		Map<Integer, JButton> btnMake = new HashMap<>();
		for (int i = 0; i < 45; i++) {
			JButton a = new RoundButton(String.valueOf(i + 1));
			btnMake.put(i + 1, a);
			a.setFont(btnFont);
			a.addMouseListener(new MouseAdapter() {

				@Override
				public void mousePressed(MouseEvent e) {
					JButton btn = (JButton) e.getSource();
					int count = getBuyLottoYNum();

					if (count == 5) {
						JOptionPane.showMessageDialog(null, "로또 게임은 한번에 5개까지만 구매할 수 있습니다.");

					} else {
						if (btn.isEnabled()) {
							if (numcount < 6) {
								int num = Integer.valueOf(btn.getText()); // int num은 버튼의 key값.
								inputLottoNum.add(Integer.valueOf(num)); // inputLottoNum에 숫자 하나 추가
								btn.setEnabled(false); // 버튼 비활성화
								numcount++; // 숫자 세기
								if (numcount == 6) {
									inputBtn.setEnabled(true);
									randomBtn.setEnabled(false);
								}
							}
						} else {
							btn.setEnabled(true);
							randomBtn.setEnabled(true);
							inputBtn.setEnabled(false);
							inputLottoNum.remove(inputLottoNum.indexOf(Integer.valueOf(btn.getText())));
							numcount--;
						}

						if (numcount > 0) {
							randomBtn.setText("반자동");
						}

						if (numcount == 0) {
							randomBtn.setText("자동");
						}
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
		JPanel info = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(rdBlindIng, 0, 0, null);
				setOpaque(false);
			}
		};
		info.setBackground(brown);

		JLabel info1 = new JLabel(
				"<html><p style = 'text-align: center;'><br><br><br><br>자동 발행 숫자는<br>구매가 끝난 후에만<br>확인 가능합니다.</p></html>");
		info1.setFont(btnFont);
		info1.setForeground(Color.white);
		info1.setHorizontalAlignment(JLabel.CENTER);
		info.add(info1);

		cardbox.add(btnBox, "A");
		cardbox.add(info, "B");

		card.show(cardbox, "A");

		inputPnl.add(cardbox);
		inputPnl.add(optionBtnBox);

		//////////////////// optionBtnBox

		optionBtnBox.add(randomBtn);
		optionBtnBox.add(inputBtn);
		optionBtnBox.add(resetBtn);

		///////////////////// pnlDesign

		Border underline = BorderFactory.createMatteBorder(0, 0, 2, 0, brown);
		Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
		topBox.setBorder(underline);
		editPnl.setBorder(padding);
		inputPnl.setBorder(padding);

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
					indx = buyLotto.indexOf(Arrays.asList(0, 0, 0, 0, 0, 0));
					buyLotto.set(buyLotto.indexOf(Arrays.asList(0, 0, 0, 0, 0, 0)), inputList);

					// 패널 객체에 숫자넣기, 자동반자동 넣기

					if (checkOption == NON_RANDOM) {
						lottoField[indx][1].setText("수동");
						for (int i = 2; i < 8; i++) {
							lottoField[indx][i].setIcon(heartImg);
							lottoField[indx][i].setText(" " + String.valueOf(inputList.get(i - 2) + " "));
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
					lottoField[indx][1].setIcon(opUBImg);
					lottoField[indx][8].setText("복사");
					card.show(cardbox, "A");
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

				int num = getBuyLottoYNum();

				if (num == 5) {
					JOptionPane.showMessageDialog(null, "로또는 한번에 5개까지 구매 가능합니다.");

				} else {
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
							card.show(cardbox, "B");
						}
					}
					randomBtn.setEnabled(false);
					inputBtn.setEnabled(true);
				}
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
				card.show(cardbox, "A");
				randomBtn.setText("자동");
			}
		});

		// 하드리셋
		hardReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				hardReset();
			}
		});

		setTitle("로또 구입 창");
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void hardReset() {
		buyLotto.clear();
		makeBuyLottoReset();
		lottoNumCount = 0;
		halfRandomNum.clear();
		inputLottoNum.clear();
		resetBtn.doClick();
		numcount = 0;
		checkOption = NON_RANDOM;
		lottoPrice.setText(String.format("금액: %d원", lottoNumCount * 1000));
		copyFunctionList = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0));

		////////////////////
		// 필드 리셋
		for (int i = 0; i < 5; i++) {
			lottoField[i][1].setText("미지정");
			lottoField[i][1].setIcon(opNoneImg);
			for (int j = 2; j < 8; j++) {
				lottoField[i][j].setIcon(backImg);
				lottoField[i][j].setText("");
			}
			lottoField[i][8].setText("붙여넣기");
			lottoField[i][9].setText("삭제");
		}
		///////////////
	}

	public int getBuyLottoYNum() {
		int count = 0;

		for (int i = 0; i < buyLotto.size(); i++) {
			if (buyLotto.contains(Arrays.asList(0, 0, 0, 0, 0, 0))) {
			} else {
				count++;
			}
		}
		return count;
	}

	public int getArrsObjY(Object[][] arr, Object obj) { // 몇번째 줄인지 찾는 로직
		int y = 0;
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
		for (int i = 0; i < 5; i++)
			buyLotto.add(Arrays.asList(0, 0, 0, 0, 0, 0));
	}

	public void makefield(JLabel[][] lotto) {
		for (int i = 0; i < 5; i++) {
			lotto[i][0] = new JLabel();
			if (i == 0) {
				lotto[i][0].setText("A");
			} else if (i == 1) {
				lotto[i][0].setText("B");
			} else if (i == 2) {
				lotto[i][0].setText("C");
			} else if (i == 3) {
				lotto[i][0].setText("D");
			} else {
				lotto[i][0].setText("E");
			}

			lotto[i][1] = new JLabel(String.valueOf("미지정"));
			lotto[i][1].setIcon(opNoneImg);
			lotto[i][1].setHorizontalTextPosition(JLabel.CENTER);

			for (int j = 2; j < 8; j++) {
				lotto[i][j] = new JLabel(backImg); // 0*6
				lotto[i][j].setText("");
				lotto[i][j].setFont(cardFont);
				lotto[i][j].setForeground(Color.white);
				lotto[i][j].setHorizontalTextPosition(JLabel.CENTER);
			}

			lotto[i][8] = new JLabel("붙여넣기");
			lotto[i][8].setIcon(btnCPImg);
			lotto[i][8].setForeground(Color.white);
			lotto[i][8].setHorizontalTextPosition(JLabel.CENTER);

			lotto[i][9] = new JLabel("삭제");
			lotto[i][9].setIcon(btnDelImg);
			lotto[i][9].setForeground(Color.white);
			lotto[i][9].setHorizontalTextPosition(JLabel.CENTER);

			lotto[i][8].addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					JLabel removeBtn = (JLabel) e.getSource();
					removeBtn.setForeground(yellow);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel removeBtn = (JLabel) e.getSource();
					removeBtn.setForeground(Color.white);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					JLabel copyBtn = (JLabel) e.getSource();

					int y = getArrsObjY(lottoField, copyBtn);

					if (copyBtn.getText().equals("복사")) { // 복사일때
						if (!(lottoField[y][1].getText().equals("수동"))) { // 수동이 아니면 복사 안되게끔
							JOptionPane.showMessageDialog(null, "복사는 수동만 가능합니다.");
						} else {
							copyFunctionList = buyLotto.get(y);
							JOptionPane.showMessageDialog(null, "복사 완료!");
						}
					} else { // 붙여넣기일때
						if (copyFunctionList.contains(0)) { // 붙여넣기인데 붙여넣을 배열 없을때
							JOptionPane.showMessageDialog(null, "붙여넣기를 하려면 복사를 먼저 해야 합니다.");
						} else { // 붙여넣기
							List<Integer> copy = makeCopyList(copyFunctionList);

							buyLotto.set(y, copy);

							lottoField[y][1].setText("수동");
							lottoField[y][1].setIcon(opUBImg);
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
					removeBtn.setForeground(new Color(241, 188, 101));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					JLabel removeBtn = (JLabel) e.getSource();
					removeBtn.setForeground(Color.white);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					int y = getArrsObjY(lottoField, e.getSource());

					if (!(lottoField[y][1].getText().equals("미지정"))) { // 그 줄이 차있을때만 삭제
						buyLotto.set(y, Arrays.asList(0, 0, 0, 0, 0, 0));
						lottoField[y][1].setText("미지정");
						lotto[y][1].setIcon(opNoneImg);
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