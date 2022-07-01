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
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.Event;

class login {
	private String id;
	private String pw;
	private String name;
	private int age; // 우리나라 최고령자 나이는 115세...
//	로또 배열 저장 필드도 만들기!
	
	public login(String id, String pw, String name, int age) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.age = age;
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
}

public class MainPage extends JFrame {
	private JPanel Mainppp;
	private JButton start;
	private JLabel stringName;
	private HashMap<String, login> map;

	public JPanel getPnl() {
		return Mainppp;
	}

	public JButton getStart() {
		return start;
	}

	public MainPage() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		map = new HashMap<>();
		// 아이디, 비밀번호, 이름
		map.put("YoouBi", new login("YoouBi", "yoyobiii", "장영빈", 20020101));
		map.put("Inha123", new login("Inha123", "Inha123", "전인하", 20020202));
		map.put("yeriming", new login("yeriming", "yeriming", "장예림", 20020303));

		int inputAge = 20220630;
		
		Mainppp = new JPanel(new BorderLayout());
		JPanel MainAll = new JPanel();
		JPanel MainPnl1 = new JPanel();
		JPanel MainPnl2 = new JPanel();
		JPanel MainPnl3 = new JPanel();
		JPanel MainPnl4 = new JPanel();
		JPanel MainPnl5 = new JPanel();
		
		CardLayout cardLogIn = new CardLayout();
		// MainPnlLogIn로 연결하여 오른쪽 패널들 바꾸는 레이아웃
		// 1. MainPnlLogPage1(기본 로그인하는 메인 페이지)
		// 2. (회원가입 페이지 만들 것)
		// 3. MainPnlLogPage2(마이페이지)
		JPanel MainPnlLogIn = new JPanel(cardLogIn);
		JPanel MainPnlLogInPage = new JPanel();
		JPanel MainPnlMyPage = new JPanel();
		JPanel MainPnlCreatePage = new JPanel();
		JPanel CreatePageIdPnl = new JPanel();
		JPanel CreatePagePwPnl = new JPanel();
		JPanel CreatePagePwCPnl = new JPanel();
		JPanel CreatePageNamePnl = new JPanel();
		JPanel CreatePageAgePnl = new JPanel();
		JPanel CreatePageAccountAndReturn = new JPanel();
		JPanel MyPagePnl = new JPanel();
		
		MainPnl1.setPreferredSize(new Dimension(350, 350));
		MainPnl4.setBounds(50, 150, 200, 200);

		MainAll.setBackground(new Color(248, 202, 204));
		MainPnl1.setOpaque(false);
		MainPnl2.setOpaque(false);
		MainPnl3.setOpaque(false);
		MainPnl4.setOpaque(false);
		MainPnl5.setOpaque(false);
		MainPnlLogIn.setOpaque(false);
		MainPnlLogInPage.setOpaque(false);
		
		MainPnlMyPage.setOpaque(false);
		MyPagePnl.setOpaque(false);
		
		MainPnlCreatePage.setOpaque(false);
		CreatePageIdPnl.setOpaque(false);
		CreatePagePwPnl.setOpaque(false);
		CreatePagePwCPnl.setOpaque(false);
		CreatePageNamePnl.setOpaque(false);
		CreatePageAgePnl.setOpaque(false);
		CreatePageAccountAndReturn.setOpaque(false);

//		BorderLayout border = new BorderLayout();
		BoxLayout box = new BoxLayout(MainAll, BoxLayout.X_AXIS);
		MainAll.setLayout(box);
		BoxLayout box2 = new BoxLayout(MainPnl4, BoxLayout.Y_AXIS);
		MainPnl4.setLayout(box2);
		BoxLayout box3 = new BoxLayout(MyPagePnl, BoxLayout.Y_AXIS);
		MyPagePnl.setLayout(box3);
		BoxLayout box4 = new BoxLayout(MainPnlCreatePage, BoxLayout.Y_AXIS);
		MainPnlCreatePage.setLayout(box4);

		Image image = kit.getImage("resorces/images/lotto.png");
		Image changeimage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);

		JLabel lottoimg = new JLabel(new ImageIcon(changeimage));
		JLabel stringId = new JLabel("아이디 :");
		JLabel stringPw = new JLabel("비밀번호 :");
		stringName = new JLabel("");
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

		JCheckBox PwSee = new JCheckBox("비밀번호 보기");
		PwSee.setOpaque(false);
		
		String[] monthCombo = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		JComboBox monthComboBox = new JComboBox<>(monthCombo);
		monthComboBox.setPreferredSize(new Dimension(50, 20));
		
		JButton signIn = new JButton("로그인");
		signIn.setBackground(new Color(255, 255, 255));
		JButton create = new JButton("회원가입");
		create.setBackground(new Color(255, 255, 255));
		JButton signout = new JButton("로그아웃");
		signout.setBackground(new Color(255, 255, 255));
		start = new JButton("로또 구매");
		start.setBackground(new Color(127, 153, 248));
		JButton createAccount = new JButton("회원가입");
		createAccount.setBackground(new Color(127, 153, 248));
		JButton createReturn = new JButton("되돌아가기");
		createReturn.setBackground(new Color(127, 153, 248));

		JTextField id = new JTextField(10);
		id.setText("");
		JPasswordField pw = new JPasswordField(10);
		pw.setText("");
		JTextField createInputId = new JTextField(10);
		JPasswordField createInputPw = new JPasswordField(10);
		JPasswordField createInputPwConfirm = new JPasswordField(10);
		JTextField createInputName = new JTextField(10);
		JTextField createInputYear = new JTextField(5);
		JTextField createInputDay = new JTextField(3);

		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (pw.getText().equals(((login) map.get(id.getText())).getPw())) {
						JOptionPane.showMessageDialog(MainPage.this, "로그인 되었습니다.");
						stringName.setText((((login) map.get(id.getText())).getName()) + "님 환영합니다!");
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

		createInputId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(createInputId.getText().length() < 4 || createInputId.getText().length() > 12) {
					createIdCheck.setText("아이디의 길이는 4~12자 사이로 입력해야 합니다.");
					createIdCheck.setForeground(new Color(155, 17, 30));
				} else if (map.containsKey(createInputId.getText())) {
					createIdCheck.setText("중복된 아이디가 있습니다!");
					createIdCheck.setForeground(new Color(155, 17, 30));
				} else {
					createIdCheck.setText("중복된 아이디가 없습니다.");
					createIdCheck.setForeground(new Color(68, 82, 28));
				}
			}
		});
		
		createInputPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (createInputPw.getText().length() < 4 || createInputPw.getText().length() > 12) {
					createPwCheck.setText("비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
				} else if (!createInputPw.getText().equals(createInputPwConfirm.getText())) {
					createPwCheck.setText("비밀번호가 같지 않습니다!");
//				} else if(createInputPw.getText().length() > 3 && createInputPw.getText().length() < 13) {
				} else {
					createPwCheck.setText(" ");
				}
			}
		});
		
		PwSee.addActionListener(new ActionListener() {
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
		
		createInputPwConfirm.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (createInputPw.getText().length() < 4 || createInputPw.getText().length() > 12) {
					createPwCheck.setText("비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
				} else if(!createInputPw.getText().equals(createInputPwConfirm.getText())) {
					createPwCheck.setText("비밀번호가 같지 않습니다!");
				} else {
					createPwCheck.setText(" ");
				}
			}
		});
		
		createInputYear.addKeyListener(new KeyAdapter() { // 년도 입력 텍스트 필드
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
		
		createInputDay.addKeyListener(new KeyAdapter() {
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
				int age = 0;
				
				boolean PwConfirm = pw.equals(pw2);
				boolean iplength = 4 > id.length() || id.length() > 12
						|| 4 > pw.length() || pw.length() > 12;

				if (map.containsKey(id)) { // 아이디 길이가 0일때 가입 됨 고쳐야함!!!!!!!!!!!!!
					JOptionPane.showMessageDialog(MainPage.this, "같은 아이디가 있습니다!");
				} else if (iplength) {
					JOptionPane.showMessageDialog(MainPage.this, "아이디와 비밀번호의 길이는 4~12자 사이로 입력해야 합니다.");
				} else if (!PwConfirm) {
					JOptionPane.showMessageDialog(MainPage.this, "비밀번호가 일치하지 않습니다.");
				} else if (19061230 > inputAge) {
					JOptionPane.showMessageDialog(MainPage.this, "대한민국의 최고령자 나이를 넘어섰어요!");
				} else if (inputAge > 20220701) { // 나중에 날짜 바꿀 것!
					JOptionPane.showMessageDialog(MainPage.this, "헉! 드디어 타임머신이 발명된걸까요?");
				} else {
					JOptionPane.showMessageDialog(MainPage.this, "회원가입 되었습니다.");
					map.put(id, new login(id, pw, name, age));
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

		Mainppp.add(MainAll, BorderLayout.CENTER);
		
		MainAll.add(MainPnl1);
		MainAll.add(MainPnlLogIn);
		MainPnl1.add(lottoimg);

		MainPnlLogIn.add(MainPnlLogInPage, "LogIn");
		MainPnlLogIn.add(MainPnlMyPage, "MyPage");
		MainPnlLogIn.add(MainPnlCreatePage, "CreatePage");
		
		MainPnl2.add(stringId);
		MainPnl2.add(id);
		MainPnl2.add(signIn);
		MainPnl3.add(stringPw);
		MainPnl3.add(pw);
		MainPnl3.add(create);

		MainPnlLogInPage.add(MainPnl4);
		MainPnl4.add(MainPnl2);
		MainPnl4.add(MainPnl3);
		
		MainPnlMyPage.add(MyPagePnl);
		MyPagePnl.add(stringName);
		MyPagePnl.add(signout);
		MyPagePnl.add(start);
		
		MainPnlCreatePage.add(CreatePageIdPnl);
		MainPnlCreatePage.add(createIdCheck);
		MainPnlCreatePage.add(CreatePagePwPnl);
		MainPnlCreatePage.add(CreatePagePwCPnl);
		MainPnlCreatePage.add(createPwCheck);
		MainPnlCreatePage.add(CreatePageNamePnl);
		MainPnlCreatePage.add(CreatePageAgePnl);
		MainPnlCreatePage.add(CreatePageAccountAndReturn);
		CreatePageIdPnl.add(createId);
		CreatePageIdPnl.add(createInputId);
		CreatePagePwPnl.add(createPw);
		CreatePagePwPnl.add(createInputPw);
		CreatePagePwPnl.add(PwSee);
		CreatePagePwCPnl.add(createPwConfirm);
		CreatePagePwCPnl.add(createInputPwConfirm);
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
		setSize(800, 500);
	}

	public static void main(String[] args) {
		new MainPage().setVisible(true);
	}
}