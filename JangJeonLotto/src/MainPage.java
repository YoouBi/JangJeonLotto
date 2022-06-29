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
	String id;
	String pw;
	String name;
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
}

public class MainPage extends JFrame {
	public MainPage() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		HashMap map = new HashMap<String, login>();
		map.put("YoouBi", new login("YoouBi", "yoyobiii", "장영빈"));
		map.put("Inha123", new login("Inha123", "Inha123", "전인하"));
		
		JPanel MainAll = new JPanel();
		JPanel MainPnl1 = new JPanel();
		MainPnl1.setPreferredSize(new Dimension(300, 300));
		JPanel MainPnl2 = new JPanel();
		JPanel MainPnl3 = new JPanel();
		JPanel MainPnl4 = new JPanel();
		JPanel MainPnl5 = new JPanel();
		JPanel MainPnl6 = new JPanel();
		
		MainAll.setBackground(new Color(248, 202, 204));
		MainPnl1.setOpaque(false);
		MainPnl2.setOpaque(false);
		MainPnl3.setOpaque(false);
		MainPnl4.setOpaque(false);
		MainPnl5.setOpaque(false);
		MainPnl6.setOpaque(false);
		
		BoxLayout box = new BoxLayout(MainAll, BoxLayout.Y_AXIS);
		MainAll.setLayout(box);
		BoxLayout box2 = new BoxLayout(MainPnl6, BoxLayout.X_AXIS);
		MainPnl6.setLayout(box2);
		
//		CardLayout layout = new CardLayout();
		
		Image image = kit.getImage("images/lotto.png");
		Image changeimage = image.getScaledInstance(350, 350, Image.SCALE_SMOOTH);
		
		JLabel lottoimg = new JLabel(new ImageIcon(changeimage));
		JLabel stringId = new JLabel("아이디 :");
		JLabel stringPw = new JLabel("비밀번호 :");
		
		JButton signIn = new JButton("로그인");
		signIn.setBackground(new Color(255, 255, 255));
		JButton create = new JButton("회원가입");
		create.setBackground(new Color(255, 255, 255));
		JButton start = new JButton("로또 구매");
		start.setBackground(new Color(127, 153, 248));

		JTextField id = new JTextField(10);
		JPasswordField pw = new JPasswordField(10);
		//영빈이 짱>_<
		//너무 열심히 하지마,.,,,
		
		signIn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String i = ((login) map.get(id.getText())).getPw();
				if(pw.getText().equals(i)) {
					JOptionPane.showMessageDialog(MainPage.this, "로그인 되었습니다.");
				} else {
					JOptionPane.showMessageDialog(MainPage.this, "일치하는 회원정보가 없습니다!");
				}
			}
		});
		
		MainAll.add(MainPnl1);
		MainAll.add(MainPnl6);
		MainPnl1.add(lottoimg);
		
		MainPnl2.add(stringId);
		MainPnl2.add(id);
		MainPnl2.add(signIn);
		MainPnl3.add(stringPw);
		MainPnl3.add(pw);
		MainPnl3.add(create);
		
		MainPnl6.add(MainPnl4);
		MainPnl6.add(MainPnl5);
		MainPnl4.add(MainPnl2);
		MainPnl4.add(MainPnl3);
		MainPnl5.add(start);
		
		add(MainAll);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 500);
	}
	
	public static void main(String[] args) {
		new MainPage().setVisible(true);
	}
}
