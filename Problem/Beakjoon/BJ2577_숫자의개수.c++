#include <stdio.h>

int a, b, c, result;
int cntArr[10] = {0};

int main() {

    scanf("%d %d %d", &a, &b, &c);
    result = a * b * c;

    while(result > 0) {
        cntArr[result % 10]++;
        result /= 10;
    }

    for(int i = 0 ; i < 10 ; i++) {
        printf("%d\n", cntArr[i]);
    }

    return 0;
}