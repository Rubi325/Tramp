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
			//���
			//judge[a[turn-2]][a[turn-1]]++;

			//�ق��
			//PatternFileWriter(a,turn);
			//�p�^�[�����4-7���o
			//�p�^�[�����o
			if(turn > 4) {
				return Hands.fromInt(Pattern(a,turn));
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

	//�����e�J�����@��
	//�����e�J�����@����
	//�O�\�A�`���L�A�p�[�Ŋe��̎��̊m�����L��������
	//�O�̎�@-> ���̎�
	//int a [] ����͑O�̎���L�������Ă���
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

	//�p�^�[�������o��
	public void PatternFileWriter(int [] a,int turn){

	}


	//�ق��
	//�p�^�[�����o
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
		int turn_len = 0;
		for(int i=4;i<8;i++){//
			if(turn < i) {
				turn_len = i;
			}
		}
		
		//�֗���temp�N
		int temp=0;
		//����J��Ԃ��Ă��邩�H
		int [] Count = new int [7];
		//�J�E���g,�S�ĂɂO����
		for(int i=0;i<7;i++) {
			Count[i] = 0;
		}
		//�J��Ԃ�����̎� 0~7
		int [] CPUHands = new int [8];
		//���̎�
		for(int i=0;i<CPUHands.length;i++) {
			CPUHands[i] = 0;
		}
		
		for(int cur = 4;cur<=turn_len;cur++) {
			//���ϐ�
			//�S�`�V�̕������list�Ɋi�[����
			int x=cur;
			//���O��4~7(�ϐ�x)�̃p�^�[�����o
			for(int i=a.length-x;i<a.length;i++) {
				list1.add(a[i]);
			}
			
			//0����(�Ō�̎�-1)�܂œǍ��E�E�E�E
			//0 1 2 3 4 5 6 7
			//4�̎�
			//0123 1234 2345 3456 4567
			//5�̎�
			//01234 12345 23456 34567
			for(int j=0;j<a.length-cur+1;j++) {
				//���������Ɋi�[
				for(int l=j;l<j+x;l++) {
					list2.add(a[l]);
					//���̎�̒l�i�[�E�E�ECPUHands
					//�A�������p�^�[���̉񐔂��i�[�E�E�ECount
					//Count�ɂ͂S�`�V�̃p�^�[�����ɒ��ׂ�
					if(list1.equals(list2)) {
						Count[x] ++;
						CPUHands[x] = a[l];
					}
				}
				//list2���g����
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
