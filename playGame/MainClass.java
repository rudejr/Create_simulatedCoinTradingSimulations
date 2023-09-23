package playGame;

import framework.WindowSetting;
import framework.GraphSetting;
import framework.GraphVolumeSetting;
import settingData.HoseoCoinData;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.io.IOException;
import java.net.*;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;



public class MainClass extends WindowSetting {
	
	// 로그인한 사용자의 이름을 출력하는 전역변수
	public JLabel showUserName = new JLabel();
	
	// 대출 금액을 갱신하기 위해 전역변수로 변경
	public JLabel showLoanAmount = new JLabel();;
	public String loanAmountString; 			// 대출금
	private double loan = 100000; 				// 대출 크기 및 자산 초기 값
	public double count = 1;					// 대출 횟수 카운트
	
	// 매도를 위한 변수를 전역변수로 선언
	public static double sellNum = 0;			// 매도량
	
	// 현재가를 동적 변경하기 위헤 전역변수로 변경
	public static JLabel showAmount = new JLabel();
	public static String currentPrice;			// 시가
	
	// 거래량을 동적 변경하기 위해 전역변수로 변경
	public static JLabel showVolume = new JLabel();
	public static String currentVolume;			// 거래량
	
	// 구매가를 동적 변경하기 위해 전역변수로 변경
	public static JLabel showPurchase = new JLabel();
	public static double purchasePrice = 0;		// 구매가
	
	// 구매 수량을 동적으로 변경하기 위해 전역변수로 변경
	public static JLabel showQuantity = new JLabel();
	public static double purchaseNum = 0; 		// 매수량
	
	// 총 자산을 동적 변경하기 위해 전역변수로 변경
	public static JLabel showProperty = new JLabel();
	public static String propertyString;		// 총 자산
	public double initalAsset = loan / 2;		// 초기 값 및 계산을 위한 double형 선언
	
	// 출금 가능 금액을 동적 변경하기 위해 전역변수로 변경
	public static JLabel showWithdrawable = new JLabel();
	public static String withdrawalString;		// 출금 가능 금액
	public double initalWithdrawal = loan / 2;	// 초기 값 및 계산을 위한 double형 선언
	
	// 손익을 동적으로 변경하기 위해 전역변수로 변경
	public static JLabel showProfitAndLosst = new JLabel();
	public static String profitLosst = "0"; 	// 손익 * 매수량
	public static double profit = 0; 			// 손익
	
	// 수익률을 동적으로 변경하기 위해 전역변수로 변경
	public static JLabel showRateOf_return = new JLabel();
	public static double profitRate = 0; 		// 수익률
	
	// 사용할 각 패널 선언
	private JPanel bgPanel;
	private JPanel impPanel;
	private JPanel newsPanel;
	private JPanel chartPanel;
	private JPanel transactPanel;
	
	// 생성자를 통해 세팅된 각 패널로 초기화 
	public MainClass () {
		background_panel();		// mainClass의 패널을 관리하는 함수
		impormation_panel();	// 상단 정보를 관리하는 함수
		graph_panel();			// 그래프의 영역을 표시하는 패널을 관리하는 함수
		news_panel();			// 사용자의 동적 정보를 표시하는 패널을 관리하는 표시하는 함수
		chartVolume_panel();	// 거래량그래프의 영역을 표시하는 패널을 관리하는 함수
		transact_panel();		// 매수매도 영역을 표시하는 패널을 관리하는 함수
		gameDescription_panel();// 게임 설명을 관리하는 패널
	}
	
	private void background_panel() {
		bgPanel = new JPanel();
		bgPanel.setBounds(0, 0, 1166, 723);
		mFrame.getContentPane().add(bgPanel);
		bgPanel.setBackground(Color.black);
		bgPanel.setLayout(null);
	}
	
	private void impormation_panel() {
		impPanel = new JPanel();
		impPanel.setBackground(new Color(31, 31, 31));
		impPanel.setBounds(12, 10, 969, 73);
		bgPanel.add(impPanel);
		impPanel.setLayout(null);
		
		// '호서코인' 출력
		JLabel itemName = new JLabel();
		itemName.setForeground(new Color(255, 255, 255));
		itemName.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 37));
		itemName.setHorizontalAlignment(SwingConstants.CENTER);
		itemName.setText("호서코인");
		itemName.setBounds(12, 10, 168, 53);
		impPanel.add(itemName);
		
		// '현재가($)' 출력
		JLabel currentAmountText = new JLabel();
		currentAmountText.setText("현재가($)");
		currentAmountText.setHorizontalAlignment(SwingConstants.CENTER);
		currentAmountText.setForeground(Color.WHITE);
		currentAmountText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		currentAmountText.setBounds(230, 10, 142, 30);
		impPanel.add(currentAmountText);
		
		// 현재가를 동적으로 표시하는 라벨
		showAmount.setHorizontalAlignment(SwingConstants.CENTER);
		showAmount.setForeground(new Color(255, 255, 255));
		showAmount.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		showAmount.setBounds(230, 33, 142, 40);
		impPanel.add(showAmount);
		
		// '구매가' 출력
		JLabel purchaseAmountText = new JLabel();
		purchaseAmountText.setText("구매가");
		purchaseAmountText.setHorizontalAlignment(SwingConstants.CENTER);
		purchaseAmountText.setForeground(Color.WHITE);
		purchaseAmountText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		purchaseAmountText.setBounds(580, 10, 142, 30);
		impPanel.add(purchaseAmountText);
		
		// 구메가를 동적으로 표시하는 라벨
		showPurchase.setHorizontalAlignment(SwingConstants.CENTER);
		showPurchase.setForeground(new Color(255, 255, 255));
		showPurchase.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		showPurchase.setBounds(580, 33, 142, 40);
		impPanel.add(showPurchase);
		
		// "구매 수량" 출력
		JLabel quantityText = new JLabel();
		quantityText.setBounds(760, 10, 136, 30);
		quantityText.setText("구매 수량");
		quantityText.setHorizontalAlignment(SwingConstants.CENTER);
		quantityText.setForeground(Color.WHITE);
		quantityText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		impPanel.add(quantityText);
		
		// 갱신되는 구매 수량을 출력
		showQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		showQuantity.setForeground(Color.WHITE);
		showQuantity.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 28));
		showQuantity.setBounds(760, 33, 136, 40);
		impPanel.add(showQuantity);
		
		// '거래량(K)' 출력
		JLabel volumeText = new JLabel();
		volumeText.setText("거래량(K)");
		volumeText.setHorizontalAlignment(SwingConstants.CENTER);
		volumeText.setForeground(Color.WHITE);
		volumeText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		volumeText.setBounds(410, 10, 142, 30);
		impPanel.add(volumeText);
		
		// 갱신되는 거래량을 출력
		showVolume.setHorizontalAlignment(SwingConstants.CENTER);
		showVolume.setForeground(Color.WHITE);
		showVolume.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		showVolume.setBounds(410, 33, 142, 40);
		impPanel.add(showVolume);
		
		// 변동 값을 저장하고 나가는 버튼
		JButton saveExit = new JButton("저장하고 나가기");
		saveExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(purchaseNum != 0) {
					JOptionPane.showMessageDialog(chartPanel, "매도를 완료해야 나갈 수 있습니다.");
				} else {
					JOptionPane.showMessageDialog(chartPanel, "서버와 연결이 안 되었습니다.");
					
					mFrame.setVisible(false); // 본인 화면 감추고
					tFrame.setVisible(false); // 매수매도 화면 감추고
					
					loanAmountString = "0"; // 대출금 초기화
					
					// 로그인 화면 불러오기
					LogInWindow logInWd = new LogInWindow();
					logInWd.lFrame.setVisible(true);
					logInWd.lFrame.setAlwaysOnTop(true);
				}
			}
		});
		saveExit.setBackground(new Color(255, 255, 255));
		saveExit.setForeground(new Color(0, 0, 0));
		saveExit.setFont(new Font("휴먼편지체", Font.BOLD, 19));
		saveExit.setBounds(993, 10, 161, 42);
		bgPanel.add(saveExit);
		
		// 코인 충전(대출) 버튼
		JButton fillCoin = new JButton("대출하기");
		fillCoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 누르면 학교 홍보 영상이 떠서 조회수 올라가게 하고, 코인은 누르는 동시에 충전
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						// 대출 알고리즘
		            	if(count < 3) {
		            		JOptionPane.showMessageDialog(chartPanel, "빚쟁이가 되었습니다.");
		            		
		            		// 호서대학교 홍보 영상 조회수 올리기
							URI schoolPromotion = new URI("https://www.youtube.com/watch?v=ksubOQAYqNs");
			               	desktop.browse(schoolPromotion);
		            		
		            		// 대출금액 += 100,000
			               	double loanAmount = loan * count; // 갱신
			               	loanAmountString = String.format("%.1f", loanAmount);
			            	showLoanAmount.setText(loanAmountString);
			            	
			            	// 총 자산 동적 변환(누적)
			            	initalAsset = Double.parseDouble(propertyString); // 누적을 위한 형변환
			            	propertyString = String.format("%.1f", initalAsset + loan); // 계산 및 형변환
			            	showProperty.setText(propertyString); // 출력
			            	
			            	// 출금 가능 금액 동적 변환(누적)
			            	initalWithdrawal = Double.parseDouble(withdrawalString); // 누적을 위한 형변환
			            	withdrawalString = String.format("%.1f", initalWithdrawal + loan); // 계산 및 형변환
			            	showWithdrawable.setText(withdrawalString); // 출력
		            	} else JOptionPane.showMessageDialog(chartPanel, "더 이상 대출이 되지 않습니다.");
		            	count++;
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (URISyntaxException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
		fillCoin.setForeground(Color.BLACK);
		fillCoin.setFont(new Font("휴먼편지체", Font.BOLD, 19));
		fillCoin.setBackground(new Color(255, 255, 255));
		fillCoin.setBounds(993, 56, 161, 27);
		bgPanel.add(fillCoin);
	}
	
	// 배경 패널 속 그래프를 직접적으로 띄우는 패널
	private void graph_panel() {
		JPanel graphPanel = new JPanel();
		graphPanel.setForeground(new Color(255, 255, 255));
		graphPanel.setBounds(12, 93, 761, 371);
		bgPanel.add(graphPanel);
		graphPanel.setLayout(null);
	}
	
	// 배경 패널 속 뉴스(사용자 현황 을/)를 띄우는 패널
	private void news_panel() {
		newsPanel = new JPanel();
		newsPanel.setBackground(new Color(31, 31, 31));
		newsPanel.setBounds(12, 474, 761, 239);
		bgPanel.add(newsPanel);
		newsPanel.setLayout(null);
		
		// 찾은 사용자 불러오기
		showUserName.setHorizontalAlignment(SwingConstants.CENTER);
		showUserName.setForeground(Color.WHITE);
		showUserName.setFont(new Font("한컴 말랑말랑 Regular", Font.BOLD, 28));
		showUserName.setBackground(Color.WHITE);
		showUserName.setBounds(12, 18, 120, 53);
		newsPanel.add(showUserName);
		
		// 패널 타이틀
		JLabel newsTitle = new JLabel();
		newsTitle.setText("님 자산현황");
		newsTitle.setHorizontalAlignment(SwingConstants.LEFT);
		newsTitle.setForeground(Color.WHITE);
		newsTitle.setFont(new Font("한컴 말랑말랑 Regular", Font.PLAIN, 28));
		newsTitle.setBackground(Color.BLACK);
		newsTitle.setBounds(114, 18, 142, 53);
		newsPanel.add(newsTitle);
		
		// '총 자산 : ' 출력
		JLabel PropertyText = new JLabel();
		PropertyText.setBackground(new Color(255, 255, 255));
		PropertyText.setBounds(12, 81, 130, 53);
		PropertyText.setText("총 자산");
		PropertyText.setHorizontalAlignment(SwingConstants.CENTER);
		PropertyText.setForeground(new Color(255, 255, 255));
		PropertyText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		newsPanel.add(PropertyText);
		
		// 실시간으로 바뀌는 총 자산을 표시하는 라벨
		showProperty.setBackground(new Color(0, 0, 0));
		showProperty.setBounds(12, 117, 130, 53);
		propertyString = String.format("%.1f", initalAsset); // 형변환
		showProperty.setText(propertyString); // 동적으로 자산 출력하기
		showProperty.setHorizontalAlignment(SwingConstants.CENTER);
		showProperty.setForeground(Color.WHITE);
		showProperty.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		newsPanel.add(showProperty);
		
		// "출금 가능 금액 : " 출력
		JLabel withdrawableText = new JLabel();
		withdrawableText.setText("출금 가능 금액");
		withdrawableText.setHorizontalAlignment(SwingConstants.CENTER);
		withdrawableText.setForeground(Color.WHITE);
		withdrawableText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		withdrawableText.setBackground(Color.WHITE);
		withdrawableText.setBounds(154, 81, 168, 53);
		newsPanel.add(withdrawableText);
		
		// 갱신되는 출금 가능 금액을 출력
		showWithdrawable.setBackground(Color.BLACK);
		showWithdrawable.setBounds(154, 117, 168, 53);
		withdrawalString = String.format("%.1f", initalWithdrawal); // 형변환
		showWithdrawable.setText(withdrawalString);
		showWithdrawable.setHorizontalAlignment(SwingConstants.CENTER);
		showWithdrawable.setForeground(Color.WHITE);
		showWithdrawable.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		newsPanel.add(showWithdrawable);
		
		// '손익 : ' 출력
		JLabel profitAndLosstText = new JLabel();
		profitAndLosstText.setBounds(462, 81, 156, 53);
		newsPanel.add(profitAndLosstText);
		profitAndLosstText.setText("손익");
		profitAndLosstText.setHorizontalAlignment(SwingConstants.CENTER);
		profitAndLosstText.setForeground(Color.WHITE);
		profitAndLosstText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		
		// 실시간으로 바뀌는 손익을 표시하는 라벨
		showProfitAndLosst.setBounds(462, 117, 156, 53);
		showProfitAndLosst.setHorizontalAlignment(SwingConstants.CENTER);
		showProfitAndLosst.setForeground(Color.WHITE);
		showProfitAndLosst.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		newsPanel.add(showProfitAndLosst);
		
		// '수익률(%) : ' 출력
		JLabel rateOf_returnText = new JLabel();
		rateOf_returnText.setText("수익률(%)");
		rateOf_returnText.setHorizontalAlignment(SwingConstants.CENTER);
		rateOf_returnText.setForeground(Color.WHITE);
		rateOf_returnText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		rateOf_returnText.setBounds(609, 81, 120, 53);
		newsPanel.add(rateOf_returnText);
		
		// 실시간으로 바뀌는 수익률을 표시하는 라벨
		showRateOf_return.setHorizontalAlignment(SwingConstants.CENTER);
		showRateOf_return.setForeground(Color.WHITE);
		showRateOf_return.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		showRateOf_return.setBounds(609, 117, 120, 53);
		newsPanel.add(showRateOf_return);
		
		// '대출금액 : ' 출력
		JLabel loanAmountText = new JLabel();
		loanAmountText.setText("누적 대출 금액");
		loanAmountText.setHorizontalAlignment(SwingConstants.CENTER);
		loanAmountText.setForeground(Color.WHITE);
		loanAmountText.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 18));
		loanAmountText.setBackground(Color.WHITE);
		loanAmountText.setBounds(316, 81, 156, 53);
		newsPanel.add(loanAmountText);
		
		// 갱신되는 대출 금액을 출력
		showLoanAmount.setHorizontalAlignment(SwingConstants.CENTER);
		showLoanAmount.setForeground(Color.WHITE);
		showLoanAmount.setFont(new Font("맑은 고딕", Font.PLAIN, 26));
		showLoanAmount.setBackground(Color.BLACK);
		showLoanAmount.setBounds(316, 117, 156, 53);
		newsPanel.add(showLoanAmount);
	}
	
	// 배경 패널 속 차트를 띄우는 패널
	private void chartVolume_panel() {
		chartPanel = new JPanel();
		chartPanel.setBounds(785, 193, 369, 271);
		bgPanel.add(chartPanel);
		chartPanel.setLayout(null);
	}
	
	// tFrame 영역표시
	private void transact_panel() {
		transactPanel = new JPanel();
		transactPanel.setBounds(785, 474, 369, 239);
		transactPanel.setBackground(new Color(255, 255, 255));
		bgPanel.add(transactPanel);
		transactPanel.setLayout(null);
	}
	
	// 게임 설명을 관리하는 패널
	public void gameDescription_panel() {
		JPanel descriptionPanel = new JPanel();
		descriptionPanel.setBackground(new Color(31, 31, 31));
		descriptionPanel.setBounds(785, 93, 369, 90);
		bgPanel.add(descriptionPanel);
		descriptionPanel.setLayout(null);
		
		JTextField gameDescriptText = new JTextField();
		gameDescriptText.setText("※ 게임설명 ※");
		gameDescriptText.setForeground(Color.WHITE);
		gameDescriptText.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		gameDescriptText.setColumns(10);
		gameDescriptText.setBackground(new Color(31, 31, 31));
		gameDescriptText.setBounds(5, 2, 123, 31);
		gameDescriptText.setBorder(null);
		descriptionPanel.add(gameDescriptText);
		
		
		JTextField description_1 = new JTextField();
		description_1.setText("모의 코인 거래 시뮬레이션을 통해");
		description_1.setForeground(Color.WHITE);
		description_1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		description_1.setColumns(10);
		description_1.setBackground(new Color(31, 31, 31));
		description_1.setBounds(5, 36, 227, 31);
		description_1.setBorder(null);
		descriptionPanel.add(description_1);
		
		JTextField description_2 = new JTextField();
		description_2.setText("영앤리치에 도전하세요!");
		description_2.setForeground(Color.WHITE);
		description_2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		description_2.setColumns(10);
		description_2.setBackground(new Color(31, 31, 31));
		description_2.setBounds(5, 59, 227, 31);
		description_2.setBorder(null);
		descriptionPanel.add(description_2);
	}
	
	// 매수 확인 시 매수 개수를 받는 함수
	public void buying(String buyNum) {
		purchasePrice = Double.parseDouble(currentPrice); // 구매가 저장, 형변환
		purchaseNum = Double.parseDouble(buyNum); // 구매 개수 저장, 형변환
		
		purchaseAmount(buyNum); // 구매가와 구매 수량 동적 변경을 위해 함수 호출
		withdrawableCalculation(purchasePrice, purchaseNum);
	}
	
	// 매도 확인 시 매도를 관리하는 함수
	public void selling(String num) {
		sellNum = Double.parseDouble(num);
		
		// 출금 가능 금액에 더하기 
		initalWithdrawal = Double.parseDouble(withdrawalString); // 현재 출금 가능 금액을 double으로
		initalWithdrawal = initalWithdrawal + ((purchasePrice * sellNum) + Double.parseDouble(profitLosst)); // 출금 가능 금액 = 출금가능 금액 + (구매가 * 개수) 손익
		withdrawalString = String.format("%.1f", initalWithdrawal);
		showWithdrawable.setText(withdrawalString);
		
		// 구매 수량 줄이기
		purchaseNum -= sellNum;
		showQuantity.setText(String.format("%.1f", purchaseNum));
		
		if(purchaseNum == 0) {
			purchasePrice = purchaseNum;
			showPurchase.setText(String.format("%.1f", purchasePrice));
		}
	}
	
	// 수익을 계산하는 함수
	public static double profitCalculation(double price, double num) {
		
		// 수익 계산 : ( 평가금액 - 매수가 )
		profit = (Double.parseDouble(currentPrice) - price);
		
		// 수익률 계산 : ( 수익 / 구매가 ) * 100 // (num/num == 매수 전 infinite 현상 방지를 위해)
		profitRate = (profit / purchasePrice) * 100 * (num/num);
		showRateOf_return.setText(String.format("%.1f", profitRate));
		
		return (profit * num); // 수익 * 구매 수량 return
	}
	
	// 출금 가능 금액을 계산하는 함수
	public static void withdrawableCalculation(double price, double num) {

		double temp = Double.parseDouble(withdrawalString);
		withdrawalString = String.format("%.1f", (temp -= (price * num)));
		showWithdrawable.setText(withdrawalString);
		
	}
	
	// 총 자산을 계산하는 함수
	public static String currentPriceCalculation() {
		double wD = Double.parseDouble(withdrawalString); // 출금 가능 금액
		double pL = Double.parseDouble(profitLosst); // 손익
		
		// 총 자산 = 출금 가능 금액 + (매수가 * 매수량) + 손익 
		double property = wD + (purchasePrice * purchaseNum) + pL;
		
		propertyString = String.format("%.1f", property); // 총 자산
		return propertyString;
	}
	
	// 구매가와 구매 수량 동적 변경
	public void purchaseAmount(String num) {
		showPurchase.setText(currentPrice);
		showQuantity.setText(num);
	}
	
	// 숫자 데이터의 색깔을 관리하는 함수
	public static void dynamicColor(double front, double rear, double frontV, double rearV) {
		
		// 현재가의 색깔이 바뀌는 조건
		if(front < rear) {
			showAmount.setForeground(new Color(255, 0, 0)); // 빨간색
		} else if(front > rear) {
			showAmount.setForeground(new Color(75, 137, 220)); // 파란색
		} else {
			showAmount.setForeground(Color.WHITE);
		}
		
		// 거래량의 색깔이 바뀌는 조건
		if(frontV < rearV) {
			showVolume.setForeground(new Color(255, 0, 0)); // 빨간색
		} else if(frontV > rearV) {
			showVolume.setForeground(new Color(75, 137, 220)); // 파란색
		} else {
			showVolume.setForeground(Color.WHITE);
		}
		
		// 손익과 자산의 색깔이 바뀌는 조건
		if(0 < Double.parseDouble(profitLosst)) {
			showProfitAndLosst.setForeground(new Color(255, 0, 0));
			showProperty.setForeground(new Color(255, 0, 0));
		} else if(0 > Double.parseDouble(profitLosst)) {
			showProfitAndLosst.setForeground(new Color(75, 137, 220));
			showProperty.setForeground(new Color(75, 137, 220));
		} else {
			showProfitAndLosst.setForeground(Color.WHITE);
		}
		
		// 수익률의 색깔이 바뀌는 조건
		if(0 < profitRate) {
			showRateOf_return.setForeground(new Color(255, 0, 0));
		} else if(0 > profitRate) {
			showRateOf_return.setForeground(new Color(75, 137, 220));
		} else {
			showRateOf_return.setForeground(Color.WHITE);
		}
	}
	// 메인 함수
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// LonIn 창 보이기
					LogInWindow logInWd = new LogInWindow();
					logInWd.lFrame.setVisible(true);
						logInWd.lFrame.setAlwaysOnTop(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		GraphSetting chart = new GraphSetting();
		GraphVolumeSetting chartV = new GraphVolumeSetting();
		HoseoCoinData coin = new HoseoCoinData();
		TransactWindow transactWd = new TransactWindow();
		Random random = new Random();
		
		// 종목과 시가를 랜덤으로 선택하는 알고리즘
		int choiceGraph = random.nextInt(4) + 1;
		int choiceStrat = random.nextInt(10) * 5;
		switch(choiceGraph) {
			case 1:
				for (int i = choiceStrat; i < coin.data1.length; i++) {
			   	    double x = i;
			   	    double y = coin.data1[i];
			   	    double xV = i;
			   	    double yV = coin.data1_volume[i];
			   	    
			   	    // 현재가 출력
			   	    currentPrice = String.format("%.1f", y); // 변수 형변환
			   	    showAmount.setText(currentPrice); // 현재가 Text 출력x
			   	    
			   	    // 거래량 출력
			   	    currentVolume = String.format("%.1f", yV);
			   	    showVolume.setText(currentVolume);
			   	    
			   	    // 손익 출력
			   	    profitLosst = String.format("%.1f", profitCalculation(purchasePrice, purchaseNum));
			   	    showProfitAndLosst.setText(profitLosst);
			   	   
			   	    // 총 자산 출력
			   	    showProperty.setText(currentPriceCalculation());
			   	    
			   	    // 그래프 그리기
			   	    chart.addValue(x, y);
			   	    chartV.addVolume(xV, yV);
			   	    
			   	    // 숫자 데이터의 색깔을 관리하는 함수 호출
			   	    if(i > 0) dynamicColor(coin.data1[i-1], coin.data1[i], coin.data1_volume[i-1], coin.data1_volume[i]);
			   	    
			   	    try {
			   			Thread.sleep(3000); // 1000 == 1초간 일시 중지
			   	    } catch (InterruptedException e) {
			   	        e.printStackTrace();
			   	    }
			   	 chart.ghFrame.setVisible(true);
			   	 chartV.ghVFrame.setVisible(true);
			   	 transactWd.tFrame.setVisible(true);
			   	} JOptionPane.showMessageDialog(transactWd.tFrame, "시장이 닫혔습니다.");
			   	break;
			case 2:
				for (int i = choiceStrat; i < coin.data2.length; i++) {
					double x = i;
			   	    double y = coin.data2[i];
			   	    double xV = i;
			   	    double yV = coin.data2_volume[i];
			   	    
			   	    // 현재가 출력
			   	    currentPrice = String.format("%.1f", y); // 변수 형변환
			   	    showAmount.setText(currentPrice); // 현재가 Text 출력x
			   	    
			   	    // 거래량 출력
			   	    currentVolume = String.format("%.1f", yV);
			   	    showVolume.setText(currentVolume);
			   	    
			   	    // 손익 출력
			   	    profitLosst = String.format("%.1f", profitCalculation(purchasePrice, purchaseNum));
			   	    showProfitAndLosst.setText(profitLosst);
			   	   
			   	    // 총 자산 출력
			   	    showProperty.setText(currentPriceCalculation());
			   	    
			   	    // 그래프 그리기
			   	    chart.addValue(x, y);
			   	    chartV.addVolume(xV, yV);
			   	    
			   	    // 숫자 데이터의 색깔을 관리하는 함수 호출
			   	    if(i > 0) dynamicColor(coin.data2[i-1], coin.data2[i], coin.data2_volume[i-1], coin.data2_volume[i]);
			   	    
			   	    try {
			   			Thread.sleep(3000); // 1000 == 1초간 일시 중지
			   	    } catch (InterruptedException e) {
			   	        e.printStackTrace();
			   	    }
			   	 chart.ghFrame.setVisible(true);
			   	 chartV.ghVFrame.setVisible(true);
			   	 transactWd.tFrame.setVisible(true);
			   	} JOptionPane.showMessageDialog(transactWd.tFrame, "시장이 닫혔습니다.");
			   	break;
			case 3:
				for (int i = choiceStrat; i < coin.data3.length; i++) {
					double x = i;
			   	    double y = coin.data3[i];
			   	    double xV = i;
			   	    double yV = coin.data3_volume[i];
			   	    
			   	    // 현재가 출력
			   	    currentPrice = String.format("%.1f", y); // 변수 형변환
			   	    showAmount.setText(currentPrice); // 현재가 Text 출력x
			   	    
			   	    // 거래량 출력
			   	    currentVolume = String.format("%.1f", yV);
			   	    showVolume.setText(currentVolume);
			   	    
			   	    // 손익 출력
			   	    profitLosst = String.format("%.1f", profitCalculation(purchasePrice, purchaseNum));
			   	    showProfitAndLosst.setText(profitLosst);
			   	   
			   	    // 총 자산 출력
			   	    showProperty.setText(currentPriceCalculation());
			   	    
			   	    // 그래프 그리기
			   	    chart.addValue(x, y);
			   	    chartV.addVolume(xV, yV);
			   	    
			   	    // 숫자 데이터의 색깔을 관리하는 함수 호출
			   	    if(i > 0) dynamicColor(coin.data3[i-1], coin.data3[i], coin.data3_volume[i-1], coin.data3_volume[i]);
			   	    
			   	    try {
			   			Thread.sleep(3000); // 1000 == 1초간 일시 중지
			   	    } catch (InterruptedException e) {
			   	        e.printStackTrace();
			   	    }
			   	 chart.ghFrame.setVisible(true);
			   	 chartV.ghVFrame.setVisible(true);
			   	 transactWd.tFrame.setVisible(true);
			   	} JOptionPane.showMessageDialog(transactWd.tFrame, "시장이 닫혔습니다.");
			   	break;
			case 4:
				for (int i = choiceStrat; i < coin.data4.length; i++) {
					double x = i;
			   	    double y = coin.data4[i];
			   	    double xV = i;
			   	    double yV = coin.data4_volume[i];
			   	    
			   	    // 현재가 출력
			   	    currentPrice = String.format("%.1f", y); // 변수 형변환
			   	    showAmount.setText(currentPrice); // 현재가 Text 출력x
			   	    
			   	    
			   	    // 거래량 출력
			   	    currentVolume = String.format("%.1f", yV);
			   	    showVolume.setText(currentVolume);
			   	    
			   	    // 손익 출력
			   	    profitLosst = String.format("%.1f", profitCalculation(purchasePrice, purchaseNum));
			   	    showProfitAndLosst.setText(profitLosst);
				   	   
			   	    // 총 자산 출력
			   	    showProperty.setText(currentPriceCalculation());
			   	    
			   	    // 그래프 그리기
			   	    chart.addValue(x, y);
			   	    chartV.addVolume(xV, yV);
			   	    
			   	    // 숫자 데이터의 색깔을 관리하는 함수 호출
			   	    if(i > 0) dynamicColor(coin.data4[i-1], coin.data4[i], coin.data4_volume[i-1], coin.data4_volume[i]);
			   	    
			   	    try {
			   			Thread.sleep(3000); // 1000 == 1초간 일시 중지
			   	    } catch (InterruptedException e) {
			   	        e.printStackTrace();
			   	    }
			   	 chart.ghFrame.setVisible(true);
			   	 chartV.ghVFrame.setVisible(true);
			   	 transactWd.tFrame.setVisible(true);
			   	} JOptionPane.showMessageDialog(transactWd.tFrame, "시장이 닫혔습니다.");
			   	break;
		}
	}
}