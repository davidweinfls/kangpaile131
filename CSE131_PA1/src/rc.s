! 
! Generated Thu Jun 05 23:28:32 PDT 2014
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

temp0:	.asciz "b3 = true << "
	.align 4
	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ---------in writeLocalVariableWOInit:b3

! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ---------in intToFloat: 3 3

! =======in intToFloat: getAddress of 3

! --------in getAddressHelper: 3
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 3
	ld	[%l0], %f0

! =======in intToFloat: call itos 3
	fitos	%f0, %f0

! ---------end of intToFloat---------

! ---------in writeLocalVariableWInit:f3
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------in writeAddressOf: f3

! --------in getAddressHelper: f3
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: f3: null

! --------in getAddressHelper: f3
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:fptr
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: fptr

! --------in getAddressHelper: fptr
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel0
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel0:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------
	add	%sp, 0, %l4
	cmp	%l0, %l4
	blu	deallocatedStack0
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack0
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack0:

! ----------in writeTypeCast-----------

! ========in writeTypeCast, float to bool=========

! --------in getAddressHelper: fptr

! -------in writeDerefAddress: fptr

! --------in getAddressHelper: fptr
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel1
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel1:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! --------in getAddressHelper: fptr
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	set	0, %l3
	cmp	%l1, %g0
	be	.typeCastBool0
	nop

	set	1, %l3

.typeCastBool0:
	st	%l3, [%l0]

! ----------end of writeTypeCast----------

! ----------in writeAssignExpr: b3  =  fptr

! -------in getValue: fptr: null

! --------in getAddressHelper: fptr
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: b3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b3: null

! --------in getAddressHelper: b3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
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

	SAVE.main = -(92 + 28) & -8

