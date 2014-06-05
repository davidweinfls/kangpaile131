! 
! Generated Thu Jun 05 01:46:28 PDT 2014
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

temp0:	.single 0r4.44
temp1:	.single 0r3.33
temp2:	.asciz "Incompatible type float to binary operator %%, equivalent to int expected."
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


! ------in writeConstantLiteral: 4.44
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 4.44: 4.44

! --------in getAddressHelper: 4.44
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! ---------in writeLocalVariableWInit:a
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! ------in writeConstantLiteral: 3.33
	set	temp1, %l0
	ld	[%l0], %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! a % 3.33

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get a's value and 3.33's value

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! -------in getValue: 3.33: 3.33

! --------in getAddressHelper: 3.33
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f1

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	mov	%l1, %o0
	mov	%l2, %o1
	call	.rem
	nop

	mov	%o0, %l1

! =======in writeBinaryExpr, do store result=========
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writePrint---------------
	set	temp2, %o0
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

