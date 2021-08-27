# Author:	THOMAS KONSTANTINIDIS
# Date:	8/1/2019




	.text
	.globl main
ReadA:
	la $t0,pin
	lw $t2,i
	lw $t3,pin.length
loop: 
	addi $t2,$t2,1
	bgt $t2,$t3,main
	
	la $a0,P
	li $v0,4
	syscall
	
	move $a0, $t2
	li $v0, 1
	syscall
	
	la $a0,P1
	li $v0,4
	syscall
	
	li $v0,5
	syscall
	add $t1, $v0, $zero
	
	sw $t1,($t0)
	addi $t0,$t0,4
	j loop
	
	
ReadB:
	lw $t2,i

CreateA:
CreateB:
	lw $t2,i
	la $t0,pin
	la $t4,Sparse
	lw $t3,pin.length
	
loop1:
	bgt  $t2,$t3,exit1
	beq  $t0,$zero,noty
	move $t4,$t2
	addi $t2,$t2,1
	addi $a0,$a0,1
	addi $t4,$t4,4
	move $t4,$t0
	addi $t0,$t0,4
	addi $t4,$t4,4
	j	 loop1
noty:
	addi $t2,$t2,1
	addi $t0,$t0,4
	j 	 loop1
	
exit1:
	move $v0,$a0
	jr $ra
	
CreateC:
	lw $t0,a
	lw $t6,b1
	lw $t2,c1
	la $t3,SparseA
	la $t4,SparseB
	la $t5,SparseC
	lw $a0,mikosA
	lw $a1,mikosB
	blt $t0,$a0,check
check:
	bge $t6,$a1,for2
	bge $t3,$t4,elseif
	move $t5,$t3
	addi $t2,$t2,1
	addi $t0,$t0,1
	addi $t3,$t3,4
	addi $t5,$t5,4
	move $t5,$t3
	addi $t2,$t2,1
	addi $t0,$t0,1
	addi $t3,$t3,4
	addi $t5,$t5,4
elseif:
	beq $t3,$t4,else2
	move $t5,$t4
	addi $t2,$t2,1
	addi $t6,$t6,1
	addi $t4,$t4,4
	addi $t5,$t5,4
	move $t5,$t4
	addi $t2,$t2,1
	addi $t6,$t6,1
	addi $t4,$t4,4
	addi $t5,$t5,4
else2:
	move $t5,$t3
	addi $t2,$t2,1
	addi $t6,$t6,1
	addi $t0,$t0,1
	addi $t3,$t3,4
	addi $t5,$t5,4
	add	 $t5,$t3,$t4
	addi $t2,$t2,1
	addi $t6,$t6,1
	addi $t0,$t0,1
	addi $t3,$t3,4
	addi $t4,$t4,4
	addi $t5,$t5,4
for2:
	bge $t0,$a0,for3
	move $t5,$t3
	addi $t2,$t2,1
	addi $t0,$t0,1
	addi $t3,$t3,4
	addi $t5,$t5,4
	move $t5,$t3
	addi $t2,$t2,1
	addi $t0,$t0,1
	addi $t3,$t3,4
	addi $t5,$t5,4
for3:
	bge $t6,$a1,main
	move $t5,$t4
	addi $t2,$t2,1
	addi $t6,$t6,1
	addi $t4,$t4,4
	addi $t5,$t5,4
	move $t5,$t4
	addi $t2,$t2,1
	addi $t6,$t6,1
	addi $t4,$t4,4
	addi $t5,$t5,4
DisplayA:
DisplayB:
	la $t4,Sparse
	lw $a0,mikos
	move $a0,$v0
	lw	$t2,i
	bge	$t2,$a0,main
	
	la $a1,P
	li $v0,4
	syscall
	
	move $a1, $t4
	li $v0, 1
	syscall
	
	addi $t4,$t4,4
	la $a1,V			
	li $v0,4
	syscall
	
	addi $t2,$t2,1
	move $a1, $t4
	li $v0, 1
	syscall

	
DisplayC:

CreateB1:
	lw $a0,k
	jal CreateB
	
main:	
	
	la $a0,text1
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text2
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text3
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text4
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text5
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text6
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text7
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text8
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text9
	li $v0,4
	syscall
	
	la $a0,CRLF
	li $v0,4
	syscall
	
	la $a0,text10
	li $v0,4
	syscall
	
	li $v0,5
	syscall
	add $t1, $v0, $zero
	
	beq $t1,1,ReadA
	beq $t1,2,ReadB
	beq $t1,3,CreateA
	beq $t1,4,CreateB1
	beq $t1,5,CreateC
	beq $t1,6,DisplayA
	beq $t1,7,DisplayB
	beq $t1,8,DisplayC
	beq $t1,0,exit
	

exit:	li $v0,10
		syscall
	
	
.data
CRLF:	.asciiz "\n"
text1:	.asciiz "1. Read Array A"
text2:	.asciiz "2. Read Array B"
text3:	.asciiz "3. Create Sparse Array A"
text4:	.asciiz "4. Create Sparse Array B"
text5:	.asciiz "5. Create Sparse Array C = A + B"
text6:	.asciiz "6. Display Sparse Array A"
text7:	.asciiz "7. Display Sparse Array B"
text8:	.asciiz "8. Display Sparse Array C"
text9:	.asciiz "-----------------------------"
text10:	.asciiz "0. Exit"
P:	.asciiz "Position "
P1:	.asciiz ": "
V:	.asciiz "Value: "
pin.length: .word 10
i: .word 0
a: .word 0
b1: .word 0
c1: .word 0
k: .word 0
mikos:	.word 0
mikosA:	.word 0
mikosB:	.word 0
pin: .space 10
Sparse: .space 10
SparseA: .space 10
SparseB: .space 10
SparseC: .space 10








