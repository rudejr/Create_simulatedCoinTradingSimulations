package framework;

import javax.swing.JFrame;

/// 기본틀 (창) 세팅하는 클래스 ///
public class WindowSetting {
	
	// 각 사용 프레임 선언
	public JFrame lFrame; // 로그인 프레임		: used LogInWindow
	public JFrame rFrame; // 회원가입 프레임		: used RegistrationWindow
	public JFrame mFrame; // 게임활동 프레임		: used MainClasse
	public JFrame ghFrame; // 그래프 프레임		: used GraphSetting
	public JFrame ghVFrame;// 그래프 거래량 프레임	: used VolumeGraphSetting
	public JFrame tFrame; // 매수매도 프레임		: used TransactWindow
	
	// 모니터 중앙에 있는 mainClassWindow 위의 JFrame 위치 동적 할당을 위한 변수 선언
	int mainWdX = 1180;
	int mainWdY = 760;
	
	// 생성자를 통해 세팅된 프레임으로 초기화
	public WindowSetting() {
		logInWindow_init();
		registrationWindow_init();
		mainClassWindow_init();
		graphWindow_init();
		volumeGraphWindow_init();
		transactWindow_init();
	}
	
	// 로그인 프레임 세팅
	private void logInWindow_init() {
		lFrame = new JFrame(); 						// 프레임 메모리 할당
		lFrame.setTitle("모의 코인 거래 시뮬레이션"); 		// title 지정
		lFrame.setBounds(100, 100, 1180, 760);		// 크기 정하기
		lFrame.setResizable(false); 				// 사용자가 크기 조절 여부 false
		lFrame.setLocationRelativeTo(null); 		// 사용자의 모니터 중앙에 띄우기
		lFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫으면 프로그램 종료
		lFrame.getContentPane().setLayout(null); 	// 자유롭게 JPanel 그리기 위한 설정
	}
	
	// 회원가입 프레임 세팅
	private void registrationWindow_init() {
		rFrame = new JFrame();
		rFrame.setTitle("회원가입");
		rFrame.setBounds(100, 100, 1180, 760);
		rFrame.setResizable(false);
		rFrame.setLocationRelativeTo(null);
		rFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rFrame.getContentPane().setLayout(null);
	}
	
	// 게임활동 프레임 세팅
	private void mainClassWindow_init() {
		mFrame = new JFrame();
		mFrame.setTitle("모의 코인 거래 시뮬레이션 플레이");
		mFrame.setBounds(100, 100, 1180, 760);
		mFrame.setResizable(false);
		mFrame.setLocationRelativeTo(null);
		mFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mFrame.getContentPane().setLayout(null);
	}
	
	// 그래프 프레임 세팅
	private void graphWindow_init() {
		ghFrame = new JFrame();
		ghFrame.setTitle("호서코인 그래프");
		ghFrame.setBounds(275, 200, 775, 380);
		ghFrame.setResizable(false);
		ghFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ghFrame.getContentPane().setLayout(null);
	}
	
	// 그래프 거래량 프레임 세팅
	private void volumeGraphWindow_init() {
		ghVFrame = new JFrame();
		ghVFrame.setTitle("호서코인 거래량 그래프");
		ghVFrame.setBounds(1048, 300, 382, 280);
		ghVFrame.setResizable(false);
		ghVFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ghVFrame.getContentPane().setLayout(null);
	}
	
	// 매수매도 프레임 세팅
	private void transactWindow_init() {
		tFrame = new JFrame();
		tFrame.setTitle("매수매도 창");
		tFrame.setBounds(1048, 580, 382, 245); // 1048, 580, 382, 245
		tFrame.setResizable(false);
		tFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tFrame.getContentPane().setLayout(null);
	}
}
