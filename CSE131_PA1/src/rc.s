! 
! Generated Sun May 25 22:37:47 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Cannot dereference a nullPointer.\n"
	.align 4

	.section ".data"
	.align 4

	.global x
	
x:	.word 5

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


! -------in writeParameter: a param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------in writeConstantLiteral: 10
	set	10, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  10

! -------in getValue: 10: 10.0

! --------in getAddressHelper: 10
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! --------in writeReturnStmt---------

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %i0
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


! -------in writePassParameter--------

! =====in writePassParameter, param x is byRef=======

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

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
	ld	[%l0], %l0
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

	SAVE.main = -(92 + 4) & -8

