! 
! Generated Fri May 30 21:45:23 PDT 2014
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

static_good_y0:	.word 5

	.global ip
	
	.section ".bss"
	.align 4

ip:	.skip 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<good>>>>>>>>>>>>>>>>>
	.align 4
	.global good
good:
	set	SAVE.good, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: alloc param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ---------In writeStaticVariable--------------

! ---------in writeLocalVariableWOInit:yy

! ------------in writeIf: alloc
	set	68, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else0
	nop


! ---------in writeNewStmt------
	set	1, %o0
	set	4, %o1
	call	calloc
	nop


! --------in getAddressHelper: yy
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%o0, [%l0]

! ---------end 0f writeNewStmt------

! --------in writeReturnStmt---------

! --------in getAddressHelper: yy
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %i0
	ret
	restore

! ---------in writeElse---------
	ba	.endIf0
	nop

else0:

! ----------in writeCloseBlock-----------
.endIf0:

! ------in writeAddressOf: y

! --------in getAddressHelper: y
	set	static_good_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! --------in writeReturnStmt---------

! --------in getAddressHelper: y
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.good = -(92 + 12) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<bad>>>>>>>>>>>>>>>>>
	.align 4
	.global bad
bad:
	set	SAVE.bad, %g1
	save	%sp, %g1, %sp


! ---------in writeLocalVariableWOInit:z

! ------in writeAddressOf: z

! --------in getAddressHelper: z
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! --------in writeReturnStmt---------

! --------in getAddressHelper: z
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.bad = -(92 + 8) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<goodBad>>>>>>>>>>>>>>>>>
	.align 4
	.global goodBad
goodBad:
	set	SAVE.goodBad, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: zz param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: zz

! --------in getAddressHelper: zz
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
	add	%sp, -12, %l4
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

! ------in writeAddressOf: zz

! --------in getAddressHelper: zz
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! --------in writeReturnStmt---------

! --------in getAddressHelper: zz
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.goodBad = -(92 + 8) & -8


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeAddressOf: ip

! --------in getAddressHelper: ip
	set	ip, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: ip: null

! --------in getAddressHelper: ip
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:ipp
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: ipp

! --------in getAddressHelper: ipp
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
	add	%sp, -12, %l4
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

! ------in writeAddressOf: ipp

! --------in getAddressHelper: ipp
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: ipp

! --------in getAddressHelper: ipp
	set	-16, %l0
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
	add	%sp, -12, %l4
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

! ------in writeConstantLiteral: true
	set	1, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter--------
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	mov	%l1, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	good
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: result

! --------in getAddressHelper: result
	set	-28, %l0
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
	add	%sp, -12, %l4
	cmp	%l0, %l4
	blu	deallocatedStack3
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack3
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack3:

! ------in writeConstantLiteral: false
	set	0, %l1
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter--------
	set	-36, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	mov	%l1, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	good
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: result

! --------in getAddressHelper: result
	set	-40, %l0
	add	%fp, %l0, %l0

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
	add	%sp, -12, %l4
	cmp	%l0, %l4
	blu	deallocatedStack4
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack4
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack4:

! ----------writeFuncCall------------
	call	bad
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-48, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: result

! --------in getAddressHelper: result
	set	-48, %l0
	add	%fp, %l0, %l0

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
	add	%sp, -12, %l4
	cmp	%l0, %l4
	blu	deallocatedStack5
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack5
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack5:

! ------in writeAddressOf: ipp

! --------in getAddressHelper: ipp
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-56, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in writePassParameter--------
	set	-56, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	mov	%l1, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	goodBad
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-60, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! -------in writeDeallocatedStack--------

! -------in writeDerefAddress: result

! --------in getAddressHelper: result
	set	-60, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
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

! -------end of writeDerefAddress-------
	add	%sp, -12, %l4
	cmp	%l0, %l4
	blu	deallocatedStack6
	nop

	add	%sp, 92, %l4
	cmp	%l0, %l4
	bgu	deallocatedStack6
	nop

	set	.deallocatedStack, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

deallocatedStack6:

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 64) & -8

