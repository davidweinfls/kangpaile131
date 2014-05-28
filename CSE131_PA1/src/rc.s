! 
! Generated Wed May 28 16:04:00 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
	.align 4

temp0:	.asciz "bar a = 5 = "
	.align 4
temp1:	.asciz "bar b = 5 = "
	.align 4
temp2:	.asciz "foo y = 5 = "
	.align 4
temp3:	.asciz "main j = 3 = "
	.align 4
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<bar>>>>>>>>>>>>>>>>>
	.align 4
	.global bar
bar:
	set	SAVE.bar, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: a param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------in writeParameter: b param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: a: null

! --------in getAddressHelper: a
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: b: null

! --------in getAddressHelper: b
	set	72, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: b  =  3

! =======in writeAssignExpr, varType is float=======

! =======in writeAssignExpr, convert right side int to float=======

! ---------in intToFloat: 3 3

! =======in intToFloat: getAddress of 3

! --------in getAddressHelper: 3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 3
	ld	[%l0], %f0

! =======in intToFloat: call itos 3
	fitos	%f0, %f0

! ---------end of intToFloat---------

! -------in getValue: 3: 3.0

! --------in getAddressHelper: 3
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: b
	set	72, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.bar = -(92 + 4) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: y param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! ------in writeConstantLiteral: 5
	set	5, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: y  =  5

! =======in writeAssignExpr, varType is float=======

! =======in writeAssignExpr, convert right side int to float=======

! ---------in intToFloat: 5 5

! =======in intToFloat: getAddress of 5

! --------in getAddressHelper: 5
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 5
	ld	[%l0], %f0

! =======in intToFloat: call itos 5
	fitos	%f0, %f0

! ---------end of intToFloat---------

! -------in getValue: 5: 5.0

! --------in getAddressHelper: 5
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: y
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	st	%f0, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %f0

! -------end of getValue------------
	call	printFloat
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! -------in writePassParameter--------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	ld	[%l0], %f0

! -------end of getValue------------
	st	%f0, [%l0]
	ld	[%l0], %o0

! -------end of writePassParameter--------

! -------in writePassParameter--------

! =====in writePassParameter, param y is byRef=======

! --------in getAddressHelper: y
	set	68, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0
	mov	%l0, %o1

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	bar
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.foo = -(92 + 8) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 8
	set	8, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ---------in intToFloat: 8 8

! =======in intToFloat: getAddress of 8

! --------in getAddressHelper: 8
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 

! =======in intToFloat: load value of 8
	ld	[%l0], %f0

! =======in intToFloat: call itos 8
	fitos	%f0, %f0

! ---------end of intToFloat---------

! ---------in writeLocalVariableWInit:j
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]


! -------in writePassParameter--------

! =====in writePassParameter, param j is byRef=======

! --------in getAddressHelper: j
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! -------end of writePassParameter--------

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! ------------in writePrint---------------
	set	temp3, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: j: null

! --------in getAddressHelper: j
	set	-16, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 16) & -8

