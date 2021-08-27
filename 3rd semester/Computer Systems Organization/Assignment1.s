#AUTHOR: THOMAS KONSTANTINIDIS
#DATE: 9/11/2018
#DESCRIPTION: A PROGRAM TO FIND THE GSD 

	.text
	.globl main
main:


		lw $t1, A		#int a, b, y, s;		
		lw $t2, B		
		lw $t3, Y		
		lw $t4, S		

		
        la $a0, prompt 		#print (prompt)
		li $v0, 4
		syscall

		li $v0,5			#A=read_int()
		syscall
		add $t1, $v0, $zero

		la $a0, CRLF		#change line
		li $v0, 4
		syscall
		
		la $a0, prompt 		#print (prompt)
		li $v0, 4
		syscall
		
		li $v0,5			#B=read_int()
		syscall
		add $t2, $v0, $zero
		
		la $a0, CRLF		#change line
		li $v0, 4
		syscall



#from here to		
		
		la $a0, ans			
		li $v0, 4
		syscall
		
		
		move $a0, $t1
		li $v0, 1
		syscall
		
		
		la $a0, ans3
		li $v0, 4
		syscall
	
		
		move $a0, $t2
		li $v0, 1
		syscall
		
		
		la $a0, ans5
		li $v0, 4
		syscall

#here the code print("which is the gsd of a and b ?)





		
#y=a%b;
		rem $t3, $t1, $t2   

		
		
		
		
#while (y != 0) {
#a = b;
#b = y;
#y = a % b;
#}		
		
again:  
		beq $t3,$zero,exit
		add $t2,$t3,$zero
		add $t1,$t2,$zero
		rem $t3, $t1, $t2
		j again



		
#s=read_int();	
		
exit:   
		
		li $v0,5
		syscall
		add $t4,$v0,$zero
		
	

	
#while (s != b) {
#print("wrong try again:")
#print("which is the gsd:")
#s= read_int();
#}
	
loopa:   
		beq $t4,$t2,finish
		la $a0, mnm 
		li $v0, 4
		syscall
		
		la $a0, CRLF
		li $v0, 4
		syscall
		
		la $a0, mkd 
		li $v0, 4
		syscall
		
		li $v0,5
		syscall
		move $t4,$v0
		j loopa
		
		
#System.out.println("Congratulation")
#finish the program
		
finish: 
		la $a0, cong 
		li $v0, 4
		syscall
		
		li $v0,10
		syscall
		
		
.data
	A:		.word 0	
	B:		.word 0
	Y:		.word 0
	S:		.word 0
	CRLF: 	.asciiz "\n"
	prompt:	.asciiz "Give an interger: "
	ans:	.asciiz "Which is the GSD of "
	ans3:	.asciiz " and "	
	ans5:	.asciiz " ? : "
	mnm:	.asciiz "Wrong! try again: "
	mkd:	.asciiz "Which is the GSD? : "
	cong:	.asciiz "Congratulation! "