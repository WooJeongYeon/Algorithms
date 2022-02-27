package programmers;

import java.util.PriorityQueue;
/*
 * Date : 2121.05.05(재)
 * Level : BaekJoon Sliver 5 / SWEA D3
 * Difficulty : 상
 * Why : 파라매트릭 서치를 몰라서...
 * URL : https://programmers.co.kr/learn/courses/30/lessons/86053
 * Thinking : PQ로 범위 지정해서 풀어야지? 응 안되네? 이분탐색으로 푸는 문제래!
 * Method : 이분탐색(파라매트릭 서치)
 * Error1 : 정말 정말 계속 안되는 문제가 있었다...(예전)
 * Error2 : (이분탐색)long으로 다 변환해서 풀었다. 계속 틀리길래..
 * Help : 이분탐색으로 푸는 문제라고 들었다.
 * Result : 엄청 오래 잡고 있떤 문제였고(3일?) 계속 하나만 틀려서 나중에 이분탐색으로 풀어야한다고 듣고 몇달 지나서 스터디 문제라 다시풀었다.
 *          가능한 최대 금, 은 개념은 가져가는게 맞는 문제였네.
 */
//
public class PRO86053_금과은운반하기 {
    class Solution {
        public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
            long ans = 0;
            long min = 0, max = 1000000000000000L;      // 가능한 최소 시간, 최대 시간
            int N = g.length;
            long aL = a, bL = b;
            while(min <= max) {                         // 파라메트릭 서치
                long sum = 0, gMax = 0, sMax = 0;       // 옮길 수 있는 합, 가능한 최대 금, 가능한 최대 은
                long mid = (min + max) / 2;
                for(int i = 0 ; i < N ; i++) {          // 모든 도시에 대해 검증
                    long cnt = 0;
                    long gold = g[i], silver = s[i], weight = w[i], time = t[i];
                    if(mid >= time) {                   // 한번 옮길 수 있으면 고고
                        cnt = 1;
                        cnt += (mid - time) / (2 * time);   // 그 이후 옮길 수 있는 횟수 구함
                    }

                    if(cnt * weight >= (gold + silver)) {   // 횟수동안 금, 은 전부 옮길 수 있다면
                        sum += gold + silver;
                        gMax += gold;
                        sMax += silver;
                    }
                    else {                                  // 일부만 옮길 수 있다면
                        sum += cnt * weight;
                        gMax += Math.min(gold, cnt * weight);
                        sMax += Math.min(silver, cnt * weight);
                    }
                }
                if((aL + bL) <= sum && aL <= gMax && bL <= sMax) {  // 필요한 금, 은을 모두 옮길 수 있다면 답이 될 수 있음!
                    ans = mid;
                    max = mid - 1;                    // 더 작은 값(시간) 구하러 범위 왼쪽으로 셋팅
                }
                else min = mid + 1;                   // 불가능하다면 시간이 더 필요하므로 범위 오른쪽으로 셋팅
            }
            return ans;
        }
    }
    
    // 1개가 시간초과난 풀이
    class Solution1 {
        public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
            long time = 0, unSSum = 0, gMax = 0, sMax = 0;
            PriorityQueue<Truck> pq = new PriorityQueue<>();
            for (int i = 0; i < t.length; i++) {
                if (g[i] == 0 && s[i] == 0) continue;
                pq.offer(new Truck(i, t[i], 0));
            }

            while (true) {
                // a, b 확인해서 가능하면 break
                if (a == 0 && b == 0) break;
                if ((unSSum >= (long) a + (long) b) && a <= gMax && b <= sMax) break;

                // pq에서 꺼내서 검사
                Truck truck = pq.poll();
                time = truck.time;        // 시간 갱신

                // 1. 금이나 은으로만 이루어진 경우
                if (g[truck.idx] == 0 || s[truck.idx] == 0) {
                    if (g[truck.idx] > 0) {        // 금만 있는 경우
                        int sub = Math.min(a, Math.min(g[truck.idx], w[truck.idx]));
                        g[truck.idx] -= sub;
                        a -= sub;
                    } else {                    // 은만 있는 경우
                        int sub = Math.min(b, Math.min(s[truck.idx], w[truck.idx]));
                        s[truck.idx] -= sub;
                        b -= sub;
                    }

                    // 금 은 모두 0이 아니면 time + t * 2해서 다시 넣음
                    if (a != 0 || b != 0) {
                        truck.time += t[truck.idx] * 2L;
                        pq.offer(truck);
                    }
                } else {
                    // 2. 둘이 섞여있는 경우
                    // 이전 range 빼고
                    unSSum -= truck.cnt * w[truck.idx];
                    gMax -= Math.min((long) g[truck.idx], (long) w[truck.idx] * truck.cnt);
                    sMax -= Math.min((long) s[truck.idx], (long) w[truck.idx] * truck.cnt);
                    truck.cnt++;
                    // 없앨건지 계산하고
                    // 없애면 -> a, b 갱신
                    if (truck.cnt * (long) w[truck.idx] >= ((long) g[truck.idx] + (long) s[truck.idx])) {
                        a -= Math.min(a, g[truck.idx]);
                        b -= Math.min(b, s[truck.idx]);
                    }
                    // 안없애면 -> range 갱신(map), unSRange 갱신, 시간 갱신해서 다시넣음(pq)
                    else {
                        unSSum += truck.cnt * (long) w[truck.idx];
                        gMax += Math.min((long) g[truck.idx], (long) w[truck.idx] * truck.cnt);
                        sMax += Math.min((long) s[truck.idx], (long) w[truck.idx] * truck.cnt);
                        truck.time += t[truck.idx] * 2L;
                        pq.offer(truck);
                    }
                }
            }

            return time;
        }

        class Truck implements Comparable<Truck> {
            int idx;
            long time;
            int cnt;

            public Truck(int idx, long time, int cnt) {
                this.idx = idx;
                this.time = time;
                this.cnt = cnt;
            }

            public int compareTo(Truck o) {
                if (this.time > o.time) return 1;
                else if (this.time < o.time) return -1;
                else return 0;
            }
        }
    }
}
