// 모든 섬은 연결 가능하므로 모든 곳에 간선
// MST - Prim 알고리즘 사용
package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210825
 */
public class SW1251_Hanaro {
	static int TC, N;		// TC, 섬의 개수
	static Point[] point;	// 섬의 인덱스를 저장
	static boolean[] visit;	// 방문했는지를 저장
	static double[] minCost;// 각 정점에서 가질 수 있는 최소비용 저장
	static double E, ans;	// 환경부담세율, 답
	static final double INFINITY = Double.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {			// TC만큼 반복
			N = Integer.parseInt(in.readLine());	
			point = new Point[N];
			visit = new boolean[N];
			minCost = new double[N];
			ans = 0;
			Arrays.fill(minCost, INFINITY);			// minCost를 최댓값으로 저장
			st = new StringTokenizer(in.readLine());
			for(int i = 0 ; i < N ; i++) {			// 각 섬의 j 인덱스로 객체 생성
				point[i] = new Point(Integer.parseInt(st.nextToken()));
			}
			st = new StringTokenizer(in.readLine());
			for(int i = 0 ; i < N ; i++) {			// 각 섬의 i 인덱스 저장
				point[i].i = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(in.readLine());	// 환경부담세율
			
			prim();									// prim 알고리즘 시작
			sb.append("#" + tc + " " + String.format("%.0f", ans) + "\n");	// 소수점 첫째자리에서 반올림해 정수를 출력하도록
		}
		System.out.println(sb);
	}
	
	static void prim() {
		int current = 0;				// 현재 포함시킨 섬의 인덱스
		minCost[current] = 0;			// 일단 0번째 섬을 포함시키기 위해 0을 저장
		for(int n = 0 ; n < N ; n++) {	// 모든 섬을 포함시켜야 하므로 N번 반복
			double min = INFINITY;		// 최솟값 초기화
			for(int i = 0 ; i < N ; i++) {	// 모든 정점에 대해
				if(!visit[i] && min > minCost[i]) {	// 방문하지 않았고, 가장 작은 값이면
					current = i;		// current, min을 갱신
					min = minCost[i];
				}
			}
			
			visit[current] = true;		// 해당 섬을 방문했음을 표시하고
			ans += E * min * min;		// E * L * L 더함(환경부담금)
			
			// 모든 정점에 대해 최소 간선 비용을 갱신
			for(int i = 0 ; i < N ; i++) {
				double d = dist(point[current], point[i]);	// 포함시킨 섬으로부터 각 섬까지의 거리를 계산
				if(!visit[i] && minCost[i] > d) {			// 해당 섬을 아직 방문하지 않았고, 여태까지 계산한 minCost보다 작다면
					minCost[i] = d;			// 갱신
				}
			}
			
		}
	}
	
	static double dist(Point a, Point b) {				// 거리를 반환(주의! double로 계산)
		return Math.sqrt((double)(a.i - b.i) * (a.i - b.i) + (double)(a.j - b.j) * (a.j - b.j));
	}
	
	static class Point {
		int i, j;
		public Point(int j) {
			this.j = j;
		}
	}
}
