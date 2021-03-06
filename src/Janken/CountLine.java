package Janken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountLine {
	public static long CountLineResult(String filePath) {
		// カウントした行数を格納する整数型の変数を定義し、0で初期化する。
		long lineCount = 0;

		// 例外処理の始まり
		try
		{
			// ファイルを読み込みモードでオープンする。ファイルが存在しなかったりする場合に FileNotFoundException がスローされる。
			FileReader fr = new FileReader(filePath);

			// ファイルを読むための便利なクラス BufferedReader のオブジェクトを作る。
			BufferedReader br = new BufferedReader(fr);

			// 読み込んだ1行の文字列を格納するための変数を定義する。
			String line;

			// 1行目を読んでみる。もし、空のファイルなら、line には null がセットされる。
			line = br.readLine();

			// ファイルの最後まで来て null が返ってくるまで、処理を繰り返す。
			while( line != null )
			{
				// 1行読み込むに成功するたびに、行数のカウントを1増やす。
				lineCount++;

				// readLine メソッドを使ってもう1行読み込む。
				line = br.readLine();
			}

			// ストリームを閉じて、BufferedReader のリソースを開放する。
			// このとき、同時にFileReader のcloseも行われるので、fr.close() は必要ない。なので、ファイルもここで閉じられます。
			br.close();
		}
		catch( FileNotFoundException e )
		{
			// 15行目でエラーが発生するとここに来る。
			System.out.println(e);
		}
		catch( IOException e )
		{
			// 18、24、38行目でエラーが発生するとここに来る。
			System.out.println(e);
		}

		// カウントした行数を返す。
		return lineCount;
	}
}
