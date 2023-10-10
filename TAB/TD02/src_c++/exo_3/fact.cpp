#include <iostream>
#include <cstdlib>

using namespace std;

// déclaration de la procédure fact
// Utilisation d'une référence pour passage param
void fact(const int n, int &res);

int main(int argc, char const *argv[])
{
    int n;
    int r;
    /* récupération des paramètres d'appel du programme */
    if ((argc < 2) || (argc >= 3)) {
        cout << "Nb paramètre(s) invalide";
        return -1;
    }
    n = atoi(argv[1]);
    /* appel de la procédure fact */
    fact(n,r);
    cout << n << "! = " << r << endl;
    return 0;
}

void fact(const int n, int &res)
{
    int i;
    res = 1;
    for (i = 1; i <= n; i++) {
        res = res * i;
    }
}