! 
! Generated Wed May 14 14:32:31 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

temp0:	.single 0r2.0
	.section ".data"
	.align 4

	.global y
	
y:	.word 3

	.global z
	
z:	.single 0r1.0

	.section ".bss"
	.align 4

	.global x
	
x:	.skip 4

	.section ".text"
	.align 4

! --main--
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp

	set	1, %l1
	set	null, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]
	set	temp0, %l0
	ld	[%l0], %f0
	set	null, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]
	call	printFloat
	nop

	set	.endl, %o0
	call	printf
	nop

	ret
	restore

	SAVE.main = -(92 + 8) & -8

