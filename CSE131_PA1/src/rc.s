! 
! Generated Thu Jun 05 00:54:22 PDT 2014
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

temp0:	.asciz "b: "
	.align 4
	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.section ".bss"
	.align 4

arr:	.skip 12
	
	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: b param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null

! --------in getAddressHelper: b
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


! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: b

! --------in getAddressHelper: b
	set	68, %l0
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

! -------in getValue: b: null

! --------in getAddressHelper: b

! -------in writeDerefAddress: b

! --------in getAddressHelper: b
	set	68, %l0
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

! -------end of getValue------------
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writePrint---------------

! -------in getValue: int*: null

! --------in getAddressHelper: int*

! ----------in writeArrayAddress: int*

! =======in writeArrayAddress, get value of index: 2

! --------in getAddressHelper: 2
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :b and store in l4

! --------in getAddressHelper: b
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

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

	SAVE.foo = -(92 + 8) & -8


! ----------in writeGlobalArray: arr is array of  int[3]

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: arr

! --------in getAddressHelper: 2
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	3, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	3, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck0:

! --------in getAddressHelper: 2
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	3, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck0:

! --------end of writeRunTimeArrayCheck---------

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: arr  =  5

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: arr

! ----------in writeArrayAddress: arr

! =======in writeArrayAddress, get value of index: 2

! --------in getAddressHelper: 2
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :arr and store in l4

! --------in getAddressHelper: arr
	set	arr, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! -------in writePassParameter: b

! --------in getAddressHelper: arr
	set	arr, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

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

	SAVE.main = -(92 + 12) & -8

