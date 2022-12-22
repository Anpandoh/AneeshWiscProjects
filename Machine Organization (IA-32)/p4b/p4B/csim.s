	.file	"csim.c"
	.text
	.globl	b
	.bss
	.align 4
	.type	b, @object
	.size	b, 4
b:
	.zero	4
	.globl	s
	.align 4
	.type	s, @object
	.size	s, 4
s:
	.zero	4
	.globl	E
	.align 4
	.type	E, @object
	.size	E, 4
E:
	.zero	4
	.globl	B
	.align 4
	.type	B, @object
	.size	B, 4
B:
	.zero	4
	.globl	S
	.align 4
	.type	S, @object
	.size	S, 4
S:
	.zero	4
	.globl	hit_cnt
	.align 4
	.type	hit_cnt, @object
	.size	hit_cnt, 4
hit_cnt:
	.zero	4
	.globl	miss_cnt
	.align 4
	.type	miss_cnt, @object
	.size	miss_cnt, 4
miss_cnt:
	.zero	4
	.globl	evict_cnt
	.align 4
	.type	evict_cnt, @object
	.size	evict_cnt, 4
evict_cnt:
	.zero	4
	.globl	verbosity
	.align 4
	.type	verbosity, @object
	.size	verbosity, 4
verbosity:
	.zero	4
	.globl	cache
	.align 4
	.type	cache, @object
	.size	cache, 4
cache:
	.zero	4
	.text
	.globl	init_cache
	.type	init_cache, @function
init_cache:
.LFB6:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%esi
	pushl	%ebx
	subl	$48, %esp
	.cfi_offset 6, -12
	.cfi_offset 3, -16
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	s@GOTOFF(%ebx), %eax
	movl	%eax, -48(%ebp)
	fildl	-48(%ebp)
	fldl	.LC0@GOTOFF(%ebx)
	leal	-8(%esp), %esp
	fstpl	(%esp)
	leal	-8(%esp), %esp
	fstpl	(%esp)
	call	pow@PLT
	addl	$16, %esp
	fnstcw	-42(%ebp)
	movzwl	-42(%ebp), %eax
	orb	$12, %ah
	movw	%ax, -44(%ebp)
	fldcw	-44(%ebp)
	fistpl	-48(%ebp)
	fldcw	-42(%ebp)
	movl	-48(%ebp), %eax
	movl	%eax, S@GOTOFF(%ebx)
	movl	b@GOTOFF(%ebx), %eax
	movl	%eax, -48(%ebp)
	fildl	-48(%ebp)
	fldl	.LC0@GOTOFF(%ebx)
	leal	-8(%esp), %esp
	fstpl	(%esp)
	leal	-8(%esp), %esp
	fstpl	(%esp)
	call	pow@PLT
	addl	$16, %esp
	fnstcw	-42(%ebp)
	movzwl	-42(%ebp), %eax
	orb	$12, %ah
	movw	%ax, -44(%ebp)
	fldcw	-44(%ebp)
	fistpl	-48(%ebp)
	fldcw	-42(%ebp)
	movl	-48(%ebp), %eax
	movl	%eax, B@GOTOFF(%ebx)
	movl	S@GOTOFF(%ebx), %eax
	sall	$2, %eax
	subl	$12, %esp
	pushl	%eax
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, cache@GOTOFF(%ebx)
	movl	$0, -36(%ebp)
	jmp	.L2
.L3:
	movl	E@GOTOFF(%ebx), %eax
	sall	$4, %eax
	movl	cache@GOTOFF(%ebx), %ecx
	movl	-36(%ebp), %edx
	sall	$2, %edx
	leal	(%ecx,%edx), %esi
	subl	$12, %esp
	pushl	%eax
	call	malloc@PLT
	addl	$16, %esp
	movl	%eax, (%esi)
	addl	$1, -36(%ebp)
.L2:
	movl	S@GOTOFF(%ebx), %eax
	cmpl	%eax, -36(%ebp)
	jl	.L3
	movl	$0, -32(%ebp)
	jmp	.L4
.L7:
	movl	$0, -28(%ebp)
	jmp	.L5
.L6:
	movb	$0, -24(%ebp)
	movl	$0, -20(%ebp)
	movl	$0, -16(%ebp)
	movl	$0, -12(%ebp)
	movl	cache@GOTOFF(%ebx), %edx
	movl	-32(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %edx
	movl	-28(%ebp), %eax
	sall	$4, %eax
	addl	%edx, %eax
	movl	-24(%ebp), %edx
	movl	%edx, (%eax)
	movl	-20(%ebp), %edx
	movl	%edx, 4(%eax)
	movl	-16(%ebp), %edx
	movl	%edx, 8(%eax)
	movl	-12(%ebp), %edx
	movl	%edx, 12(%eax)
	addl	$1, -32(%ebp)
.L5:
	movl	E@GOTOFF(%ebx), %eax
	cmpl	%eax, -28(%ebp)
	jl	.L6
	addl	$1, -32(%ebp)
.L4:
	movl	S@GOTOFF(%ebx), %eax
	cmpl	%eax, -32(%ebp)
	jl	.L7
	nop
	nop
	leal	-8(%ebp), %esp
	popl	%ebx
	.cfi_restore 3
	popl	%esi
	.cfi_restore 6
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE6:
	.size	init_cache, .-init_cache
	.globl	free_cache
	.type	free_cache, @function
free_cache:
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
	movl	$0, -12(%ebp)
	jmp	.L9
.L10:
	movl	cache@GOTOFF(%ebx), %edx
	movl	-12(%ebp), %eax
	sall	$2, %eax
	addl	%edx, %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	free@PLT
	addl	$16, %esp
	addl	$1, -12(%ebp)
.L9:
	movl	S@GOTOFF(%ebx), %eax
	cmpl	%eax, -12(%ebp)
	jl	.L10
	movl	cache@GOTOFF(%ebx), %eax
	subl	$12, %esp
	pushl	%eax
	call	free@PLT
	addl	$16, %esp
	nop
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE7:
	.size	free_cache, .-free_cache
	.globl	access_data
	.type	access_data, @function
access_data:
.LFB8:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	subl	$8, %esp
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
	movl	8(%ebp), %eax
	movl	%eax, -8(%ebp)
	movl	12(%ebp), %eax
	movl	%eax, -4(%ebp)
	nop
	leave
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE8:
	.size	access_data, .-access_data
	.section	.rodata
.LC1:
	.string	"r"
.LC2:
	.string	"%s: %s\n"
.LC3:
	.string	"%llx,%u"
.LC4:
	.string	"%c %llx,%u "
	.text
	.globl	replay_trace
	.type	replay_trace, @function
replay_trace:
.LFB9:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%esi
	pushl	%ebx
	subl	$1040, %esp
	.cfi_offset 6, -12
	.cfi_offset 3, -16
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	8(%ebp), %eax
	movl	%eax, -1036(%ebp)
	movl	%gs:20, %eax
	movl	%eax, -12(%ebp)
	xorl	%eax, %eax
	movl	$0, -1024(%ebp)
	movl	$0, -1020(%ebp)
	movl	$0, -1032(%ebp)
	subl	$8, %esp
	leal	.LC1@GOTOFF(%ebx), %eax
	pushl	%eax
	pushl	-1036(%ebp)
	call	fopen@PLT
	addl	$16, %esp
	movl	%eax, -1028(%ebp)
	cmpl	$0, -1028(%ebp)
	jne	.L14
	call	__errno_location@PLT
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	strerror@PLT
	addl	$16, %esp
	movl	stderr@GOT(%ebx), %edx
	movl	(%edx), %edx
	pushl	%eax
	pushl	-1036(%ebp)
	leal	.LC2@GOTOFF(%ebx), %eax
	pushl	%eax
	pushl	%edx
	call	fprintf@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L20:
	movzbl	-1011(%ebp), %eax
	cmpb	$83, %al
	je	.L15
	movzbl	-1011(%ebp), %eax
	cmpb	$76, %al
	je	.L15
	movzbl	-1011(%ebp), %eax
	cmpb	$77, %al
	jne	.L14
.L15:
	leal	-1012(%ebp), %eax
	addl	$3, %eax
	leal	-1032(%ebp), %edx
	pushl	%edx
	leal	-1024(%ebp), %edx
	pushl	%edx
	leal	.LC3@GOTOFF(%ebx), %edx
	pushl	%edx
	pushl	%eax
	call	__isoc99_sscanf@PLT
	addl	$16, %esp
	movl	verbosity@GOTOFF(%ebx), %eax
	testl	%eax, %eax
	je	.L16
	movl	-1032(%ebp), %esi
	movl	-1024(%ebp), %eax
	movl	-1020(%ebp), %edx
	movzbl	-1011(%ebp), %ecx
	movsbl	%cl, %ecx
	subl	$12, %esp
	pushl	%esi
	pushl	%edx
	pushl	%eax
	pushl	%ecx
	leal	.LC4@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$32, %esp
.L16:
	movzbl	-1011(%ebp), %eax
	cmpb	$76, %al
	je	.L17
	movzbl	-1011(%ebp), %eax
	cmpb	$83, %al
	jne	.L18
.L17:
	movl	-1024(%ebp), %eax
	movl	-1020(%ebp), %edx
	subl	$8, %esp
	pushl	%edx
	pushl	%eax
	call	access_data
	addl	$16, %esp
	jmp	.L19
.L18:
	movzbl	-1011(%ebp), %eax
	cmpb	$77, %al
	jne	.L19
	movl	-1024(%ebp), %eax
	movl	-1020(%ebp), %edx
	subl	$8, %esp
	pushl	%edx
	pushl	%eax
	call	access_data
	addl	$16, %esp
	movl	-1024(%ebp), %eax
	movl	-1020(%ebp), %edx
	subl	$8, %esp
	pushl	%edx
	pushl	%eax
	call	access_data
	addl	$16, %esp
.L19:
	movl	verbosity@GOTOFF(%ebx), %eax
	testl	%eax, %eax
	je	.L14
	subl	$12, %esp
	pushl	$10
	call	putchar@PLT
	addl	$16, %esp
.L14:
	subl	$4, %esp
	pushl	-1028(%ebp)
	pushl	$1000
	leal	-1012(%ebp), %eax
	pushl	%eax
	call	fgets@PLT
	addl	$16, %esp
	testl	%eax, %eax
	jne	.L20
	subl	$12, %esp
	pushl	-1028(%ebp)
	call	fclose@PLT
	addl	$16, %esp
	nop
	movl	-12(%ebp), %eax
	subl	%gs:20, %eax
	je	.L21
	call	__stack_chk_fail_local
.L21:
	leal	-8(%ebp), %esp
	popl	%ebx
	.cfi_restore 3
	popl	%esi
	.cfi_restore 6
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE9:
	.size	replay_trace, .-replay_trace
	.section	.rodata
	.align 4
.LC5:
	.string	"Usage: %s [-hv] -s <num> -E <num> -b <num> -t <file>\n"
.LC6:
	.string	"Options:"
	.align 4
.LC7:
	.string	"  -h         Print this help message."
	.align 4
.LC8:
	.string	"  -v         Optional verbose flag."
	.align 4
.LC9:
	.string	"  -s <num>   Number of s bits for set index."
	.align 4
.LC10:
	.string	"  -E <num>   Number of lines per set."
	.align 4
.LC11:
	.string	"  -b <num>   Number of b bits for block offsets."
.LC12:
	.string	"  -t <file>  Trace file."
.LC13:
	.string	"\nExamples:"
	.align 4
.LC14:
	.string	"  linux>  %s -s 4 -E 1 -b 4 -t traces/yi.trace\n"
	.align 4
.LC15:
	.string	"  linux>  %s -v -s 8 -E 2 -b 4 -t traces/yi.trace\n"
	.text
	.globl	print_usage
	.type	print_usage, @function
print_usage:
.LFB10:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$4, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	8(%ebp), %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC5@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC6@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC7@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC8@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC9@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC10@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC11@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC12@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	subl	$12, %esp
	leal	.LC13@GOTOFF(%ebx), %eax
	pushl	%eax
	call	puts@PLT
	addl	$16, %esp
	movl	8(%ebp), %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC14@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	movl	8(%ebp), %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC15@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	$0
	call	exit@PLT
	.cfi_endproc
.LFE10:
	.size	print_usage, .-print_usage
	.section	.rodata
	.align 4
.LC16:
	.string	"hits:%d misses:%d evictions:%d\n"
.LC17:
	.string	"w"
.LC18:
	.string	".csim_results"
.LC19:
	.string	"csim.c"
.LC20:
	.string	"output_fp"
.LC21:
	.string	"%d %d %d\n"
	.text
	.globl	print_summary
	.type	print_summary, @function
print_summary:
.LFB11:
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
	pushl	16(%ebp)
	pushl	12(%ebp)
	pushl	8(%ebp)
	leal	.LC16@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$8, %esp
	leal	.LC17@GOTOFF(%ebx), %eax
	pushl	%eax
	leal	.LC18@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fopen@PLT
	addl	$16, %esp
	movl	%eax, -12(%ebp)
	cmpl	$0, -12(%ebp)
	jne	.L24
	leal	__PRETTY_FUNCTION__.0@GOTOFF(%ebx), %eax
	pushl	%eax
	pushl	$303
	leal	.LC19@GOTOFF(%ebx), %eax
	pushl	%eax
	leal	.LC20@GOTOFF(%ebx), %eax
	pushl	%eax
	call	__assert_fail@PLT
.L24:
	subl	$12, %esp
	pushl	16(%ebp)
	pushl	12(%ebp)
	pushl	8(%ebp)
	leal	.LC21@GOTOFF(%ebx), %eax
	pushl	%eax
	pushl	-12(%ebp)
	call	fprintf@PLT
	addl	$32, %esp
	subl	$12, %esp
	pushl	-12(%ebp)
	call	fclose@PLT
	addl	$16, %esp
	nop
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE11:
	.size	print_summary, .-print_summary
	.section	.rodata
.LC22:
	.string	"s:E:b:t:vh"
	.align 4
.LC23:
	.string	"%s: Missing required command line argument\n"
	.text
	.globl	main
	.type	main, @function
main:
.LFB12:
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
	subl	$28, %esp
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	%ecx, %esi
	movl	$0, -28(%ebp)
	jmp	.L26
.L35:
	movsbl	-29(%ebp), %eax
	cmpl	$69, %eax
	je	.L27
	cmpl	$69, %eax
	jl	.L28
	cmpl	$118, %eax
	jg	.L28
	cmpl	$98, %eax
	jl	.L28
	subl	$98, %eax
	cmpl	$20, %eax
	ja	.L28
	sall	$2, %eax
	movl	.L30@GOTOFF(%eax,%ebx), %eax
	addl	%ebx, %eax
	jmp	*%eax
	.section	.rodata
	.align 4
	.align 4
.L30:
	.long	.L34@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L33@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L28@GOTOFF
	.long	.L32@GOTOFF
	.long	.L31@GOTOFF
	.long	.L28@GOTOFF
	.long	.L29@GOTOFF
	.text
.L34:
	movl	optarg@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi@PLT
	addl	$16, %esp
	movl	%eax, b@GOTOFF(%ebx)
	jmp	.L26
.L27:
	movl	optarg@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi@PLT
	addl	$16, %esp
	movl	%eax, E@GOTOFF(%ebx)
	jmp	.L26
.L33:
	subl	$12, %esp
	pushl	4(%esi)
	call	print_usage
	addl	$16, %esp
	subl	$12, %esp
	pushl	$0
	call	exit@PLT
.L32:
	movl	optarg@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	atoi@PLT
	addl	$16, %esp
	movl	%eax, s@GOTOFF(%ebx)
	jmp	.L26
.L31:
	movl	optarg@GOT(%ebx), %eax
	movl	(%eax), %eax
	movl	%eax, -28(%ebp)
	jmp	.L26
.L29:
	movl	$1, verbosity@GOTOFF(%ebx)
	jmp	.L26
.L28:
	subl	$12, %esp
	pushl	4(%esi)
	call	print_usage
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L26:
	subl	$4, %esp
	leal	.LC22@GOTOFF(%ebx), %eax
	pushl	%eax
	pushl	4(%esi)
	pushl	(%esi)
	call	getopt@PLT
	addl	$16, %esp
	movb	%al, -29(%ebp)
	cmpb	$-1, -29(%ebp)
	jne	.L35
	movl	s@GOTOFF(%ebx), %eax
	testl	%eax, %eax
	je	.L36
	movl	E@GOTOFF(%ebx), %eax
	testl	%eax, %eax
	je	.L36
	movl	b@GOTOFF(%ebx), %eax
	testl	%eax, %eax
	je	.L36
	cmpl	$0, -28(%ebp)
	jne	.L37
.L36:
	movl	4(%esi), %eax
	movl	(%eax), %eax
	subl	$8, %esp
	pushl	%eax
	leal	.LC23@GOTOFF(%ebx), %eax
	pushl	%eax
	call	printf@PLT
	addl	$16, %esp
	subl	$12, %esp
	pushl	4(%esi)
	call	print_usage
	addl	$16, %esp
	subl	$12, %esp
	pushl	$1
	call	exit@PLT
.L37:
	call	init_cache
	subl	$12, %esp
	pushl	-28(%ebp)
	call	replay_trace
	addl	$16, %esp
	call	free_cache
	movl	evict_cnt@GOTOFF(%ebx), %ecx
	movl	miss_cnt@GOTOFF(%ebx), %edx
	movl	hit_cnt@GOTOFF(%ebx), %eax
	subl	$4, %esp
	pushl	%ecx
	pushl	%edx
	pushl	%eax
	call	print_summary
	addl	$16, %esp
	movl	$0, %eax
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
.LFE12:
	.size	main, .-main
	.section	.rodata
	.align 4
	.type	__PRETTY_FUNCTION__.0, @object
	.size	__PRETTY_FUNCTION__.0, 14
__PRETTY_FUNCTION__.0:
	.string	"print_summary"
	.align 8
.LC0:
	.long	0
	.long	1073741824
	.section	.text.__x86.get_pc_thunk.ax,"axG",@progbits,__x86.get_pc_thunk.ax,comdat
	.globl	__x86.get_pc_thunk.ax
	.hidden	__x86.get_pc_thunk.ax
	.type	__x86.get_pc_thunk.ax, @function
__x86.get_pc_thunk.ax:
.LFB13:
	.cfi_startproc
	movl	(%esp), %eax
	ret
	.cfi_endproc
.LFE13:
	.section	.text.__x86.get_pc_thunk.bx,"axG",@progbits,__x86.get_pc_thunk.bx,comdat
	.globl	__x86.get_pc_thunk.bx
	.hidden	__x86.get_pc_thunk.bx
	.type	__x86.get_pc_thunk.bx, @function
__x86.get_pc_thunk.bx:
.LFB14:
	.cfi_startproc
	movl	(%esp), %ebx
	ret
	.cfi_endproc
.LFE14:
	.hidden	__stack_chk_fail_local
	.ident	"GCC: (Ubuntu 11.3.0-1ubuntu1~22.04) 11.3.0"
	.section	.note.GNU-stack,"",@progbits
