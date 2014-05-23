! 
! Generated Thu May 22 23:51:14 PDT 2014
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

	.section ".bss"
	.align 4

static_main_x0:	.skip 20
	
	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeGlobalArray: x is array of  int[5]

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 0: 0.0

! --------in getAddressHelper: 0
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:i
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeWhileStart---------
whileStart1:

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! i < 5

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get i's value and 5's value

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bge	compOp0
	nop

	set	1, %l3
compOp0:
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ---------in writeWhile: result
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	whileEnd1
	nop


! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x
	set	static_main_x0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: i
	set	4, %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------writeUnaryExpr: i ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: i

! =======in writePost, step 1: load value to local1

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! =======in writePost, step 1.5: store original value 
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 1, %l3

! =======in writePost, step 3: store value 
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! -------in writeWhileEnd------------
	ba	whileStart1
	nop


! ----------in writeCloseBlock-----------
whileEnd1:

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 24) & -8

