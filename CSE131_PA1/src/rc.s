! 
! Generated Fri May 23 22:44:54 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r3.5
	.section ".data"
	.align 4

	.section ".bss"
	.align 4

x:	.skip 8
	
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

! ---------in writeLocalVariableWOInit:f

! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 3.5
	set	temp0, %l0
	ld	[%l0], %f0
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  3.5

! =======in writeAssignExpr, varType is float=======

! -------in getValue: 3.5: 3.5

! --------in getAddressHelper: 3.5
	set	-36, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------

! --------in getAddressHelper: a

! -------in writeStructAddress: a

! ----------in writeArrayAddress: f

! =======in writeArrayAddress, get address of var :f and store in l4

! --------in getAddressHelper: f
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 2

! --------in getAddressHelper: 2
	set	-32, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, scale the offset
	mov	%l5, %o0
	set	8, %o1
	call	.mul
	nop

	mov	%o0, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a

! -------in writeStructAddress: a

! ----------in writeArrayAddress: f

! =======in writeArrayAddress, get address of var :f and store in l4

! --------in getAddressHelper: f
	set	-28, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, get value of index: 2

! --------in getAddressHelper: 2
	set	-40, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, scale the offset
	mov	%l5, %o0
	set	8, %o1
	call	.mul
	nop

	mov	%o0, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 40) & -8

