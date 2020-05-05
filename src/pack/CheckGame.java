package pack;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/* ******************************************************
* ���α׷��� : CheckGame.java - CheckGameŬ����
* �ۼ��� : 2013041039 �̹μ�, 2016039083 ������
* ���� : ������(����), �����, �̹μ�, ������
* �ۼ��� : 2017.12.8
* ���α׷� ���� : ���� Ŭ����, ���и� �Ǵ��Ͽ� ������ִ� ���α׷�
* ���� : 10�� ť��
********************************************************* */
public class CheckGame extends JFrame implements Runnable {

	GameManager g;
	
	CheckGame(GameManager gm)
	{
		g = gm;
	}
	
	@Override
	public void run() {
		while(true)
		{
			try {
				//System.out.println("üũ����");
				
				if(g.PlayerLife < 1) // ������ < 1
				{
					// ���ӿ��� ���
					JPanel PGameOver = new JPanel();
					PGameOver.setOpaque(false);
					PGameOver.setBounds(0, 0, 700, 500);
					JLabel JGameOver = new JLabel(new ImageIcon("./�̹�������/uiframe/gameover.png"));
					JGameOver.setBounds(0, 0, 700, 500);
					PGameOver.add(JGameOver);
					g.RenderingPan.add(PGameOver, new Integer(5000));
					JOptionPane.showMessageDialog(null, "���ӿ��� ��.��");
					System.exit(0);
				}
				if(g.PlayerLevel > 3) // ���� > 3
				{
					// ����Ŭ���� ���
					JPanel PGameOver = new JPanel();
					PGameOver.setOpaque(false);
					PGameOver.setBounds(0, 0, 700, 500);
					JLabel JGameOver = new JLabel(new ImageIcon("./�̹�������/uiframe/stageclear.png"));
					JGameOver.setBounds(0, 0, 700, 500);
					PGameOver.add(JGameOver);
					g.RenderingPan.add(PGameOver, new Integer(5000));
					JOptionPane.showMessageDialog(null, "����Ŭ����!!!");
					System.exit(1);
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	
}
