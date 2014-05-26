! 
! Generated Mon May 26 15:11:36 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Attempt to dereference NULL pointer.\n"
	.align 4

	.section ".data"
	.align 4

	.section ".bss"
	.align 4

	.global gangster
gangster:	.skip 16000
	
	.global bee
bee:	.skip 4
	
	.section ".text"
	.align 4


! --------in writeGlobalStruct--------

! --------in writeGlobalStruct--------

! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 2431
	set	2431, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeAddressOf: dude

! --------in getAddressHelper: dude

! ----------in writeArrayAddress: dude

! =======in writeArrayAddress, get value of index: 2431

! --------in getAddressHelper: 2431
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l5

! =======in writeArrayAddress, get address of var :dude and store in l4

! --------in getAddressHelper: dude

! -------in writeStructAddress: dude
	set	gangster, %l0
	add	%g0, %l0, %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l4

! =======in writeArrayAddress, scale the offset
	sll	%l5, 2, %l5

! =======in writeArrayAddress, base + offset
	add	%l4, %l5, %l0

! ---------end of writeArrayAddress--------

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: dude: null

! --------in getAddressHelper: dude
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:dudette
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeAddressOf: bee

! --------in getAddressHelper: bee
	set	bee, %l0
	add	%g0, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: bee: null

! --------in getAddressHelper: bee
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:bird
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeAddressOf: dudette

! --------in getAddressHelper: dudette
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! ----------in writeAssignExpr: c  =  dudette

! -------in getValue: dudette: null

! --------in getAddressHelper: dudette
	set	-24, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! --------in getAddressHelper: c

! -------in writeStructAddress: c

! =======in writeStructAddress: bird is a ptr or byRef========
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! ----------end of writeAssignExpr--------

! ------------in writePrint---------------

! -------in getValue: c: null

! --------in getAddressHelper: c

! -------in writeDerefAddress: c

! --------in getAddressHelper: c

! -------in writeDerefAddress: c

! --------in getAddressHelper: c

! -------in writeStructAddress: c

! =======in writeStructAddress: bird is a ptr or byRef========
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel0
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel0:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel1
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel1:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------

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


! --------in writeOr, ||, check first operand--------
	set	1, %l3

! --------in getAddressHelper: c

! -------in writeDerefAddress: c

! --------in getAddressHelper: c

! -------in writeDerefAddress: c

! --------in getAddressHelper: c

! -------in writeStructAddress: c

! =======in writeStructAddress: bird is a ptr or byRef========
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l0
	add	%l0, 0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel2
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel2:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------

! --------end of getAddressHelper------------ 
	ld	[%l0], %l0

! ======in writeDerefAddress, check nullPtrExcep=======
	set	0, %l4
	cmp	%l0, %l4
	bne	ptrLabel3
	nop

	set	.NullPtrException, %o0
	call	printf
	nop

	set	1, %o0
	call	exit
	nop

ptrLabel3:

! ======end of check nullPtrExcep=======

! -------end of writeDerefAddress-------

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop


! ------in writeConstantLiteral: true
	set	1, %l1
	set	-44, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! --------in writeBinaryExpr-------

! c || true

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr, do computation=========

! =======in writeBinaryExpr, ||, check second operand=========
	set	-44, %l0
	add	%fp, %l0, %l0
	set	1, %l3
	ld	[%l0], %l1
	cmp	%l1, %g0
	bne	endOR0
	nop

	set	0, %l3
endOR0:

! =======in writeBinaryExpr, do store result=========
	set	-48, %l0
	add	%fp, %l0, %l0
	st	%l3, [%l0]

! ------------in writePrint---------------

! -------in getValue: result: null

! --------in getAddressHelper: result
	set	-48, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 48) & -8

