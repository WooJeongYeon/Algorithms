

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.03
 * Level : BaekJoon Gold 4
 * Difficulty : 중간
 * Time : 1시간 30분
 * URL : https://www.acmicpc.net/problem/20058
 * Select1 : 메소드를 엄청 나눠놓은거..?
 * Thinking : 그냥 필요한거 차례대로 추가하기 시작
 * Method : 시뮬레이션, BFS
 * Error1 : 문제 잘못 읽음 - 덩어리 없을 때 0출력하라는게 어따 0출력하라는겨 -> 2번째줄
 * Error2 : 얼음 녹이는게 DFS나 BFS 써서 이어진거 녹이는게 아니라 상하좌우에 얼음이 2개 이하일 때 녹이는거네.. -> 단순 반복
 * Error3 : 그래서 DFS -> BFS로 갈아엎었는데(마지막에 덩어리 구하는 데 필요) -> 방문 체크용 0 저장 안해줬고, 인덱스 틀렸네(i랑 now.i) 
 * Error4 : (논리적 오류)얼음 녹일 때 매번 1씩 감소시키는게 아니라, 녹일 얼음 다 선택해두고 한번에 녹여야 함
 * Result : 문제가 그렇게 어렵지는 않은데 문제 이해하는게 어려웠음. 질문 검색 보고 문제 이해했다...		
 * 			- 그냥 생각만 대충 하고 안적고 바로바로 모듈마다 구현 및 테스트함 
 */
public class BJ20058_마법사상어와파이어스톰 {
	static int N, Q;
	static int[][] map;
	static boolean[][] isMelt;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = 1 << Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(in.readLine(), " ");
		for(int n = 0 ; n < Q ; n++) {
			int l = Integer.parseInt(st.nextToken());
			fireStorm(l);
		}
		
		System.out.println(getIce());
		int ans = getIceMassMax();
		if(ans == 1) ans = 0;					// 얼음덩어리가 없으면 0으로 저장(크기 2 이상일 때 덩어리라 함)
		System.out.println(ans);
	}
	
	static void fireStorm(int l) {				// 파이어스톰!
		rotate(l);
		meltIce();
	}
	
	static void rotate(int size) {				// 각 배열 구역들 회전
		int[][] simulMap = copyMap(map);
		size = 1 << size;						// 2 ^ size
		for(int m = 0 ; m < N ; m += size) {
			for(int n = 0 ; n < N ; n += size) {
				for(int i = 0 ; i < size ; i++) {
					for(int j = 0 ; j < size ; j++) {
						simulMap[j + m][size - i - 1 + n] = map[i + m][j + n];
					}
				}
			}
		}
		
		map = simulMap;
	}
	
	static void meltIce() {								// 얼음 녹이기
		isMelt = new boolean[N][N];						// 녹일 얼음 표시해줌
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] == 0) continue;
				int cnt = 0;
				for(int d = 0 ; d < 4 ; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if(isOutOfIdx(ni, nj)) continue;
					if(map[ni][nj] > 0) cnt++;
				}
				if(cnt < 3) isMelt[i][j] = true;
			}
		}
		for(int i = 0 ; i < N ; i++) {					// 표시한 얼음 녹이기
			for(int j = 0 ; j < N ; j++) {
				if(isMelt[i][j]) map[i][j]--;
			}
		}
	}
	
	static int getIce() {								// 맵에서 얼음 전체 양
		int sum = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	static int getIceMassMax() {						// 가장 큰 얼음덩어리 구하기
		int size = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] == 0) continue;
				size = Math.max(size, bfs(i, j));
			}
		}
		return size;
	}
	
	static int bfs(int i, int j) {						// 해당 얼음덩어리 크기 반환해줌
		Queue<Point> queue = new LinkedList<>();
		int size = 0;
		map[i][j] = 0;									// 더이상 map 쓸 일 없어서 visit배열 따로 안만들고 방문표시 0으로 저장
		queue.offer(new Point(i, j));
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			size++;
			for(int d = 0 ; d < 4 ; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if(isOutOfIdx(ni, nj) || map[ni][nj] == 0) continue;
				map[ni][nj] = 0;
				queue.offer(new Point(ni, nj));
			}
		}
		return size;
	}
	
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static int[][] copyMap(int[][] map) {
		int[][] simulMap = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			simulMap[i] = map[i].clone();
		}
		return simulMap;
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
