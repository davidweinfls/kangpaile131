! 
! Generated Fri May 23 02:41:07 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.asciz "before calling foo, i is "
	.align 4
temp1:	.asciz " and j is"
	.align 4
temp2:	.asciz "after calling foo, i is modified, and is now "
	.align 4
temp3:	.asciz " and j is "
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<bar>>>>>>>>>>>>>>>>>
	.align 4
	.global bar
bar:
	set	SAVE.bar, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: b param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------in writeParameter: c param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

! ------in writeConstantLiteral: 777
	set	777, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: b  =  777

! -------in getValue: 777: 777.0

! --------in getAddressHelper: 777
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

! --------in writeBinaryExpr-------

! b + c

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get b's value and c's value

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: c: null

! --------in getAddressHelper: c
	set	72, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: c  =  result

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	72, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l0
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.bar = -(92 + 8) & -8


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

! -------in writeParameter: b param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

! -------in writePassParameter--------

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %o0

! -------in writePassParameter--------

! --------in getAddressHelper: b
	set	72, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %o1

! ----------writeFuncCall------------
	call	bar
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 8) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:i
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 1: 1.0

! --------in getAddressHelper: 1
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:j
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: j: null

! --------in getAddressHelper: j
	set	-24, %l0
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


! -------in writePassParameter--------

! --------in getAddressHelper: i
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------in writePassParameter--------

! --------in getAddressHelper: j
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o1

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! ------------in writePrint---------------
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	temp3, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: j: null

! --------in getAddressHelper: j
	set	-24, %l0
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


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 24) & -8

