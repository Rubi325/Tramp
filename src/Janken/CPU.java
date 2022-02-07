package Janken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CPU extends Player {

	private final String[] names = {"試作１号"};

	int [][] judge = {{0,0,0},{0,0,0},{0,0,0}};
	int gu=0,choki=0,pa=0;


	@Override
	public void setName() {
		String enemyName = names[(int) (Math.random() * names.length)];
		this.name = enemyName;
	}

	@Override
	public Hands nextHand(int [] a,int turn) { //リズム実装

		if(turn > 1) {
			//モンテカルロ法時解放！　表代入
			//judge[a[turn-2]][a[turn-1]]++;

			//パターン解析AI
			//PatternFileWriter(a,turn);
			//パターン解析4-7書出
			//パターン抽出
			
			if(turn > 7) {
				return Hands.fromInt(Pattern(a,turn));
			}else {
				return Hands.fromInt(WinHands(a[turn-1]));
			}
			
			
			//モンテカルロ法呼出↓
			/*
				switch(a[turn]) {
				case 0:
					return Hands.fromInt(Calculate(0));
				case 1:
					return Hands.fromInt(Calculate(1));
				case 2:
					return Hands.fromInt(Calculate(2));
				}
			*/
			 
		}
		//モンテカルロ法時解放！！
		return Hands.fromInt((int) (Math.random() * 3));
	}

	//グ―、チョキ、パーで各手の次の確立を記憶させる
	//前の手　-> 次の手
	//int a [] からは前の手を記憶させてある
	/* |     | g | c | p |
	 * |-----|---|---|---|
	 * |  g  | 0 | 0 | 0 |
	 * |  c  | 0 | 0 | 0 |
	 * |  p  | 0 | 0 | 0 |
	 */
	public int Calculate(int a) {
		double [] hogehoge = new double [3];
		double len=0.0;
		for(int i=0;i<3;i++) {
			len += judge[a][i];
		}

		for(int i=0;i<3;i++) {
			if(judge[a][i] == 0) {
				hogehoge[i]=0;
			}else {
				hogehoge[i] = judge[a][i] / len;
			}
		}
		//markov連鎖が分かるやつ
		//System.out.println(judge[0][0] + "|" + judge[0][1] + "|" + judge[0][2]);
		//System.out.println(judge[1][0] + "|" + judge[1][1] + "|" + judge[1][2]);
		//System.out.println(judge[2][0] + "|" + judge[2][1] + "|" + judge[2][2]);
		//System.out.println(hogehoge[0] + " ::: " + hogehoge[1] + " ::: " + hogehoge[2]);

		double x = Math.random();

		if (x<hogehoge[0]) {
			return 0;
		} else if (x<hogehoge[0] + hogehoge[1]) {
			return 1;
		} else {
			return 2;
		}
	}

	//毎ターンずつ何の手を出せばよいのか思考する
	public int Pattern(int [] a,int turn){

		String PatternFile_path = "src\\Janken\\PatternFile.txt";
		File file = new File(PatternFile_path);
		try {
			PrintWriter add = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();

		//Turnの大きさ、繰返し判定
		//turnは初期値5
		int turn_len = turn;
		if(turn_len >= 7) {
			turn_len = 7;
		}
		
		int temp=0;
		double temp1=0.0;
		double temp2=0.0;
		
		//何回繰り返しているか？
		double [] Count = new double [8];
		for(int i=0;i<Count.length;i++) {
			Count[i] = 0.0;
		}
		
		//繰り返した後の手 0~7
		int [] CPUHands = new int [8];
		for(int i=0;i<CPUHands.length;i++) {
			CPUHands[i] = 0;
		}
		
		// 4XXX 5XXXX 6XXXXX 7XXXXXX		
		for(int cur = 4;cur<=turn_len;cur++) {
			//ぴったしの時が無かった場合
			boolean equals = false;
			//仮変数
			//４～７の文字列をlistに格納する
			int x=cur;
			//直前の4~7(変数x)のパターン抽出
			for(int i=turn-x;i<turn;i++) {
				list1.add(a[i]);
			}
			//list1値確認用
			//System.out.println("Debug.log list 1 .... " + list1);
			//0から(最後の手-1)まで読込・・・・
			for(int j=0;j<turn-cur;j++) {
				//小さい順に格納
				for(int l=j;l<j+x;l++) {
					list2.add(a[l]);
					//list2確認用
					//System.out.println(list2);
				}
				
				//list2,turn確認
				//System.out.println(list2 + " " + turn);
				
				//次の手の値格納・・・CPUHands
				//連続したパターンの回数を格納・・・Count
				//Countには４～７のパターン事に調べる
				if(list1.equals(list2)) {
					Count[x] ++;
					CPUHands[x] = a[j];
					equals = true;
				}else{
					temp1 = EqualsRate(list1,list2,x);
					if(temp1 >= temp2) {
						temp2 = temp1;
						Count[x]=temp1*0.001;
						CPUHands[x] = a[j];
					}
				}
				//list2中身消す
				list2.clear();
			}
			list1.clear();

			//Count[0~6]
			double intMax = Count[0];
			for(int i=1;i<Count.length;i++) {
				if(equals) {
					if(intMax<=Count[i]) {
						intMax = Count[i];
						temp = i;
					}
				}else {
					if(intMax<=Count[i]) {
						intMax = Count[i];
						temp = i;
					}
				}
			}
		}
		return WinHands(CPUHands[temp]); //ここで勝てる手に変換
	}

	private static int EqualsRate(ArrayList<Integer> list1, ArrayList<Integer> list2,int len) {
		int parsent=0;
		for(int i=0;i<len;i++) {
			if(list1.get(i).equals(list2.get(i))) {
				parsent++;
			}
		}
		return parsent*100/len;
	}

	public static int WinHands(int hand) {
		if(hand == 0) {//g
			return 2;
		}else if(hand == 1) {//c
			return 0;
		}else{//p
			return 1;
		}
	}

}
