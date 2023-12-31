package day0818;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210818
 */
public class SW5644_WirelessCharging {
	static int TC;
	static int total;						// 충전량 합의 최댓값
	static int[] di = {0, -1, 0, 1, 0};		// 델타배열 - 이동X, 상, 우, 하, 좌
	static int[] dj = {0, 0, 1, 0, -1};
	static int[][] move;					// A와 B의 이동정보
	static BC[] bcInfo;						// BC의 정보
	static int M, A;
	public static void main(String[] args) throws NumberFormatException, IOException {
//		System.setIn(new FileInputStream(new File("sample_input2.txt")));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {						// TC만큼 반복
			total = 0;
			st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());				// 총 이동시간
			A = Integer.parseInt(st.nextToken());				// BC의 개수
			move = new int[2][M];			
			bcInfo = new BC[A];
			for(int i = 0 ; i < 2 ; i++) {						// 이동정보를 저장
				st = new StringTokenizer(in.readLine());
				for(int j = 0 ; j < M ; j++) {
					move[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for(int i = 0 ; i < A ; i++) {						// BC정보를 저장
				st = new StringTokenizer(in.readLine());
				int col = Integer.parseInt(st.nextToken()) - 1;	// 0부터 시작하도록 -1해줌
				int row = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcInfo[i] = new BC(row, col, c, p);
				// System.out.println(bcInfo[i]);
			}
			
			Point aPos = new Point(0, 0);						// A의 초기위치
			Point bPos = new Point(9, 9);						// B의 초기위치
			int t = 0;											// 시간은 0부터 시작
			while(true) {
				// System.out.println(aPos + " " + bPos);
				int aNum = 0;					// 현재 A가 가능한 BC 개수
				int bNum = 0;					// 현재 B가 가능한 BC 개수
				int[] aBCIdxs = new int[A];		// 현재 A의 위치에서 가능한 BC(충전소) 인덱스
				int[] bBCIdxs = new int[A]; 	// 현재 B의 위치에서 가능한 BC(충전소) 인덱스
				int maxCharge = 0;				// 이번 시간에서의 최대 충전량 합
				for(int i = 0 ; i < A ; i++) {	// 모든 BC에 대해
					BC now = bcInfo[i];
					if(Math.abs(now.i - aPos.i) + Math.abs(now.j - aPos.j) <= now.c) {	// 해당 BC 범위에 A가 존재한다면
						aBCIdxs[aNum++] = i;	// BC 인덱스를 저장
					} 
					if(Math.abs(now.i - bPos.i) + Math.abs(now.j - bPos.j) <= now.c) {	// 해당 BC 범위에 B가 존재한다면
						bBCIdxs[bNum++] = i;	// BC 인덱스를 저장
					}
				}
				if(aNum == 0) {					// A가 소속된 BC가 없는 경우
					for(int j = 0 ; j < bNum ; j++) {	// B의 충전량만 계산
						int nowCharge = bcInfo[bBCIdxs[j]].p;
						maxCharge = Integer.max(maxCharge, nowCharge);
					}
				}
				else if(bNum == 0) {			// B가 소속된 BC가 없는 경우
					for(int i = 0 ; i < aNum ; i++) {	// A의 충전량만 계산
						int nowCharge = bcInfo[aBCIdxs[i]].p;
						maxCharge = Integer.max(maxCharge, nowCharge);
					}
				}
				else {
					for(int i = 0 ; i < aNum ; i++) {			// A의 BC를 한번씩 모두 선택
						int aCharge = bcInfo[aBCIdxs[i]].p;		// 뽑은 BC의 충전량 저장
						for(int j = 0 ; j < bNum ; j++) {		// B의 BC를 한번씩 모두 선택
							int bCharge = 0;
							if(aBCIdxs[i] != bBCIdxs[j]) bCharge = bcInfo[bBCIdxs[j]].p;	// 둘이 같은 BC를 선택하지 않은 경우 B가 뽑은 BC의 충전량 저장
							// 둘이 같은 BC를 선택한 경우는 총 충전량이 한번 더해짐(bCharge가 0이므로)
							maxCharge = Integer.max(maxCharge, aCharge + bCharge);			// maxCharge와 현재 총 충전량 중 최댓값 저장
						}
					}
				}
				
				total += maxCharge;
				
				t++;							// 시간을 증가시켜
				if(M < t) break;				// M을 넘어가면 무한반복문 중단
				aPos.i += di[move[0][t - 1]];	// A와 B를 다음 위치로 이동
				aPos.j += dj[move[0][t - 1]];
				bPos.i += di[move[1][t - 1]];
				bPos.j += dj[move[1][t - 1]];
			}
			sb.append("#" + tc + " " + total + "\n");
		}
		System.out.println(sb);
	}
	static class Point {				// Point 클래스 - 인덱스 저장
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}
	static class BC extends Point {		// BC 클래스(인덱스를 사용하기 위해 Point클래스를 상속받음)
		int c, p;						// 충전범위, 처리량
		public BC(int i, int j, int c, int p) {
			super(i, j);
			this.c = c;
			this.p = p;
		}
		@Override
		public String toString() {
			return "BC [i=" + i + ", j=" + j + ", c=" + c + ", p=" + p + "]";
		}
	}
}
