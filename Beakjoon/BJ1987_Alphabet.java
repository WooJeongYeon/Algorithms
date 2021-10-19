package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210819
 */
public class BJ1987_Alphabet {
	static int R, C;		// 행, 열
	static char[][] map;	// 맵
	static int max;			// 말이 지날 수 있는 최댓값
	static int[] di = {-1, 1, 0, 0};	// 델타배열 - 상 하 좌 우
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());	
		max = Integer.MIN_VALUE;		
		map = new char[R][];
		for(int i = 0 ; i < R ; i++) {	// 맵을 초기화
			map[i] = in.readLine().toCharArray();
		}
		String s = map[0][0] + "";		// 여태까지 지나온 알파벳을 저장
		move(0, 0, s);
		System.out.println(max);		// 결과를 출력
	}
	// 재귀 호출 전 검사
	static void move(int i, int j, String s) {	// 이동
		boolean isGo = false;
		for(int d = 0 ; d < 4 ; d++) {			// 상, 하, 좌, 우로 이동
			int ni = i + di[d];
			int nj = j + dj[d];
			// 인덱스를 벗어나지 않고, 아직 지나가지 않은 장소이고, 아직 해당 알파벳을 지나지 않았다면
			if(ni >= 0 && ni < R && nj >= 0 && nj < C && map[ni][nj] != '-' && s.indexOf(map[ni][nj]) == -1) {
				char c = map[ni][nj];
				map[ni][nj] = '-';				// 지나갔음을 맵에 표시하고
				move(ni, nj, s + c);
				map[ni][nj] = c;				// 되돌아왔으면 원래 문자 저장
				isGo = true;					// 이동 할 수 있음을 표시
			}
		}
		if(!isGo) {								// 이동을 한번도 하지 않았으면
			max = Math.max(max, s.length());	// 더이상 갈 수 없으므로 최댓값 저장
		}
	}
}
