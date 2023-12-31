// 두번째 푼거
// 210917 금

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ14502_연구소_재 {
	static int N, M, ans = Integer.MIN_VALUE;
	static int[][] map;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static ArrayList<Point> blank, virus;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		blank = new ArrayList<>();
		virus = new ArrayList<>();
		
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) blank.add(new Point(i, j));
				if(map[i][j] == 2) virus.add(new Point(i, j));
			}
		}
		visit = new boolean[blank.size()];
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int idx, int cnt) {
		if(cnt == 3) {
			int[][] simulMap = makeSimulMap();
			
			bfs(simulMap);
			
			int area = getSafeArea(simulMap);
			ans = Math.max(ans, area);
			return;
		}
		if(idx == blank.size()) return;
		visit[idx] = true;
		comb(idx + 1, cnt + 1);
		visit[idx] = false;
		comb(idx + 1, cnt);
	}
	
	static void bfs(int[][] simulMap) {
		
		Queue<Point> queue = new LinkedList<>();
		for(Point v : virus) {
			queue.offer(v);
		}
		
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for(int d = 0 ; d < 4 ; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if(!isOutOfIndex(ni, nj) && simulMap[ni][nj] == 0) {
					simulMap[ni][nj] = 1;
					queue.offer(new Point(ni, nj));
				}
			}
		}
	}
	
	static int getSafeArea(int[][] simulMap) {
		int sum = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(simulMap[i][j] == 0) sum++; 
			}
		}
		return sum;
	}
	
	static int[][] makeSimulMap() {
		int[][] simulMap = new int[N][];
		for(int i = 0 ; i < N ; i++) {
			simulMap[i] = map[i].clone(); 
		}
		
		for(int i = 0 ; i < blank.size() ; i++) {
			if(visit[i]) {
				simulMap[blank.get(i).i][blank.get(i).j] = 1;
			}
		}
		
		return simulMap;
	}
	
	static boolean isOutOfIndex(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= M;
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
