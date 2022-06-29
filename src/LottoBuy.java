import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class LottoBuy extends JFrame {
	////////////////////////
	Random random = new Random();
	int numcount=0;
	int[] lottoNum = new int[6];
	JPanel pnl = new JPanel();
	JButton nextBtn = new JButton("결과 보기");
	
	public JPanel getLottoBuyPnl() {
		return pnl;
	}

	LottoBuy() {
		
		JPanel inputPnl = new JPanel();
		JPanel editPnl = new JPanel();
		BoxLayout pnlLayout = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(pnlLayout);

		pnl.add(editPnl);
		pnl.add(inputPnl);

		add(pnl);
		////////////////////////////////////
		// Edit

		JLabel check = new JLabel("로또번호는?");
		editPnl.add(check);
		editPnl.add(nextBtn);

		/// inputPnl
		////////////////////////////////////////////

		JPanel btnBox = new JPanel(); // 버튼 감싸는 박스
		JButton inputBtn = new JButton("입력");
		JButton resetBtn = new JButton("reset");
		JButton randomBtn = new JButton("Random");
		inputPnl.add(btnBox);
		inputPnl.add(inputBtn);
		inputPnl.add(resetBtn);
		inputPnl.add(randomBtn);

		GridLayout grid = new GridLayout(10, 5);
		btnBox.setLayout(grid);

		Map<Integer, JButton> btnMake = new HashMap<>();
		List<Integer> inputLottoNum = new ArrayList<>();
		
		

		for (int i = 0; i < 45; i++) {
			JButton a = new JButton(String.valueOf(i + 1));
			btnMake.put(i + 1, a);
			a.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (numcount<6) {
					String num = ((JButton) e.getSource()).getText();
					inputLottoNum.add(Integer.valueOf(num));
					((JButton) e.getSource()).setEnabled(false);
					numcount++;
					}
				}
			});
			btnBox.add(a);
		}

		inputBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int key : btnMake.keySet()) {
					btnMake.get(key).setEnabled(true);
				}
				for (int i = 0; i < 6; i++)
					lottoNum[i] = inputLottoNum.get(i);
				check.setText(Arrays.toString(lottoNum));
				inputLottoNum.clear();
				numcount=0;
			}
		});
		
		randomBtn.addActionListener(new ActionListener() {	// 랜덤 버튼
			@Override
			public void actionPerformed(ActionEvent e) {
				while (inputLottoNum.size()!=6) {
					int randomNum = random.nextInt(45)+1;
					if (inputLottoNum.contains(randomNum)) {
						continue;
					} else {
						inputLottoNum.add(randomNum);
						btnMake.get(randomNum).setEnabled(false);
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
				numcount=0;
			}
		});
		
		nextBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 로또 배열 넘겨주기, 다음패널로 넘어가기
				// 이 부분은 메인메소드에 만들기
			}
		});

		setTitle("로또 구입 창");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new LottoBuy().setVisible(true);
	}

}