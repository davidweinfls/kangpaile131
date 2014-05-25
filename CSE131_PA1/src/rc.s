! 
! Generated Sat May 24 22:44:39 PDT 2014
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

	.global x
	
	.global y
	
	.section ".bss"
	.align 4

x:	.skip 4

y:	.skip 4

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ----------in writeAssignExpr: x  =  y

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 0) & -8

