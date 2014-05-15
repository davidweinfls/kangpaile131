! 
! Generated Thu May 15 00:48:08 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

temp0:	.asciz "a is: "
	.align 4
	.section ".data"
	.align 4

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
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

	set	temp0, %o0
	call	printf
	nop

	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop

	ret
	restore

	SAVE.main = -(92 + 8) & -8

