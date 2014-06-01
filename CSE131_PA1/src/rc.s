! 
! Generated Sun Jun 01 15:44:22 PDT 2014
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

temp0:	.single 0r10.5
temp1:	.single 0r13.5
	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.global a7
	
a7:	.single 0r7.5

	.global a8
	
a8:	.single 0r8.5

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: a1 param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------in writeParameter: a2 param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

! -------in writeParameter: a3 param num: 2
	set	76, %l0
	add	%fp, %l0, %l0
	st	%i2, [%l0]

! -------in writeParameter: a4 param num: 3
	set	80, %l0
	add	%fp, %l0, %l0
	st	%i3, [%l0]

! -------in writeParameter: a5 param num: 4
	set	84, %l0
	add	%fp, %l0, %l0
	st	%i4, [%l0]

! -------in writeParameter: a6 param num: 5
	set	88, %l0
	add	%fp, %l0, %l0
	st	%i5, [%l0]

! -------in writeParameter: a7 param num: 6
	set	92, %l0
	add	%fp, %l0, %l0

! -------in writeParameter: a8 param num: 7
	set	96, %l0
	add	%fp, %l0, %l0

! ------------in writePrint---------------

! -------in getValue: a1: null

! --------in getAddressHelper: a1
	set	68, %l0
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

! -------in getValue: a2: null

! --------in getAddressHelper: a2
	set	72, %l0
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

! -------in getValue: a3: null

! --------in getAddressHelper: a3
	set	76, %l0
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

! -------in getValue: a4: null

! --------in getAddressHelper: a4
	set	80, %l0
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

! -------in getValue: a5: null

! --------in getAddressHelper: a5
	set	84, %l0
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

! -------in getValue: a6: null

! --------in getAddressHelper: a6
	set	88, %l0
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


! ------in writeConstantLiteral: 10.5
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a7  =  10.5

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 10.5: 10.5

! --------in getAddressHelper: 10.5
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: a7
	set	92, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 13.5
	set	temp1, %l0
	ld	[%l0], %f0
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a8  =  13.5

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 13.5: 13.5

! --------in getAddressHelper: 13.5
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: a8
	set	96, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

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


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 4
	set	4, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 6
	set	6, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter: a1

! --------in getAddressHelper: 1
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o0

! -------end of writePassParameter--------

! -------in writePassParameter: a2

! --------in getAddressHelper: 2
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o1

! -------end of writePassParameter--------

! -------in writePassParameter: a3

! --------in getAddressHelper: 3
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o2

! -------end of writePassParameter--------

! -------in writePassParameter: a4

! --------in getAddressHelper: 4
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o3

! -------end of writePassParameter--------

! -------in writePassParameter: a5

! --------in getAddressHelper: 5
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o4

! -------end of writePassParameter--------

! -------in writePassParameter: a6

! --------in getAddressHelper: 6
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o5

! -------end of writePassParameter--------

! ---------in writeExtraArguments--------
	add	%sp, -8 & -8, %sp

! =======in writeExtraArguments, param is: a7

! --------in getAddressHelper: a7
	set	a7, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	92, %l0
	add	%sp, %l0, %l0
	st	%l1, [%l0]

! =======in writeExtraArguments, param is: a8

! --------in getAddressHelper: a8
	set	a8, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	96, %l0
	add	%sp, %l0, %l0
	st	%l1, [%l0]

! ---------end of writeExtraArguments-------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! ------------in writePrint---------------

! -------in getValue: a7: null

! --------in getAddressHelper: a7
	set	a7, %l0
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


! ------------in writePrint---------------

! -------in getValue: a8: null

! --------in getAddressHelper: a8
	set	a8, %l0
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

	SAVE.main = -(92 + 24) & -8

