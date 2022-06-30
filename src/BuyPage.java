import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

public class BuyPage extends JFrame {
	////////////////////////
	Random random = new Random();
	int numcount=0;
	int[] lottoNum = new int[6];
	JPanel pnl = new JPanel();
	JButton nextBtn = new JButton("결과 보기");
	
	public JPanel getLottoBuyPnl() {
		return pnl;
	}
	
	public JButton getNextBtn() {
		return nextBtn;
	}

	BuyPage() {
		pnl.setBackground(new Color(248, 202, 204));
		JPanel inputPnl = new JPanel();
		inputPnl.setOpaque(false);
		BoxLayout inputpnlLayout = new BoxLayout(inputPnl, BoxLayout.Y_AXIS);
		JPanel editPnl = new JPanel();
		editPnl.setOpaque(false);
		BoxLayout pnlLayout = new BoxLayout(pnl, BoxLayout.X_AXIS);
		pnl.setLayout(pnlLayout);
		inputPnl.setLayout(inputpnlLayout);
		
		pnl.add(inputPnl);
		pnl.add(editPnl);
		
		add(pnl);
		////////////////////////////////////
		// Edit

		JLabel check = new JLabel("로또번호는?");
		editPnl.add(check);
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
						numcount++;
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
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new BuyPage().setVisible(true);
	}
}