package Janken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class main {
	//�ߋ��̃f�[�^�񐔁E��
	static int Pre_Turn = 0;
	static int [] Pre_yourHands = new int [1000] ;
	static boolean NameWriting = true;

	public static void main(String[] args) throws IOException {

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

		//�t�@�C���Ǎ�
		ReadLogFile(yourName);
		//�^�[���m�F�p
		//System.out.println("pre_turn : " + Pre_Turn);
		//�ߋ��f�[�^������Α��
		if(Pre_Turn>0) {
			System.out.println("�O��̃v���C�f�[�^��ǂݍ��݂܂���");
		}
		
		for(int i=0;i<Pre_Turn;i++) {
			yourHands[i] = Pre_yourHands[i];
			enemy.nextHand(yourHands, i);
			turn++;
		}
		//System.out.println(turn + " " + Pre_Turn);

		//game�J�n�A���
		for(int i=0;i<2;i++) {
			while(p_points < 5 && e_points < 5) {
				System.out.println();
				Hands yourHand  = you.nextHand(yourHands,turn);
				Hands enemyHand = enemy.nextHand(yourHands,turn);

				System.out.println(yourName   + "�́@" + yourHand  +  "  ���������B");
				System.out.println(enemyName  + "�́@" + enemyHand +  "  ���������B");
				
				yourHands[turn] = Math_P_Hands(yourHand);
				enemyHands[turn] = Math_E_Hands(enemyHand);
				System.out.println();
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
	//���s����
	private static String Judge(int player,int enemy) {
		if(player>enemy) {
			return "You Win !!!....Congratulations!Congratulations!";
		}else {
			return "You Lose...���ŕ������������܂ōl���Ƃ��Ă�������!";
		}
	}
	//HandsFile.txt�ւ̏�������
	private static void Memorizer(String name,int p_hands,int e_hands,int p_pointa,int e_pointa,int turn) {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file,true);
			PrintWriter add = new PrintWriter(pw);
			if(NameWriting == true) {
				add.println(name);
				NameWriting = false;
			}

			add.print(p_hands + "" + e_hands);
			if(p_pointa == 5 || e_pointa == 5) {
				add.println();
			}
			add.flush();
			add.close();

		}catch(IOException e) {
			System.out.println("error!!");
			e.printStackTrace();
		}
	}
	//�f�[�^���Q�Ƃ��A�z��֊i�[����
	private static void ReadLogFile(String PlayerName) throws IOException {
		//���O�̏ꏊ
		int NamePoint = 1;
		String Pre_PlayerName;

		long max = 0;
		String HandsFile_path = "src\\Janken\\HandsFile.txt";

		//file�Ǎ�
		File file = new File("src\\Janken\\HandsFile.txt");

		max = CountLine.CountLineResult(HandsFile_path);
		//System.out.println(max);

		Scanner sc;
		sc = new Scanner(file);

		while(NamePoint<=max) {

			String temp;

			//�v���C���[�����f
			if(NamePoint%3 == 1) {
				Pre_PlayerName = sc.nextLine();
				//System.out.println(Pre_PlayerName + "  " + NamePoint);

				if(Pre_PlayerName.equals(PlayerName)) {
					//System.out.println(NamePoint + PlayerName);
					for(int i_0=0;i_0<2;i_0++) {//��s�Ǎ�
						//System.out.println("Loading..." + i_0);
						temp = sc.nextLine();
						String strSplit[] = temp.split("");
						int intStdInLen = strSplit.length;
						int intSplit [] = new int[intStdInLen];
						//System.out.println("Veah" + intStdInLen);
						for(int i=0;i<intStdInLen;i++) {
							if(i%2==0) {
								intSplit[i] = Integer.parseInt(strSplit[i]);
								Pre_yourHands[i] = Integer.parseInt(strSplit[i]);
								Pre_Turn++;
							}
						}
					}
					//System.out.println(Pre_PlayerName + " : " + NamePoint);
					NamePoint+=2;
					//System.out.println(Pre_PlayerName + " :: " + NamePoint);
				}else {
					//System.out.println("Othor Player!");
				}
			}else {
				Pre_PlayerName = sc.nextLine();
				//System.out.println("Othor Player's Hands");
			}

			NamePoint++;
		}

		sc.close();

		//System.out.println("Pre_turn:" + Pre_Turn);
		//return Pre_Turn;
	}
}
