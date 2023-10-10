#include <iostream>
#include <cstdlib>

using namespace std;

void afficheTab(const int t[], const int n);
void interclassement(const int t1[], const int m, const int t2[], const int n,
                     int t3[]);

int main(int argc, char *argv[]) {

    int t1[10];
    int t2[10];
    int t3[20];

    int i, m, n;
    if (argc < 2) {
        cout << "Paramètre(s) manquant(s)" << endl;
        return -1;
    }
    m = atoi(argv[1]);
    n = atoi(argv[1 + m + 1]);
    for (i = 0; i < m; i++) {
        t1[i] = atoi(argv[2 + i]);
    }
    for (i = 0; i < n; i++) {
        t2[i] = atoi(argv[2 + m + 1 + i]);
    }
    cout << "T1 : ";
    afficheTab(t1, m);
    cout << "T2 : ";
    afficheTab(t2, n);
    interclassement(t1, m, t2, n, t3);
    cout << "T3 : ";
    afficheTab(t3, n + m);
    return 0;
}

void afficheTab(const int t[], const int n) {
    int i;
    for (i = 0; i < n - 1; i++) {
        cout << t[i] << "\t";
    }
    cout << t[n - 1] << endl;
}


void interclassement(const int t1[], const int m, const int t2[], const int n,
                     int t3[]) {
    int i = 0;
    int j = 0;
    while (i < m && j < n) {
        if (t1[i] < t2[j]) {
        t3[i + j] = t1[i];
        i++;
        } else {
        t3[i + j] = t2[j];
        j++;
        }
    }
    if (i == m) {
        for (; j < n; j++) {
           t3[i + j] = t2[j];
        }
    } else {
        for (; i < n; i++) {
            t3[i + j] = t1[i];
        }
    }
}