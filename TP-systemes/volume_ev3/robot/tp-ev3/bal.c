/*
 * BALD.c
 *
 *  Created on: 25 janv. 2017
 *      Author: grolleau
 */
#include <pthread.h>
#include <string.h>
#include <malloc.h>
#include "bal.h"

/**
 * Initializes a shared int
 *\param val[in] initial value of the shared data
 *\return the new shared int, memory allocation is done inside the function
 */
BALD_int BALD_int_init(const int val) {
	BALD_int m=(BALD_int)malloc(sizeof(struct s_BALD_int));
    m->val=val;
	m->dirty=0;
	pthread_mutex_init(&(m->mutex),0);
	pthread_cond_init(&(m->c_bal), 0);
	return m;
}

/**
 * Writes in a shared int
 *\param BALD[in,out] the shared data
 *\param val[in] value to write in the shared data
 */
void BALD_int_write(BALD_int BALD, const int val) {
	pthread_mutex_lock(&(BALD->mutex));
    BALD->val=val;
    BALD->dirty=1;
	pthread_cond_broadcast(&(BALD->c_bal));
	pthread_mutex_unlock(&(BALD->mutex));
}

/**
 * Reads in a shared int, while returning if it was dirty
 *\param BALD[in] the shared data
 *\param val[out] value of the shared data
 *\return  1 if the shared data was modified since last reading (i.e., dirty), 0 else
 */
int BALD_int_read2(BALD_int BALD,int *val) {
	int dirty;
	pthread_mutex_lock(&(BALD->mutex));
	dirty=BALD->dirty;
    if (!dirty) {
        *val=BALD->val;
    }
    else {
        while (1) {pthread_cond_wait(&(BALD->c_bal),&(BALD->mutex));}
    }
	BALD->dirty=0;
	pthread_mutex_unlock(&(BALD->mutex));
	return dirty;

}