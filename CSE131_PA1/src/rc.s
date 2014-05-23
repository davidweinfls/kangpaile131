! 
! Generated Fri May 23 04:05:00 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "x: "
	.align 4
	.section ".data"
	.align 4

	.global x
	
x:	.word 0

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! -------in writeWhileStart---------
whileStart1:

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! x < 5

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 5's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-4, %l0
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
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ---------in writeWhile: result
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	whileEnd1
	nop


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! x + 1

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 1's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: 1: 1.0

! --------in getAddressHelper: 1
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: x  =  result

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	x, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

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


! ---------in writeBreak----------
	ba	whileEnd1
	nop


! -------in writeWhileEnd------------
	ba	whileStart1
	nop


! ----------in writeCloseBlock-----------
whileEnd1:

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

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


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 16) & -8

