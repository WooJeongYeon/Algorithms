import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1786_ã�� {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = in.readLine().toCharArray();
		char[] pattern = in.readLine().toCharArray();
		int[] pArr = new int[pattern.length];
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		
		// �����Լ� ����� : KMP�� ���̵� �Ȱ��� ����, W���� W�� ã�� ���� ������ �ؼ�...
		// i : ���̻� ������(i=1���� ����: �츮�� �����Լ��� ����°� �����̹Ƿ� ù���� Ʋ���� 0��ġ�� �����ϹǷ�.)
		// j : ���λ� ������
		for(int i = 1, j = 0 ; i < pattern.length ; i++) {
			while(j != 0 && pattern[i] != pattern[j]) j = pArr[j - 1];
			if(pattern[i] == pattern[j]) pArr[i] = ++j;
		}
		// i : �ؽ�Ʈ ������
		// j : ���� ������ 
		for(int i = 0, j = 0 ; i < text.length ; i++) {
			while(j != 0 && text[i] != pattern[j]) j = pArr[j - 1];
			if(text[i] == pattern[j]) { //�� ���� ��ġ
				if(j == pattern.length - 1) {  // j�� ������ ������ �ε������ ���� ���ڰ� ��� ��ġ�� ��Ȳ
					cnt++;
					sb.append((i + 2 - pattern.length) + " ");
					j = pArr[j];		// �� ���� ���ϰ˻� �ϱ� ���� ininin���� inin�� ã�� ��� ù��° inin�� ã�� �� ���� ��ȿ�� �񱳸� �ϱ� ����
				} else {				// ���� ��ġ �߰� ����(���� ���� ��ġ�ϸ� �������� ��Ȳ)
					j++;				
				}
			}
		}
		
		System.out.println(cnt);
		if(cnt > 0)
			System.out.println(sb);
	}
}
