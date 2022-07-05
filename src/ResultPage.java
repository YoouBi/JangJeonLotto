import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.ToolTipManager;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class ResultPage {
	// 6/30 : 넘겨받는 배열 타입에 따라 메소드 전반적 변경
	// 6/31 : 보너스 번호 추첨, 등수 출력, 버튼 마우스오버창
	// 7/1 : 같음, 다름 여부 메소드 수정
	// 7/2 : 등수 출력 메소드 완성, 변수명 변경
	// 7/4 : 당첨 금액 메소드 완성
	// 할일
	// 1. 돌아갈 때 사용자 구입 로또 배열과 당첨번호 리셋
	// 2. 같으면 번호 색 변경 메소드 만들기

	BuyPage lottoBuy = new BuyPage();

	// Random 인스턴스 생성
	Random random = new Random();
	// 로또 당첨 번호
	List<Integer> lottoNum;
	// 내 선택 로또값 들어간 배열
	List<Integer> buyLottoNum;
	// 내 선택 로또값 들어간 배열을 받는 ArrayList
	List<List<Integer>> buyLottoNumList = new ArrayList<>();

	// 로또 보너스 값
	int lottoBonus = 0;

	// 같음, 다름 여부 넣는 배열
	List<String> same;
	// 같음, 다름 배열을 받는 String 배열
	List<List<String>> sameList = new ArrayList<>();

	// 등수 출력 위한 배열
	List<String> ranking;
	// 전체 판매액 담을 정수 타입
	int totalMoney = 300000000;
	// 총 당첨 금액 담을 정수 타입
	int winningTotal = 0;
	/////////////// 연습 값 담을 set/////////////////////////
	Set<Integer> practice = new HashSet<>();
	/////////////// 연습 값 담을 set end/////////////////////////

	JLabel[][] lotto = new JLabel[5][8];

	private JPanel pnl;
	private JButton nextBtn;

	public List<List<Integer>> getBuyLottoNumList() {
		return buyLottoNumList;
	}

	public void setBuyLottoNumList(List<List<Integer>> buyLottoNumList) {
		this.buyLottoNumList = buyLottoNumList;
	}

	public JPanel getPnl() {
		return pnl;
	}

	public JButton getNextBtn() {
		return nextBtn;
	}

	// Result 화면 생성
	public ResultPage() {
		getLottoNum();
	}

	// pnl 만들 메소드
	public void setPanel() {

		// 이미지 받아오기
		Toolkit kit = Toolkit.getDefaultToolkit();
		URL url = ResultPage.class.getClassLoader().getResource("images/critical.png");
		ImageIcon image = new ImageIcon(kit.getImage(url).getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		
		URL backgroundUrl = ResultPage.class.getClassLoader().getResource("images/backgroundimg.png");
		Image background = new ImageIcon(backgroundUrl).getImage();
		// 이미지 받아오기 끝

		// pnlLottoNums 시작
		JPanel pnlLottoNums = new JPanel();
		JLabel lblLottoNums = new JLabel("당첨 번호");
		pnlLottoNums.add(lblLottoNums);
		for (int lottoNumIndex = 0; lottoNumIndex < lottoNum.size(); lottoNumIndex++) {
			JLabel showLottoNum = new JLabel(String.valueOf(lottoNum.get(lottoNumIndex)));
			pnlLottoNums.add(showLottoNum);
		}

		JLabel lblBonusNum = new JLabel("보너스 번호");
		pnlLottoNums.add(lblBonusNum);
		JLabel showBonusNum = new JLabel(Integer.toString(lottoBonus));
		pnlLottoNums.add(showBonusNum);
		// pnlLottoNums 끝

		// underLottoNums
		JPanel underLottoNums = new JPanel(); // lblLottoNums와 나누기 위해 만든 패널

		// printResult
		makefield(lotto);

		JPanel printResult = new JPanel();
		for (int i = 0; i < 5; i++) {
			JPanel a = new JPanel();
			printResult.add(a);
			for (int j = 0; j < 8; j++) {
				a.add(lotto[i][j]);
			}
		}
		// printResult 끝
		
		BoxLayout result = new BoxLayout(printResult, BoxLayout.Y_AXIS);
		printResult.setLayout(result);

		// pnlBuyNums
		for (int i=0; i<buyLottoNumList.size(); i++) {
			for(int j=1; j < buyLottoNumList.get(0).size()+1; j++) {
				lotto[i][j].setText(String.valueOf(buyLottoNumList.get(i).get(j - 1)));
				lotto[i][j].setForeground(Color.white);
				
				lotto[i][j].setHorizontalTextPosition(JLabel.CENTER);
			}
		}
		// pnlBuyNums 끝

		// others (당첨번호, 내 선택번호 제외 부가적인 것들)
		JPanel others = new JPanel();

		JLabel info = new JLabel(image);
		info.setToolTipText("<html><p>"
				+ "당첨금 분배 방식 <br> [1등] 6개 번호 일치 : 총 당첨금 중 4등, 5등 금액을 제외한 금액의 75% <br> [2등] 5개 번호 일치 + 보너스 번호 일치 : 총 당첨금 중 4등, 5등 금액을 제외한 금액의 12.5% <br> [3등] 5개 번호 일치 : 총 당첨금 중 4등, 5등 금액을 제외한 금액의 12.5% <br> [4등] 4개 번호 일치 : 50,000원 <br> [5등] 3개 번호 일치: 5,000원 "
				+ "</p></html>");
		ToolTipManager m = ToolTipManager.sharedInstance(); // 툴팁 여는 시간 조정 위해 객체 생성
		m.setInitialDelay(0); // 초기 툴팁 출력 지연시간 0초 설정
		JLabel price = new JLabel(String.valueOf(totalMoney));
		nextBtn = new JButton("다음 회차");

		others.add(info);
		others.add(price);
		others.add(nextBtn);
		// others 끝

//		underLottoNums.add(pnlWinning);
		underLottoNums.add(printResult);
//		underLottoNums.add(pnlBuyNums);
		underLottoNums.add(others);
		// underLottoNums 끝
		
		BoxLayout underLottoNumsY = new BoxLayout(underLottoNums, BoxLayout.Y_AXIS);
		underLottoNums.setLayout(underLottoNumsY);

		// pnl 전체 담을 패널
		pnl = new JPanel(){
		      public void paintComponent(Graphics g) {
		          g.drawImage(background, 0, 0, null);
		          setOpaque(false);
		       }
		    };
		pnl.add(pnlLottoNums);
		pnl.add(underLottoNums);
		// pnl 끝
		
		
		BoxLayout pnlY = new BoxLayout(pnl, BoxLayout.Y_AXIS);
		pnl.setLayout(pnlY);

		// 레이아웃
		pnlLottoNums.setBounds(0, 0, 784, 138);
//		pnlWinning.setBounds(0, 138, 784, 138);
//		pnlBuyNums.setBounds(0, 276, 784, 138);
//		lblBuyNums.setBounds(0, 0, 65, 40);
		price.setBounds(78, 424, 315, 24);
		
		price.setFont(new Font("굴림", Font.PLAIN, 20));
		// 레이아웃 끝

		// 디자인
		pnl.setBackground(new Color(248, 202, 204));

		pnlLottoNums.setOpaque(false); // 배경 색을 따라가도록
//		pnlWinning.setOpaque(false);
//		pnlBuyNums.setOpaque(false);
		// 디자인 끝
		
		changeColor(lotto);
	}

// J라벨 35개 만드는 메소드
	public void makefield(JLabel[][] lotto) {
		Font cardFont = new Font("Serif", Font.BOLD, 25);
		Toolkit kit = Toolkit.getDefaultToolkit();
		URL cardS = ResultPage.class.getClassLoader().getResource("images/card_spade.png");
		URL cardH = ResultPage.class.getClassLoader().getResource("images/card_heart.png");
		URL cardC = ResultPage.class.getClassLoader().getResource("images/card_clover.png");
		URL cardD = ResultPage.class.getClassLoader().getResource("images/card_diamond.png");
		
		ImageIcon cardSpade = new ImageIcon(kit.getImage(cardS));
		ImageIcon cardHeart = new ImageIcon(kit.getImage(cardH));
		ImageIcon cardClover = new ImageIcon(kit.getImage(cardC));
		ImageIcon cardDiamond = new ImageIcon(kit.getImage(cardD));
		
		for (int i = 0; i < 5; i++) {
			lotto[i][0] = new JLabel(String.valueOf(i + 1));
			for (int j = 1; j < 7; j++) {
				lotto[0][j] = new JLabel(cardSpade); // 0*6
				lotto[0][j].setFont(cardFont);
				lotto[1][j] = new JLabel(cardHeart); // 0*6
				lotto[1][j].setFont(cardFont);
				lotto[2][j] = new JLabel(cardClover); // 0*6
				lotto[2][j].setFont(cardFont);
				lotto[3][j] = new JLabel(cardDiamond); // 0*6
				lotto[3][j].setFont(cardFont);
				lotto[4][j] = new JLabel(cardSpade); // 0*6
				lotto[4][j].setFont(cardFont);
	 		}
			lotto[i][7] = new JLabel(ranking.get(i));
		}

	}

// 카드패널 색 변경 메소드
	public void changeColor(JLabel[][] lotto) {
		Font cardFontnone = new Font("Serif", Font.BOLD, 15);
		for(int i = 0; i < sameList.size(); i++) {
			for(int j = 0; j < sameList.get(i).size(); j++) {
				if(sameList.get(i).get(j).equals("다름")) {
					lotto[i][j + 1].setForeground(new Color (107,106,105));
					lotto[i][j + 1].setFont(cardFontnone);
				} else if(sameList.get(i).get(j).equals("보너스 번호 당첨!")) {
					lotto[i][j + 1].setForeground(new Color (221, 168, 81));
				}
			}
		}
	}

	// 당첨번호 만드는 메소드
	public void getLottoNum() {
		// 로또 당첨 번호(중복x) 출력 Set
		Set<Integer> lotto = new HashSet<>();
		while (lotto.size() < 6) {
			Random random = new Random();
			// 로또 번호 1개 받는 랜덤 번호 생성
			int lottoRandom = random.nextInt(45) + 1;
			if (lottoRandom != 0) {
				lotto.add(lottoRandom);
			}
		}

		// 리스트로 변환 후 정렬
		lottoNum = new ArrayList<>(lotto);
		Collections.sort(lottoNum);
		System.out.println("당첨 번호: " + lottoNum);

		// 보너스 값 출력
		lottoBonus = random.nextInt(45) + 1;
		while (lotto.contains(lottoBonus)) {
			lottoBonus = random.nextInt(45) + 1;
		}
		System.out.println("보너스 값: " + lottoBonus);
		getNumberPractice();
		compareLottoNum(); 
	}

//	// 내가 구매한 로또 5회(5000원) 구하는 메소드
//	public void getBuyLottoNum() {
//		for (int j = 0; j < 5; j++) {
//			buyLottoNum = new ArrayList<>();
//			// 내가 구매한 로또 1회(1000원) 6자리 수 구하는 메소드
//			for (int i = 0; i <= 5; i++) { // 6번 돌리기 위한 숫자
//				buyLottoNum.add(random.nextInt(45) + 1);
//				Collections.sort(buyLottoNum);
//			}
//
//			// 이중배열에 list배열 담기
//			buyLottoNumList.add(buyLottoNum);
//		}
//	}

///////////////////////////// 연습 list 배열에 6개의 값 담기 /////////////////////////
	public void getNumberPractice() {
		for (int j = 0; j < 4; j++) {
			buyLottoNum = new ArrayList<>();
			for (int i = 0; i <= 5; i++) {
				buyLottoNum.add(random.nextInt(45) + 1);
				Collections.sort(buyLottoNum);
			}

//			System.out.println(buyLottoNum1);
			// 이중배열에 list배열 담기
			buyLottoNumList.add(buyLottoNum);
//			System.out.println(buyLottoNum);
		}
		buyLottoNumList.add(lottoNum);
		System.out.println("연습할 이중 배열 출력" + buyLottoNum);
	}
////////////////////////////////// 연습 list 배열에 6개의 값 담기 end/////////////////////////

	// 로또번호와 내가 선택한 번호 비교 메소드
	public void compareLottoNum() {
		// 내가 선택한 번호 출력
		System.out.println("내가 선택한 번호: " + buyLottoNumList);

		// 변수 설정
		Integer buyLottoRandom = 0; // 내가 선택한 숫자 6개 배열 중 1개의 숫자 담을 변수
		Integer lottoRandom = 0; // 로또 당첨 번호 1개의 숫자 담을 변수

		for (int buyListIndex = 0; buyListIndex < buyLottoNumList.size(); buyListIndex++) {
			same = new ArrayList<>();
//			for (int buyNumIndex = 0; buyNumIndex < buyLottoNum.size(); buyNumIndex++) {
			for (int buyNumIndex = 0; buyNumIndex < buyLottoNumList.get(buyListIndex).size(); buyNumIndex++) {
				buyLottoRandom = buyLottoNumList.get(buyListIndex).get(buyNumIndex);
				int count = 0;
				for (int lottoIndex = 0; lottoIndex < lottoNum.size(); lottoIndex++) {
					lottoRandom = lottoNum.get(lottoIndex);
					if (buyLottoRandom.equals(lottoRandom)) {
//							System.out.println("같음");
						same.add(buyNumIndex, "같음");
						break;
					} else {
						count++;
					}
					if (count == 6) {
//							System.out.println("다름");
						same.add(buyNumIndex, "다름");
						break;
					}
				}
			}
			sameList.add(buyListIndex, same);
		}
		comparingBonus();
		System.out.println("당첨 번호 추첨: " + sameList.toString());
		rank();
	}

	// 보너스 번호 비교 메소드
	public void comparingBonus() {
		for (int buyListIndex = 0; buyListIndex < buyLottoNumList.size(); buyListIndex++) {
			if (buyLottoNumList.get(buyListIndex).contains(lottoBonus)) {
				int sameIndex = buyLottoNumList.get(buyListIndex).indexOf(lottoBonus);
				sameList.get(buyListIndex).set(sameIndex, "보너스 번호 당첨!");
			}
		}
	}

	// 등수 추첨 메소드
	public void rank() {
		int countD = 0; // 다름 개수 체크
		int countB = 0; // 보너스 번호 당첨 여부
		// ranking 배열 길이 설정(필드에서 설정시 buyLottoNumList에 값 x라서 0으로 나옴)
		ranking = new ArrayList<>();
//////////////////////////////////////배열일 때 비교 메소드 Start////////////////////////////////////////////
//		for (int sameArrayIndex = 0; sameArrayIndex < sameList.size(); sameArrayIndex++) {
//			for (int sameIndex = 0; sameIndex < same.size(); sameIndex++) {
//				if (sameList[sameArrayIndex][sameIndex].equals("다름")) {
//					countD++;
//				} else if (sameList[sameArrayIndex][sameIndex].equals("보너스 번호 당첨!")) {
//					countB++;
//				}
//			}
//		}
//////////////////////////////////////배열일 때 비교 메소드 end////////////////////////////////////////////

		for (int rankingIndex = 0; rankingIndex < sameList.size(); rankingIndex++) {
			countD = Collections.frequency(sameList.get(rankingIndex), "다름");
			countB = Collections.frequency(sameList.get(rankingIndex), "보너스 번호 당첨!");

			switch (countD) {
			case 0:
				if (countB == 0) { // 같음이 6개라면
					ranking.add("1등");
				} else {
					ranking.add("2등");
				}
				break;
			case 1:
				ranking.add("3등");
				break;
			case 2:
				ranking.add("4등");
				break;
			case 3:
				ranking.add("5등");
				break;
			default:
				ranking.add("낙첨");
				break;
			}
		}
		countD = 0;
		countB = 0;
		System.out.println("당첨 여부: " + ranking.toString());
		getMoney();
	}

	// 금액 출력 메소드

	// 2. 4, 5등 복권 당첨 금액 저장 - 1등 수령 금액에서 마이너스 - 2등 수령금액에서 1등 금액 마이너스 - 3등 수령금액에서 2등
	// 금액 마이너스
	// 3. 실 수령 금액(세금 제외)
	public void getMoney() {
		int winningMoney;
		// 1. totalMoney 당첨분 금액으로 설정하기
		totalMoney = (totalMoney + (1000 * buyLottoNumList.size())) / 2;
		System.out.println("당첨분 총 금액: " + totalMoney);

		// 5등 당첨 횟수 -> 1, 2, 3등 계산에서 사용
		int fifthCount = Collections.frequency(ranking, "5등");
		// 4등 당첨 횟수
		int fourthCount = Collections.frequency(ranking, "4등");

		// 4등 , 5등 당첨 시 당첨 금액 메소드
		winningMoney = fifthCount * 5000 + fourthCount * 50000;
		totalMoney -= winningMoney;
		winningTotal += winningMoney;
		// 1등 당첨 시 당첨 금액 메소드
		winningMoney = totalMoney / 75 * Collections.frequency(ranking, "1등");
		totalMoney -= winningMoney;
		winningTotal += winningMoney;
		// 2등 당첨 시 당첨 금액 메소드
		winningMoney = (int) (Double.valueOf(totalMoney) / 12.5 * Collections.frequency(ranking, "2등"));
		totalMoney -= winningMoney;
		winningTotal += winningMoney;
		// 3등 당첨 시 당첨 금액 메소드
		winningMoney = (int) (Double.valueOf(totalMoney) / 12.5 * Collections.frequency(ranking, "3등"));
		totalMoney -= winningMoney;
		winningTotal += winningMoney;
		System.out.println("총 당첨 금액: " + winningTotal);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ResultPage page = new ResultPage();
		page.setPanel();
		JPanel pnl = page.getPnl();
		frame.add(pnl);
		frame.setSize(900, 600);
		frame.setVisible(true);
	}
}