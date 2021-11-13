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
			//代入
			//judge[a[turn-2]][a[turn-1]]++;

			//ほんへ
			//PatternFileWriter(a,turn);
			//パターン解析4-7書出
			//パターン抽出
			if(turn > 4) {
				return Hands.fromInt(Pattern(a,turn));
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

	//モンテカルロ法↓
	//モンテカルロ法整理
	//グ―、チョキ、パーで各手の次の確立を記憶させる
	//前の手　-> 次の手
	//int a [] からは前の手を記憶させてある
	/* | e\p | g | c | p |
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

	//パターン書き出し
	public void PatternFileWriter(int [] a,int turn){

	}


	//ほんへ
	//パターン抽出
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
		int turn_len = 0;
		for(int i=4;i<8;i++){//
			if(turn < i) {
				turn_len = i;
			}
		}
		
		//便利なtemp君
		int temp=0;
		//何回繰り返しているか？
		int [] Count = new int [7];
		//カウント,全てに０を代入
		for(int i=0;i<7;i++) {
			Count[i] = 0;
		}
		//繰り返した後の手 0~7
		int [] CPUHands = new int [8];
		//次の手
		for(int i=0;i<CPUHands.length;i++) {
			CPUHands[i] = 0;
		}
		
		for(int cur = 4;cur<=turn_len;cur++) {
			//仮変数
			//４～７の文字列をlistに格納する
			int x=cur;
			//直前の4~7(変数x)のパターン抽出
			for(int i=a.length-x;i<a.length;i++) {
				list1.add(a[i]);
			}
			
			//0から(最後の手-1)まで読込・・・・
			//0 1 2 3 4 5 6 7
			//4の時
			//0123 1234 2345 3456 4567
			//5の時
			//01234 12345 23456 34567
			for(int j=0;j<a.length-cur+1;j++) {
				//小さい順に格納
				for(int l=j;l<j+x;l++) {
					list2.add(a[l]);
					//次の手の値格納・・・CPUHands
					//連続したパターンの回数を格納・・・Count
					//Countには４～７のパターン事に調べる
					if(list1.equals(list2)) {
						Count[x] ++;
						CPUHands[x] = a[l];
					}
				}
				//list2中身消す
				list2.clear();
			}
			list1.clear();
			
			//Count[0~6]
			int intMax = Count[0];
			for(int i=1;i<Count.length;i++) {
				if(intMax<Count[i]) {
					intMax = Count[i];
					temp = i;
				}
			}
		}
		
		return CPUHands[temp];

	}
}
