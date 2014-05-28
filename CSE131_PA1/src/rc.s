! 
! Generated Wed May 28 15:00:50 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
	.align 4

temp0:	.asciz "&a"
	.align 4
temp1:	.single 0r44.0
temp2:	.asciz "x is: "
	.align 4
temp3:	.single 0r50.0
temp4:	.asciz "new x is: "
	.align 4
temp5:	.single 0r10.0
	.section ".data"
	.align 4

	.global y
	
y:	.single 0r5.0

	.section ".text"
	.align 4


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

! -------writeUnaryExpr: a ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: a

! =======in writePost, step 1: load value to local1

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %f0

! -------end of getValue------------
	set	.float_one, %l0
	ld	[%l0], %f2

! =======in writePost, step 1.5: store original value 
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	fadds	%f0, %f2, %f3

! =======in writePost, step 3: store value 

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f3, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------in writeAddressOf: a

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	-12, %l0
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

	SAVE.foo = -(92 + 12) & -8


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo1>>>>>>>>>>>>>>>>>
	.align 4
	.global foo1
foo1:
	set	SAVE.foo1, %g1
	save	%sp, %g1, %sp


! --------in writeReturnStmt---------

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo1 = -(92 + 12) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 44.0
	set	temp1, %l0
	ld	[%l0], %f0
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 44.0: 44.0

! --------in getAddressHelper: 44.0
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! ---------in writeLocalVariableWInit:x
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------in writePassParameter--------

! =====in writePassParameter, param x is byRef=======

! --------in getAddressHelper: x
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
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

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------in writePassParameter--------

! =====in writePassParameter, param x is byRef=======

! --------in getAddressHelper: x
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------in writeConstantLiteral: 50.0
	set	temp3, %l0
	ld	[%l0], %f0
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: result  =  50.0

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 50.0: 50.0

! --------in getAddressHelper: 50.0
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: result
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------
	set	temp4, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ----------writeFuncCall------------
	call	foo1
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------in writeConstantLiteral: 10.0
	set	temp5, %l0
	ld	[%l0], %f0
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: result  =  10.0

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 10.0: 10.0

! --------in getAddressHelper: 10.0
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: result
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 40) & -8

