package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * Date : 210825
 */
public class BJ10026_RedGreen_BFS {
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		char[][] map1 = new char[N][];
		char[][] map2 = new char[N][N];
		for(int i = 0 ; i < N ; i++) {
			map1[i] = in.readLine().toCharArray();
		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map2[i][j] = (map1[i][j] == 'G' ? 'R' : map1[i][j]);
			}
		}
		System.out.println(BFS(map1, N) + " " + BFS(map2, N));
	}
	static int BFS(char[][] map, int N) {
		int area = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] != '-') {
					area++;
					char c = map[i][j];
					map[i][j] = '-';
					
					Queue<Point> queue = new LinkedList<>();
					queue.offer(new Point(i, j));
					while(!queue.isEmpty()) {
						Point now = queue.poll();
						for(int d = 0 ; d < 4 ; d++) {
							int ni = now.i + di[d];
							int nj = now.j + dj[d];
							if(ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] == c) {
								map[ni][nj] = '-';
								queue.offer(new Point(ni, nj));
							}
						}
					}
				}
			}
		}
		return area;
	}
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
