package day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210811
 */
public class BJ17406_RotateArray4 {
	static boolean[] selected;
	static int[][] map, newMap;	
	static int N, M, K;
	static int arrMin;
	static Rotation[] list;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());	// 행
		M = Integer.parseInt(st.nextToken());	// 얄
		K = Integer.parseInt(st.nextToken());	// 회전 연산 정보 개수
		map = new int[N][M];		// 원본 맵
		newMap = new int[N][M];		// 작업할 맵
		selected = new boolean[K];	// 순열 
		list = new Rotation[K];		// 회전 연산 정보들 저장
		arrMin = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++) {		// 맵 정보 받음
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < K ; i++) {		// 회전 연산 정보들 배열에 저장
			st = new StringTokenizer(in.readLine());
			list[i] = new Rotation(Integer.parseInt(st.nextToken()) - 1, // 인덱스 1씩 빼서 저장(0부터 시작하도록)
					Integer.parseInt(st.nextToken()) - 1, 
					Integer.parseInt(st.nextToken()));
		}
		perm(0, "");		// 순열 함수 실행
		System.out.println(arrMin);	// 결과 출력
	}
	static void perm(int idx, String s) {
		if(idx == K) {		// 순열 하나를 구하면
//			System.out.println("순열 : " + s);
			for(int i = 0 ; i < N ; i++)	// map을 새로운 map에 복사해서 저장
				newMap[i] = map[i].clone();
			for(int r = 0 ; r < K ; r++) {	// 각 회전 연산 정보들에 대해 회전
				Rotation rot = list[Integer.parseInt(s.charAt(r) + "")];
				rotate(rot.r, rot.c, rot.s);		// 회전
			}
			findMin();		// 최솟값을 찾음
			return;			// 리턴
		}
		for(int i = 0 ; i < K ; i++) {			// 순열 돌리기
			if(!selected[i]) {					// 포함 안되어있으면
				selected[i] = true;				// 포함시켜서 해당 인덱스(순서) String 더해 재귀실행
				perm(idx + 1, s + i);
				selected[i] = false;
			}
		}
	}
	static void rotate(int r, int c, int s) {		// 시계방향으로 한칸 회전
		for(int n = 0 ; n < s ; n++) {		// 매 회전 반지름마다 회전
			int ni = r - s + n;				// 맨 왼쪽 위 인덱스
			int nj = c - s + n;
			int temp = newMap[ni][nj];		// 첫번째 원소 저장해둠
			// 왼쪽 세로 이동
			for(int i = 0 ; i < 2 * (s - n) ; i++) newMap[ni + i][nj] = newMap[ni + i + 1][nj];
			// 아래 가로 이동
			for(int j = 0 ; j < 2 * (s - n) ; j++) newMap[ni + 2 * (s - n)][nj + j] = newMap[ni + 2 * (s - n)][nj + j + 1];
			// 오른쪽 세로 이등
			for(int i = 2 * (s - n) ; i > 0 ; i--) newMap[ni + i][nj + 2 * (s - n)] = newMap[ni + i - 1][nj + 2 * (s - n)];
			// 위에 가로 이등
			for(int j = 2 * (s - n) ; j > 1 ; j--) newMap[ni][nj + j] = newMap[ni][nj + j - 1];
			newMap[ni][nj + 1] = temp;		// 첫번째 원소 다음칸에 저장
		}
	}
	static void findMin() {			// 행마다 더해서 최종 최소값과 비교해 저장
		for(int i = 0 ; i < N ; i++) {
			int sum = 0;
			for(int j = 0 ; j < M ; j++) {
				sum += newMap[i][j];
			}
			if(sum < arrMin) arrMin = sum;
		}
	}
	static class Rotation {		// 회전연산 정보
		int r;				// 회전 중심 i
		int c;				// 회전 중심 j
		int s;				// 반지름
		public Rotation(int r, int c, int s) {
			this.r = r;
			this.c = c;
			this.s = s;
		}
	}
}
