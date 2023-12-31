import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.17
 * Level : BaekJoon Silver 3
 * URL : https://www.acmicpc.net/problem/15654
 * Method : 순열
 */
public class BJ15654_N과M5 {
	static int N, M;
	static int[] data, result;
	static boolean[] choosed;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new int[N];
		result = new int[M];
		choosed = new boolean[N];
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < N ; i++) {
			data[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(data);
		perm(0);
		
		System.out.println(sb);
	}
	private static void perm(int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < M ; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int i = 0 ; i < N ; i++) {
			if(choosed[i]) continue;
			result[cnt] = data[i];
			choosed[i] = true;
			perm(cnt + 1);
			choosed[i] = false;
		}
		
	}
}
