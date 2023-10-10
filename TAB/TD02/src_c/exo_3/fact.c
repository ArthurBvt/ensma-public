#include <stdio.h>
#include <stdlib.h>

void fact(const int n, int *res);

int main (int argc, char *argv[]){
    int n = 0;
    int r;
    if (argc == 2) {
        n = atoi(argv[1]);
    }

    fact(n, &r);

    printf("%d\n", r);

    return 0;
}

void fact(int n, int *res){
    *res = 1;
    while (n > 1) {
        *res *= n;
        n--;
    }
}