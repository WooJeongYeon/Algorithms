package day0812;

import java.util.Scanner;
/*
 * Date : 210812
 */
public class SW1974_CheckSudoku {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		final int TC = in.nextInt();
		int[][] map = new int[9][9];
		for(int tc = 1 ; tc <= TC ; tc++) {
			for(int i = 0 ; i < 9 ; i++) {	// map 저장
				for(int j = 0 ; j < 9 ; j++) {
					map[i][j] = in.nextInt();
				}
			}
			sb.append("#" + tc + " " + check(map) + "\n");
		}
		System.out.println(sb);
	}
	static int check(int[][] map) {
		int checked;
		for(int i = 0 ; i < 9 ; i++) {		// 가로 세로 스도쿠 체크해줌
			checked = 0;		// 가로에 대해 저장
			int checked2 = 0;	// 세로에 대해 저장
			for(int j = 0 ; j < 9 ; j++) {
				checked = checked | 1 << (map[i][j] - 1);	// 해당 수가 있음을 비트마스크로 표시
				checked2 = checked2 | 1 << (map[j][i] - 1);
			}
			for(int j = 0 ; j < 9 ; j++) {
				if(checked != 511) return 0;				// 모든 수가 존재하지 않는다면 0리턴
				if(checked2 != 511) return 0;							
			}
		}
		for(int i = 0 ; i < 3 ; i++) {			// 3 X 3 부분에 대해 각각 검사
			for(int j = 0 ; j < 3 ; j++) {
				checked = 0;
				int si = i * 3;			// 시작지점 설정
				int sj = j * 3;
				for(int n = 0 ; n < 3 ; n++) {	// 해당 부분에 모든 수가 있는지
					for(int m = 0 ; m < 3 ; m++) {
						checked = checked | 1 << (map[n][m] - 1);
					}
				}
				for(int k = 0 ; k < 9 ; k++)
					if(checked != 511) return 0;
			}
		}
		return 1;	// 일치하면 1 반환
	}
}
