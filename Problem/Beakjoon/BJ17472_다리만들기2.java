// preiod : 210829 ~ 210917
// error1 : 섬과 섬 사이에 거리 잴 때 그 사이에 섬 있어도 이을 수 있다고 해버림
// error2 : 섬이 여러 방향으로 뻗어있는 경우를 고려하지 않음 -> 섬과 섬의 모든 부분에 대해 비교해서 최솟값 edge로 만들어아 함
// error3 : 또 틀려서 왜 틀렸는지 모르겠는데 우선순위큐로 바꾸고 코드 정리하니까 해결됨

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17472_다리만들기2 {
	static int N, M;						// 땅 크기
	static int[][] map;
	static ArrayList<Island> islandList;	// 섬 리스트
	static ArrayList<Edge> edgeList;		// 엣지 리스트
	static int ans;
	static int[] parents;					// disjoint에 사용할 부모를 저장하는 배열
	static int[] di = {-1, 1, 0, 0};		// 위 아래 왼 오
	static int[] dj = {0, 0, -1, 1};
	static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;		// 방향 표시
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		islandList = new ArrayList<>();
		edgeList = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {		// 맵을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) map[i][j] = -1;		// 맵이 1이면 -1로 저장(맵에 섬번호 표시하기 위함)
			}
		}
		
		findIslands();						// 1. 섬을 찾고
		makeEdgeList();						// 2. 엣지를 만들고
		kruskal();							// 3. MST 알고리즘 쓴다
		
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < M ; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		for(Island i : islandList) {
//			System.out.println(i.ground.size());
//		}
//		for(Edge e : edgeList) {
//			System.out.println(e);
//		}
		
		System.out.println(ans);			// 결과 출력
	}
	
	static void findIslands() {				// 섬 찾기(BFS)
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == -1) {		// 섬이 가능하면
					Point now = new Point(i, j);
					islandList.add(new Island(new ArrayList<>()));		// 섬을 리스트에 추가해서
					islandList.get(Island.num - 1).ground.add(now);		// 해당 섬의 땅 위치를 추가해줌(첫번째)
					map[i][j] = islandList.get(Island.num - 1).id;		// 맵에 섬 번호 표시(첫번째)
					Queue<Point> queue = new LinkedList<Point>();			
					queue.offer(now);
					while(!queue.isEmpty()) {							// 모든 땅들을 섬에 추가
						now = queue.poll();
						for(int d = 0 ; d < 4 ; d++) {
							int ni = now.i + di[d];
							int nj = now.j + dj[d];
							if(ni >= 0 && ni < N && nj >= 0 && nj < M && map[ni][nj] == -1) {
								Point newP = new Point(ni, nj);
								map[ni][nj] = islandList.get(Island.num - 1).id;
								islandList.get(Island.num - 1).ground.add(newP);
								queue.offer(newP);
							}
						}
					}
				}
			}
		}
	}
	
	static void makeEdgeList() {										// 엣지를 만들자
		for(int i = 0 ; i < Island.num - 1 ; i++) {						// 모든 섬과 모든 섬을 비교(한번씩)
			for(int j = i + 1 ; j < Island.num ; j++) {	
				Island i1 = islandList.get(i);							// 섬을 하나씩 정해서
				Island i2 = islandList.get(j);
				findEdges(i1, i2);										// 그 섬 사이의 가장 짧은 엣지를 구하자
			}
		}
	}
	
	static void findEdges(Island i1, Island i2) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();					// 엣지 중 가장 짧은 엣지를 구하기 위해 우선순위큐 사용
		for(Point p1 : i1.ground) {										// 섬의 모든 땅에 대해
			for(Point p2 : i2.ground) {									// 다른 섬의 모든 땅과 비교
				boolean isPossible = true;								
				int d = -1;
				int dist = -1;
				if(p1.i == p2.i) {										// 행이 같다면
					if(p1.j < p2.j) d = RIGHT;							// 어느 방향으로 검사할건지를 저장
					else d = LEFT;
					dist = Math.abs(p1.j - p2.j) - 1;
//					System.out.println(i1.id + ": " + p1.j + ", " + i2.id + ": " + p2.j + " " + dist);
					for(int j = p1.j + dj[d] ; j != p2.j ; j += dj[d]) {	// 각 땅 사이에
						if(map[p1.i][j] != 0) {								// 섬의 땅이 있다면
							isPossible = false;								// 엣지 불가능
							break;
						}
					}
				}
				else if(p1.j == p2.j) {									// 열이 같다면...
					if(p1.i < p2.i) d = DOWN;
					else d = UP;
					dist = Math.abs(p1.i - p2.i) - 1;
//					System.out.println(i1.id + ": " + p1.i + ", " + i2.id + ": " + p2.i + " " + dist);
					for(int i = p1.i + di[d] ; i != p2.i ; i += di[d]) {
						if(map[i][p1.j] != 0) {
							isPossible = false;
							break;
						}
					}
				}
				if(!isPossible || dist < 2) continue;					// 거리가 2보다 작거나, 섬을 이을 수 없다면 패스
//					for(int i = maxI + 1 ; i < minI ; i++) {
//						map[i][p1.j] = 9;
//					}
				pq.add(new Edge(i1.id, i2.id, dist));					// 가능하면 엣지 추가
			}
		}
		if(pq.size() == 0) return;										// 구한 엣지가 없다면 리턴
		edgeList.add(pq.poll());										// 있다면 가장 작은 엣지 추가
	}
	
	static void make() {							
		parents = new int[Island.num + 1];
		for(int i = 0 ; i <= Island.num ; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	static void kruskal() {										// kruskal 알고리즘(최소 신장 트리 찾기)
		int cnt = 0;
		Collections.sort(edgeList);								// 엣지를 정렬하고
		make();													
		for(Edge e : edgeList) {								// 모든 엣지에 대해
			if(union(e.start, e.end)) {							// 이을 수 있다면(순환이 없다면)
				ans += e.dist;									// 해당 엣지를 퐇마
				if(++cnt == Island.num - 1) break;				// MST를 만들었으면 끝
			}
		}
		if(cnt != Island.num - 1) ans = -1;						// 만들지 못했다면 -1 저장
	}
	
	static class Point {										// Point 클래스
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static class Island {										// 섬 클래스
		int id;													// 섬 번호(1부터)
		ArrayList<Point> ground;								// 섬에 속한 땅들을 모두 저장하는 리스트
		static int num;											// 섬의 개수를 저장(static으로 만들어 자동증가)
		public Island(ArrayList<Point> ground) {
			num++;
			this.id = num;
			this.ground = ground;
		}
	}
	static class Edge implements Comparable<Edge> {				// 엣지 클래스
		int start, end;
		int dist;
		public Edge(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
		@Override
		public int compareTo(Edge o) {							// Kruskal 알고리즘에서 엣지를 정렬시키기 위해 구현
			return this.dist - o.dist;
		}
		@Override
		public String toString() {
			return "Edge [start=" + (start + 1) + ", end=" + (end + 1) + ", dist=" + dist + "]";
		}
	}
}