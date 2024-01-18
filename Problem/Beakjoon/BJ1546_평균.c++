#include <stdio.h>

int main() {
    int N, sum = 0, max = 0, x;
    scanf("%d", &N);
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &x);
        sum += x;
        max = max < x ? x : max;
    }

    printf("%lf", (((sum) * 100) / (double)max) / N);
}