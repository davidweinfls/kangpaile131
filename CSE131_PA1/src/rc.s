! 
! Generated Mon May 19 23:54:42 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "b = a++: "
	.align 4
temp1:	.asciz "a is: "
	.align 4
temp2:	.asciz "b++ is: "
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 1: 1.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ---------in writeLocalVariableWOInit:b

! -------writeUnaryExpr: a ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: a

! =======in writePost, step 1: load value to local1

! -------in getValue: a: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! =======in writePost, step 1.5: store original value 
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 1, %l3

! =======in writePost, step 3: store value 
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ----------in writeAssignExpr: b  =  result

! -------in getValue: result: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null
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


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: a: null
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


! -------writeUnaryExpr: b ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: b

! =======in writePost, step 1: load value to local1

! -------in getValue: b: null
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! =======in writePost, step 1.5: store original value 
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 1, %l3

! =======in writePost, step 3: store value 
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null
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


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 20) & -8

