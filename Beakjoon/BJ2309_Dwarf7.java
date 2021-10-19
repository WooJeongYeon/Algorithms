package day0805;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Date : 210805
 */
public class BJ2309_Dwarf7 {
	private static int[] heights;
	private static int[] temp;
	private static int[] result;
	private final static int DWARF_NUM = 9;
	private final static int temp_NUM = 7;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		heights = new int[DWARF_NUM];
		temp = new int[temp_NUM];
		result = new int[temp_NUM];
		for(int i = 0 ; i < DWARF_NUM ; i++) {		// 난쟁이들의 키 입력받아 저장
			heights[i] = in.nextInt();
		}
		combi(0, 0, 0);			// 조합 시작
		Arrays.sort(result);	// 결과를 오름차순
		for(int i = 0 ; i < temp_NUM ; i++) {		// 출력
			System.out.println(result[i]);
		}
	}
	// 조합을 사용해 합이 100이 되는 7난쟁이 키들을 구함
	private static void combi(int start, int idx, int sum) {
		if(idx == temp_NUM && sum == 100) {	// 합이 100이고 7명 모두 선택했으면
			for(int i = 0 ; i < temp_NUM ; i++) {		// 결과 배열에 저장
				result[i] = temp[i];
			}
			return;
		}
		else if(idx == temp_NUM || sum > 100) return;	// 100이 넘거나 7명 모두 선택했으면 중단
		for(int i = start ; i < DWARF_NUM ; i++) {		// 9명의 난쟁이에 대한 9C7 조합
			temp[idx] = heights[i];		// 현재 자리에 현재 난쟁이의 키 저장
			combi(i + 1, idx + 1, sum + heights[i]);	// 다음 난쟁이부터 조합, 다음 자릿수, 합에 현재 난쟁이의 키 더함
		}
 	}
}
