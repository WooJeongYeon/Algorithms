#include <stdio.h>
#define SIZE 100000

int getLen(char* str) {
    int len;
    for(len = 0; str[len] != '\0' ; len++) {
    }
    return len;
}

bool check(char* str) {
    int len = getLen(str);
    int cnt = len / 2;
    for(int i = 0 ; i < cnt ; i++) {
        if(str[i] != str[len - i - 1]) {
            return false;
        }
    }
    return true;
}

int main() {
    char str[SIZE];
    while(true) {
        scanf("%s", str);
        if(str[0] == '0') {
            break;
        }
        
        if(check(str)) {
            printf("yes\n");
        } else {
            printf("no\n");
        }
    }
}