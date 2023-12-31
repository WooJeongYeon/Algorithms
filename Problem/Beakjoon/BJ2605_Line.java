package day0814;

import java.util.LinkedList;
import java.util.Scanner;
/*
 * Date : 210814
 */
public class BJ2605_Line {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int N = in.nextInt();
		
		LinkedList<Integer> list = new LinkedList<>();		// 줄 세울 리스트(중간에 삽입하기 위해 링크드리스트 사용)
		for(int i = 1 ; i <= N ; i++) {
			list.add(list.size() - in.nextInt(), i);		// 뒤에서부터 입력받은 만큼 이동한 자리에 해당 수를 저장(1부터) 
		}
		for(int x : list)		// 최종 줄 선 순서대로 출력
			System.out.print(x + " ");
	}
}
