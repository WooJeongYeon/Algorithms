// 피보나치네... dp[i] = dp[i - 1] + dp[i - 2] 
// dp[i - 1] -> 0또는1로 끝난 수에 0을 붙임
// dp[i - 2] -> 0또는1로 끝난 수에 01을 붙임 -> 이 두갤 더한 값

#include <stdio.h>
#define SIZE 91

int main() {
    int N;
    long long dp[SIZE][2] = {{0, 0}, {0, 1}, {1, 0}};

    scanf("%d", &N);

    for(int i = 3 ; i <= N ; i++) {
        dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
        dp[i][1] = dp[i - 1][0];
    }

    printf("%lld\n", dp[N][0] + dp[N][1]);

    return 0;
}