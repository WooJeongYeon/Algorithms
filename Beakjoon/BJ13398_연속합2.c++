#include <stdio.h>
#define SIZE 100001
#define max(x, y) x > y ? x : y


int n, dp[SIZE][2], num, max;

int main() {
    scanf("%d", &n);
    scanf("%d", &dp[0][0]);

    max = dp[0][0];

    for(int i = 1 ; i < n ; i++) {
        scanf("%d", &num);
        dp[i][0] = num;
        if(dp[i - 1][0] > 0)
            dp[i][0] += dp[i - 1][0];
        dp[i][1] = max(dp[i - 1][0], dp[i - 1][1] + num);
        max = max(max, dp[i][0]);
        max = max(max, dp[i][1]);
    }

    printf("%d\n", max);

    return 0;
}