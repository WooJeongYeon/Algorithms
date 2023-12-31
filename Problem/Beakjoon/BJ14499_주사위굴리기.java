import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.05
 * Level : BaekJoon Gold 4
 * Difficulty : 중상
 * Time : 2시간..?
 * URL : https://www.acmicpc.net/problem/14499
 * Select1 : 주사위를 어케 굴릴지 - 배열 두개로 돌리기 VS 객체값 저장해 돌리기 VS 인덱스 저장해 돌리기(선택)
 * Thinking)
 * 		1. 어케 주사위를 굴릴건지	
 * 			- 주사위 각 면을 객체로 사용 - 값을 저장해 돌리면 겹치는 윗면, 아랫면 두 배열에서 계속 갱신해줘야 함
 * 			- 객체 저장! 6개의 Integer? -> Integer은 값변경 안된대 -> 그냥 클래스 만들기(Side)
 * 			- 인덱스를 저장해 돌릴 때, 인덱스로 Side의 값을 변경시키자. 저장된 인덱스는 그대로
 * 		2. 방향처리	
 * 			- 각 방향에 맞게 값을 돌리도록 함
 * 			- 0 : 오른쪽, 1 : 왼쪽, 2 : 위, 3 : 밑
 * Method : 같은 행/열에서의 배열회전
 * Error1 : 돌릴 때 for문에서 끝 인덱스 length - 2로 씀 -> length - 1
 * Error2 : leftRight[leftRight.length - 1]를 인덱스로 써야 하는걸 까먹음...객체값이 아니라 인덱스 저장하니까 코드가 엄청 헷갈림
 * Error3 : 아니 x, y라 하길래 당연하게 열, 행인줄 알았는데 x -> 행, y -> 열이네ㅋㅋㅋㅋㅋ(주사위 초기 위치)
 * Result : 겹치는 윗면, 아랫면을 매번 갱신해주는게 싫어서 객체값을 이용해 돌리는거 생각하는게 어려웠음..
 * Plus1 : 이걸 그냥 옮겨지는 주사위 값을 생각해서 직접 옮겨주네. 객체값 저장(or 인덱스 저장)해서 돌리는것만 생각했는데 ㅋㅋㅋㅋㅋ
 */
public class BJ14499_주사위굴리기 {
	static int N, M, K;
	static int[][] map;
	static Dice dice;
	static int[] di = {0, 0, -1, 1};
	static int[] dj = {1, -1, 0, 0};
	static final int UP = 0, DOWN = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		dice = new Dice(x, y);
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(in.readLine(), " ");
		for(int k = 0 ; k < K ; k++) {
//			print();
			
			int d = Integer.parseInt(st.nextToken()) - 1;
			if(!dice.move(d)) continue;			// 움직일 수 없으면 다음명령으로 
			if(map[dice.i][dice.j] == 0) {		// 맵에 저장된 값이 0이면
				map[dice.i][dice.j] = dice.sides[DOWN].num;	// 주사위 밑면의 값 복사
			} else {							// 맵에 저장된 값이 있으면
				dice.sides[DOWN].num = map[dice.i][dice.j];	// 주사위 밑면에 맵에 저장된 값 복사
				map[dice.i][dice.j] = 0;					// 맵에 저장된 값은 0으로
			}
			sb.append(dice.sides[UP].num + "\n");
		}
//		print();
		
		System.out.println(sb);
	}
	
//	static void print() {
//		System.out.println("UP : " + dice.sides[UP].num);
//		System.out.println("DOWN : " + dice.sides[DOWN].num);
//		for(int i = 0 ; i < 4 ; i++) {
//			System.out.print(dice.sides[dice.upDown[i]].num + " ");
//		}
//		System.out.println();
//		for(int i = 0 ; i < 4 ; i++) {
//			System.out.print(dice.sides[dice.leftRight[i]].num + " ");
//		}
//		System.out.println();
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < M ; j++) {
//				if(i == dice.i && j == dice.j) {
//					System.out.print("+ ");
//				}
//				else System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("------------------------------------");
//	}
	
	static class Dice {
		Side[] sides;		// 6개 면 저장 - 0인덱스-위 / 2인덱스-아래
		int[] upDown, leftRight; // 위아래 회전 / 왼오른 회전 - 면의 참조값 저장한 배열
		int i, j;
		public Dice(int i, int j) {
			this.sides = new Side[6];
			for(int s = 0 ; s < sides.length ; s++) {
				sides[s] = new Side();
			}
			this.upDown = new int[]{0, 1, 2, 3};	// 면 인덱스 저장 -> 맨앞 - 아래 - 뒤 - 위
			this.leftRight = new int[]{0, 5, 2, 4}; // 맨앞 - 오른 - 뒤 - 왼
			this.i = i;
			this.j = j;
		}
		boolean move(int d) {		// 주사위 이동시킬 수 있는지를 반환 - 그리고 이동시킴
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= M) {
				return false;
			}
			this.i = ni;
			this.j = nj;
			int tmp = 0;
			switch(d) {
				case 0:			// 오른쪽으로 이동
					tmp = sides[leftRight[leftRight.length - 1]].num;
					for(int j = leftRight.length - 1 ; j > 0 ; j--) {
						sides[leftRight[j]].num = sides[leftRight[j - 1]].num;
					}
					sides[0].num = tmp; 
					break;
				case 1:			// 왼쪽으로 이동
					tmp = sides[leftRight[0]].num;
					for(int j = 0 ; j < leftRight.length - 1 ; j++) {	// 인덱스 length - 2로 쓰기도 했고
						sides[leftRight[j]].num = sides[leftRight[j + 1]].num;
					}
					sides[leftRight[leftRight.length - 1]].num = tmp; // 여기서 문제발생
					break;
				case 2:			// 위쪽으로 이동
					tmp = sides[upDown[0]].num;
					for(int i = 0 ; i < upDown.length - 1 ; i++) {
						sides[upDown[upDown[i]]].num = sides[upDown[i + 1]].num;
					}
					sides[upDown[upDown.length - 1]].num = tmp; 
					break;
				case 3:			// 아래쪽으로 이동
					tmp = sides[upDown[upDown.length - 1]].num;
					for(int i = upDown.length - 1 ; i > 0 ; i--) {
						sides[upDown[i]].num = sides[upDown[i - 1]].num;
					}
					sides[0].num = tmp; 
					break;
			}
			return true;
		}
	}
	static class Side {			// 주사위 면(숫자 저장됨) -> 참조값으로 저장해서 돌리기 위함
		int num;
	}
}
