! 
! Generated Thu May 22 23:20:58 PDT 2014
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


! -------in writeParameter: x param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: x  =  3

! -------in getValue: 3: 3.0

! --------in getAddressHelper: 3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	68, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l0
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! --------in writeReturnStmt---------

! --------in getAddressHelper: x
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo = -(92 + 4) & -8


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

! --------in getAddressHelper: a
	set	a, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-8, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 8) & -8

