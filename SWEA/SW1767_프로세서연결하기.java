/*
 * Date : 210919
 * error1 : 입력받은 값을 map에 저장 안함
 * error2 : minLen, maxCoreNum을 초기화 안함ㅠㅠ
 * method : 가장자리에 없는 모든 코어들에 대해 각 방향으로 전선을 놓아봄. 해당 코어를 포함시키지 않으므로써 가능성이 전혀 없다면 다음 방향으로 검사
 * plus : 210923 4방향으로 못 놓는 경우도 실행되게 해놨네. 근데 이경우는 4방향에 대해 전선 없을 때 4번 실행하게 되겠네... 마지막에 1번만 실행되도록 바꿈
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW1767_프로세서연결하기 {
	static int TC, N;							// TC, 맵의 크기
	static int[][] map;
	static ArrayList<Point> cores;				// 가장자리에 없는 코어들을 저장할 리스트
	static int minLen, maxCoreNum;				// 가장 짧은 전선길이, 가장 많이 포함시킨 코어들의 수
	static int[] di = {-1, 1, 0, 0};			// 델타배열 - 위, 아래, 왼, 오
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream("SW1767_sample_input.txt"));
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(bf.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {						// TC만큼 반복
			N = Integer.parseInt(bf.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			minLen = 0;
			maxCoreNum = 0;
			int v = 0;
			for(int i = 0 ; i < N ; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(i == 0 || i == N - 1 || j == 0 || j == N - 1) continue; 	// 가장자리에 있는 코어는 포함시키지 않음
					if(map[i][j] == 1) {						// 그 외의 코어라면
						cores.add(new Point(i, j));				// 리스트에 추가
					}
				}
			}
//			for(Point p : cores) System.out.println(p.i + " " + p.j);
			connectCore(0, 0, 0);								// 가장 많이 코어를 포함시켰을 때, 가장 짧은 전선의 길이 구함
			
			sb.append("#" + tc + " " + minLen + "\n");
		}
		System.out.println(sb);
	}
	
	// idx - 코어인덱스, cnt - 연결한 코어 개수, len - 여태까지의 전선길이
	static void connectCore(int idx, int cnt, int len) {
		if(idx == cores.size()) {					// 모든 코어를 다 체크했다면
//			System.out.println(cnt + " " + len);
//			print();
			if(cnt > maxCoreNum) {					// 코어 개수가 가장 크다면
				maxCoreNum = cnt;					// 맥스코어개수, 전선길이 갱신
				minLen = len;
			}
			else if(cnt == maxCoreNum) 				// 코어 길이가 이전과 같다면
				minLen = Math.min(minLen, len);		// 작은 전선 길이 저장
			return;
		}
		Point nowCore = cores.get(idx);				// 해당 코어를 저장
		for(int d = 0 ; d < 4 ; d++) {				// 모든 방향에 대해 전선을 놓을 수 있는지 검사
			int ni = nowCore.i + di[d];				// 코어로부터 해당 방향으로의 다음칸
			int nj = nowCore.j + dj[d];
			int tmpI = ni, tmpJ = nj;
			boolean isPossible = true;				// 전선을 놓을 수 있는지를 저장
			while(true) {							// 맵의 칸마다 전선을 놓을 수 있는지 검사
				if(isOutOfIndex(tmpI, tmpJ)) break;	// 1. 인덱스 벗어나면 전선 놓을 수 있음!
				if(map[tmpI][tmpJ] != 0) {			// 2. 사이에 전선이나 코어가 있다면 전선 못놓음! 
					isPossible = false;
					break;
				}
				tmpI += di[d]; tmpJ += dj[d];
			}	
			
			if(!isPossible) continue; 				// 전선 못놓으면 다음으로
			// 전선을 놓을 수 있다면
			int nowLen = 0;
			tmpI = ni; tmpJ = nj;
			while(true) {								// map에 전선을 표시함
				if(isOutOfIndex(tmpI, tmpJ)) break;
				map[tmpI][tmpJ] = 2;
				nowLen++;								// 전선의 길이를 구함
				tmpI += di[d]; tmpJ += dj[d];
			}
			
			connectCore(idx + 1, cnt + 1, len + nowLen);	// 다음 코어를 검사하러 고고!
			tmpI = ni; tmpJ = nj;
			while(true) {								// map에 표시한 전선을 0으로 다시 저장(다음 방향으로 검사하기 위해)
				if(isOutOfIndex(tmpI, tmpJ)) break;
				map[tmpI][tmpJ] = 0;
				tmpI += di[d]; tmpJ += dj[d];
			}
		}
		
		// 만약 전선을 놓을 수 없고, 이번 코어를 포함시키지 않아도 앞으로 남은 코어들을 포함시키면 maxCoreNum보다 크거나 같다면
		// 가능성이 있으므로 다음 코어를 검사하러 고고!
		if((cnt + (cores.size() - idx - 1)) >= maxCoreNum) {
			connectCore(idx + 1, cnt, len);
		}
	}
	
	static boolean isOutOfIndex(int i, int j) {			// 인덱스를 나가는지 반환하는 메소드
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	static void print() {								// 출력해보는 메소드
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	static class Point {								// Point 클래스
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
