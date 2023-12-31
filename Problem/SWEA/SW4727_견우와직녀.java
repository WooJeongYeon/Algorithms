import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * Date : 2021.10.17
 * Level : SWEA D4
 * URL : https://swexpertacademy.com/main/code/userProblem/userProblemDetail.do?contestProbId=AWSHOpR6f_sDFARw&categoryId=AWSHOpR6f_sDFARw&categoryType=CODE&&&
 * Select1 : 
 * Thinking) 원숭이, 벽뚫기 문제와 비슷하나, 쉬는시간때는 못지나가는게 다르네
 * 		- 고려사항 : 오작교 두번 연속으로 못감
 * 		 			오작교 쉬는시간 T일 때 0, T, 2T... 이 때만 지나갈 수 있음
 * 					다리 한 번 놓을 수 있음
 * 		- t % T == 0일 때만 해당 칸으로 갈 수 있고, 아니면 다시 now를 queue에 넣음
 * 		- visit에 다리 만들 수 있는 횟수 큰값 저장되도록(-1(초기값), 0(다리 만들었음), 1(다리 아직 안만듬))
 * Method : BFS(이동하는 최소 시간)
 * Error1 : now가 여러번 들어감 -> now칸에서 상하좌우에 대해 오작교나 절벽(다리 아직 안만들어서 건너갈 수 있을 때)이 여러개면 now 여러번 들어가면서 쉬는시간동안 증식...
 * Error2 : 런타임 에러 -> 또 증식중... -> visit[ni][nj] >= now.cnt 같을 때도 패스해야 함(다시 방문하지 않도록)
 * Error3 : 각 오작교 쉬는시간 다 끝나지도 않았는데 지나가버림 -> 오작교인 경우, (time + 1) % map[ni][nj] == 0로 해야 함(% M 했었음..)
 * Error4 : 오작교 연속으로 두번 못건넘
 * Result : 써놓고 한건데... 문제 이해하는데도 오래걸리고 생각보다 코드도 지저분하고 디버깅도 많이한듯. 증식은 생각도 안해놨었네. 1시간 40분정도 걸린듯ㅠ
 */
public class SW4727_견우와직녀 {
	static int TC, N, M;
	static int[][] map, visit;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		TC = in.nextInt();
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = in.nextInt();
			M = in.nextInt();
			map = new int[N][N];
			visit = new int[N][N];
			for(int i = 0 ; i < N ; i++) {
				Arrays.fill(visit[i], -1);			// 다리 건널 수 있는 최대 횟수 저장(0, 1도 저장하기 위해 -1로 초기화)
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = in.nextInt();
				}
			}
			
			sb.append("#" + tc + " " + bfs() + "\n");
		}
		System.out.println(sb);
	}
	static int bfs() {								// bfs ㄱㄱㄱ
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 1));			// 처음 위치 추가(다리 만들 수 있는 횟수 1)
		visit[0][0] = 1;
		int time = 0;
		while(!queue.isEmpty()) {		
			int size = queue.size();
			for(int s = 0 ; s < size ; s++) {
				Point now = queue.poll();
				boolean isPutMe = false;			// now를 한번만 집어넣기 위함
//				System.out.println(time + " " +now.i + " " + now.j + " " + now.cnt + " ");
				if(now.i == N - 1 && now.j == N - 1) return time;		// 목표 위치에 도달하면 time 리턴!
				
				for(int d = 0 ; d < 4 ; d++) {		// 4가지 위치에 대해
					int ni = now.i + di[d];
					int nj = now.j + dj[d];	
					if(isOutOfIdx(ni, nj) || visit[ni][nj] >= now.cnt) continue;	// 인덱스 나가거나, 이 위치에 저장된 횟수 값이 같거나 더 크다면 -> 갈 필요 X
					if(map[ni][nj] == 1) {			// 갈 수 있으면
						visit[ni][nj] = now.cnt;	// visit 갱신
						queue.offer(new Point(ni, nj, now.cnt));					// 큐에 넣음
					}
					else if(map[ni][nj] == 0 && now.cnt == 1 && map[now.i][now.j] == 1) {	// 0이고, 다리를 놓을 수 있으면, 이전 값이 오작교가 아니라면
						if((time + 1) % M == 0) {									// 현재 쉬는시간이 아니라면
							visit[ni][nj] = 0;										// 다리를 놓으므로 0 저장
							queue.offer(new Point(ni, nj, 0));						// 큐에 넣음
						}
						else if(!isPutMe){						// 아직 now를 큐에 넣지 않았었다면
							isPutMe = true;						// now를 넣었음을 표시하고
							queue.offer(now);					// 넣음(쉬는시간 대기하기 위해)
						}
					}
					else if(map[ni][nj] > 1 && map[now.i][now.j] == 1) {			// 오작교가 있고, 이전 위치가 오작교가 아니라면
						if((time + 1) % map[ni][nj] == 0) {							// 쉬는시간이 아니라면
							visit[ni][nj] = now.cnt;								// 지나갑니다..
							queue.offer(new Point(ni, nj, now.cnt));
						}
						else if(!isPutMe){						// 아직 now를 큐에 넣지 않았었다면
							isPutMe = true;						// now를 넣었음을 표시하고
							queue.offer(now);					// 넣음(쉬는시간 대기하기 위해)
						}
					}
				}
			}
			
			time++;								// 시간 증가
		}
		return time;							// 답은 무조건 있다고 했으니까 사실 여기까지 올 일이없음
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static class Point {
		int i, j, cnt;

		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;							// 다리 만들 수 있는 횟수
		}
	}
}
