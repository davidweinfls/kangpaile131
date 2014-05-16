! 
! Generated Thu May 15 23:24:33 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

temp0:	.asciz "here"
	.align 4
temp1:	.asciz "else"
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConst: 2
	set	2, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------in writeConst: 1
	set	1, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! 2>1
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf------------
	set	1, %l1
	cmp	%l0, %g0
	be	else1
	nop

	set	temp0, %o0
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! ---------writeElse---------
	ba	.endIf0
	nop

else1:
	set	temp1, %o0
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! ----------in writeCloseBlock-----------
.endIf0:

! --------------in writeFuncClose--------------
ret
restore

	SAVE.main = -(92 + 12) & -8

