! 
! Generated Thu Jun 05 20:04:30 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
.ArrayOutOfBounds:	.asciz "Index value of %d is outside legal range [0,%d).\n"
.deallocatedStack:	.asciz "Attempt to dereference a pointer into deallocated stack space.\n"
.memoryLeakError:	.asciz "%d memory leak(s) detected in heap space.\n"
.doubleDeleteError:	.asciz "Double delete detected. Memory region has already been released in heap space.\n"
	.align 4

temp0:	.asciz "10 = "
	.align 4
	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.global x
	
x:	.word 0

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


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! x + 5

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 5's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: x  =  result

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! x > 5

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 5's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	ble	compOp0
	nop

	set	1, %l3
compOp0:
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result

! --------in getAddressHelper: result
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else0
	nop


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeReturnStmt---------
	set	5, %i0
	ret
	restore

! ---------in writeElse---------
	ba	.endIf0
	nop

else0:

! ----------in writeCloseBlock-----------
.endIf0:

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! --------in writeBinaryExpr-------

! 5 + result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get 5's value and result's value

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeReturnStmt---------

! --------in getAddressHelper: result
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo = -(92 + 36) & -8


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
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:y
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y
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
	ret
	restore

! -------in writeMemoryLeak-------
	set	.doubleDeleteError, %o0
	set	.allocatedMemory, %l0
	ld	[%l0], %o1
	cmp	%o1, %g0
	bge	._memleaklabel0
	nop

	call	printf
	nop

._memleaklabel0:
	set	.memoryLeakError, %o0
	set	.allocatedMemory, %l0
	ld	[%l0], %o1
	cmp	%o1, %g0
	be	._memleaklabel1
	nop

	call	printf
	nop

._memleaklabel1:

! -------end of writeMemoryLeak--------

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 12) & -8

