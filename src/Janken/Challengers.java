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
	public Hands nextHand(int [] a,int turn) throws TooLateException {
		Scanner scanner = new Scanner(System.in);
		//時間計測
		long start = System.currentTimeMillis();
		Random r = new Random();

		while (true) {
			System.out.println("４秒以内に手を出すんだ...");
			System.out.print("何を出しますか? グー:0 チョキ:1 パー:2  > ");
			try {
				int hand_number = Integer.parseInt(scanner.nextLine());
				if (0 <= hand_number && hand_number <= 2) {
					long end = System.currentTimeMillis();
					if((end - start) > 4000) {
						System.err.println("判断が遅い！　　" + ((end -start)/1000) + "秒以上経過");
						throw new TooLateException("手を出す判断が遅い！！"+ ((end -start)/1000) + "秒以上経過");
						//return Hands.fromInt(r.nextInt(3));
					}else {
						return Hands.fromInt(hand_number);
					}
				} else {
					System.err.println("範囲外の数字が入力されています。");
				}
			} catch (NumberFormatException e) {
				System.err.println("数字以外が入力されています");
			}
		}
	}
}