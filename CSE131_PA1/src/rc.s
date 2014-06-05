! 
! Generated Thu Jun 05 01:11:02 PDT 2014
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

temp0:	.asciz "ok"
	.align 4
	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.section ".bss"
	.align 4

	.global a
a:	.skip 4
	
	.section ".text"
	.align 4


! --------in writeGlobalStruct--------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeAddressOf: a

! --------in getAddressHelper: a
	set	a, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:struct
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: struct

! --------in getAddressHelper: struct
	set	-8, %l0
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

! ------in writeConstantLiteral: true
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: z  =  true

! -------in getValue: true: 1.0

! --------in getAddressHelper: true
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: z

! -------in writeStructAddress: z

! -------in writeDerefAddress: struct

! --------in getAddressHelper: struct
	set	-8, %l0
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
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: struct

! --------in getAddressHelper: struct
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel2
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel2:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------
	add	%sp, 0, %l4
	cmp	%l0, %l4
	blu	deallocatedStack1
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack1
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack1:

! ------------in writeIf: z

! --------in getAddressHelper: z

! -------in writeStructAddress: z

! -------in writeDerefAddress: struct

! --------in getAddressHelper: struct
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel3
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel3:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else0
	nop


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf0
	nop

else0:

! ----------in writeCloseBlock-----------
.endIf0:

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

