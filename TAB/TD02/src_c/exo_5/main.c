#include <stdio.h>
#include <stdlib.h>
#include <src_lib/libmatrice_c.h>

int main()
{
    T_matrice mat;
    mat = creationMatrice(10,10);

    printf("%ld\n", sizeof(mat));

    return 0;
}
