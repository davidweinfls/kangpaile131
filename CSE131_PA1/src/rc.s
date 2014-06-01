! 
! Generated Sat May 31 23:09:48 PDT 2014
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
	.align 4

	.section ".data"
.allocatedMemory:	.word	0
	.align 4

	.global x
	
	.global y
	
	.global z
	
	.section ".bss"
	.align 4

x:	.skip 4

y:	.skip 4

z:	.skip 4

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! ---------In writeGLobalVariable--------------

! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ---------in writeNewStmt------
	set	1, %o0
	set	4, %o1
	call	calloc
	nop


! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%o0, [%l0]
	set	.allocatedMemory, %l0
	ld	[%l0], %l1
	inc	%l1
	st	%l1, [%l0]

! ---------end 0f writeNewStmt------

! ---------in writeNewStmt------
	set	1, %o0
	set	4, %o1
	call	calloc
	nop


! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%o0, [%l0]
	set	.allocatedMemory, %l0
	ld	[%l0], %l1
	inc	%l1
	st	%l1, [%l0]

! ---------end 0f writeNewStmt------

! ---------in writeNewStmt------
	set	1, %o0
	set	4, %o1
	call	calloc
	nop


! --------in getAddressHelper: z
	set	z, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%o0, [%l0]
	set	.allocatedMemory, %l0
	ld	[%l0], %l1
	inc	%l1
	st	%l1, [%l0]

! ---------end 0f writeNewStmt------

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writeMemoryLeak-------
	set	.memoryLeakError, %o0
	set	.allocatedMemory, %l0
	ld	[%l0], %o1
	cmp	%o1, %g0
	be	._memleaklabel0
	nop

	call	printf
	nop

._memleaklabel0:

! -------end of writeMemoryLeak--------

! --------in writeReturnStmt---------
	set	0, %i0
	ret
	restore

! ---------in writeDeleteStmt------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ======in writeDeleteStmt, check nullPtrExcep=======
	set	0, %l4
	cmp	%l1, %l4
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
	mov	%l1, %o0
	call	free
	nop


! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	set	0, %l1
	st	%l1, [%l0]
	set	.allocatedMemory, %l0
	ld	[%l0], %l1
	dec	%l1
	st	%l1, [%l0]

! ---------end 0f writeDeleteStmt------

! -------in writeMemoryLeak-------
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

	SAVE.main = -(92 + 4) & -8

