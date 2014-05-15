! 
! Generated Thu May 15 14:14:56 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

	.section ".data"
	.align 4

	.global x
	
x:	.word 5

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConst--------
	set	3, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ---------in writeLocalVariableW)Init:y

! ----------in writeAssignExpr: y  =  x
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 12) & -8

