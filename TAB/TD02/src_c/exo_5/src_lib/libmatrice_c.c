#include "libmatrice_c.h"
#include <errno.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct matrice {
    int n;
    int m;
    float ** matrice;
};

T_matrice creationMatrice(const int n, const int m) {
    T_matrice mat = (T_matrice) malloc(sizeof(struct matrice));
    int i;
    int errno_0 = errno;
    errno = 0;

    mat->n = n;
    mat->m = m;
    mat->matrice = (float **) calloc(n, sizeof(float *));

    for (i = 0; i < n; i++){
        mat->matrice[i] = (float *) calloc(m, sizeof(float));
    }

    printf((errno) ? "erreur creation matrice\n" : "matrice creee\n");
    errno = errno_0;

    return mat;
}

void detruireMatrice(T_matrice * mat) {

}