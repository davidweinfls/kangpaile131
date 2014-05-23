! 
! Generated Fri May 23 00:34:55 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r1.0
	.section ".data"
	.align 4

	.section ".text"
	.align 4


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

! ---------in writeLocalVariableWOInit:y

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 1.0
	set	temp0, %l0
	ld	[%l0], %f0
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: y  =  1.0

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 1.0: 1.0

! --------in getAddressHelper: 1.0
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: y

! ----------in writeArrayAddress: y

! =======in writeArrayAddress, get address of var :y and store in l4

! --------in getAddressHelper: y
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 1
	set	4, %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------writeUnaryExpr: y ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: y

! =======in writePost, step 1: load value to local1

! -------in getValue: y: null

! --------in getAddressHelper: y

! ----------in writeArrayAddress: y

! =======in writeArrayAddress, get address of var :y and store in l4

! --------in getAddressHelper: y
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 1
	set	4, %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	ld	[%l0], %f1

! -------end of getValue------------

! =======in writePost, step 1.5: store original value 
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	set	.float_one, %l0
	ld	[%l0], %f2
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	fadds	%f1, %f2, %f3

! --------in getAddressHelper: y

! ----------in writeArrayAddress: y

! =======in writeArrayAddress, get address of var :y and store in l4

! --------in getAddressHelper: y
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 1
	set	4, %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	st	%f3, [%l0]

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y

! ----------in writeArrayAddress: y

! =======in writeArrayAddress, get address of var :y and store in l4

! --------in getAddressHelper: y
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 1
	set	4, %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

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

	SAVE.main = -(92 + 32) & -8

