package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210824
 */
public class BJ10163_ColorPaper {
	static int N;				// 색종이의 개수
	static int SIZE = 1001;		// 색종이를 놓을 평면의 크기
	static int[][] map;			// 평면
	static int[] size;			// 최종적으로 보이는 각 색종이의 넓이
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(in.readLine());
		map = new int[SIZE][SIZE];
		size = new int[N];
		for(int n = 1 ; n <= N ; n++) {				
			st = new StringTokenizer(in.readLine());
			int si = Integer.parseInt(st.nextToken());	// 색종이의 시작 i값
			int sj = Integer.parseInt(st.nextToken());	// 색종이의 시작 j값
			int w = Integer.parseInt(st.nextToken());	// 색종이의 너비
			int h = Integer.parseInt(st.nextToken());	// 색종이의 높이
			for(int i = si ; i < w + si ; i++) {
				for(int j = sj ; j < h + sj ; j++) {	// 색종이가 차지하는 면적에 해당 색종이의 번호를 저장해줌(다른 색종이가 나중에 덮는 경우, 그 색종이로 덮어씌워 저장)
					map[i][j] = n;
				}
			}
		}
		for(int i = 0 ; i < SIZE ; i++) {				// 각 색종이가 차지하는 면적을 각각 계산
			for(int j = 0 ; j < SIZE ; j++) {
				if(map[i][j] > 0) size[map[i][j] - 1]++;
			}
		}
		for(int n = 0 ; n < N ; n++) {					// 각 색종이의 결과 면적을 StringBuilder에 더함
			sb.append(size[n] + "\n");
		}
		System.out.println(sb);
	}
}
