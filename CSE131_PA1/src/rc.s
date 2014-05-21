! 
! Generated Tue May 20 22:30:48 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "foo2 return: "
	.align 4
temp1:	.asciz "a: "
	.align 4
temp2:	.asciz "b: "
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 123
	set	123, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeReturnStmt---------
	set	123, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo = -(92 + 4) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo2>>>>>>>>>>>>>>>>>
	.align 4
	.global foo2
foo2:
	set	SAVE.foo2, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: false
	set	0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeReturnStmt---------
	set	0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo2 = -(92 + 8) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in getValue: result: null
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:a
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ----------writeFuncCall------------
	call	foo2
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
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


! ----------writeFuncCall------------
	call	foo2
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in getValue: result: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:b
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: a: null
	set	-16, %l0
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
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null
	set	-28, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool1
	nop

	set	.boolT, %o0

.printBool1:
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 28) & -8

