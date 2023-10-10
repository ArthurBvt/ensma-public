#include "libdeque_cpp.hpp"
#include <stdexcept>
#include <iostream>

namespace libdeque
{
    struct node_st
    {
        void *data;
        struct node_st *prev;
        struct node_st *next;
    };

    struct deque_st
    {
        long size;
        struct node_st *head;
        struct node_st *queue;
    };

    t_deque init() noexcept(false)
    {
        t_deque dq = nullptr;
        try
        {
            dq = new struct deque_st;
            dq->size = 0;
            dq->head = nullptr;
            dq->queue = nullptr;
        }
        catch (const std::bad_alloc &ba)
        {
            std::cerr << ba.what() << '\n';
            throw dq;
        }
        return dq;
    }

    int sizeDeque(const t_deque dq) noexcept(false)
    {
        if (dq == nullptr) {
            throw std::invalid_argument ("Matrice mal allouée");
        }
        return dq->size;
    }

    void insertHead(t_deque dq, const void *el) noexcept(false)
    {
        struct node_st *cell;
        if ((dq == nullptr) || (el == nullptr))
        {
            throw std::invalid_argument("Matrice ou element invalide");
        }
        try
        {
            cell = new struct node_st;
            cell->data = (void *)el;
            cell->prev = nullptr;
            cell->next = dq->head;
            if (dq->head == nullptr)
            {
                dq->head = cell;
                dq->queue = cell;
            }
            else
            {
                dq->head->prev = cell;
                dq->head = cell;
            }
            dq->size++;
        }
        catch (const std::bad_alloc &ba)
        {
            std::cerr << ba.what() << '\n';
            throw dq;
        }
    }

    void printDeque(const t_deque dq, void (*printElem)(void *)) noexcept(false)
    {
        struct node_st *iter;
        if (dq == nullptr)
        {
            throw std::invalid_argument("Matrice invalide");
        }
        std::cout << "(size = " << dq->size <<")H->";
        if (dq->head != nullptr)
        {
            iter = dq->head;
            do
            {
                printElem(iter->data);
                iter = iter->next;
                if (iter != nullptr)
                {
                    printf("-");
                };
            } while (iter != nullptr);
        }
        printf("<-Q\n");
    }

    void *removeHead(t_deque dq) noexcept(false)
    {
        struct node_st *cell;
        void *val;
        if ((dq == nullptr))
        {
            throw std::invalid_argument("Deque invalide");
        }
        if ((dq->size == 0))
        {
            throw std::invalid_argument("Deque vide");
        }
        cell = dq->head;
        val = cell->data;
        dq->head = cell->next;
        if (dq->head != nullptr)
        {
            dq->head->prev = nullptr;
        }
        else
        {
            dq->queue = nullptr;
        }
        delete(cell);
        dq->size--;
        return val;
    }

    void deleteDeque(t_deque &dq) noexcept(false)
    {
        if ((dq == nullptr))
        {
            throw std::invalid_argument("Deque invalide");
        }
        if (dq->head != NULL)
        {
            while (dq->queue != NULL)
            {
                removeHead(dq);
            }
        }
        delete(dq);
        /* Mettre le pointeur libéré à NULL pour éviter les ennuis ...*/
        dq = NULL;
    }
}