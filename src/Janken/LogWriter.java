package Janken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	public static void main(String[] args) throws IOException {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file);
			
			pw.write("ああああああ");
			pw.write("test1dddd");
			
			//pw.flush();
			
			pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

//廃棄コード場所
/*
while(p_point < 5 && e_point < 5) {
	Hands yourHand  = you.nextHand(yourHands,turn);
	Hands enemyHand = enemy.nextHand(yourHands,turn);

	System.out.println(yourName   + "は　" + yourHand  +  "  をだした。");
	System.out.println(enemyName  + "は　" + enemyHand +  "  をだした。");

	yourHands[turn] = Math_P_Hands(yourHand);
	enemyHands[turn] = Math_E_Hands(enemyHand);

	if (yourHand.winTo(enemyHand)) {
		System.out.println(yourName + "　のかち！");
		p_point += 1;
	} else if (yourHand.loseTo(enemyHand)) {
		System.out.println(yourName + "　のまけ…");
		e_point += 1;
	} else {
		System.out.println("あいこです。");
	}
	//ここで手をHandsFileに書き込む
	Memorizer(yourName,yourHands[turn],enemyHands[turn],p_point,e_point);

	turn += 1;
}*/