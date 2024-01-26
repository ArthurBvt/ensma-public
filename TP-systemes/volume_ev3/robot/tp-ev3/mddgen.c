#include <pthread.h>
#include <string.h>
#include <malloc.h>
#include "mddgen.h"

mdd_gen mdd_gen_init(size_t size) {
    mdd_gen m=(mdd_gen)malloc(sizeof(struct s_mdd_gen));
    m->val = malloc(size);
    m->size = size;
    m->dirty=0;
	pthread_mutex_init(&(m->mutex),0);
    return m;
}

void mdd_gen_write(mdd_gen mdd, void *val) {


    pthread_mutex_lock(&(mdd->mutex));
    memcpy(mdd->val, val, mdd->size);
	mdd->dirty=1;
	pthread_mutex_unlock(&(mdd->mutex));

}

int mdd_gen_read2(mdd_gen mdd, void *val) {
    int dirty;
	pthread_mutex_lock(&(mdd->mutex));
	memcpy(val, mdd->val, mdd->size);
	dirty=mdd->dirty;
	mdd->dirty=0;
	pthread_mutex_unlock(&(mdd->mutex));
	return dirty;
}