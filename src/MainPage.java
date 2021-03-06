import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Graphics;

class Account { // 개인정보
	private String id;
	private String pw;
	private String name;
	private int age; // 우리나라 최고령자 나이는 115세~
	private int bankReserve;
	private int lottoReserve;
	
	public Account(String id, String pw, String name, int age, int bankReserve, int lottoReserve) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
		this.bankReserve = bankReserve;
		this.lottoReserve = lottoReserve;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getBankReserve() {
		return bankReserve;
	}

	public void setBankReserve(int bankReserve) {
		this.bankReserve = bankReserve;
	}

	public int getLottoReserve() {
		return lottoReserve;
	}

	public void setLottoReserve(int lottoReserve) {
		this.lottoReserve = lottoReserve;
	}
}

class MoneyChargeWindow extends JDialog { // 충전 페이지
	MainPage mainpage;
	
	private JTextField reserveCharge;
	private JLabel reserveNotification = new JLabel("충전금 최소 금액은 천원이며, 천원 단위로만 충전이 가능합니다.");

	public MoneyChargeWindow(MainPage mainpage) {
		this.mainpage = mainpage;
		
		add(reserveNotification);
		
		reserveCharge = new JTextField(10);
		add(reserveCharge, "North");
		
		JButton chargeBtn = new JButton("충전");
		add(chargeBtn);
		
		reserveCharge.addKeyListener(new KeyAdapter() { // 입력 텍스트 필드
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		
		chargeBtn.addActionListener(new ActionListener() { //충전 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				int reserveInput = Integer.valueOf(getCharge()); // 충전금
//// 				맵 가져온거에서 로그인 되어있는 정보만 바꾸기...
				int banktotal = ((Account) mainpage.getMap().get(mainpage.getId().getText())).getBankReserve();
				int bankInt = banktotal - reserveInput;
				int reserveInt = ((Account) mainpage.getMap().get(mainpage.getId().getText())).getLottoReserve() + reserveInput;
				
				if(banktotal - reserveInput >= 0) {
					((Account) mainpage.getMap().get(mainpage.getId().getText())).setBankReserve(bankInt); // 은행 잔고에서 충전금 빼기
					((Account) mainpage.getMap().get(mainpage.getId().getText())).setLottoReserve(reserveInt); // 보유금 충전
				
					mainpage.getMypageBank().setText("계좌 : " + bankInt);
					mainpage.getMypageReserve().setText("보유금 : " + reserveInt);
					mainpage.setMylottoReserve(mainpage.getMylottoReserve() - reserveInput);
					
					dispose();
				} else {
					JOptionPane.showMessageDialog(MoneyChargeWindow.this, "계좌의 잔액이 부족합니다.");
				}
			}
		});
		
		setSize(300, 200);
	}
	
	public void showDialog() {
		setVisible(true);
	}
	
	public String getCharge() {
		return reserveCharge.getText();
	}
	
}

public class MainPage extends JFrame {
	private JPanel Mainppp;
	private JButton nextBtn;
	
	private int totalLotteWinnings = 300000000;
	
	private JLabel stringName; // 개인정보들
	private HashMap<String, Account> AccountMap;
	private int inputYear;
	private int inputMonth;
	private int inputDay;
	private int inputAge;
	private int showMyBankMoney;
	private int mylottoReserve = 0; // 현재 내 보유금
	
	private JTextField id = new JTextField(10); // 로그인 필드
	private int ranMyNum;
	private JLabel mypageBank;
	private JLabel mypageReserve;
	private JLabel lottoTotalMoney;
	private JButton signout;

	public JPanel getPnl() {
		return Mainppp;
	}
	public JButton getNextBtn() {
		return nextBtn;
	}
	
	public int getTotalLotteWinnings() {
		return totalLotteWinnings;
	}

	public void setTotalLotteWinnings(int totalLotteWinnings) {
		this.totalLotteWinnings = totalLotteWinnings;
	}
	
	public JButton getSignout() {
		return signout;
	}
	public JLabel getLottoTotalMoney() {
		return lottoTotalMoney;
	}
	public void setLottoTotalMoney(JLabel lottoTotalMoney) {
		this.lottoTotalMoney = lottoTotalMoney;
	}
	public HashMap<String, Account> getMap() {
		return AccountMap;
	}

	public void setMap(HashMap<String, Account> map) {
		this.AccountMap = map;
	}

	public int getShowMyBankMoney() {
		return showMyBankMoney;
	}

	public void setShowMyBankMoney(int showMyBankMoney) {
		this.showMyBankMoney = showMyBankMoney;
	}

	public int getMylottoReserve() {
		return mylottoReserve;
	}

	public void setMylottoReserve(int mylottoReserve) {
		this.mylottoReserve = mylottoReserve;
	}
	
	public JLabel getMypageBank() {
		return mypageBank;
	}

	public JLabel getMypageReserve() {
		return mypageReserve;
	}

	public JTextField getId() {
		return id;
	}

	public void setId(String idStr) {
		id.setText(idStr);
	}

	public MainPage() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		SimpleDateFormat ageTextFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar todayCalender = Calendar.getInstance();
		Random randomInt = new Random();
		AccountMap = new HashMap<>();
		// 아이디, 비밀번호, 이름
		
		inputYear = 0;
		inputMonth = 0;
		inputDay = 0;
		inputAge = 20220630;
		ranMyNum = randomInt.nextInt(99999) + 1;
		int myBankMoney;
		int myReserve = 0;
		showMyBankMoney = 0;
		
		AccountMap.put("YoouBi", new Account("YoouBi", "yoyobiii", "장영빈", 20020101, randomInt.nextInt(999) * 1000, 10000));
		AccountMap.put("Inha123", new Account("Inha123", "Inha123", "전인하", 20020202, randomInt.nextInt(999) * 1000, 20000));
		AccountMap.put("yeriming", new Account("yeriming", "yeriming", "장예림", 20020303, randomInt.nextInt(999) * 1000, 30000));
		AccountMap.put("nonmember", new Account("nonmember", "nonmember", "비회원", 20020404, 5000, 5000)); // 비회원용 계정
		
		URL imageUrl = MainPage.class.getClassLoader().getResource("images/Lotto-MainPage-Background.png");
		ImageIcon icon = new ImageIcon(kit.getImage(imageUrl).getScaledInstance(885, 565, Image.SCALE_SMOOTH));
//		.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		
		Mainppp = new JPanel(new BorderLayout()) {
			 public void paintComponent(Graphics g) {
				 g.drawImage(icon.getImage(), 0, 0, null);
				 setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	             super.paintComponent(g);
			 }
		};
		JPanel MainAll = new JPanel();
		JPanel MainIMGPnl = new JPanel();
		
		JPanel MainLogInTotalMoneyPnl = new JPanel(new BorderLayout());
		JPanel MainLogInIDPnl = new JPanel();
		JPanel MainLogInPWPnl = new JPanel();
		JPanel MainLogInAllPnl = new JPanel();
		JPanel MainLogInCenterPnl = new JPanel();
		
		CardLayout cardLogIn = new CardLayout();
		// MainPnlLogIn로 연결하여 오른쪽 패널들 바꾸는 레이아웃
		// 1. MainPnlLogPage1(기본 로그인하는 메인 페이지)
		// 2. (회원가입 페이지 만들 것)
		// 3. MainPnlLogPage2(마이페이지)
		JPanel MainPnlLogIn = new JPanel(cardLogIn);
		JPanel MainPnlLogInPage = new JPanel();
		
		JPanel MainPnlMyPage = new JPanel();
		JPanel MyPagePnl = new JPanel();
		JPanel MyPageAllPnl = new JPanel();
		JPanel MyPageHelloPnl = new JPanel(new BorderLayout());
		JPanel MyPageMoneyPnl = new JPanel();
		JPanel MyPageReservePnl = new JPanel();
		JPanel MyPageLogOutPnl = new JPanel();
		
		JPanel MainPnlCreatePage = new JPanel();
		JPanel CreatenullPnl = new JPanel();
		JPanel CreateAllPnl = new JPanel();

		FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 50, 10);
		JPanel CreatePageIdPnl = new JPanel(flow);
		JPanel CreatePageIdCheckPnl = new JPanel(flow);
		JPanel CreatePagePwPnl = new JPanel(flow);
		JPanel CreatePagePwPnlTop = new JPanel(flow);
		JPanel CreatePagePwCPnl = new JPanel(flow);
		JPanel CreatePagePwCheckPnl = new JPanel(flow);
		JPanel CreatePageNamePnl = new JPanel(flow);
		JPanel CreatePageAgePnl = new JPanel();
		JPanel CreatePageAccountAndReturn = new JPanel();
		
		/////////////////////// 패널들 사이즈 수정 ////////////////////////
		
		MainLogInCenterPnl.setPreferredSize(new Dimension(350, 350));
		MainIMGPnl.setPreferredSize(new Dimension(600, 350));
		MainLogInTotalMoneyPnl.setPreferredSize(new Dimension(350, 50));
		MainLogInPWPnl.setPreferredSize(new Dimension(120, 150));
		
		MyPageHelloPnl.setPreferredSize(new Dimension(120, 150));
		MyPageLogOutPnl.setPreferredSize(new Dimension(120, 200));
		
		CreateAllPnl.setPreferredSize(new Dimension(120, 0));
		CreatenullPnl.setPreferredSize(new Dimension(120, 50));
		CreatePageIdPnl.setPreferredSize(new Dimension(120, 0));
		CreatePageIdCheckPnl.setPreferredSize(new Dimension(120, 20));
		CreatePagePwPnl.setPreferredSize(new Dimension(130, 0));
		CreatePagePwPnlTop.setPreferredSize(new Dimension(130, 0));
		CreatePagePwCPnl.setPreferredSize(new Dimension(120, 0));
		CreatePagePwCheckPnl.setPreferredSize(new Dimension(120, 20));
		CreatePageNamePnl.setPreferredSize(new Dimension(120, 0));
		
		/////////////////////// 패널들 배경 수정 ////////////////////////
		
		MainAll.setOpaque(false);
		MainIMGPnl.setOpaque(false);
		MainLogInTotalMoneyPnl.setOpaque(false);
		MainLogInIDPnl.setOpaque(false);
		MainLogInPWPnl.setOpaque(false);
		MainLogInAllPnl.setOpaque(false);
		MainLogInCenterPnl.setOpaque(false);
		MainPnlLogIn.setOpaque(false);
		MainPnlLogInPage.setOpaque(false);
		
		MainPnlMyPage.setOpaque(false);
		MyPagePnl.setOpaque(false);
		MyPageAllPnl.setOpaque(false);
		MyPageHelloPnl.setOpaque(false);
		MyPageMoneyPnl.setOpaque(false);
		MyPageReservePnl.setOpaque(false);
		MyPageLogOutPnl.setOpaque(false);
		
		MainPnlCreatePage.setOpaque(false);
		CreateAllPnl.setOpaque(false);
		CreatenullPnl.setOpaque(false);
		CreatePageIdPnl.setOpaque(false);
		CreatePageIdCheckPnl.setOpaque(false);
		CreatePagePwPnlTop.setOpaque(false);
		CreatePagePwPnl.setOpaque(false);
		CreatePagePwCPnl.setOpaque(false);
		CreatePagePwCheckPnl.setOpaque(false);
		CreatePageNamePnl.setOpaque(false);
		CreatePageAgePnl.setOpaque(false);
		CreatePageAccountAndReturn.setOpaque(false);
		

		
		///////////////////////////////////////////////
		
		
		
		BoxLayout MainAllLayout = new BoxLayout(MainAll, BoxLayout.X_AXIS);
		MainAll.setLayout(MainAllLayout);
		BoxLayout LogInLayout = new BoxLayout(MainLogInCenterPnl, BoxLayout.Y_AXIS);
		MainLogInCenterPnl.setLayout(LogInLayout);
		BoxLayout mypageLayout = new BoxLayout(MyPagePnl, BoxLayout.Y_AXIS);
		MyPagePnl.setLayout(mypageLayout);
		BoxLayout helloMoneyboxLayout = new BoxLayout(MyPageAllPnl, BoxLayout.Y_AXIS);
		MyPageAllPnl.setLayout(helloMoneyboxLayout);
		BoxLayout createAllLayout = new BoxLayout(MainPnlCreatePage, BoxLayout.Y_AXIS);
		MainPnlCreatePage.setLayout(createAllLayout);
		BoxLayout createInputLayout = new BoxLayout(CreateAllPnl, BoxLayout.Y_AXIS);
		CreateAllPnl.setLayout(createInputLayout);
		
		lottoTotalMoney = new JLabel("당첨금 " + totalLotteWinnings + "원!!!");
		lottoTotalMoney.setHorizontalAlignment(JLabel.CENTER);		
		JLabel stringId = new JLabel("아이디 :");
		JLabel stringPw = new JLabel("비밀번호 :");
		stringName = new JLabel("");
		stringName.setHorizontalAlignment(JLabel.CENTER);	
		JLabel createId = new JLabel("아이디 :");
		JLabel createIdCheck = new JLabel("중복된 아이디가 없습니다.");
		createIdCheck.setForeground(new Color(68, 82, 28));
		JLabel createPw = new JLabel("비밀번호 :");
		JLabel createPwConfirm = new JLabel("비밀번호 확인 :");
		JLabel createName = new JLabel("이름 :");
		JLabel createPwCheck = new JLabel("비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
		createPwCheck.setForeground(new Color(155, 17, 30));
		JLabel createAge = new JLabel("생년월일 :");
		JLabel createYear = new JLabel("년 ");
		JLabel createMonth = new JLabel("월 ");
		JLabel createDay = new JLabel("일");
		mypageReserve = new JLabel("보유금 : ");
		mypageBank = new JLabel("계좌 : ");

		JCheckBox PwSee = new JCheckBox("비밀번호 보기");
		PwSee.setOpaque(false);
		
		String[] monthCombo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		JComboBox monthComboBox = new JComboBox<>(monthCombo);
		monthComboBox.setPreferredSize(new Dimension(50, 20));
		
		JButton signIn = new MyButton("로그인");
		JButton create = new MyButton("회원가입");
		JButton nonMembers = new MyButton("비회원 구입");
		signout = new MyEmphasizeButton("로그아웃");
		nextBtn = new MyButton("로또 구매");
		nextBtn.setBackground(new Color(127, 153, 248));
		JButton createAccount = new MyButton("회원가입");
		JButton createReturn = new MyEmphasizeButton("되돌아가기");
		JButton chargeBtn = new MyButton("충전");

		id.setText("");
		JPasswordField pw = new JPasswordField(10);
		pw.setText("");
		JTextField createInputId = new JTextField(10);
		JPasswordField createInputPw = new JPasswordField(10);
		JPasswordField createInputPwConfirm = new JPasswordField(10);
		JTextField createInputName = new JTextField(10);
		JTextField createInputYear = new JTextField(5);
		JTextField createInputDay = new JTextField(3);

		id.addKeyListener(new KeyAdapter() { // 로그인 아이디 텍스트 필드에서 엔터로 로그인
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
					signIn.doClick();
				}
			}
		});
		
		pw.addKeyListener(new KeyAdapter() { // 로그인 비번 텍스트 필드에서 엔터 쳐도 로그인
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
					signIn.doClick();
				}
			}
		});
		
		signIn.addActionListener(new ActionListener() { // 로그인 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (pw.getText().equals(((Account) AccountMap.get(id.getText())).getPw())) {
						JOptionPane.showMessageDialog(MainPage.this, "로그인 되었습니다.");
						stringName.setText((((Account) AccountMap.get(id.getText())).getName()) + "님 환영합니다!");
						showMyBankMoney = (((Account) AccountMap.get(id.getText())).getBankReserve());
						mylottoReserve = (((Account) AccountMap.get(id.getText())).getLottoReserve());
						mypageBank.setText("계좌 : " + showMyBankMoney);
						mypageReserve.setText("보유금 : " + mylottoReserve);
						cardLogIn.show(MainPnlLogIn, "MyPage");
					} else {
						JOptionPane.showMessageDialog(MainPage.this, "일치하는 회원정보가 없습니다!");
					}
				} catch (NullPointerException n) {
					if (0 == id.getText().length()) {
						JOptionPane.showMessageDialog(MainPage.this, "아이디를 입력해주세요.");
					} else if (0 == pw.getText().length()) {
						JOptionPane.showMessageDialog(MainPage.this, "비밀번호를 입력해주세요.");
					} else {
						JOptionPane.showMessageDialog(MainPage.this, "일치하는 회원정보가 없습니다!");
					}
				}
			}
		});
		
		signout.addActionListener(new ActionListener() { // 로그아웃 버튼 // 아이디 저장 체크박스 만들자!
			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				pw.setText("");
				cardLogIn.show(MainPnlLogIn, "LogIn");
			}
		});
		
		create.addActionListener(new ActionListener() { // 회원가입 페이지로 넘어가는 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLogIn.show(MainPnlLogIn, "CreatePage");
				id.setText("");
				pw.setText("");
				createInputId.setText("");
				createInputPw.setText("");
				createInputPwConfirm.setText("");
				createInputName.setText("");
			}
		});
		
		nonMembers.addActionListener(new ActionListener() { // 비회원 구입
			@Override
			public void actionPerformed(ActionEvent e) {
				ranMyNum++;
				JOptionPane.showMessageDialog(MainPage.this, "비회원 구입을 선택하셨습니다.\n"
						+ "로또 당첨 시, 개인고유번호를 지참하셔야 당첨금을 받을 수 있습니다.\n\n"
						+ "개인고유번호 : " + String.format("%06d", ranMyNum));
				stringName.setText("비회원 페이지입니다!");
				
				id.setText("nonmember");
				showMyBankMoney = (((Account) AccountMap.get("nonmember")).getBankReserve());
				mylottoReserve = (((Account) AccountMap.get("nonmember")).getLottoReserve());
				mypageBank.setText("계좌 : ∞");
				mypageReserve.setText("보유금 : " + mylottoReserve);
				
				nextBtn.doClick();
			}
		});

		createInputId.addKeyListener(new KeyAdapter() { // 회원가입 아이디 입력
			@Override
			public void keyReleased(KeyEvent e) {
				String copyId = createInputId.getText().replaceAll("\\s", "");
				createInputId.setText(copyId);
				
				if(createInputId.getText().length() < 4 || createInputId.getText().length() > 12) {
					createIdCheck.setText("아이디의 길이는 4~12자 사이로 입력해야 합니다.");
					createIdCheck.setForeground(new Color(155, 17, 30));
				} else if (AccountMap.containsKey(createInputId.getText())) {
					createIdCheck.setText("중복된 아이디가 있습니다!");
					createIdCheck.setForeground(new Color(155, 17, 30));
				} else {
					createIdCheck.setText("중복된 아이디가 없습니다.");
					createIdCheck.setForeground(new Color(68, 82, 28));
				}
			}
		});
		
		PwSee.addActionListener(new ActionListener() { // 회원가입 비밀번호 보이기 체크박스
			@Override
			public void actionPerformed(ActionEvent e) {
				if(PwSee.isSelected()) {
					createInputPw.setEchoChar((char)0);
					createInputPwConfirm.setEchoChar((char)0);
				} else {
					createInputPw.setEchoChar('\u2022');
					createInputPwConfirm.setEchoChar('\u2022');
				}
			}
		});
		
		createInputPw.addKeyListener(new KeyAdapter() { // 회원가입 비밀번호 입력
			@Override
			public void keyReleased(KeyEvent e) {
				String copyPw = createInputPw.getText().replaceAll("\\s", "");
				createInputPw.setText(copyPw);
				
				if (createInputPw.getText().length() < 4 || createInputPw.getText().length() > 12) {
					createPwCheck.setText("비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
				} else if (!createInputPw.getText().equals(createInputPwConfirm.getText())) {
					createPwCheck.setText("비밀번호가 같지 않습니다!");
				} else {
					createPwCheck.setText(" ");
				}
			}
		});
		
		createInputPwConfirm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String copyPw = createInputPwConfirm.getText().replaceAll("\\s", "");
				createInputPwConfirm.setText(copyPw);
				
				if (createInputPw.getText().length() < 4 || createInputPw.getText().length() > 12) {
					createPwCheck.setText("비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
				} else if(!createInputPw.getText().equals(createInputPwConfirm.getText())) {
					createPwCheck.setText("비밀번호가 같지 않습니다!");
				} else {
					createPwCheck.setText(" ");
				}
			}
		});
		
		createInputYear.addKeyListener(new KeyAdapter() { // 회원가입 년도 입력 텍스트 필드
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if(createInputYear.getText().length() > 3) {
					e.consume();
				}
			}
		});
		
		createInputDay.addKeyListener(new KeyAdapter() { // 회원가입 생일 입력 텍스트 필드
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				if(createInputDay.getText().length() > 1) {
					e.consume();
				}
			}
		});
		
		createAccount.addActionListener(new ActionListener() { // 계정 만들기 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = createInputId.getText();
				String pw = createInputPw.getText();
				String pw2 = createInputPwConfirm.getText();
				String name = createInputName.getText();
				
				int today = Integer.valueOf(ageTextFormat.format(todayCalender.getTime()));
				
				inputYear =  Integer.valueOf(createInputYear.getText()) * 10000;
				inputMonth =  Integer.valueOf(monthComboBox.getSelectedItem().toString()) * 100;
				inputDay =  Integer.valueOf(createInputDay.getText());
				inputAge =  inputYear + inputMonth + inputDay;
				
				boolean PwConfirm = pw.equals(pw2);
				boolean iplength = 4 > id.length() || id.length() > 12
						|| 4 > pw.length() || pw.length() > 12;
						
				String checkIdPw = "^[a-zA-Z0-9]*$";
				String checkName = "^[가-힣]*$";
						
				if (AccountMap.containsKey(id)) {
					JOptionPane.showMessageDialog(MainPage.this, "같은 아이디가 있습니다!");
				} else if (!(id.matches(checkIdPw) && pw.matches(checkIdPw))) {
					JOptionPane.showMessageDialog(MainPage.this, "아이디와 비밀번호에는 대소문자와 숫자만 입력 가능합니다.");
				} else if (!name.matches(checkName)) {
					JOptionPane.showMessageDialog(MainPage.this, "이름의 형식이 잘못되었습니다.\n한글로 입력해주세요.");
				} else if (iplength) {
					JOptionPane.showMessageDialog(MainPage.this, "아이디와 비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
				} else if (!PwConfirm) {
					JOptionPane.showMessageDialog(MainPage.this, "비밀번호가 일치하지 않습니다.");
				} else if (19061230 > inputAge) {
					JOptionPane.showMessageDialog(MainPage.this, "대한민국의 최고령자 나이를 넘어섰어요!");
				} else if (inputAge > today) {
					JOptionPane.showMessageDialog(MainPage.this, "헉! 드디어 타임머신이 발명된걸까요?");
				} else if ((today / 10000) - (inputYear / 10000)  < 19) {
					JOptionPane.showMessageDialog(MainPage.this, "청소년보호법 제2조 제1호 규정에 의거\n만 19세 이하는 로또를 구입할 수 없습니다.");
				} else {
					JOptionPane.showMessageDialog(MainPage.this, "회원가입 되었습니다.");
					int myBankMoney = randomInt.nextInt(999) * 1000 + randomInt.nextInt(999) * 10;
					AccountMap.put(id, new Account(id, pw, name, inputAge, myBankMoney, mylottoReserve)); // 보유금 수정!
					createInputYear.setText("");
					createInputDay.setText("");
					monthComboBox.setSelectedIndex(0);
					cardLogIn.show(MainPnlLogIn, "LogIn");
				}
			}
		});
		
		createReturn.addActionListener(new ActionListener() { // 계정 생성 취소하고 되돌아가기 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				createInputId.setText("");
				createInputPw.setText("");
				createInputPwConfirm.setText("");
				createInputName.setText("");
				createInputYear.setText("");
				createInputDay.setText("");
				cardLogIn.show(MainPnlLogIn, "LogIn");
			}
		});
		
		chargeBtn.addActionListener(new ActionListener() {	// 보유금 충전 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				MoneyChargeWindow dialog = new MoneyChargeWindow(MainPage.this);
				dialog.showDialog();
			}
		});

		Mainppp.add(MainAll);
		
		MainAll.add(MainIMGPnl, BorderLayout.WEST);
		MainAll.add(MainPnlLogIn);

		MainPnlLogIn.add(MainPnlLogInPage, "LogIn"); // 카드 레이아웃 먹인 전체 패널 MainPnlLogIn에 바뀔 카드 패널들
		MainPnlLogIn.add(MainPnlMyPage, "MyPage");
		MainPnlLogIn.add(MainPnlCreatePage, "CreatePage");
		
		MainPnlLogInPage.add(MainLogInAllPnl);
		MainLogInAllPnl.add(MainLogInCenterPnl);
		MainLogInCenterPnl.add(MainLogInTotalMoneyPnl);
		MainLogInCenterPnl.add(MainLogInIDPnl);
		MainLogInCenterPnl.add(MainLogInPWPnl);
		MainLogInCenterPnl.add(nonMembers);
		MainLogInTotalMoneyPnl.add(lottoTotalMoney);
		MainLogInIDPnl.add(stringId);
		MainLogInIDPnl.add(id);
		MainLogInIDPnl.add(signIn);
		MainLogInPWPnl.add(stringPw);
		MainLogInPWPnl.add(pw);
		MainLogInPWPnl.add(create);
		
		MainPnlMyPage.add(MyPagePnl);	
		MyPagePnl.add(MyPageAllPnl);
		MyPagePnl.add(nextBtn);
		MyPageAllPnl.add(MyPageHelloPnl);
		MyPageAllPnl.add(MyPageMoneyPnl);
		MyPageAllPnl.add(MyPageLogOutPnl);
		MyPageHelloPnl.add(stringName);
		MyPageMoneyPnl.add(mypageBank);
		MyPageMoneyPnl.add(MyPageReservePnl);
		MyPageReservePnl.add(mypageReserve);
		MyPageReservePnl.add(chargeBtn);
		MyPageLogOutPnl.add(signout);
		
		MainPnlCreatePage.add(CreateAllPnl);
		CreateAllPnl.add(CreatenullPnl);
		CreateAllPnl.add(CreatePageIdPnl);
		CreateAllPnl.add(CreatePageIdCheckPnl);
		CreateAllPnl.add(CreatePagePwPnlTop);
		CreateAllPnl.add(CreatePagePwPnl);
		CreateAllPnl.add(CreatePagePwCPnl);
		CreateAllPnl.add(CreatePagePwCheckPnl);
		CreateAllPnl.add(CreatePageNamePnl);
		CreateAllPnl.add(CreatePageAgePnl);
		MainPnlCreatePage.add(CreatePageAccountAndReturn);
		CreatePageIdPnl.add(createId);
		CreatePageIdPnl.add(createInputId);
		CreatePageIdCheckPnl.add(createIdCheck);
		CreatePagePwPnlTop.add(PwSee);
		CreatePagePwPnl.add(createPw);
		CreatePagePwPnl.add(createInputPw);
		CreatePagePwCPnl.add(createPwConfirm);
		CreatePagePwCPnl.add(createInputPwConfirm);
		CreatePagePwCheckPnl.add(createPwCheck);
		CreatePageNamePnl.add(createName);
		CreatePageNamePnl.add(createInputName);
		CreatePageAgePnl.add(createAge);
		CreatePageAgePnl.add(createInputYear);
		CreatePageAgePnl.add(createYear);
		CreatePageAgePnl.add(monthComboBox);
		CreatePageAgePnl.add(createMonth);
		CreatePageAgePnl.add(createInputDay);
		CreatePageAgePnl.add(createDay);
		CreatePageAccountAndReturn.add(createAccount);
		CreatePageAccountAndReturn.add(createReturn);

		add(Mainppp);
		
		cardLogIn.show(MainPnlLogIn, "LogIn");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 600);
	}

	public static void main(String[] args) {
		new MainPage().setVisible(true);
	}
}