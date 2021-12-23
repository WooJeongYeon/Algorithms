import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.12.23
 * Level : BaekJoon Gold 2
 * Difficulty : 중상
 * Why : 링크드리스트 생각하는게 헷갈렸음
 * URL : https://www.acmicpc.net/workbook/view/1152
 * Select1 : 이차원 링크드리스트 배열 + Piece 배열 VS 이중링크드리스트 구현 + Piece(선택) -> 공부좀 해보려구...
 * Select2 : 반대방향 -> 인덱스 홀수이면 -1, 짝수이면 +1
 * Select3 : 빨간색 칸 reverse 시키는거 링크들 다 거꾸로 해주고 이전 링크 맞춰줘야 하나 생각하다가, last, next 링크를 바꾸는 것보다
 * 			idx랑 d를 바꾸는 게 더 나을 거 같아서 이걸로 함 -> 훨씬 낫네
 * Thinking : 턴마다 1 ~ K 말을 순서대로 이동(올라간 말도 함께 이동)
 * 			- A의 다음 칸이
 * 				1) 체스판 벗어남 OR 파랑 -> 방향 전환하고 한칸 이동. 이동하려는 칸이 파랑이면 이동 X, 빨강이면 reverse
 * 				2) 흰칸 -> 다 같이 이동
 * 				3) 빨강 -> 이동 + 반전(A와 위에 있는 애들끼리)
 * 			- 말이 4개 이상이면 종료(매 말 이동마다 검사)
 * Method : 이중LinkedList 조금 구현
 * Error1 : 이동하고 그 칸에 말 있는 경우 올릴 때 pieces를 다 탐색함 -> 이게 지금 이동한 A 위에 있는 애들로 걸려서 visited 체크해줌
 * Error2 : 마지막게 틀리는데 도저히 모르겠어서 print로 찍어서 찾음
 * 			- print로 찍는데 다 맞는거 같아서 문제를 다시 봄
 * 			- 파란색이면 이동할 때 A만 이동하는건가? 하다가 그건 아님
 * 			- 파란색이었어서 또 이동할 때 또 색을 체크해줘야 하는거였음 -> 문제에 제대로 안나왔었어ㅠㅠ 
 * Result : last랑 next값 바꿔주는거 계속 헷갈렸다. 이렇게 노드로 이어주니까 print 찍는것도 힘들고ㅠ
 * Plus1 : 다음엔 이차원 링크드리스트 배열 쓰자.. 
 */
public class BJ17837_새로운게임2 {
	static int N, K, turn;
	static int[][] map;
	static Piece[] pieces;
	static int[] di = {0, 0, -1, 1};
	static int[] dj = {1, -1, 0, 0};
	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		pieces = new Piece[K];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int n = 0 ; n < K ; n++) {
			st = new StringTokenizer(in.readLine(), " ");
			int i = Integer.parseInt(st.nextToken()) - 1;
			int j = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			pieces[n] = new Piece(n, i, j, d);
		}
		while(turn++ <= 1000) {			// 1000번 까지 게임 진행
			if(game()) break;
		}
		if(turn > 1000) turn = -1;
		System.out.println(turn);
	}
	private static boolean game() {
		for(int n = 0 ; n < K ; n++) {	// 1 ~ K번의 말들을 이동시킴
			go(pieces[n]);				// 말 이동
//			print(n);
			if(isFourConnect()) return true;	// 말이 4개이상 쌓였으면 종료
		}
		return false;
	}
	private static void go(Piece now) {	// 말 이동
		int ni = now.i + di[now.d]; 	// 다음 위치
		int nj = now.j + dj[now.d];
		if(ni < 0 || ni >= N || nj < 0 || nj >= N) changeDirAndMove(now);	// 인덱스 나가면
		else if(map[ni][nj] == BLUE) changeDirAndMove(now);		// 파랑이면
		else if(map[ni][nj] == WHITE) move(ni, nj, now);		// 흰색이면
		else if(map[ni][nj] == RED) {							// 빨강이면
			move(ni, nj, now);
			reverse(now);
		}
	}
	private static void changeDirAndMove(Piece now) {			// 방향 바꾸고 이동
		now.d += now.d % 2 == 1 ? -1 : 1;  	// 방향 반대로
		int ni = now.i + di[now.d]; 
		int nj = now.j + dj[now.d];
		if(ni < 0 || ni >= N || nj < 0 || nj >= N) return;
		move(ni, nj, now);
		if(map[ni][nj] == RED) reverse(now);		// 2) 이거 추가해줌... 이동할때 체크
	}
	private static void move(int ni, int nj, Piece now) {		// 이동
		boolean[] visited = new boolean[K];
		if(map[ni][nj] == BLUE) return;		
		disconnect(now);				// 이동하려는 A의 밑에 있는 애들과 연결을 끊음
		Piece next = now;
		while(next != null) {			// A와 위에 있는 말들 이동
			next.i = ni;
			next.j = nj;
			visited[next.idx] = true;
			next = next.next;
		}
		for(int i = 0 ; i < K ; i++) {	// 새로 간 칸에 말이 있는 경우,
			if(visited[i]) continue;			// 1) 본인들은 걸러줘야 함
			if(pieces[i].i == ni && pieces[i].j == nj) {
				Piece end = getEndPiece(pieces[i]);		// 마지막 말과 A를 연결해줌
				connect(end, now);
				break;
			}
		}
	}
	private static Piece getEndPiece(Piece now) {		// 해당 말과 연결된 마지막 말을 찾음
		while(now.next != null) {
			now = now.next;
		}
		return now;
	}
	private static void connect(Piece end, Piece now) {	// 말을 연결
		end.next = now;
		now.last = end;
	}
	private static void disconnect(Piece now) {			// 해당 말과 앞의 말의 연결을 끊음
		if(now.last != null) now.last.next = null;
		now.last = null;
	}
	private static void reverse(Piece now) {			// A와 위의 애들 반전
		boolean[] visited = new boolean[K];
		Piece left = now;								// 첫번째 말(A)
		if(left.next == null) return;
		Piece right = getEndPiece(now);					// 마지막 말을 찾아서 저장
		while(true) {
			if(left == null || right == null) break;	// 바꿀 말이 없으면 중단(말의 개수 1개)
			if(visited[left.idx] || visited[right.idx]) break;	// 다 봤으면 중단(말의 개수가 짝수인 경우)
			if(left == right) break;					// 다 돌고 중간으로 왔으면 중단(말의 개수가 홀수인 경우)
			else {
				int tmp = left.idx;						// idx 바꿈
				left.idx = right.idx;
				right.idx = tmp;
				
				tmp = left.d;							// d 바꿈
				left.d = right.d;
				right.d = tmp;
				
				pieces[left.idx] = left;				// pieces에 저장된 애들도 바꿔줌
				pieces[right.idx] = right;
			}
			visited[left.idx] = true;					// 방문 표시
			visited[right.idx] = true;			
			left = left.next;							// 왼쪽 -> 오른쪽
			right = right.last;							// 오른쪽 -> 왼쪽
		}
	}
	private static boolean isFourConnect() {		// 4개 이상 연결되어 있는지
		boolean[] visited = new boolean[K];
		for(int n = 0 ; n < K ; n++) {				// 모든 말을 다 체크해봄
			if(visited[n]) continue;				// 체크 했었으면 패스
			int cnt = 1;
			visited[n] = true;
			Piece left = pieces[n].last;
			Piece right = pieces[n].next;
			while(left != null) {					// 현재 말 기준으로 왼쪽으로 연결된 애들 개수 셈
				cnt++;
				visited[left.idx] = true;
				left = left.last;
			}
			while(right != null) {					// 현재 말 기준으로 오른쪽으로 연결된 애들
				cnt++;
				visited[right.idx] = true;
				right = right.next;
			}
			if(cnt >= 4) return true;				// 4개 이상이면 true 반환
		}
		
		return false;
	}
	static class Piece {			
		int idx;				// visit 체크하려고 추가
		int i, j, d;			// 위치, 방향
		Piece last, next;		// 이전 Piece와 다음 Piece
		public Piece(int idx, int i, int j, int d) {
			this.idx = idx;
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
//	static void print(int n) {
//		boolean[] visited = new boolean[K];
//		System.out.println(turn + "회차 - " + n + "번/" + 9 + "-------------------------------");
//		for(int i = 0 ; i < K ; i++) {
//			if(visited[i]) continue;
//			Piece start = pieces[i];
//			String s = "";
//			switch(map[start.i][start.j]) {
//			case 0 :
//				s = "White";
//				break;
//			case 1 :
//				s = "Red";
//				break;
//			case 2 :
//				s = "Blue";
//				break;
//			}
//			System.out.println("위치 : " + start.i + " " + start.j + " -> " + s);
//			System.out.print("\t ");
//			while(start.last != null) {
//				start = start.last;
//			}
//			while(start != null) {
//				char d = '.';
//				switch(start.d) {
//				case 0 :
//					d = '→';
//					break;
//				case 1 :
//					d = '←';
//					break;
//				case 2 :
//					d = '↑';
//					break;
//				case 3 :
//					d = '↓';
//					break;
//				}
//				System.out.print(start.idx + "(" + d + ") > ");
//				visited[start.idx] = true;
//				start = start.next;
//			}
//			System.out.println();
//		}
//	}
}
