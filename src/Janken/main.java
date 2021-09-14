package Janken;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class main {

	public static void main(String[] args) {
		
		Player you   = new Challengers();
		Player enemy = new CPU();

		int p_point=0;
		int e_point = 0;
		int p_points=0;
		int e_points=0;
		
		//カウント　
		int turn = 0;
		int [] yourHands = new int [100];
		
		//game,名前入力
		System.out.print("おなまえを入力して下さい。： > ");
		you.setName();
		enemy.setName();
		String yourName  = you.getName();
		String enemyName = enemy.getName();
		System.out.println("Your name　" + yourName);
		System.out.println("Enemy name　" + enemyName);
		
		//game開始ィイィィイイイイイイイイ
		//一回戦目
		while(p_points < 5 || e_points < 5) {
			Hands yourHand  = you.nextHand(yourHands,turn);
			Hands enemyHand = enemy.nextHand(yourHands,turn);
			
			System.out.println(yourName   + "は　" + yourHand  +  "  をだした。");
			System.out.println(enemyName  + "は　" + enemyHand +  "  をだした。");
			
			if(yourHand == Hands.Rock) {
				yourHands[turn] = 0;
			}else if(yourHand == Hands.Scissors) {
				yourHands[turn] = 1;
			}else if(yourHand == Hands.Paper) {
				yourHands[turn] = 2;
			}
			//ここで手をHandsFileに書き込む
			Memorizer(yourName,yourHands[turn]);
			
			if (yourHand.winTo(enemyHand)) {
				System.out.println(yourName + "　のかち！");
				p_points += 1;
			} else if (yourHand.loseTo(enemyHand)) {
				System.out.println(yourName + "　のまけ…");
				e_points += 1;
			} else {
				System.out.println("あいこです。");
			}
			turn +=1;
		}
		System.out.println("--------------------------");
		System.out.println("You : " + p_points + "   Enemy : " + e_points );
		System.out.println(Judge(p_points,e_points));
		System.out.println("**************************");
		System.out.println("");
		System.out.println("");
		System.out.println("Enemy Level Up!");
		

		//二回戦目
		
		while(p_point < 5 || e_point < 5) {
			Hands yourHand  = you.nextHand(yourHands,turn);
			Hands enemyHand = enemy.nextHand(yourHands,turn);
			
			System.out.println(yourName   + "は　" + yourHand  +  "  をだした。");
			System.out.println(enemyName  + "は　" + enemyHand +  "  をだした。");
			
			if(yourHand == Hands.Rock) {
				yourHands[turn] = 0;
			}else if(yourHand == Hands.Scissors) {
				yourHands[turn] = 1;
			}else if(yourHand == Hands.Paper) {
				yourHands[turn] = 2;
			}
			
			//ここで手をHandsFileに書き込む
			Memorizer(yourName,yourHands[turn]);
			
			if (yourHand.winTo(enemyHand)) {
				System.out.println(yourName + "　のかち！");
				p_point += 1;
			} else if (yourHand.loseTo(enemyHand)) {
				System.out.println(yourName + "　のまけ…");
				e_point += 1;
			} else {
				System.out.println("あいこです。");
			}
			turn += 1;
		}
		
		System.out.println("--------------------------");
		System.out.println("You : " + p_point + "   Enemy : " + e_point );
		System.out.println(Judge(p_point,e_point));
		System.out.println("**************************");
		
	}
	
	private static void Memorizer(String name,int hands) {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file,true);
			PrintWriter add = new PrintWriter(pw); 
			
			add.print(hands + " ");
			add.flush();
			
			add.close();
			
		}catch(IOException e) {
			System.out.println("error!!");
			e.printStackTrace();
		}
	}
	private static String Judge(int player,int enemy) {
		if(player>enemy) {
			return "You Win !!!";
		}else {
			return "You Lose...";
		}
	}
}
