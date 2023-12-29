// 참고 - https://www.acmicpc.net/source/21697379

#include <stdio.h>
#define SIZE 201
#define MOD 1000000000

int main() {
    int N, K;
    int dp[SIZE] = {1};

    scanf("%d %d", &N, &K);

    for(int i = 1 ; i <= K ; i++ ) {
        for(int j = 1 ; j <= N ; j++) {
            dp[j] = (dp[j - 1] + dp[j]) % MOD;
        }
    }

    printf("%d\n", dp[N]);

    return 0;
}