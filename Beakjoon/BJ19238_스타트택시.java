import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.25
 * Level : BaekJoon Gold 4
 * Difficulty : 생각해놓는게 좀 어려웠음
 * Time : 띄엄띄엄 풀었긴한데 2시간 좀 넘게 걸리지 않았을가?
 * URL : https://www.acmicpc.net/problem/19238
 * Select1 : 배열에 해당 승객의 인덱스를 변환해서 저장해두자(-승객인덱스 - 2) 왜냐면 0, 1이 빈칸/벽이고 -1은 안된다 표시로 반환해주려구
 * Thinking : 택시 위치 갱신, 갔던 승객은 map에 표시해두는정도
 * 		1. (M번 반복)승객 -> 목적지 거리저장(BFS)
 * 		2. (M번 반복)택시고고
 * 		┗━ 1) 택시로부터 가장 가까운 승객 선택 및 거리저장 - 여러개면 행번호 작은 - 여러개면 열번호 작은
 * 		┗━ 2) 승객까지의 거리 + 목적지까지의 거리(1번에서 구해놓은) 이동 가능한지
 * 		┗━ 3) 연료 충전
 * Method : BFS
 * Error1 : 거리가 같은 승객 여러명일때 행번호, 열번호로 처리해주는걸 까먹음
 * Result : 문제이해 + 만들 클래스 + 사용할 변수들 + 순서 + 주의할 점 정도 생각하고 풀었다. 
 * 			코드를 자세하게 생각한건 아니라 귀찮아서 미루다 짰는데 생각보다 바로되네 
 * Plus1 : BFS + 최단거리로도 풀 수 있을거같은데...
 */
public class BJ19238_스타트택시 {
	static int N, M, fuel, distTToP;
	static int[][] map;
	static boolean[][] visit;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static Person[] person;
	static Point taxi;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		person = new Person[M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(in.readLine(), " ");
		int ti = Integer.parseInt(st.nextToken()) - 1;		// 배열 인덱스에 맞게 저장(-1)
		int tj = Integer.parseInt(st.nextToken()) - 1;
		taxi = new Point(ti, tj);
		for(int m = 0 ; m < M ; m++) {
			st = new StringTokenizer(in.readLine(), " ");
			int si = Integer.parseInt(st.nextToken()) - 1;
			int sj = Integer.parseInt(st.nextToken()) - 1;
			int ei = Integer.parseInt(st.nextToken()) - 1;
			int ej = Integer.parseInt(st.nextToken()) - 1;
			map[si][sj] = -m - 2;		// 배열에 -m -2로 변환해서 저장해놓음
			person[m] = new Person(new Point(si, sj), new Point(ei, ej));
		}
		if(!go()) {						// 갈 수 없다면
			fuel = -1;					// fuel -1로 저장
		}
		System.out.println(fuel);		// 답 출력
		
	}
	
	static boolean go() {					// 가라!
		for(int m = 0 ; m < M ; m++) {		// m번 반복해 각 승객으로부터 도착지까지의 거리 저장해놓음
			int dist = getDist(person[m]);
			if(dist == -1) return false;	// 갈 수 없다면 false 반환
			person[m].dist = dist;
		}
		for(int m = 0 ; m < M ; m++) {		// m번 반복해
			int idx = choosePerson();		// 승객 선택
			if(idx == -1) return false;		// 선택된 승객이 없다면 false 반환
			idx = -(idx + 2);				// 승객 인덱스 디코딩
			
			fuel = fuel - distTToP - person[idx].dist;		// 사용한 연료를 빼주고
			if(fuel < 0) return false;						// 갈 수 없는 거리였다면 false 반환
			fuel += person[idx].dist * 2;					// 연료 충전
			
			map[person[idx].start.i][person[idx].start.j] = 0;	// 갔던 승객부분은 map에서 없애줌
			taxi = person[idx].end;							// 택시 위치 갱신
		}
		
		return true;						// 다 갔다면 true 반환
	}
	
	static int getDist(Person p) {							// 거리 얻기
		Queue<Point> queue = new LinkedList<>();			// BFS 할거라 queue 생성
		visit = new boolean[N][N];
		int len = 0;										// 거리
		queue.offer(p.start);								// 승객의 위치 추가해놓음
		visit[p.start.i][p.start.j] = true;
		while(!queue.isEmpty()) {
			int size = queue.size();					
			for(int s = 0 ; s < size ; s++) {
				Point now = queue.poll();
				if(now.i == p.end.i && now.j == p.end.j) {	// 도착지까지 갔으면 거리 반환
					return len;
				}
				for(int d = 0 ; d < 4 ; d++) {				// 상하좌우에 대해
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(isOutOfIdx(ni, nj) || visit[ni][nj] || map[ni][nj] == 1) continue;	// 인덱스 나가거나, 방문했거나, 못가면 패스
					visit[ni][nj] = true;					// 방문했음을 표시해줌
					queue.offer(new Point(ni, nj));
				}
			}
			len++;
		}
		
		return -1;											// 큐가 비었는데 도착지까지 못갔으면 불가한거
	}	
	
	static int choosePerson() {								// 승객 선택
		Queue<Point> queue = new LinkedList<>();
		visit = new boolean[N][N];
		distTToP = 0;										// 거리는 0으로 저장
		int i = N; int j = N;								// 거리가 가까운 승객이 여러명일 경우 사용할 i, j값
		queue.offer(taxi);									// 택시 위치를 넣음
		visit[taxi.i][taxi.j] = true;
		while(!queue.isEmpty()) {
			int size = queue.size();						// 같은 거리에 해당하는 위치들만 검사하기 위함
			for(int s = 0 ; s < size ; s++) {
				Point now = queue.poll();
				if(map[now.i][now.j] < -1) {				// 승객이라면
					if(i > now.i) {							// 더 작은 행 저장
						i = now.i;
						j = now.j;
					}
					else if(i == now.i) {					// 같은 행이면 더 작은 열을 저장
						j = Math.min(j, now.j);
					}
				}
				for(int d = 0 ; d < 4 ; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(isOutOfIdx(ni, nj) || visit[ni][nj] || map[ni][nj] == 1) continue;
					visit[ni][nj] = true;
					queue.offer(new Point(ni, nj));
				}
			}
			if(i != N || j != N) return map[i][j];			// 이번에 검사한 위치들 중 승객이 있다면 선택된 map값을 반환
			distTToP++;
		}
		
		return -1;			// 승객까지 갈 수 없는 경우
	}

	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}

	static class Person {						 // 승객은 시작, 도착 위치와 그 사이의 거리를 가짐
		Point start, end;
		int dist;
		public Person(Point start, Point end) {
			this.start = start;
			this.end = end;
		}
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
