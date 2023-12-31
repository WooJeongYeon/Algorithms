package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210825
 */
public class BJ14696_딱지치기 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();	
		int R = Integer.parseInt(in.readLine());			// 딱지치기 라운드 수
		int result = 0;					// 결과 저장
		for(int r = 0 ; r < R ; r++) {	// 라운드만큼 반복
			int[] a = new int[5];		// 1 ~ 4의 인덱스에 각가의 모양이 몇개인지 저장
			int[] b = new int[5];
			st = new StringTokenizer(in.readLine());
			int n = Integer.parseInt(st.nextToken());
			for(int i = 0 ; i < n ; i++) {
				a[Integer.parseInt(st.nextToken())]++;
			}
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			for(int i = 0 ; i < n ; i++) {
				b[Integer.parseInt(st.nextToken())]++;
			}
			result = match(a, b);		// 승부!
			sb.append((result == 1 ? 'A' : (result == -1 ? 'B' : 'D')) + "\n");	// 1이면 A, -1이면 B, 0이면 D를 추가
		}
		System.out.println(sb);
	}
	static int match(int[] a, int[] b) {
		for(int i = 4 ; i >= 1 ; i--) {	// 4 - 3 - 2 - 1순으로 승부(별 - 동그라미 - 네모 - 세모)
			if(a[i] > b[i]) return 1;	// a의 모양이 더 많으면 a 승리
			else if(a[i] < b[i]) return -1;	// b 모양이 더 많으면 b 승리
		}
		return 0;						// 모두 같으면 무승부
	}
}
