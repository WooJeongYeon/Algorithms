package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210817
 */
public class SW7964_DimensionalGate {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		final int TC = Integer.parseInt(in.readLine());		// 테스트케이스를 입력받음
		for(int tc = 1 ; tc <= TC ; tc++) {					// TC만큼 반복
			 st = new StringTokenizer(in.readLine());
			 int N = Integer.parseInt(st.nextToken());		// N : 도시수
			 int D = Integer.parseInt(st.nextToken());		// D : 이동제한거리
			 int d = 0, ans = 0;							// d는 전의 차원관문으로부터 현재 도시까지의 거리, ans는 필요한 차원관문의 개수
			 st = new StringTokenizer(in.readLine());
			 for(int i = 0 ; i < N ; i++) {					// 도시의 상태를 받아옴
				 int now = Integer.parseInt(st.nextToken());
				 if(now == 1) d = 0;		// 차원거리면 d는 0으로
				 else d++;					// 차원거리가 아니라면 d를 증가
				 if(d == D) {				// d가 D와 같다면 차원관문이 필요하므로
					 d = 0;					// d를 0으로 리셋하고
					 ans++;					// 필요한 차원관문의 수 증가
				 }
			 }
			 sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
}
