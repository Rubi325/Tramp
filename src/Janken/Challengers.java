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
		//���Ԍv��
		long start = System.currentTimeMillis();
		Random r = new Random();

		while (true) {
			System.out.println("�S�b�ȓ��Ɏ���o����...");
			System.out.print("�����o���܂���? �O�[:0 �`���L:1 �p�[:2  > ");
			try {
				int hand_number = Integer.parseInt(scanner.nextLine());
				if (0 <= hand_number && hand_number <= 2) {
					long end = System.currentTimeMillis();
					if((end - start) > 4000) {
						System.err.println("���f���x���I�@�@" + ((end -start)/1000) + "�b�ȏ�o��");
						throw new TooLateException("����o�����f���x���I�I"+ ((end -start)/1000) + "�b�ȏ�o��");
						//return Hands.fromInt(r.nextInt(3));
					}else {
						return Hands.fromInt(hand_number);
					}
				} else {
					System.err.println("�͈͊O�̐��������͂���Ă��܂��B");
				}
			} catch (NumberFormatException e) {
				System.err.println("�����ȊO�����͂���Ă��܂�");
			}
		}
	}
}