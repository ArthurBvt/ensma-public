#ifndef LIBMATRICE_C_H_
#define LIBMATRICE_C_H_

typedef struct matrice * T_matrice;

T_matrice creationMatrice(const int n, const int m);

void saisieMatrice(const T_matrice mat);

void afficheMatrice(const T_matrice mat);

void detruireMatrice(T_matrice * mat);

T_matrice sommeMat(const T_matrice m1, const T_matrice m2);

char * matriceToString(const T_matrice mat);

#endif