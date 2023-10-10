namespace libdeque
{
    typedef struct deque_st *t_deque;

    t_deque init()
        noexcept(false);
    int sizeDeque(const t_deque dq)
        noexcept(false);
    void insertHead(t_deque dq, const void *el)
        noexcept(false);
    void printDeque(const t_deque dq, void (*printElem)(void *))
        noexcept(false);
    void *removeHead(t_deque dq) noexcept(false);

    void deleteDeque(t_deque &dq) noexcept(false);
}