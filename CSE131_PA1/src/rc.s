! 
! Generated Sat May 17 22:45:23 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "e: "
	.align 4
temp1:	.asciz "a: "
	.align 4
temp2:	.asciz "after"
	.align 4
temp3:	.asciz "e: "
	.align 4
temp4:	.asciz "a: "
	.align 4
	.section ".data"
	.align 4

	.global a
	
a:	.word 1

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


! ---------in writeLocalVariableW)Init:e

! -------writeUnaryExpr: a ++

! ----------writePost: a

! -----------in writePost, step 1: load value to local1

! -------in writeExpr: a: null
	set	a, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! -----------in writePost, step 1.5: store original value 
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -----------in writePost, step 2: computation 
	add	%l1, 1, %l3

! -----------in writePost, step 3: store value 
	set	a, %l0
	add	%g0, %l0, %l0
	st	%l3, [%l0]

! ----------in writeAssignExpr: e  =  result

! -------in writeExpr: result: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	temp0, %o0
	call	printf
	nop


! -------in writeExpr: e: null
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop

	set	temp1, %o0
	call	printf
	nop


! -------in writeExpr: a: null
	set	a, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop

	set	temp2, %o0
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! -------writeUnaryExpr: a ++

! ----------in writePre: a

! -----------in writePre, step 1: load value to local1

! -------in writeExpr: a: null
	set	a, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! -----------in writePre, step 2: computation 
	add	%l1, 1, %l1

! -----------in writePre, step 3: store value 
	set	a, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: e  =  a

! -------in writeExpr: a: null
	set	a, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	temp3, %o0
	call	printf
	nop


! -------in writeExpr: e: null
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop

	set	temp4, %o0
	call	printf
	nop


! -------in writeExpr: a: null
	set	a, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 8) & -8

