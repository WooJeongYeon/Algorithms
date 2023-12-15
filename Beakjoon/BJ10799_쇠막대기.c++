#include <stdio.h>

int main() {
    int num = 0, sum = 0, i = 1;
    char str[100001];

    scanf("%s", str);

    num = 1;
    
    while(str[i] != '\0') {
        if(str[i - 1] == '(' && str[i] == ')') {
            sum += --num;
        } else if(str[i] == '(') {
            num++;
        } else {
            num--;
            sum++;
        }

        i++;
    }

    printf("%d\n", sum);

    return 0;
}