package day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210804
 */
public class BJ1012_OrganicCabbage {
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < TC ; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int worm = 0;
			Queue<Point> queue = new LinkedList<Point>();
			int[][] map = new int[N][M];
			for(int i = 0 ; i < K ; i++) {
				st = new StringTokenizer(in.readLine());
				int nowJ = Integer.parseInt(st.nextToken());
				int nowI = Integer.parseInt(st.nextToken());
				map[nowI][nowJ] = 1;
			}
//			for(int i = 0 ; i < N ; i++) {
//				for(int j = 0 ; j < M ; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < M ; j++) {
					if(map[i][j] == 1) {
						queue.add(new Point(j, i));
						worm++;
						while(queue.size() > 0) {
							//System.out.println(queue.size());
							Point p = queue.poll();
							for(int d = 0 ; d < 4 ; d++) {
								int ni = p.y + di[d];
								int nj = p.x + dj[d];
								if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 0) continue;
								if(map[ni][nj] == 1) {
									map[ni][nj] = 0;		// 얘가 while 초반에 있다가 일로 옮기니까 메모리 초과 안나네...?
									queue.add(new Point(nj, ni));
								}
							}
						}
					}
				}
			}
			System.out.println(worm);
		}
	}
	public static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
