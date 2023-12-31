import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.04
 * Level : BaekJoon 골드 2
 * Select1 : ArrayList에 순서 저장 VS 배열에 저장(선택) VS 매번 순서 for문 돌리기
 * Select2 : 상어 탐색 방향과 입력방향이 다름 -> 입력방향 변환해주는 배열 changeDir
 * Thinking) 문제 풀기 넘 싫어서 틀 만들어놓고 메소드 하나만 만들어놓고 잘까 하다가 오기생겨서 다해버림... 메소드 하나씩 실행되는지 확인하는게 좋더라
 * 		- ArrayList / LinkedList쓰거나 배열에서 매번 배열값 당겨주면 시간초과 날 것 같다 -> 다 없애거나 작업하고 마지막 한번씩 이동되도록
 * 		1. Info 배열에 초기값들 추가 - 1 2번 2 2번 3 2번....
 * 		2. 마법시전 - Info에서 해당 위치 찾아서 0으로
 * 		3. 폭발 - Info배열에서 연석 4개인거 모두 0으로 만듬 -> ans에 더함 + stack 사용
 * 		4. 변화 - 새로운 배열 만들어서 여기에 추가
 * 		5. 이동	- 매번 0이 된 칸을 당겨서 배열 다시배치
 * Method : stack, 반복문 하나로 인덱스 두개 사용해서 배열 2개 처리하기
 * Error1 : 문제 잘못읽음 - 연속 4개이상이 없을때까지 무한반복
 * Error2 : 4개 연속 아닐때도 stack 비워줘야 하는데 안함 - else stack.clear();
 * Error3 : (change)while문 안끝나는디..? -> info배열이 먼저 끝나는 경우를 고려 안해서 구슬 번호가 0인 경우도 끝나도록 처리
 * Error4 : (move)근데 새로 만들어서 옮기면 i, j 옮기는거랑 뒤에 null값 되는거랑 처리해야함... -> 위치는 info 반복문에 맞춰서 저장하도록. 이후 구슬번호만 바꿔줌
 * Error5 : 찍어보니까 옮기다가 위치 꼬인듯. 메소드마다 info배열 찍어보면서 본문 결과와 비교 -> (move)idx를 nIdx로 씀...
 * Plus1 : 코드 더 줄일 수 있을거같은데...
 * Result : 코드 미리 안짜고, 변수랑 방법만 대충 생각해서 바로 코드로 옮겼더니 디버깅 좀 오래했다. 1시간..? 담부턴 더 구체적으로 계획하자...
 */
public class BJ21611_마법사상어와블리자드 {
	static int N, M, ans;
	static int[][] map;
	static Point[] info, newInfo;
	static Point shark;							// 상어의 위치
	static int[] di = {0, 1, 0, -1};			// 좌하우상
	static int[] dj = {-1, 0, 1, 0};		
	static int[] changeDir = {3, 1, 0, 2};		// 상하좌우를 바꿔줌
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 배열 크기
		M = Integer.parseInt(st.nextToken());	// 마법 시전수
		map = new int[N][N];					// 맵의 초기값
		info = new Point[N * N - 1];			// 칸의 번호를 인덱스로 맵을 옮겨 담아서 작업할것 
		shark = new Point(N / 2, N / 2, 0);		// 상어 위치
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeInfo();								// info배열 만들어줌(상어 위치 다음부터 차례대로...)
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int d = changeDir[Integer.parseInt(st.nextToken()) - 1];		// 방향 변환해 저장
			int s = Integer.parseInt(st.nextToken());						// 거리
			magic(d, s);													// 해당 뱡향과 거리만큼 마법 시전
			while(explode());												// 연속된 4개 이상의 수가 없을 때까지 폭발 반복
			change();														// 변화
//			print(i + 1);
		}
		System.out.println(ans);
	}
	
	static void makeInfo() {	// info배열을 만들어줌
		int i = shark.i;		// 상어 위치부터 시작해 달팽이 시계반대방향으로 ㄱㄱ..
		int j = shark.j;
		int d = 0;				// 처음 방향은 왼쪽
		int s = 1;				// 1개 2번, 2개 2번, 3개 2번... 방향은 갯수 1번씩 할 때마다 바뀜
		int idx = 0;			// info 배열의 인덱스(칸의 번호 0번부터)
		while(true) {			// 무한반복
			for(int n = 0 ; n < 2 ; n++) {			// 2번씩 ㄱㄱㄱ
				for(int k = 0 ; k < s ; k++) {		// s개의 배열의 값을 info에 저장해줌
					i += di[d];						// 해당 방향으로 한칸 감
					j += dj[d]; 
					info[idx++] = new Point(i, j, map[i][j]);	// info배열에 해당 위치와 구슬의 값을 저장해줌
					if(idx == info.length) return;				// info배열 다 채웠으면 중단(끝까지 갔으면)
				}
				d = (d + 1) % 4;					// 방향 바꿔줌 - 좌하우상
			}
			s++;									// 1, 2, 3... 계속 개수 늘려감
		}
	}
	
	static void magic(int d, int s) {				// 마법 뾰로롱
		int ni = shark.i + di[d];					// 마법 시전된 첫번째 위치를 저장
		int nj = shark.j + dj[d];
		for(int idx = 0 ; idx < info.length ; idx++) {			// info배열을 탐색하면서 마법 시전된 위치의 구슬값을 0으로 바꿔줌
			if(info[idx].i == ni && info[idx].j == nj) {		// 마법 시전된 위치면 
				info[idx].num = 0;								// 구슬값 0으로
				ni += di[d];									// 다음 마법 시전된 위치로...
				nj += dj[d];
				if(--s == 0) break;								// 해당 거리만큼 마법 시전 끝났다면 중단
			}
		}
		move();													// 마법 시전됬으므로 0인값 덮어지게 당겨줌
	}
	
	// stack사용 - 연속된 4개 이상의 구슬 있을 시, 해당 구슬의 처음 위치부터 0으로 바꿔주는 식으로 인덱스 처리하기 귀찮아서
	// stack에 넣고 Point 객체를 참조하므로 stack안에 있는 값을 바꿔주는 식으로 함
	// 만약 값 0으로 바꿔주는거 없다면 stack 사용 필요 X -> 예시 - 아래의 change 메소드
	static boolean explode() {									// 연속된 4개 이상의 구슬이 있을 시 폭발
		boolean isExplode = false;								// 폭발이 있는지를 저장
		Stack<Point> stack = new Stack<>();						// 연속된 4개 이상의 구슬을 찾기 위해 스택 사용
		stack.add(info[0]);										// 처음값 넣음
		for(int idx = 1 ; idx < info.length ; idx++) {			// info 배열 반복
			if(info[idx].num != stack.peek().num) {				// 다른 구슬 값이 나왔고
				if(stack.size() >= 4) {							// 연속된 구슬이 4개 이상이면
					isExplode = true;							// 폭발한다!
					ans += stack.peek().num * stack.size();		// 답을 더해줌
					while(!stack.isEmpty()) {					// 안의 값들을 0으로 바꿔줌(pop 사용하므로 stack도 비워짐)
						stack.pop().num = 0;
					}
				}
				else stack.clear();		// error 여기 안해줬었음// stack 비워줌
			}
			stack.add(info[idx]);								// 구슬이 같은값이던 다른값이던 stack에 넣어줌 
		}
		
		move();													// 구슬 이동
		return isExplode;										// 폭발여부 반환
	}
	
// 문제 - newInfo에 위치값 어케해줄거...(i, j 설정) -> 같은 idx꺼 놓으면 되잔슴..	
// error - while문 안끝나는디..?		
// 2가지 고려 - newInfo가 꽉차거나, info배열에 저장된 구슬이 적어서 newInfo가 꽉차지 않고 info배열이 먼저 끝나거나 
	static void change() {										// 변화 - 그룹별 구슬 개수, 구슬 번호를 새로 저장
		newInfo = new Point[info.length];						// info 배열 옮겨서 작업하려고 새로 만듬
		int size = 1, num = info[0].num;						// 구슬개수, 구슬번호
		int idx = 1, nIdx = 0;									// idx - info배열 인덱스, nIdx - newInfo배열 인덱스
		while(true) {																
			if(info[idx].num != num) {							// 다른 구슬번호가 나오면
				newInfo[nIdx] = new Point(info[nIdx].i, info[nIdx].j, size);	// nIdx에 해당하는 위치, 개수를 저장
				if(++nIdx == info.length) break;								// newInfo가 꽉차면 중단(이거 빼면 안되네)
				newInfo[nIdx] = new Point(info[nIdx].i, info[nIdx].j, num);		// 위치, 번호를 저장
				if(++nIdx == info.length) break;								// newInfo가 꽉차면 중단
				
				size = 1;														// 새로운 그룹을 계산하기 위해 구슬개수 1로
				num = info[idx].num;											// 구슬번호 새로
			}
			else size++;										// 같은 구슬번호면 개수 증가
			if(info[idx].num == 0) break;						// info에 저장된 구슬번호가 0이면(구슬이 없으면) 중단
			idx++;												// idx 증가
		}
		for( ; nIdx < newInfo.length ; nIdx++) {				// info배열이 먼저 끝난 경우, newInfo의 나머지를 0으로 채워줌
			newInfo[nIdx] = new Point(info[nIdx].i, info[nIdx].j, num);
		}
		
		info = newInfo;											// info배열에 newInfo 저장
	}
	
// 배열 한 배열에서 일일이 옮기는거 귀찮아... 그냥 새로 만들어서 옮길래
// error - 근데 새로 만들어서 옮기면 i, j 옮기는거랑 뒤에 null값 되는거랑 처리해야함...
	static void move() {												// 배열 당기기(중간 0값들 없애기)
		newInfo = new Point[info.length];								// 작업할 newInfo를 만들어줌
		int nIdx = 0;													// nIdx - newInfo의 인덱스
		for(int idx = 0 ; idx < info.length ; idx++) {					// info배열에 대해 반복
			newInfo[idx] = new Point(info[idx].i, info[idx].j, 0);		// 해당 info 배열의 위치를 newInfo배열에 넣어줌, error - idx를 nIdx로 씀...
			if(info[idx].num != 0) newInfo[nIdx++].num = info[idx].num; 	// 만약 구슬이 존재하면 번호를 newInfo에 옮겨 저장
		}
		
		info = newInfo;													// info배열에 newInfo 저장
	}
	
	static boolean isOutOfIdx(int i, int j) {							// 인덱스 나가는지를 반환
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	
	static void print(int i) {
		System.out.println(i + "번째) " );
		for(int idx = 0 ; idx < info.length ; idx++) {
			System.out.print(info[idx].num + " ");	
		}
		System.out.println();
	}
	
	static class Point {												// 위치, 구슬번호 저장
		int i, j, num;
		public Point(int i, int j, int num) {
			this.i = i;
			this.j = j;
			this.num = num;
		}
	}
}
