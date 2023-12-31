#include <stdio.h>
#include <string.h>

int main() {
    char binaryArr[1000001];
    scanf("%s", binaryArr);
    int n = (strlen(binaryArr) + 2) % 3, sum = 0;

    for(int i = 0 ; binaryArr[i] != '\0' ; i++) {
        sum += (binaryArr[i] - '0') << n;
        if(--n < 0) {
            n = 2;
            printf("%d", sum);
            sum = 0;
        }
    }
    printf("\n");

    return 0;
}