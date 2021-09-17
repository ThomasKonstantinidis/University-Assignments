#include <stdio.h>
#include "p3160074-res2.h"
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <time.h>

pthread_mutex_t lock, lockca, trapeza, trans, total, averageserv, averagew, plano, sid, r90, tseat, cseat, screen, Pzn, f, s, th;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
unsigned int OriginalSeed;
int Ntil = Ntel;
int Nca = Ncash;
int last=0;
int Bank = 0;
int first=0;
int second=0;
int third=0;
int transactions = 0;
int totalseats[25] = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
double Averagewait, Averageservice;
int Seatable[25][10] = {{0}};


void* Pelatis(void* x){

	int Numoftickets;
	int Tseatime;
	int Ctime;
	int thes;
	int flag = 0;
	char zone;
	int whereseat;
	int Possib;
	int PsuccZone;
	int cost=0;
	struct timespec start, stop;
	int *id = (int* )x;
	int rc;

	rc = pthread_mutex_lock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	while(Ntil == 0){
		clock_gettime(CLOCK_REALTIME, &start);
		if( clock_gettime( CLOCK_REALTIME, &start) == -1 ) {
			printf("ERROR: clock gettime = -1");
			exit(-1);
		}
		rc = pthread_cond_wait(&cond, &lock);
		if (rc != 0) {	
			printf("ERROR: return code from pthread_cond_wait() is %d\n", rc);
			pthread_exit(&rc);
		}
	}
	
	clock_gettime(CLOCK_REALTIME, &stop); //waiting time
	if( clock_gettime( CLOCK_REALTIME, &stop) == -1 ) {
		printf("ERROR: clock gettime = -1");
		exit(-1);
	}
	Ntil--;
	rc = pthread_mutex_unlock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	rc = pthread_mutex_lock(&averagew);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Averagewait = Averagewait + (stop.tv_sec - start.tv_sec);
	rc = pthread_mutex_unlock(&averagew);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	clock_gettime(CLOCK_REALTIME, &start);
	if( clock_gettime( CLOCK_REALTIME, &start) == -1 ) {
		printf("ERROR: clock gettime = -1");
		exit(-1);
	}
	rc = pthread_mutex_lock(&Pzn);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	PsuccZone = rand_r(&OriginalSeed) %100;	
	rc = pthread_mutex_unlock(&Pzn);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	rc = pthread_mutex_lock(&sid);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Numoftickets = (rand_r(&OriginalSeed) % Nseathigh) + Nseatlow;
	rc = pthread_mutex_unlock(&sid);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	rc = pthread_mutex_lock(&tseat);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Tseatime = (rand_r(&OriginalSeed) % Tseathigh) + Tseatlow;
	rc = pthread_mutex_unlock(&tseat);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	sleep(Tseatime); //douleia me tilefoniti

	if(PsuccZone < PzoneA*100){
		zone = 'A';
	}else if(PsuccZone < PzoneB*100 + 20){
		zone = 'B';
	}else{
		zone = 'C';
	}
	if(zone == 'A'){
		whereseat = 0;
		for(int i=0; i<5; i++){
			if(totalseats[i] >= Numoftickets){
				whereseat = i;
				rc = pthread_mutex_lock(&total);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
					exit(-1);		
				}
				totalseats[i] = totalseats[i] - Numoftickets;
				i=5;
				rc = pthread_mutex_unlock(&total);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
					exit(-1);		
				}
			}
		}
		if(whereseat == 0){
			rc = pthread_mutex_lock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			printf("Customer: %d. There aren't enough available tickets\n", *id);
			rc = pthread_mutex_lock(&s);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			second++;
			rc = pthread_mutex_unlock(&s);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			rc = pthread_mutex_unlock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			flag = 1;
		}else{
			for(int j=0; j<Numoftickets; j++){
				rc = pthread_mutex_lock(&plano);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
					exit(-1);		
				}
				Seatable[whereseat][totalseats[whereseat] + j] = *id;
				rc = pthread_mutex_unlock(&plano);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
					exit(-1);		
				}
				cost = CzoneA * Numoftickets;
			}
		}
	}else if(zone == 'B'){
		whereseat = 0;
		for(int i=5; i<15; i++){
			if(totalseats[i] >= Numoftickets){
				whereseat = i;
				rc = pthread_mutex_lock(&total);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
					exit(-1);		
				}
				totalseats[i] = totalseats[i] - Numoftickets;
				i=15;
				rc = pthread_mutex_unlock(&total);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
					exit(-1);		
				}
			}
		}
		if(whereseat == 0){
			rc = pthread_mutex_lock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			printf("Customer: %d. There aren't enough available tickets\n", *id);
			rc = pthread_mutex_lock(&s);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			second++;
			rc = pthread_mutex_unlock(&s);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			rc = pthread_mutex_unlock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			flag = 1;
		}else{
			for(int j=0; j<Numoftickets; j++){
				rc = pthread_mutex_lock(&plano);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
					exit(-1);		
				}
				Seatable[whereseat][totalseats[whereseat] + j] = *id;
				rc = pthread_mutex_unlock(&plano);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
					exit(-1);		
				}
				cost = CzoneB * Numoftickets;
			}
		}
	}else if(zone == 'C'){
		whereseat = 0;
		for(int i=15; i<25; i++){
			if(totalseats[i] >= Numoftickets){
				whereseat = i;
				rc = pthread_mutex_lock(&total);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
					exit(-1);		
				}
				totalseats[i] = totalseats[i] - Numoftickets;
				i=25;
				rc = pthread_mutex_unlock(&total);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
					exit(-1);		
				}
			}
		}
		if(whereseat == 0){
			rc = pthread_mutex_lock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			printf("Customer: %d. There aren't enough available tickets\n", *id);
			rc = pthread_mutex_lock(&s);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			second++;
			rc = pthread_mutex_unlock(&s);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			rc = pthread_mutex_unlock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			flag = 1;
		}else{
			for(int j=0; j<Numoftickets; j++){
				rc = pthread_mutex_lock(&plano);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
					exit(-1);		
				}
				Seatable[whereseat][totalseats[whereseat] + j] = *id;
				rc = pthread_mutex_unlock(&plano);
				if (rc != 0) {
					printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
					exit(-1);		
				}
				cost = CzoneC * Numoftickets;
			}
		}
	}

	rc = pthread_mutex_lock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Ntil++;
	rc = pthread_mutex_unlock(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	clock_gettime(CLOCK_REALTIME, &stop);
	if( clock_gettime( CLOCK_REALTIME, &stop) == -1 ) {
		printf("ERROR: clock gettime = -1");
		exit(-1);
	}
	rc = pthread_mutex_lock(&averageserv);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
		exit(-1);		
	}
	Averageservice = Averageservice + (stop.tv_sec - start.tv_sec);
	rc = pthread_mutex_unlock(&averageserv);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
		exit(-1);		
	}
	
	if(flag == 0){		
		rc = pthread_mutex_lock(&lockca);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
			exit(-1);		
		}
		while(Nca == 0){
			clock_gettime(CLOCK_REALTIME, &start);
			if( clock_gettime( CLOCK_REALTIME, &start) == -1 ) {
				printf("ERROR: clock gettime = -1");
				exit(-1);
			}
			rc = pthread_cond_wait(&cond, &lockca);
			if (rc != 0) {	
				printf("ERROR: return code from pthread_cond_wait() is %d\n", rc);
				pthread_exit(&rc);
			}
		}

		clock_gettime(CLOCK_REALTIME, &stop); //xronos anamonis
		if( clock_gettime( CLOCK_REALTIME, &stop) == -1 ) {
			printf("ERROR: clock gettime = -1");
			exit(-1);
		}

		Nca--;
		rc = pthread_mutex_unlock(&lockca);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
			exit(-1);		
		}
		rc = pthread_mutex_lock(&averagew);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
			exit(-1);		
		}
		Averagewait = Averagewait + (stop.tv_sec - start.tv_sec);
		rc = pthread_mutex_unlock(&averagew);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
			exit(-1);		
		}


		clock_gettime(CLOCK_REALTIME, &start);
		if( clock_gettime( CLOCK_REALTIME, &start) == -1 ) {
			printf("ERROR: clock gettime = -1");
			exit(-1);
		}

		rc = pthread_mutex_lock(&cseat);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
			exit(-1);		
		}
		Ctime = (rand_r(&OriginalSeed) % Tcashhigh) + Tcashlow;
		rc = pthread_mutex_unlock(&cseat);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
			exit(-1);		
		}
		sleep(Ctime); //douleia me tameia

		rc = pthread_mutex_lock(&r90);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
			exit(-1);		
		}
		Possib = rand_r(&OriginalSeed) %100;

		rc = pthread_mutex_unlock(&r90);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
			exit(-1);		
		}

		if(Possib < Pcardsucces){
			rc = pthread_mutex_lock(&f);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			first++;
			rc = pthread_mutex_unlock(&f);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			rc = pthread_mutex_lock(&trans);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			transactions++;
			rc = pthread_mutex_unlock(&trans);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}	
				
			rc = pthread_mutex_lock(&trapeza);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			Bank = Bank + cost;
			rc = pthread_mutex_unlock(&trapeza);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
				
			rc = pthread_mutex_lock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			printf("Customer: %d. Reservation completed successfully. The transaction number is %i and your positions are ", *id, transactions);
				
			for(int i=0; i<10; i++){
				if(Seatable[whereseat][i] == *id){
					thes = i;
					if(whereseat<5){
						printf("Zone A/ Line %d/ Seats", whereseat);
						for(int k=thes; k<(thes+Numoftickets); k++){
							printf(" %d", i);
							i++;
						}
						i=10;
					}else if(whereseat<15){
						printf("Zone B/ Line %d/ Seats", whereseat);
						for(int k=thes; k<(thes+Numoftickets); k++){
							printf(" %d", i);
							i++;
						}
						i=10;
					}else{
						printf("Zone C/ Line %d/ Seats", whereseat);
						for(int k=thes; k<(thes+Numoftickets); k++){
							printf(" %d,", i);
							i++;
						}
						i=10;
					}
				}		
			}
			printf("and transaction cost is %i.\n", cost);
			printf("\n");
			rc = pthread_mutex_unlock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
		}else{	
			rc = pthread_mutex_lock(&th);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			third++;
			rc = pthread_mutex_unlock(&th);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}	
			rc = pthread_mutex_lock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			printf("Customer: %d. The reservation was canceled because the credit card transaction was not accepted.\n",*id);
			rc = pthread_mutex_unlock(&screen);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
				
			rc = pthread_mutex_lock(&plano);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			for(int i=thes; i<(thes+Numoftickets); i++){
				Seatable[whereseat][i] = 0;
			}
			rc = pthread_mutex_unlock(&plano);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			rc = pthread_mutex_lock(&total);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			totalseats[whereseat] += Numoftickets;
			rc = pthread_mutex_unlock(&total);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
			
			rc = pthread_mutex_lock(&trapeza);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
				exit(-1);		
			}
			Bank = Bank - cost;
			rc = pthread_mutex_unlock(&trapeza);
			if (rc != 0) {
				printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
				exit(-1);		
			}
		}
			
		clock_gettime(CLOCK_REALTIME, &stop);
		if( clock_gettime( CLOCK_REALTIME, &stop) == -1 ) {
			printf("ERROR: clock gettime = -1");
			exit(-1);
		}
		
		rc = pthread_mutex_lock(&averageserv);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
			exit(-1);		
		}
		Averageservice = Averageservice + (stop.tv_sec - start.tv_sec);
		rc = pthread_mutex_unlock(&averageserv);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
			exit(-1);		
		}

		rc = pthread_mutex_lock(&lockca);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_lock() is %d\n", rc);
			exit(-1);		
		}
		Nca++;
		rc = pthread_mutex_unlock(&lockca);
		if (rc != 0) {
			printf("ERROR: return code from pthread_mutex_unlock() is %d\n", rc);
			exit(-1);		
		}
	}
	rc = pthread_cond_signal(&cond);
	if (rc != 0) {	
		printf("ERROR: return code from pthread_cond_signal() is %d\n", rc);
		pthread_exit(&rc);
	}
	pthread_exit(id);
}



int main(int argc, char** argv) {
	
	if (argc != 3) {
		printf("ERROR: the program should take two arguments, the number of customers and the original seed!\n");
		exit(-1);
	}

	int Numofcustomers = atoi(argv[1]);
	int OriginalSeed = atoi(argv[2]);
	
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
	int countArray[Numofcustomers];
	pthread_mutex_init(&lock, NULL);
	pthread_mutex_init(&tseat, NULL);
	pthread_mutex_init(&r90, NULL);
	pthread_mutex_init(&trapeza, NULL);
	pthread_mutex_init(&trans, NULL);
	pthread_mutex_init(&total, NULL);
	pthread_mutex_init(&averagew, NULL);
	pthread_mutex_init(&averageserv, NULL);
	pthread_mutex_init(&plano, NULL);
	pthread_mutex_init(&sid, NULL);
	pthread_mutex_init(&cseat, NULL);
	pthread_mutex_init(&lockca, NULL);
	pthread_mutex_init(&screen, NULL);
	pthread_mutex_init(&Pzn, NULL);
	pthread_mutex_init(&f, NULL);
	pthread_mutex_init(&s, NULL);
	pthread_mutex_init(&th, NULL);
	
	for(threadid = 0; threadid < Numofcustomers; threadid++){
		countArray[threadid] = threadid + 1;
		rc = pthread_create(&threads[threadid], NULL, Pelatis, &countArray[threadid]);
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
	Averagewait = Averagewait/Numofcustomers;

	for(int i=0; i<25; i++){
		for(int j=0; j<Nseat; j++){
			if(Seatable[i][j] != 0){
				if(i<5){
					printf("Zone A/ Line %d/ Seat %d / Customer %d\n", i, j, Seatable[i][j]);
				}else if(i<15){
					printf("Zone B/ Line %d/ Seat %d / Customer %d\n", i, j, Seatable[i][j]);
				}else{
					printf("Zone C/ Line %d/ Seat %d / Customer %d\n", i, j, Seatable[i][j]);
				}
			}
		}
	}
	printf("The percentage of transactions, which there aren't enough available tickets is: %d%\n", first*100/Numofcustomers);
	printf("The percentage of transactions, which the credit card was accepted is: %d%\n", second*100/Numofcustomers);
	printf("The percentage of transactions, which the credit card was not accepted is: %d%\n", third*100/Numofcustomers);
	printf("Total sales revenue: %d.\n", Bank);
	printf("Average customer waiting time: %.3f.\n", Averagewait);
	printf("Average customer service time: %.3f.\n", Averageservice);
	
	pthread_mutex_destroy(&lock);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&trapeza);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&f);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&s);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&th);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&tseat);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&r90);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&trans);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&total);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&averagew);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&averageserv);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&plano);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&Pzn);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&sid);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&cseat);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&lockca);
	if (rc != 0) {
		printf("ERROR: return code from pthread_mutex_destroy() is %d\n", rc);
		exit(-1);		
	}
	pthread_mutex_destroy(&screen);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
