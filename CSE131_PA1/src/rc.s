! 
! Generated Sat May 24 02:38:58 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "b is: "
	.align 4
temp1:	.asciz "c is: "
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


! -------in writeParameter: b param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null

! --------in getAddressHelper: b
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


! --------in writeReturnStmt---------

! --------in getAddressHelper: b
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
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


! ------in writeConstantLiteral: 9
	set	9, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter--------

! ---------intToFloat: 9 9

! =======in intToFloat: getAddress of 9

! --------in getAddressHelper: 9
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 9
	ld	[%l0], %f0

! =======in intToFloat: call itos 9
	fitos	%f0, %f0

! ---------intToFloat---------
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in getValue: 9: 9.0

! --------in getAddressHelper: 9
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	st	%l1, [%l0]
	ld	[%l0], %o0

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! ---------in writeLocalVariableWInit:c
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: c: null

! --------in getAddressHelper: c
	set	-12, %l0
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

	SAVE.main = -(92 + 12) & -8

