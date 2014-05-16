! 
! Generated Thu May 15 22:03:26 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

	.global x
x:	.word 5

temp0:	.asciz "x is: "
	.align 4
const_main_a0:	.word 8

const_main_b1:	.word 2

temp1:	.asciz "a is: "
	.align 4
temp2:	.asciz "b is: "
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! ------------in writeConst: x

! in writeFuncDec
! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp

	set	temp0, %o0
	call	printf
	nop

	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! ------in writeConst: 8
	set	8, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeConst: a

! ------in writeConst: 2
	set	2, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeConst: b
	set	temp1, %o0
	call	printf
	nop

	set	const_main_a0, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop

	set	temp2, %o0
	call	printf
	nop

	set	const_main_b1, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop

	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 8) & -8

