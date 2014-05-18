! 
! Generated Sun May 18 16:38:15 PDT 2014
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

	.section ".bss"
	.align 4

	.global x
	
x:	.skip 4

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 7
	set	7, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! x+7

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 7's value

! -------in getValue: x: null
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: 7: 7.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: x  =  result

! -------in getValue: result: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	x, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: x: null
	set	x, %l0
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

	SAVE.main = -(92 + 8) & -8

