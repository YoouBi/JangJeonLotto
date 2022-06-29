import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

public class Results extends JFrame {
	
	Random random = new Random();
	Set<Integer> lotto = new HashSet<>(); // 당첨번호
	int[] a = new int[6];
	Integer[] b = new Integer[6];
	List<Integer> list;
	String[] same = new String[6];// 같음, 다름 넣을 배열
	private JPanel pnl;
	private JButton btn;
	
	public JPanel getPnl() {
		return pnl;
	}

	public JButton getBtn() {
		return btn;
	}


	public Results() {
		getNumber(lotto);
		getNumber2(a);
		comparing(a);
		
		
		pnl = new JPanel();
		JPanel pnlA = new JPanel();
		pnlA.setBounds(0, 0, 784, 138);
		JLabel lbl1 = new JLabel("당첨 번호");
		JPanel pnlB = new JPanel();
		pnlB.setBounds(0, 138, 784, 138);
		JLabel lbl2 = new JLabel("일치 여부");
		JPanel pnlC = new JPanel();
		pnlC.setBounds(0, 276, 784, 138);
		JLabel lbl3 = new JLabel("추첨 번호");
		
		pnlA.add(lbl1);
		pnlB.add(lbl2);
		pnlC.add(lbl3);
		pnl.setLayout(null);
		pnl.add(pnlA);
		for(int i = 0; i < lotto.size(); i++) {
			JLabel lottoA = new JLabel(String.valueOf(list.get(i)));
			pnlA.add(lottoA);
		}
		
		pnl.add(pnlB);
		for(int i = 0; i < same.length; i++) {
			JLabel same1 = new JLabel(same[i]);
			pnlB.add(same1);
		}
		
		pnl.add(pnlC);
		for(int i = 0; i < a.length; i++) {
			JLabel lottoB = new JLabel(String.valueOf(a[i]));
			pnlC.add(lottoB);
		}
		lbl3.setBounds(0, 0, 65, 40);
		
		
		JLabel price = new JLabel("금액 = 300,000,000원");
		price.setBounds(78, 424, 315, 24);
		price.setFont(new Font("굴림", Font.PLAIN, 20));
		btn = new JButton("다음 회차");
		btn.setBounds(550, 424, 222, 23);
		
		pnl.setBackground(new Color(248, 202, 204));
		pnl.add(price);
		pnl.add(btn);
		
		pnlA.setOpaque(false); // 배경 색을 따라가도록
		pnlB.setOpaque(false);
		pnlC.setOpaque(false);
		getContentPane().add(pnl);
		
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	

	// 당첨번호 만드는 메소드 메소드로 만들기
	public void getNumber(Set<Integer> set) {
		// set일 때(중복 값 지우기)
		while (set.size() < 6) {
			Random random = new Random();
			int r = random.nextInt(46);
			if (r != 0) {
				set.add(r);
			}
		}
		
		// 리스트로 변환 후 정렬
		list = new ArrayList<>(lotto);
		Collections.sort(list);
		System.out.println(list);
	}

	// 당첨번호 만드는 메소드 메소드로 만들기
	public void getNumber2(int[] a) {
		// 배열일 때
		for (int i = 0; i <= 5; i++) {
			a[i] = random.nextInt(45) + 1;
		}
		
		Arrays.sort(a);
	}

	// 배열과 set 비교
	public void comparing(int[] a) {
		int count = 0;
		for (int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		for (int i = 0; i <= 5; i++) {
			for (Object o : lotto) {
				if (b[i] == o) {
					System.out.println(b[i]);
					// 같음이 나오면 배열 "같음
					same[i] = "같음";
					System.out.println(same[i]);
				} else {
					count++;
					}
				if(count == 6) {
					System.out.println("다름");
					// 다름이 나오면 배열 "다름"
					same[i] = "다름";
					System.out.println(same[i]);
					count = 0;
				}
			}
			
		}
	}

	public static void main(String[] args) {
		Results re = new Results();
		re.getNumber(re.lotto);
		re.getNumber2(re.a);
//		System.out.println(Arrays.toString(re.a));
//		re.comparing(re.a);
		
		new Results().setVisible(true);
	}
}
