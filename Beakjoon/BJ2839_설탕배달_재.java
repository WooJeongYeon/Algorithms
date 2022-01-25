import java.util.Scanner;
/*
 * Date : 2021.01.25(��)
 * Level : BaekJoon Bronze 1
 * URL : https://www.acmicpc.net/problem/2839
 * Thinking : 15(�����)�������� ������ �ؼ� ������ 5Ű�� ���� ���, ������ �� 3�� �����鼭 �������� Ȯ��
 * Method : �׸���?
 * Error1 : �ּ� ���� ���� ��ȯ�ϴ� �Ŷ� while������ 3���Ͱ� �ƴ϶� 5���� ����� ��
 * Result : �����Ŷ� ���� ���� Ǯ���µ� �ڵ�� ª���� 
 */
public class BJ2839_�������_�� {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int ans = 0;
		if(n >= 15) {
			ans += (n - 15) / 5;
			n = 15 + (n - 15) % 5;
		}
		while(n > 0) {
			if(n % 5 == 0) {		// 5���� ������
				ans += n / 5;
				n = 0;
				break;
			}
			ans++;
			n -= 3;
		}
		if(n < 0) ans = -1;
		System.out.println(ans);
	}
}
