package day0810;

import java.util.Scanner;
/*
 * Date : 210810
 */
public class BJ2563_ColorPaper {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int MAP_SIZE = 100;		// 도화지 크기
		final int PAPER_SIZE = 10;		// 색종이 크기
		boolean[][] map = new boolean[MAP_SIZE][MAP_SIZE];	// 색종이가 해당 위치에 붙었는지를 저장할 boolean 배열
		int area = 0;					// 색종이가 붙은 영역 크기
		int N = in.nextInt();			// 색종이의 개수
		for(int n = 0 ; n < N ; n++) {	// 색종이 개수만큼 반복
			int x = in.nextInt();		// 색종이의 x, y좌표
			int y = in.nextInt();
			for(int i = 0 ; i < PAPER_SIZE ; i++)	// 색종이가 가리는 영역을 모두 true로 저장
				for(int j = 0 ; j < PAPER_SIZE ; j++)
					map[y + i][x + j] = true;
		}
		for(int i = 0 ; i < MAP_SIZE ; i++)			// true인 영역이라면 영역 크기를 증가
			for(int j = 0 ; j < MAP_SIZE ; j++)
				if(map[i][j]) area++;
		System.out.println(area);					// 결과 출력
	}
}
