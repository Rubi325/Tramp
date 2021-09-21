package Janken;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//検証対象の文字列
		  //String text = "1２89";
		String text = "abe2222";

		  //検証結果を格納する変数
		  boolean result = true;

		  //一文字ずつ先頭から確認する。for文は文字数分繰り返す
		  for(int i = 0; i < text.length(); i++) {

		    //i文字めの文字についてCharacter.isDigitメソッドで判定する
		    if(Character.isDigit(text.charAt(i))) {
		      //数字の場合は次の文字の判定へ
		      continue;
		    }else {
		      //数字でない場合は検証結果をfalseに上書きする
		      result =false;
		      break;
		    }
		  }

		  //結果出力
		  System.out.println(text + "は数字である=>" + result);
	}

}
