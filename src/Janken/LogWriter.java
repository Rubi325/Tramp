package Janken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	public static void main(String[] args) throws IOException {
		try {
			File file = new File("src\\Janken\\HandsFile.txt");
			FileWriter pw = new FileWriter(file);
			
			pw.write("������������");
			pw.write("test1dddd");
			
			//pw.flush();
			
			pw.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}

//�p���R�[�h�ꏊ
/*
while(p_point < 5 && e_point < 5) {
	Hands yourHand  = you.nextHand(yourHands,turn);
	Hands enemyHand = enemy.nextHand(yourHands,turn);

	System.out.println(yourName   + "�́@" + yourHand  +  "  ���������B");
	System.out.println(enemyName  + "�́@" + enemyHand +  "  ���������B");

	yourHands[turn] = Math_P_Hands(yourHand);
	enemyHands[turn] = Math_E_Hands(enemyHand);

	if (yourHand.winTo(enemyHand)) {
		System.out.println(yourName + "�@�̂����I");
		p_point += 1;
	} else if (yourHand.loseTo(enemyHand)) {
		System.out.println(yourName + "�@�̂܂��c");
		e_point += 1;
	} else {
		System.out.println("�������ł��B");
	}
	//�����Ŏ��HandsFile�ɏ�������
	Memorizer(yourName,yourHands[turn],enemyHands[turn],p_point,e_point);

	turn += 1;
}*/