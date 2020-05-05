package pack;

import java.awt.*;
import javax.swing.*;

/* ******************************************************
* ���α׷��� : Tower.java - Tower~Tower5 Ŭ����
* �ۼ��� : 2013041039 �̹μ�, 2016039083 ������
* ���� : ������(����), �����, �̹μ�, ������
* �ۼ��� : 2017.12.8
* ���α׷� ���� : Map_Panel�� Ÿ����ġ �� ��������� �� �ִ� ���α׷�
* ���� : 10�� ť��
********************************************************* */
class Tower extends JFrame
{ // Ÿ��1���� ������ ����Ǿ��ִ� Ŭ����
	GameManager g; // ���ӸŴ���
	String name; // Ÿ���̸�
	int cost; // Ÿ������
	int damage; // Ÿ�� ����
	int sellcost; // �ǸŰ���
	
	Tower(GameManager gm, String n, int c, int d, int s)
	{ // ������
		g = gm;
		name = n;
		cost = c;
		damage = d;
		sellcost = s;
	}
	
	void setInfo()
	{ // �����гο� ����ǥ��
		g.RightLabel1.setText("Ÿ�� �̸� : "+ name);
		g.RightLabel2.setText("Ÿ�� ���ݷ� : "+ damage);
		g.RightLabel3.setText("���� ���� : "+ cost);
		g.RightLabel4.setText("�Ǹ� ���� : "+ sellcost);
	}
	
	void DrawTower(int x, int y)
	{ // Ÿ���� JLayerd�� JButton�� �̿��Ͽ� ȭ�鿡 ǥ���ϴ� �۾�
		System.out.println("1��Ÿ�� ��ο� ��ġ "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./�̹�������/tower/ũ�⺯ȯ_lvl1towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // ���콺��ġ�κ��� 46*46ũ��� �� ũ�� ����
		g.jb[y/23][x/23].setContentAreaFilled(false); // ���ȭ�� �����ϰ�
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // ���� �տ� ������ �켱������ �ΰ� add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23;
		g.p1 = x/23;
		g.validate();
		g.repaint(); // ������Ʈ

		
		System.out.println("������Ʈ");
		System.out.println("1��Ÿ�� ��ġ�Ϸ�");
	}
	
}
// ���� Ŭ������ ���� Ŭ������ ������ ���� ��� ��. ���� �Ӽ��� ���ݾ� �ٸ���


class Tower2 extends Tower
{ // Ÿ��2���� ������ ����Ǿ��ִ� Ŭ����
	Tower2(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("2��Ÿ�� ��ο� ��ġ "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./�̹�������/tower/ũ�⺯ȯ_lvl2towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // ���콺��ġ�κ��� 46*46ũ��� �� ũ�� ����
		g.jb[y/23][x/23].setContentAreaFilled(false); // ���ȭ�� �����ϰ�
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // ���� �տ� ������ �켱������ �ΰ� add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // ������Ʈ
		
		System.out.println("������Ʈ");
		System.out.println("2��Ÿ�� ��ġ�Ϸ�");
	}
}

class Tower3 extends Tower
{ // Ÿ��3���� ������ ����Ǿ��ִ� Ŭ����
	Tower3(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("3��Ÿ�� ��ο� ��ġ "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./�̹�������/tower/ũ�⺯ȯ_lvl3towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // ���콺��ġ�κ��� 46*46ũ��� �� ũ�� ����
		g.jb[y/23][x/23].setContentAreaFilled(false); // ���ȭ�� �����ϰ�
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // ���� �տ� ������ �켱������ �ΰ� add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // ������Ʈ
		
		System.out.println("������Ʈ");
		System.out.println("3��Ÿ�� ��ġ�Ϸ�");
	}
}

class Tower4 extends Tower
{ // Ÿ��4���� ������ ����Ǿ��ִ� Ŭ����
	Tower4(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("4��Ÿ�� ��ο� ��ġ "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./�̹�������/tower/ũ�⺯ȯ_lvl4towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // ���콺��ġ�κ��� 46*46ũ��� �� ũ�� ����
		g.jb[y/23][x/23].setContentAreaFilled(false); // ���ȭ�� �����ϰ�
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // ���� �տ� ������ �켱������ �ΰ� add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // ������Ʈ
		
		System.out.println("������Ʈ");
		System.out.println("4��Ÿ�� ��ġ�Ϸ�");
	}
}

class Tower5 extends Tower
{ // Ÿ��5���� ������ ����Ǿ��ִ� Ŭ����
	Tower5(GameManager gm, String n, int c , int d, int s) {
		super(gm, n, c, d, s);
	}
	void DrawTower(int x, int y)
	{
		System.out.println("5��Ÿ�� ��ο� ��ġ "+x+" "+y);
		
		g.jb[y/23][x/23] = new JButton(new ImageIcon("./�̹�������/tower/ũ�⺯ȯ_lvl5towerani.gif"));
		g.jb[y/23][x/23].setBounds(x, y, 46, 46); // ���콺��ġ�κ��� 46*46ũ��� �� ũ�� ����
		g.jb[y/23][x/23].setContentAreaFilled(false); // ���ȭ�� �����ϰ�
		g.RenderingPan.add(g.jb[y/23][x/23], new Integer(300)); // ���� �տ� ������ �켱������ �ΰ� add
		g.jb[y/23][x/23].addActionListener(g);
		g.p2 = y/23; 
		g.p1 = x/23;
		g.validate();
		g.repaint(); // ������Ʈ
		
		System.out.println("������Ʈ");
		System.out.println("5��Ÿ�� ��ġ�Ϸ�");
	}
}

class Draw_Tower_Image extends JPanel 
{ // �̹����� �׷��ִ� Ŭ����
   private ImageIcon icon;
   private Image img;
   Draw_Tower_Image(String s)
   {
	   icon = new ImageIcon(s);
	   img = icon.getImage();
   }
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(img,46, 46, this); // �ش���ġ�� �׸��� �׷�����
   }
}


