package day0805;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/*
 * Date : 210805
 */
public class SW1225_GeneratePassword_Array {
	private static int TC = 10;		// 테스트케이스 수
	private static int len = 8;		// 암호 길이
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		for(int tc = 1 ; tc <= TC ; tc++) {	// 테스트케이스 수만큼 반복
			in.nextInt();	// 필요없는 입력을 받아서 없앰
			Queue<Integer> queue = new LinkedList<Integer>();	// LinkedList를 저장한 queue참조자 생성
			for(int i = 0 ; i < len ; i++) {	// 큐에 암호를 각각 넣음
				queue.offer(in.nextInt());
			}
			int i = 0;	// 사이클마다 빼는 수(0 ~ 4를 일단 가지고 뺄때 -1을 더함)
			String result = "";	// 결과를 저장할 String 변수
			while(true) {		// 무한 반복
				int now = queue.poll() - i - 1;	// 큐에서 요소를 꺼내서 -i -1(1~5)만큼 뺌
				if(now <= 0) {	// 0보다 작으면
					now = 0;	// 0으로 만들고
					queue.offer(now);	// 큐에 추가
					for(int j = 0 ; j < len ; j++) {	// 큐를 출력하기 위해 하나씩 빼서 String 변수에 추가
						result += queue.poll() +" ";
					}
					break;	// 반복문 종료
				}
				queue.offer(now);	// 0보다 아직 크면 큐에 추가하고
				i = (i + 1) % 5;	// i를 하나 증가
			}
			System.out.println("#" + tc + " " + result);	// 결과 출력ㄴ
		}
	}
}
