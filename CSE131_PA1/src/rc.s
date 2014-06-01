! 
! Generated Sat May 31 18:59:30 PDT 2014
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
	.align 4

	.section ".data"
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


! -------in writeParameter: a param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------in writeConstantLiteral: 1000
	set	1000, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  1000

! -------in getValue: 1000: 1000.0

! --------in getAddressHelper: 1000
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 4) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 6
	set	6, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ---------in writeLocalVariableWOInit:array

! ------in writeConstantLiteral: 4
	set	4, %l1
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: array

! --------in getAddressHelper: 4
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	6, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	6, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck0:

! --------in getAddressHelper: 4
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	6, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck0:

! --------end of writeRunTimeArrayCheck---------

! ------in writeConstantLiteral: 999
	set	999, %l1
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: array  =  999

! -------in getValue: 999: 999.0

! --------in getAddressHelper: 999
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: array

! ----------in writeArrayAddress: array

! =======in writeArrayAddress, get value of index: 4

! --------in getAddressHelper: 4
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :array and store in l4

! --------in getAddressHelper: array
	set	-28, %l0
	add	%fp, %l0, %l0

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

! ------in writeConstantLiteral: 4
	set	4, %l1
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: array

! --------in getAddressHelper: 4
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	6, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck1
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	6, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck1:

! --------in getAddressHelper: 4
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck1
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	6, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck1:

! --------end of writeRunTimeArrayCheck---------

! -------in writePassParameter--------

! =====in writePassParameter, param array is byRef=======

! --------in getAddressHelper: array

! ----------in writeArrayAddress: array

! =======in writeArrayAddress, get value of index: 4

! --------in getAddressHelper: 4
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :array and store in l4

! --------in getAddressHelper: array
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! ------in writeConstantLiteral: 4
	set	4, %l1
	set	-44, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in  writeRunTimeArrayCheck: array

! --------in getAddressHelper: 4
	set	-44, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	6, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck2
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	6, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck2:

! --------in getAddressHelper: 4
	set	-44, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bge	arrayLowerBoundCheck2
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	6, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck2:

! --------end of writeRunTimeArrayCheck---------

! ------------in writePrint---------------

! -------in getValue: array: null

! --------in getAddressHelper: array

! ----------in writeArrayAddress: array

! =======in writeArrayAddress, get value of index: 4

! --------in getAddressHelper: 4
	set	-44, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :array and store in l4

! --------in getAddressHelper: array
	set	-28, %l0
	add	%fp, %l0, %l0

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


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 44) & -8

