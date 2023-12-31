#include <stdio.h>

// GCD(Greatest Common Divisor) - 최대공약수 -> 유클리드 호제법
// LCM(Least Common Multiple) - 최소공배수

int main() {
    int x, y, a, b, tmp;
    scanf("%d %d", &x, &y);

    a = x; b = y;

    while(b != 0) {
        tmp = a % b;
        a = b;
        b = tmp;
    }

    printf("%d\n%d\n", a, a * (x / a) * (y / a));

    return 0;
}