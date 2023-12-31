/*
 * Date : 2022.01.20
 * Level : BaekJoon Sliver 2
 * Difficulty : 쉬움
 * URL : https://www.acmicpc.net/problem/1780
 * Method : 분할정복
 * Error1 : 9칸으로 나누는걸 생각 못했다 
 * Result : 백준의 쿼드트리 문제와 비슷
 * Plus1 : 배열 전부 돌면서 다른 값 있을 때 break 걸지 말고, sum으로 다 더해서 판단하는게 더 깔끔할듯
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1780_종이의개수 {
	static int N;
	static int[][] map;
	static int[] cntArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		cntArr = new int[3];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cutPaper(0, 0, N);
		
		for(int i = 0 ; i < 3 ; i++) {
			System.out.println(cntArr[i]);
		}
	}
	static void cutPaper(int startI, int startJ, int size) {
		if(size == 1) {
			cntArr[map[startI][startJ] + 1]++;
			return;
		}
		boolean isCut = false;
		int num = map[startI][startJ];
		for(int i = 0 ; i < size ; i++) {
			for(int j = 0 ; j < size ; j++) {
				if(num != map[startI + i][startJ + j]) {
					isCut = true;
					break;
				}
			}
			if(isCut) break;
		}
		if(!isCut) {
			cntArr[map[startI][startJ] + 1]++;
			return;
		}
		else {
			int sub = size / 3;
			for(int i = 0 ; i < 3 ; i++) {
				for(int j = 0 ; j < 3 ; j++) {
					cutPaper(startI + i * sub, startJ + j * sub, sub);
				}
			}
		}
	}
}
