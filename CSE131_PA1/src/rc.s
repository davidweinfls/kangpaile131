! 
! Generated Wed May 21 03:00:03 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "recursive foo call "
	.align 4
temp1:	.asciz "breaking out of foo recursive call"
	.align 4
temp2:	.asciz "rec, in the end, is: "
	.align 4
	.section ".data"
	.align 4

	.section ".bss"
	.align 4

	.global rec
	
rec:	.skip 4

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


! -------writeUnaryExpr: rec ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: rec

! =======in writePost, step 1: load value to local1

! -------in getValue: rec: null
	set	rec, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! =======in writePost, step 1.5: store original value 
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 1, %l3

! =======in writePost, step 3: store value 
	set	rec, %l0
	add	%g0, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: rec: null
	set	rec, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------in writeConstantLiteral: 10
	set	10, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! rec > 10

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get rec's value and 10's value

! -------in getValue: rec: null
	set	rec, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: 10: 10.0
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	ble	compOp0
	nop

	set	1, %l3
compOp0:
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else0
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------in writeReturnStmt---------
	ret
	restore

! ---------in writeElse---------
	ba	.endIf0
	nop

else0:

! ----------in writeCloseBlock-----------
.endIf0:

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 12) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: rec  =  1

! -------in getValue: 1: 1.0
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	rec, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! ------------in writePrint---------------
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: rec: null
	set	rec, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
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

