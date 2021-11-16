import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.15(재)
 * Level : BaekJoon Gold 2
 * Difficulty : 상
 * URL : https://www.acmicpc.net/problem/17472
 * Select1 : 
 * Thinking :
 * 		1. 섬 만들기 - DFS로 이중 ArrayList에 저장(섬의 개수, 크기 모르므로 ArrayList 사용)
 * 		2. Edge 만들기 - 어디 칸을 선택하는지는 중요하지 않고, 섬 번호 + 가능한 최단길이만 알면 됨.
 * 		3. Edge 선택하기(Prim)
 * Method : DFS, MST(Prim), 시뮬레이션 
 * Error1 : 테케 다 되는디. 테케 만들다 찾음 -> 인접행렬 만들 때 두 섬의 최소길이로 저장 안하고 길이 존재하면 무조건 갱신하고 있었네...
 * Error2 : 왜ㅠㅠㅠ 이건 테케 다른사람거 돌려봄 -> map[i][j]이 1이라(섬이 존재) 짤라도 len이 2가 넘어가는 경우, edge로 저장해버림 -> boolean 변수 사용
 * Result : 방법 다 쓰고 구현해서 위에 에러들도 다 써놨던건데 코드에 적용이 안됬었음ㅠㅠㅠ 담부턴 제대로 적용하자... 
 * Plus1 : 다른 분 코드 보니까 map에 섬 번호 저장해두고 각 섬 칸에서 상하좌우로 가서 다른 섬 만나면 edge로 저장하네.. 이런 방법이!
 */
public class BJ17472_다리만들기_재 {
	static int N, M, islandNum, ans;
	static int[][] map, adjArr;
	static boolean[][] visit;
	static ArrayList<ArrayList<Point>> islands;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		ans = INF;
		islands = new ArrayList<ArrayList<Point>>();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeBridge();
		
		System.out.println(ans);
	}
	
	static void makeBridge() {
		makeIsland();
//		printIslands();
		setEdge();
//		printArr();
		prim();
	}
	
	static void makeIsland() {				// ArrayList에 섬 만들어 놓음
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(!visit[i][j] && map[i][j] == 1) {
					islandNum++;
					islands.add(new ArrayList<Point>());
					dfs(i, j);
				}
			}
		}
	}
	
	static void dfs(int i, int j) {
		visit[i][j] = true;
		islands.get(islands.size() - 1).add(new Point(i, j));
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= M || visit[ni][nj] || map[ni][nj] != 1)
				continue;
			dfs(ni, nj);
		}
	}
	
	static void setEdge() {
		adjArr = new int[islandNum][islandNum];
		for(int i = 0 ; i < islandNum ; i++) {
			Arrays.fill(adjArr[i], INF);
		}
		
		for(int i = 0 ; i < islandNum - 1 ; i++) {			// 5중for문...?! - 섬 2개 선택 그 중 각 섬의 칸 선택 + 그 사이 섬이 있는지 확인
			ArrayList<Point> island1 = islands.get(i);
			for(int j = i + 1 ; j < islandNum ; j++) {
				ArrayList<Point> island2 = islands.get(j);
				int min = INF;
				for(Point p1 : island1) {
					for(Point p2 : island2) {
						int len = 0;
						boolean isPossible = true;
						if(p1.i == p2.i) {
							int start = Math.min(p1.j, p2.j);
							int end = Math.max(p1.j, p2.j);
							for(int c = start + 1 ; c < end ; c++) {
								if(map[p1.i][c] == 1) {
									isPossible = false;
									break;
								}
								len++;
							}
						} else if(p1.j == p2.j) {
							int start = Math.min(p1.i, p2.i);
							int end = Math.max(p1.i, p2.i);
							for(int r = start + 1 ; r < end ; r++) {
								if(map[r][p1.j] == 1) {
									isPossible = false;
									break;
								}
								len++;
							}
						}
						if(isPossible && len >= 2 && min > len) {
							min = len;
						}
					}
				}
				adjArr[i][j] = min;			// 양방향 그래프
				adjArr[j][i] = min;
			}
		}
	}
	
	static void prim() {
		int[] minArr = new int[islandNum];
		Arrays.fill(minArr, INF);
		boolean[] visited = new boolean[islandNum];
		int nowIdx, nowWeight;
		minArr[0] = 0;
		int cnt = 0, sum = 0;
		while(true) {
			nowIdx = -1;
			nowWeight = INF;
//			1. 최솟값인 정점 + 최솟값 선택
			for(int i = 0 ; i < islandNum ; i++) {
				if(!visited[i] && minArr[i] < nowWeight) {
					nowIdx = i;
					nowWeight = minArr[i];
				}
			}
			
//			2. break처리, 선택한 정점 visit처리, 최솟값 더함(ans에), idx, weight 갱신
			if(nowWeight == INF) {		// 더이상 선택할 수 있는 정점이 없음
				sum = -1;
				break;
			}
			visited[nowIdx] = true;
			sum += nowWeight;
			if(++cnt == islandNum) break;
			
//			3. 선택한 정점으로부터 최솟값들 갱신
			for(int i = 0 ; i < islandNum ; i++) {
				if(!visited[i]) {
					minArr[i] = Math.min(minArr[i], adjArr[nowIdx][i]);
				}
			}
		}
		ans = sum;
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static void printIslands() {
		int idx = 0;
		for(ArrayList<Point> list : islands) {
			System.out.println(idx++ + "번째 섬-----------------");
			for(Point p : list) {
				System.out.println(p.i + " " + p.j);
			}
		}
	}
	
	static void printArr() {
		System.out.println("인접행렬--------------------------");
		for(int i = 0 ; i < islandNum ; i++) {
			for(int j = 0 ; j < islandNum ; j++) {
				System.out.print(adjArr[i][j] + " ");
			}
			System.out.println();
		}
	}
}
