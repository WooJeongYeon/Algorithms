package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210825
 */
public class SW6485_삼성시의버스노선 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		StringTokenizer st;
		int TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			int N = Integer.parseInt(in.readLine());// 버스 노선의 개수
			int[] v = new int[5001];				// 버스 정류장 개수만큼(노선들이 몇번 지나가는지를 저장)
			for(int n = 0 ; n < N ; n++) {			// 버스 노선만큼 반복
				st = new StringTokenizer(in.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				for(int i = start ; i <= end ; i++) {		// 해당 버스 노선이 지나가는 정류장에 대해 1씩 증가
					v[i]++;
				}
			}
			int P = Integer.parseInt(in.readLine());
			sb.append("#" + tc + " " );
			for(int p = 0 ; p < P ; p++) {					// 주어진 버스 정류장에 몇 개의 버스 노선이 지나가는지를 결과에더함
				int n = Integer.parseInt(in.readLine());
				sb.append(v[n] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
