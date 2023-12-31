import java.util.Scanner;
/*
 * Date : 2021.01.25(재)
 * Level : BaekJoon Bronze 1
 * URL : https://www.acmicpc.net/problem/2839
 * Thinking : 15(공배수)언저리로 남도록 해서 가능한 5키로 봉지 담고, 나머지 중 3을 빼가면서 가능한지 확인
 * Method : 그리디?
 * Error1 : 최소 봉지 개수 반환하는 거라 while문에서 3부터가 아니라 5부터 빼줘야 함
 * Result : 예전거랑 거의 같게 풀었는데 코드는 짧아짐 
 */
public class BJ2839_설탕배달_재 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int ans = 0;
		if(n >= 15) {
			ans += (n - 15) / 5;
			n = 15 + (n - 15) % 5;
		}
		while(n > 0) {
			if(n % 5 == 0) {		// 5부터 빼야함
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
