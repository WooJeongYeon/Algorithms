/*
 * Date : 2021.11.05
 * Level : Programmers Level 1
 * Difficulty : 쉬움
 * Time : 3분..?
 * URL : https://programmers.co.kr/learn/courses/30/lessons/76501
 */
class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for(int i = 0 ; i < absolutes.length ; i++) {
            answer += signs[i] ? absolutes[i] : -absolutes[i];
        }
        return answer;
    }
}