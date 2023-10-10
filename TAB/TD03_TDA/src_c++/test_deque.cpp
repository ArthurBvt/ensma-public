#include "libdeque_cpp.cpp"
#include <iostream>

using namespace libdeque;

/* Procédure d'affichage d'un entier */
void printEntier(void *v)
{
    /* attention au "cast" et au déréférencement */
    std::cout << *((int *)v);
}

/* int main(int argc, char *argv[]) */
int main()
{
    int val1 = 4;
    int val2 = 44;
    t_deque deque;
    deque = init();
    insertHead(deque, &val1);
    insertHead(deque, &val2);
    printDeque(deque, printEntier);
    std::cout << "Remove Head... " <<*((int *)removeHead(deque))
        << std::endl;
    printDeque(deque, printEntier);
    deleteDeque(deque);
}