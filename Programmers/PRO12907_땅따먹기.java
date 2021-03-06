/*
 * Date : 2022.01.20
 * Level : Programmers level 2
 * Difficulty : 雌
 * Why : 祢澗 号狛聖 乞牽畏嬢辞 微闘 条嬢辞 念ばばば
 * URL : https://programmers.co.kr/learn/courses/30/lessons/12913?language=java
 * Thinking : 戚戚穿 楳引 戚穿 楳拭辞 笛葵 蓄窒馬形 敗
 * Method : DP
 * Help : DP研 1託据 壕伸稽 馬形壱 梅澗汽 2託据 壕伸稽 熱嬢醤鞠希虞ばばばば
 * Result : DP 格巷 嬢形酔ばば 覗稽益掘袴什 嬢形ぬばば
 */
public class PRO12907_競魚股奄 {
	final int size = 4;
    int solution(int[][] land) {
        int N = land.length;
        int[][] memo = new int[N][size];
        for(int i = 0 ; i < size ; i++) {
            memo[0][i] = land[0][i];
        }
        for(int i = 1 ; i < N ; i++) {
            for(int j = 0 ; j < size ; j++) {
                int max = 0;
                for(int k = 0 ; k < size ; k++) {
                    if(j == k) continue;
                    max = Math.max(memo[i - 1][k], max);
                }
                memo[i][j] = max + land[i][j];
            }
        }
        int ans = 0;
        for(int i = 0 ; i < size ; i++) {
            ans = Math.max(ans, memo[N - 1][i]);
        }
        return ans;
    }
}
