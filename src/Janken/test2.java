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
		//最後まで読み込んだら...false
				boolean NullPo = true;
				
				//名前の場所
				int NamePoint = 1;
				String Pre_PlayerName;
				
				long max = 0;
				String HandsFile_path = "src\\Janken\\HandsFile.txt";
				
				//file読込
				File file = new File("src\\Janken\\HandsFile.txt");
				
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				String PlayerName = br.readLine();
				
				max = CountLine.CountLineResult(HandsFile_path);
				System.out.println(max);
				
				while(NamePoint<max) {
					Scanner sc;
					String temp;
					
					try {
						sc = new Scanner(file);
						//プレイヤー名判断
						if(NamePoint%3 == 1) {
							Pre_PlayerName = sc.nextLine();
							System.out.println(Pre_PlayerName + "  " + NamePoint);
							
							if(Pre_PlayerName.equals(PlayerName)) {
								//System.out.println(NamePoint);
								temp = sc.nextLine();
								String strSplit[] = temp.split("");
								int intStdInLen = strSplit.length;
								int intSplit [] = new int[intStdInLen];
								//System.out.println("Veah" + intStdInLen);
								for(int i=0;i<intStdInLen;i++) {
									if(i%2==0) {
										intSplit[i] = Integer.parseInt(strSplit[i]);
										Pre_yourHands[i] = Integer.parseInt(strSplit[i]);
										Pre_Turn++;
									}
								}
								NamePoint+=2;
							}else {
								System.out.println("Any Player!");
							}
						}else {
							Pre_PlayerName = sc.nextLine();
							System.out.println("Any Player's Hands");
						}
					} catch (FileNotFoundException e) {
						System.out.println("filenot");
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Ioe");
						e.printStackTrace();
					}
					NamePoint++;
				}
				System.out.println("Pre_turn:" + Pre_Turn);
			}

}
