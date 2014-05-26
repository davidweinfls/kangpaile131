! 
! Generated Sun May 25 21:29:47 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Cannot dereference a nullPointer.\n"
	.align 4

temp0:	.asciz "baz's sword: "
	.align 4
	.section ".data"
	.align 4

	.global baz
	
	.section ".bss"
	.align 4

	.global magic
magic:	.skip 4
	
baz:	.skip 4

	.section ".text"
	.align 4


! --------in writeGlobalStruct--------

! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 54
	set	54, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  54

! -------in getValue: 54: 54.0

! --------in getAddressHelper: 54
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: a

! -------in writeStructAddress: a
	set	magic, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeAddressOf: magic

! --------in getAddressHelper: magic
	set	magic, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! ----------in writeAssignExpr: baz  =  magic

! -------in getValue: magic: null

! --------in getAddressHelper: magic
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: baz
	set	baz, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a

! -------in writeStructAddress: a
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

	SAVE.main = -(92 + 8) & -8

