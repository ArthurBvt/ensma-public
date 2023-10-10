namespace libdeque_obj{

    template <typename T>
    class Deque{
        public:
            Deque(void);
            ~Deque(void);
            long sizedeque(void) const;
        private:
            struct node_st
            {
                T data;
                struct node_st *prev;
                struct node_st *next;
            };

            long size;
            struct node_st *head;
            struct node_st *queue;
    };

}