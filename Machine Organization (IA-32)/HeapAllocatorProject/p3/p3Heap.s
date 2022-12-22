	.file	"p3Heap.c"
	.text
	.globl	malloc
	.type	malloc, @function
malloc:
.LFB6:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
	movl	$0, %eax
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE6:
	.size	malloc, .-malloc
	.globl	heap_start
	.bss
	.align 4
	.type	heap_start, @object
	.size	heap_start, 4
heap_start:
	.zero	4
	.globl	alloc_size
	.align 4
	.type	alloc_size, @object
	.size	alloc_size, 4
alloc_size:
	.zero	4
	.text
	.globl	balloc
	.type	balloc, @function
balloc:
.LFB7:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
	movl	$0, %eax
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE7:
	.size	balloc, .-balloc
	.globl	bfree
	.type	bfree, @function
bfree:
.LFB8:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
	movl	$-1, %eax
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE8:
	.size	bfree, .-bfree
	.globl	coalesce
	.type	coalesce, @function
coalesce:
.LFB9:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	call	__x86.get_pc_thunk.ax
	addl	$_GLOBAL_OFFSET_TABLE_, %eax
	movl	$0, %eax
	popl	%ebp
	.cfi_restore 5
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE9:
	.size	coalesce, .-coalesce
	.section	.rodata
	.align 4
.LC0:
	.string	"Error:mem.c: InitHeap has allocated space during a previous call\n"
	.align 4
.LC1:
	.string	"Error:mem.c: Requested block size is not positive\n"
.LC2:
	.string	"/dev/zero"
	.align 4
.LC3:
	.string	"Error:mem.c: Cannot open /dev/zero\n"
	.align 4
.LC4:
	.string	"Error:mem.c: mmap cannot allocate space\n"
	.text
	.globl	init_heap
	.type	init_heap, @function
init_heap:
.LFB10:
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
	movl	allocated_once.0@GOTOFF(%ebx), %eax
	testl	%eax, %eax
	je	.L10
	movl	stderr@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$65
	pushl	$1
	leal	.LC0@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	$-1, %eax
	jmp	.L11
.L10:
	cmpl	$0, 8(%ebp)
	jg	.L12
	movl	stderr@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$50
	pushl	$1
	leal	.LC1@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	$-1, %eax
	jmp	.L11
.L12:
	call	getpagesize@PLT
	movl	%eax, -32(%ebp)
	movl	8(%ebp), %eax
	cltd
	idivl	-32(%ebp)
	movl	%edx, -28(%ebp)
	movl	-32(%ebp), %eax
	subl	-28(%ebp), %eax
	cltd
	idivl	-32(%ebp)
	movl	%edx, -28(%ebp)
	movl	8(%ebp), %edx
	movl	-28(%ebp), %eax
	addl	%edx, %eax
	movl	%eax, alloc_size@GOTOFF(%ebx)
	subl	$8, %esp
	pushl	$2
	leal	.LC2@GOTOFF(%ebx), %eax
	pushl	%eax
	call	open@PLT
	addl	$16, %esp
	movl	%eax, -24(%ebp)
	cmpl	$-1, -24(%ebp)
	jne	.L13
	movl	stderr@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$35
	pushl	$1
	leal	.LC3@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	$-1, %eax
	jmp	.L11
.L13:
	movl	alloc_size@GOTOFF(%ebx), %eax
	subl	$8, %esp
	pushl	$0
	pushl	-24(%ebp)
	pushl	$2
	pushl	$3
	pushl	%eax
	pushl	$0
	call	mmap@PLT
	addl	$32, %esp
	movl	%eax, -20(%ebp)
	cmpl	$-1, -20(%ebp)
	jne	.L14
	movl	stderr@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$40
	pushl	$1
	leal	.LC4@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	$0, allocated_once.0@GOTOFF(%ebx)
	movl	$-1, %eax
	jmp	.L11
.L14:
	movl	$1, allocated_once.0@GOTOFF(%ebx)
	movl	alloc_size@GOTOFF(%ebx), %eax
	subl	$8, %eax
	movl	%eax, alloc_size@GOTOFF(%ebx)
	movl	-20(%ebp), %eax
	addl	$4, %eax
	movl	%eax, heap_start@GOTOFF(%ebx)
	movl	heap_start@GOTOFF(%ebx), %edx
	movl	alloc_size@GOTOFF(%ebx), %eax
	addl	%edx, %eax
	movl	%eax, -16(%ebp)
	movl	-16(%ebp), %eax
	movl	$1, (%eax)
	movl	heap_start@GOTOFF(%ebx), %eax
	movl	alloc_size@GOTOFF(%ebx), %edx
	movl	%edx, (%eax)
	movl	heap_start@GOTOFF(%ebx), %eax
	movl	(%eax), %edx
	movl	heap_start@GOTOFF(%ebx), %eax
	addl	$2, %edx
	movl	%edx, (%eax)
	movl	heap_start@GOTOFF(%ebx), %edx
	movl	alloc_size@GOTOFF(%ebx), %eax
	subl	$4, %eax
	addl	%edx, %eax
	movl	%eax, -12(%ebp)
	movl	alloc_size@GOTOFF(%ebx), %edx
	movl	-12(%ebp), %eax
	movl	%edx, (%eax)
	movl	$0, %eax
.L11:
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE10:
	.size	init_heap, .-init_heap
	.section	.rodata
	.align 4
.LC5:
	.string	"*********************************** Block List **********************************\n"
	.align 4
.LC6:
	.string	"No.\tStatus\tPrev\tt_Begin\t\tt_End\t\tt_Size\n"
	.align 4
.LC7:
	.string	"---------------------------------------------------------------------------------\n"
.LC8:
	.string	"%d\t%s\t%s\t0x%08lx\t0x%08lx\t%4i\n"
	.align 4
.LC9:
	.string	"*********************************************************************************\n"
.LC10:
	.string	"Total used size = %4d\n"
.LC11:
	.string	"Total free size = %4d\n"
.LC12:
	.string	"Total size      = %4d\n"
	.text
	.globl	disp_heap
	.type	disp_heap, @function
disp_heap:
.LFB11:
	.cfi_startproc
	pushl	%ebp
	.cfi_def_cfa_offset 8
	.cfi_offset 5, -8
	movl	%esp, %ebp
	.cfi_def_cfa_register 5
	pushl	%ebx
	subl	$52, %esp
	.cfi_offset 3, -12
	call	__x86.get_pc_thunk.bx
	addl	$_GLOBAL_OFFSET_TABLE_, %ebx
	movl	%gs:20, %eax
	movl	%eax, -12(%ebp)
	xorl	%eax, %eax
	movl	$0, -32(%ebp)
	movl	$0, -28(%ebp)
	movl	heap_start@GOTOFF(%ebx), %eax
	movl	%eax, -48(%ebp)
	movl	$1, -56(%ebp)
	movl	$0, -44(%ebp)
	movl	$0, -40(%ebp)
	movl	$-1, -36(%ebp)
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$82
	pushl	$1
	leal	.LC5@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$39
	pushl	$1
	leal	.LC6@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$82
	pushl	$1
	leal	.LC7@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	jmp	.L16
.L23:
	movl	-48(%ebp), %eax
	movl	%eax, -32(%ebp)
	movl	-48(%ebp), %eax
	movl	(%eax), %eax
	movl	%eax, -52(%ebp)
	movl	-52(%ebp), %eax
	andl	$1, %eax
	testl	%eax, %eax
	je	.L17
	movl	$1869376609, -24(%ebp)
	movw	$99, -20(%ebp)
	movl	$1, -36(%ebp)
	subl	$1, -52(%ebp)
	jmp	.L18
.L17:
	movl	$1162170950, -24(%ebp)
	movw	$32, -20(%ebp)
	movl	$0, -36(%ebp)
.L18:
	movl	-52(%ebp), %eax
	andl	$2, %eax
	testl	%eax, %eax
	je	.L19
	movl	$1869376609, -18(%ebp)
	movw	$99, -14(%ebp)
	subl	$2, -52(%ebp)
	jmp	.L20
.L19:
	movl	$1162170950, -18(%ebp)
	movw	$32, -14(%ebp)
.L20:
	cmpl	$0, -36(%ebp)
	je	.L21
	movl	-52(%ebp), %eax
	addl	%eax, -44(%ebp)
	jmp	.L22
.L21:
	movl	-52(%ebp), %eax
	addl	%eax, -40(%ebp)
.L22:
	movl	-52(%ebp), %eax
	leal	-1(%eax), %edx
	movl	-32(%ebp), %eax
	addl	%edx, %eax
	movl	%eax, -28(%ebp)
	movl	-28(%ebp), %ecx
	movl	-32(%ebp), %edx
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	-52(%ebp)
	pushl	%ecx
	pushl	%edx
	leal	-18(%ebp), %edx
	pushl	%edx
	leal	-24(%ebp), %edx
	pushl	%edx
	pushl	-56(%ebp)
	leal	.LC8@GOTOFF(%ebx), %edx
	pushl	%edx
	pushl	%eax
	call	fprintf@PLT
	addl	$32, %esp
	movl	-52(%ebp), %eax
	addl	%eax, -48(%ebp)
	addl	$1, -56(%ebp)
.L16:
	movl	-48(%ebp), %eax
	movl	(%eax), %eax
	cmpl	$1, %eax
	jne	.L23
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$82
	pushl	$1
	leal	.LC7@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$82
	pushl	$1
	leal	.LC9@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$4, %esp
	pushl	-44(%ebp)
	leal	.LC10@GOTOFF(%ebx), %edx
	pushl	%edx
	pushl	%eax
	call	fprintf@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$4, %esp
	pushl	-40(%ebp)
	leal	.LC11@GOTOFF(%ebx), %edx
	pushl	%edx
	pushl	%eax
	call	fprintf@PLT
	addl	$16, %esp
	movl	-44(%ebp), %edx
	movl	-40(%ebp), %eax
	addl	%eax, %edx
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$4, %esp
	pushl	%edx
	leal	.LC12@GOTOFF(%ebx), %edx
	pushl	%edx
	pushl	%eax
	call	fprintf@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	pushl	%eax
	pushl	$82
	pushl	$1
	leal	.LC9@GOTOFF(%ebx), %eax
	pushl	%eax
	call	fwrite@PLT
	addl	$16, %esp
	movl	stdout@GOT(%ebx), %eax
	movl	(%eax), %eax
	subl	$12, %esp
	pushl	%eax
	call	fflush@PLT
	addl	$16, %esp
	nop
	movl	-12(%ebp), %eax
	subl	%gs:20, %eax
	je	.L25
	call	__stack_chk_fail_local
.L25:
	movl	-4(%ebp), %ebx
	leave
	.cfi_restore 5
	.cfi_restore 3
	.cfi_def_cfa 4, 4
	ret
	.cfi_endproc
.LFE11:
	.size	disp_heap, .-disp_heap
	.local	allocated_once.0
	.comm	allocated_once.0,4,4
	.section	.text.__x86.get_pc_thunk.ax,"axG",@progbits,__x86.get_pc_thunk.ax,comdat
	.globl	__x86.get_pc_thunk.ax
	.hidden	__x86.get_pc_thunk.ax
	.type	__x86.get_pc_thunk.ax, @function
__x86.get_pc_thunk.ax:
.LFB12:
	.cfi_startproc
	movl	(%esp), %eax
	ret
	.cfi_endproc
.LFE12:
	.section	.text.__x86.get_pc_thunk.bx,"axG",@progbits,__x86.get_pc_thunk.bx,comdat
	.globl	__x86.get_pc_thunk.bx
	.hidden	__x86.get_pc_thunk.bx
	.type	__x86.get_pc_thunk.bx, @function
__x86.get_pc_thunk.bx:
.LFB13:
	.cfi_startproc
	movl	(%esp), %ebx
	ret
	.cfi_endproc
.LFE13:
	.hidden	__stack_chk_fail_local
	.ident	"GCC: (Ubuntu 11.3.0-1ubuntu1~22.04) 11.3.0"
	.section	.note.GNU-stack,"",@progbits
