! 
! Generated Sun May 25 00:16:14 PDT 2014
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

	.global y
	
	.section ".bss"
	.align 4

	.global x
x:	.skip 4
	
y:	.skip 4

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


! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  5

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
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

! ------in writeAddressOf: x

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! ----------in writeAssignExpr: y  =  x

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! -------in getValue: a: null

! --------in getAddressHelper: a

! -------in writeStructAddress: a

! =======in writeStructAddress: y is a ptr or byRef========
	set	y, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:z
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------------in writePrint---------------

! -------in getValue: z: null

! --------in getAddressHelper: z
	set	-12, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 12) & -8

