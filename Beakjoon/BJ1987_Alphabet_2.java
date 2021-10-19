package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210819
 */
public class BJ1987_Alphabet_2 {
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
		String s = "";		// 여태까지 지나온 알파벳을 저장
		move(0, 0, s);
		System.out.println(max);		// 결과를 출력
	}
	// 현재 검사
	// 지나간 맵 설정 안해줌 - 어짜피 지나간 곳은 또 검사하면 알파벳이 중복이라 설정 안해도 되긴 되는데...
	static void move(int i, int j, String s) {	// 이동
		if(s.indexOf(map[i][j]) != -1)			// 들어가봐서 중복이면 리턴
			return;
		s += map[i][j];
		max = Math.max(max,  s.length());		// 매 길이에 대해 더 큰 값을 저장
		for(int d = 0 ; d < 4 ; d++) {			// 상, 하, 좌, 우로 이동
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni >= 0 && ni < R && nj >= 0 && nj < C) {
				move(ni, nj, s);
			}
		}
	}
}
