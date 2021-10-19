package day0806;

import java.util.Scanner;
import java.util.Stack;
/*
 * Date : 210806
 */
public class BJ4963_NumOfIsland {
	// 델타 배열
	static int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};	// 위 - 오른위 - 오 - 아래오 - 아래 - 아래왼 - 왼 - 왼위
	static int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {		// 0, 0을 입력받을 때까지 무한반복
			int w = in.nextInt();		// 너비 저장
			int h = in.nextInt();		// 높이 저장
			if(w == 0 && h == 0) break;	// 0, 0을 입력받으면 반복문 중단
			int[][] map = new int[h][w];	// 맵을 생성(W * H)
			for(int i = 0 ; i < h ; i++) {			// 맵에 입력받은 값들을 저장
				for(int j = 0 ; j < w ; j++) {
					map[i][j] = in.nextInt();
				}
			}
			int result = 0;			// 섬의 개수를 저장
			for(int i = 0 ; i < h ; i++) {		// 맵에 대해 반복
				for(int j = 0 ; j < w ; j++) {
					if(map[i][j] == 1) {		// 땅이 있다면(섬의 시작)
						result++;				// 1을 더함
						map[i][j] = 0;			// 해당 값을 다시체크하지 않도록 0(바다)로 바꿈
						Stack<Point> stack = new Stack<Point>();	// 스택 생성
						stack.push(new Point(i, j));	// 첫번째 값을 스택에 넣음
						while(!stack.empty()) {			// 스택이 빌 때까지 반복(이어지는 땅이 없을때까지)
							Point now = stack.pop();	// 가장 위의 값을 꺼냄
							for(int d = 0 ; d < 8 ; d++) {	// 상하좌우대각선 8방향에 대해 반복
								int ni = now.i + di[d]; 	// 해당 방향으로 갔을 때의 인덱스 저장
								int nj = now.j + dj[d];
								if(ni < 0 || ni >= h || nj < 0 || nj >= w) continue;	// 맵을 빠져나가면 continue
								if(map[ni][nj] == 1) {		// 땅이라면
									map[ni][nj] = 0;		// 체크하지 않도록 0으로 바꾸고
									stack.push(new Point(ni, nj));	// 땅이 얼마나 더 이어지는지 확인하기 위해 현재 땅의 인덱스를 스택에 넣음
								}
							}	// for : d
						}	// while
					}	// if
				}	// for : j
			}	// for : i
			System.out.println(result);		// 섬의 총 개수 출력
		}	// while
	}
	static class Point {		// i, j 값을 저장한 Point 클래스
		int i;
		int j;
		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
