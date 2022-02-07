package Janken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CPU extends Player {

	private final String[] names = {"����P��"};

	int [][] judge = {{0,0,0},{0,0,0},{0,0,0}};
	int gu=0,choki=0,pa=0;


	@Override
	public void setName() {
		String enemyName = names[(int) (Math.random() * names.length)];
		this.name = enemyName;
	}

	@Override
	public Hands nextHand(int [] a,int turn) { //���Y������

		if(turn > 1) {
			//�����e�J�����@������I�@�\���
			//judge[a[turn-2]][a[turn-1]]++;

			//�p�^�[�����AI
			//PatternFileWriter(a,turn);
			//�p�^�[�����4-7���o
			//�p�^�[�����o
			
			if(turn > 7) {
				return Hands.fromInt(Pattern(a,turn));
			}else {
				return Hands.fromInt(WinHands(a[turn-1]));
			}
			
			
			//�����e�J�����@�ďo��
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
		//�����e�J�����@������I�I
		return Hands.fromInt((int) (Math.random() * 3));
	}

	//�O�\�A�`���L�A�p�[�Ŋe��̎��̊m�����L��������
	//�O�̎�@-> ���̎�
	//int a [] ����͑O�̎���L�������Ă���
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
		//markov�A������������
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

	//���^�[�������̎���o���΂悢�̂��v�l����
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

		//Turn�̑傫���A�J�Ԃ�����
		//turn�͏����l5
		int turn_len = turn;
		if(turn_len >= 7) {
			turn_len = 7;
		}
		
		int temp=0;
		double temp1=0.0;
		double temp2=0.0;
		
		//����J��Ԃ��Ă��邩�H
		double [] Count = new double [8];
		for(int i=0;i<Count.length;i++) {
			Count[i] = 0.0;
		}
		
		//�J��Ԃ�����̎� 0~7
		int [] CPUHands = new int [8];
		for(int i=0;i<CPUHands.length;i++) {
			CPUHands[i] = 0;
		}
		
		// 4XXX 5XXXX 6XXXXX 7XXXXXX		
		for(int cur = 4;cur<=turn_len;cur++) {
			//�҂������̎������������ꍇ
			boolean equals = false;
			//���ϐ�
			//�S�`�V�̕������list�Ɋi�[����
			int x=cur;
			//���O��4~7(�ϐ�x)�̃p�^�[�����o
			for(int i=turn-x;i<turn;i++) {
				list1.add(a[i]);
			}
			//list1�l�m�F�p
			//System.out.println("Debug.log list 1 .... " + list1);
			//0����(�Ō�̎�-1)�܂œǍ��E�E�E�E
			for(int j=0;j<turn-cur;j++) {
				//���������Ɋi�[
				for(int l=j;l<j+x;l++) {
					list2.add(a[l]);
					//list2�m�F�p
					//System.out.println(list2);
				}
				
				//list2,turn�m�F
				//System.out.println(list2 + " " + turn);
				
				//���̎�̒l�i�[�E�E�ECPUHands
				//�A�������p�^�[���̉񐔂��i�[�E�E�ECount
				//Count�ɂ͂S�`�V�̃p�^�[�����ɒ��ׂ�
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
				//list2���g����
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
		return WinHands(CPUHands[temp]); //�����ŏ��Ă��ɕϊ�
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
