! 
! Generated Sun May 18 02:30:42 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

	.section ".data"
	.align 4

	.global a
	
a:	.word 1

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! in writeFuncDec
! --foo--
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! ------in writeConstValeu: true
	set	1, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in writeExpr: true: 1.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:t
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! --------in writeReturnStmt---------
	ret
	restore

! --------------in writeFuncClose--------------

	SAVE.foo = -(92 + 8) & -8

