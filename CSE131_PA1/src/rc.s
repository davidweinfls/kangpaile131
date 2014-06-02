! 
! Generated Sun Jun 01 23:36:43 PDT 2014
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

temp0:	.single 0r44.0
temp1:	.asciz "x is: "
	.align 4
temp2:	.single 0r50.0
temp3:	.asciz "new x is: "
	.align 4
temp4:	.single 0r10.0
	.section ".data"
.allocatedMemory:	.word	0
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

	SAVE.foo = -(92 + 8) & -8


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

	SAVE.foo1 = -(92 + 0) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 44.0
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 44.0: 44.0

! --------in getAddressHelper: 44.0
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------in writePassParameter: a

! =====in writePassParameter, param x is byRef=======

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-16, %l0
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
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
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


! -------in writePassParameter: a

! =====in writePassParameter, param x is byRef=======

! --------in getAddressHelper: x
	set	-8, %l0
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

! ------in writeConstantLiteral: 50.0
	set	temp2, %l0
	ld	[%l0], %f0
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: result  =  50.0

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 50.0: 50.0

! --------in getAddressHelper: 50.0
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: result
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------
	set	temp3, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
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
	set	temp4, %l0
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

	SAVE.main = -(92 + 40) & -8

