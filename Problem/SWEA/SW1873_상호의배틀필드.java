	package day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210804
 */
public class SW1873_상호의배틀필드 {
	static int[] di = {-1, 1, 0, 0};	// 위, 아래, 왼쪽, 오른쪽 방향 저장
	static int[] dj = {0, 0, -1, 1};
	static char[] tankD = {'^', 'v', '<', '>'};		// 탱크 방향모양 저장(인덱스로 접근) - 모두 위 아래 왼 오순
	static char[] tankAl = {'U', 'D', 'L', 'R'};	// 탱크 영어모양 저장(인덱스로 접근)
	static int posI = -1;	// 현재 탱크 위치 인덱스
	static int posJ = -1;
	static int dir = -1;	// 탱크의 방향(위의 배열들에 접근할 인덱스 0~3 저장)
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());		// 테스트케이스 몇번인지 저장
		for(int tc = 0 ; tc < TC ; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());	// 두 수를 tokenizer을 이용해 공백 기준으로 받아옴
			int H = Integer.parseInt(st.nextToken());	// 맵의 높이
			int W = Integer.parseInt(st.nextToken());	// 맵의 너비
			char[][] map = new char[H][];		// H * W크기의 맵 생성
			for(int i = 0 ; i < H ; i++) {
				map[i] = in.readLine().toCharArray();	// 행마다 char배열을 입력받아옴
			}
			findTank(H, W, map); 	// 탱크의 위치와 방향을 찾아오는 메소드
			
			int N = Integer.parseInt(in.readLine());	// 명령어 개수 저장
			String commands = in.readLine();			// 명령어를 입력받음
			for(int i = 0 ; i < N ; i++) {		// 모든 명령어에 대해
				char com = commands.charAt(i);	// 현재 명령어 저장
				if(com == 'U' || com == 'D' || com == 'L' || com == 'R') {	// 이동 명령인 경우
					// 탱크 방향 갱신
 					for(int j = 0 ; j < tankAl.length ; j++) {		// 해당 방향에 해당하는 인덱스를 찾아 저장
						if(tankAl[j] == com) {
							dir = j;	// 명령어에 대한 방향 설정
							break;		// 4개짜리 반복문 break 거나 안거나 같대ㅋㅋㅋㅋㅋㅋㅋ
						}
					}
					int nextI = posI + di[dir];		// 해당 방향으로 한칸 간다면
					int nextJ = posJ + dj[dir];
					// 배열을 벗어나지 않고 평지인 경우, 현재 위치를 그 위치로 설정(벽도 X)
					// 못가는 곳이 많으면 갈 수있는 곳을 보기(&&)
					if(nextI >= 0 && nextI < H && nextJ >= 0 && nextJ < W && map[nextI][nextJ] == '.') {						
						posI = nextI;
						posJ = nextJ;
					}
				}
				else if(com == 'S') {		// 슈팅 명령이라면
					int r = posI;			// 해당 방향을 검사할 인덱스 설정
					int c = posJ;
					while(true) {
						r += di[dir];		// 쏜 탄환이 해당 방향으로 한칸 이동해
						c += dj[dir];
						// 배열을 벗어나거나 강철벽이면
						if(r < 0 || r >= H || c < 0 || c >= W || map[r][c] == '#') break;
						// 벽돌이라면, 벽돌 부수고 평지로 변환 후 중단
						else if(map[r][c] == '*') {
							map[r][c] = '.';
							break;
						}
					}
				}
			}
			map[posI][posJ] = tankD[dir];		// 현재 위치에 탱크를 알맞은 방향으로 설정하고
			System.out.print("#" + (tc + 1) + " ");
			for(int i = 0 ; i < H ; i++) {		// 결과 게임 맵을 출력
				for(int j = 0 ; j < W ; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}	
		}
	}
	static void findTank(int H, int W, char[][] map) {
		for(int i = 0 ; i < H ; i++) {		// 모든 map에 대해
			for(int j = 0 ; j < W ; j++) {
				for(int k = 0 ; k < tankD.length ; k++) {	// 탱크 4가지 방향에 대해 
					if(map[i][j] == tankD[k]) {			// 현재 위치의 값과 탱크방향값이 같다면(탱크를 찾았다면)
						posI = i;		// 현재 위치로 설정
						posJ = j;
						dir = k;	// 탱크의 방향 설정
						map[i][j] = '.';	// 탱크의 위치를 저장하므로 탱크 모양값 필요없음(평지로 저장)
						return;		// 반환(메소드 종료)
					}
				}
			}
		}
	}
}
