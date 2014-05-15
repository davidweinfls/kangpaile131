! 
! Generated Thu May 15 01:31:03 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

temp0:	.asciz "a is: "
	.align 4
temp1:	.asciz "b is: "
	.align 4
	.section ".data"
	.align 4

static_main_b1:	.word 1

	.section ".bss"
	.align 4

static_main_a0:	.skip 4

	.section ".text"
	.align 4

! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp

	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	temp0, %o0
	call	printf
	nop

	set	static_main_a0, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop

	set	.endl, %o0
	call	printf
	nop

	set	temp1, %o0
	call	printf
	nop

	set	static_main_b1, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool0
	nop

	set	.boolT, %o0

.printBool0:
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop

	ret
	restore

	SAVE.main = -(92 + 4) & -8

