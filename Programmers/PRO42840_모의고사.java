package day1128;
/*
 * Date : 2021.11.28
 * Level : Programmers level 1
 * URL : https://programmers.co.kr/learn/courses/30/lessons/42840
 */
class Solution {
    int[][] arr = {{1, 2, 3, 4, 5},
                   {2, 1, 2, 3, 2, 4, 2, 5},
                   {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
    final int SIZE = 3;
    public int[] solution(int[] answers) {
        int[] result = new int[SIZE];
        for(int i = 0 ; i < answers.length ; i++) {
            for(int j = 0 ; j < SIZE ; j++) {
                if(arr[j][i % arr[j].length] == answers[i]) {
                    result[j]++;
                }
            }
        }
        int max = -1;
        for(int i = 0 ; i < SIZE ; i++) {
            if(max < result[i]) {
                max = result[i];
            }
        }
        int size = 0;
        for(int i = 0 ; i < SIZE ; i++) {
            if(max == result[i]) size++;
        }
        int[] answer = new int[size];
        int idx = 0;
        for(int i = 0 ; i < SIZE ; i++) {
            if(max == result[i])
                answer[idx++] = i + 1;
        }
        
                
        return answer;
    }
}
