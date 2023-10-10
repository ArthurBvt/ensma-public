#include <stdio.h>
#include <stdlib.h>

int * inter(int * tab_1, int * tab_2, int m, int n);

int main (int argc, char *argv[]){
    int n_1 = atoi(argv[1]);
    int * tab_1 = malloc(sizeof(int) * n_1);
    int n_2 = atoi(argv[n_1 + 2]);
    int * tab_2 = malloc(sizeof(int) * n_2);
    int * tab_3 = malloc(sizeof(int) * (n_1 + n_2));

    int i;

    if (argc < 1)
        return -1;

    for (i = 0; i < n_1; i++){
        tab_1[i] = atoi(argv[i + 2]);
    }

    for (i = 0; i < n_2; i++){
        tab_2[i] = atoi(argv[i + n_1 + 3]);
    }

    tab_3 = inter(tab_1, tab_2, n_1, n_2);

    /* === */

    printf("tab_1[%d] = ", n_1);

    for (i = 0; i < n_1; i++)
        printf("%d, ", tab_1[i]);

    printf("\ntab_2[%d] = ", n_2);

    for (i = 0; i < n_2; i++)
        printf("%d, ", tab_2[i]);

    printf("\ntab_3[%d] = ", n_1 + n_2);

    for (i = 0; i < n_1 + n_2; i++)
        printf("%d, ", tab_3[i]);

    printf("\n");

    return 0;
}

int * inter(int * tab_1, int * tab_2, int m, int n){
    int * tab_3 = malloc(sizeof(int) * (m + n));

    int i = 0;
    int j = 0;

    while ((i < m) && (j < n)){
        if (tab_1[i] <= tab_2[j]){
            tab_3[i + j] = tab_1[i];
            i++;
        } else {
            tab_3[i + j] = tab_2[j];
            j++;
        }
    }

    while (i < m) {
        tab_3[i + j] = tab_1[i];
        i++;
    }

    while (j < n) {
        tab_3[i + j] = tab_2[j];
        j++;
    }

    return(tab_3);
}