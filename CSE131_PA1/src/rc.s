! 
! Generated Fri May 23 21:47:06 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

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

! -------in writeParameter: y param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

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

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	72, %l0
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


! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 2: 2.0

! --------in getAddressHelper: 2
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 777
	set	777, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter--------

! ---------intToFloat: 777 777

! =======in intToFloat: getAddress of 777

! --------in getAddressHelper: 777
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 777
	ld	[%l0], %f0

! =======in intToFloat: call itos 777
	fitos	%f0, %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in getValue: 777: 777.0

! --------in getAddressHelper: 777
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	st	%f0, [%l0]
	ld	[%l0], %o0

! -------in writePassParameter--------

! ---------intToFloat: a null

! =======in intToFloat: getAddress of a

! --------in getAddressHelper: a
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of a
	ld	[%l0], %f0

! =======in intToFloat: call itos a
	fitos	%f0, %f0
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	st	%f0, [%l0]
	ld	[%l0], %o1

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 12) & -8

