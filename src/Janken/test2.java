package Janken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test2 {
	static int Pre_Turn = 0;
	static int [] Pre_yourHands = new int [1000] ;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//�Ō�܂œǂݍ��񂾂�...false
				boolean NullPo = true;
				
				//���O�̏ꏊ
				int NamePoint = 1;
				String Pre_PlayerName;
				
				//file�Ǎ�
				File file = new File("src\\Janken\\HandsFile.txt");
				
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String PlayerName = br.readLine();
				
				while(NullPo == true) {
					Scanner sc;
					String temp;
					try {
						sc = new Scanner(file);
						//�v���C���[�����f
						if(NamePoint%3 == 1) {
							Pre_PlayerName = sc.nextLine();
							System.out.println(Pre_PlayerName);
							if(Pre_PlayerName.contentEquals(PlayerName)) {
								System.out.println(NamePoint);
								temp = sc.nextLine();
								String strSplit[] = temp.split("");
								int intStdInLen = strSplit.length;
								int intSplit [] = new int[intStdInLen];
								System.out.println("Veah" + intStdInLen);
								for(int i=0;i<intStdInLen;i++) {
									if(i%2==0) {
										intSplit[i] = Integer.parseInt(strSplit[i]);
										Pre_yourHands[i] = Integer.parseInt(strSplit[i]);
										Pre_Turn++;
									}
								}
								NamePoint+=3;
							}else if(Pre_PlayerName.equals(null)){
								System.out.println("NoData Player!");
								NullPo = false;
							}else {
								NamePoint++;
							}
						}else {
							NamePoint++;
						}
					} catch (FileNotFoundException e) {
						System.out.println("filenot");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Ioe");
						e.printStackTrace();
					}
					if(NamePoint==100) {
						NullPo = false;
					}
				}
				System.out.println("Pre_turn:" + Pre_Turn);
			}

}
