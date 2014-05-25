! 
! Generated Sat May 24 17:00:52 PDT 2014
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

	.global b
	
b:	.word 0

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


! -------writeUnaryExpr: b !

! =======in writeUnaryExpr, non-const folding, computation=========

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	b, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	xor	%l1, 1, %l1

! =======in writeUnaryExpr, result get address=========
	set	-4, %l0
	add	%fp, %l0, %l0

! =======in writeUnaryExpr, non-const folding, store value=========
	st	%l1, [%l0]

! ---------end of writeUnary----------

! =======in writeAnd, &&, check first operand=========
	set	0, %l3

! --------in getAddressHelper: result
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop


! -------writeUnaryExpr: b !

! =======in writeUnaryExpr, non-const folding, computation=========

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	b, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------
	xor	%l2, 1, %l2

! =======in writeUnaryExpr, result get address=========
	set	-8, %l0
	add	%fp, %l0, %l0

! =======in writeUnaryExpr, non-const folding, store value=========
	st	%l2, [%l0]

! ---------end of writeUnary----------

! --------in writeBinaryExpr-------

! result && result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, &&, check second operand=========
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop

	set	1, %l3
endAND0:

! =======in writeBinaryExpr, do store result=========
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool0
	nop

	set	.boolT, %o0

.printBool0:
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 12) & -8

