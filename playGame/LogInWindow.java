package playGame;


import framework.WindowSetting;
import settingData.MembersOf_The_01st_Class;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;


public class LogInWindow extends WindowSetting {
	
	// 로그인인을 위한 전역변수 선언
	public JTextField inputName = new JTextField(); // 이름 입력 받는 변수
	public JTextField inputId = new JTextField(); 	// 학번 입력 받는 변수
	public String printName;						// 찾은 사용자의 이름
	
	public LogInWindow() {
		
		// 패널 생성 및 frame과 연결
		JPanel lPanel = new JPanel();
		lPanel.setBounds(0, 0, 1166, 723);
		lFrame.getContentPane().add(lPanel); // extends WindowSetting
		lPanel.setLayout(null);
		
		// 게임 서브 제목 생성 및 설정, 출력
		JLabel gameSubName = new JLabel("호서대 컴공부(객체 01분반)");
		gameSubName.setHorizontalAlignment(SwingConstants.CENTER);
		gameSubName.setFont(new Font("한컴 울주 반구대 암각화체", Font.PLAIN, 39));
		gameSubName.setBounds(0, 141, 1166, 58);
		lPanel.add(gameSubName);
		
		// 게임 메인 제목 생성 및 설정, 출력
		JLabel gameMainName = new JLabel("모의 코인 거래 시뮬레이션");
		gameMainName.setFont(new Font("한컴 울주 반구대 암각화체", Font.BOLD, 43));
		gameMainName.setHorizontalAlignment(SwingConstants.CENTER);
		gameMainName.setBounds(0, 199, 1166, 58);
		lPanel.add(gameMainName);
		
		// '이름 : ' 출력
		JLabel nameInputGuid = new JLabel("이름 : ");
		nameInputGuid.setHorizontalAlignment(SwingConstants.CENTER);
		nameInputGuid.setFont(new Font("HY헤드라인M", Font.PLAIN, 36));
		nameInputGuid.setBounds(280, 310, 112, 66);
		lPanel.add(nameInputGuid);
		
		// 'ID(학번) : ' 출력
		JLabel idInputGuid = new JLabel("학번 : ");
		idInputGuid.setHorizontalAlignment(SwingConstants.CENTER);
		idInputGuid.setFont(new Font("HY헤드라인M", Font.PLAIN, 36));
		idInputGuid.setBounds(280, 386, 112, 66);
		lPanel.add(idInputGuid);
		
		// 이름 입력 받기
		inputName.setHorizontalAlignment(SwingConstants.CENTER);
		inputName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 36));
		inputName.setColumns(10);
		inputName.setBounds(393, 310, 369, 66);
		lPanel.add(inputName);
		
		// 학번 입력 받기
		inputId.setHorizontalAlignment(SwingConstants.CENTER);
		inputId.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 36));
		inputId.setColumns(10);
		inputId.setBounds(393, 385, 369, 66);
		lPanel.add(inputId);
		
		// 로그인 버튼 생성 및 설정, 출력
		JButton logInButton = new JButton("로그인");
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MembersOf_The_01st_Class member = new MembersOf_The_01st_Class();
				
				// 배열 속 학번과 이름 찾기
				int findName = -1;
				int findId = -1;
				for(int i = 0; i < member.name.length; i++) {
					if(member.name[i].equals(inputName.getText())) {
						findName = i;
						break;
					}
				}
				for(int i = 0; i < member.id.length; i++) {
					if(member.id[i].equals(inputId.getText())) {
						findId = i;
						break;
					}
				}
				
				// 로그인 조건	
				MainClass mainC = new MainClass();
				if(findName == -1 && findId == -1) {
					JOptionPane.showMessageDialog(lFrame, "비회원으로 로그인합니다.");
					
					// 사용자 이름 출력 알고리즘	
					mainC.showUserName.setText("비회원");
					lFrame.setVisible(false); // 본인화면 감추고
					
					mainC.mFrame.setVisible(true); // 게임화면 불러오기
					mainC.mFrame.setAlwaysOnTop(false);
					
					ghFrame.setAlwaysOnTop(true); // 그래프를 가렸다가 보이는 방법을 선택
					ghVFrame.setAlwaysOnTop(true);
					tFrame.setAlwaysOnTop(true); // 매수매도창을 가렸다가 보이는 방법을 선택
				} else {
					if(findName != findId) {
						JOptionPane.showMessageDialog(lFrame, "이름과 학번이 일치하지 않습니다.");
					} else {
						JOptionPane.showMessageDialog(lFrame, member.name[findName] + "님 환영합니다.");
						
						// 사용자 이름 출력 알고리즘	
						printName = String.format(member.name[findName]);
						mainC.showUserName.setText(printName);
						
						lFrame.setVisible(false); // 본인화면 감추고
						mainC.mFrame.setVisible(true); // 게임화면 불러오기
						mainC.mFrame.setAlwaysOnTop(false);
						
						ghFrame.setAlwaysOnTop(true); // 그래프를 가렸다가 보이는 방법을 선택
						ghVFrame.setAlwaysOnTop(true);
						tFrame.setAlwaysOnTop(true); // 매수매도창을 가렸다가 보이는 방법을 선택
					}
				}
			}
		});
		logInButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 30));
		logInButton.setBounds(774, 310, 133, 141);
		lPanel.add(logInButton);
		
		// 회원가입 버튼 생성 및 설정, 출력
		JButton registButton = new JButton("회원가입");
		registButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lFrame.setVisible(false); // 본 화면 감추고
				
				// 회원가입 화면 불러오기
				RegistrationWindow registrationWd = new RegistrationWindow();
				registrationWd.rFrame.setVisible(true);
				registrationWd.rFrame.setAlwaysOnTop(true);
				
			}
		});
		registButton.setFont(new Font("HY헤드라인M", Font.PLAIN, 22));
		registButton.setBounds(774, 461, 133, 58);
		lPanel.add(registButton);
		
		// 화면에 팀원 출력
		JLabel make_it = new JLabel("Makers 안경덕, 노우진, 석범진");
		make_it.setFont(new Font("HY견고딕", Font.PLAIN, 28));
		make_it.setVerticalAlignment(SwingConstants.BOTTOM);
		make_it.setHorizontalAlignment(SwingConstants.CENTER);
		make_it.setBounds(0, 654, 1166, 40);
		lPanel.add(make_it);
	}
}
