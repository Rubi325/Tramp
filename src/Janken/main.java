package Janken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class main {
	//過去のデータ回数・手
	static int Pre_Count = 0;
	static int [] Pre_yourHands = new int [1000] ;
	
	public static void main(String[] args) {

		Player you   = new Challengers();
		Player enemy = new CPU();

		//ポイント
		int p_points=0;
		int e_points=0;
		
		
		//ターン数、じゃんけんの手の格納用配列　
		int turn = 0;
		
		int [] yourHands = new int [1000];
		int [] enemyHands = new int [1000];

		//game,名前入力
		System.out.print("おなまえを入力して下さい。： > ");
		you.setName();
		enemy.setName();
		String yourName  = you.getName();
		String enemyName = enemy.getName();
		System.out.println("Your name　" + yourName);
		System.out.println("Enemy name　" + enemyName);
		
		//ファイル読込
		ReadLogFile(yourName);
		System.out.print("");
		//過去データがあれば代入
		for(int i=0;i<Pre_Count;i++) {
			yourHands[i] = Pre_yourHands[i];
			enemy.nextHand(yourHands, i);
			turn++;
			System.out.print(Pre_Count);
		}
		System.out.println(turn + " " + Pre_Count);
		
		//game開始、二回
		for(int i=0;i<2;i++) {
			while(p_points < 5 && e_points < 5) {
				Hands yourHand  = you.nextHand(yourHands,turn);
				Hands enemyHand = enemy.nextHand(yourHands,turn);

				System.out.println(yourName   + "は　" + yourHand  +  "  をだした。");
				System.out.println(enemyName  + "は　" + enemyHand +  "  をだした。");

				yourHands[turn] = Math_P_Hands(yourHand);
				enemyHands[turn] = Math_E_Hands(enemyHand);

				if (yourHand.winTo(enemyHand)) {
					System.out.println(yourName + "　のかち！");
					p_points += 1;
				} else if (yourHand.loseTo(enemyHand)) {
					System.out.println(yourName + "　のまけ…");
					e_points += 1;
				} else {
					System.out.println("あいこです。");
				}
				//ここで手をHandsFileに書き込む
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
	//プレイヤーの出した手を数字に格納
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
	//エネミーの出した手を数字に格納
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
	//勝敗判定
	private static String Judge(int player,int enemy) {
		if(player>enemy) {
			return "You Win !!!";
		}else {
			return "You Lose...";
		}
	}
	//HandsFile.txtへの書き込み
	private static void Memorizer(String name,int p_hands,int e_hands,int p_pointa,int e_pointa,int turn) {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file,true);
			PrintWriter add = new PrintWriter(pw);
			if(turn == 0) {
				add.println(name);
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
	//データを参照し、配列へ格納する
	private static void ReadLogFile(String PlayerName) {
		//最後まで読み込んだら...false
		boolean NullPo = true;
		//名前の場所
		int NamePoint = 1;
		String Pre_PlayerName;
		//file読込
		File file = new File("src\\Janken\\HandsFile.txt");
		while(NullPo = false) {
			Scanner sc;
			String temp;
			
			try {
				sc = new Scanner(file);
				//プレイヤー名判断
				if(NamePoint%3 == 1) {
					Pre_PlayerName = sc.nextLine();
					System.out.println(Pre_PlayerName);
					if(Pre_PlayerName == PlayerName) {
						temp = sc.nextLine();
						String strSplit[] = temp.split("");
						int intStdInLen = strSplit.length;
						int intSplit [] = new int[intStdInLen];
						for(int i=0;i<intStdInLen;i++) {
							if(i%2==0) {
								intSplit[i] = Integer.parseInt(strSplit[i]);
								Pre_yourHands[i] = Integer.parseInt(strSplit[i]);
								Pre_Count++;
							}
						}
						NamePoint+=3;
					}else if(Pre_PlayerName == null){
						System.out.println("NoData Player!");
						break;
					}
				}else {
					NamePoint++;
				}
			} catch (FileNotFoundException e) {
				System.out.println("filenot");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Ioe");
				e.printStackTrace();
			}
		}
	}
}
