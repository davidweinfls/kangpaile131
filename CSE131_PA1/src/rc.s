! 
! Generated Wed May 21 02:28:56 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "foo"
	.align 4
	.section ".data"
	.align 4

	.global x
	
x:	.single 0r1.22

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------in writeReturnStmt---------
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %f0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo = -(92 + 0) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 4) & -8

