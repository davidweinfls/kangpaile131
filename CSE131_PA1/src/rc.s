! 
! Generated Thu May 29 21:06:07 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
.ArrayOutOfBounds:	.asciz "Index value of %d is outside legal range [0, %d).\n"
	.align 4

temp0:	.asciz "foo1"
	.align 4
temp1:	.asciz "abaaba"
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo1>>>>>>>>>>>>>>>>>
	.align 4
	.global foo1
foo1:
	set	SAVE.foo1, %g1
	save	%sp, %g1, %sp


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo1 = -(92 + 0) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ----------writeFuncCall------------
	call	foo1
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! ------------in writePrint---------------

! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 0) & -8

