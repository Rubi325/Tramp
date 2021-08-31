package Janken;
import java.util.*;

public class Challengers extends Player {

	@Override
	public void setName() {
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		this.name = name;
	}

	@Override
	public Hands nextHand(int [] a,int turn) {
		Scanner scanner = new Scanner(System.in);
		
		while (true) {
			System.out.print("何を出しますか? グー:0 チョキ:1 パー:2  > ");
			try {
				int hand_number = Integer.parseInt(scanner.nextLine());
				if (0 <= hand_number && hand_number <= 2) {
					return Hands.fromInt(hand_number);
				} else {
					System.err.println("範囲外の数字が入力されています。");
				}
			} catch (NumberFormatException e) {
				System.err.println("数字以外が入力されています");
			}
		}
	}

}