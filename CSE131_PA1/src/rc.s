! 
! Generated Wed May 28 22:52:01 PDT 2014
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

	.global x
	
	.section ".bss"
	.align 4

x:	.skip 4

	.global y
	
y:	.skip 4

	.section ".text"
	.align 4

! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp

	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	y, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	z, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	ret
	restore

	SAVE.main = -(92 + 0) & -8

