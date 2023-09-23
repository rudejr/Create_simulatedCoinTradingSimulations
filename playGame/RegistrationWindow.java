package playGame;


import framework.WindowSetting;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;

/*
 * 서버와 연동해서 회원가입 도전은 했으나 차선책 선택
 */

public class RegistrationWindow extends WindowSetting {
	
	// 전역변수로 사용
	public JTextField inputRName;
	public JTextField inputRId;
	
	
	public RegistrationWindow() {
		
		JPanel rPanel = new JPanel();
		rPanel.setBounds(0, 0, 1166, 723);
		rFrame.getContentPane().add(rPanel);
		rPanel.setLayout(null);
		
		// 패널 title 생성 및 설정, 출력
		JLabel rTitle = new JLabel("회원가입");
		rTitle.setHorizontalAlignment(SwingConstants.LEFT);
		rTitle.setFont(new Font("휴먼모음T", Font.BOLD, 43));
		rTitle.setBounds(254, 204, 168, 62);
		rPanel.add(rTitle);
		
		// '이름 : ' 출력
		JLabel rNameInputGuid = new JLabel("이름 :");
		rNameInputGuid.setFont(new Font("휴먼모음T", Font.BOLD, 36));
		rNameInputGuid.setHorizontalAlignment(SwingConstants.CENTER);
		rNameInputGuid.setBounds(276, 280, 112, 66);
		rPanel.add(rNameInputGuid);
		
		// '학번 : ' 출력
		JLabel rIdInputGuid = new JLabel("학번 : ");
		rIdInputGuid.setHorizontalAlignment(SwingConstants.CENTER);
		rIdInputGuid.setFont(new Font("휴먼모음T", Font.BOLD, 36));
		rIdInputGuid.setBounds(286, 351, 112, 66);
		rPanel.add(rIdInputGuid);
		
		// 이름 입력 받기
		inputRName = new JTextField();
		inputRName.setHorizontalAlignment(SwingConstants.CENTER);
		inputRName.setFont(new Font("휴먼모음T", Font.PLAIN, 36));
		inputRName.setColumns(10);
		inputRName.setBounds(403, 280, 369, 66);
		rPanel.add(inputRName);
		
		// 학번 입력 받기
		inputRId = new JTextField();
		inputRId.setHorizontalAlignment(SwingConstants.CENTER);
		inputRId.setFont(new Font("휴먼모음T", Font.PLAIN, 36));
		inputRId.setColumns(10);
		inputRId.setBounds(403, 352, 369, 66);
		rPanel.add(inputRId);
		
		// 등록 버튼 생성 및 설정, 출력
		JButton registerButton = new JButton("등록");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(rFrame, "서버와 연결이 안 되었습니다.");
			}
		});
		registerButton.setFont(new Font("휴먼모음T", Font.PLAIN, 54));
		registerButton.setBounds(783, 277, 133, 141);
		rPanel.add(registerButton);
		
		// 돌아가기 버튼 생성 및 설정, 출력
		JButton gotoLogInWindow = new JButton("돌아가기");
		gotoLogInWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				rFrame.setVisible(false); // 본 화면 감추고
				tFrame.setVisible(false); // 매수매도 감추고
				
				// 로그인 화면 불러오기
				LogInWindow logInWd = new LogInWindow();
				logInWd.lFrame.setVisible(true);
				logInWd.lFrame.setAlwaysOnTop(true);
			}
		});
		gotoLogInWindow.setFont(new Font("휴먼모음T", Font.BOLD, 26));
		gotoLogInWindow.setBounds(783, 428, 133, 58);
		rPanel.add(gotoLogInWindow);
	}
}
