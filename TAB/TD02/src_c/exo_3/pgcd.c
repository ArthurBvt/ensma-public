#include <stdio.h>
#include <stdlib.h>

int pgcd(int a, int b);

int main (int argc, char *argv[]){
    int a = 0;
    int b = 0;
    if (argc == 3) {
        a = atoi(argv[1]);
        b = atoi(argv[2]);
    }

    printf("pgcd(%d, %d) = %d\n", a, b, pgcd(a, b));

    return 0;
}

int pgcd(int a, int b){
    if (a < b)
        return pgcd(a, b - a);
    else if (a > b)
        return pgcd(a - b, b);
    else
        return a;
}