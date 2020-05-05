package pack;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


/* ******************************************************
* ���α׷��� : Unit.java - Unit~Unit5 Ŭ����
* �ۼ��� : 2013041066 ������, 2013041049 �����
* ���� : ������(����), �����, �̹μ�, ������
* �ۼ��� : 2017.12.8
* ���α׷� ���� : ������ �����ǰ�, Ÿ���� ���� �������� �԰� ���ִ� ���α׷�
* ���� : 10�� ť��
********************************************************* */
public class Unit implements Runnable  {

	GameManager g;
	
	// �ʵ�
	int Unit_Map[][] = { // ������ ������ ��ġ��
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
	
	
	int a=700; // �⺻���� ���̴� 700
	int hp=300; // ����1 ������ ü���� 300
	int money = 10; // ����1 ������ �׾��� �� ��� ȭ�� 10
	
	Unit(GameManager gm, int k) // ������
	{
		g = gm; // GameManager ��ü �޾ƿ�
		Set_Unit(); // ���� ����
		a = k; // J���̾�� ����
		g.UnitCount++; // ����ī��Ʈ+1
	}
	
	public void run() // ������ ����
	{
        try {
        	g.stb.setEnabled(false);
        	System.out.println("����1 ����");
        		for(int i = 0 ; i < 77; i++) // ������ ���� �� ���� ��� �� ����
        		{
        			if(g.Unit_Panel[0] != null) // ������ ����ִٸ�
        			{
        				Move_Unit(i); // ������ ������(+��Ī)
        				
        				if(this.hp <= 0) // ������ hp�� �� ��� �׾��� ��
        				{
        					
        					File Clap1 =new File("deadunit.wav"); // ����
        					GameManager.PlaySound(Clap1);
        					// ���ֻ���� ���� ����Ʈ ���
        					JLabel eff = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_deadeff.png"));
        					eff.setBounds(Unit_Map[i][0], Unit_Map[i][1], 46, 46);
        					g.RenderingPan.add(eff, new Integer(2000));
        					
        					// ���ְ�ü ���� ����
        					g.Unit_Panel[0].removeAll();
        					g.Unit_Panel[0].setVisible(false);
        					g.Unit_Panel[0].repaint();
        					g.Unit_Panel[0] = null;
        					
        					// �ش� ������ ��ġ�� ����, �÷��̾� ȭ�� ���
        					g.PlayerMoney += this.money;
        					g.TopLabel4.setText("ȭ�� : "+ g.PlayerMoney);
        					
        					//������ �׾��⶧���� ī��Ʈ--
        					g.UnitCount--;
        					System.out.println("����1 ���");
        					
        					Thread.sleep(1000);
        					eff.setVisible(false);
        					eff.removeAll();
        					eff.repaint();
        				}
        				if(i == 76) // ������ ���·� ������ �������� ������ ���
        				{
        					File Clap1 =new File("deadunit.wav"); // ����
        					GameManager.PlaySound(Clap1);
        					// ���� ����
        					g.Unit_Panel[0].removeAll();
        					g.Unit_Panel[0].setVisible(false);
        					g.Unit_Panel[0].repaint();
        					g.Unit_Panel[0] = null;
        					
        					// �÷��̾� ������ ����
        					g.PlayerLife -= 1;
        					g.TopLabel1.setText("���� ���� : " + g.PlayerLife);
        					System.out.println("����1 ���� -> ������ ����");
        				}
        			Thread.sleep(100); // 0.1�ʸ��� ������
        			}
        		}
        		
        }catch(Exception e) { // ����Ȯ��
        	System.out.println(e);
        }
    }
	
	
	void Move_Unit(int x) // ���� ������
	{
		if(x == 76) // ������ ����Ʈ�� �����Ͽ��� ��� �޽��� ���
		{
			System.out.println("����1 ����");
		}
		else // ������ ����Ʈ�� �ƴҰ��
		{
			// ����(JPanel)�� ������ ����Ʈ�� ���� ������
			g.Unit_Panel[0].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"����1 ������");
			
			// ���� �ֺ��� Ÿ���� ���� ���, ������ ��ŭ ü�� ����
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("����1 ü�� : "+ hp);
			g.Unit_Panel[0].repaint();
			g.Unit_Panel[0].setVisible(true);
		}
	}
	
	void Set_Unit() // ���� ����
	{
		// ������ǥ JPanel�ϳ� �Ҵ�
		g.Unit_Panel[0] = new JPanel();
		g.Unit_Panel[0].setLayout(null);
		
		if(g.PlayerLevel == 1) // ����1�� ���
			g.Unit_Label[0] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl1.gif"));
		if(g.PlayerLevel == 2) // ����2�� ���
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[0] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3) // ����3�� ���
		{
			this.hp = this.hp+200;
			this.money = this.money+200;
			g.Unit_Label[0] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl3.gif"));
		}
		g.Unit_Label[0].setBounds(0, 0, 46, 46); // �� ũ������
		
		g.Unit_Panel[0].add(g.Unit_Label[0]);
		g.RenderingPan.add(g.Unit_Panel[0], new Integer(a)); // RenderingPan�� add
		g.Unit_Panel[0].setVisible(true);
		System.out.println("����1 ����");
		
	}
	
	int Detecting_Damage_Received(int x) // ������ �������鼭 ���ظ� ����
	{ //�� ��
		System.out.println("����Ʈ ��ġ��ġ : "+Unit_Map[x][1]/23+", " +Unit_Map[x][0]/23);
		
		// ������ ��ġ ���ؿ��� 8���� ��Ī, Ÿ��Ÿ��1,2,3,4,5�� ���� hp���ҷ��� �ٸ�
		if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 1 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 1 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 1 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 1 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 1 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 1 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 1 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 1) // ++
			{System.out.println("Ÿ��1 Ž��");return 5;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 2 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 2 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 2 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 2 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 2 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 2 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 2 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 2) // ++
			{System.out.println("Ÿ��2 Ž��");return 15;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 3 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 3 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 3 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 3 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 3 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 3 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 3 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 3) // ++
			{System.out.println("Ÿ��3 Ž��");return 25;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 4 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 4 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 4 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 4 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 4 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 4 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 4 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 4) // ++
			{System.out.println("Ÿ��4 Ž��");return 35;}
		else if(g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23-2] == 5 //--
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23-2] == 5 //0-
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23-2] == 5 //+-
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23] == 5 //-0
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23] == 5 //+0
				|| g.TowerPosition[Unit_Map[x][1]/23-2][Unit_Map[x][0]/23+2] == 5 //-+
				|| g.TowerPosition[Unit_Map[x][1]/23][Unit_Map[x][0]/23+2] == 5 //0+ 
				|| g.TowerPosition[Unit_Map[x][1]/23+2][Unit_Map[x][0]/23+2] == 5) // ++
			{System.out.println("Ÿ��5 Ž��");return 45;}
		else
			{System.out.println("�߰�x");return 0;}		
	}
}

//
// ���� Ŭ�������ʹ� UnitŬ�������� �г�,�� �ε���,���ڿ����� �ٲ�� ����, ������ ��� �����Ƿ� �ּ� ����
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
        	System.out.println("����2 ����");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[1] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // �׿��� ��
    				{
        				File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_deadeff.png"));
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
    					g.TopLabel4.setText("ȭ�� : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("����2 ���");
    				}
    				if(i == 76) // ������ ���·� ������ ���
    				{
    					File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[1].removeAll();
    					g.Unit_Panel[1].setVisible(false);
    					g.Unit_Panel[1].repaint();
    					g.Unit_Panel[1] = null;
    					
    					g.PlayerLife -= 1;
    					g.TopLabel1.setText("���� ���� : " + g.PlayerLife);
    					System.out.println("����2 ���� -> ������ ����");
    				}
        			Thread.sleep(100);
        			}
        		}
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // ���� ������
	{
		if(x == 76)
		{
			System.out.println("����2 ����");
		}
		else
		{
			g.Unit_Panel[1].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"����2 ������");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("����2 ü�� : "+ hp);
			g.Unit_Panel[1].repaint();
			g.Unit_Panel[1].setVisible(true);
		}
	}
	
	void Set_Unit() // ����
	{
		g.Unit_Panel[1] = new JPanel();
		g.Unit_Panel[1].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[1] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[1] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[1] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl3.gif"));
		}
		g.Unit_Label[1].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[1].add(g.Unit_Label[1]);
		g.RenderingPan.add(g.Unit_Panel[1], new Integer(a));
		g.Unit_Panel[1].setVisible(true);
		System.out.println("����2 ����");
		
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
        	System.out.println("����3 ����");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[2] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // �׿��� ��
    				{
        				File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_deadeff.png"));
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
    					g.TopLabel4.setText("ȭ�� : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("����3 ���");
    				}
    				if(i == 76) // ������ ���·� ������ ���
    				{
    					File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[2].removeAll();
    					g.Unit_Panel[2].setVisible(false);
    					g.Unit_Panel[2].repaint();
    					g.Unit_Panel[2] = null;
    					
    					g.PlayerLife -= 1;
    					g.TopLabel1.setText("���� ���� : " + g.PlayerLife);
    					System.out.println("����3 ���� -> ������ ����");
    				}
        			Thread.sleep(100);
        			}
        		}
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // ���� ������
	{
		if(x == 76)
		{
			System.out.println("����3 ����");
		}
		else
		{
			g.Unit_Panel[2].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"����3 ������");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("����3 ü�� : "+ hp);
			g.Unit_Panel[2].repaint();
			g.Unit_Panel[2].setVisible(true);
		}
	}
	
	void Set_Unit() // ����
	{
		g.Unit_Panel[2] = new JPanel();
		g.Unit_Panel[2].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[2] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[2] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[2] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl3.gif"));
		}
		g.Unit_Label[2].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[2].add(g.Unit_Label[2]);
		g.RenderingPan.add(g.Unit_Panel[2], new Integer(a));
		g.Unit_Panel[2].setVisible(true);
		System.out.println("����3 ����");
		
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
        	System.out.println("����4 ����");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[3] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // �׿��� ��
    				{
        				File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_deadeff.png"));
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
    					g.TopLabel4.setText("ȭ�� : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("����4 ���");
    				}
    				if(i == 76) // ������ ���·� ������ ���
    				{
    					File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[3].removeAll();
    					g.Unit_Panel[3].setVisible(false);
    					g.Unit_Panel[3].repaint();
    					g.Unit_Panel[3] = null;
    					
    					g.PlayerLife -= 1;
    					g.TopLabel1.setText("���� ���� : " + g.PlayerLife);
    					System.out.println("����4 ���� -> ������ ����");
    				}
        			Thread.sleep(100);
        			}
        		}
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // ���� ������
	{
		if(x == 76)
		{
			System.out.println("����4 ����");
		}
		else
		{
			g.Unit_Panel[3].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"����4 ������");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("����4 ü�� : "+ hp);
			g.Unit_Panel[3].repaint();
			g.Unit_Panel[3].setVisible(true);
		}
	}
	
	void Set_Unit() // ����
	{
		g.Unit_Panel[3] = new JPanel();
		g.Unit_Panel[3].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[3] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[3] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[3] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl3.gif"));
		}
		g.Unit_Label[3].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[3].add(g.Unit_Label[3]);
		g.RenderingPan.add(g.Unit_Panel[3], new Integer(a));
		g.Unit_Panel[3].setVisible(true);
		System.out.println("����4 ����");
		
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
        	System.out.println("����5 ����");
        		for(int i = 0 ; i < 77; i++)
        		{
        			if(g.Unit_Panel[4] != null)
        			{
        			Move_Unit(i);
        			if(this.hp <= 0) // �׿��� ��
    				{
        				File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
        				JLabel eff = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_deadeff.png"));
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
    					g.TopLabel4.setText("ȭ�� : "+ g.PlayerMoney);
    					g.UnitCount--;
    					System.out.println("����5 ���");
    				}
        			if(g.UnitCount == 0)
					{
						g.PlayerLevel++;
						
						g.TopLabel2.setText("���� : "+ g.PlayerLevel);
					}
    				if(i == 76) // ������ ���·� ������ ���
    				{
    					File Clap1 =new File("deadunit.wav"); // ����
    					GameManager.PlaySound(Clap1);
    					g.Unit_Panel[4].removeAll();
    					g.Unit_Panel[4].setVisible(false);
    					g.Unit_Panel[4].repaint();
    					g.Unit_Panel[4] = null;
    					
    					g.PlayerLife -= 1;
    					g.UnitCount = 0;
    					g.TopLabel1.setText("���� ���� : " + g.PlayerLife);
    					System.out.println("����5 ���� -> ������ ����");
    				}
        			Thread.sleep(100);
        			}
        		}
        		g.stb.setEnabled(true);
        }catch(Exception e) {
        	System.out.println(e);
        }
    }
	
	void Move_Unit(int x) // ���� ������
	{
		if(x == 76)
		{
			System.out.println("����5 ����");
		}
		else
		{
			g.Unit_Panel[4].setBounds(Unit_Map[x][0], Unit_Map[x][1], 46, 46);
			System.out.println(Unit_Map[x][0] + ", "+ Unit_Map[x][1] +"����5 ������");
			this.hp -= Detecting_Damage_Received(x);
			System.out.println("����5 ü�� : "+ hp);
			g.Unit_Panel[4].repaint();
			g.Unit_Panel[4].setVisible(true);
		}
	}
	
	void Set_Unit() // ����
	{
		g.Unit_Panel[4] = new JPanel();
		g.Unit_Panel[4].setLayout(null);
		
		if(g.PlayerLevel == 1)
			g.Unit_Label[4] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl1.gif"));
		if(g.PlayerLevel == 2)
		{
			this.hp = 500;
			this.money = 100;
			g.Unit_Label[4] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl2.gif"));
		}
		if(g.PlayerLevel >= 3)
		{
			this.hp += 200;
			this.money += 200;
			g.Unit_Label[4] = new JLabel(new ImageIcon("./�̹�������/terrain/ũ�⺯ȯ_monsterlvl3.gif"));
		}
		g.Unit_Label[4].setBounds(0, 0, 46, 46);
		
		g.Unit_Panel[4].add(g.Unit_Label[4]);
		g.RenderingPan.add(g.Unit_Panel[4], new Integer(a));
		g.Unit_Panel[4].setVisible(true);
		System.out.println("����5 ����");
		
	}
}
