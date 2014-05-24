! 
! Generated Fri May 23 22:08:09 PDT 2014
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

	.section ".bss"
	.align 4

x:	.skip 12
	
	.section ".text"
	.align 4


! --------in writeGlobalStruct--------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  3

! -------in getValue: 3: 3.0

! --------in getAddressHelper: 3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: a

! -------in writeStructAddress: a
	set	x, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a

! -------in writeStructAddress: a
	set	x, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

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

	SAVE.main = -(92 + 4) & -8

