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
main.java
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


/*
CPU.java
if(hogehoge[0] >= hogehoge[1] && hogehoge[0] >= hogehoge[2]) {
	return 0;
}else if(hogehoge[1] >= hogehoge[0] && hogehoge[1] >= hogehoge[2]) {
	return 1;
}else if(hogehoge[2] >= hogehoge[0] && hogehoge[2] >= hogehoge[1]) {
	return 2;
}

if(i>=2) { //3��ȏ�̂���񂯂�̎��̂ݔ���AI�I�I�I
if(a[i-2] == 0) {//g
	return Hands.fromInt(2);
}else if(a[i-2] == 1) {//c
	return Hands.fromInt(0);
}else if(a[i-2] == 2){//p
	return Hands.fromInt(1);
}


*/