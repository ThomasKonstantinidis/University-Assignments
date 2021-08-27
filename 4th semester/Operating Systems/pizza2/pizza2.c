#include <stdio.h>
#include "p3160074-pizza2.h"
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>

pthread_mutex_t lock, STime, averageserv, Tdelpi, averagecool, Maxcool, del, Maxser, NP, order, Screen, Prep, oven;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

unsigned int OriginalSeed;
int Nmaker= Ncook;
int Ovens = Noven;
int Tmake = Tprep;
int TLOW = Tlow;
int THIGH = Thigh;
int TimeBake = Tbake;
int deliver = Ndeliverer;
int Pdeltime;
int NumOfPizza;
int Preperation;

double Maxservice, Maxcooling, Averageservice, Averagecooling, ServiceTime;




void* ORDER(void* x){

	struct timespec start, stop;
	int *id = (int* )x;
	int rc;
	
	
	
	//Clock starts for measuring the preperation time of the order.
	clock_gettime(CLOCK_REALTIME, &start);
	if( clock_gettime( CLOCK_REALTIME, &start) == -1 ) {
		printf("ERROR: clock gettime = -1");
		exit(-1);
	}
	
	
	
	//Number of pizza.
	rc = pthread_mutex_lock(&NP);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	NumOfPizza = (rand_r(&OriginalSeed) % Norderhigh) + Norderlow;
	rc = pthread_mutex_unlock(&NP);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	
	
	
	//Waiting for the next available pizza maker.
	rc = pthread_mutex_lock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	while(Nmaker == 0){
		rc = pthread_cond_wait(&cond, &lock);
		if (rc != 0) {	
			printf("ERROR: return code from pthread_cond_wait() is %d\n", rc);
			pthread_exit(&rc);
		}
	}
	
	Nmaker--;
	rc = pthread_mutex_unlock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	
	
	
	//Preperation time of pizza.
	rc = pthread_mutex_lock(&Prep);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Preperation = NumOfPizza * Tmake;
	rc = pthread_mutex_unlock(&Prep);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	sleep(Preperation);
	
	
	
	
	//Waiting for next available oven.
	rc = pthread_mutex_lock(&oven);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	while(Ovens == 0){
		rc = pthread_cond_wait(&cond, &oven);
		if (rc != 0) {	
			printf("ERROR: return code from pthread_cond_wait() is %d\n", rc);
			pthread_exit(&rc);
		}
	}
	
	Ovens--;
	rc = pthread_mutex_unlock(&oven);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	

	
	//Maker get free.
	rc = pthread_mutex_lock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Nmaker++;
	rc = pthread_cond_signal(&cond);
	if (rc != 0) {	
		printf("ERROR: return code from pthread_cond_signal() is %d\n", rc);
		pthread_exit(&rc);
	}

	rc = pthread_mutex_unlock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	
	//Pizza baking.
	sleep(TimeBake);
	
	
	
	//Waiting for next available deliverer.
	rc = pthread_mutex_lock(&del);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	while(deliver == 0){
		rc = pthread_cond_wait(&cond, &del);
		if (rc != 0) {	
			printf("ERROR: return code from pthread_cond_wait() is %d\n", rc);
			pthread_exit(&rc);
		}
	}
	
	deliver--;
	rc = pthread_mutex_unlock(&del);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	
	
	
	//Oven get free.
	rc = pthread_mutex_lock(&oven);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Ovens++;
	rc = pthread_cond_signal(&cond);
	if (rc != 0) {	
		printf("ERROR: return code from pthread_cond_signal() is %d\n", rc);
		pthread_exit(&rc);
	}
	rc = pthread_mutex_unlock(&oven);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	
	
	
	//Pizza delivery time.
	rc = pthread_mutex_lock(&Tdelpi);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Pdeltime = (rand_r(&OriginalSeed) % THIGH) + TLOW;
	rc = pthread_mutex_unlock(&Tdelpi);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	




	//Get the max cooling time.
	rc = pthread_mutex_lock(&Maxcool);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	if (Pdeltime > Maxcooling){
		Maxcooling = Pdeltime;
	}
	rc = pthread_mutex_unlock(&Maxcool);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}



	
	//Delivery time.
	sleep(Pdeltime);
	




	//Clock stop about measuring the preperation time of the order.
	clock_gettime(CLOCK_REALTIME, &stop);
	if( clock_gettime( CLOCK_REALTIME, &stop) == -1 ) {
		printf("ERROR: clock gettime = -1");
		exit(-1);
	}




	//Order preperation time
	rc = pthread_mutex_lock(&STime);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	ServiceTime = stop.tv_sec - start.tv_sec;
	rc = pthread_mutex_unlock(&STime);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	



	//Get the max service time.
	rc = pthread_mutex_lock(&Maxser);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	if (ServiceTime > Maxservice){
		Maxservice = ServiceTime;
	}
	rc = pthread_mutex_unlock(&Maxser);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}





	//Display of order text at the time of delivery.
	rc = pthread_mutex_lock(&Screen);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	
	printf("The order with ID: %d,", *id);
	printf(" was ordered in %.1f", ServiceTime);
	printf(" minutes and cooled for %d", Pdeltime);
	printf(" minutes.\n");
	
	rc = pthread_mutex_unlock(&Screen);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	

	
	

	//Return time of distributor.
	sleep(Pdeltime);
	

	
	
	
	//deliverer get free.
	rc = pthread_mutex_lock(&del);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	deliver++;
	rc = pthread_cond_signal(&cond);
	if (rc != 0) {	
		printf("ERROR: return code from pthread_cond_signal() is %d\n", rc);
		pthread_exit(&rc);
	}
	rc = pthread_mutex_unlock(&del);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	

	
	
	//Get the sum of Service Times. Then, in main divide to find average.
	rc = pthread_mutex_lock(&averageserv);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Averageservice += ServiceTime;
	rc = pthread_mutex_unlock(&averageserv);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	
	//Get the sum of Cooling Times. Then, in main divide to find average.
	rc = pthread_mutex_lock(&averagecool);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Averagecooling += Pdeltime;
	rc = pthread_mutex_unlock(&averagecool);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	//Exit.
	pthread_exit(id);
}




int main(int argc, char** argv) {
	
	if (argc != 3) {
		printf("ERROR: the program should take two arguments, the number of customers and the original seed!\n");
		exit(-1);
	}

	int Numofcustomers = atoi(argv[1]);
	unsigned int OriginalSeed = atoi(argv[2]);
	
	if (Numofcustomers < 0 || OriginalSeed < 0) {
		printf("ERROR: the number of customers and the Original Seed should be positive numbers. Current numbers given %d.\n", Numofcustomers);
		exit(-1);
	}	
	
	
	srand(OriginalSeed);
	pthread_t *threads;
	threads = malloc (Numofcustomers * sizeof(pthread_t));
	if (threads == NULL) {
		printf("NOT ENOUGH MEMORY!\n");
		return -1;
	}
	
	int rc;
	int threadid;
	int Next;
	int countArray[Numofcustomers];
	pthread_mutex_init(&lock, NULL);
	pthread_mutex_init(&order, NULL);
	pthread_mutex_init(&averageserv, NULL);
	pthread_mutex_init(&Prep, NULL);
	pthread_mutex_init(&oven, NULL);
	pthread_mutex_init(&STime, NULL);
	pthread_mutex_init(&Maxser, NULL);
	pthread_mutex_init(&NP, NULL);
	pthread_mutex_init(&Tdelpi, NULL);
	pthread_mutex_init(&averagecool, NULL);
	pthread_mutex_init(&Maxcool, NULL);
	pthread_mutex_init(&del, NULL);
	pthread_mutex_init(&Screen, NULL);
	
	for(threadid = 0; threadid < Numofcustomers; threadid++){
		countArray[threadid] = threadid + 1;
		if (threadid != 0){
			Next = (rand_r(&OriginalSeed) % Torderhigh) + Torderlow;
			sleep(Next);
		}
		rc = pthread_create(&threads[threadid], NULL, ORDER, &countArray[threadid]);
		if(rc != 0){
			printf("ERROR : return code from pthread_create() is %d\n", rc);
			exit(-1);
		}
	}
	
	void* status;
	for(threadid = 0; threadid < Numofcustomers; threadid++){
		rc = pthread_join(threads[threadid], &status);
		
		if(rc != 0){
			printf("ERROR: return code from pthread_join() is %d\n", rc);
			exit(-1);
		}
	}
	
	
	Averageservice = Averageservice/Numofcustomers;
	Averagecooling = Averagecooling/Numofcustomers;

	
	printf("Average delivery time: %.3f", Averageservice);
	printf(" minutes.\n");
	printf("Max delivery time: %.1f", Maxservice);
	printf(" minutes.\n");
	
	printf("Average cooling time: %.3f", Averagecooling);
	printf(" minutes.\n");
	printf("Max cooling time: %.1f", Maxcooling);
	printf(" minutes.\n");
	
	
	pthread_mutex_destroy(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&order);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&averageserv);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&Prep);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&oven);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&STime);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&Maxser);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&NP);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&Tdelpi);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&averagecool);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&Maxcool);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&del);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&Screen);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	
	
	
	
	pthread_cond_destroy(&cond);
	if (rc != 0) {
		printf("ERROR: return code from pthread_cond_destroy() is %d\n", rc);
		exit(-1);		
	}

	free(threads);
	return 0;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
