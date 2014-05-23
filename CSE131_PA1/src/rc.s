! 
! Generated Thu May 22 21:22:38 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "foo(): "
	.align 4
temp1:	.asciz "a: "
	.align 4
	.section ".data"
	.align 4

	.global a
	
a:	.word 5

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


! -------in writeParameter: x param no: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------writeUnaryExpr: x ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------in writePre: x

! =======in writePre, step 1: load value to local1

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! =======in writePre, step 2: computation 
	add	%l1, 1, %l1

! =======in writePre, step 3: store value 
	set	68, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeReturnStmt---------
	set	68, %l0
	add	%fp, %l0, %l0
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


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! -------in writePassParameter--------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
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

! --------in getAddressHelper: a
	set	a, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
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

	SAVE.main = -(92 + 4) & -8

