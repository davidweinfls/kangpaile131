! 
! Generated Fri May 23 23:51:35 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r5.0
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: x param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 0) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 5.0
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter--------

! -------in getValue: 5.0: 5.0

! --------in getAddressHelper: 5.0
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	st	%f0, [%l0]
	ld	[%l0], %o0

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 4) & -8

