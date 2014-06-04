! 
! Generated Tue Jun 03 22:39:56 PDT 2014
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

temp0:	.asciz "int"
	.align 4
temp1:	.asciz "bool"
	.align 4
	.section ".data"
.allocatedMemory:	.word	0
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

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 0) & -8

foobool_1:
	set	SAVE.foobool_1, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: b param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeReturnStmt---------
	set	2, %i0
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foobool_1 = -(92 + 4) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter: a

! --------in getAddressHelper: 3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o0

! -------end of writePassParameter--------

! --------in writeOverloadFuncCall: foo
	call	foo
	nop


! --------end of writeOverloadFuncCall-------

! ------in writeConstantLiteral: false
	set	0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter: b

! --------in getAddressHelper: false
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	mov	%l1, %o0

! -------end of writePassParameter--------

! --------in writeOverloadFuncCall: foobool_1
	call	foobool_1
	nop

	set	-20, %l0
	add	%fp, %l0, %l0
	st	%o0, [%l0]

! --------end of writeOverloadFuncCall-------

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

	SAVE.main = -(92 + 20) & -8

