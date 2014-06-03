! 
! Generated Mon Jun 02 20:11:34 PDT 2014
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

	.section ".bss"
	.align 4

	.global b
b:	.skip 20
	
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

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 0: 0.0

! --------in getAddressHelper: 0
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:i
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeWhileStart---------
whileStart1:

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! i < 5

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get i's value and 5's value

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

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
	bge	compOp0
	nop

	set	1, %l3
compOp0:
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ---------in writeWhile: result
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	whileEnd1
	nop


! --------in  writeRunTimeArrayCheck: x

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	5, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	5, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck0:

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	5, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck0:

! --------end of writeRunTimeArrayCheck---------

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get value of index: i

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x

! -------in writeStructAddress: x

! =======in writeStructAddress: a is a ptr or byRef========
	set	68, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l0
	ld	[%l0], %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

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

! -------in writeDerefAddress: a

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
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

! --------in  writeRunTimeArrayCheck: x

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	5, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck1
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	5, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck1:

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck1
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	5, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck1:

! --------end of writeRunTimeArrayCheck---------

! ------in writeConstantLiteral: 100
	set	100, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! x + 100

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and 100's value

! -------in getValue: x: null

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get value of index: i

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x

! -------in writeStructAddress: x

! -------in writeDerefAddress: a

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
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
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! ---------in intToFloat: 100 100

! =======in intToFloat: getAddress of 100

! --------in getAddressHelper: 100
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 100
	ld	[%l0], %f1

! =======in intToFloat: call itos 100
	fitos	%f1, %f1

! ---------end of intToFloat---------

! =======in writeBinaryExpr, do computation=========
	fadds	%f0, %f1, %f0

! =======in writeBinaryExpr, do store result=========
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-24, %l0
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


! -------writeUnaryExpr: i ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: i

! =======in writePost, step 1: load value to local1

! -------in getValue: i: null

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! =======in writePost, step 1.5: store original value 
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 1, %l3

! =======in writePost, step 3: store value 

! --------in getAddressHelper: i
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l3, [%l0]

! -------in writeWhileEnd------------
	ba	whileStart1
	nop


! ----------in writeCloseBlock-----------
whileEnd1:

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 32) & -8


! --------in writeGlobalStruct--------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeAddressOf: b

! --------in getAddressHelper: b
	set	b, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:a
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writePassParameter: a

! =====in writePassParameter, param a is byRef=======

! --------in getAddressHelper: a
	set	-24, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 28) & -8

