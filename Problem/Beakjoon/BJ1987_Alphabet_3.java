package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210820
 */
public class BJ1987_Alphabet_3 {
	static int R, C;		// 행, 열
	static char[][] map;	// 맵
	static int max;			// 말이 지날 수 있는 최댓값
	static int[] di = {-1, 1, 0, 0};	// 델타배열 - 상 하 좌 우
	static int[] dj = {0, 0, -1, 1};
	static boolean[] isPass;			// 해당 알파벳을 지났는지 저장하는 배열(A ~ Z)
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
		isPass = new boolean['Z' - 'A' + 1];	// 알파벳은 26개
		move(0, 0, 1);
		System.out.println(max);		// 결과를 출력
	}
	// 현재 검사
	// 지나간 맵 설정 안해줌 - 어짜피 지나간 곳은 또 검사하면 알파벳이 중복이라 설정 안해도 되긴 되는데...
	static void move(int i, int j, int cnt) {	// 현재까지의 지난 알파벳 개수
		if(isPass[map[i][j] - 'A'])			// 해당 알파벳을 지났었다면 -> 알파벳을 isPass의 인덱스로 사용 
			return;
		isPass[map[i][j] - 'A'] = true; 	// 지났다고 표시
		max = Math.max(max,  cnt);		// 매 길이에 대해 더 큰 값을 저장
		for(int d = 0 ; d < 4 ; d++) {			// 상, 하, 좌, 우로 이동
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni >= 0 && ni < R && nj >= 0 && nj < C) {	// 해당 범위 내에 있다면
				move(ni, nj, cnt + 1);
			}
		}
		isPass[map[i][j] - 'A'] = false; 		// 이 알파벳 전으로 돌아가므로 지나지 않았음을 다시 표시해줌
	}
}
