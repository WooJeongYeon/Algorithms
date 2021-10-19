import java.util.Scanner;
/*
 * Date : 210830
 */
public class SW4796_의석이의우뚝선산 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int TC = in.nextInt();
		final int UP = 0;
		final int DOWN = 1;
		for(int tc = 1 ; tc <= TC ; tc++) {
			int ans = 0;
			int left = 0, right = 0, now = 0, state = -1;
			int N = in.nextInt();
			int[] arr = new int[N];
			for(int i = 0 ; i < N ; i++) {
				arr[i] = in.nextInt();
			}
			for(int i = 1 ; i < N ; i++) {
				if(arr[i - 1] < arr[i]) {
					left = 2;
					now = i;
					state = UP;
					break;
				}
			}
			if(state == UP) {
				for(int i = now + 1 ; i < N ; i++) {
					if(state == UP) {
						if(arr[i - 1] > arr[i]) {
							left -= 1;
							right = 1;
							state = DOWN;
						}
						else left++;
					}
					else if(state == DOWN) {
						if(arr[i - 1] < arr[i]) {
							ans += left * right;
							left = 2;
							state = UP;
						}
						else right++;
					}
				}
			}
			if(state == DOWN) ans += left * right;
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
}
