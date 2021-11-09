package Janken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ResultGraph {

	static int WinCount=0;
	static int LoseCount=0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//���ʏ���

		System.out.println("���ʏW�v�I�I�I");

		String HandsFile_Path = "src\\Janken\\HandsFile.txt" ;
		File file = new File(HandsFile_Path);

		Scanner sc;
		sc = new Scanner(file);

		String temp;
		String name;
		int count=0;
		long max=0;
		max = CountLine.CountLineResult(HandsFile_Path);
		while(count < max) {
			int CountPlayer=0;
			int CountCPU=0;
			int CountEven=0;
			int RoundPlayer = 0;
			int RoundCPU = 0;
			int RoundEven = 0;
			//System.out.println("Loading...");
			System.out.println();
			if(count%3==0) {
				name = sc.nextLine();
				for(int j=0;j<2;j++) {
					System.out.println("Round" + (j+1) + "�ł��B");
					System.out.println();
					temp = sc.nextLine();
					String strSplit[] = temp.split("");
					int intStdInLen = strSplit.length;
					int intSplit [] = new int[intStdInLen];
					//System.out.println("Veah" + intStdInLen);					
					for(int i=0;i<intStdInLen;i++) {
						intSplit[i] = Integer.parseInt(strSplit[i]);
					}
					for(int i=0;i<intStdInLen;i+=2) {
						//�v���C���[�ڐ��@0�͂����� �A1�͏����A2�͕���
						int x = judge(intSplit[i],intSplit[i+1]);
						
						//�������̎��\��
						HandsResult(intSplit[i],intSplit[i+1]);
						
						if(x==0) {
							CountEven++;
							System.out.println("�������ł�");
						}else if(x==1) {
							CountPlayer++;
							System.out.println("�v���C���[�̏����ł�");
						}else {
							CountCPU++;
							System.out.println("�v���C���[�̕����ł�");
						}
						System.out.println();
						if(i==intStdInLen-2 && j==0) {
							RoundPlayer = CountPlayer;
							RoundCPU = CountCPU;
							RoundEven = CountEven;
						}
					}
				}
				int RP = RoundPlayer;
				int RC = RoundCPU;
				int RE = RoundEven;
				int R_total = (RP+RC+RE);
				int RP2 = (CountPlayer-RoundPlayer);
				int RC2 = (CountCPU-RoundCPU);
				int RE2 = (CountEven-RoundEven);
				int R2_total = (RP2+RC2+RE2);
				int CP = CountPlayer;
				int CC = CountCPU;
				int CE = CountEven;
				int C_total = (CP+CC+CE);

				System.out.println();
				System.out.println("�v���C���[�� : " + name);
				System.out.println("Round1");
				System.out.println("�v���C���[�������@�F�@" + RP + " CPU�������@�F�@" + RC + " ���������@�F�@" + RE);
				System.out.println("Player�̏����@�F�@" + RP*100/R_total + " %  CPU�̏����@: " + RC*100/R_total + " % ");
				Result(RP*100/R_total,RC*100/R_total);
				System.out.println();
				System.out.println("Round2");
				System.out.println("�v���C���[�������@�F�@" + RP2 + " CPU�������@�F�@" + RC2 + " ���������@�F�@" +RE2);
				System.out.println("Player�̏����@�F�@" + RP2*100/R2_total + " %  CPU�̏����@: " + RC2*100/R2_total + " % ");
				Result(RP2*100/R2_total,RC2*100/R2_total);
				System.out.println();
				System.out.println("Round1�{Round2");
				System.out.println("�v���C���[�������@�F�@" + CP+ " CPU�������@�F�@" + CC + " ���������@�F�@" + CE);
				System.out.println("Player�̏����@�F�@" + CP*100/C_total + " %  CPU�̏����@: " + CC*100/C_total + " % ");
			}
			count+=3;
		}
		System.out.println();
		System.out.println();
		System.out.println("Result...." + "CPU�Z�b�g�������@�F�@" + WinCount + "�@�@Player�Z�b�g�������@�F�@"
				+ LoseCount);
		System.out.println("����...." + WinCount*100/(WinCount+LoseCount) + " %");
	}

	public static int judge(int p,int c) {
		int x = (p-c + 3)%3;
		if(x == 0) {
			return 0;
		}else if(x == 2){
			return 1;
		}else {
			return 2;
		}
	}

	public static void Result(int a,int b) {
		if(a>b) {
			System.out.println("Player�̏����ł�");
			LoseCount++;
		}else {
			System.out.println("CPU�̏����ł�");
			WinCount++;
		}
	}

	public static void HandsResult(int p,int c) {
		int [] jank = new int [2];
		System.out.println("P�@�F�@C");
		jank[0] = p;
		jank[1] = c;
		for(int i=0;i<2;i++) {
			if(jank[i] == 0) {
				System.out.print("�O�[");
			}else if(jank[i] == 1) {
				System.out.print("�`���L");
			}else if(jank[i] == 2) {
				System.out.print("�p�[");
			}
			if(i==0) {
				System.out.print("�@�F�@");
			}else {
				System.out.println();
			}
		}
	}

}
