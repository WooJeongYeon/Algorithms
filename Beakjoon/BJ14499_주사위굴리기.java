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
 * Select1 : 
 * Thinking : 
 * Method : 
 * Help : 
 * Error1 : 
 * Result : 
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
			if(!dice.move(d)) continue;
			if(map[dice.i][dice.j] == 0) {
				map[dice.i][dice.j] = dice.sides[DOWN].num;
			} else {
				dice.sides[DOWN].num = map[dice.i][dice.j];
				map[dice.i][dice.j] = 0;
			}
			sb.append(dice.sides[UP].num + "\n");
		}
//		print();
		
		System.out.println(sb);
	}
	
	static void print() {
		System.out.println("UP : " + dice.sides[UP].num);
		System.out.println("DOWN : " + dice.sides[DOWN].num);
		for(int i = 0 ; i < 4 ; i++) {
			System.out.print(dice.sides[dice.upDown[i]].num + " ");
		}
		System.out.println();
		for(int i = 0 ; i < 4 ; i++) {
			System.out.print(dice.sides[dice.leftRight[i]].num + " ");
		}
		System.out.println();
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(i == dice.i && j == dice.j) {
					System.out.print("+ ");
				}
				else System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("------------------------------------");
	}
	
	static class Dice {
		Side[] sides;		// 0인덱스-위 / 2인덱스-아래
		int[] upDown, leftRight;
		int i, j;
		public Dice(int i, int j) {
			this.sides = new Side[6];
			for(int s = 0 ; s < sides.length ; s++) {
				sides[s] = new Side();
			}
			this.upDown = new int[]{0, 1, 2, 3};
			this.leftRight = new int[]{0, 5, 2, 4};
			this.i = i;
			this.j = j;
		}
		boolean move(int d) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= M) {
				return false;
			}
			this.i = ni;
			this.j = nj;
			int tmp = 0;
			switch(d) {
				case 0:
					tmp = sides[leftRight[leftRight.length - 1]].num;
					for(int j = leftRight.length - 1 ; j > 0 ; j--) {
						sides[leftRight[j]].num = sides[leftRight[j - 1]].num;
					}
					sides[0].num = tmp; 
					break;
				case 1:
					tmp = sides[leftRight[0]].num;
					for(int j = 0 ; j < leftRight.length - 1 ; j++) {	// 인덱스 length - 2로 쓰기도 했고
						sides[leftRight[j]].num = sides[leftRight[j + 1]].num;
					}
					sides[leftRight[leftRight.length - 1]].num = tmp; // 여기서 문제발생
					break;
				case 2:
					tmp = sides[upDown[0]].num;
					for(int i = 0 ; i < upDown.length - 1 ; i++) {
						sides[upDown[upDown[i]]].num = sides[upDown[i + 1]].num;
					}
					sides[upDown.length - 1].num = tmp; 
					break;
				case 3:
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
	static class Side {
		int num;
	}
}
