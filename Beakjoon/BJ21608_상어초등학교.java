import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.30
 * Level : BaekJoon Sliver 1
 * Difficulty : 중간
 * Time : 1시간
 * URL : https://www.acmicpc.net/problem/21608
 * Select1 : 한번에 저장해놓고 위치 찾기 VS 매번 계산(이거 선택) 
 * Method : 반복
 * Error1 : 비어있는 칸들만 검사해야댐
 * Result : 생각보다 어려웠는데... 조건이 많아서? 너무 코드가 길어졌다
 * Plus1 : 다 저장해두고 정렬시켜서 찾기도 좋을듯 
 * Plus2 : sort함수 이런식으로도 쓸 수 있구나
 * 		Arrays.sort(conditions, (a,b) -> a[0] != b[0] ? b[0]-a[0] : a[1] != b[1] ? b[1]-a[1] : a[2]-b[2]);
 * Plus3 : 행과 열 비교하는 부분은 그냥 idx로 놓고 해도 좋을듯
 */
public class BJ21608_상어초등학교 {
	static int N, size;
	static int[] order;
	static int[][] map, students;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		size = N * N;
		map = new int[N][N];
		order = new int[size];
		students = new int[size + 1][4];
		for(int i = 0 ; i < size ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			order[i] = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < 4 ; j++) {
				students[order[i]][j] = Integer.parseInt(st.nextToken()); 
			}
		}
		
		setSeat();
		
		System.out.println(totalScore());
	}
	
	static void setSeat() {
		for(int n = 0 ; n < size ; n++) {
			int now = order[n];
			ArrayList<Point> seats = new ArrayList<>();
			ArrayList<Point> newSeats = new ArrayList<>();
			int max = 0;
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < N ; j++) {
					if(map[i][j] != 0) continue;
					int cnt = getLikeCnt(i, j, now);
					if(max < cnt) {
						max = cnt;
						seats.clear();
						seats.add(new Point(i, j));
					} else if(max == cnt) {
						seats.add(new Point(i, j));						
					}
				}
			}
			
			if(seats.size() == 1) {
				map[seats.get(0).i][seats.get(0).j] = now;
				continue;
			}
			// 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
			max = 0;
			for(int i = 0 ; i < seats.size() ; i++) {
				Point nowP = seats.get(i);
				int cnt = getBlankCnt(nowP.i, nowP.j);
				if(max < cnt) {
					max = cnt;
					newSeats.clear();
					newSeats.add(nowP);
				} else if(max == cnt) {
					newSeats.add(nowP);
				}
			}
			
			if(newSeats.size() == 1) {
				map[newSeats.get(0).i][newSeats.get(0).j] = now;
				continue;
			}
			// 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 
			// 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
			int row = N;
			int col = N;
			for(int i = 0 ; i < newSeats.size() ; i++) {
				Point nowP = newSeats.get(i);
				if(row > nowP.i) {
					row = nowP.i;
					col = nowP.j;
				}
				else if(row == nowP.i) {
					col = Math.min(col, nowP.j);
				}
			}
			map[row][col] = now;
		} 
	}
	
	static int getBlankCnt(int i, int j) {
		int cnt = 0;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(isOutOfIdx(ni, nj)) continue;
			if(map[ni][nj] == 0) cnt++;
		}
		return cnt;
	}
	
	static int getLikeCnt(int i, int j, int now) {
		int cnt = 0;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(isOutOfIdx(ni, nj)) continue;
			for(int n = 0 ; n < 4 ; n++) {
				if(map[ni][nj] == students[now][n]) {
					cnt++;
					break;
				}
			}
		}
		return cnt;
	}

	static int totalScore() {
		int sum = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				int cnt = getLikeCnt(i, j, map[i][j]);
				if(cnt >= 1) {
					sum += Math.pow(10, cnt - 1);
				}
			}
		}
		
		return sum;
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
