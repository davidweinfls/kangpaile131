! 
! Generated Tue May 20 01:46:08 PDT 2014
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

	.global x
	
x:	.skip 4

	.global y
	
y:	.skip 4

	.global z
	
z:	.skip 4

	.section ".text"
	.align 4


! ---------In writeGLobalVariable--------------

! ---------In writeGLobalVariable--------------

! ---------In writeGLobalVariable--------------

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

! -------in getValue: 3: 3.0
	set	-4, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:localX
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 6
	set	6, %l1
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 6: 6.0
	set	-12, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:localY
	set	-16, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 9
	set	9, %l1
	set	-20, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 9: 9.0
	set	-20, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:localZ
	set	-24, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! ------in writeConstantLiteral: 12
	set	12, %l1
	set	-28, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! -------in getValue: 12: 12.0
	set	-28, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! ---------in writeLocalVariableWInit:localA
	set	-32, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]


! --------in writeBinaryExpr-------

! localX * localY

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get localX's value and localY's value

! -------in getValue: localX: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: localY: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	mov	%l1, %o0
	mov	%l2, %o1
	call	.mul
	nop

	mov	%o0, %l1

! =======in writeBinaryExpr, do store result=========
	set	-36, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! result * localZ

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get result's value and localZ's value

! -------in getValue: result: null
	set	-36, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: localZ: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	mov	%l1, %o0
	mov	%l2, %o1
	call	.mul
	nop

	mov	%o0, %l1

! =======in writeBinaryExpr, do store result=========
	set	-40, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: x  =  result

! -------in getValue: result: null
	set	-40, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	x, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! localZ / localX

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get localZ's value and localX's value

! -------in getValue: localZ: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: localX: null
	set	-8, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	mov	%l1, %o0
	mov	%l2, %o1
	call	.div
	nop

	mov	%o0, %l1

! =======in writeBinaryExpr, do store result=========
	set	-44, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! localY + result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get localY's value and result's value

! -------in getValue: localY: null
	set	-16, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: result: null
	set	-44, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-48, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: y  =  result

! -------in getValue: result: null
	set	-48, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	y, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! y - localZ

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get y's value and localZ's value

! -------in getValue: y: null
	set	y, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: localZ: null
	set	-24, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	sub	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-52, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! localA * y

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get localA's value and y's value

! -------in getValue: localA: null
	set	-32, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: y: null
	set	y, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	mov	%l1, %o0
	mov	%l2, %o1
	call	.mul
	nop

	mov	%o0, %l1

! =======in writeBinaryExpr, do store result=========
	set	-56, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! result - result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get result's value and result's value

! -------in getValue: result: null
	set	-52, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: result: null
	set	-56, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	sub	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-60, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! result + x

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get result's value and x's value

! -------in getValue: result: null
	set	-60, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: x: null
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	add	%l1, %l2, %l1

! =======in writeBinaryExpr, do store result=========
	set	-64, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! --------in writeBinaryExpr-------

! x * result

! =======in writeBinaryExpr: Not const folding=======

! =======in writeBinaryExpr: get x's value and result's value

! -------in getValue: x: null
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1

! -------in getValue: result: null
	set	-64, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l2

! =======in writeBinaryExpr, do computation=========
	mov	%l1, %o0
	mov	%l2, %o1
	call	.mul
	nop

	mov	%o0, %l1

! =======in writeBinaryExpr, do store result=========
	set	-68, %l0
	add	%fp, %l0, %l0
	st	%l1, [%l0]

! ----------in writeAssignExpr: z  =  result

! -------in getValue: result: null
	set	-68, %l0
	add	%fp, %l0, %l0
	ld	[%l0], %l1
	set	z, %l0
	add	%g0, %l0, %l0
	st	%l1, [%l0]

! ------------in writePrint---------------

! -------in getValue: x: null
	set	x, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: y: null
	set	y, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
	set	.intFmt, %o0
	mov	%l1, %o1
	call	printf
	nop


! ------------in writePrint---------------
	set	.endl, %o0
	call	printf
	nop


! ------------in writePrint---------------

! -------in getValue: z: null
	set	z, %l0
	add	%g0, %l0, %l0
	ld	[%l0], %l1
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

	SAVE.main = -(92 + 68) & -8

