package BigNumber;

import java.util.Random;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] player1 = new int[5];
		int [] player2 = new int[5];
		
		int num1=0,num2=0;
		int x1=35,x2=35;
		
		boolean turn = true;
		
		for(int i=0;i<player1.length;i++) {
			Random rand1 = new Random();
			Random rand2 = new Random();
			player1[i] = rand1.nextInt(58)+6;//25 20 35���ϒl�w��
			player2[i] = rand2.nextInt(58)+6;//25 20 35���ϒl�w��
			num1 += player1[i];
			num2 += player2[i];
			if(i == 4) {
				if(num1 / 5 < x1) {
					i = 0;
				}else if(num2 / 5 < x2) {
					i = 0;
				}else {	
				}	
			}
			System.out.print(i+1 + ":" + player1[i] + ",");
			System.out.println(i+1 + ":" + player2[i]);
		}
		
		if(turn) {
			
		}
			
		
	}
}


/*
�������U�`�U�S�̒�����T�Âv���C���[�ɔz��(���͖���)
���̐����̓|�[�J�[�Ŕz��ꂽ�T���̐����̍��v�̐��ł���B
�v���C���[�͂��̔z��ꂽ�����J�[�h�𖈃^�[���񎦂�����������菟������B
���^�[���x�b�g�����Ă��炢������Γq�����R�C����S�ĒD���A���ĂΒD����B
�ŏI�I�ɂT�^�[���̒��ŃR�C���𑽂��������Ă���Ώ���
�A�������ɂR��A���ŕ�����Ɩⓚ���p�ŕ����ƂȂ�
�q���`�b�v�͈�^�[���ɍő�T���A�ŏ��̎莝���`�b�v�͂P�T��
*/