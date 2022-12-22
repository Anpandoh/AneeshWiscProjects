	.file	"check_board.c"
	.text
	.globl	DELIM
	.section	.rodata
.LC0:
	.string	","
	.section	.data.rel.local,"aw"
	.align 4
	.type	DELIM, @object
	.size	DELIM, 4
DELIM:
	.long	.LC0
	.section	.rodata
.LC1:
	.string	"Error reading the input file."
	.text
	.globl	get_board_size
	.type	get_board_size, @function
get_board_size:
.LFB6:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$36, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	8(%ebp), %eax
	movl	%eax, -28(%ebp)
	movl	12(%ebp), %eax
	movl	%eax, -32(%ebp)
	movl	%gs:20, %eax
	movl	%eax, -12(%ebp)
	xorl	%eax, %eax
	movl	$0, -24(%ebp)
	movl	$0, -20(%ebp)
	subl	$4, %esp
	pushl	-28(%ebp)
	leal	-20(%ebp), %eax
	pushl	%eax
	leal	-24(%ebp), %eax
	pushl	%eax
	call	getline@PLT
	addl	$16, %esp
	cmpl	$-1, %eax
	jne	.L2
	subl	$12, %esp
	leal	.LC1@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	movl	-24(%ebp), %eax
	subl	$12, %esp
	pushl	%eax
	call	free@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L2:
	movl	$0, -16(%ebp)
	movl	DELIM@GOTOFF(%ebx), %edx
	movl	-24(%ebp), %eax
	subl	$8, %esp
	pushl	%edx
	pushl	%eax
	call	strtok@PLT
	addl	$16, %esp
	movl	%eax, -16(%ebp)
	subl	$12, %esp
	pushl	-16(%ebp)
	call	atoi@PLT
	addl	$16, %esp
	movl	-32(%ebp), %edx
	movl	%eax, (%edx)
	movl	-24(%ebp), %eax
	subl	$12, %esp
	pushl	%eax
	call	free@PLT
	addl	$16, %esp
	movl	$0, -24(%ebp)
	nop
	movl	-12(%ebp), %eax
	subl	%gs:20, %eax
	je	.L3
	call	__stack_chk_fail_local
.L3:
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE6:
	.size	get_board_size, .-get_board_size
	.section	.rodata
.LC2:
	.string	"%d  "
.LC3:
	.string	"%d\n"
.LC4:
	.string	"\n"
	.text
	.globl	valid_board
	.type	valid_board, @function
valid_board:
.LFB7:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$20, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	$0, -24(%ebp)
	jmp	.L5
.L13:
	movl	12(%ebp), %eax
	sall	$2, %eax
	subl	$12, %esp
	pushl	%eax
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, -12(%ebp)
	movl	$0, -20(%ebp)
	jmp	.L6
.L10:
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-20(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC2@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-20(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	testl	%eax, %eax
	je	.L7
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-20(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	addl	$1073741823, %eax
	leal	0(,%eax,4), %edx
	movl	-12(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	cmpl	$1, %eax
	jne	.L7
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-20(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC3@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	-12(%ebp)
	call	free@PLT
	addl	$16, %esp
	movl	$0, -12(%ebp)
	movl	$0, %eax
	jmp	.L8
.L7:
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-20(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	testl	%eax, %eax
	je	.L9
	movl	-24(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	8(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-20(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	addl	$1073741823, %eax
	leal	0(,%eax,4), %edx
	movl	-12(%ebp), %eax
	addl	%edx, %eax
	movl	$1, (%eax)
.L9:
	addl	$1, -20(%ebp)
.L6:
	movl	-20(%ebp), %eax
	cmpl	12(%ebp), %eax
	jl	.L10
	movl	$0, -16(%ebp)
	jmp	.L11
.L12:
	movl	-16(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-12(%ebp), %eax
	addl	%edx, %eax
	movl	$0, (%eax)
	addl	$1, -16(%ebp)
.L11:
	movl	-16(%ebp), %eax
	cmpl	12(%ebp), %eax
	jl	.L12
	subl	$12, %esp
	pushl	-12(%ebp)
	call	free@PLT
	addl	$16, %esp
	movl	$0, -12(%ebp)
	subl	$12, %esp
	leal	.LC4@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	addl	$1, -24(%ebp)
.L5:
	movl	-24(%ebp), %eax
	cmpl	12(%ebp), %eax
	jl	.L13
	movl	$1, %eax
.L8:
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE7:
	.size	valid_board, .-valid_board
	.section	.rodata
.LC5:
	.string	"Only need 1 CLA argument."
.LC6:
	.string	"r"
.LC7:
	.string	"Can't open file for reading."
	.align 4
.LC8:
	.string	"Error while reading line %i of the file.\n"
.LC9:
	.string	"valid"
.LC10:
	.string	"invalid"
.LC11:
	.string	"Error while closing the file."
	.text
	.globl	main
	.type	main, @function
main:
.LFB8:
	.cfi_startproc
	leal	4(%esp), %ecx
	.cfi_def_cfa 1, 0
	andl	$-16, %esp
	pushl	-4(%ecx)
	pushl	%ebp
	movl	%esp, %ebp
	.cfi_escape 0x10,0x5,0x2,0x75,0
	pushl	%esi
	pushl	%ebx
	pushl	%ecx
	.cfi_escape 0xf,0x3,0x75,0x74,0x6
	.cfi_escape 0x10,0x6,0x2,0x75,0x7c
	.cfi_escape 0x10,0x3,0x2,0x75,0x78
	subl	$76, %esp
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	%ecx, %eax
	movl	4(%eax), %edx
	movl	%edx, -76(%ebp)
	movl	%gs:20, %edx
	movl	%edx, -28(%ebp)
	xorl	%edx, %edx
	cmpl	$2, (%eax)
	je	.L15
	subl	$12, %esp
	leal	.LC5@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L15:
	movl	-76(%ebp), %eax
	addl	$4, %eax
	movl	(%eax), %eax
	subl	$8, %esp
	leal	.LC6@GOTOFF(%ebx), %edx
	pushl	%edx
	pushl	%eax
	call	fopen@PLT
	addl	$16, %esp
	movl	%eax, -36(%ebp)
	cmpl	$0, -36(%ebp)
	jne	.L16
	subl	$12, %esp
	leal	.LC7@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L16:
	subl	$8, %esp
	leal	-64(%ebp), %eax
	pushl	%eax
	pushl	-36(%ebp)
	call	get_board_size
	addl	$16, %esp
	movl	-64(%ebp), %eax
	sall	$2, %eax
	subl	$12, %esp
	pushl	%eax
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, -32(%ebp)
	movl	$0, -60(%ebp)
	movl	$0, -56(%ebp)
	movl	$0, -52(%ebp)
	movl	$0, -48(%ebp)
	jmp	.L17
.L21:
	subl	$4, %esp
	pushl	-36(%ebp)
	leal	-56(%ebp), %eax
	pushl	%eax
	leal	-60(%ebp), %eax
	pushl	%eax
	call	getline@PLT
	addl	$16, %esp
	cmpl	$-1, %eax
	jne	.L18
	movl	-48(%ebp), %eax
	addl	$2, %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC8@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L18:
	movl	-64(%ebp), %eax
	sall	$2, %eax
	movl	-48(%ebp), %edx
	leal	0(,%edx,4), %ecx
	movl	-32(%ebp), %edx
	leal	(%ecx,%edx), %esi
	subl	$12, %esp
	pushl	%eax
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, (%esi)
	movl	DELIM@GOTOFF(%ebx), %edx
	movl	-60(%ebp), %eax
	subl	$8, %esp
	pushl	%edx
	pushl	%eax
	call	strtok@PLT
	addl	$16, %esp
	movl	%eax, -52(%ebp)
	movl	$0, -44(%ebp)
	jmp	.L19
.L20:
	movl	-48(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-32(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-44(%ebp), %eax
	sall	$2, %eax
	leal	(%edx,%eax), %esi
	subl	$12, %esp
	pushl	-52(%ebp)
	call	atoi@PLT
	addl	$16, %esp
	movl	%eax, (%esi)
	movl	DELIM@GOTOFF(%ebx), %eax
	subl	$8, %esp
	pushl	%eax
	pushl	$0
	call	strtok@PLT
	addl	$16, %esp
	movl	%eax, -52(%ebp)
	addl	$1, -44(%ebp)
.L19:
	movl	-64(%ebp), %eax
	cmpl	%eax, -44(%ebp)
	jl	.L20
	addl	$1, -48(%ebp)
.L17:
	movl	-64(%ebp), %eax
	cmpl	%eax, -48(%ebp)
	jl	.L21
	movl	-64(%ebp), %eax
	subl	$8, %esp
	pushl	%eax
	pushl	-32(%ebp)
	call	valid_board
	addl	$16, %esp
	testl	%eax, %eax
	je	.L22
	subl	$12, %esp
	leal	.LC9@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	jmp	.L23
.L22:
	subl	$12, %esp
	leal	.LC10@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
.L23:
	movl	$0, -40(%ebp)
	jmp	.L24
.L25:
	movl	-40(%ebp), %eax
	leal	0(,%eax,4), %edx
	movl	-32(%ebp), %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	free@PLT
	addl	$16, %esp
	addl	$1, -40(%ebp)
.L24:
	movl	-64(%ebp), %eax
	cmpl	%eax, -40(%ebp)
	jl	.L25
	subl	$12, %esp
	pushl	-32(%ebp)
	call	free@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	-36(%ebp)
	call	fclose@PLT
	addl	$16, %esp
	testl	%eax, %eax
	je	.L26
	subl	$12, %esp
	leal	.LC11@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L26:
	movl	$0, %eax
	movl	-28(%ebp), %edx
	subl	%gs:20, %edx
	je	.L28
	call	__stack_chk_fail_local
.L28:
	leal	-12(%ebp), %esp
	popl	%ecx
	.cfi_restore 1
	.cfi_def_cfa 1, 0
	popl	%ebx
	.cfi_restore 3
	popl	%esi
	.cfi_restore 6
	popl	%ebp
	.cfi_restore 5
	leal	-4(%ecx), %esp
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE8:
	.size	main, .-main
	.section	.text.__x86.get_pc_thunk.bx,"axG",@progbits,__x86.get_pc_thunk.bx,comdat
	.globl	__x86.get_pc_thunk.bx
	.hidden	__x86.get_pc_thunk.bx
	.type	__x86.get_pc_thunk.bx, @function
__x86.get_pc_thunk.bx:
.LFB9:
	.cfi_startproc
	movl	(%esp), %ebx
	ret
	.cfi_endproc
.LFE9:
	.hidden	__stack_chk_fail_local
	.ident	"GCC: (Ubuntu 11.2.0-19ubuntu1) 11.2.0"
	.section	.note.GNU-stack,"",@progbits
