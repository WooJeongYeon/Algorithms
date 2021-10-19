package day0810;

import java.util.Scanner;
/*
 * Date : 210810
 */
public class SW1220_Magnetic {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TC = 10;		// TC 10번
		for(int tc = 1 ; tc <= TC ; tc++) {		// TC만큼 반복
			int N = in.nextInt();				// 배열의 크기
			int result = 0;						// 교착상태 개수 저장
			char[][] map = new char[N][N];		
			for(int i = 0 ; i < N ; i++) {		// 2차원 map에 각 상태들 저장
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = in.next().charAt(0);
				}	
			}
			for(int j = 0 ; j < N ; j++) {		// 각 열에 대해 행을 탐색(세로로 탐색)
				boolean isLastN = false;		// 전에 N극이 있는지를 저장
				for(int i = 0 ; i < N ; i++) {	// 각 행을 탐색
					if(map[i][j] == '1') 		// 만약 N극이 있으면
						isLastN = true;			// N극이 있음을 저장
					else if(map[i][j] == '2' && isLastN) {	// 만약 S극이고, 전에 N극이 있으면
						result++;				// 교착상태 증가
						isLastN = false;		// 교착상태가 끝났으므로 false로 저장
					}
				}
			}
			System.out.println("#" + tc + " " + result);		// 결과 출력
		}
	}
}