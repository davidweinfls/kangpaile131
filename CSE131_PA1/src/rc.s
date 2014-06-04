! 
! Generated Tue Jun 03 22:06:07 PDT 2014
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


! ---------in writeLocalVariableWOInit:a

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: x

! --------in getAddressHelper: 1
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	2, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck0:

! --------in getAddressHelper: 1
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck0:

! --------end of writeRunTimeArrayCheck---------

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: x  =  5

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get value of index: 1

! --------in getAddressHelper: 1
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x

! -------in writeStructAddress: x
	set	-8, %l0
	add	%fp, %l0, %l0
	add	%l0, 0, %l0

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

! ---------in writeLocalVariableWOInit:b

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: y

! --------in getAddressHelper: 0
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	2, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck1
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck1:

! --------in getAddressHelper: 0
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck1
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck1:

! --------end of writeRunTimeArrayCheck---------

! ----------in writeAssignExpr: y  =  a

! =======in writeAssignExpr, struct assign=======

! =======in writeAssignExpr, get var address, store in out0=======

! --------in getAddressHelper: y

! ----------in writeArrayAddress: y

! =======in writeArrayAddress, get value of index: 0

! --------in getAddressHelper: 0
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :y and store in l4

! --------in getAddressHelper: y

! -------in writeStructAddress: y
	set	-32, %l0
	add	%fp, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	mov	%l5, %o0
	set	8, %o1
	call	.mul
	nop

	mov	%o0, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	mov	%l0, %l6

! =======in writeAssignExpr, get expr address, store in out1=======

! --------in getAddressHelper: a
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l6, %o0
	mov	%l0, %o1

! =======in writeAssignExpr, get exprType.size, store in out2=======
	set	8, %o2

! =======in writeAssignExpr, call memmove=======
	call	memmove, 0
	nop


! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: y

! --------in getAddressHelper: 0
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	2, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck2
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck2:

! --------in getAddressHelper: 0
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck2
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck2:

! --------end of writeRunTimeArrayCheck---------

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-44, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: x

! --------in getAddressHelper: 1
	set	-44, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	2, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck3
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck3:

! --------in getAddressHelper: 1
	set	-44, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck3
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	2, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck3:

! --------end of writeRunTimeArrayCheck---------

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get value of index: 1

! --------in getAddressHelper: 1
	set	-44, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x

! -------in writeStructAddress: x

! ----------in writeArrayAddress: y

! =======in writeArrayAddress, get value of index: 0

! --------in getAddressHelper: 0
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l6

! =======in writeArrayAddress, get address of var :y and store in l4

! --------in getAddressHelper: y

! -------in writeStructAddress: y
	set	-32, %l0
	add	%fp, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	mov	%l6, %o0
	set	8, %o1
	call	.mul
	nop

	mov	%o0, %l6

! =======in writeArrayAddress, base + offset
	add	%l4, %l6, %l0

! ---------end of writeArrayAddress--------
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
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

	SAVE.main = -(92 + 44) & -8

