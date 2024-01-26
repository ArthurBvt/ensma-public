/*
 * BALD.h
 *
 *  Created on: 30 oct. 2023
 *      Author: Noah
 */
#include <pthread.h>
#ifndef BALD_H_int
#define BALD_H_int

typedef struct s_BALD_int {
	int val;
	int dirty;
    size_t size;
	pthread_mutex_t mutex;
	pthread_cond_t c_bal; /* sert à bloquer/réveiller une tâche en attente de message */
} * BALD_int;
/**
 * Initializes a shared int
 *\param val[in] initial value of the shared data
 *\return the new shared int, memory allocation is done inside the function
 */
BALD_int BALD_int_init(const int val);

/**
 * Writes in a shared int
 *\param BALD[in,out] the shared data
 *\param val[in] value to write in the shared data
 * Side effect: shared int is considered dirty until next reading
 */
void BALD_int_write(BALD_int BALD,const int val);
/**
 * Reads in a shared int, while returning if it was dirty
 *\param BALD[in] the shared data
 *\param val[out] value of the shared data
 *\return  1 if the shared data was modified since last reading (i.e., dirty), 0 else
 * Side effect: shared int is now considered clean
 */
int BALD_int_read2(BALD_int BALD,int *val);
#endif /* BALD_H_ */