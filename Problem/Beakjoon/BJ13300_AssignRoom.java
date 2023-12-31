package day0820;

import java.util.Scanner;
/*
 * Date : 210820
 */
public class BJ13300_AssignRoom {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();				// N명의 학생에 대해
		int K = in.nextInt();				// 한 방에 K명까지 저장
		int result = 0;						// 방의개수
		int[][] students = new int[2][6];	// 학년, 성별을 기준으로 몇명인지를 저장
		for(int i = 0 ; i < N ; i++) {	
			students[in.nextInt()][in.nextInt() - 1]++;
		}
		for(int i = 0 ; i < 2 ; i++) {		// 성별에 대해
			for(int j = 0 ; j < 6 ; j++) {	// 학년에 대해
				result += students[i][j] / K;		// 방의 최대인원으로 나눠서 방의 개수 구함
				students[i][j] %= K;				// 다 들어간 후 나머지를 구해
				if(students[i][j] > 0)				// 남은 학생이 존재하면
					result += 1; 					// 그 학생을 위한 방을 하나 증가
			}
		}
		System.out.println(result);
	}
}
