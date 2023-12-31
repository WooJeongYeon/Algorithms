
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.04
 * Level : BaekJoon 실버 1
 * Error2 : BFS로 풀려했는데 시간초과남. 10만 넘어가니까 계속 끝없이 1씩 더하고있드라...
 */
public class BJ1697_숨바꼭질_DFS {
	static int N, K;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		memo = new int[2 * K];
		Arrays.fill(memo, Integer.MAX_VALUE);
				
		if(N >= K) memo[K] = N - K;
		else dfs(N, 0);

		System.out.println(memo[K]);
	}
	
	static void dfs(int now, int time) {
		memo[now] = time;
//		System.out.println(now);
		if(now == K) {
			return;
		}
		if(now < K && memo[now * 2] > time + 1) {
			dfs(now * 2, time + 1);
		}
		if(now + 1 < 2 * K && memo[now + 1] > time + 1) {
			dfs(now + 1, time + 1);
		}
		if(now - 1 >= 0 && memo[now - 1] > time + 1) {
			dfs(now - 1, time + 1);
		}
	}
}
