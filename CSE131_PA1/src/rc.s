! 
! Generated Wed May 28 15:23:00 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
	.align 4

	.section ".data"
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


! ---------in writeLocalVariableWOInit:p

! ---------in writeNewStmt------
	set	1, %o0
	set	4, %o1
	call	calloc
	nop


! --------in getAddressHelper: p
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%o0, [%l0]

! ---------end 0f writeNewStmt------

! ------------in writePrint---------------

! -------in getValue: p: null

! --------in getAddressHelper: p

! -------in writeDerefAddress: p

! --------in getAddressHelper: p
	set	-4, %l0
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


! ---------in writeDeleteStmt------

! -------in getValue: p: null

! --------in getAddressHelper: p
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ======in writeDeleteStmt, check nullPtrExcep=======
	set	0, %l4
	cmp	%l1, %l4
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
	mov	%l1, %o0
	call	free
	nop


! ---------end 0f writeDeleteStmt------

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 8) & -8

