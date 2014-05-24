! 
! Generated Sat May 24 00:00:55 PDT 2014
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


! ---------in writeLocalVariableWOInit:struct1

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: a  =  5

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: a

! -------in writeStructAddress: a
	set	-8, %l0
	add	%fp, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------in writeConstantLiteral: 10
	set	10, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: b  =  10

! -------in getValue: 10: 10.0

! --------in getAddressHelper: 10
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: b

! -------in writeStructAddress: b
	set	-8, %l0
	add	%fp, %l0, %l0
	add	%l0, 4, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! -------in getValue: struct1: null

! -------end of getValue------------

! ---------in writeLocalVariableWInit:struct2
	set	-24, %l0
	add	%fp, %l0, %l0

! =======in writeAssignExpr, struct assign=======

! =======in writeAssignExpr, get var address, store in out0=======

! --------in getAddressHelper: struct2
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! =======in writeAssignExpr, get expr address, store in out1=======

! --------in getAddressHelper: struct1
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o1

! =======in writeAssignExpr, get exprType.size, store in out2=======
	set	8, %o2

! =======in writeAssignExpr, call memmove=======
	call	memmove, 0
	nop



! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a

! -------in writeStructAddress: a
	set	-24, %l0
	add	%fp, %l0, %l0
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


! ------------in writePrint---------------

! -------in getValue: b: null

! --------in getAddressHelper: b

! -------in writeStructAddress: b
	set	-24, %l0
	add	%fp, %l0, %l0
	add	%l0, 4, %l0

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

	SAVE.main = -(92 + 24) & -8

