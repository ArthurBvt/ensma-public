#include "libdeque_obj_cpp.hpp"
#include <iostream>

using namespace libdeque_obj;

namespace libdeque_obj {

    template <typename T>
    Deque<T>::Deque(void) {
        size = 0;
        head = nullptr;
        queue = nullptr;
        std::cout << "fin du constructeur" << std::endl;
    }

    template <typename T>
    Deque<T>::~Deque(void) {
        std::cout << "C'est ciao la deque" << std::endl;
    }

    template <typename T>
    long Deque<T>::sizedeque(void) const {
        return size;
    }
}