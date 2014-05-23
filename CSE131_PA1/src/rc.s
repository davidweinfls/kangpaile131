! 
! Generated Fri May 23 01:16:37 PDT 2014
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

	.section ".text"
	.align 4


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

! ---------in writeLocalVariableWOInit:x

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: x  =  5

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 1

! --------in getAddressHelper: 1
	set	-20, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 6
	set	6, %l1
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: x  =  6

! -------in getValue: 6: 6.0

! --------in getAddressHelper: 6
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 0

! --------in getAddressHelper: 0
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 1

! --------in getAddressHelper: 1
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

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


! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x

! ----------in writeArrayAddress: x

! =======in writeArrayAddress, get address of var :x and store in l4

! --------in getAddressHelper: x
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 0

! --------in getAddressHelper: 0
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

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

	SAVE.main = -(92 + 40) & -8

