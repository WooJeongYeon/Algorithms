// 개선의 여지가 있음
// 1 ~ M개 치킨집 포함하는 부분집합 구하고 리턴 안하므로
// 예를 들어 5개중 1개 true했을 때, 이후에도 true, false / true, false, false ... 똑같은 경우들이 실행됨
// 최솟값 구하는 거라 상관없긴함(시간, 메모리 더드는 정도일듯)

package day0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210813
 */
public class BJ15686_DeliveryChicken {
	static int[][] map;		// 맵
	static int N, M;		// 맵 크기, 선택하는 최대 치킨집 개수
	static int chickNum;	// 치킨집 총 개수
	static Point[] chickPoints;	// 치킨집 위치 저장 배열
	static boolean[] checked;	// 어떤 치킨집이 선택됬는지(subset메소드에서 체크)
	static int ans;			// 결과 - 총 치킨거리 최솟값
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		ans = Integer.MAX_VALUE;			// 비교하기 위해 최댓값 저장
		for(int i = 0 ; i < N ; i++) {		// 맵을 입력받음
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) chickNum++;		// 치킨집인 경우 개수++
			}
		}
		chickPoints = new Point[chickNum];		
		checked = new boolean[chickNum];
		int idx = 0;						// 치킨집 배열의 위치
		for(int i = 0 ; i < N ; i++) {		// 치킨집인 경우
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] == 2) {		
					chickPoints[idx++] = new Point(i, j);	// 해당 치킨집 인덱스 저장
				}
			}
		}
		subset(0, 0);			// 최솟값 구함
		System.out.println(ans);	// 결과
	}
	static void subset(int idx, int cnt) {		// 조합 구하는 메소드
		if(cnt == M) {				// 치킨집을 M개 선택했으면
			int here = 0;		// 선택된 치킨집 배열을 저장할 인덱스
			Point[] chickSelected = new Point[cnt];		// 
			for(int i = 0 ; i < chickNum ; i++) {		// 선택된 치킨집들을 저장
				if(checked[i]) chickSelected[here++] = chickPoints[i];
			}
			int totalMin = findMinD(chickSelected);		// 도시의 치킨거리의 최솟값 구함
			if(totalMin < ans) ans = totalMin;			// 비교해서 더 최솟값 저장
			return;
		}
		if(idx == chickNum) return;			// 더이상 고를 치킨집이 없는 경우 리턴
		checked[idx] = true;				// 해당 치킨집 선택
		subset(idx + 1, cnt + 1);
		checked[idx] = false;				// 해당 치킨집 선택X
		subset(idx + 1, cnt);	
	}
	static int findMinD(Point[] list) {		// 도시의 치킨거리 최솟값 구함
		int totalMin = 0;					// 도시의 치킨거리 최솟값을 저장할 변수
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map[i][j] == 1) {		// 집인 경우
					int min = Integer.MAX_VALUE;
					for(Point p : list) {	// 해당 집에서 제일 가까운 치킨집과의 거리를 저장 
						int now = Math.abs(i - p.i) + Math.abs(j - p.j);
						if(min > now) {
							min = now;
						}
					}
					totalMin += min;		// 가까운 치킨집과의 거리 더함
				}
			}
		}
		return totalMin;
	}
	static class Point {			// 위칫값 저장
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
