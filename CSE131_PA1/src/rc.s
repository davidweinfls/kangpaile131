! 
! Generated Fri May 23 13:22:04 PDT 2014
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

a:	.skip 12
	
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


! --------------in writeFuncClose--------------
	ret
	restore

	SAVE.main = -(92 + 0) & -8

