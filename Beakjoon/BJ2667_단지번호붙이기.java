import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Date : 210922
 * Thinking : 최대 단지수, 각 단지에 속하는 집의 수 오름차순(매 줄마다)
 * Method : BFS
 */
public class BJ2667_단지번호붙이기 {
	static int N;
	static char[][] map;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static ArrayList<Integer> houseList;
	static int areaNum;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		map = new char[N][N];
		houseList = new ArrayList<>();
		for(int i = 0 ; i < N ; i++) {
			map[i] = in.readLine().toCharArray();
		}
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] == '1') {
					areaNum++;
					bfs(i, j);
				}
			}
		}
		
		Collections.sort(houseList);
		System.out.println(areaNum);
		for(Integer n : houseList) {
			System.out.println(n);
		}
	}
	static void bfs(int i, int j) {
		Queue<Point> queue = new LinkedList<>();
		int houseNum = 1;
		map[i][j] = '0';
		queue.offer(new Point(i, j));
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			for(int d = 0 ; d < 4 ; d++) {
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if(!isOutOfIndex(ni, nj) && map[ni][nj] == '1') {
					map[ni][nj] = '0';
					houseNum++;
					queue.offer(new Point(ni, nj));
				}
			}
		}
		houseList.add(houseNum);
	}
	
	static boolean isOutOfIndex(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
