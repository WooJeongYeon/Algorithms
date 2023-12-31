/*
 * Date : 2022.01.24
 * Level : Programmers level 3
 * Difficulty : ��
 * Time : 1h 40m
 * URL : https://programmers.co.kr/learn/courses/30/lessons/60059
 * Select1 : �ݺ����� ��û �������� �Լ��� ������ ����
 * Thinking)
 * 		1. Key�� 4�� ȸ��
 * 			- Lock�迭���� 0 ~ M + N - 2�ȿ� ���ϴ� ��� ������ i, j�� ���ҽ��Ѱ��鼭 Key�� ��ġ���� ���������� �Ǵ�
 * 			- �� �� Lock�� Ȩ�� ����ŷ�Ǵ� ������ ����� �������� ���
 * Method : �迭 ȸ��, �迭
 * Error1 : ȸ���Ҷ� ���ο� �迭�� ������ ��
 * Error2 : ��Ÿ�� ���� - ȸ���� �� M�� N���� �Ἥ ũ�� �ٸ� ��� �ε��� ��������
 * Result : ������ ��°�, ��� ����ŷ������ ���ؾ����� ����� ������
 */
import java.util.*;

public class PRO60059_�ڹ���Ϳ��� {
	class Solution {
	    int[][] keyMap;
	    int[][] lockMap;
	    int M, N;
	    LinkedList<int[]> lockHomeList;
	    public boolean solution(int[][] key, int[][] lock) {
	        boolean answer = false;
	        lockHomeList = new LinkedList<>();
	        keyMap = key;
	        lockMap = lock;
	        M = key.length;
	        N = lock.length;
	        for(int i = 0 ; i < N ; i++) {		// �ڹ����� Ȩ ��ġ�� ����
	            for(int j = 0 ; j < N ; j++) {
	                if(lockMap[i][j] == 0) {
	                    lockHomeList.offer(new int[]{i, j});
	                }
	            }
	        }
	        for(int n = 0 ; n < 4 ; n++) {
	            rotateKeyMap();			// ȸ��
	            if(getIsOpen()) {
	                answer = true;
	                break;
	            }
	        }
	        return answer;
	    }
	    void rotateKeyMap() {
	        int[][] newArr = new int[M][M];     // 1. ���ο� �迭�� ������ ��
	        for(int i = 0 ; i < M ; i++) {
	            for(int j = 0 ; j < M ; j++) {
	                newArr[M - j - 1][i] = keyMap[i][j];
	            }
	        }
	        keyMap = newArr;
	    }
	    boolean getIsOpen() {
	        for(int i = 0 ; i < M + N - 1 ; i++) {		
	            for(int j = 0 ; j < M + N - 1 ; j++) {
	                if(compare(i, j)) return true;
	            }
	        }
	        return false;
	    }
	    boolean compare(int startLockI, int startLockJ) {
	        // lock�� Ȩ �κ��� ������ ���Ե��� ������ false
	        for(int[] point : lockHomeList) {
	            if(point[0] <= startLockI && point[0] >= (startLockI - M + 1) && point[1] <= startLockJ && point[1] >= (startLockJ - M + 1))
	                continue;
	            return false;
	        }
	        for(int i = 0 ; i < M ; i++) {		// Key�� Lock �񱳹��� ��� i, j ���������� ���ҽ��Ѱ��鼭 ��
	            for(int j = 0 ; j < M ; j++) {
	                //if(isOutOfIdx(M - 1 - i, M - 1 - j, M)) continue;				// Key�� ���� ����� ����
	                if(isOutOfIdx(startLockI - i, startLockJ - j, N)) continue;		// Lock ���� ����� continue
	                if(keyMap[M - 1 - i][M - 1 - j] == lockMap[startLockI - i][startLockJ - j] ) {
	                    return false;
	                }
	            }
	        }
	        return true;
	    }
	    boolean isOutOfIdx(int i, int j, int size) {
	        return i < 0 || i >= size || j < 0 || j >= size;
	    }
//	    void print(int[][] map) {
//	        System.out.println("--------------------------");
//	        for(int i = 0 ; i < map.length ; i++) {
//	            for(int j = 0 ; j < map[0].length ; j++) {
//	                System.out.print(map[i][j] + " ");
//	            }
//	            System.out.println();
//	        }
//	    }
	}
}
