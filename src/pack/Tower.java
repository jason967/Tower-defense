package pack;

import java.awt.*;
import javax.swing.*;

/* ******************************************************
* 프로그램명 : Tower.java - Tower~Tower5 클래스
* 작성자 : 2013041039 이민섭, 2016039083 강예지
* 조원 : 서재준(팀장), 최재웅, 이민섭, 강예지
* 작성일 : 2017.12.8
* 프로그램 설명 : Map_Panel에 타워설치 및 정보출력을 해 주는 프로그램
* 팀명 : 10조 큐빅
********************************************************* */
class Tower extends JFrame
{ // 타워1번의 정보가 저장되어있는 클래스
	GameManager g; // 게임매니저
	String name; // 타워이름
	int cost; // 타워가격
	int damage; // 타워 공격
	int sellcost; // 판매가격
	
	Tower(GameManager gm, String n, int c, int d, int s)
	{ // 생성자
		g = gm;
		name = n;
		cost = c;
		damage = d;
		sellcost = s;
	}
	
	void setInfo()
	{ // 우측패널에 정보표시
		g.RightLabel1.setText("타워 이름 : "+ name);
		g.RightLabel2.setText("타워 공격력 : "+ damage);
		g.RightLabel3.setText("구입 가격 : "+ cost);
		g.RightLabel4.setText("판매 가격 : "+ sellcost);
	}
	
	void DrawTower(int x, int y)
	{ // 타워를 JLayerd와 JButton을 이용하여 화면에 표시하는 작업
		System.out.println("1번타워 드로우 위치 "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./이미지파일/tower/크기변환_lvl1towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // 마우스위치로부터 46*46크기로 라벨 크기 조절
		g.jb[y/23][x/23].setContentAreaFilled(false); // 배경화면 투명하게
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // 가장 앞에 나오게 우선순위를 두고 add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23;
		g.p1 = x/23;
		g.validate();
		g.repaint(); // 업데이트

		
		System.out.println("리페인트");
		System.out.println("1번타워 설치완료");
	}
	
}
// 이하 클래스는 위의 클래스와 로직은 거의 비슷 함. 단지 속성이 조금씩 다를뿐


class Tower2 extends Tower
{ // 타워2번의 정보가 저장되어있는 클래스
	Tower2(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("2번타워 드로우 위치 "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./이미지파일/tower/크기변환_lvl2towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // 마우스위치로부터 46*46크기로 라벨 크기 조절
		g.jb[y/23][x/23].setContentAreaFilled(false); // 배경화면 투명하게
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // 가장 앞에 나오게 우선순위를 두고 add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // 업데이트
		
		System.out.println("리페인트");
		System.out.println("2번타워 설치완료");
	}
}

class Tower3 extends Tower
{ // 타워3번의 정보가 저장되어있는 클래스
	Tower3(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("3번타워 드로우 위치 "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./이미지파일/tower/크기변환_lvl3towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // 마우스위치로부터 46*46크기로 라벨 크기 조절
		g.jb[y/23][x/23].setContentAreaFilled(false); // 배경화면 투명하게
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // 가장 앞에 나오게 우선순위를 두고 add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // 업데이트
		
		System.out.println("리페인트");
		System.out.println("3번타워 설치완료");
	}
}

class Tower4 extends Tower
{ // 타워4번의 정보가 저장되어있는 클래스
	Tower4(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("4번타워 드로우 위치 "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./이미지파일/tower/크기변환_lvl4towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // 마우스위치로부터 46*46크기로 라벨 크기 조절
		g.jb[y/23][x/23].setContentAreaFilled(false); // 배경화면 투명하게
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // 가장 앞에 나오게 우선순위를 두고 add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // 업데이트
		
		System.out.println("리페인트");
		System.out.println("4번타워 설치완료");
	}
}

class Tower5 extends Tower
{ // 타워5번의 정보가 저장되어있는 클래스
	Tower5(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("5번타워 드로우 위치 "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./이미지파일/tower/크기변환_lvl5towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // 마우스위치로부터 46*46크기로 라벨 크기 조절
		g.jb[y/23][x/23].setContentAreaFilled(false); // 배경화면 투명하게
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // 가장 앞에 나오게 우선순위를 두고 add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // 업데이트
		
		System.out.println("리페인트");
		System.out.println("5번타워 설치완료");
	}
}

class Draw_Tower_Image extends JPanel 
{ // 이미지를 그려주는 클래스
   private ImageIcon icon;
   private Image img;
   Draw_Tower_Image(String s)
   {
	   icon = new ImageIcon(s);
	   img = icon.getImage();
   }
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(img,46, 46, this); // 해당위치에 그림을 그려넣음
   }
}


