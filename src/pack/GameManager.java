package pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.StringTokenizer;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/* ******************************************************
* 프로그램명 : GameManager.java - GameManager클래스
* 작성자 : 2013041066 서재준, 2013041049 최재웅
* 조원 : 서재준(팀장), 최재웅, 이민섭, 강예지
* 작성일 : 2017.12.8
* 프로그램 설명 : 게임이 필요로 하는 요소(패널, 이벤트)를 포함하고 셋팅 해주는 프로그램
* 팀명 : 10조 큐빅
********************************************************* */
public class GameManager extends JFrame implements ActionListener,MouseListener, MouseMotionListener, Runnable{
	
	// 필드
	
	// 패널
	public JPanel Start_panel; // 시작화면
	public JPanel Top_panel; // 탑 패널
	public JPanel Right_panel; // 우측 패널
	public JPanel Map_panel; // 맵출력 패널
	public JPanel Under_panel; // 타워이미지 출력 패널
	public JButton btn1 = null; // 게임화면 넘어갈 때 JButton
	
	// 언더패널 - 타워버튼
	public JButton tower1btn = null; // 타워1버튼
	public JButton tower2btn = null; // 타워2버튼
	public JButton tower3btn = null; // 타워3버튼
	public JButton tower4btn = null; // 타워4버튼
	public JButton tower5btn = null; // 타워5버튼
	
	// 탑패널 - 플레이어 정보 출력 라벨
	public JLabel TopLabel1 = null; // 탑패널 라벨1
	public JLabel TopLabel2 = null; // 탑패널 라벨2
	public JLabel TopLabel3 = null; // 탑패널 라벨3
	public JLabel TopLabel4 = null; // 탑패널 라벨4
	public String PlayerName = null; // 플레이어 이름
	public int PlayerLife = 10; // 플레이어 목숨
	public int PlayerMoney = 5000; // 플레이어 화폐
	public int PlayerLevel = 1; // 플레이어 레벨
	
	// 우측패널 - 타워 정보 출력 라벨
	public JLabel RightLabel1 = null; // 오른쪽패널 라벨1
	public JLabel RightLabel2 = null; // 오른쪽패널 라벨2
	public JLabel RightLabel3 = null; // 오른쪽패널 라벨3
	public JLabel RightLabel4 = null; // 오른쪽패널 라벨4
	public JButton buytowerbtn = null; // 타워구매버튼
	
	//마우스 오버레이
	public JPanel mousepanel = null; // 마우스 오버레이
	
	// Map_Panel 출력 관련 필드
	JLayeredPane RenderingPan = null; // 맵패널
	Rendering[][] land; // 출력 정보를 담을 렌더링클래스의 2차원배열
	
	// 타워설치 관련 필드
	public boolean Isit_buildable[][] = new boolean[22][32]; // 타워설치가능한가
	public int TowerPosition[][] = new int[22][32]; // 맵위치에 따른 타워타입 저장
	public Tower bufftower; // 타워객체를 받을 임시저장소1
	public Tower bufftower2; // 타워객체를 받을 임시저장소2
	public int TowerType = 0; // 타워 타입
	public JButton jb[][]= new JButton[22][32]; // 타워의 이미지가 올라갈 JButton
	
	// 유닛 관련 필드
	public JPanel Unit_Panel[] = new JPanel[5]; // 유닛출력
	public JLabel Unit_Label[] = new JLabel[5]; // 유닛출력
	public int UnitCount = 0; // 유닛 카운터
	
	public JButton stb = null; // 게임시작 버튼
	
	
	public int p2=0,p1=0; // 좌표묶음
	
	
	// 메인
	public static void main(String[] args) {
		GameManager g1 = new GameManager(); // 메인쓰레드 생성
		Thread t1 = new Thread(g1);
		t1.start();
	}
	
	@Override
	public void run() { // 메인 쓰레드 내용
		GameManager frame = new GameManager(); // 프레임 출력
		frame.setVisible(true);
		File Clap =new File("bgm.wav"); // 사운드
		PlaySound(Clap);
	}	

	// 생성자
	public GameManager() {
		
		setTitle("큐빅 타워디펜스"); // 타이틀 지정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x누를 시 창이 닫힘
		setSize(1040,805); // 프레임 크기 지정
		
		Draw_Image di = new Draw_Image("./이미지파일/uiframe/introbg.png");
		setContentPane(di); // 배경이미지 삽입
		getContentPane().setLayout(null); // 절대좌표로 화면을 나눔

		setStart_Panel(); // 패널 출력 시작
		
	}
	static void PlaySound(File Sound) // 사운드
	{

		try{
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(Sound));
			clip.start();
			Thread.sleep(10);
		}
		catch(Exception e){}
	}

	class Draw_Image extends JPanel 
	{ // 이미지를 그려주는 클래스
	   private ImageIcon icon;
	   private Image img;
	   Draw_Image(String s)
	   {
		   icon = new ImageIcon(s);
		   img = icon.getImage();
	   }
	   public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(img, 0, 0, this); // 해당위치에 그림을 그려넣음
	   }
	}
	
	///////////////////////////패널 셋팅 모음//////////////////////////////
	
	void setStart_Panel() // 시작 패널
	{
		Start_panel = new JPanel(); // 메인화면
		Start_panel.setBounds(0, 0, 1040, 805); // 페널하나 더 얹어서 크기 조절
		Start_panel.setLayout(null); // 절대좌표로 지정
		
		
		btn1 = new JButton(""); // 게임시작 버튼 (투명)
		btn1.setBounds(330, 610, 359, 60);
		btn1.setBackground(Color.white);
		getContentPane().add(btn1); // 현재 프레임에 버튼달기
		btn1.setOpaque(false);//투명하게
		
		btn1.addActionListener(this); // 액션리스너 달기
		
	}

	void setTop_Panel()
	{ // 프레임의 가장 위에 위치하는 패널 (플레이어의 정보를 출력)
		Top_panel = new JPanel(); // 북쪽 (20,30,980,50)
		Top_panel.setBounds(40, 40, 950, 50); // 크기지정
		Top_panel.setLayout(null); // 절대좌표 지정
	      
	    TopLabel1 = new JLabel("남은 생명 : " + this.PlayerLife); // 라벨1
	    TopLabel1.setForeground(Color.WHITE);
	    TopLabel1.setBounds(30, 15, 130, 30);
	    Top_panel.add(TopLabel1);
	      
	    TopLabel2 = new JLabel("레벨 : " + this.PlayerLevel); // 라벨2
	    TopLabel2.setForeground(Color.WHITE);
	    TopLabel2.setBounds(230, 15, 330, 30);
	    Top_panel.add(TopLabel2);
	    
	    TopLabel3 = new JLabel("이름 : "+ this.PlayerName); // 라벨3
	    TopLabel3.setForeground(Color.WHITE);
	    TopLabel3.setBounds(430, 15, 530, 30);
	    Top_panel.add(TopLabel3);
	    
	    TopLabel4 = new JLabel("화폐 : "+ this.PlayerMoney); // 라벨4
	    TopLabel4.setForeground(Color.WHITE);
	    TopLabel4.setBounds(630, 15, 530, 30);
	    Top_panel.add(TopLabel4);
	    
	    stb = new JButton();
	    stb.setText("Start_Unit");
	    stb.setBounds(760, 15, 100, 30);
	    stb.addActionListener(this);
	    Top_panel.add(stb);
	    
	    Top_panel.setOpaque(false); // 패널 배경을 투명하게
	    getContentPane().add(Top_panel); // 프레임에 패널 달기
	}
	
	
	void setRight_Panel()
	{ // 프레임의 가장 우측에 위치하는 패널 (타워의 정보를 출력)
		Right_panel = new JPanel(); // 동쪽
		Right_panel.setBounds(800, 120, 190, 500); // 크기지정
		Right_panel.setLayout(null); // 절대좌표 지정
		
		RightLabel1 = new JLabel("타워 이름"); // 라벨1
		RightLabel1.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		RightLabel1.setBounds(10, 70, 150, 80);
		Right_panel.add(RightLabel1);
		
		RightLabel2 = new JLabel("타워 공격"); // 라벨2
		RightLabel2.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		RightLabel2.setBounds(10, 130, 150, 80);
		Right_panel.add(RightLabel2);
		
		RightLabel3 = new JLabel("구입 가격"); // 라벨3
		RightLabel3.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		RightLabel3.setBounds(10, 190, 150, 80);
		Right_panel.add(RightLabel3);
		
		RightLabel4 = new JLabel("판매 가격"); // 라벨3
		RightLabel4.setFont(new Font("나눔고딕", Font.BOLD | Font.ITALIC, 15));
		RightLabel4.setBounds(10, 250, 150, 80);
		Right_panel.add(RightLabel4);
		
		buytowerbtn = new JButton("Buy towers on click"); // 라벨4
		buytowerbtn.setBounds(10, 375, 160, 50);
		buytowerbtn.addActionListener(this);
		Right_panel.add(buytowerbtn);
		
	    	
		Right_panel.setOpaque(false); // 패널 배경을 투명하게
	    getContentPane().add(Right_panel); // 프레임에 패널 달기
	}
	
	void setUnder_Panel()
	{ // 프레임의 가장 하단에 위치하는 패널 (타워이미지가 있는 버튼을 출력)
		Under_panel = new JPanel(); // 남쪽
		Under_panel.setBounds(40, 640, 735, 95); // 크기지정
		Under_panel.setLayout(null); // 절대좌표 지정
		
		tower1btn = new JButton(); // 버튼생성 및 이미지삽입
		tower1btn.setContentAreaFilled(false); // 투명하게
		tower1btn.setBounds(90, 8, 85, 85); // 위치,크기 조정
		tower1btn.addActionListener(this); // 액션리스너 달기
		Under_panel.add(tower1btn); // JButton을 언더페널에 달아주기
		
		tower2btn = new JButton(); // 이하 같음
		tower2btn.setContentAreaFilled(false);
		tower2btn.setBounds(206, 8, 85, 85);
		tower2btn.addActionListener(this);
		Under_panel.add(tower2btn);
		
		tower3btn = new JButton();
		tower3btn.setContentAreaFilled(false);
		tower3btn.setBounds(325, 8, 85, 85);
		tower3btn.addActionListener(this);
		Under_panel.add(tower3btn);
		
		tower4btn = new JButton();
		tower4btn.setContentAreaFilled(false);
		tower4btn.setBounds(445, 6, 85, 85);
		tower4btn.addActionListener(this);
		Under_panel.add(tower4btn);
		
		tower5btn = new JButton();
		tower5btn.setContentAreaFilled(false);
		tower5btn.setBounds(560, 8, 88, 85);
		tower5btn.addActionListener(this);
		Under_panel.add(tower5btn);
	    	
		Under_panel.setOpaque(false); // 패널배경을 투명하게
	    getContentPane().add(Under_panel); // 프레임에 언더페널 달아줌
	}
	
	void setMap_Panel()
	{ // 좌측~센터까지 차지하는 패널로써, 맵을 구현하여 출력하는 패널
		
		
		//11*16 맵정보
		int map[][] = {
			         {2,2,1,2,2,2,2,2,2,2,2,2,2,2,2,2},
			         {2,0,1,0,3,0,0,0,0,0,0,0,0,0,0,2},
			         {2,0,1,0,3,0,1,1,1,1,1,1,1,1,0,2},
			         {2,0,1,0,3,0,1,0,0,0,0,0,0,1,0,2},
			         {2,0,1,0,3,0,1,0,3,3,3,3,0,1,0,2},
			         {2,0,1,0,3,0,1,0,3,0,0,0,0,1,0,2},
			         {2,0,1,0,3,0,1,0,3,0,1,1,1,1,0,2},
			         {2,0,1,0,0,0,1,0,3,0,1,0,0,0,0,2},
			         {2,0,1,1,1,1,1,0,3,0,1,1,1,1,1,1},
			         {2,0,0,0,0,0,0,0,3,0,0,0,0,0,0,2},
			         {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}
			         };
		
		
		Map_panel = new JPanel(); // JPanel 새로 생성
		Map_panel.setBounds(40, 120, 736, 506); // 크기 지정

		Map_panel.setLayout(null); // 절대좌표 지정
		Map_panel.setOpaque(false); // 배경 투명하게
	   
	    
		RenderingPan = new JLayeredPane(); 
		// 패널위에 정보를 띄우는 것이기 때문에 제이레이어드팬을 사용한다. 실질적으로 이곳에 띄울것임 
		RenderingPan.setBounds(0, 0, 736, 506);
	    	
		
		land = new Rendering[11][16]; // 2차원배열을 맵크기만큼 할당
	    	
	    for(int i=0;i<11;i++) // x는11만큼
	       for(int j=0;j<16;j++) // y는16만큼 반복
	       {
	          if (map[i][j] == 0) // 해당 위치의 맵정보가 0 이라면
	          {
	             land[i][j] = new Rendering(j*46, i*46, 0); // Rendering객체에 해당위치와 타입을 보냄
	             Isit_buildable[i*2][j*2] = true;
	             Isit_buildable[i*2][j*2 +1] = true;
	             Isit_buildable[i*2 +1][j*2] = true;
	             Isit_buildable[i*2 +1][j*2 +1] = true;
	          }
	          else if (map[i][j] == 1){ // 해당 위치의 맵정보가 1일때
	             land[i][j] = new Rendering(j*46, i*46, 1);   
	             
	          }else if (map[i][j] == 2){ // 해당 위치의 맵정보가 2일때
	             land[i][j] = new Rendering(j*46, i*46, 2);   
	             
	          }
	          else 
	          { // 그렇지 않다면 모두 3
	             land[i][j] = new Rendering(j*46, i*46, 3);
	             
	          }
	          land[i][j].setBounds(j*46, i*46, 46, 46); // 하나의 객체를 출력시킨 다음 크기를 지정
	          RenderingPan.add(land[i][j], new Integer(10)); // 깊이가 10인 곳에 해당 크기의 이미지를 출력
	       }
	    Map_panel.add(RenderingPan); // 깊이가10인애들을 추가한 레이어드팬을 맵패널에 추가함
	    
		getContentPane().add(Map_panel); // map_panel을 다만들고 현재프레임에 map_panel추가
	}
	
	////////////////////////////마우스 레이아웃////////////////////
	public void clickpanel() // 마우스 밑에 JPanel을 달아서, 마우스가 움직이면 밑에 패널이 마우스밑에서 같이 따라 움직임
	{
		mousepanel = new JPanel(); // 마우스패널 생성
		mousepanel.setBounds(0,0,46,46); // 46*46 크기로 셋팅
		RenderingPan.add(mousepanel, new Integer(50)); // 50의우선순위를 가진채로 JLayerd에 add
		mousepanel.setVisible(false); // 보이지 않게
		RenderingPan.addMouseListener(this); // 마우스리스너 add
		RenderingPan.addMouseMotionListener(this); // 마우스모션리스너 add
	}

	////////////////////////마우스 이벤트 연산//////////////////////////
	@Override
	public void mouseDragged(MouseEvent arg0) {} // 필요없는기능
	public void mouseReleased(MouseEvent arg0) {} // 필요없는기능
	public void mouseClicked(MouseEvent arg0) {} // 필요없는기능
	
	public void mouseEntered(MouseEvent arg0) 
	{ // 마우스가 Map_Panel에서 벗어났다가 다시들어온경우
		mousepanel.setVisible(true); // 보여주어야함
	}
	public void mouseExited(MouseEvent arg0) 
	{ // 마우스가 Map_Panel밖으로 나갔을 경우
		mousepanel.setVisible(false); // 보여주지 않아야함. 해당패널안에서만 보여야함
	}
	
	public void mouseMoved(MouseEvent arg0) { // 마우스가 움직일경우
		int x = arg0.getX()-arg0.getX()%23; // 현재 마우스포인터 위치(x,y)캐치
		int y = arg0.getY()-arg0.getY()%23; // x,y위치에서 나머지연산을 한값을빼서 맨 좌상측의 위치를 알아냄
		mousepanel.setBounds(x, y, 46 ,46); // 해당 위치에 46*46으로 크기셋팅
		
		if(bufftower != null) // 타워객체가 들어와있는 상황이라면
		{
			int px = arg0.getX()/23;
			int py = arg0.getY()/23;
			
			if (Isit_buildable[py][px] && Isit_buildable[py][px+1] && Isit_buildable[py+1][px] && Isit_buildable[py+1][px+1])
				mousepanel.setBackground(Color.white); // 설치가능 하다면 흰색으로 표시
			else
				mousepanel.setBackground(Color.red); // 설치 불가능 하다면 빨간색으로 표시
		}
		else // 타워객체가 들어와있는 상황이 아니라면
			mousepanel.setVisible(false); // 마우스밑에 뜨는 패널을 보이지않게함
		
	}

	public void mousePressed(MouseEvent arg0) { // 마우스버튼이 눌러졌을때
		int px = arg0.getX()/23; // 맵 한칸당 46*46이기때문에 반칸씩 기준을 잡아야해서 23으로 나눔
		int py = arg0.getY()/23;
		
		if(bufftower != null)
		{
			if (Isit_buildable[py][px] && Isit_buildable[py][px+1] && Isit_buildable[py+1][px] && Isit_buildable[py+1][px+1])
			{
				int x= arg0.getX() - arg0.getX()%23; // x,y위치에서 나머지연산을 한값을빼서 맨 좌상측의 위치를 알아냄
				int y = arg0.getY() - arg0.getY()%23;
				System.out.println(x +" "+y );
			
				bufftower.DrawTower(x,y); // 해당위치를 정보로 타워를 설치
				if(this.TowerType == 1) // 타워타입에 따라 화폐감소량 차이가 있음, 화폐출력
				{
					this.PlayerMoney -= 50;
					this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
				}
				if(this.TowerType == 2)
				{
					this.PlayerMoney -= 100;
					this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
				}
				if(this.TowerType == 3)
				{
					this.PlayerMoney -= 150;
					this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
				}
				if(this.TowerType == 4)
				{
					this.PlayerMoney -= 200;
					this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
				}
				if(this.TowerType == 5)
				{
					this.PlayerMoney -= 250;
					this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
				}
				mousepanel.setVisible(false); // 설치가 되었으면 마우스밑에 있던 패널 감추기
				File Clap =new File("dispersionpistol.wav"); // 사운드
				PlaySound(Clap);
				// 설치가되면 해당 해당 구역은 설치불가능으로 설정
				Isit_buildable[py][px] = false;
				Isit_buildable[py][px+1] = false;
				Isit_buildable[py+1][px] = false;
				Isit_buildable[py+1][px+1] = false;	
				
				
				// 해당 맵위치에 타워타입(1~5) 저장 -> 유닛 서치
				TowerPosition[py][px] =  TowerType;
				TowerPosition[py][px+1] =  TowerType;
				TowerPosition[py+1][px] =  TowerType;
				TowerPosition[py+1][px+1] =  TowerType;
				
				System.out.println("타워위치 : " + py +", " + px + "타워타입 : "+TowerType);
				
				bufftower = null; // 타워객체를 저장하는 임시저장소를 비워줌
			}
			else // 설치 할게아니면
			{
				mousepanel.setVisible(true); // 패널보여줌
			}
		}
	}
	
	
	
	//////////////////////////액션리스너/////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) { // 액션리스너
		System.out.println("클릭이벤트발생");
		//Mousclik
		File Clap =new File("Mousclik.wav"); // 사운드
		PlaySound(Clap);
		 if ( e.getSource() == btn1 ) // 시작패널에서 "GAME START" 클릭 시
		    {
			 
			 String buff = JOptionPane.showInputDialog("Player 이름을 입력하세요!!","Cuvic");
			 if(buff == null) this.PlayerName = "10조 Cuvic";
			 else this.PlayerName = buff;
			 
			 getContentPane().removeAll(); // 모두 지우기
			 Draw_Image di = new Draw_Image("./이미지파일/uiframe/mainbg.png");
			 setContentPane(di); // 메인이미지 지정
			 getContentPane().setLayout(null); // 절대좌표 설정
				
			 setTop_Panel(); // 탑패널 출력
			 setRight_Panel(); // 오른쪽패널 출력
			 setMap_Panel(); // 맵패널 출력
			 setUnder_Panel(); // 언더패널 출력
			 this.TopLabel3.setText("이름 : "+ this.PlayerName);
			 revalidate();
			 repaint(); // 업데이트
			 
			// 게임 오버, 클리어 체크
			 CheckGame cg = new CheckGame(this);
			 Thread Tcg = new Thread(cg);
			 Tcg.start();
			 
		    }
		 else if ( e.getSource() == buytowerbtn) // 우측패널의 구매버튼을 눌렀을 경우
		 {
			 
			if(TowerType == 1) // 타워타입이 1일 경우 1번타워 객체 생성
			{
				bufftower = new Tower(this, "1번타워", 50, 5, 25);
			}
			if(TowerType == 2) // 타워타입이 2일 경우 2번타워 객체 생성
			{
				bufftower = new Tower2(this, "2번타워", 100, 15,50);
			}
			if(TowerType == 3) // 타워타입이 3일 경우 3번타워 객체 생성
			{
				bufftower = new Tower3(this, "3번타워", 150, 25,75);
			}
			if(TowerType == 4) // 타워타입이 4일 경우 4번타워 객체 생성
			{
				bufftower = new Tower4(this, "4번타워", 200, 35,100);
			}
			if(TowerType == 5) // 타워타입이 5일 경우 5번타워 객체 생성
			{
				bufftower = new Tower5(this, "5번타워", 250, 45,125);
			}
			
			clickpanel(); // 마우스 레이아웃 시작
			 
		 }
		 else if ( e.getSource() == tower1btn) // 하단패널의 1번타워를 클릭했을 경우
		 {
			 TowerType = 1; // 타워타입을 1로 정의
			 new Tower(this, "1번타워", 50, 5, 25).setInfo(); // 1번타워의 정보 출력
		 }
		 else if ( e.getSource() == tower2btn) // 하단패널의 2번타워를 클릭했을 경우
		 {
			 TowerType = 2; // 타워타입을 2로 정의
			 new Tower(this, "2번타워", 100, 15, 50).setInfo(); // 2번타워의 정보 출력
		 }
		 else if ( e.getSource() == tower3btn) // 하단패널의 3번타워를 클릭했을 경우
		 {
			 TowerType = 3; // 타워타입을 3으로 정의
			 new Tower(this, "3번타워", 150, 25, 75).setInfo(); // 3번타워의 정보 출력
		 }
		 else if ( e.getSource() == tower4btn) // 하단패널의 4번타워를 클릭했을 경우
		 {
			 TowerType = 4; // 타워타입을 4로 정의
			 new Tower(this, "4번타워", 200, 35, 100).setInfo(); // 4번타워의 정보 출력
		 }
		 else if ( e.getSource() == tower5btn) // 하단패널의 5번타워를 클릭했을 경우
		 {
			 TowerType = 5; // 타워타입을 5로 정의
			 new Tower(this, "5번타워", 250, 45, 125).setInfo(); // 5번타워의 정보출력
		 }
		 else if ( e.getSource() == stb) // 게임시작버튼을 눌렀을 경우
		 {
			 // 유닛 객체 생성
			 Unit un1 = new Unit(this, 700);
			 Unit2 un2 = new Unit2(this, 800);
			 Unit3 un3 = new Unit3(this, 900);
			 Unit4 un4 = new Unit4(this, 1000);
			 Unit5 un5 = new Unit5(this, 1100);
				 
			 // 각 유닛마다 쓰레드 생성
			 Thread unst1 = new Thread(un1);
			 Thread unst2 = new Thread(un2);
			 Thread unst3 = new Thread(un3);
			 Thread unst4 = new Thread(un4);
			 Thread unst5 = new Thread(un5);

			 File Clap1 =new File("oldhorn.wav"); // 사운드
			 PlaySound(Clap1);
			 // 쓰레드 시작
			 unst1.start();
			 unst2.start();
			 unst3.start();
			 unst4.start();
			 unst5.start();
			 
			 
		 }
		 else if ( e.getID() == 1001 ) // 타워 JButton
		 {
			 System.out.println("1001-> " +p2 + ", "+ p1);
			 
			 // 객체를 받아와서 토큰 확인 -> x,y 좌표 찾아내는 것
			 String buff = e.getSource().toString();
			 StringTokenizer st = new StringTokenizer(buff, ",");
			 st.nextToken(); 
			 // 받아온 좌표 렌더링
			 this.p1 = Integer.parseInt(st.nextToken())/23;
			 this.p2 = Integer.parseInt(st.nextToken())/23;
			 
			 
			 // 받아온 좌표에있는 버튼의 타워종류에 따라 판매,업그레이드 가능하게 함 
			 if( (TowerPosition[p2][p1] == 1) && (TowerPosition[p2+1][p1] == 1) 
					 && (TowerPosition[p2][p1+1] == 1)&& (TowerPosition[p2+1][p1+1] == 1))
			 {
				// 판매 , 업그레이드
				 String[] buttons = {"Sell", "Upgrade", "취소"};
				 int result = JOptionPane.showOptionDialog(null, "타워1 판매 가격 : 25원\n타워1 => 타워2 업그레이드 비용 : 50.", "타워1",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
				 if(result == JOptionPane.YES_OPTION)
					 {
					 	this.SellTower(25);
					 	File Clap1 =new File("coins.wav"); // 사운드
					 	PlaySound(Clap1);
					 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(50);
					 File Clap1 =new File("upgrade.wav"); // 사운드
					 PlaySound(Clap1);
				 }

			 }
			 if( (TowerPosition[p2][p1] == 2) && (TowerPosition[p2+1][p1] == 2) 
					 && (TowerPosition[p2][p1+1] == 2)&& (TowerPosition[p2+1][p1+1] == 2))
			 {
				// 판매 , 업그레이드
				 String[] buttons = {"Sell", "Upgrade", "취소"};
				 int result = JOptionPane.showOptionDialog(null, "타워2 판매 가격 : 50원\n타워2 => 타워3 업그레이드 비용 : 100.", "타워2",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
				 if(result == JOptionPane.YES_OPTION)
				 {
					 this.SellTower(50);
					 File Clap1 =new File("coins.wav"); // 사운드
					 PlaySound(Clap1);
				 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(100);
					 File Clap1 =new File("upgrade.wav"); // 사운드
					 PlaySound(Clap1);
				 }
			 }
			 if( (TowerPosition[p2][p1] == 3) && (TowerPosition[p2+1][p1] == 3) 
					 && (TowerPosition[p2][p1+1] == 3)&& (TowerPosition[p2+1][p1+1] == 3))
			 {
				// 판매 , 업그레이드
				 String[] buttons = {"Sell", "Upgrade", "취소"};
				 int result = JOptionPane.showOptionDialog(null, "타워3 판매 가격 : 75원\n타워3 => 타워4 업그레이드 비용 : 150.", "타워3",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
				 if(result == JOptionPane.YES_OPTION)
				 {
					 this.SellTower(75);
					 File Clap1 =new File("coins.wav"); // 사운드
					 PlaySound(Clap1);
				 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(150);
					 File Clap1 =new File("upgrade.wav"); // 사운드
					 PlaySound(Clap1);
				 }
			 }
			 if( (TowerPosition[p2][p1] == 4) && (TowerPosition[p2+1][p1] == 4) 
					 && (TowerPosition[p2][p1+1] == 4)&& (TowerPosition[p2+1][p1+1] == 4))
			 {
				// 판매 , 업그레이드
				 String[] buttons = {"Sell", "Upgrade", "취소"};
				 int result = JOptionPane.showOptionDialog(null, "타워4 판매 가격 : 100원\n타워4 => 타워5 업그레이드 비용 : 200.", "타워4",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
				 if(result == JOptionPane.YES_OPTION)
				 {
					 this.SellTower(100);
					 File Clap1 =new File("coins.wav"); // 사운드
					 PlaySound(Clap1);
				 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(200);
					 File Clap1 =new File("upgrade.wav"); // 사운드
					 PlaySound(Clap1);
				 };
			 }
			 if( (TowerPosition[p2][p1] == 5) && (TowerPosition[p2+1][p1] == 5) 
					 && (TowerPosition[p2][p1+1] == 5)&& (TowerPosition[p2+1][p1+1] == 5))
			 {
				// 판매 , 업그레이드
				 String[] buttons = {"Sell", "Upgrade", "취소"};
				 int result = JOptionPane.showOptionDialog(null, "타워5 판매 가격 : 125원\n더이상 타워업그레이드를 할 수 없습니다..", "타워5",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두번째값");
				 if(result == JOptionPane.YES_OPTION)
				 {	 
					 this.SellTower(125);
					 File Clap1 =new File("coins.wav"); // 사운드
					 PlaySound(Clap1);
				 }
			 }
		 }
        
	}
	
	void SellTower(int sellcost) // 타워 판매
	{
		System.out.println("타워 제거, 얻은 화폐 : " + sellcost);
		// 설치해제 -> 설치가능
		Isit_buildable[p2][p1] = true;
		Isit_buildable[p2][p1+1] = true;
		Isit_buildable[p2+1][p1] = true;
		Isit_buildable[p2+1][p1+1] = true;	
		
		// 타워 타입 해제
		TowerPosition[p2][p1] =  0;
		TowerPosition[p2][p1+1] =  0;
		TowerPosition[p2+1][p1] =  0;
		TowerPosition[p2+1][p1+1] =  0;
		
		// 렌더링패널에서 해당버튼 삭제 후 갱신
		RenderingPan.remove(jb[p2][p1]);
		RenderingPan.revalidate();
		RenderingPan.repaint();
		jb[p2][p1] = null; // 널값 넣어줌
		
		// 판매가격만큼 플레이어 화폐 증가
		this.PlayerMoney += sellcost;
		this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
	}
	
	void UpgradTower(int Ucost) // 타워 업그레이드
	{
		int type = this.TowerPosition[p2][p1];
		System.out.println("업그레이드 될 타워 몇번?: "+type);
		
		if(type == 1) // 기존 1이면 2로 업그레이드
		{
			this.SellTower(0); // 지우고
			System.out.println("타워1->2로 업그레이드, 소모 화폐 : " + Ucost);
			bufftower = new Tower2(this, "2번타워", 100, 15,50);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// 설치가되면 해당 해당 구역은 설치불가능으로 설정
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// 해당 맵위치에 타워타입(1~5) 저장 -> 유닛 서치
			TowerPosition[p2][p1] =  2;
			TowerPosition[p2][p1+1] =  2;
			TowerPosition[p2+1][p1] =  2;
			TowerPosition[p2+1][p1+1] =  2;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
		}
		if(type == 2) // 기존 2면 3으로 업그레이드
		{
			this.SellTower(0); // 지우고
			System.out.println("타워2->3으로 업그레이드, 소모 화폐 : " + Ucost);
			bufftower = new Tower3(this, "3번타워", 150, 25,75);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// 설치가되면 해당 해당 구역은 설치불가능으로 설정
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// 해당 맵위치에 타워타입(1~5) 저장 -> 유닛 서치
			TowerPosition[p2][p1] =  3;
			TowerPosition[p2][p1+1] =  3;
			TowerPosition[p2+1][p1] =  3;
			TowerPosition[p2+1][p1+1] =  3;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
		}
		if(type == 3)
		{
			this.SellTower(0); // 지우고
			System.out.println("타워3->4로 업그레이드, 소모 화폐 : " + Ucost);
			bufftower = new Tower4(this, "4번타워", 200, 35,100);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// 설치가되면 해당 해당 구역은 설치불가능으로 설정
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// 해당 맵위치에 타워타입(1~5) 저장 -> 유닛 서치
			TowerPosition[p2][p1] =  4;
			TowerPosition[p2][p1+1] =  4;
			TowerPosition[p2+1][p1] =  4;
			TowerPosition[p2+1][p1+1] =  4;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
		}
		if(type == 4)
		{
			this.SellTower(0); // 지우고
			System.out.println("타워4->5로 업그레이드, 소모 화폐 : " + Ucost);
			bufftower = new Tower5(this, "5번타워", 250, 45, 125);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// 설치가되면 해당 해당 구역은 설치불가능으로 설정
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// 해당 맵위치에 타워타입(1~5) 저장 -> 유닛 서치
			TowerPosition[p2][p1] =  5;
			TowerPosition[p2][p1+1] =  5;
			TowerPosition[p2+1][p1] =  5;
			TowerPosition[p2+1][p1+1] =  5;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("화폐 : "+ this.PlayerMoney);
		}
	}
}


/* ******************************************************
* 프로그램명 : GameManager.java - Rendering클래스
* 작성자 : 2013041066 서재준, 2013041049 최재웅
* 조원 : 서재준(팀장), 최재웅, 이민섭, 강예지
* 작성일 : 2017.12.1
* 프로그램 설명 : Map_Panel을 셋팅 해주는 프로그램
* 팀명 : 10조 큐빅
********************************************************* */
class Rendering extends JPanel
{ // 실질적으로 정보를 받아와 출력 해주는 클래스
   public int x; // 절대좌표에서 x
   public int y; // 절대좌표에서 y
   public int type = 0; // 맵의 속성 중 하나
   Image img; // 이미지
   
   
   Rendering(int x, int y , int t) // 생성자
   {
	  // 받아온 좌표를 저장
	   this.x = x;
	   this.y = y;
	   type = t;
      
	   Toolkit tk = Toolkit.getDefaultToolkit(); //이미지를 받아올 toolkit 객체선언
	   try{
		   if ( t == 1) 
		   { // 타입이 1일때 길 출력
			   img = tk.getImage("./이미지파일/terrain/road.png");
		   }
		   else if (t == 0) 
		   { // 타입이 0일때 풀 출력
			   img = tk.getImage("./이미지파일/terrain/grass.png");
		   }
		   else if (t==3)
		   { // 타입이 3일때 나무 출력
			   img = tk.getImage("./이미지파일/terrain/tree.png");
		   }
		   else if (t == 2) 
		   { // 타입이 2일때 물 출력
			   img = tk.getImage("./이미지파일/terrain/water.png");
		   }
	   }catch(Exception e){
    	  System.out.println(e);
	   }           
   	}
   
   public void paintComponent(Graphics g) // 해당위치에 46*46크기만큼 이미지를 그려줌
   {
      g.drawImage(img,0,0,46,46,this);         
   }
}
