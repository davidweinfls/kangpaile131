! 
! Generated Fri Jun 06 19:36:29 PDT 2014
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

temp0:	.single 0r8.6
	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.section ".bss"
	.align 4

	.global a
a:	.skip 4
	
	.global b
b:	.skip 4
	
	.section ".text"
	.align 4


! --------in writeGlobalStruct--------

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

! ---------in writeLocalVariableWInit:ap
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeAddressOf: b

! --------in getAddressHelper: b
	set	b, %l0
	add	%g0, %l0, %l0

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

! ---------in writeLocalVariableWInit:bp
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ----------in writeAssignExpr: b  =  bp

! -------in getValue: bp: null

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: b

! -------in writeStructAddress: b

! =======in writeStructAddress: ap is a ptr or byRef========

! --------in getAddressHelper: ap
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: b

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ---------in writeNewStmt------
	set	1, %o0
	set	4, %o1
	call	calloc
	nop


! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

! --------end of getAddressHelper------------ 
	st	%o0, [%l0]
	set	.allocatedMemory, %l0
	ld	[%l0], %l1
	inc	%l1
	st	%l1, [%l0]

! ---------end 0f writeNewStmt------

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: y

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

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

! ------in writeConstantLiteral: 8.6
	set	temp0, %l0
	ld	[%l0], %f0
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: y  =  8.6

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 8.6: 8.6

! --------in getAddressHelper: 8.6
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: y

! -------in writeDerefAddress: y

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

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
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: y

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

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

! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y

! -------in writeDerefAddress: y

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

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

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: y

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! -------in writeStructAddress: b

! =======in writeStructAddress: ap is a ptr or byRef========

! --------in getAddressHelper: ap
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: b
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel4
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel4:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------
	add	%sp, 0, %l4
	cmp	%l0, %l4
	blu	deallocatedStack2
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack2
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack2:

! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y

! -------in writeDerefAddress: y

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! -------in writeStructAddress: b

! =======in writeStructAddress: ap is a ptr or byRef========

! --------in getAddressHelper: ap
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: b
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel5
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel5:

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


! ---------in writeDeleteStmt------

! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! ======in writeDeleteStmt, check nullPtrExcep=======
	set	0, %l4
	cmp	%l1, %l4
	bne	ptrLabel6
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel6:

! ======end of check nullPtrExcep=======
	mov	%l1, %o0
	call	free
	nop


! --------in getAddressHelper: y

! -------in writeStructAddress: y

! =======in writeStructAddress: bp is a ptr or byRef========

! --------in getAddressHelper: bp
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	add	%l0, 0, %l0

! -------------end of writeStructAddress: y

! --------end of getAddressHelper------------ 
	set	0, %l1
	st	%l1, [%l0]
	set	.allocatedMemory, %l0
	ld	[%l0], %l1
	dec	%l1
	st	%l1, [%l0]

! ---------end 0f writeDeleteStmt------

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

	SAVE.main = -(92 + 32) & -8

