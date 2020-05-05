package pack;

import java.awt.Color;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/* ******************************************************
* 프로그램명 : CheckGame.java - CheckGame클래스
* 작성자 : 2013041039 이민섭, 2016039083 강예지
* 조원 : 서재준(팀장), 최재웅, 이민섭, 강예지
* 작성일 : 2017.12.8
* 프로그램 설명 : 게임 클리어, 실패를 판단하여 출력해주는 프로그램
* 팀명 : 10조 큐빅
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
				//System.out.println("체크게임");
				
				if(g.PlayerLife < 1) // 라이프 < 1
				{
					// 게임오버 출력
					JPanel PGameOver = new JPanel();
					PGameOver.setOpaque(false);
					PGameOver.setBounds(0, 0, 700, 500);
					JLabel JGameOver = new JLabel(new ImageIcon("./이미지파일/uiframe/gameover.png"));
					JGameOver.setBounds(0, 0, 700, 500);
					PGameOver.add(JGameOver);
					g.RenderingPan.add(PGameOver, new Integer(5000));
					JOptionPane.showMessageDialog(null, "게임오버 ㅠ.ㅠ");
					System.exit(0);
				}
				if(g.PlayerLevel > 3) // 레벨 > 3
				{
					// 게임클리어 출력
					JPanel PGameOver = new JPanel();
					PGameOver.setOpaque(false);
					PGameOver.setBounds(0, 0, 700, 500);
					JLabel JGameOver = new JLabel(new ImageIcon("./이미지파일/uiframe/stageclear.png"));
					JGameOver.setBounds(0, 0, 700, 500);
					PGameOver.add(JGameOver);
					g.RenderingPan.add(PGameOver, new Integer(5000));
					JOptionPane.showMessageDialog(null, "게임클리어!!!");
					System.exit(1);
				}
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	
}
