! 
! Generated Sat May 24 03:42:42 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

	.global x
x:	.word 0

temp0:	.asciz "YES!"
	.align 4
temp1:	.asciz "GOT YOU!"
	.align 4
temp2:	.asciz "YES!"
	.align 4
temp3:	.asciz "YES!"
	.align 4
temp4:	.asciz "GOT YOU!"
	.align 4
temp5:	.asciz "GOT YOU!"
	.align 4
temp6:	.asciz "YES!"
	.align 4
temp7:	.asciz "GOT YOU!"
	.align 4
temp8:	.asciz "YES!"
	.align 4
temp9:	.asciz "YES!"
	.align 4
temp10:	.asciz "GOT YOU!"
	.align 4
temp11:	.asciz "YES!"
	.align 4
temp12:	.asciz "GOT YOU!"
	.align 4
temp13:	.asciz "GOT YOU!"
	.align 4
temp14:	.asciz "YES!"
	.align 4
temp15:	.asciz "NO!"
	.align 4
temp16:	.asciz "YES!"
	.align 4
temp17:	.asciz "YES!"
	.align 4
temp18:	.asciz "GOT YOU!"
	.align 4
temp19:	.asciz "YES!"
	.align 4
temp20:	.asciz "GOT YOU!"
	.align 4
temp21:	.asciz "YES!"
	.align 4
temp22:	.asciz "GOT YOU!"
	.align 4
temp23:	.asciz "YES!"
	.align 4
temp24:	.asciz "YES!"
	.align 4
temp25:	.asciz "GOT YOU!"
	.align 4
temp26:	.asciz "YES!"
	.align 4
temp27:	.asciz "GOT YOU!"
	.align 4
temp28:	.asciz "GOT YOU!"
	.align 4
temp29:	.asciz "YES!"
	.align 4
temp30:	.asciz "NO!"
	.align 4
temp31:	.asciz "YES!"
	.align 4
	.section ".data"
	.align 4

	.global y
	
y:	.word 1

static_main_y0:	.word 1

	.section ".text"
	.align 4


! ------------in writeConstVar: x

! ---------In writeGLobalVariable--------------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 0: 0.0

! --------in getAddressHelper: 0
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:x
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ---------In writeStaticVariable--------------

! ------in writeConstantLiteral: 2
	set	2, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writeIf: 2
	set	2, %l1
	cmp	%l1, %g0
	be	else0
	nop


! ------------in writePrint---------------
	set	temp0, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf0
	nop

else0:

! ----------in writeCloseBlock-----------
.endIf0:

! ------in writeConstantLiteral: 0
	set	0, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writeIf: 0
	set	0, %l1
	cmp	%l1, %g0
	be	else1
	nop


! ------------in writePrint---------------
	set	temp1, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf1
	nop

else1:

! ----------in writeCloseBlock-----------
.endIf1:

! ------in writeConstantLiteral: 10
	set	10, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------writeUnaryExpr: 10 -
	set	-10, %l1
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------end of writeUnary----------

! ------------in writeIf: 10
	set	-10, %l1
	cmp	%l1, %g0
	be	else2
	nop


! ------------in writePrint---------------
	set	temp2, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf2
	nop

else2:

! ----------in writeCloseBlock-----------
.endIf2:

! ------in writeConstantLiteral: true
	set	1, %l1
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writeIf: true
	set	1, %l1
	cmp	%l1, %g0
	be	else3
	nop


! ------------in writePrint---------------
	set	temp3, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf3
	nop

else3:

! ----------in writeCloseBlock-----------
.endIf3:

! ------in writeConstantLiteral: false
	set	0, %l1
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------------in writeIf: false
	set	0, %l1
	cmp	%l1, %g0
	be	else4
	nop


! ------------in writePrint---------------
	set	temp4, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf4
	nop

else4:

! ----------in writeCloseBlock-----------
.endIf4:

! ------------in writeIf: x
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else5
	nop


! ------------in writePrint---------------
	set	temp5, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf5
	nop

else5:

! ----------in writeCloseBlock-----------
.endIf5:

! ------------in writeIf: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else6
	nop


! ------------in writePrint---------------
	set	temp6, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf6
	nop

else6:

! ----------in writeCloseBlock-----------
.endIf6:

! ------------in writeIf: x
	set	0, %l1
	cmp	%l1, %g0
	be	else7
	nop


! ------------in writePrint---------------
	set	temp7, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf7
	nop

else7:

! ----------in writeCloseBlock-----------
.endIf7:

! ------------in writeIf: y
	set	y, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else8
	nop


! ------------in writePrint---------------
	set	temp8, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf8
	nop

else8:

! ----------in writeCloseBlock-----------
.endIf8:

! ------in writeConstantLiteral: 1
	set	1, %l1
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-44, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! 1 + 3

! =======in writeBinaryExpr: Const folding=======
	set	4, %l1
	set	-48, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf: result
	set	4, %l1
	cmp	%l1, %g0
	be	else9
	nop


! ------------in writePrint---------------
	set	temp9, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf9
	nop

else9:

! ----------in writeCloseBlock-----------
.endIf9:

! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-52, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 3
	set	3, %l1
	set	-56, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! 3 - 3

! =======in writeBinaryExpr: Const folding=======
	set	0, %l1
	set	-60, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf: result
	set	0, %l1
	cmp	%l1, %g0
	be	else10
	nop


! ------------in writePrint---------------
	set	temp10, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf10
	nop

else10:

! ----------in writeCloseBlock-----------
.endIf10:

! --------in writeBinaryExpr-------

! x + y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-64, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf: result
	set	-64, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else11
	nop


! ------------in writePrint---------------
	set	temp11, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf11
	nop

else11:

! ----------in writeCloseBlock-----------
.endIf11:

! --------in writeBinaryExpr-------

! x + x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-68, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf: result
	set	-68, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else12
	nop


! ------------in writePrint---------------
	set	temp12, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf12
	nop

else12:

! ----------in writeCloseBlock-----------
.endIf12:

! --------in writeBinaryExpr-------

! y - y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get y's value and y's value

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, do computation=========
	sub	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-72, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf: result
	set	-72, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else13
	nop


! ------------in writePrint---------------
	set	temp13, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf13
	nop

else13:

! ----------in writeCloseBlock-----------
.endIf13:

! --------in writeBinaryExpr-------

! x < y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bge	compOp0
	nop

	set	1, %l3
compOp0:
	set	-76, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-76, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else14
	nop


! ------------in writePrint---------------
	set	temp14, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf14
	nop

else14:

! ----------in writeCloseBlock-----------
.endIf14:

! --------in writeBinaryExpr-------

! x > y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	ble	compOp1
	nop

	set	1, %l3
compOp1:
	set	-80, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-80, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else15
	nop


! ------------in writePrint---------------
	set	temp15, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf15
	nop

else15:

! ----------in writeCloseBlock-----------
.endIf15:

! --------in writeBinaryExpr-------

! x >= x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bl	compOp2
	nop

	set	1, %l3
compOp2:
	set	-84, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-84, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else16
	nop


! ------------in writePrint---------------
	set	temp16, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf16
	nop

else16:

! ----------in writeCloseBlock-----------
.endIf16:

! --------in writeBinaryExpr-------

! y <= y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get y's value and y's value

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bg	compOp3
	nop

	set	1, %l3
compOp3:
	set	-88, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-88, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else17
	nop


! ------------in writePrint---------------
	set	temp17, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf17
	nop

else17:

! ----------in writeCloseBlock-----------
.endIf17:

! --------in writeBinaryExpr-------

! x >= y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bl	compOp4
	nop

	set	1, %l3
compOp4:
	set	-92, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-92, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else18
	nop


! ------------in writePrint---------------
	set	temp18, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf18
	nop

else18:

! ----------in writeCloseBlock-----------
.endIf18:

! --------in writeBinaryExpr-------

! x == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp5
	nop

	set	1, %l3
compOp5:
	set	-96, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-96, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else19
	nop


! ------------in writePrint---------------
	set	temp19, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf19
	nop

else19:

! ----------in writeCloseBlock-----------
.endIf19:

! --------in writeBinaryExpr-------

! x == y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp6
	nop

	set	1, %l3
compOp6:
	set	-100, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-100, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else20
	nop


! ------------in writePrint---------------
	set	temp20, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf20
	nop

else20:

! ----------in writeCloseBlock-----------
.endIf20:

! --------in writeBinaryExpr-------

! x != y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	be	compOp7
	nop

	set	1, %l3
compOp7:
	set	-104, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-104, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else21
	nop


! ------------in writePrint---------------
	set	temp21, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf21
	nop

else21:

! ----------in writeCloseBlock-----------
.endIf21:

! --------in writeBinaryExpr-------

! x != x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	be	compOp8
	nop

	set	1, %l3
compOp8:
	set	-108, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-108, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else22
	nop


! ------------in writePrint---------------
	set	temp22, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf22
	nop

else22:

! ----------in writeCloseBlock-----------
.endIf22:

! ------in writeConstantLiteral: true
	set	1, %l1
	set	-112, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! =======in writeAnd, &&, check first operand=========
	set	0, %l3
	set	-112, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop


! ------in writeConstantLiteral: true
	set	1, %l1
	set	-116, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! true && true

! =======in writeBinaryExpr: Const folding=======
endAND0:
	set	1, %l1
	set	-120, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------------in writeIf: result
	set	1, %l1
	cmp	%l1, %g0
	be	else23
	nop


! ------------in writePrint---------------
	set	temp23, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf23
	nop

else23:

! ----------in writeCloseBlock-----------
.endIf23:

! --------in writeBinaryExpr-------

! x == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp9
	nop

	set	1, %l3
compOp9:
	set	-124, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! =======in writeAnd, &&, check first operand=========
	set	0, %l3
	set	-124, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND1
	nop


! --------in writeBinaryExpr-------

! y == y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get y's value and y's value

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp10
	nop

	set	1, %l3
compOp10:
	set	-128, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result && result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, &&, check second operand=========
	set	-128, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND1
	nop

	set	1, %l3
endAND1:

! =======in writeBinaryExpr, do store result=========
	set	-132, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-132, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else24
	nop


! ------------in writePrint---------------
	set	temp24, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf24
	nop

else24:

! ----------in writeCloseBlock-----------
.endIf24:

! --------in writeBinaryExpr-------

! x == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp11
	nop

	set	1, %l3
compOp11:
	set	-136, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! =======in writeAnd, &&, check first operand=========
	set	0, %l3
	set	-136, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND2
	nop


! --------in writeBinaryExpr-------

! x == y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp12
	nop

	set	1, %l3
compOp12:
	set	-140, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result && result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, &&, check second operand=========
	set	-140, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND2
	nop

	set	1, %l3
endAND2:

! =======in writeBinaryExpr, do store result=========
	set	-144, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-144, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else25
	nop


! ------------in writePrint---------------
	set	temp25, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf25
	nop

else25:

! ----------in writeCloseBlock-----------
.endIf25:

! --------in writeBinaryExpr-------

! x == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp13
	nop

	set	1, %l3
compOp13:
	set	-148, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! =======in writeOr, ||, check first operand=========
	set	1, %l3
	set	-148, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop


! --------in writeBinaryExpr-------

! x == y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp14
	nop

	set	1, %l3
compOp14:
	set	-152, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result || result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, ||, check second operand=========
	set	-152, %l0
	add	%fp, %l0, %l0
	set	1, %l3
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop

	set	0, %l3
endOR0:

! =======in writeBinaryExpr, do store result=========
	set	-156, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-156, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else26
	nop


! ------------in writePrint---------------
	set	temp26, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf26
	nop

else26:

! ----------in writeCloseBlock-----------
.endIf26:

! --------in writeBinaryExpr-------

! y == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get y's value and x's value

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	y, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp15
	nop

	set	1, %l3
compOp15:
	set	-160, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! =======in writeOr, ||, check first operand=========
	set	1, %l3
	set	-160, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR1
	nop


! --------in writeBinaryExpr-------

! y == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get y's value and x's value

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp16
	nop

	set	1, %l3
compOp16:
	set	-164, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result || result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, ||, check second operand=========
	set	-164, %l0
	add	%fp, %l0, %l0
	set	1, %l3
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR1
	nop

	set	0, %l3
endOR1:

! =======in writeBinaryExpr, do store result=========
	set	-168, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writeIf: result
	set	-168, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else27
	nop


! ------------in writePrint---------------
	set	temp27, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf27
	nop

else27:

! ----------in writeCloseBlock-----------
.endIf27:

! ------in writeConstantLiteral: true
	set	1, %l1
	set	-172, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------writeUnaryExpr: true !
	set	0, %l1
	set	-176, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------end of writeUnary----------

! ------------in writeIf: false
	set	0, %l1
	cmp	%l1, %g0
	be	else28
	nop


! ------------in writePrint---------------
	set	temp28, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf28
	nop

else28:

! ----------in writeCloseBlock-----------
.endIf28:

! ------in writeConstantLiteral: false
	set	0, %l1
	set	-180, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------writeUnaryExpr: false !
	set	1, %l1
	set	-184, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ---------end of writeUnary----------

! ------------in writeIf: true
	set	1, %l1
	cmp	%l1, %g0
	be	else29
	nop


! ------------in writePrint---------------
	set	temp29, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf29
	nop

else29:

! ----------in writeCloseBlock-----------
.endIf29:

! --------in writeBinaryExpr-------

! x == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp17
	nop

	set	1, %l3
compOp17:
	set	-188, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! =======in writeOr, ||, check first operand=========
	set	1, %l3
	set	-188, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR2
	nop


! --------in writeBinaryExpr-------

! x == y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp18
	nop

	set	1, %l3
compOp18:
	set	-192, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result || result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, ||, check second operand=========
	set	-192, %l0
	add	%fp, %l0, %l0
	set	1, %l3
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR2
	nop

	set	0, %l3
endOR2:

! =======in writeBinaryExpr, do store result=========
	set	-196, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! -------writeUnaryExpr: result !

! =======in writeUnaryExpr, non-const folding, computation=========

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-196, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	xor	%l1, 1, %l1

! =======in writeUnaryExpr, result get address=========
	set	-200, %l0
	add	%fp, %l0, %l0

! =======in writeUnaryExpr, non-const folding, store value=========
	st	%l1, [%l0]

! ---------end of writeUnary----------

! ------------in writeIf: result
	set	-200, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else30
	nop


! ------------in writePrint---------------
	set	temp30, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf30
	nop

else30:

! ----------in writeCloseBlock-----------
.endIf30:

! --------in writeBinaryExpr-------

! x == x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and x's value

! -------in getValue: x: 0.0

! --------in getAddressHelper: x
	set	x, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp19
	nop

	set	1, %l3
compOp19:
	set	-204, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! =======in writeAnd, &&, check first operand=========
	set	0, %l3
	set	-204, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND3
	nop


! --------in writeBinaryExpr-------

! x == y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and y's value

! -------in getValue: x: null

! --------in getAddressHelper: x
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	static_main_y0, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writeBinaryExpr, non comparsionOP=========
	set	0, %l3

! =======in writeBinaryExpr, compare two operands=========
	cmp	%l1, %l2
	bne	compOp20
	nop

	set	1, %l3
compOp20:
	set	-208, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! --------in writeBinaryExpr-------

! result && result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, &&, check second operand=========
	set	-208, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND3
	nop

	set	1, %l3
endAND3:

! =======in writeBinaryExpr, do store result=========
	set	-212, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! -------writeUnaryExpr: result !

! =======in writeUnaryExpr, non-const folding, computation=========

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-212, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	xor	%l1, 1, %l1

! =======in writeUnaryExpr, result get address=========
	set	-216, %l0
	add	%fp, %l0, %l0

! =======in writeUnaryExpr, non-const folding, store value=========
	st	%l1, [%l0]

! ---------end of writeUnary----------

! ------------in writeIf: result
	set	-216, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	else31
	nop


! ------------in writePrint---------------
	set	temp31, %o0
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ---------in writeElse---------
	ba	.endIf31
	nop

else31:

! ----------in writeCloseBlock-----------
.endIf31:

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 216) & -8

