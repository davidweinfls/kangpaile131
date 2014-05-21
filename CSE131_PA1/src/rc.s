! 
! Generated Wed May 21 01:19:12 PDT 2014
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
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 4
	set	4, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 4: 4.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------writeUnaryExpr: x --

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is --, do nothing=========

! ----------writePost: x

! =======in writePost, step 1: load value to local1

! -------in getValue: x: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! =======in writePost, step 1.5: store original value 
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	sub	%l1, 1, %l3

! =======in writePost, step 3: store value 
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------writeUnaryExpr: x --

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is --, do nothing=========

! ----------in writePre: x

! =======in writePre, step 1: load value to local1

! -------in getValue: x: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! =======in writePre, step 2: computation 
	sub	%l1, 1, %l1

! =======in writePre, step 3: store value 
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writePrint---------------

! -------in getValue: x: null
	set	-8, %l0
	add	%fp, %l0, %l0
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

	SAVE.main = -(92 + 12) & -8

