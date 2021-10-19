package day0813;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210813
 */
public class BJ3190_Snake {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		final int N = Integer.parseInt(in.readLine());		// 맵의 크기
		final int[] di = {0, 1, 0, -1};		// 방향 - 오른쪽, 아래, 왼쪽, 위
		final int[] dj = {1, 0, -1, 0};
		boolean[][] map = new boolean[N][N];
		DInfo[] dInfo = new DInfo[100];		// 뱀의 방향전환을 저장
		int nowDIdx = 0;					// 다음 방향전환 인덱스
		final int K = Integer.parseInt(in.readLine());	// 사과 개수
		for(int i = 0 ; i < K ; i++) {		// map에 사과를 놓음(true로 표시)
			st = new StringTokenizer(in.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;	// 인덱스로 저장하기 위해 -1해줌(0부터)
		}
		final int L = Integer.parseInt(in.readLine()); 		// 방향전환 횟수
		for(int i = 0 ; i < L ; i++) {
			st = new StringTokenizer(in.readLine());
			dInfo[i] = new DInfo(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));	// X초와 방향전환문자를 저장
		}
		Queue<Point> snake = new LinkedList<Point>();		// 뱀의 경로를 저장
		int time = 0;	// 0초부터 시작
		int d = 0;		// 방향은 오른쪽부터 시작
		snake.add(new Point(0, 0));	// 첫번째 위치를 넣어줌
		Point last = new Point(0, 0);	// 이전 위치로 넣어줌(다음 위치를 구하기 위함)
		
		while(true) {		// 맵 벗어나거나, snake가 자기 몸에 닿을때까지 무한반복
			time++;			// 시간을 제일 먼저 증가
			Point now = new Point(last.i + di[d], last.j + dj[d]);	// 다음 위치 구함
			
			if(now.i < 0 || now.i >= N || now.j < 0 || now.j >= N)	// 맵 벗어나면 중단
				break;
			
			boolean isBreak = false;		// 부딪히는지를 표시
			for(int i = 0 ; i < snake.size() ; i++) {	// 모든 snake의 위치에 대해 검사
				Point check = snake.poll();			// snake 위치를 꺼내서
				if(check.i == now.i && check.j == now.j) {	// 현재 위치와 같으면(부딪혔다면)
					isBreak = true;			// 부딪혔음을 표시
					break;
				}
				snake.add(check);			// 빼서 검사한 것을 다시 넣어줌
			}
			if(isBreak) break;				//부딪혔으면 중단
			
			if(map[now.i][now.j]) map[now.i][now.j] = false;		// 사과가 있으면 해당 칸을 false로 표시
			else if(!map[now.i][now.j]) snake.poll();				// 사과가 없다면 꼬리부분 없앰
			
			if(nowDIdx < L && dInfo[nowDIdx].time == time) {		// 방향전환이 아직 남아있고, 방향전환할 시간이라면
//				System.out.println(dInfo[nowDIdx].time + " " + dInfo[nowDIdx].d);
				if(dInfo[nowDIdx].d == 'L') {			// 왼쪽이면
					d -= 1;
					d = d < 0 ? d + 4 : d;				// 방향인덱스를 -1
				}
				else {									// 오른쪽이면
					d = (d + 1) % 4;					// 방향인덱스를 +1
				}
				nowDIdx++;								// 방향인덱스 증가
			}
			snake.add(now);								// 현재 위치를 뱀의 queue에 넣음
			last.i = now.i;			// 이전 위치로 표시
			last.j = now.j;
		}
		System.out.println(time);	// 뱀이 죽은 시간 출력
	}
	static class Point {			// i, j 위치 저장
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static class DInfo {			// 방향전환 정보 저장(시간, 방향)
		int time;
		char d;
		public DInfo(int time, char d) {
			this.time = time;
			this.d = d;
		}
	}
}
