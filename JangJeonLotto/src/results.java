import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 결과 - 랜덤 로또 번호 생성(보너스 생각 말고 6배열 - 6배열 비교), 비교 - 같은 수 개수 + 값 출력

public class results extends JFrame {
	Random random = new Random();
	Set<Integer> lotto = new HashSet<>();	// 당첨번호
	int[] a = new int[6];
	Integer[] b = new Integer[6];
	
//	public results() {
//		JPanel pnl = new JPanel();
//		JLabel lbl = new JLabel();
//		for(int i = 1; i <= 5; i++) {
//			J
//		}
//		
//	}
	
	
	/// 당첨번호 만드는 메소드 메소드로 만들기
	public void getNumber(Set<Integer> set) {
		// 배열일 때
//		for(int i = 0; i <= 5; i++) {
//		b[i] = random.nextInt(45) + 1;
//		}
		
		// set일 때(중복 값 지우기)
		while(set.size() < 6) {
			Random random = new Random();
			int r = random.nextInt(46);
			if(r != 0) {
				set.add(r);
			}
		}
		
		
	}
	
	//
	public void comparing(int[] a) {
		for(int i = 0; i < a.length; i++) {
			b[i] = a[i];
		}
		
		for(int i = 0; i <= 5; i++) {
			for(Object o : lotto) {
				if(b[i] = o
			}
		}
	}
	
	
//	public boolean compares(int[][] a) {
//		for(int j =0; j <= 5; j++) {
//			for(int i = 0; i<= 5; i++) {
//				if(answer[i] == a[0][i]) {
//					System.out.println("같다");
//				} else {
//					System.out.println("다르다");
//				}
//			}
//		}
//	}
	
//	public void comparing(int[] a) {
//		for(int i = 0; i<= 5; i++) {
//			if(answer[i] == a[i]) {
//				System.out.println("같다");
//				System.out.println(a[i]);
//			} else {
//				System.out.println("다르다");
//				System.out.println(a[i]);
//			}
//		}
//	}
	
	
	public static void main(String[] args) {
		results re = new results();
		re.getNumber(re.a);
		re.getNumber(re.answer);
		System.out.println(Arrays.toString(re.a));
		System.out.println(Arrays.toString(re.answer));
		re.comparing(re.a);
	}
}
