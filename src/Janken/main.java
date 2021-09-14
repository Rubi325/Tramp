package Janken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main {

	public static void main(String[] args) {

		Player you   = new Challengers();
		Player enemy = new CPU();
		
		//�|�C���g
		int p_points=0;
		int e_points=0;

		//�^�[�����A����񂯂�̎�̊i�[�p�z��@
		int turn = 0;
		int [] yourHands = new int [1000];
		int [] enemyHands = new int [1000];

		//game,���O����
		System.out.print("���Ȃ܂�����͂��ĉ������B�F > ");
		you.setName();
		enemy.setName();
		String yourName  = you.getName();
		String enemyName = enemy.getName();
		System.out.println("Your name�@" + yourName);
		System.out.println("Enemy name�@" + enemyName);

		//game�J�n�A���
		for(int i=0;i<2;i++) {
			while(p_points < 5 && e_points < 5) {
				Hands yourHand  = you.nextHand(yourHands,turn);
				Hands enemyHand = enemy.nextHand(yourHands,turn);

				System.out.println(yourName   + "�́@" + yourHand  +  "  ���������B");
				System.out.println(enemyName  + "�́@" + enemyHand +  "  ���������B");

				yourHands[turn] = Math_P_Hands(yourHand);
				enemyHands[turn] = Math_E_Hands(enemyHand);

				if (yourHand.winTo(enemyHand)) {
					System.out.println(yourName + "�@�̂����I");
					p_points += 1;
				} else if (yourHand.loseTo(enemyHand)) {
					System.out.println(yourName + "�@�̂܂��c");
					e_points += 1;
				} else {
					System.out.println("�������ł��B");
				}
				//�����Ŏ��HandsFile�ɏ�������
				Memorizer(yourName,yourHands[turn],enemyHands[turn],e_points,p_points,turn);

				turn +=1;
			}
			System.out.println("--------------------------");
			System.out.println("");
			System.out.println("You : " + p_points + "   Enemy : " + e_points );
			System.out.println(Judge(p_points,e_points));
			if(i==0) {
				System.out.println("Enemy Level Up!");
			}else{
				System.out.println("Finish Game!");
			}
			System.out.println("");
			System.out.println("**************************");
			
			p_points=0;
			e_points=0;
		}
		
	}
	//�v���C���[�̏o������𐔎��Ɋi�[
	private static int Math_P_Hands(Hands yourHand) {
		if(yourHand == Hands.Rock) {
			return 0;
		}else if(yourHand == Hands.Scissors) {
			return 1;
		}else if(yourHand == Hands.Paper) {
			return 2;
		}else {
			return 3;
		}
	}
	//�G�l�~�[�̏o������𐔎��Ɋi�[
	private static int Math_E_Hands(Hands enemyHand) {
		if(enemyHand == Hands.Rock) {
			return 0;
		}else if(enemyHand == Hands.Scissors) {
			return 1;
		}else if(enemyHand == Hands.Paper) {
			return 2;
		}else {
			return 3;
		}
	}
	//HandsFile.txt�ւ̏�������
	private static void Memorizer(String name,int p_hands,int e_hands,int p_pointa,int e_pointa,int turn) {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file,true);
			PrintWriter add = new PrintWriter(pw); 
			if(turn == 0) {
				add.println(name);
			}
			if(p_pointa == 5 || e_pointa == 5) {
				add.println("p e " + 
						p_hands + " " + e_hands);
			}else {
				add.print("p e " + 
						p_hands + " " + e_hands);
			}
			add.flush();
			add.close();

		}catch(IOException e) {
			System.out.println("error!!");
			e.printStackTrace();
		}
	}
	//���s����
	private static String Judge(int player,int enemy) {
		if(player>enemy) {
			return "You Win !!!";
		}else {
			return "You Lose...";
		}
	}
}
