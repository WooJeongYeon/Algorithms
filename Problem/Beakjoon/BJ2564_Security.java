package day0819;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * Date : 210819
 */
public class BJ2564_Security {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int C = in.nextInt();				// 가로길이	
		int R = in.nextInt();				// 세로길이
		int N = in.nextInt();				// 상점 수
		int[] dIdx = {0, R, 0, C};			// 가지는 인덱스의 최솟값 또는 최댓값
		int result = 0;
		ArrayList<Point> list = new ArrayList<>();		
		for(int i = 0 ; i < N ; i++) {		// 상점을 저장
			list.add(new Point(in.nextInt() - 1, in.nextInt()));
		}
		Point me = new Point(in.nextInt() - 1, in.nextInt());	// 동근이의 위치
		for(Point p : list) {				// 모든 상점에 대해 동근이와의 최단 거리를 구함
			int nowLen = 0;
			if(p.d == me.d) {				// 같은 방향에 위치하면
				nowLen += Math.abs(p.l - me.l);
			}
			else if(p.d + me.d == 5 || p.d + me.d == 1) {		// 반대 방향에 위치하면
				int x = Math.max(dIdx[p.d], dIdx[me.d]);
				if(x == R)
					nowLen += Math.min(2 * C - p.l - me.l, p.l + me.l);
				else
					nowLen += Math.min(2 * R - p.l - me.l, p.l + me.l);
				nowLen += x;
			}
			else {							// 옆에 방향에 위치하면
				if(dIdx[p.d] != 0)
					nowLen += dIdx[p.d] - me.l;
				else nowLen += me.l;
				
				if(dIdx[me.d] != 0)
					nowLen += dIdx[me.d] - p.l;
				else nowLen += p.l;
			}
			result += nowLen;
		}
		System.out.println(result);			// 결과 출력
	}
	static class Point {					// 방향과 거리를 저장
		int d;
		int l;
		public Point(int d, int l) {
			this.d = d;
			this.l = l;
		}
	}
}
