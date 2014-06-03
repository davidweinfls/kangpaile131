! 
! Generated Mon Jun 02 23:41:13 PDT 2014
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

temp0:	.asciz " "
	.align 4
temp1:	.asciz " "
	.align 4
	.section ".data"
.allocatedMemory:	.word	0
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


! -------in writeParameter: i param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------in writeParameter: ii param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

! -------in writeParameter: iii param num: 2
	set	76, %l0
	add	%fp, %l0, %l0
	st	%i2, [%l0]

! ------------in writePrint---------------

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: ii: null

! --------in getAddressHelper: ii
	set	72, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: iii: null

! --------in getAddressHelper: iii
	set	76, %l0
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


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 0) & -8


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

! -------in writePassParameter: i

! ---------in intToFloat: 1 1

! =======in intToFloat: getAddress of 1

! --------in getAddressHelper: 1
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 1
	ld	[%l0], %f0

! =======in intToFloat: call itos 1
	fitos	%f0, %f0

! ---------end of intToFloat---------
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	ld	[%l0], %o0

! -------in writePassParameter: ii

! ---------in intToFloat: 2 2

! =======in intToFloat: getAddress of 2

! --------in getAddressHelper: 2
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 2
	ld	[%l0], %f1

! =======in intToFloat: call itos 2
	fitos	%f1, %f1

! ---------end of intToFloat---------
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f1, [%l0]
	ld	[%l0], %o1

! -------in writePassParameter: iii

! ---------in intToFloat: 3 3

! =======in intToFloat: getAddress of 3

! --------in getAddressHelper: 3
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 3
	ld	[%l0], %f0

! =======in intToFloat: call itos 3
	fitos	%f0, %f0

! ---------end of intToFloat---------
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	ld	[%l0], %o2

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

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

	SAVE.main = -(92 + 16) & -8

