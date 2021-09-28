package Janken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountLine {
	public static long CountLineResult(String filePath) {
		// �J�E���g�����s�����i�[���鐮���^�̕ϐ����`���A0�ŏ���������B
		long lineCount = 0;

		// ��O�����̎n�܂�
		try
		{
			// �t�@�C����ǂݍ��݃��[�h�ŃI�[�v������B�t�@�C�������݂��Ȃ������肷��ꍇ�� FileNotFoundException ���X���[�����B
			FileReader fr = new FileReader(filePath);

			// �t�@�C����ǂނ��߂֗̕��ȃN���X BufferedReader �̃I�u�W�F�N�g�����B
			BufferedReader br = new BufferedReader(fr);

			// �ǂݍ���1�s�̕�������i�[���邽�߂̕ϐ����`����B
			String line;

			// 1�s�ڂ�ǂ�ł݂�B�����A��̃t�@�C���Ȃ�Aline �ɂ� null ���Z�b�g�����B
			line = br.readLine();

			// �t�@�C���̍Ō�܂ŗ��� null ���Ԃ��Ă���܂ŁA�������J��Ԃ��B
			while( line != null )
			{
				// 1�s�ǂݍ��ނɐ������邽�тɁA�s���̃J�E���g��1���₷�B
				lineCount++;

				// readLine ���\�b�h���g���Ă���1�s�ǂݍ��ށB
				line = br.readLine();
			}

			// �X�g���[������āABufferedReader �̃��\�[�X���J������B
			// ���̂Ƃ��A������FileReader ��close���s����̂ŁAfr.close() �͕K�v�Ȃ��B�Ȃ̂ŁA�t�@�C���������ŕ����܂��B
			br.close();
		}
		catch( FileNotFoundException e )
		{
			// 15�s�ڂŃG���[����������Ƃ����ɗ���B
			System.out.println(e);
		}
		catch( IOException e )
		{
			// 18�A24�A38�s�ڂŃG���[����������Ƃ����ɗ���B
			System.out.println(e);
		}

		// �J�E���g�����s����Ԃ��B
		return lineCount;
	}
}
