// TSP문제지만 N의 값이 작아서 순열로 풀이 진행
// 처음 시작에 visit 체크를 안해줬어서 수정

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210908
 */
public class BJ10971_외판원순회2 {
	static int N;
	static int[][] w;
	static int start;
	static boolean[] visit;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		w = new int[N][N];
		visit = new boolean[N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				w[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 첫번째 도시를 포함시킴(perm에서 첫번째 도시를 포함시키러면 따로 처리해야 하므로)
		for(int i = 0 ; i < N ; i++) {
			start = i;
			visit[i] = true;
			perm(1, start, 0);
			visit[i] = false;
		}
		System.out.println(result);
	}
	static void perm(int idx, int now, int value) {
		if(value >= result) return;					// 최솟값 넘어버리면 return해서 백트레킹!(나중에 추가)
		if(N == idx && w[now][start] != 0) {		// 모든 도시를 포함시키고 맨 처음 도시로 돌아갈 수 있다면 
			result = Integer.min(result, value + w[now][start]);	// 비용을 비교해 적은 비용의 결과를 저장
			return;
		}
		for(int i = 0 ; i < N ; i++) {						// 모든 도시에 대해
			if(visit[i] || w[now][i] == 0) continue;		// 아직 해당 도시를 방문하지 않았고, 길이 있다면
			visit[i] = true;					
			perm(idx + 1, i, value + w[now][i]);			// 방문!
			visit[i] = false;
		}
	}
}
