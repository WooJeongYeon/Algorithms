import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.05
 * Level : SWEA 기출
 * Select1 : ArrayList 사용 VS 배열 사용 -> ArrayList 했다가 배열로 갈아엎음
 * Thinking) 배열 오른쪽으로 돌려서 사용(W - 행크기, H - 열크기)
 * 		1. ChooseBrick - 순열로 벽돌 하나씩 선택
 * 		2. brickBreaker - 선택한 벽돌 실행시키기. 없애없애
 * 		3. getBrickCnt - 남은 벽돌 개수 반환
 * Method : 중복순열, BFS, 배열 새로 만들어서 옮기는거 구현
 * Error1 : ArrayList 배열 중 특정 ArrayList배열이 비었을때는 넘어가도록
 * Error2 : 문제 잘못이해 - 모두 동시에 터지는거 -> ArrayList로 쓸 경우 없앨거 표시해두고 나중에 없애야함 -> queue에 따로 보관, visit 체크
 * 		Error3 : boolean배열 초기화안함. 객체 아니야!!! 기본자료형들은 초기화 해줘야해!(전역변수 제외) ...? 상관없는건데... 이때는 뭐가문제였지
 * Error4 : 하지만 무한반복 -> queue에 따로 보관한 애들 하나씩 없애다보면 인덱스가 꼬여버림... -> 배열 사용
 * Error5 : 옮기는거 이상하네.. 직접 옮겨줘야할듯. 전달한 메소드 내에서 참조변수에 새로운 값 넣어줘봤자 값에 의한 참조임!!! 직접 배열값 바꿔주던지, 반환으로 넘기든지(이거 선택)
 * Error6 : size배열 이상함 - 전역으로 써서 그렇자나!!! -> 각 시뮬맵에 들어가도록 마지막 H열 추가해서 size 계산해줌..
 * Plus1 : 
 * Result : 대충계획 40분하고 1시간 40분정도 디버깅했다고!!! 구체적으로 계획해!!!!
 */
public class SW5656_벽돌깨기 {
	static int TC, N, W, H, ans;
	static int[][] smap;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			ans = Integer.MAX_VALUE;
			st = new StringTokenizer(in.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			smap = new int[W][H + 1];			// 끝에 크기
			for(int i = 0 ; i < W ; i++) {		// 크기 0으로 초기화해줌
				smap[i][H] = 0;
			}
			for(int i = 0 ; i < H ; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for(int j = 0 ; j < W ; j++) {
					smap[j][H - i - 1] = Integer.parseInt(st.nextToken());	// 배열을 돌려서 만듬 W * H 되도록
					if(smap[j][H - i - 1] != 0) smap[j][H]++;		// 해당 행 크기 계산
				}
			}
//			print(smap);
			chooseBrick(0, smap);
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	
	static void chooseBrick(int cnt, int[][] map) {						// 벽돌 선택
		if(cnt == N || getBrickCnt(map) == 0) {							// 벽돌 N개만큼 선택해봤거나, 더이상 선택할 벽돌이 없으면
			ans = Math.min(ans, getBrickCnt(map));						// 결과 갱신
			return;														// 반환
		} 
		for(int i = 0 ; i < W ; i++) {									// 중복순열로 행 중 1개 선택(벽돌 선택)
			int[][] simulMap = clone(map);								// 새로 시뮬릴 맵 생성
//			System.out.println(cnt + " " + i);
			if(map[i][H] != 0) simulMap = brickBreaker(i, simulMap);	// 해당 행이 벽돌이 있다면 벽돌 부수러 ㄱㄱ
//			print(simulMap);
			chooseBrick(cnt + 1, simulMap);								// 다음 벽돌 선택하도록 ㄱㄱ
		}
	}
	
	static int[][] brickBreaker(int idx, int[][] simulMap) {			// 벽돌 부수기
		Queue<Brick> queue = new LinkedList<>();
		queue.offer(new Brick(idx, simulMap[idx][H] - 1, simulMap[idx][simulMap[idx][H] - 1]));	// 해당 행의 가장 끝부분 
		simulMap[idx][simulMap[idx][H] - 1] = 0;						// 벽돌 부셔부셔
		while(!queue.isEmpty()) {										// 큐 빌때까지 반복
			Brick now = queue.poll();
			for(int n = 1 ; n < now.num ; n++) {						// 길이 num - 1만큼 부셔부셔
				for(int d = 0 ; d < 4 ; d++) {							// 상하좌우로
					int ni = now.i + di[d] * n;
					int nj = now.j + dj[d] * n;
					if(ni >= 0 && ni < W && nj >= 0 && nj < H			// 배열 벗어나지 않고, 해당 배열에 벽돌이 있고, 해당 칸이 0이 아니라면 
							&& simulMap[idx][H] != 0 && simulMap[ni][nj] != 0) {
						queue.offer(new Brick(ni, nj, simulMap[ni][nj]));	// 큐에 벽돌 넣음
						simulMap[ni][nj] = 0;								// 해당 벽돌 부셔부셔
					}
				}
			}
		}
		return move(simulMap);											// 일부 비었으므로 이동시킴
	}
	
	static int[][] move(int[][] simulMap) {		// 이동(배열의 0부분 없애도록 앞으로 당기기)
		int[][] newMap = new int[W][H + 1];		// 새로운 배열에 옮겨줄거임
		for(int i = 0 ; i < W ; i++) {			// 행마다 size 0으로 초기화
			newMap[i][H] = 0;
		}
		for(int i = 0 ; i < W ; i++) {
			int idx = 0;						// 새로운 배열 인덱스
			Arrays.fill(newMap[i], 0);			// 처음엔 해당행 전부 0으로 채우도록
			for(int j = 0 ; j < H ; j++) {
				if(simulMap[i][j] != 0) {		// 0이 아닌 경우, 옮겨담음
					newMap[i][idx++] = simulMap[i][j];	
					newMap[i][H]++;				// 사이즈 증가
				}
			}
		}
		return newMap;
	}
	
	static int getBrickCnt(int[][] simulMap) {	// 벽돌 개수 계산해 반환
		int sum = 0;
		for(int i = 0 ; i < W ; i++) {
			for(int j = 0 ; j < H ; j++) {
				if(simulMap[i][j] != 0) sum++;
			}
		}
		return sum;
	}
	
	static int[][] clone(int[][] map) {			// int 이차원 배열 복사해 반환
		int[][] newMap = new int[W][H + 1];
		for(int i = 0 ; i < W ; i++) {
			newMap[i] = map[i].clone();
		}
		
		return newMap;
	}
	
	static void print(int[][] map) {
		for(int i = 0 ; i < W ; i++) {
			for(int j = 0 ; j < H ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------------------");
	}
	
	static class Brick {			// 벽돌 정보 - 위치, 벽돌번호
		int i, j, num;

		public Brick(int i, int j, int num) {
			this.i = i;
			this.j = j;
			this.num = num;
		}
	}
}
