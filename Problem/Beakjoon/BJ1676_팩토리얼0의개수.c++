// 10의 '약수'인 2와 5의 개수 중 적은쪽이 0의 개수이네
// 5, 25, 125로 나눠주면 된대
// 난 일케 안품ㅠ

#include <stdio.h>

int main() {
    int x = 1, n, cnt = 0;
    scanf("%d", &n);

    for(int i = 1 ; i <= n ; i++) {
        x *= i;
        while(true) {
            if(x % 10 != 0) {
                x %= 1000;
                break;
            }
            x /= 10;
            cnt++;
        }
    }
    
    printf("%d\n", cnt);

    return 0;
}