import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.20
 * Level : BaekJoon Silver 3
 * URL : https://www.acmicpc.net/problem/15655
 * Method : 조합(부분집합)
 */
public class BJ15655_N과M6 {
	static int N, M;
	static int[] input;
	static boolean[] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		input = new int[N];
		arr = new boolean[N];
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int i = 0 ; i < N ; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input);
		
		comb(0, 0);
		
		System.out.println(sb);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			for(int i = 0 ; i < N ; i++) {
				if(arr[i]) {
					sb.append(input[i] + " ");
				}
			}
			sb.append("\n");
			return;
		}
		if(idx == N) return;
		
		arr[idx] = true;
		comb(idx + 1, cnt + 1);
		arr[idx] = false;
		comb(idx + 1, cnt);
		
	}
}
