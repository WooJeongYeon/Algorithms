#include <stdio.h>

int gcd(int a, int b) {
    int tmp;
    while(b > 0) {
        tmp = a % b;
        a = b;
        b = tmp;
    }

    return a;
}

int main() {
    int N, S, a, dist, ans = 0;

    scanf("%d %d", &N, &S);
    
    for(int i = 0 ; i < N ; i++) {
        scanf("%d", &a);
        dist = (S - a) > 0 ? S - a : a - S;
        ans = gcd(dist, ans);
    }

    printf("%d\n", ans);

    return 0;
}