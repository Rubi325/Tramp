package Janken;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//���ؑΏۂ̕�����
		  //String text = "1�Q89";
		String text = "abe2222";

		  //���،��ʂ��i�[����ϐ�
		  boolean result = true;

		  //�ꕶ�����擪����m�F����Bfor���͕��������J��Ԃ�
		  for(int i = 0; i < text.length(); i++) {

		    //i�����߂̕����ɂ���Character.isDigit���\�b�h�Ŕ��肷��
		    if(Character.isDigit(text.charAt(i))) {
		      //�����̏ꍇ�͎��̕����̔����
		      continue;
		    }else {
		      //�����łȂ��ꍇ�͌��،��ʂ�false�ɏ㏑������
		      result =false;
		      break;
		    }
		  }

		  //���ʏo��
		  System.out.println(text + "�͐����ł���=>" + result);
	}

}
