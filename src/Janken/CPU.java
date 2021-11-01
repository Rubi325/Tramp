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
			judge[a[turn-2]][a[turn-1]]++;
			
			//�ق��
			//�p�^�[�����4-7
			//�p�^�[�����o
			//Pattern(a,turn);
			//�p�^�[���I��
			
			//�����e�J�����@�ďo��
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
	
	//�ق��
	//�p�^�[�����o
	public void Pattern(int [] a,int turn){
		
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
		
		//���ϐ�
		int x=4;
		
		if(a.length > x) {
			for(int i=x;i<a.length;i++) {
				for(int l=x;l>0;l--) {
					list1.add(a[i-l]);
				}
				for(int j=x+1;j<a.length;j++) {
					//���������Ɋi�[
					for(int l=x;l>0;l--) {
						list2.add(a[j-l]);
					}
					if(list1.equals(list2)) {
						
					}
				}
			}
		}
	}
	//�p�^�[�����3
	
	
	
	
	
}