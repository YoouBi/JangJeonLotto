import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Dimension;

class login {
	private String id;
	private String pw;
	private String name;
//	로또 배열 저장할거 들어갈 것

	public login(String id, String pw, String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
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
}

public class MainPage extends JFrame {
	private JPanel MainAll;
	private JButton start;
	private JLabel stringName;
	
	public JPanel getMainAll() {
		return MainAll;
	}

	public JButton getStart() {
		return start;
	}

	public MainPage() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		HashMap map = new HashMap<String, login>();
		// 아이디, 비밀번호, 이름
		map.put("YoouBi", new login("YoouBi", "yoyobiii", "장영빈"));
		map.put("Inha123", new login("Inha123", "Inha123", "전인하"));
		map.put("yeriming", new login("yeriming", "yeriming", "장예림"));

//		Mainppp = new JPanel();
//		JPanel MainAll = new JPanel();
		MainAll = new JPanel();
		JPanel MainPnl1 = new JPanel();
		JPanel MainPnl2 = new JPanel();
		JPanel MainPnl3 = new JPanel();
		JPanel MainPnl4 = new JPanel();
		JPanel MainPnl5 = new JPanel();
		
		CardLayout cardLogIn = new CardLayout();
		// MainPnlLogIn로 연결하여 오른쪽 패널들 바꾸는 레이아웃
		// 1. MainPnlLogPage1(기본 로그인하는 메인 페이지),
		// 2. (회원가입 페이지 만들 것),
		// 3. MainPnlLogPage2(마이페이지)
		JPanel MainPnlLogIn = new JPanel(cardLogIn);
		JPanel MainPnlLogInPage = new JPanel();
		JPanel MainPnlMyPage = new JPanel();
		JPanel MainPnlCreatePage = new JPanel();
		
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
		MainPnlCreatePage.setOpaque(false);

		BoxLayout box = new BoxLayout(MainAll, BoxLayout.X_AXIS);
		MainAll.setLayout(box);
		BoxLayout box2 = new BoxLayout(MainPnl4, BoxLayout.Y_AXIS);
		MainPnl4.setLayout(box2);
		BoxLayout box3 = new BoxLayout(MainPnlMyPage, BoxLayout.Y_AXIS);
		MainPnlMyPage.setLayout(box3);

		Image image = kit.getImage("images/lotto.png");
		Image changeimage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);

		JLabel lottoimg = new JLabel(new ImageIcon(changeimage));
		JLabel stringId = new JLabel("아이디 :");
		JLabel stringPw = new JLabel("비밀번호 :");
		JLabel stringPwConfirm = new JLabel("비밀번호 확인 :");
		stringName = new JLabel("");

		JButton signIn = new JButton("로그인");
		signIn.setBackground(new Color(255, 255, 255));
		JButton create = new JButton("회원가입");
		create.setBackground(new Color(255, 255, 255));
		JButton signout = new JButton("로그아웃");
		signout.setBackground(new Color(255, 255, 255));
		start = new JButton("로또 구매");
		start.setBackground(new Color(127, 153, 248));

		JTextField id = new JTextField(10);
		id.setText("");
		JPasswordField pw = new JPasswordField(10);
		pw.setText("");

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
		
		create.addActionListener(new ActionListener() { // 회원가입 페이지로 넘어갈 것
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		signout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				pw.setText("");
				stringName.setText("");
				cardLogIn.show(MainPnlLogIn, "LogIn");
			}
		});

//		Mainppp.add(MainAll);
		
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
		
		MainPnlMyPage.add(stringName);
		MainPnlMyPage.add(signout);
		MainPnlMyPage.add(start);

		add(MainAll);
		
		cardLogIn.show(MainPnlLogIn, "LogIn");

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
	}

	public static void main(String[] args) {
		new MainPage().setVisible(true);
	}

}
