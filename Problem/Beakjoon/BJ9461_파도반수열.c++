#include <stdio.h>
#define SIZE 101

int main() {
    int tc, n;
    long long dp[SIZE] = {0, 1, 1, 1, 2, 2};

    for(int i = 6 ; i < SIZE ; i++) {
        dp[i] = dp[i - 1] + dp[i - 5];
    }

    scanf("%d", &tc);
    for(int t = 0 ; t < tc ; ++t) {
        scanf("%d", &n);
        printf("%lld\n", dp[n]);
    }
}