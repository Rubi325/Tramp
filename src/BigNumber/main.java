package BigNumber;

import java.util.Random;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] player1 = new int[5];
		int [] player2 = new int[5];
		
		int num1=0,num2=0;
		int x1=35,x2=35;
		
		boolean turn = true;
		
		for(int i=0;i<player1.length;i++) {
			Random rand1 = new Random();
			Random rand2 = new Random();
			player1[i] = rand1.nextInt(58)+6;//25 20 35平均値指定
			player2[i] = rand2.nextInt(58)+6;//25 20 35平均値指定
			num1 += player1[i];
			num2 += player2[i];
			if(i == 4) {
				if(num1 / 5 < x1) {
					i = 0;
				}else if(num2 / 5 < x2) {
					i = 0;
				}else {	
				}	
			}
			System.out.print(i+1 + ":" + player1[i] + ",");
			System.out.println(i+1 + ":" + player2[i]);
		}
		
		if(turn) {
			
		}
			
		
	}
}


/*
数字を６～６４の中から５つづつプレイヤーに配る(被りは無し)
その数字はポーカーで配られた５枚の数字の合計の数である。
プレイヤーはその配られた数字カードを毎ターン提示し強い役を作り勝負する。
毎ターンベットをしてもらい負ければ賭けたコインを全て奪われ、勝てば奪える。
最終的に５ターンの中でコインを多く所持していれば勝ち
但し勝負に３回連続で負けると問答無用で負けとなる
賭けチップは一ターンに最大５枚、最初の手持ちチップは１５枚
*/