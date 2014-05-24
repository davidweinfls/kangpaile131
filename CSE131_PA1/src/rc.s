! 
! Generated Sat May 24 16:35:31 PDT 2014
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

	.global bed
bed:	.skip 4
	
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


! -------in getValue: bed: null

! -------end of getValue------------

! ---------in writeLocalVariableWInit:cyclops
	set	-4, %l0
	add	%fp, %l0, %l0

! =======in writeAssignExpr, struct assign=======

! =======in writeAssignExpr, get var address, store in out0=======

! --------in getAddressHelper: cyclops
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o0

! =======in writeAssignExpr, get expr address, store in out1=======

! --------in getAddressHelper: bed
	set	bed, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %o1

! =======in writeAssignExpr, get exprType.size, store in out2=======
	set	4, %o2

! =======in writeAssignExpr, call memmove=======
	call	memmove, 0
	nop



! ------in writeConstantLiteral: true
	set	1, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ----------in writeAssignExpr: barC  =  true

! -------in getValue: true: 1.0

! --------in getAddressHelper: true
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: barC

! -------in writeStructAddress: barC
	set	bed, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------

! -------in getValue: barC: null

! --------in getAddressHelper: barC

! -------in writeStructAddress: barC
	set	bed, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool0
	nop

	set	.boolT, %o0

.printBool0:
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! =======in writeAnd, &&, check first operand=========
	set	0, %l3

! --------in getAddressHelper: barC

! -------in writeStructAddress: barC
	set	bed, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop


! ------in writeConstantLiteral: false
	set	0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! barC && false

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, &&, check second operand=========
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	cmp	%l1, %g0
	be	endAND0
	nop

	set	1, %l3
endAND0:

! =======in writeBinaryExpr, do store result=========
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ----------in writeAssignExpr: barC  =  result

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: barC

! -------in writeStructAddress: barC
	set	-4, %l0
	add	%fp, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------

! -------in getValue: barC: null

! --------in getAddressHelper: barC

! -------in writeStructAddress: barC
	set	-4, %l0
	add	%fp, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------
	set	.boolF, %o0
	cmp	%l1, %g0
	be	.printBool1
	nop

	set	.boolT, %o0

.printBool1:
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 16) & -8

