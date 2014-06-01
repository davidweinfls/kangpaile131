! 
! Generated Sat May 31 17:48:35 PDT 2014
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
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeTypeCast-----------

! ========in writeTypeCast, int to bool=========

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	0, %l3
	cmp	%l1, %g0
	be	.typeCastBool0
	nop

	set	1, %l3

.typeCastBool0:

! --------in getAddressHelper: 5
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l3, [%l0]

! ----------end of writeTypeCast----------

! ----------in writeTypeCast-----------

! ========in writeTypeCast, do regular case=========

! ========in writeTypeCast, get oldSTO value=========

! -------in getValue: 5: 1.0

! --------in getAddressHelper: 5
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! ========in writeTypeCast, get newSTO address=========

! --------in getAddressHelper: 5
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! ========in writeTypeCast, store old value to new sto=========
	st	%l2, [%l0]

! ----------end of writeTypeCast----------

! ------------in writePrint---------------

! -------in getValue: 5: 1.0

! --------in getAddressHelper: 5
	set	-12, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 12) & -8

