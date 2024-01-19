#ifndef BALINT_H_
#define BALINT_H_
#include <pthread.h>


typedef struct s_mdd_gen {
    void * val;
    size_t size;
    int dirty;
    pthread_mutex_t mutex;
} volatile *mdd_gen;

mdd_gen mdd_gen_init(size_t size);

void mdd_gen_write(mdd_gen mdd, void *val);

int mdd_gen_read2(mdd_gen mdd, void *val);

#endif