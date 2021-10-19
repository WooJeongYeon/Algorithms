package day0803;

import java.util.Scanner;
/*
 * Date : 2021.08.03
 * Level : SWEA D3
 * Method : 반복문
 */
public class SW1208_Flatten {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TC = 10;
		final int BOX_NUM = 100;
		for(int tc = 0 ; tc < TC ; tc++) {
			int dump = in.nextInt();
			int[] boxHeightArr = new int[BOX_NUM];			
			for(int i = 0 ; i < BOX_NUM ; i++) {	// 박스 높이를 입력받음
				boxHeightArr[i] = in.nextInt();
			}
			boolean isFinal = false;				// 평준화가 끝났는지(높이차가 0 또는 1)
			int min = boxHeightArr[0];
			int max = boxHeightArr[0];
			for(int i = 0 ; i < dump ; i++) {		// dump수만큼 반복
				max = boxHeightArr[0];
				int maxId = 0;
				min = boxHeightArr[0];
				int minId = 0;
				for(int j = 0 ; j < BOX_NUM ; j++) {	// 모든 상자에 대해
					if(max < boxHeightArr[j]) {			// 최고점의 상자와 최저점의 상자를 찾음
						max = boxHeightArr[j];
						maxId = j;
					}
					if(min > boxHeightArr[j]) {
						min = boxHeightArr[j];
						minId = j;
					}
				}
				if(max - min <= 1) {			// 차이가 1 이하이면
					isFinal = true;				// 평준화 끝났음을 표시하고
					break;						// 중단
				}
				boxHeightArr[maxId]--;			// 가장 높은 상자 1 감소
				boxHeightArr[minId]++;			// 가장 낮은 상자 1 증가
			}
			if(!isFinal) {		// 높이가 0 또는 1이 아닌 경우
				min = boxHeightArr[0];
				max = boxHeightArr[0];
				for(int i = 0 ; i < BOX_NUM ; i++) {		// 최고점과 최저점을 찾아
					if(max < boxHeightArr[i]) max = boxHeightArr[i];
					if(min > boxHeightArr[i]) min = boxHeightArr[i];
				}
			}
			System.out.println("#" + (tc + 1) + " " + (max - min));	// 출력
		}
	}
}
