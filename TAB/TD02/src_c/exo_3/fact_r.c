#include <stdio.h>
#include <stdlib.h>

int fact_r(const int n);

int main (int argc, char *argv[]){
    int n = 0;

    if (argc == 2) {
        n = atoi(argv[1]);
    }

    printf("%d\n", fact_r(n));

    return 0;
}

int fact_r(const int n){
    return (n > 1) ? n * fact_r(n - 1) : 1;
}