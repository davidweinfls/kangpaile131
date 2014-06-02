! 
! Generated Sun Jun 01 22:41:40 PDT 2014
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


! ------in writeConstantLiteral: 441
	set	441, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 441: 441.0

! --------in getAddressHelper: 441
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:b
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeAddressOf: b

! --------in getAddressHelper: b
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:c
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ----------in writeTypeCast-----------

! ========in writeTypeCast, do regular case=========

! ========in writeTypeCast, get oldSTO value=========

! -------in getValue: c: null

! --------in getAddressHelper: c
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ========in writeTypeCast, get newSTO address=========

! --------in getAddressHelper: c
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! ========in writeTypeCast, store old value to new sto=========
	st	%l1, [%l0]

! ----------end of writeTypeCast----------

! ---------in writeLocalVariableWInit:a
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: a

! --------in getAddressHelper: a
	set	-24, %l0
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

! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a

! -------in writeDerefAddress: a

! --------in getAddressHelper: a
	set	-24, %l0
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

	SAVE.main = -(92 + 28) & -8

