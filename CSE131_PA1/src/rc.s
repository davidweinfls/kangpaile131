! 
! Generated Wed May 14 17:50:34 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
	.align 4

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

	set	z, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %f0
	call	printFloat
	nop

	set	.endl, %o0
	call	printf
	nop

	ret
	restore

	SAVE.main = -(92 + 0) & -8

