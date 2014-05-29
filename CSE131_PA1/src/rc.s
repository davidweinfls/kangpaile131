! 
! Generated Thu May 29 00:24:25 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
.ArrayOutOfBounds:	.asciz "Index value of %d is outside legal range [0, %d).\n"
	.align 4

	.section ".data"
	.align 4

	.global x
	
	.section ".bss"
	.align 4

myArr:	.skip 12
	
x:	.skip 4

	.section ".text"
	.align 4

! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp

	set	47, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	x, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l0
	set	3, %l1
	cmp	%l0, %l1
	bl	arrayUpperBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	3, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayUpperBoundCheck0:
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l0
	set	0, %l1
	cmp	%l0, %l1
	bl	arrayLowerBoundCheck0
	nop

	set	.ArrayOutOfBounds, %o0
	mov	%l0, %o1
	set	3, %o2
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

arrayLowerBoundCheck0:
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l5
	set	myArr, %l0
	add	%g0, %l0, %l0
	mov	%l0, %l4
	sll	%l5, 2, %l5
	add	%l4, %l5, %l0
	ld	[%l0], %l1
	set	x, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]
	set	0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	0, %i0
	ret
	restore

	SAVE.main = -(92 + 8) & -8

