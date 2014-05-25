! 
! Generated Sun May 25 01:58:54 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
.NullPtrException:	.asciz "Cannot dereference a nullPointer.\n"
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


! ------in writeConstantLiteral: 8233
	set	8233, %l1
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ------end of writeConstantLiteral-------

! -------in getValue: 8233: 8233.0

! --------in getAddressHelper: 8233
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:k
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeAddressOf: k

! --------in getAddressHelper: k
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	mov	%l0, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------end of writeAddressOf-------

! -------in getValue: k: null

! --------in getAddressHelper: k
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! ---------in writeLocalVariableWInit:p
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! -------writeUnaryExpr: p ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: p

! =======in writePost, step 1: load value to local1

! -------in getValue: p: null

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! =======in writePost, step 1.5: store original value 
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! =======in writePost, step 2: computation 
	add	%l1, 4, %l3

! =======in writePost, step 3: store value 

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l3, [%l0]

! -------writeUnaryExpr: p ++

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is ++, do nothing=========

! ----------writePost: p

! =======in writePost, step 1: load value to local1

! -------in getValue: p: null

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writePost, step 1.5: store original value 
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l2, [%l0]

! =======in writePost, step 2: computation 
	add	%l2, 4, %l3

! =======in writePost, step 3: store value 

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l3, [%l0]

! -------writeUnaryExpr: p --

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is --, do nothing=========

! ----------in writePre: p

! =======in writePre, step 1: load value to local1

! -------in getValue: p: null

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l1

! -------end of getValue------------

! =======in writePre, step 2: computation 
	sub	%l1, 4, %l1

! =======in writePre, step 3: store value 

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l1, [%l0]

! -------writeUnaryExpr: p --

! =======in writeUnaryExpr, non-const folding, computation=========

! =======in writeUnaryExpr, non-const folding, op is --, do nothing=========

! ----------in writePre: p

! =======in writePre, step 1: load value to local1

! -------in getValue: p: null

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %l2

! -------end of getValue------------

! =======in writePre, step 2: computation 
	sub	%l2, 4, %l2

! =======in writePre, step 3: store value 

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	st	%l2, [%l0]

! ------------in writePrint---------------

! -------in getValue: p: null

! --------in getAddressHelper: p

! -------in writeDerefAddress: p

! --------in getAddressHelper: p
	set	-16, %l0
	add	%fp, %l0, %l0

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

	SAVE.main = -(92 + 44) & -8

