import java.util.Scanner;
/*
 * Date : 2021.11.04
 * Level : BaekJoon Bronze 2
 * Difficulty : 쉬움
 * Time : 14분 
 * URL : https://www.acmicpc.net/problem/13458
 * Thinking : long 사용해야댐. ans가 백만 * 백만 될 수 있어서
 * Method : 연산
 * Result : 정답비율 낮길래 어려운 문제인줄 알았는데 아니였다.
 * Plus1 : a를 먼저받길래 배열로 따로 저장했는데 그럴 필요없이 StringTokenizer로 받아왔으면 됬네..!!! 생각도 못함
 */
public class BJ13458_시험감독 {
	static int N, B, C;
	static int[] a;
	static long ans;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		a = new int[N];
		for(int i = 0 ; i < N ; i++) {		// 이걸 먼저 받길래 저장했는데... 배열로 안해도 되네..!
			a[i] = in.nextInt();
		}
		B = in.nextInt();
		C = in.nextInt();
		for(int i = 0 ; i < N ; i++) {
			a[i] = a[i] - B < 0 ? 0 : a[i] - B;
			int cnt = (a[i] + C - 1) / C;
			ans += (long)cnt + 1;
		}
		System.out.println(ans);
	}
}
