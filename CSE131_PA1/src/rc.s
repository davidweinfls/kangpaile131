! 
! Generated Fri Jun 06 21:37:15 PDT 2014
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

	.global a
	
a:	.word 0

	.section ".text"
	.align 4


! --------in writeAnd, &&, check first operand--------
	set	0, %l3
	set	1, %l1
	cmp	%l1, %g0
	be	endAND0
	nop


! --------in writeBinaryExpr-------

! true && false

! =======in writeBinaryExpr: Const folding=======
endAND0:
	set	0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeAnd, &&, check first operand--------
	set	0, %l3

! --------in getAddressHelper: result
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND1
	nop


! --------in writeBinaryExpr-------

! result && true

! =======in writeBinaryExpr: Const folding=======
endAND1:
	set	0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	a, %l0
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

	SAVE.main = -(92 + 8) & -8

