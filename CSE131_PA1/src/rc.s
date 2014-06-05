! 
! Generated Thu Jun 05 01:38:53 PDT 2014
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

	.global b
	
	.section ".bss"
	.align 4

b:	.skip 4

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo2>>>>>>>>>>>>>>>>>
	.align 4
	.global foo2
foo2:
	set	SAVE.foo2, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: param param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! --------in writeReturnStmt---------

! --------in getAddressHelper: param
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo2 = -(92 + 0) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! -------in writePassParameter: param

! =====in writePassParameter, param b is byRef=======

! --------in getAddressHelper: b
	set	b, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	foo2
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! ------in writeConstantLiteral: true
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: result  =  true

! -------in getValue: true: 1.0

! --------in getAddressHelper: true
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: result
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	b, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool0
	nop

	set	.boolT, %o0

.printBool0:
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

	SAVE.main = -(92 + 12) & -8

