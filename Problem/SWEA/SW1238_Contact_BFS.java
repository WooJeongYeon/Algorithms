// Graph 탐색방법 - BFS 사용(QUEUE)
// Graph 저장방법 - 인접행렬 사용(100 * 100)

package day0823;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * Date : 210823
 */
public class SW1238_Contact_BFS {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		final int SIZE = 100;			// 100개의 정점이 존재
		final int TC = 10;				// TC 10번 실행
		for(int tc = 1 ; tc <= TC ; tc++) {
			int N = in.nextInt();		// 데이터 길이
			int start = in.nextInt() - 1;	// 시작점(1부터 시작하므로 -1)
			boolean[][] graph = new boolean[SIZE][SIZE];	// 그래프를 저장(i -> 시작점, j -> 도착점)
			boolean[] isPass = new boolean[SIZE];			// 해당 정점을 지났는지를 표시
			int max = Integer.MIN_VALUE;					// 결과를 저장
		
			for(int i = 0 ; i < N / 2 ; i++) {				// 그래프에 결과 저장
				graph[in.nextInt() - 1][in.nextInt() - 1] = true;	// 시작점, 도착점에 해당하는 값을 true로 저장
			}
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.offer(start);								// 큐에 시작점을 넣음
			isPass[start] = true;							// 방문처리
			while(!queue.isEmpty()) {						// 큐가 빌때까지 반복
				int size = queue.size();					// 같은 너비의 정점들에 대해 반복하기 위해 size 저장
				max = Integer.MIN_VALUE;					// 매 너비마다 가장 높은 값을 저장함(마지막 너비에서의 높은 값이 정답임)
				for(int i = 0 ; i < size ; i++) {			// 해당 너비의 정점들에 대해 반복
					int now = queue.poll();					// 큐에서 요소를 빼서 저장 
					max = Integer.max(max, now);			// 더 큰 값 저장
					for(int j = 0 ; j < SIZE ; j++) {		// 1~100의 정점에 대해 
						if(graph[now][j] && !isPass[j]) {	// 해당 정점으로 갈 수 있고, 아직 지나지 않았다면
							isPass[j] = true;				// 방문처리해주고
							queue.offer(j);					// 큐에 추가
						}
					}
				}
			}
			
			sb.append("#" + tc + " " + (max + 1) + "\n");	// StringBuilder에 결과 추가
		}
		System.out.println(sb);
	}
}
