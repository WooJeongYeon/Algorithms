package day0815;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210815
 */
public class BJ1592_YoungSikAndFriends {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		final int L = Integer.parseInt(st.nextToken());
		int result = 0, pos = 0, now = 0;
		int[] friends = new int[N];		// 각 친구들의 던진 횟수를 저장할 배열
		while(true) {
			friends[pos]++;				// 던진 횟수를 증가
			now = friends[pos];			// 현재 친구의 던진 횟수를 저장
			if(now == M) break;			// M번 던졌다면 중단
			if(now % 2 == 0) pos = (pos - L) < 0 ? pos + N - L : pos - L;	// 짝수이면 왼쪽으로 L만큼 던짐
			else pos = (pos + L) % N;	// 홀수이면 오른쪽으로 L만큼 던짐
			result++;					// 던진 횟수 증가
		}
		System.out.println(result);
	}
}
