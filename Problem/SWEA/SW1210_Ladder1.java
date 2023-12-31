package day0803;

import java.util.ArrayList;
import java.util.Scanner;
/*
 * Date : 2021.08.03
 * Level : SWEA D3
 * Method : 반복문
 */
public class SW1210_Ladder1 {
	public static void main(String[] args) {
		final int LEFT = -1;	// 왼쪽
		final int RIGHT = 1;	// 오른쪽
		final int TC = 10;		// 테스트케이스 수
		final int N = 100;		// 배열 크기
		Scanner in = new Scanner(System.in);
		for(int tc = 1 ; tc <= TC ; tc++) {	// 테스트케이스 수만큼 실행
			in.nextInt();
			char[][] map = new char[N][N];		// 사다리 맵을 저장
			ArrayList<Integer> rodArr = new ArrayList<Integer>();	// 사다리 맵에서 1인 값(기둥)을 저장할 ArrayList
			int index = -1;		// rodArr에서 현재 열의 값이 저장된 인덱스를 저장할 변수
			for(int i = 0 ; i < N - 1 ; i++) {		// 맵을 입력받음
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = in.next().charAt(0);
				}
			}
			for(int j = 0 ; j < N ; j++) {			// 맵의 마지막 행에 대해
				map[N - 1][j] = in.next().charAt(0);	// 저장
				if(map[N - 1][j] == '1')			// 1이면 rodArr에 열값 저장
					rodArr.add(j);				
				else if(map[N - 1][j] == '2') {		// 2(목표)이면 rodArr에 열값(시작점) 저장
					index = rodArr.size();			// rodArr에서 2일때의 열이 저장된 인덱스 저장
					rodArr.add(j);
				}
			}
			for(int i = N - 2 ; i >= 0 ; i--) {		// 98행부터 0행까지 올라감
				if((rodArr.get(index) + LEFT) >= 0 && map[i][rodArr.get(index) + LEFT] == '1')	// map 벗어나지 않고 왼쪽이 1이면
					index--;		// rodArr의 인덱스를 한칸 줄임
				else if((rodArr.get(index) + RIGHT) < N && map[i][rodArr.get(index) + RIGHT] == '1')  // map를 벗어나지 않고 오른쪽이 1이면
					index++;		// rodArr의 인덱스를 한칸 늘림
			}
			System.out.println("#" + (tc) + " " + rodArr.get(index));		// 해당 인덱스에 해당하는 rodArr의 값을 출력
		}
	}
}
