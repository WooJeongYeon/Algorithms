package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210829
 */
public class BJ9372_상근이의여행 {
	static int TC, N, M;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(in.readLine());
			}
			sb.append((N - 1) + "\n");
		}
		System.out.println(sb);
	}
}