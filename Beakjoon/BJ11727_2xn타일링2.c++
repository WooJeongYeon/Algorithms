#include <stdio.h>
#define SIZE 1001

int main() {
    int n;
    int dp[SIZE] = {0};
    // int dp[SIZE] = { 0, 1, 3, 0 };
    scanf("%d", &n);

    dp[1] = 1;
    dp[2] = 3;
    for(int i = 3 ; i <= n ; i++) {
        dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
    }

    printf("%d\n", dp[n]);

    return 0;
}