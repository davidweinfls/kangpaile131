! 
! Generated Sat May 24 01:20:35 PDT 2014
! 

	.section ".rodata"
.endl:	.asciz "\n"
.intFmt:	.asciz "%d"
.boolT:	.asciz "true"
.boolF:	.asciz "false"
.float_one:	.single 0r1
	.align 4

temp0:	.single 0r5.0
temp1:	.single 0r6.0
temp2:	.single 0r7.0
	.section ".data"
	.align 4

	.section ".text"
	.align 4


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<foo>>>>>>>>>>>>>>>>>
	.align 4
	.global foo
foo:
	set	SAVE.foo, %g1
	save	%sp, %g1, %sp


! -------in writeParameter: x param num: 0
	set	68, %l0
	add	%fp, %l0, %l0
	st	%i0, [%l0]

! -------in writeParameter: y param num: 1
	set	72, %l0
	add	%fp, %l0, %l0
	st	%i1, [%l0]

! -------in writeParameter: z param num: 2
	set	76, %l0
	add	%fp, %l0, %l0
	st	%i2, [%l0]

! ------------in writePrint---------------

! -------in getValue: x: null

! --------in getAddressHelper: x
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

! -------in getValue: y: null

! --------in getAddressHelper: y
	set	72, %l0
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

! -------in getValue: z: null

! --------in getAddressHelper: z
	set	76, %l0
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

	SAVE.foo = -(92 + 0) & -8


! in writeFuncDec
! <<<<<<<<<<<<<<<<<<<main>>>>>>>>>>>>>>>>>
	.align 4
	.global main
main:
	set	SAVE.main, %g1
	save	%sp, %g1, %sp


! ------in writeConstantLiteral: 5.0
	set	temp0, %l0
	ld	[%l0], %f0
	set	-4, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 6.0
	set	temp1, %l0
	ld	[%l0], %f0
	set	-8, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! ------in writeConstantLiteral: 7.0
	set	temp2, %l0
	ld	[%l0], %f0
	set	-12, %l0
	add	%fp, %l0, %l0
	st	%f0, [%l0]

! ------end of writeConstantLiteral-------

! -------in writePassParameter--------

! -------in getValue: 5.0: 5.0

! --------in getAddressHelper: 5.0
	set	-4, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	st	%f0, [%l0]
	ld	[%l0], %o0

! -------in writePassParameter--------

! -------in getValue: 6.0: 6.0

! --------in getAddressHelper: 6.0
	set	-8, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f1

! -------end of getValue------------
	st	%f1, [%l0]
	ld	[%l0], %o1

! -------in writePassParameter--------

! -------in getValue: 7.0: 7.0

! --------in getAddressHelper: 7.0
	set	-12, %l0
	add	%fp, %l0, %l0

! --------end of getAddressHelper------------ 
	ld	[%l0], %f0

! -------end of getValue------------
	st	%f0, [%l0]
	ld	[%l0], %o2

! ----------writeFuncCall------------
	call	foo
	nop


! ========writeFuncCall: get address of retSTO, store retValue in it ==========

! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 12) & -8

