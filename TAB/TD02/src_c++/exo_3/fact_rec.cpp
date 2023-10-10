#include <iostream>
#include <cstdlib>

using namespace std;

/*déclaration de la fonction fact récursive*/
int fact_r(const int n);

int main()
{
    int n, r;
    cout << "Donner un entier (>=0) : ";
    cin >> n;
    r = fact_r(n);
    cout << "n! = " << r << endl;
    return 0;
}

/* implementation de la fonction fact récursive*/
int fact_r(const int n) {
    if (n > 1) {
        return n * fact_r(n - 1);
    } else {
        return 1;
    }
}