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
* ���α׷��� : GameManager.java - GameManagerŬ����
* �ۼ��� : 2013041066 ������, 2013041049 �����
* ���� : ������(����), �����, �̹μ�, ������
* �ۼ��� : 2017.12.8
* ���α׷� ���� : ������ �ʿ�� �ϴ� ���(�г�, �̺�Ʈ)�� �����ϰ� ���� ���ִ� ���α׷�
* ���� : 10�� ť��
********************************************************* */
public class GameManager extends JFrame implements ActionListener,MouseListener, MouseMotionListener, Runnable{
	
	// �ʵ�
	
	// �г�
	public JPanel Start_panel; // ����ȭ��
	public JPanel Top_panel; // ž �г�
	public JPanel Right_panel; // ���� �г�
	public JPanel Map_panel; // ����� �г�
	public JPanel Under_panel; // Ÿ���̹��� ��� �г�
	public JButton btn1 = null; // ����ȭ�� �Ѿ �� JButton
	
	// ����г� - Ÿ����ư
	public JButton tower1btn = null; // Ÿ��1��ư
	public JButton tower2btn = null; // Ÿ��2��ư
	public JButton tower3btn = null; // Ÿ��3��ư
	public JButton tower4btn = null; // Ÿ��4��ư
	public JButton tower5btn = null; // Ÿ��5��ư
	
	// ž�г� - �÷��̾� ���� ��� ��
	public JLabel TopLabel1 = null; // ž�г� ��1
	public JLabel TopLabel2 = null; // ž�г� ��2
	public JLabel TopLabel3 = null; // ž�г� ��3
	public JLabel TopLabel4 = null; // ž�г� ��4
	public String PlayerName = null; // �÷��̾� �̸�
	public int PlayerLife = 10; // �÷��̾� ���
	public int PlayerMoney = 5000; // �÷��̾� ȭ��
	public int PlayerLevel = 1; // �÷��̾� ����
	
	// �����г� - Ÿ�� ���� ��� ��
	public JLabel RightLabel1 = null; // �������г� ��1
	public JLabel RightLabel2 = null; // �������г� ��2
	public JLabel RightLabel3 = null; // �������г� ��3
	public JLabel RightLabel4 = null; // �������г� ��4
	public JButton buytowerbtn = null; // Ÿ�����Ź�ư
	
	//���콺 ��������
	public JPanel mousepanel = null; // ���콺 ��������
	
	// Map_Panel ��� ���� �ʵ�
	JLayeredPane RenderingPan = null; // ���г�
	Rendering[][] land; // ��� ������ ���� ������Ŭ������ 2�����迭
	
	// Ÿ����ġ ���� �ʵ�
	public boolean Isit_buildable[][] = new boolean[22][32]; // Ÿ����ġ�����Ѱ�
	public int TowerPosition[][] = new int[22][32]; // ����ġ�� ���� Ÿ��Ÿ�� ����
	public Tower bufftower; // Ÿ����ü�� ���� �ӽ������1
	public Tower bufftower2; // Ÿ����ü�� ���� �ӽ������2
	public int TowerType = 0; // Ÿ�� Ÿ��
	public JButton jb[][]= new JButton[22][32]; // Ÿ���� �̹����� �ö� JButton
	
	// ���� ���� �ʵ�
	public JPanel Unit_Panel[] = new JPanel[5]; // �������
	public JLabel Unit_Label[] = new JLabel[5]; // �������
	public int UnitCount = 0; // ���� ī����
	
	public JButton stb = null; // ���ӽ��� ��ư
	
	
	public int p2=0,p1=0; // ��ǥ����
	
	
	// ����
	public static void main(String[] args) {
		GameManager g1 = new GameManager(); // ���ξ����� ����
		Thread t1 = new Thread(g1);
		t1.start();
	}
	
	@Override
	public void run() { // ���� ������ ����
		GameManager frame = new GameManager(); // ������ ���
		frame.setVisible(true);
		File Clap =new File("bgm.wav"); // ����
		PlaySound(Clap);
	}	

	// ������
	public GameManager() {
		
		setTitle("ť�� Ÿ�����潺"); // Ÿ��Ʋ ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x���� �� â�� ����
		setSize(1040,805); // ������ ũ�� ����
		
		Draw_Image di = new Draw_Image("./�̹�������/uiframe/introbg.png");
		setContentPane(di); // ����̹��� ����
		getContentPane().setLayout(null); // ������ǥ�� ȭ���� ����

		setStart_Panel(); // �г� ��� ����
		
	}
	static void PlaySound(File Sound) // ����
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
	{ // �̹����� �׷��ִ� Ŭ����
	   private ImageIcon icon;
	   private Image img;
	   Draw_Image(String s)
	   {
		   icon = new ImageIcon(s);
		   img = icon.getImage();
	   }
	   public void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      g.drawImage(img, 0, 0, this); // �ش���ġ�� �׸��� �׷�����
	   }
	}
	
	///////////////////////////�г� ���� ����//////////////////////////////
	
	void setStart_Panel() // ���� �г�
	{
		Start_panel = new JPanel(); // ����ȭ��
		Start_panel.setBounds(0, 0, 1040, 805); // ����ϳ� �� �� ũ�� ����
		Start_panel.setLayout(null); // ������ǥ�� ����
		
		
		btn1 = new JButton(""); // ���ӽ��� ��ư (����)
		btn1.setBounds(330, 610, 359, 60);
		btn1.setBackground(Color.white);
		getContentPane().add(btn1); // ���� �����ӿ� ��ư�ޱ�
		btn1.setOpaque(false);//�����ϰ�
		
		btn1.addActionListener(this); // �׼Ǹ����� �ޱ�
		
	}

	void setTop_Panel()
	{ // �������� ���� ���� ��ġ�ϴ� �г� (�÷��̾��� ������ ���)
		Top_panel = new JPanel(); // ���� (20,30,980,50)
		Top_panel.setBounds(40, 40, 950, 50); // ũ������
		Top_panel.setLayout(null); // ������ǥ ����
	      
	    TopLabel1 = new JLabel("���� ���� : " + this.PlayerLife); // ��1
	    TopLabel1.setForeground(Color.WHITE);
	    TopLabel1.setBounds(30, 15, 130, 30);
	    Top_panel.add(TopLabel1);
	      
	    TopLabel2 = new JLabel("���� : " + this.PlayerLevel); // ��2
	    TopLabel2.setForeground(Color.WHITE);
	    TopLabel2.setBounds(230, 15, 330, 30);
	    Top_panel.add(TopLabel2);
	    
	    TopLabel3 = new JLabel("�̸� : "+ this.PlayerName); // ��3
	    TopLabel3.setForeground(Color.WHITE);
	    TopLabel3.setBounds(430, 15, 530, 30);
	    Top_panel.add(TopLabel3);
	    
	    TopLabel4 = new JLabel("ȭ�� : "+ this.PlayerMoney); // ��4
	    TopLabel4.setForeground(Color.WHITE);
	    TopLabel4.setBounds(630, 15, 530, 30);
	    Top_panel.add(TopLabel4);
	    
	    stb = new JButton();
	    stb.setText("Start_Unit");
	    stb.setBounds(760, 15, 100, 30);
	    stb.addActionListener(this);
	    Top_panel.add(stb);
	    
	    Top_panel.setOpaque(false); // �г� ����� �����ϰ�
	    getContentPane().add(Top_panel); // �����ӿ� �г� �ޱ�
	}
	
	
	void setRight_Panel()
	{ // �������� ���� ������ ��ġ�ϴ� �г� (Ÿ���� ������ ���)
		Right_panel = new JPanel(); // ����
		Right_panel.setBounds(800, 120, 190, 500); // ũ������
		Right_panel.setLayout(null); // ������ǥ ����
		
		RightLabel1 = new JLabel("Ÿ�� �̸�"); // ��1
		RightLabel1.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 15));
		RightLabel1.setBounds(10, 70, 150, 80);
		Right_panel.add(RightLabel1);
		
		RightLabel2 = new JLabel("Ÿ�� ����"); // ��2
		RightLabel2.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 15));
		RightLabel2.setBounds(10, 130, 150, 80);
		Right_panel.add(RightLabel2);
		
		RightLabel3 = new JLabel("���� ����"); // ��3
		RightLabel3.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 15));
		RightLabel3.setBounds(10, 190, 150, 80);
		Right_panel.add(RightLabel3);
		
		RightLabel4 = new JLabel("�Ǹ� ����"); // ��3
		RightLabel4.setFont(new Font("�������", Font.BOLD | Font.ITALIC, 15));
		RightLabel4.setBounds(10, 250, 150, 80);
		Right_panel.add(RightLabel4);
		
		buytowerbtn = new JButton("Buy towers on click"); // ��4
		buytowerbtn.setBounds(10, 375, 160, 50);
		buytowerbtn.addActionListener(this);
		Right_panel.add(buytowerbtn);
		
	    	
		Right_panel.setOpaque(false); // �г� ����� �����ϰ�
	    getContentPane().add(Right_panel); // �����ӿ� �г� �ޱ�
	}
	
	void setUnder_Panel()
	{ // �������� ���� �ϴܿ� ��ġ�ϴ� �г� (Ÿ���̹����� �ִ� ��ư�� ���)
		Under_panel = new JPanel(); // ����
		Under_panel.setBounds(40, 640, 735, 95); // ũ������
		Under_panel.setLayout(null); // ������ǥ ����
		
		tower1btn = new JButton(); // ��ư���� �� �̹�������
		tower1btn.setContentAreaFilled(false); // �����ϰ�
		tower1btn.setBounds(90, 8, 85, 85); // ��ġ,ũ�� ����
		tower1btn.addActionListener(this); // �׼Ǹ����� �ޱ�
		Under_panel.add(tower1btn); // JButton�� �����ο� �޾��ֱ�
		
		tower2btn = new JButton(); // ���� ����
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
	    	
		Under_panel.setOpaque(false); // �гι���� �����ϰ�
	    getContentPane().add(Under_panel); // �����ӿ� ������ �޾���
	}
	
	void setMap_Panel()
	{ // ����~���ͱ��� �����ϴ� �гην�, ���� �����Ͽ� ����ϴ� �г�
		
		
		//11*16 ������
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
		
		
		Map_panel = new JPanel(); // JPanel ���� ����
		Map_panel.setBounds(40, 120, 736, 506); // ũ�� ����

		Map_panel.setLayout(null); // ������ǥ ����
		Map_panel.setOpaque(false); // ��� �����ϰ�
	   
	    
		RenderingPan = new JLayeredPane(); 
		// �г����� ������ ���� ���̱� ������ ���̷��̾������ ����Ѵ�. ���������� �̰��� ������ 
		RenderingPan.setBounds(0, 0, 736, 506);
	    	
		
		land = new Rendering[11][16]; // 2�����迭�� ��ũ�⸸ŭ �Ҵ�
	    	
	    for(int i=0;i<11;i++) // x��11��ŭ
	       for(int j=0;j<16;j++) // y��16��ŭ �ݺ�
	       {
	          if (map[i][j] == 0) // �ش� ��ġ�� �������� 0 �̶��
	          {
	             land[i][j] = new Rendering(j*46, i*46, 0); // Rendering��ü�� �ش���ġ�� Ÿ���� ����
	             Isit_buildable[i*2][j*2] = true;
	             Isit_buildable[i*2][j*2 +1] = true;
	             Isit_buildable[i*2 +1][j*2] = true;
	             Isit_buildable[i*2 +1][j*2 +1] = true;
	          }
	          else if (map[i][j] == 1){ // �ش� ��ġ�� �������� 1�϶�
	             land[i][j] = new Rendering(j*46, i*46, 1);   
	             
	          }else if (map[i][j] == 2){ // �ش� ��ġ�� �������� 2�϶�
	             land[i][j] = new Rendering(j*46, i*46, 2);   
	             
	          }
	          else 
	          { // �׷��� �ʴٸ� ��� 3
	             land[i][j] = new Rendering(j*46, i*46, 3);
	             
	          }
	          land[i][j].setBounds(j*46, i*46, 46, 46); // �ϳ��� ��ü�� ��½�Ų ���� ũ�⸦ ����
	          RenderingPan.add(land[i][j], new Integer(10)); // ���̰� 10�� ���� �ش� ũ���� �̹����� ���
	       }
	    Map_panel.add(RenderingPan); // ���̰�10�ξֵ��� �߰��� ���̾������ ���гο� �߰���
	    
		getContentPane().add(Map_panel); // map_panel�� �ٸ���� ���������ӿ� map_panel�߰�
	}
	
	////////////////////////////���콺 ���̾ƿ�////////////////////
	public void clickpanel() // ���콺 �ؿ� JPanel�� �޾Ƽ�, ���콺�� �����̸� �ؿ� �г��� ���콺�ؿ��� ���� ���� ������
	{
		mousepanel = new JPanel(); // ���콺�г� ����
		mousepanel.setBounds(0,0,46,46); // 46*46 ũ��� ����
		RenderingPan.add(mousepanel, new Integer(50)); // 50�ǿ켱������ ����ä�� JLayerd�� add
		mousepanel.setVisible(false); // ������ �ʰ�
		RenderingPan.addMouseListener(this); // ���콺������ add
		RenderingPan.addMouseMotionListener(this); // ���콺��Ǹ����� add
	}

	////////////////////////���콺 �̺�Ʈ ����//////////////////////////
	@Override
	public void mouseDragged(MouseEvent arg0) {} // �ʿ���±��
	public void mouseReleased(MouseEvent arg0) {} // �ʿ���±��
	public void mouseClicked(MouseEvent arg0) {} // �ʿ���±��
	
	public void mouseEntered(MouseEvent arg0) 
	{ // ���콺�� Map_Panel���� ����ٰ� �ٽõ��°��
		mousepanel.setVisible(true); // �����־����
	}
	public void mouseExited(MouseEvent arg0) 
	{ // ���콺�� Map_Panel������ ������ ���
		mousepanel.setVisible(false); // �������� �ʾƾ���. �ش��гξȿ����� ��������
	}
	
	public void mouseMoved(MouseEvent arg0) { // ���콺�� �����ϰ��
		int x = arg0.getX()-arg0.getX()%23; // ���� ���콺������ ��ġ(x,y)ĳġ
		int y = arg0.getY()-arg0.getY()%23; // x,y��ġ���� ������������ �Ѱ������� �� �»����� ��ġ�� �˾Ƴ�
		mousepanel.setBounds(x, y, 46 ,46); // �ش� ��ġ�� 46*46���� ũ�����
		
		if(bufftower != null) // Ÿ����ü�� �����ִ� ��Ȳ�̶��
		{
			int px = arg0.getX()/23;
			int py = arg0.getY()/23;
			
			if (Isit_buildable[py][px] && Isit_buildable[py][px+1] && Isit_buildable[py+1][px] && Isit_buildable[py+1][px+1])
				mousepanel.setBackground(Color.white); // ��ġ���� �ϴٸ� ������� ǥ��
			else
				mousepanel.setBackground(Color.red); // ��ġ �Ұ��� �ϴٸ� ���������� ǥ��
		}
		else // Ÿ����ü�� �����ִ� ��Ȳ�� �ƴ϶��
			mousepanel.setVisible(false); // ���콺�ؿ� �ߴ� �г��� �������ʰ���
		
	}

	public void mousePressed(MouseEvent arg0) { // ���콺��ư�� ����������
		int px = arg0.getX()/23; // �� ��ĭ�� 46*46�̱⶧���� ��ĭ�� ������ ��ƾ��ؼ� 23���� ����
		int py = arg0.getY()/23;
		
		if(bufftower != null)
		{
			if (Isit_buildable[py][px] && Isit_buildable[py][px+1] && Isit_buildable[py+1][px] && Isit_buildable[py+1][px+1])
			{
				int x= arg0.getX() - arg0.getX()%23; // x,y��ġ���� ������������ �Ѱ������� �� �»����� ��ġ�� �˾Ƴ�
				int y = arg0.getY() - arg0.getY()%23;
				System.out.println(x +" "+y );
			
				bufftower.DrawTower(x,y); // �ش���ġ�� ������ Ÿ���� ��ġ
				if(this.TowerType == 1) // Ÿ��Ÿ�Կ� ���� ȭ�󰨼ҷ� ���̰� ����, ȭ�����
				{
					this.PlayerMoney -= 50;
					this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
				}
				if(this.TowerType == 2)
				{
					this.PlayerMoney -= 100;
					this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
				}
				if(this.TowerType == 3)
				{
					this.PlayerMoney -= 150;
					this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
				}
				if(this.TowerType == 4)
				{
					this.PlayerMoney -= 200;
					this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
				}
				if(this.TowerType == 5)
				{
					this.PlayerMoney -= 250;
					this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
				}
				mousepanel.setVisible(false); // ��ġ�� �Ǿ����� ���콺�ؿ� �ִ� �г� ���߱�
				File Clap =new File("dispersionpistol.wav"); // ����
				PlaySound(Clap);
				// ��ġ���Ǹ� �ش� �ش� ������ ��ġ�Ұ������� ����
				Isit_buildable[py][px] = false;
				Isit_buildable[py][px+1] = false;
				Isit_buildable[py+1][px] = false;
				Isit_buildable[py+1][px+1] = false;	
				
				
				// �ش� ����ġ�� Ÿ��Ÿ��(1~5) ���� -> ���� ��ġ
				TowerPosition[py][px] =  TowerType;
				TowerPosition[py][px+1] =  TowerType;
				TowerPosition[py+1][px] =  TowerType;
				TowerPosition[py+1][px+1] =  TowerType;
				
				System.out.println("Ÿ����ġ : " + py +", " + px + "Ÿ��Ÿ�� : "+TowerType);
				
				bufftower = null; // Ÿ����ü�� �����ϴ� �ӽ�����Ҹ� �����
			}
			else // ��ġ �ҰԾƴϸ�
			{
				mousepanel.setVisible(true); // �гκ�����
			}
		}
	}
	
	
	
	//////////////////////////�׼Ǹ�����/////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) { // �׼Ǹ�����
		System.out.println("Ŭ���̺�Ʈ�߻�");
		//Mousclik
		File Clap =new File("Mousclik.wav"); // ����
		PlaySound(Clap);
		 if ( e.getSource() == btn1 ) // �����гο��� "GAME START" Ŭ�� ��
		    {
			 
			 String buff = JOptionPane.showInputDialog("Player �̸��� �Է��ϼ���!!","Cuvic");
			 if(buff == null) this.PlayerName = "10�� Cuvic";
			 else this.PlayerName = buff;
			 
			 getContentPane().removeAll(); // ��� �����
			 Draw_Image di = new Draw_Image("./�̹�������/uiframe/mainbg.png");
			 setContentPane(di); // �����̹��� ����
			 getContentPane().setLayout(null); // ������ǥ ����
				
			 setTop_Panel(); // ž�г� ���
			 setRight_Panel(); // �������г� ���
			 setMap_Panel(); // ���г� ���
			 setUnder_Panel(); // ����г� ���
			 this.TopLabel3.setText("�̸� : "+ this.PlayerName);
			 revalidate();
			 repaint(); // ������Ʈ
			 
			// ���� ����, Ŭ���� üũ
			 CheckGame cg = new CheckGame(this);
			 Thread Tcg = new Thread(cg);
			 Tcg.start();
			 
		    }
		 else if ( e.getSource() == buytowerbtn) // �����г��� ���Ź�ư�� ������ ���
		 {
			 
			if(TowerType == 1) // Ÿ��Ÿ���� 1�� ��� 1��Ÿ�� ��ü ����
			{
				bufftower = new Tower(this, "1��Ÿ��", 50, 5, 25);
			}
			if(TowerType == 2) // Ÿ��Ÿ���� 2�� ��� 2��Ÿ�� ��ü ����
			{
				bufftower = new Tower2(this, "2��Ÿ��", 100, 15,50);
			}
			if(TowerType == 3) // Ÿ��Ÿ���� 3�� ��� 3��Ÿ�� ��ü ����
			{
				bufftower = new Tower3(this, "3��Ÿ��", 150, 25,75);
			}
			if(TowerType == 4) // Ÿ��Ÿ���� 4�� ��� 4��Ÿ�� ��ü ����
			{
				bufftower = new Tower4(this, "4��Ÿ��", 200, 35,100);
			}
			if(TowerType == 5) // Ÿ��Ÿ���� 5�� ��� 5��Ÿ�� ��ü ����
			{
				bufftower = new Tower5(this, "5��Ÿ��", 250, 45,125);
			}
			
			clickpanel(); // ���콺 ���̾ƿ� ����
			 
		 }
		 else if ( e.getSource() == tower1btn) // �ϴ��г��� 1��Ÿ���� Ŭ������ ���
		 {
			 TowerType = 1; // Ÿ��Ÿ���� 1�� ����
			 new Tower(this, "1��Ÿ��", 50, 5, 25).setInfo(); // 1��Ÿ���� ���� ���
		 }
		 else if ( e.getSource() == tower2btn) // �ϴ��г��� 2��Ÿ���� Ŭ������ ���
		 {
			 TowerType = 2; // Ÿ��Ÿ���� 2�� ����
			 new Tower(this, "2��Ÿ��", 100, 15, 50).setInfo(); // 2��Ÿ���� ���� ���
		 }
		 else if ( e.getSource() == tower3btn) // �ϴ��г��� 3��Ÿ���� Ŭ������ ���
		 {
			 TowerType = 3; // Ÿ��Ÿ���� 3���� ����
			 new Tower(this, "3��Ÿ��", 150, 25, 75).setInfo(); // 3��Ÿ���� ���� ���
		 }
		 else if ( e.getSource() == tower4btn) // �ϴ��г��� 4��Ÿ���� Ŭ������ ���
		 {
			 TowerType = 4; // Ÿ��Ÿ���� 4�� ����
			 new Tower(this, "4��Ÿ��", 200, 35, 100).setInfo(); // 4��Ÿ���� ���� ���
		 }
		 else if ( e.getSource() == tower5btn) // �ϴ��г��� 5��Ÿ���� Ŭ������ ���
		 {
			 TowerType = 5; // Ÿ��Ÿ���� 5�� ����
			 new Tower(this, "5��Ÿ��", 250, 45, 125).setInfo(); // 5��Ÿ���� �������
		 }
		 else if ( e.getSource() == stb) // ���ӽ��۹�ư�� ������ ���
		 {
			 // ���� ��ü ����
			 Unit un1 = new Unit(this, 700);
			 Unit2 un2 = new Unit2(this, 800);
			 Unit3 un3 = new Unit3(this, 900);
			 Unit4 un4 = new Unit4(this, 1000);
			 Unit5 un5 = new Unit5(this, 1100);
				 
			 // �� ���ָ��� ������ ����
			 Thread unst1 = new Thread(un1);
			 Thread unst2 = new Thread(un2);
			 Thread unst3 = new Thread(un3);
			 Thread unst4 = new Thread(un4);
			 Thread unst5 = new Thread(un5);

			 File Clap1 =new File("oldhorn.wav"); // ����
			 PlaySound(Clap1);
			 // ������ ����
			 unst1.start();
			 unst2.start();
			 unst3.start();
			 unst4.start();
			 unst5.start();
			 
			 
		 }
		 else if ( e.getID() == 1001 ) // Ÿ�� JButton
		 {
			 System.out.println("1001-> " +p2 + ", "+ p1);
			 
			 // ��ü�� �޾ƿͼ� ��ū Ȯ�� -> x,y ��ǥ ã�Ƴ��� ��
			 String buff = e.getSource().toString();
			 StringTokenizer st = new StringTokenizer(buff, ",");
			 st.nextToken(); 
			 // �޾ƿ� ��ǥ ������
			 this.p1 = Integer.parseInt(st.nextToken())/23;
			 this.p2 = Integer.parseInt(st.nextToken())/23;
			 
			 
			 // �޾ƿ� ��ǥ���ִ� ��ư�� Ÿ�������� ���� �Ǹ�,���׷��̵� �����ϰ� �� 
			 if( (TowerPosition[p2][p1] == 1) && (TowerPosition[p2+1][p1] == 1) 
					 && (TowerPosition[p2][p1+1] == 1)&& (TowerPosition[p2+1][p1+1] == 1))
			 {
				// �Ǹ� , ���׷��̵�
				 String[] buttons = {"Sell", "Upgrade", "���"};
				 int result = JOptionPane.showOptionDialog(null, "Ÿ��1 �Ǹ� ���� : 25��\nŸ��1 => Ÿ��2 ���׷��̵� ��� : 50.", "Ÿ��1",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
				 if(result == JOptionPane.YES_OPTION)
					 {
					 	this.SellTower(25);
					 	File Clap1 =new File("coins.wav"); // ����
					 	PlaySound(Clap1);
					 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(50);
					 File Clap1 =new File("upgrade.wav"); // ����
					 PlaySound(Clap1);
				 }

			 }
			 if( (TowerPosition[p2][p1] == 2) && (TowerPosition[p2+1][p1] == 2) 
					 && (TowerPosition[p2][p1+1] == 2)&& (TowerPosition[p2+1][p1+1] == 2))
			 {
				// �Ǹ� , ���׷��̵�
				 String[] buttons = {"Sell", "Upgrade", "���"};
				 int result = JOptionPane.showOptionDialog(null, "Ÿ��2 �Ǹ� ���� : 50��\nŸ��2 => Ÿ��3 ���׷��̵� ��� : 100.", "Ÿ��2",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
				 if(result == JOptionPane.YES_OPTION)
				 {
					 this.SellTower(50);
					 File Clap1 =new File("coins.wav"); // ����
					 PlaySound(Clap1);
				 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(100);
					 File Clap1 =new File("upgrade.wav"); // ����
					 PlaySound(Clap1);
				 }
			 }
			 if( (TowerPosition[p2][p1] == 3) && (TowerPosition[p2+1][p1] == 3) 
					 && (TowerPosition[p2][p1+1] == 3)&& (TowerPosition[p2+1][p1+1] == 3))
			 {
				// �Ǹ� , ���׷��̵�
				 String[] buttons = {"Sell", "Upgrade", "���"};
				 int result = JOptionPane.showOptionDialog(null, "Ÿ��3 �Ǹ� ���� : 75��\nŸ��3 => Ÿ��4 ���׷��̵� ��� : 150.", "Ÿ��3",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
				 if(result == JOptionPane.YES_OPTION)
				 {
					 this.SellTower(75);
					 File Clap1 =new File("coins.wav"); // ����
					 PlaySound(Clap1);
				 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(150);
					 File Clap1 =new File("upgrade.wav"); // ����
					 PlaySound(Clap1);
				 }
			 }
			 if( (TowerPosition[p2][p1] == 4) && (TowerPosition[p2+1][p1] == 4) 
					 && (TowerPosition[p2][p1+1] == 4)&& (TowerPosition[p2+1][p1+1] == 4))
			 {
				// �Ǹ� , ���׷��̵�
				 String[] buttons = {"Sell", "Upgrade", "���"};
				 int result = JOptionPane.showOptionDialog(null, "Ÿ��4 �Ǹ� ���� : 100��\nŸ��4 => Ÿ��5 ���׷��̵� ��� : 200.", "Ÿ��4",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
				 if(result == JOptionPane.YES_OPTION)
				 {
					 this.SellTower(100);
					 File Clap1 =new File("coins.wav"); // ����
					 PlaySound(Clap1);
				 }
				 else if(result == JOptionPane.NO_OPTION)
				 {
					 this.UpgradTower(200);
					 File Clap1 =new File("upgrade.wav"); // ����
					 PlaySound(Clap1);
				 };
			 }
			 if( (TowerPosition[p2][p1] == 5) && (TowerPosition[p2+1][p1] == 5) 
					 && (TowerPosition[p2][p1+1] == 5)&& (TowerPosition[p2+1][p1+1] == 5))
			 {
				// �Ǹ� , ���׷��̵�
				 String[] buttons = {"Sell", "Upgrade", "���"};
				 int result = JOptionPane.showOptionDialog(null, "Ÿ��5 �Ǹ� ���� : 125��\n���̻� Ÿ�����׷��̵带 �� �� �����ϴ�..", "Ÿ��5",
						 JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "�ι�°��");
				 if(result == JOptionPane.YES_OPTION)
				 {	 
					 this.SellTower(125);
					 File Clap1 =new File("coins.wav"); // ����
					 PlaySound(Clap1);
				 }
			 }
		 }
        
	}
	
	void SellTower(int sellcost) // Ÿ�� �Ǹ�
	{
		System.out.println("Ÿ�� ����, ���� ȭ�� : " + sellcost);
		// ��ġ���� -> ��ġ����
		Isit_buildable[p2][p1] = true;
		Isit_buildable[p2][p1+1] = true;
		Isit_buildable[p2+1][p1] = true;
		Isit_buildable[p2+1][p1+1] = true;	
		
		// Ÿ�� Ÿ�� ����
		TowerPosition[p2][p1] =  0;
		TowerPosition[p2][p1+1] =  0;
		TowerPosition[p2+1][p1] =  0;
		TowerPosition[p2+1][p1+1] =  0;
		
		// �������гο��� �ش��ư ���� �� ����
		RenderingPan.remove(jb[p2][p1]);
		RenderingPan.revalidate();
		RenderingPan.repaint();
		jb[p2][p1] = null; // �ΰ� �־���
		
		// �ǸŰ��ݸ�ŭ �÷��̾� ȭ�� ����
		this.PlayerMoney += sellcost;
		this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
	}
	
	void UpgradTower(int Ucost) // Ÿ�� ���׷��̵�
	{
		int type = this.TowerPosition[p2][p1];
		System.out.println("���׷��̵� �� Ÿ�� ���?: "+type);
		
		if(type == 1) // ���� 1�̸� 2�� ���׷��̵�
		{
			this.SellTower(0); // �����
			System.out.println("Ÿ��1->2�� ���׷��̵�, �Ҹ� ȭ�� : " + Ucost);
			bufftower = new Tower2(this, "2��Ÿ��", 100, 15,50);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// ��ġ���Ǹ� �ش� �ش� ������ ��ġ�Ұ������� ����
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// �ش� ����ġ�� Ÿ��Ÿ��(1~5) ���� -> ���� ��ġ
			TowerPosition[p2][p1] =  2;
			TowerPosition[p2][p1+1] =  2;
			TowerPosition[p2+1][p1] =  2;
			TowerPosition[p2+1][p1+1] =  2;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
		}
		if(type == 2) // ���� 2�� 3���� ���׷��̵�
		{
			this.SellTower(0); // �����
			System.out.println("Ÿ��2->3���� ���׷��̵�, �Ҹ� ȭ�� : " + Ucost);
			bufftower = new Tower3(this, "3��Ÿ��", 150, 25,75);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// ��ġ���Ǹ� �ش� �ش� ������ ��ġ�Ұ������� ����
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// �ش� ����ġ�� Ÿ��Ÿ��(1~5) ���� -> ���� ��ġ
			TowerPosition[p2][p1] =  3;
			TowerPosition[p2][p1+1] =  3;
			TowerPosition[p2+1][p1] =  3;
			TowerPosition[p2+1][p1+1] =  3;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
		}
		if(type == 3)
		{
			this.SellTower(0); // �����
			System.out.println("Ÿ��3->4�� ���׷��̵�, �Ҹ� ȭ�� : " + Ucost);
			bufftower = new Tower4(this, "4��Ÿ��", 200, 35,100);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// ��ġ���Ǹ� �ش� �ش� ������ ��ġ�Ұ������� ����
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// �ش� ����ġ�� Ÿ��Ÿ��(1~5) ���� -> ���� ��ġ
			TowerPosition[p2][p1] =  4;
			TowerPosition[p2][p1+1] =  4;
			TowerPosition[p2+1][p1] =  4;
			TowerPosition[p2+1][p1+1] =  4;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
		}
		if(type == 4)
		{
			this.SellTower(0); // �����
			System.out.println("Ÿ��4->5�� ���׷��̵�, �Ҹ� ȭ�� : " + Ucost);
			bufftower = new Tower5(this, "5��Ÿ��", 250, 45, 125);
			bufftower.DrawTower(p1*23, p2*23);
			
			bufftower = null;
			
			// ��ġ���Ǹ� �ش� �ش� ������ ��ġ�Ұ������� ����
			Isit_buildable[p2][p1] = false;
			Isit_buildable[p2][p1+1] = false;
			Isit_buildable[p2+1][p1] = false;
			Isit_buildable[p2+1][p1+1] = false;	
			
			
			// �ش� ����ġ�� Ÿ��Ÿ��(1~5) ���� -> ���� ��ġ
			TowerPosition[p2][p1] =  5;
			TowerPosition[p2][p1+1] =  5;
			TowerPosition[p2+1][p1] =  5;
			TowerPosition[p2+1][p1+1] =  5;
			
			this.PlayerMoney += -Ucost;
			this.TopLabel4.setText("ȭ�� : "+ this.PlayerMoney);
		}
	}
}


/* ******************************************************
* ���α׷��� : GameManager.java - RenderingŬ����
* �ۼ��� : 2013041066 ������, 2013041049 �����
* ���� : ������(����), �����, �̹μ�, ������
* �ۼ��� : 2017.12.1
* ���α׷� ���� : Map_Panel�� ���� ���ִ� ���α׷�
* ���� : 10�� ť��
********************************************************* */
class Rendering extends JPanel
{ // ���������� ������ �޾ƿ� ��� ���ִ� Ŭ����
   public int x; // ������ǥ���� x
   public int y; // ������ǥ���� y
   public int type = 0; // ���� �Ӽ� �� �ϳ�
   Image img; // �̹���
   
   
   Rendering(int x, int y , int t) // ������
   {
	  // �޾ƿ� ��ǥ�� ����
	   this.x = x;
	   this.y = y;
	   type = t;
      
	   Toolkit tk = Toolkit.getDefaultToolkit(); //�̹����� �޾ƿ� toolkit ��ü����
	   try{
		   if ( t == 1) 
		   { // Ÿ���� 1�϶� �� ���
			   img = tk.getImage("./�̹�������/terrain/road.png");
		   }
		   else if (t == 0) 
		   { // Ÿ���� 0�϶� Ǯ ���
			   img = tk.getImage("./�̹�������/terrain/grass.png");
		   }
		   else if (t==3)
		   { // Ÿ���� 3�϶� ���� ���
			   img = tk.getImage("./�̹�������/terrain/tree.png");
		   }
		   else if (t == 2) 
		   { // Ÿ���� 2�϶� �� ���
			   img = tk.getImage("./�̹�������/terrain/water.png");
		   }
	   }catch(Exception e){
    	  System.out.println(e);
	   }           
   	}
   
   public void paintComponent(Graphics g) // �ش���ġ�� 46*46ũ�⸸ŭ �̹����� �׷���
   {
      g.drawImage(img,0,0,46,46,this);         
   }
}
