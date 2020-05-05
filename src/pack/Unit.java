package pack;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/* ******************************************************
* 프로그램명 : Unit.java - Unit~Unit5 클래스
* 작성자 : 2013041066 서재준, 2013041049 최재웅
* 조원 : 서재준(팀장), 최재웅, 이민섭, 강예지
* 작성일 : 2017.12.8
* 프로그램 설명 : 유닛이 생성되고, 타워를 보면 데미지를 입게 해주는 프로그램
* 팀명 : 10조 큐빅
********************************************************* */
public class Unit implements Runnable  {

	GameManager g;
	
	// 필드
	int Unit_Map[][] = { // 유닛이 움직일 위치들
			{23*4, 23*2}, {23*4, 23*3}, {23*4, 23*4}, {23*4, 23*5}, {23*4, 23*6}, {23*4, 23*7} ,{23*4, 23*8}
			, {23*4, 23*9}, {23*4, 23*10}, {23*4, 23*11}, {23*4, 23*12}, {23*4, 23*13}, {23*4, 23*14}, {23*4, 23*15}, {23*4, 23*16}
			
			,{23*5, 23*16}, {23*6, 23*16}, {23*7, 23*16}, {23*8, 23*16}, {23*9, 23*16}, {23*10, 23*16}
			
			, {23*11, 23*16}, {23*12, 23*16}, {23*12, 23*15}, {23*12, 23*14}, {23*12, 23*13}, {23*12, 23*12}, {23*12, 23*11}, {23*12, 23*10}
			, {23*12, 23*9}, {23*12, 23*8}, {23*12, 23*7}, {23*12, 23*6}, {23*12, 23*5}, {23*12, 23*4}

			, {23*13, 23*4}, {23*14, 23*4}, {23*15, 23*4}, {23*16, 23*4}, {23*17, 23*4}, {23*18, 23*4}, {23*19, 23*4}, {23*20, 23*4}
			, {23*21, 23*4}, {23*22, 23*4}, {23*23, 23*4}, {23*24, 23*4}, {23*25, 23*4}, {23*26, 23*4}
			
			, {23*26, 23*5}, {23*26, 23*6}, {23*26, 23*7}, {23*26, 23*8}, {23*26, 23*9}, {23*26, 23*10}, {23*26, 23*11}, {23*26, 23*12}
			
			, {23*25, 23*12}, {23*24, 23*12}, {23*23, 23*12}, {23*22, 23*12}, {23*21, 23*12}, {23*20, 23*12}
			
			, {23*20, 23*13}, {23*20, 23*14}, {23*20, 23*15}, {23*20, 23*16}

			, {23*21, 23*16}, {23*22, 23*16}, {23*23, 23*16}, {23*24, 23*16}, {23*25, 23*16}, {23*26, 23*16}, {23*27, 23*16}, {23*28, 23*16}
			, {23*29, 23*16}
			};
	
	
	int a=700; // 기본적인 깊이는 700
	int hp=300; // 레벨1 유닛의 체력은 300
	int money = 10; // 레벨1 유닛이 죽었을 때 얻는 화폐량 10
	
	Unit(GameManager gm, int k) // 생성자
	{
		g = gm; // GameManager 객체 받아옴
		Set_Unit(); // 유닛 셋팅
		a = k; // J레이어드 깊이
		g.UnitCount++; // 유닛카운트+1
	}
	
	public void run() // 쓰레드 내용
	{
        try {
        	g.stb.setEnabled(false);
        	System.out.println("유닛1 시작");
        		for(int i = 0 ; i < 77; i++) // 유닛이 가야 할 길을 모두 돌 예정
        		{
        			if(g.Unit_Panel[0] != null) // 유닛이 살아있다면
        			{
        				Move_Unit(i); // 유닛이 움직임(+서칭)
        				
        				if(this.hp <= 0) // 유닛의 hp가 다 닳아 죽었을 때
        				{
        					
        					File Clap1 =new File("deadunit.wav"); // 사운드
        					GameManager.PlaySound(Clap1);
        					// 유닛사망에 대한 이펙트 출력
        					JLabel eff = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_deadeff.png"));
        					eff.setBounds(Unit_Map[i][0], Unit_Map[i][1], 46, 46);
        					g.RenderingPan.add(eff, new Integer(2000));
        					
        					// 유닛객체 삭제 과정
        					g.Unit_Panel[0].removeAll();
        					g.Unit_Panel[0].setVisible(false);
        					g.Unit_Panel[0].repaint();
        					g.Unit_Panel[0] = null;
        					
        					// 해당 유닛의 가치에 따라, 플레이어 화폐 상승
        					g.PlayerMoney += this.money;
        					g.TopLabel4.setText("화폐 : "+ g.PlayerMoney);
        					
        					//유닛이 죽었기때문에 카운트--
        					g.UnitCount--;
        					System.out.println("유닛1 사망");
        					
        					Thread.sleep(1000);
        					eff.setVisible(false);
        					eff.removeAll();
        					eff.repaint();
        				}
        				if(i == 76) // 못죽인 상태로 유닛의 목적지에 도착할 경우
        				{
        					File Clap1 =new File("deadunit.wav"); // 사운드
        					GameManager.PlaySound(Clap1);
        					// 유닛 삭제
        					g.Unit_Panel[0].removeAll();
        					g.Unit_Panel[0].setVisible(false);
        					g.Unit_Panel[0].repaint();
        					g.Unit_Panel[0] = null;
        					
        					// 플레이어 라이프 감소
        					g.PlayerLife -= 1;
        					g.TopLabel1.setText("남은 생명 : " + g.PlayerLife);
        					System.out.println("유닛1 도착 -> 라이프 감소");
        				}
        			Thread.sleep(100); // 0.1초마다 움직임
        			}
        		}
        		
        }catch(Exception e) { // 예외확인
        	System.out.println(e);
        }
    }
	
	
	void Move_Unit(int x) // 유닛 움직임
	{
		if(x == 76) // 마지막 포인트에 도착하였을 경우 메시지 출력
		{
			System.out.println("유닛1 도착");
		}
		else // 마지막 포인트가 아닐경우
		{
			// 유닛(JPanel)이 정해진 포인트를 따라 움직임
			g.Unit_Panel[0].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"유닛1 움직임");
			
			// 유닛 주변에 타워가 있을 경우, 데미지 만큼 체력 감소
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("유닛1 체력 : "+ hp);
			g.Unit_Panel[0].repaint();
			g.Unit_Panel[0].setVisible(true);
		}
	}
	
	void Set_Unit() // 유닛 셋팅
	{
		// 절대좌표 JPanel하나 할당
		g.Unit_Panel[0] = new JPanel();
		g.Unit_Panel[0].setLayout(null);
		
		if(g.PlayerLevel == 1) // 레벨1의 경우
			g.Unit_Label[0] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl1.gif"));
		if(g.PlayerLevel == 2) // 레벨2의 경우
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[0] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3) // 레벨3의 경우
		{
			this.hp = this.hp+200;
			this.money = this.money+200;
			g.Unit_Label[0] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl3.gif"));
		}
		g.Unit_Label[0].setBounds(0, 0, 46, 46); // 라벨 크기지정
		
		g.Unit_Panel[0].add(g.Unit_Label[0]);
		g.RenderingPan.add(g.Unit_Panel[0], new Integer(a)); // RenderingPan에 add
		g.Unit_Panel[0].setVisible(true);
		System.out.println("유닛1 생성");
		
	}
	
	int Detecting_Damage_Received(int x) // 유닛이 지나가면서 피해를 입음
	{ //열 행
		System.out.println("디텍트 서치위치 : "+Unit_Map[x][1]/23+", " +Unit_Map[x][0]/23);
		
		// 유닛의 위치 기준에서 8방향 서칭, 타워타입1,2,3,4,5에 따라 hp감소량이 다름
		if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 1 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 1 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 1 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 1 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 1 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 1 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 1 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 1) // ++
			{System.out.println("타워1 탐지");return 5;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 2 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 2 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 2 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 2 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 2 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 2 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 2 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 2) // ++
			{System.out.println("타워2 탐지");return 15;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 3 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 3 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 3 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 3 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 3 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 3 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 3 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 3) // ++
			{System.out.println("타워3 탐지");return 25;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 4 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 4 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 4 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 4 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 4 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 4 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 4 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 4) // ++
			{System.out.println("타워4 탐지");return 35;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 5 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 5 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 5 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 5 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 5 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 5 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 5 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 5) // ++
			{System.out.println("타워5 탐지");return 45;}
		else
			{System.out.println("발견x");return 0;}		
	}
}

//
// 이하 클래스부터는 Unit클래스에서 패널,라벨 인덱스,문자열등이 바뀌는 것임, 로직이 모두 같으므로 주석 생략
//
class Unit2 extends Unit
{

	Unit2(GameManager gm, int k) {
		super(gm, k);
	}
	
	public void run()
	{
        try {
        	Thread.sleep(300);
        	System.out.println("유닛2 시작");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[1] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // 죽였을 때
    				{
        				File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_deadeff.png"));
    					eff.setBounds(Unit_Map[i][0], Unit_Map[i][1], 46, 46);
    					g.RenderingPan.add(eff, new Integer(2000));
    					Thread.sleep(1000);
    					eff.setVisible(false);
    					eff.removeAll();
    					eff.repaint();
        				
    					g.Unit_Panel[1].removeAll();
    					g.Unit_Panel[1].setVisible(false);
    					g.Unit_Panel[1].repaint();
    					g.Unit_Panel[1] = null;
    					
    					g.PlayerMoney += this.money;
    					g.TopLabel4.setText("화폐 : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("유닛2 사망");
    				}
    				if(i == 76) // 못죽인 상태로 도착할 경우
    				{
    					File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[1].removeAll();
    					g.Unit_Panel[1].setVisible(false);
    					g.Unit_Panel[1].repaint();
    					g.Unit_Panel[1] = null;
    					
    					g.PlayerLife -= 1;
    					g.TopLabel1.setText("남은 생명 : " + g.PlayerLife);
    					System.out.println("유닛2 도착 -> 라이프 감소");
    				}
        			Thread.sleep(100);
        			}
        		}
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // 유닛 움직임
	{
		if(x == 76)
		{
			System.out.println("유닛2 도착");
		}
		else
		{
			g.Unit_Panel[1].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"유닛2 움직임");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("유닛2 체력 : "+ hp);
			g.Unit_Panel[1].repaint();
			g.Unit_Panel[1].setVisible(true);
		}
	}
	
	void Set_Unit() // 셋팅
	{
		g.Unit_Panel[1] = new JPanel();
		g.Unit_Panel[1].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[1] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[1] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[1] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl3.gif"));
		}
		g.Unit_Label[1].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[1].add(g.Unit_Label[1]);
		g.RenderingPan.add(g.Unit_Panel[1], new Integer(a));
		g.Unit_Panel[1].setVisible(true);
		System.out.println("유닛2 생성");
		
	}
}

class Unit3 extends Unit
{

	Unit3(GameManager gm, int k) {
		super(gm, k);
	}
	
	public void run()
	{
        try {
        	Thread.sleep(600);
        	System.out.println("유닛3 시작");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[2] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // 죽였을 때
    				{
        				File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_deadeff.png"));
    					eff.setBounds(Unit_Map[i][0], Unit_Map[i][1], 46, 46);
    					g.RenderingPan.add(eff, new Integer(2000));
    					Thread.sleep(1000);
    					eff.setVisible(false);
    					eff.removeAll();
    					eff.repaint();
        				
    					g.Unit_Panel[2].removeAll();
    					g.Unit_Panel[2].setVisible(false);
    					g.Unit_Panel[2].repaint();
    					g.Unit_Panel[2] = null;
    					
    					g.PlayerMoney += this.money;
    					g.TopLabel4.setText("화폐 : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("유닛3 사망");
    				}
    				if(i == 76) // 못죽인 상태로 도착할 경우
    				{
    					File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[2].removeAll();
    					g.Unit_Panel[2].setVisible(false);
    					g.Unit_Panel[2].repaint();
    					g.Unit_Panel[2] = null;
    					
    					g.PlayerLife -= 1;
    					g.TopLabel1.setText("남은 생명 : " + g.PlayerLife);
    					System.out.println("유닛3 도착 -> 라이프 감소");
    				}
        			Thread.sleep(100);
        			}
        		}
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // 유닛 움직임
	{
		if(x == 76)
		{
			System.out.println("유닛3 도착");
		}
		else
		{
			g.Unit_Panel[2].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"유닛3 움직임");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("유닛3 체력 : "+ hp);
			g.Unit_Panel[2].repaint();
			g.Unit_Panel[2].setVisible(true);
		}
	}
	
	void Set_Unit() // 셋팅
	{
		g.Unit_Panel[2] = new JPanel();
		g.Unit_Panel[2].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[2] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[2] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[2] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl3.gif"));
		}
		g.Unit_Label[2].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[2].add(g.Unit_Label[2]);
		g.RenderingPan.add(g.Unit_Panel[2], new Integer(a));
		g.Unit_Panel[2].setVisible(true);
		System.out.println("유닛3 생성");
		
	}
}

class Unit4 extends Unit
{

	Unit4(GameManager gm, int k) {
		super(gm, k);
	}
	
	public void run()
	{
        try {
        	Thread.sleep(900);
        	System.out.println("유닛4 시작");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[3] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // 죽였을 때
    				{
        				File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_deadeff.png"));
    					eff.setBounds(Unit_Map[i][0], Unit_Map[i][1], 46, 46);
    					g.RenderingPan.add(eff, new Integer(2000));
    					Thread.sleep(1000);
    					eff.setVisible(false);
    					eff.removeAll();
    					eff.repaint();
        				
    					g.Unit_Panel[3].removeAll();
    					g.Unit_Panel[3].setVisible(false);
    					g.Unit_Panel[3].repaint();
    					g.Unit_Panel[3] = null;
    					
    					g.PlayerMoney += this.money;
    					g.TopLabel4.setText("화폐 : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("유닛4 사망");
    				}
    				if(i == 76) // 못죽인 상태로 도착할 경우
    				{
    					File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[3].removeAll();
    					g.Unit_Panel[3].setVisible(false);
    					g.Unit_Panel[3].repaint();
    					g.Unit_Panel[3] = null;
    					
    					g.PlayerLife -= 1;
    					g.TopLabel1.setText("남은 생명 : " + g.PlayerLife);
    					System.out.println("유닛4 도착 -> 라이프 감소");
    				}
        			Thread.sleep(100);
        			}
        		}
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // 유닛 움직임
	{
		if(x == 76)
		{
			System.out.println("유닛4 도착");
		}
		else
		{
			g.Unit_Panel[3].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"유닛4 움직임");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("유닛4 체력 : "+ hp);
			g.Unit_Panel[3].repaint();
			g.Unit_Panel[3].setVisible(true);
		}
	}
	
	void Set_Unit() // 셋팅
	{
		g.Unit_Panel[3] = new JPanel();
		g.Unit_Panel[3].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[3] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[3] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[3] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl3.gif"));
		}
		g.Unit_Label[3].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[3].add(g.Unit_Label[3]);
		g.RenderingPan.add(g.Unit_Panel[3], new Integer(a));
		g.Unit_Panel[3].setVisible(true);
		System.out.println("유닛4 생성");
		
	}
}

class Unit5 extends Unit
{

	Unit5(GameManager gm, int k) {
		super(gm, k);
	}
	
	public void run()
	{
        try {
        	Thread.sleep(1200);
        	System.out.println("유닛5 시작");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[4] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // 죽였을 때
    				{
        				File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_deadeff.png"));
    					eff.setBounds(Unit_Map[i][0], Unit_Map[i][1], 46, 46);
    					g.RenderingPan.add(eff, new Integer(2000));
    					Thread.sleep(1000);
    					eff.setVisible(false);
    					eff.removeAll();
    					eff.repaint();
        				
    					g.Unit_Panel[4].removeAll();
    					g.Unit_Panel[4].setVisible(false);
    					g.Unit_Panel[4].repaint();
    					g.Unit_Panel[4] = null;
    					
    					g.PlayerMoney += this.money;
    					g.TopLabel4.setText("화폐 : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("유닛5 사망");
    				}
        			if(g.UnitCount == 0)
					{
						g.PlayerLevel++;
						
						g.TopLabel2.setText("레벨 : "+ g.PlayerLevel);
					}
    				if(i == 76) // 못죽인 상태로 도착할 경우
    				{
    					File Clap1 =new File("deadunit.wav"); // 사운드
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[4].removeAll();
    					g.Unit_Panel[4].setVisible(false);
    					g.Unit_Panel[4].repaint();
    					g.Unit_Panel[4] = null;
    					
    					g.PlayerLife -= 1;
    					g.UnitCount = 0;
    					g.TopLabel1.setText("남은 생명 : " + g.PlayerLife);
    					System.out.println("유닛5 도착 -> 라이프 감소");
    				}
        			Thread.sleep(100);
        			}
        		}
        		g.stb.setEnabled(true);
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // 유닛 움직임
	{
		if(x == 76)
		{
			System.out.println("유닛5 도착");
		}
		else
		{
			g.Unit_Panel[4].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"유닛5 움직임");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("유닛5 체력 : "+ hp);
			g.Unit_Panel[4].repaint();
			g.Unit_Panel[4].setVisible(true);
		}
	}
	
	void Set_Unit() // 셋팅
	{
		g.Unit_Panel[4] = new JPanel();
		g.Unit_Panel[4].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[4] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[4] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[4] = new JLabel(new ImageIcon("./이미지파일/terrain/크기변환_monsterlvl3.gif"));
		}
		g.Unit_Label[4].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[4].add(g.Unit_Label[4]);
		g.RenderingPan.add(g.Unit_Panel[4], new Integer(a));
		g.Unit_Panel[4].setVisible(true);
		System.out.println("유닛5 생성");
		
	}
}
