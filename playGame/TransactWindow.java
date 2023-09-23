package playGame;


import framework.WindowSetting;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class TransactWindow extends WindowSetting {
	
	// 매수 횟수 제한을 위해 전역변수로 선언
	public int buyCheck = 0;
	
	// 클래스 내 패널 간 상호작용을 위해 전역변수로 선언
	private JPanel actPanel;
	private JPanel buyPanel;
	private JPanel sellPanel;
	
	// 메인클래스 객체 생성
	MainClass mainC = new MainClass();
	
	public TransactWindow () {	
		activity_panel();	// 매수매도 기본틀 함수
		actBuy(); 			// 매수 활동을 관리하는 함수
		actSell(); 			// 매도 활동을 관리하는 함수
	}
	
	
	private void activity_panel() {
		actPanel = new JPanel();
		actPanel.setBounds(0, 0, 380, 210);
		actPanel.setBackground(new Color(0, 0, 0));
		tFrame.getContentPane().add(actPanel);
		actPanel.setLayout(null);
	}
	
	public void actBuy() {
		buyPanel = new JPanel();
		buyPanel.setBackground(new Color(128, 255, 128));
		buyPanel.setBounds(12, 66, 345, 138);
		actPanel.add(buyPanel);
		buyPanel.setLayout(null);
		buyPanel.setVisible(false);
	
		// '매수개수 : ' 출력
		JLabel coinInputGuid = new JLabel("매수개수 : ");
		coinInputGuid.setHorizontalAlignment(SwingConstants.CENTER);
		coinInputGuid.setBackground(new Color(255, 255, 255));
		coinInputGuid.setFont(new Font("HY엽서L", Font.BOLD, 18));
		coinInputGuid.setBounds(38, 23, 101, 42);
		buyPanel.add(coinInputGuid);
	
		// 매수 값 입력 받기
		JTextField inputNum = new JTextField();
		inputNum.setHorizontalAlignment(SwingConstants.RIGHT);
		inputNum.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
		inputNum.setBounds(138, 29, 133, 31);
		buyPanel.add(inputNum);
	
		// '개' 출력
		JLabel unitGuid = new JLabel("개");
		unitGuid.setHorizontalAlignment(SwingConstants.LEFT);
		unitGuid.setFont(new Font("HY엽서L", Font.BOLD, 18));
		unitGuid.setBackground(Color.WHITE);
		unitGuid.setBounds(274, 23, 50, 42);
		buyPanel.add(unitGuid);
	
		// 매수 버튼 생성 및 설정
		JButton buyButton = new JButton("매수");
		buyButton.setForeground(new Color(0, 128, 64));
		buyButton.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		buyButton.setBackground(new Color(0, 255, 64));
		buyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				sellPanel.setVisible(false); // 매도 패널 가리기
				buyPanel.setVisible(true); // 매수 패널 보이기
			}
		});
		buyButton.setBounds(12, 10, 170, 48);
		actPanel.add(buyButton);
	
		// 최종 확인 버튼
		JButton dicision = new JButton("확인");
		dicision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 매수 알고리즘
				if(buyCheck != 0) JOptionPane.showMessageDialog(buyPanel, "매도를 먼저 해주기 바랍니다.");
				else {
					if(0 < Double.parseDouble(inputNum.getText())){
						if(Double.parseDouble(MainClass.withdrawalString) <
								Double.parseDouble(MainClass.currentPrice)*Double.parseDouble(inputNum.getText())) {
							JOptionPane.showMessageDialog(buyPanel, "잔액이 부족 합니다.");
						} else {
							
							mainC.buying(inputNum.getText()); // 입력 값 넘기기
							
							// 안내 메시지 및 buyPanel 가리기
							JOptionPane.showMessageDialog(buyPanel, "매수 하였습니다.");
							buyPanel.setVisible(false);
							
							buyCheck = 1;
						}
					} else JOptionPane.showMessageDialog(buyPanel, "1개 이상 매수 바랍니다.");
				}
			}
		});
		dicision.setFont(new Font("HY중고딕", Font.BOLD, 21));
		dicision.setBounds(181, 94, 101, 39);
		buyPanel.add(dicision);
	
		// 취소 버튼 : 안내 메시지 및 buyPanel 가리기
		JButton cancellation = new JButton("취소");
		cancellation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(buyPanel, "취소 하였습니다.");
				buyPanel.setVisible(false);
			}
		});
		cancellation.setFont(new Font("HY중고딕", Font.BOLD, 21));
		cancellation.setBounds(69, 94, 101, 39);
		buyPanel.add(cancellation);
	}
	
	// 매도 활동을 관리하는 함수
	public void actSell() {
		sellPanel = new JPanel();
		sellPanel.setBackground(new Color(255, 128, 128));
		sellPanel.setBounds(12, 66, 345, 138);
		actPanel.add(sellPanel);
		sellPanel.setLayout(null);
		sellPanel.setVisible(false);
	
		// '매도개수 : ' 출력
		JLabel coinOutputGuid = new JLabel("매도개수 : ");
		coinOutputGuid.setHorizontalAlignment(SwingConstants.CENTER);
		coinOutputGuid.setBackground(new Color(255, 255, 255));
		coinOutputGuid.setFont(new Font("HY엽서L", Font.BOLD, 18));
		coinOutputGuid.setBounds(38, 23, 101, 42);
		sellPanel.add(coinOutputGuid);
	
		// 매도 값 입력 받기
		JTextField outputNum = new JTextField();
		outputNum.setHorizontalAlignment(SwingConstants.RIGHT);
		outputNum.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 20));
		outputNum.setBounds(138, 29, 133, 31);
		sellPanel.add(outputNum);
	
		// '개' 출력
		JLabel unitGuid = new JLabel("개");
		unitGuid.setHorizontalAlignment(SwingConstants.LEFT);
		unitGuid.setFont(new Font("HY엽서L", Font.BOLD, 18));
		unitGuid.setBackground(Color.WHITE);
		unitGuid.setBounds(274, 23, 50, 42);
		sellPanel.add(unitGuid);
	
		// 매도 버튼 생성 및 설정
		JButton sellButton = new JButton("매도");
		sellButton.setForeground(new Color(128, 0, 0));
		sellButton.setFont(new Font("맑은 고딕", Font.BOLD, 24));
		sellButton.setBackground(new Color(255, 128, 128));
		sellButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				buyPanel.setVisible(false); // 매수 패널 가리기
				sellPanel.setVisible(true); // 매도 패널 보이기
			}
		});
		sellButton.setBounds(187, 10, 170, 48);
		actPanel.add(sellButton);
	
		// 최종 확인 버튼
		JButton dicision = new JButton("확인");
		dicision.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(buyCheck == 0) JOptionPane.showMessageDialog(sellPanel, "매수를 먼저 해주기 바랍니다.");
				else {
					if(0 < Double.parseDouble(outputNum.getText())){
						if(MainClass.purchaseNum <
								Double.parseDouble(outputNum.getText())) {
							JOptionPane.showMessageDialog(sellPanel, "입력 개수가 보유 개수보다 큽니다.");
						} else {
							
							mainC.selling(outputNum.getText()); // 입력 값 넘기기
							
							// 안내 메시지 및 sellPanel 가리기
							JOptionPane.showMessageDialog(sellPanel, "매도 하였습니다.");
							sellPanel.setVisible(false);
							
							if(MainClass.purchaseNum == 0) buyCheck = 0;
						}
					} else JOptionPane.showMessageDialog(sellPanel, "1개 이상 매도 바랍니다.");
				}
			}
		});
		dicision.setFont(new Font("HY중고딕", Font.BOLD, 21));
		dicision.setBounds(181, 94, 101, 39);
		sellPanel.add(dicision);
	
		// 취소 버튼  // 안내 메시지 및 sellPanel 가리기
		JButton cancellation = new JButton("취소");
		cancellation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				JOptionPane.showMessageDialog(sellPanel, "취소 하였습니다.");
				sellPanel.setVisible(false);
			}
		});
		cancellation.setFont(new Font("HY중고딕", Font.BOLD, 21));
		cancellation.setBounds(69, 94, 101, 39);
		sellPanel.add(cancellation);
	}
}
