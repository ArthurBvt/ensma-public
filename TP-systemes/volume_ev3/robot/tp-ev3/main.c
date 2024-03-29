/*
 \file		deadreck.c
 \author	${user}
 \date		${date}
 \brief		Simple Hello World! for the Ev3
 */
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#include <unistd.h>
#include <ev3.h>
#include <ev3_tacho.h>
#include <ev3_port.h>
#include <ev3_sensor.h>
#include <pthread.h>
#include <time.h>
#include "time_util.h"
#include "mdd.h"
#include "communication.h"
#include "myev3.h"
#include "workers.h"
#include "bal.h"



// Shared data and mailboxes
volatile MDD_int MDD_quit;
volatile MDD_int MDD_status;
volatile MDD_int MDD_auto_command;
volatile MDD_int MDD_power;
volatile BALD_int BAL_direct_command;
volatile mdd_gen mdd_reset;
volatile mdd_gen mdd_position;
volatile mdd_gen mdd_target;
// TODO: declare the rest

typedef struct
{
	int x;
	int y;
	int deg;
}reset;

typedef struct
{
	double x;
	double y;
	double rad;
}position;

typedef struct
{
	int x;
	int y;
}target;





void init_comms() {
	MDD_quit = MDD_int_init(0);
	MDD_auto_command = MDD_int_init(0);
	MDD_status = MDD_int_init(STATUS_STANDBY);
	MDD_power = MDD_int_init(0);
	BAL_direct_command = BALD_int_init(CMD_STOP);
	mdd_reset = mdd_gen_init(sizeof(reset));
	mdd_position = mdd_gen_init(sizeof(position));
	mdd_target = mdd_gen_init(sizeof(target));

	// TODO: initialize the rest
}

/**
 * Thread sending information to the ground station
 * It should read the status, and, if modified, fprintf it on outStream
 * It should read the position and angle, and fprintf this information on outStream
 * Protocol format from robot to ground station:
 * Status: s status\n
 * Position: p x y a\n where x, y and a are integers!
 * Do not forget to fflush outStream at the end of each iteration, or data will be buffered but not sent
 * This thread should end when the application quits, and fclose the outStream socket
 */
void *sendThread(FILE * outStream) {
	int x, y, a;
	int status;
	struct timespec horloge;
	int dirty_pos;
	int dirty_status;
	position * pos;

	clock_gettime(CLOCK_REALTIME, &horloge);
	while (!MDD_int_read(MDD_quit)) {
		// TODO : complete this
		dirty_pos = mdd_gen_read2(mdd_position, pos);
		if (dirty_pos) {
			x = (int) pos->x;
			fprintf(outStream, x);
			y = (int) pos->y;
			fprintf(outStream, y);
			a = (int) pos->rad;
			fprintf(outStream, a);
		}
		dirty_status = mdd_int_read2(MDD_status, status);
		if (dirty_status) {
			fprintf(outStream, status);
		}

		fflush(outStream);
		add_ms(&horloge, 200);
		clock_nanosleep(CLOCK_REALTIME, TIMER_ABSTIME, &horloge, 0);
	}
	fclose(outStream);
	return 0;
}

/**
 * Thread commanding the robot in direct mode, it should wait everytime for a new command
 * As long as the application does not quit, this thread should apply the
 * received direct commands, using the current power, while updating the status
 * of the application (STATUS_STANDBY, STATUS_DIRECT_MOVE)
 * ev3dev-c functions that will be useful are:
 * set_tacho_command_inx (for commands TACHO_STOP and TACHO_RUN_DIRECT),
 * set_tacho_duty_cycle_sp in order to configure the power between -100 and 100
 */
void *directThread(void*dummy) {
	// TODO : this is a bit long of a switch/case structure but it's fun
	int commande;
	while (!MDD_int_read(MDD_quit)) {
		BALD_int_read2(BAL_direct_command, &commande);
		switch (commande)
		{
		case CMD_STOP:
			/* code */
			break;
		case CMD_FORWARD:
			/* code */
			break;
		case CMD_BACKWARD:
			/* code */
			break;
		case CMD_RIGHT:
			/* code */
			break;
		case CMD_LEFT:
			/* code */
			break;
		default:
			break;
		}
	}
	return 0;
}

/**
 * deadrecknoning thread, should first initialize (see deadRWorkerInit)
 * and then periodically, until quit:
 * if reset has been required, reset the coords
 * call its worker (see deadRWorker)
 * and then update the shared variable of the coords
 * Note: careful, the deadRWorker assumes angles in radian, while
 * ground station assumes angles in degrees (reset, and data sent)
 */
void * deadreckoningThread(void *dummy) {
	// TODO : all by yourself
	return 0;
}

/**
 * auto move thread
 * when a target is defined, sets its mode to running (and updates the MDD status),
 * until STOP is received, or quit is received, or the target is reached with an acceptable error.
 */
void * autoThread(void *dummy) {
	// TODO : keep this as the bonus question, at the end
	return 0;
}

/**
 * The main function will be used as one of the application thread: readGroundstationThread
 * It will be in charge of initializing the ev3: ev3_init ev3_port_init ev3_tacho_init ev3_sensor_init
 * then initializing our specific ev3 ports: my_init_ev3
 * Then it will initialize every shared data and mailbox,
 * Then it will wait for ground station connection:  WaitClient
 * Then it will create the threads
 * Then it will read the ground station orders using fscanf on the inStream
 * Protocol:
 * q\n -> quit
 * p pow\n -> current power to be set to pow
 * r x y alpha\n -> current position and heading should be reset to (x,y,alpha)
 * m 0|1\n -> set the mode to MODE_DIRECT (0) or MODE_AUTO (1)
 * S\n -> stop
 * F\n -> direct Forward
 * B\n -> direct Backward
 * L\n -> direct Left
 * R\n -> direct Right
 * g x y\n -> auto goto (x,y)
 * Note : when fscanf returns EOF, this means the ground station closed the connection, and
 * the application should stop.
 * Before exiting, we should send the quitting information to the other threads,
 * in order to cleanup (stop engines, close sockets, etc.)
 * Cleanup made in this thread, after making sure that every other thread is finished (pthread_join):
 * Every motor should be stopped, inStream should be closed
 */
int main(void) {
	char cmd;
	char buf[256];
	int mode = MODE_DIRECT;
	FILE *inStream, *outStream;
	ev3_init();
	ev3_port_init();
	ev3_tacho_init();
	ev3_sensor_init();
	if (my_init_ev3()) {
		return 1;
	}
	init_comms();
	printf("Ready and waiting for incoming connection...\n");
	if (WaitClient(&outStream, &inStream)) {
		return 1;
	}
	// TODO: run the threads
	int quit = 0;
	while (!quit) {
		if (fgets(buf,256,inStream)) {
			cmd = buf[0];
			switch (cmd) {
			// TODO: add every command treatment, think about using sscanf on buf to extract arguments
			default:
				printf("Unrecognized command: %s\n", buf);
			}
		} else {
			// Connection closed
			quit = 1;
		}
	}
	MDD_int_write(MDD_quit, 1);
	// TODO: wait for threads termination (pthread_join)
	fclose(inStream);
	set_tacho_command_inx(MY_RIGHT_TACHO, TACHO_STOP);
	set_tacho_command_inx(MY_LEFT_TACHO, TACHO_STOP);
	ev3_uninit();
	CloseSockets();
	return 0;
}
