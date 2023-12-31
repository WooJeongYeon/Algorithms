import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.08(재)
 * Level : BaekJoon 골드 4
 * Why : 좀 빨리풀려구...스터디에 내게
 * Result : 예전이 더 나은듯
 */
public class BJ1987_알파벳_재 {
	static int R, C, ans;
	static char[][] map;
	static boolean[] alpha;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static final int SIZE = 26;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		alpha = new boolean[SIZE];
		for(int i = 0 ; i < R ; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		alpha[map[0][0] - 'A'] = true; 
		dfs(0, 0, 1);
		
		System.out.println(ans);
	}
	static void dfs(int i, int j, int size) {
		if(size == R * C) {
			ans = size;
			return;
		}
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni >= 0 && ni < R && nj >= 0 && nj < C) {
//				System.out.println(map[ni][nj] - 'A');
				if(alpha[map[ni][nj] - 'A']) {
					ans = Math.max(ans, size);
					continue;
				}
				alpha[map[ni][nj] - 'A'] = true;
				dfs(ni, nj, size + 1);
				alpha[map[ni][nj] - 'A'] = false;
			}
		}
	}
}
