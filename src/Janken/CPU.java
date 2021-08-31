package Janken;

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
		if(turn > 1) {
			//グーの場合
			if(a[turn-2] == 0) {
				if(a[turn-1] == 0) {
					judge[0][0]++;
				}else if(a[turn-1] == 1) {
					judge[0][1]++;
				}else if(a[turn-1] == 2) {
					judge[0][2]++;
				}
			}
			//チョキの場合
			if(a[turn-2] == 1) {
				if(a[turn-1] == 0) {
					judge[1][0]++;
				}else if(a[turn-1] == 1) {
					judge[1][1]++;
				}else if(a[turn-1] == 2) {
					judge[1][2]++;
				}
			}
			//パーの場合
			if(a[turn-2] == 2) {
				if(a[turn-1] == 0) {
					judge[2][0]++;
				}else if(a[turn-1] == 1) {
					judge[2][1]++;
				}else if(a[turn-1] == 2) {
					judge[2][2]++;
				}
			}

			switch(a[turn]) {
			case 0:
				return Hands.fromInt(Calculate(0));
			case 1:
				return Hands.fromInt(Calculate(1));
			case 2:
				return Hands.fromInt(Calculate(2));
			}
		}

		return Hands.fromInt((int) (Math.random() * 3));
	}

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
		System.out.println(judge[0][0] + "|" + judge[0][1] + "|" + judge[0][2]);
		System.out.println(judge[1][0] + "|" + judge[1][1] + "|" + judge[1][2]);
		System.out.println(judge[2][0] + "|" + judge[2][1] + "|" + judge[2][2]);

		System.out.println(hogehoge[0] + " ::: " + hogehoge[1] + " ::: " + hogehoge[2]);

		double x = Math.random();
		
		if (x<hogehoge[0]) {
			return 0;
		} else if (x<hogehoge[0] + hogehoge[1]) {
			return 1;
		} else {
			return 2;
		}
		/*
		if(hogehoge[0] >= hogehoge[1] && hogehoge[0] >= hogehoge[2]) {
			return 0;
		}else if(hogehoge[1] >= hogehoge[0] && hogehoge[1] >= hogehoge[2]) {
			return 1;
		}else if(hogehoge[2] >= hogehoge[0] && hogehoge[2] >= hogehoge[1]) {
			return 2;
		}
		*/
	}


}

/*
if(i>=2) { //3回以上のじゃんけんの時のみ発動AI！！！
if(a[i-2] == 0) {//g
	return Hands.fromInt(2);
}else if(a[i-2] == 1) {//c
	return Hands.fromInt(0);
}else if(a[i-2] == 2){//p
	return Hands.fromInt(1);
}
}*/