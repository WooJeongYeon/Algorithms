
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
 * Thinking : N >= K면 ans = N - K, 아니면 가장 빠른 시간 출력하는 거므로 BFS 돌려서 가장 먼저나온게 답 
 * Method : DFS
 * Result : 예전 스터디문제였는데 못풀었었음... memo에 어떻게 저장해야하는지나 좀 계산으로 풀려했어서...
 * Error1 : 틀림 -> 배열을 K보다 길게 사용해야 함
 * Error2 : BFS로 풀려했는데 시간초과남. 10만 넘어가니까 계속 끝없이 1씩 더하고있드라...
 * Plus1 : 다들 배열크기 100001로 잡고 했네... 안될거같은데 왜되는거지? 나는 2 * K로함
 * Plus2 : DP로도 풀 수 있다는데 패스...
 */
public class BJ1697_숨바꼭질_BFS {
	static int N, K;
	static int[] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		memo = new int[2 * K];
		Arrays.fill(memo, Integer.MAX_VALUE);
				
		int ans = N - K;

		if(N < K) ans = bfs();

		System.out.println(ans);
	}
	
	static int bfs() {							// 동생(K)에서 수빈(N)이 찾아가도록 함
		Queue<Integer> queue = new LinkedList<>();
		int time = 0;
		queue.offer(K);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int s = 0 ; s < size ; s++) {
				int now = queue.poll();
				if(now == N) return time;		// 답 나오면 중단
				if(now + 1 < 2 * K && memo[now + 1] > time) {
					memo[now + 1] = time;
					queue.offer(now + 1);
				}
				if(now - 1 >= 0 && memo[now - 1] > time) {
					memo[now - 1] = time;
					queue.offer(now - 1);
				}
				if(now % 2 == 0 && (now / 2) >= 0 && memo[now / 2] > time) {
					memo[now / 2] = time;
					queue.offer(now / 2);
				}
			}
//			System.out.println(Arrays.toString(memo));
			time++;
		}
		
		return time;
	}
}
