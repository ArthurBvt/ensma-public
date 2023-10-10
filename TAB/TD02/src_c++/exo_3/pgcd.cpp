#include <iostream>
#include <cstdlib>

using namespace std;

int pgcd(const int a, const int b);

int main()
{
    int a, b;

    cout << "Donner a puis b : ";
    cin >> a >> b;
    cout << "PGCD(a,b) = " << pgcd(a, b) << endl;
    return 0;
}

int pgcd(const int a, const int b) {
    if (a == b) {
        return a;
    } else if (a > b) {
        return pgcd(b, a - b);
    } else {
        return pgcd(a, b - a);
    }
}