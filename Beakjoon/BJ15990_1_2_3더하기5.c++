#include <stdio.h>
#define SIZE 100001
#define DIVIDE 1000000009


int main() {
    int T, n;
    int dp[SIZE][4] = {0};

    dp[1][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

    for(int i = 4 ; i < SIZE ; i++) {
        dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % DIVIDE;
        dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % DIVIDE;
        dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % DIVIDE;
    }

    scanf("%d", &T);

    for(int tc = 0 ; tc < T ; tc++) {
        scanf("%d", &n);
        printf("%d\n", (((dp[n][1] + dp[n][2]) % DIVIDE) + dp[n][3]) % DIVIDE);
    }

    return 0;
}