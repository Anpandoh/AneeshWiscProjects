
_test_1:     file format elf32-i386


Disassembly of section .text:

00000000 <main>:
#include "types.h"
#include "stat.h"
#include "user.h"

int main(void)
{    
   0:	8d 4c 24 04          	lea    0x4(%esp),%ecx
   4:	83 e4 f0             	and    $0xfffffff0,%esp
   7:	ff 71 fc             	push   -0x4(%ecx)
   a:	55                   	push   %ebp
   b:	89 e5                	mov    %esp,%ebp
   d:	53                   	push   %ebx
   e:	51                   	push   %ecx
    char message[256];  
    getlastcat(message); //&message[0]
   f:	8d 9d f8 fe ff ff    	lea    -0x108(%ebp),%ebx
{    
  15:	81 ec 0c 01 00 00    	sub    $0x10c,%esp
    getlastcat(message); //&message[0]
  1b:	53                   	push   %ebx
  1c:	e8 12 03 00 00       	call   333 <getlastcat>
    printf(1,"XV6_TEST_OUTPUT Last catted filename: %s\n",message);
  21:	83 c4 0c             	add    $0xc,%esp
  24:	53                   	push   %ebx
  25:	68 18 07 00 00       	push   $0x718
  2a:	6a 01                	push   $0x1
  2c:	e8 bf 03 00 00       	call   3f0 <printf>
    exit();
  31:	e8 5d 02 00 00       	call   293 <exit>
  36:	66 90                	xchg   %ax,%ax
  38:	66 90                	xchg   %ax,%ax
  3a:	66 90                	xchg   %ax,%ax
  3c:	66 90                	xchg   %ax,%ax
  3e:	66 90                	xchg   %ax,%ax

00000040 <strcpy>:
#include "user.h"
#include "x86.h"

char*
strcpy(char *s, const char *t)
{
  40:	55                   	push   %ebp
  char *os;

  os = s;
  while((*s++ = *t++) != 0)
  41:	31 c0                	xor    %eax,%eax
{
  43:	89 e5                	mov    %esp,%ebp
  45:	53                   	push   %ebx
  46:	8b 4d 08             	mov    0x8(%ebp),%ecx
  49:	8b 5d 0c             	mov    0xc(%ebp),%ebx
  4c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
  while((*s++ = *t++) != 0)
  50:	0f b6 14 03          	movzbl (%ebx,%eax,1),%edx
  54:	88 14 01             	mov    %dl,(%ecx,%eax,1)
  57:	83 c0 01             	add    $0x1,%eax
  5a:	84 d2                	test   %dl,%dl
  5c:	75 f2                	jne    50 <strcpy+0x10>
    ;
  return os;
}
  5e:	8b 5d fc             	mov    -0x4(%ebp),%ebx
  61:	89 c8                	mov    %ecx,%eax
  63:	c9                   	leave  
  64:	c3                   	ret    
  65:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
  6c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi

00000070 <strcmp>:

int
strcmp(const char *p, const char *q)
{
  70:	55                   	push   %ebp
  71:	89 e5                	mov    %esp,%ebp
  73:	53                   	push   %ebx
  74:	8b 55 08             	mov    0x8(%ebp),%edx
  77:	8b 4d 0c             	mov    0xc(%ebp),%ecx
  while(*p && *p == *q)
  7a:	0f b6 02             	movzbl (%edx),%eax
  7d:	84 c0                	test   %al,%al
  7f:	75 17                	jne    98 <strcmp+0x28>
  81:	eb 3a                	jmp    bd <strcmp+0x4d>
  83:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
  87:	90                   	nop
  88:	0f b6 42 01          	movzbl 0x1(%edx),%eax
    p++, q++;
  8c:	83 c2 01             	add    $0x1,%edx
  8f:	8d 59 01             	lea    0x1(%ecx),%ebx
  while(*p && *p == *q)
  92:	84 c0                	test   %al,%al
  94:	74 1a                	je     b0 <strcmp+0x40>
    p++, q++;
  96:	89 d9                	mov    %ebx,%ecx
  while(*p && *p == *q)
  98:	0f b6 19             	movzbl (%ecx),%ebx
  9b:	38 c3                	cmp    %al,%bl
  9d:	74 e9                	je     88 <strcmp+0x18>
  return (uchar)*p - (uchar)*q;
  9f:	29 d8                	sub    %ebx,%eax
}
  a1:	8b 5d fc             	mov    -0x4(%ebp),%ebx
  a4:	c9                   	leave  
  a5:	c3                   	ret    
  a6:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
  ad:	8d 76 00             	lea    0x0(%esi),%esi
  return (uchar)*p - (uchar)*q;
  b0:	0f b6 59 01          	movzbl 0x1(%ecx),%ebx
  b4:	31 c0                	xor    %eax,%eax
  b6:	29 d8                	sub    %ebx,%eax
}
  b8:	8b 5d fc             	mov    -0x4(%ebp),%ebx
  bb:	c9                   	leave  
  bc:	c3                   	ret    
  return (uchar)*p - (uchar)*q;
  bd:	0f b6 19             	movzbl (%ecx),%ebx
  c0:	31 c0                	xor    %eax,%eax
  c2:	eb db                	jmp    9f <strcmp+0x2f>
  c4:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
  cb:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
  cf:	90                   	nop

000000d0 <strlen>:

uint
strlen(const char *s)
{
  d0:	55                   	push   %ebp
  d1:	89 e5                	mov    %esp,%ebp
  d3:	8b 55 08             	mov    0x8(%ebp),%edx
  int n;

  for(n = 0; s[n]; n++)
  d6:	80 3a 00             	cmpb   $0x0,(%edx)
  d9:	74 15                	je     f0 <strlen+0x20>
  db:	31 c0                	xor    %eax,%eax
  dd:	8d 76 00             	lea    0x0(%esi),%esi
  e0:	83 c0 01             	add    $0x1,%eax
  e3:	80 3c 02 00          	cmpb   $0x0,(%edx,%eax,1)
  e7:	89 c1                	mov    %eax,%ecx
  e9:	75 f5                	jne    e0 <strlen+0x10>
    ;
  return n;
}
  eb:	89 c8                	mov    %ecx,%eax
  ed:	5d                   	pop    %ebp
  ee:	c3                   	ret    
  ef:	90                   	nop
  for(n = 0; s[n]; n++)
  f0:	31 c9                	xor    %ecx,%ecx
}
  f2:	5d                   	pop    %ebp
  f3:	89 c8                	mov    %ecx,%eax
  f5:	c3                   	ret    
  f6:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
  fd:	8d 76 00             	lea    0x0(%esi),%esi

00000100 <memset>:

void*
memset(void *dst, int c, uint n)
{
 100:	55                   	push   %ebp
 101:	89 e5                	mov    %esp,%ebp
 103:	57                   	push   %edi
 104:	8b 55 08             	mov    0x8(%ebp),%edx
}

static inline void
stosb(void *addr, int data, int cnt)
{
  asm volatile("cld; rep stosb" :
 107:	8b 4d 10             	mov    0x10(%ebp),%ecx
 10a:	8b 45 0c             	mov    0xc(%ebp),%eax
 10d:	89 d7                	mov    %edx,%edi
 10f:	fc                   	cld    
 110:	f3 aa                	rep stos %al,%es:(%edi)
  stosb(dst, c, n);
  return dst;
}
 112:	8b 7d fc             	mov    -0x4(%ebp),%edi
 115:	89 d0                	mov    %edx,%eax
 117:	c9                   	leave  
 118:	c3                   	ret    
 119:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi

00000120 <strchr>:

char*
strchr(const char *s, char c)
{
 120:	55                   	push   %ebp
 121:	89 e5                	mov    %esp,%ebp
 123:	8b 45 08             	mov    0x8(%ebp),%eax
 126:	0f b6 4d 0c          	movzbl 0xc(%ebp),%ecx
  for(; *s; s++)
 12a:	0f b6 10             	movzbl (%eax),%edx
 12d:	84 d2                	test   %dl,%dl
 12f:	75 12                	jne    143 <strchr+0x23>
 131:	eb 1d                	jmp    150 <strchr+0x30>
 133:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 137:	90                   	nop
 138:	0f b6 50 01          	movzbl 0x1(%eax),%edx
 13c:	83 c0 01             	add    $0x1,%eax
 13f:	84 d2                	test   %dl,%dl
 141:	74 0d                	je     150 <strchr+0x30>
    if(*s == c)
 143:	38 d1                	cmp    %dl,%cl
 145:	75 f1                	jne    138 <strchr+0x18>
      return (char*)s;
  return 0;
}
 147:	5d                   	pop    %ebp
 148:	c3                   	ret    
 149:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
  return 0;
 150:	31 c0                	xor    %eax,%eax
}
 152:	5d                   	pop    %ebp
 153:	c3                   	ret    
 154:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 15b:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 15f:	90                   	nop

00000160 <gets>:

char*
gets(char *buf, int max)
{
 160:	55                   	push   %ebp
 161:	89 e5                	mov    %esp,%ebp
 163:	57                   	push   %edi
 164:	56                   	push   %esi
  int i, cc;
  char c;

  for(i=0; i+1 < max; ){
    cc = read(0, &c, 1);
 165:	8d 7d e7             	lea    -0x19(%ebp),%edi
{
 168:	53                   	push   %ebx
  for(i=0; i+1 < max; ){
 169:	31 db                	xor    %ebx,%ebx
{
 16b:	83 ec 1c             	sub    $0x1c,%esp
  for(i=0; i+1 < max; ){
 16e:	eb 27                	jmp    197 <gets+0x37>
    cc = read(0, &c, 1);
 170:	83 ec 04             	sub    $0x4,%esp
 173:	6a 01                	push   $0x1
 175:	57                   	push   %edi
 176:	6a 00                	push   $0x0
 178:	e8 2e 01 00 00       	call   2ab <read>
    if(cc < 1)
 17d:	83 c4 10             	add    $0x10,%esp
 180:	85 c0                	test   %eax,%eax
 182:	7e 1d                	jle    1a1 <gets+0x41>
      break;
    buf[i++] = c;
 184:	0f b6 45 e7          	movzbl -0x19(%ebp),%eax
 188:	8b 55 08             	mov    0x8(%ebp),%edx
 18b:	88 44 1a ff          	mov    %al,-0x1(%edx,%ebx,1)
    if(c == '\n' || c == '\r')
 18f:	3c 0a                	cmp    $0xa,%al
 191:	74 1d                	je     1b0 <gets+0x50>
 193:	3c 0d                	cmp    $0xd,%al
 195:	74 19                	je     1b0 <gets+0x50>
  for(i=0; i+1 < max; ){
 197:	89 de                	mov    %ebx,%esi
 199:	83 c3 01             	add    $0x1,%ebx
 19c:	3b 5d 0c             	cmp    0xc(%ebp),%ebx
 19f:	7c cf                	jl     170 <gets+0x10>
      break;
  }
  buf[i] = '\0';
 1a1:	8b 45 08             	mov    0x8(%ebp),%eax
 1a4:	c6 04 30 00          	movb   $0x0,(%eax,%esi,1)
  return buf;
}
 1a8:	8d 65 f4             	lea    -0xc(%ebp),%esp
 1ab:	5b                   	pop    %ebx
 1ac:	5e                   	pop    %esi
 1ad:	5f                   	pop    %edi
 1ae:	5d                   	pop    %ebp
 1af:	c3                   	ret    
  buf[i] = '\0';
 1b0:	8b 45 08             	mov    0x8(%ebp),%eax
 1b3:	89 de                	mov    %ebx,%esi
 1b5:	c6 04 30 00          	movb   $0x0,(%eax,%esi,1)
}
 1b9:	8d 65 f4             	lea    -0xc(%ebp),%esp
 1bc:	5b                   	pop    %ebx
 1bd:	5e                   	pop    %esi
 1be:	5f                   	pop    %edi
 1bf:	5d                   	pop    %ebp
 1c0:	c3                   	ret    
 1c1:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 1c8:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 1cf:	90                   	nop

000001d0 <stat>:

int
stat(const char *n, struct stat *st)
{
 1d0:	55                   	push   %ebp
 1d1:	89 e5                	mov    %esp,%ebp
 1d3:	56                   	push   %esi
 1d4:	53                   	push   %ebx
  int fd;
  int r;

  fd = open(n, O_RDONLY);
 1d5:	83 ec 08             	sub    $0x8,%esp
 1d8:	6a 00                	push   $0x0
 1da:	ff 75 08             	push   0x8(%ebp)
 1dd:	e8 f1 00 00 00       	call   2d3 <open>
  if(fd < 0)
 1e2:	83 c4 10             	add    $0x10,%esp
 1e5:	85 c0                	test   %eax,%eax
 1e7:	78 27                	js     210 <stat+0x40>
    return -1;
  r = fstat(fd, st);
 1e9:	83 ec 08             	sub    $0x8,%esp
 1ec:	ff 75 0c             	push   0xc(%ebp)
 1ef:	89 c3                	mov    %eax,%ebx
 1f1:	50                   	push   %eax
 1f2:	e8 f4 00 00 00       	call   2eb <fstat>
  close(fd);
 1f7:	89 1c 24             	mov    %ebx,(%esp)
  r = fstat(fd, st);
 1fa:	89 c6                	mov    %eax,%esi
  close(fd);
 1fc:	e8 ba 00 00 00       	call   2bb <close>
  return r;
 201:	83 c4 10             	add    $0x10,%esp
}
 204:	8d 65 f8             	lea    -0x8(%ebp),%esp
 207:	89 f0                	mov    %esi,%eax
 209:	5b                   	pop    %ebx
 20a:	5e                   	pop    %esi
 20b:	5d                   	pop    %ebp
 20c:	c3                   	ret    
 20d:	8d 76 00             	lea    0x0(%esi),%esi
    return -1;
 210:	be ff ff ff ff       	mov    $0xffffffff,%esi
 215:	eb ed                	jmp    204 <stat+0x34>
 217:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 21e:	66 90                	xchg   %ax,%ax

00000220 <atoi>:

int
atoi(const char *s)
{
 220:	55                   	push   %ebp
 221:	89 e5                	mov    %esp,%ebp
 223:	53                   	push   %ebx
 224:	8b 55 08             	mov    0x8(%ebp),%edx
  int n;

  n = 0;
  while('0' <= *s && *s <= '9')
 227:	0f be 02             	movsbl (%edx),%eax
 22a:	8d 48 d0             	lea    -0x30(%eax),%ecx
 22d:	80 f9 09             	cmp    $0x9,%cl
  n = 0;
 230:	b9 00 00 00 00       	mov    $0x0,%ecx
  while('0' <= *s && *s <= '9')
 235:	77 1e                	ja     255 <atoi+0x35>
 237:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 23e:	66 90                	xchg   %ax,%ax
    n = n*10 + *s++ - '0';
 240:	83 c2 01             	add    $0x1,%edx
 243:	8d 0c 89             	lea    (%ecx,%ecx,4),%ecx
 246:	8d 4c 48 d0          	lea    -0x30(%eax,%ecx,2),%ecx
  while('0' <= *s && *s <= '9')
 24a:	0f be 02             	movsbl (%edx),%eax
 24d:	8d 58 d0             	lea    -0x30(%eax),%ebx
 250:	80 fb 09             	cmp    $0x9,%bl
 253:	76 eb                	jbe    240 <atoi+0x20>
  return n;
}
 255:	8b 5d fc             	mov    -0x4(%ebp),%ebx
 258:	89 c8                	mov    %ecx,%eax
 25a:	c9                   	leave  
 25b:	c3                   	ret    
 25c:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi

00000260 <memmove>:

void*
memmove(void *vdst, const void *vsrc, int n)
{
 260:	55                   	push   %ebp
 261:	89 e5                	mov    %esp,%ebp
 263:	57                   	push   %edi
 264:	8b 45 10             	mov    0x10(%ebp),%eax
 267:	8b 55 08             	mov    0x8(%ebp),%edx
 26a:	56                   	push   %esi
 26b:	8b 75 0c             	mov    0xc(%ebp),%esi
  char *dst;
  const char *src;

  dst = vdst;
  src = vsrc;
  while(n-- > 0)
 26e:	85 c0                	test   %eax,%eax
 270:	7e 13                	jle    285 <memmove+0x25>
 272:	01 d0                	add    %edx,%eax
  dst = vdst;
 274:	89 d7                	mov    %edx,%edi
 276:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 27d:	8d 76 00             	lea    0x0(%esi),%esi
    *dst++ = *src++;
 280:	a4                   	movsb  %ds:(%esi),%es:(%edi)
  while(n-- > 0)
 281:	39 f8                	cmp    %edi,%eax
 283:	75 fb                	jne    280 <memmove+0x20>
  return vdst;
}
 285:	5e                   	pop    %esi
 286:	89 d0                	mov    %edx,%eax
 288:	5f                   	pop    %edi
 289:	5d                   	pop    %ebp
 28a:	c3                   	ret    

0000028b <fork>:
  name: \
    movl $SYS_ ## name, %eax; \
    int $T_SYSCALL; \
    ret

SYSCALL(fork)
 28b:	b8 01 00 00 00       	mov    $0x1,%eax
 290:	cd 40                	int    $0x40
 292:	c3                   	ret    

00000293 <exit>:
SYSCALL(exit)
 293:	b8 02 00 00 00       	mov    $0x2,%eax
 298:	cd 40                	int    $0x40
 29a:	c3                   	ret    

0000029b <wait>:
SYSCALL(wait)
 29b:	b8 03 00 00 00       	mov    $0x3,%eax
 2a0:	cd 40                	int    $0x40
 2a2:	c3                   	ret    

000002a3 <pipe>:
SYSCALL(pipe)
 2a3:	b8 04 00 00 00       	mov    $0x4,%eax
 2a8:	cd 40                	int    $0x40
 2aa:	c3                   	ret    

000002ab <read>:
SYSCALL(read)
 2ab:	b8 05 00 00 00       	mov    $0x5,%eax
 2b0:	cd 40                	int    $0x40
 2b2:	c3                   	ret    

000002b3 <write>:
SYSCALL(write)
 2b3:	b8 10 00 00 00       	mov    $0x10,%eax
 2b8:	cd 40                	int    $0x40
 2ba:	c3                   	ret    

000002bb <close>:
SYSCALL(close)
 2bb:	b8 15 00 00 00       	mov    $0x15,%eax
 2c0:	cd 40                	int    $0x40
 2c2:	c3                   	ret    

000002c3 <kill>:
SYSCALL(kill)
 2c3:	b8 06 00 00 00       	mov    $0x6,%eax
 2c8:	cd 40                	int    $0x40
 2ca:	c3                   	ret    

000002cb <exec>:
SYSCALL(exec)
 2cb:	b8 07 00 00 00       	mov    $0x7,%eax
 2d0:	cd 40                	int    $0x40
 2d2:	c3                   	ret    

000002d3 <open>:
SYSCALL(open)
 2d3:	b8 0f 00 00 00       	mov    $0xf,%eax
 2d8:	cd 40                	int    $0x40
 2da:	c3                   	ret    

000002db <mknod>:
SYSCALL(mknod)
 2db:	b8 11 00 00 00       	mov    $0x11,%eax
 2e0:	cd 40                	int    $0x40
 2e2:	c3                   	ret    

000002e3 <unlink>:
SYSCALL(unlink)
 2e3:	b8 12 00 00 00       	mov    $0x12,%eax
 2e8:	cd 40                	int    $0x40
 2ea:	c3                   	ret    

000002eb <fstat>:
SYSCALL(fstat)
 2eb:	b8 08 00 00 00       	mov    $0x8,%eax
 2f0:	cd 40                	int    $0x40
 2f2:	c3                   	ret    

000002f3 <link>:
SYSCALL(link)
 2f3:	b8 13 00 00 00       	mov    $0x13,%eax
 2f8:	cd 40                	int    $0x40
 2fa:	c3                   	ret    

000002fb <mkdir>:
SYSCALL(mkdir)
 2fb:	b8 14 00 00 00       	mov    $0x14,%eax
 300:	cd 40                	int    $0x40
 302:	c3                   	ret    

00000303 <chdir>:
SYSCALL(chdir)
 303:	b8 09 00 00 00       	mov    $0x9,%eax
 308:	cd 40                	int    $0x40
 30a:	c3                   	ret    

0000030b <dup>:
SYSCALL(dup)
 30b:	b8 0a 00 00 00       	mov    $0xa,%eax
 310:	cd 40                	int    $0x40
 312:	c3                   	ret    

00000313 <getpid>:
SYSCALL(getpid)
 313:	b8 0b 00 00 00       	mov    $0xb,%eax
 318:	cd 40                	int    $0x40
 31a:	c3                   	ret    

0000031b <sbrk>:
SYSCALL(sbrk)
 31b:	b8 0c 00 00 00       	mov    $0xc,%eax
 320:	cd 40                	int    $0x40
 322:	c3                   	ret    

00000323 <sleep>:
SYSCALL(sleep)
 323:	b8 0d 00 00 00       	mov    $0xd,%eax
 328:	cd 40                	int    $0x40
 32a:	c3                   	ret    

0000032b <uptime>:
SYSCALL(uptime)
 32b:	b8 0e 00 00 00       	mov    $0xe,%eax
 330:	cd 40                	int    $0x40
 332:	c3                   	ret    

00000333 <getlastcat>:
SYSCALL(getlastcat)
 333:	b8 16 00 00 00       	mov    $0x16,%eax
 338:	cd 40                	int    $0x40
 33a:	c3                   	ret    
 33b:	66 90                	xchg   %ax,%ax
 33d:	66 90                	xchg   %ax,%ax
 33f:	90                   	nop

00000340 <printint>:
  write(fd, &c, 1);
}

static void
printint(int fd, int xx, int base, int sgn)
{
 340:	55                   	push   %ebp
 341:	89 e5                	mov    %esp,%ebp
 343:	57                   	push   %edi
 344:	56                   	push   %esi
 345:	53                   	push   %ebx
 346:	83 ec 3c             	sub    $0x3c,%esp
 349:	89 4d c4             	mov    %ecx,-0x3c(%ebp)
  uint x;

  neg = 0;
  if(sgn && xx < 0){
    neg = 1;
    x = -xx;
 34c:	89 d1                	mov    %edx,%ecx
{
 34e:	89 45 b8             	mov    %eax,-0x48(%ebp)
  if(sgn && xx < 0){
 351:	85 d2                	test   %edx,%edx
 353:	0f 89 7f 00 00 00    	jns    3d8 <printint+0x98>
 359:	f6 45 08 01          	testb  $0x1,0x8(%ebp)
 35d:	74 79                	je     3d8 <printint+0x98>
    neg = 1;
 35f:	c7 45 bc 01 00 00 00 	movl   $0x1,-0x44(%ebp)
    x = -xx;
 366:	f7 d9                	neg    %ecx
  } else {
    x = xx;
  }

  i = 0;
 368:	31 db                	xor    %ebx,%ebx
 36a:	8d 75 d7             	lea    -0x29(%ebp),%esi
 36d:	8d 76 00             	lea    0x0(%esi),%esi
  do{
    buf[i++] = digits[x % base];
 370:	89 c8                	mov    %ecx,%eax
 372:	31 d2                	xor    %edx,%edx
 374:	89 cf                	mov    %ecx,%edi
 376:	f7 75 c4             	divl   -0x3c(%ebp)
 379:	0f b6 92 a4 07 00 00 	movzbl 0x7a4(%edx),%edx
 380:	89 45 c0             	mov    %eax,-0x40(%ebp)
 383:	89 d8                	mov    %ebx,%eax
 385:	8d 5b 01             	lea    0x1(%ebx),%ebx
  }while((x /= base) != 0);
 388:	8b 4d c0             	mov    -0x40(%ebp),%ecx
    buf[i++] = digits[x % base];
 38b:	88 14 1e             	mov    %dl,(%esi,%ebx,1)
  }while((x /= base) != 0);
 38e:	39 7d c4             	cmp    %edi,-0x3c(%ebp)
 391:	76 dd                	jbe    370 <printint+0x30>
  if(neg)
 393:	8b 4d bc             	mov    -0x44(%ebp),%ecx
 396:	85 c9                	test   %ecx,%ecx
 398:	74 0c                	je     3a6 <printint+0x66>
    buf[i++] = '-';
 39a:	c6 44 1d d8 2d       	movb   $0x2d,-0x28(%ebp,%ebx,1)
    buf[i++] = digits[x % base];
 39f:	89 d8                	mov    %ebx,%eax
    buf[i++] = '-';
 3a1:	ba 2d 00 00 00       	mov    $0x2d,%edx

  while(--i >= 0)
 3a6:	8b 7d b8             	mov    -0x48(%ebp),%edi
 3a9:	8d 5c 05 d7          	lea    -0x29(%ebp,%eax,1),%ebx
 3ad:	eb 07                	jmp    3b6 <printint+0x76>
 3af:	90                   	nop
    putc(fd, buf[i]);
 3b0:	0f b6 13             	movzbl (%ebx),%edx
 3b3:	83 eb 01             	sub    $0x1,%ebx
  write(fd, &c, 1);
 3b6:	83 ec 04             	sub    $0x4,%esp
 3b9:	88 55 d7             	mov    %dl,-0x29(%ebp)
 3bc:	6a 01                	push   $0x1
 3be:	56                   	push   %esi
 3bf:	57                   	push   %edi
 3c0:	e8 ee fe ff ff       	call   2b3 <write>
  while(--i >= 0)
 3c5:	83 c4 10             	add    $0x10,%esp
 3c8:	39 de                	cmp    %ebx,%esi
 3ca:	75 e4                	jne    3b0 <printint+0x70>
}
 3cc:	8d 65 f4             	lea    -0xc(%ebp),%esp
 3cf:	5b                   	pop    %ebx
 3d0:	5e                   	pop    %esi
 3d1:	5f                   	pop    %edi
 3d2:	5d                   	pop    %ebp
 3d3:	c3                   	ret    
 3d4:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
  neg = 0;
 3d8:	c7 45 bc 00 00 00 00 	movl   $0x0,-0x44(%ebp)
 3df:	eb 87                	jmp    368 <printint+0x28>
 3e1:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 3e8:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 3ef:	90                   	nop

000003f0 <printf>:

// Print to the given fd. Only understands %d, %x, %p, %s.
void
printf(int fd, const char *fmt, ...)
{
 3f0:	55                   	push   %ebp
 3f1:	89 e5                	mov    %esp,%ebp
 3f3:	57                   	push   %edi
 3f4:	56                   	push   %esi
 3f5:	53                   	push   %ebx
 3f6:	83 ec 2c             	sub    $0x2c,%esp
  uint *ap;


  state = 0;
  ap = (uint*)(void*)&fmt + 1;
  for(i = 0; fmt[i]; i++){
 3f9:	8b 5d 0c             	mov    0xc(%ebp),%ebx
{
 3fc:	8b 75 08             	mov    0x8(%ebp),%esi
  for(i = 0; fmt[i]; i++){
 3ff:	0f b6 13             	movzbl (%ebx),%edx
 402:	84 d2                	test   %dl,%dl
 404:	74 6a                	je     470 <printf+0x80>
  ap = (uint*)(void*)&fmt + 1;
 406:	8d 45 10             	lea    0x10(%ebp),%eax
 409:	83 c3 01             	add    $0x1,%ebx
  write(fd, &c, 1);
 40c:	8d 7d e7             	lea    -0x19(%ebp),%edi
  state = 0;
 40f:	31 c9                	xor    %ecx,%ecx
  ap = (uint*)(void*)&fmt + 1;
 411:	89 45 d0             	mov    %eax,-0x30(%ebp)
 414:	eb 36                	jmp    44c <printf+0x5c>
 416:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 41d:	8d 76 00             	lea    0x0(%esi),%esi
 420:	89 4d d4             	mov    %ecx,-0x2c(%ebp)
    c = fmt[i] & 0xff;
    if(state == 0){
      if(c == '%'){
        state = '%';
 423:	b9 25 00 00 00       	mov    $0x25,%ecx
      if(c == '%'){
 428:	83 f8 25             	cmp    $0x25,%eax
 42b:	74 15                	je     442 <printf+0x52>
  write(fd, &c, 1);
 42d:	83 ec 04             	sub    $0x4,%esp
 430:	88 55 e7             	mov    %dl,-0x19(%ebp)
 433:	6a 01                	push   $0x1
 435:	57                   	push   %edi
 436:	56                   	push   %esi
 437:	e8 77 fe ff ff       	call   2b3 <write>
 43c:	8b 4d d4             	mov    -0x2c(%ebp),%ecx
      } else {
        putc(fd, c);
 43f:	83 c4 10             	add    $0x10,%esp
  for(i = 0; fmt[i]; i++){
 442:	0f b6 13             	movzbl (%ebx),%edx
 445:	83 c3 01             	add    $0x1,%ebx
 448:	84 d2                	test   %dl,%dl
 44a:	74 24                	je     470 <printf+0x80>
    c = fmt[i] & 0xff;
 44c:	0f b6 c2             	movzbl %dl,%eax
    if(state == 0){
 44f:	85 c9                	test   %ecx,%ecx
 451:	74 cd                	je     420 <printf+0x30>
      }
    } else if(state == '%'){
 453:	83 f9 25             	cmp    $0x25,%ecx
 456:	75 ea                	jne    442 <printf+0x52>
      if(c == 'd'){
 458:	83 f8 25             	cmp    $0x25,%eax
 45b:	0f 84 07 01 00 00    	je     568 <printf+0x178>
 461:	83 e8 63             	sub    $0x63,%eax
 464:	83 f8 15             	cmp    $0x15,%eax
 467:	77 17                	ja     480 <printf+0x90>
 469:	ff 24 85 4c 07 00 00 	jmp    *0x74c(,%eax,4)
        putc(fd, c);
      }
      state = 0;
    }
  }
}
 470:	8d 65 f4             	lea    -0xc(%ebp),%esp
 473:	5b                   	pop    %ebx
 474:	5e                   	pop    %esi
 475:	5f                   	pop    %edi
 476:	5d                   	pop    %ebp
 477:	c3                   	ret    
 478:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 47f:	90                   	nop
  write(fd, &c, 1);
 480:	83 ec 04             	sub    $0x4,%esp
 483:	88 55 d4             	mov    %dl,-0x2c(%ebp)
 486:	6a 01                	push   $0x1
 488:	57                   	push   %edi
 489:	56                   	push   %esi
 48a:	c6 45 e7 25          	movb   $0x25,-0x19(%ebp)
 48e:	e8 20 fe ff ff       	call   2b3 <write>
        putc(fd, c);
 493:	0f b6 55 d4          	movzbl -0x2c(%ebp),%edx
  write(fd, &c, 1);
 497:	83 c4 0c             	add    $0xc,%esp
 49a:	88 55 e7             	mov    %dl,-0x19(%ebp)
 49d:	6a 01                	push   $0x1
 49f:	57                   	push   %edi
 4a0:	56                   	push   %esi
 4a1:	e8 0d fe ff ff       	call   2b3 <write>
        putc(fd, c);
 4a6:	83 c4 10             	add    $0x10,%esp
      state = 0;
 4a9:	31 c9                	xor    %ecx,%ecx
 4ab:	eb 95                	jmp    442 <printf+0x52>
 4ad:	8d 76 00             	lea    0x0(%esi),%esi
        printint(fd, *ap, 16, 0);
 4b0:	83 ec 0c             	sub    $0xc,%esp
 4b3:	b9 10 00 00 00       	mov    $0x10,%ecx
 4b8:	6a 00                	push   $0x0
 4ba:	8b 45 d0             	mov    -0x30(%ebp),%eax
 4bd:	8b 10                	mov    (%eax),%edx
 4bf:	89 f0                	mov    %esi,%eax
 4c1:	e8 7a fe ff ff       	call   340 <printint>
        ap++;
 4c6:	83 45 d0 04          	addl   $0x4,-0x30(%ebp)
 4ca:	83 c4 10             	add    $0x10,%esp
      state = 0;
 4cd:	31 c9                	xor    %ecx,%ecx
 4cf:	e9 6e ff ff ff       	jmp    442 <printf+0x52>
 4d4:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
        s = (char*)*ap;
 4d8:	8b 45 d0             	mov    -0x30(%ebp),%eax
 4db:	8b 10                	mov    (%eax),%edx
        ap++;
 4dd:	83 c0 04             	add    $0x4,%eax
 4e0:	89 45 d0             	mov    %eax,-0x30(%ebp)
        if(s == 0)
 4e3:	85 d2                	test   %edx,%edx
 4e5:	0f 84 8d 00 00 00    	je     578 <printf+0x188>
        while(*s != 0){
 4eb:	0f b6 02             	movzbl (%edx),%eax
      state = 0;
 4ee:	31 c9                	xor    %ecx,%ecx
        while(*s != 0){
 4f0:	84 c0                	test   %al,%al
 4f2:	0f 84 4a ff ff ff    	je     442 <printf+0x52>
 4f8:	89 5d d4             	mov    %ebx,-0x2c(%ebp)
 4fb:	89 d3                	mov    %edx,%ebx
 4fd:	8d 76 00             	lea    0x0(%esi),%esi
  write(fd, &c, 1);
 500:	83 ec 04             	sub    $0x4,%esp
          s++;
 503:	83 c3 01             	add    $0x1,%ebx
 506:	88 45 e7             	mov    %al,-0x19(%ebp)
  write(fd, &c, 1);
 509:	6a 01                	push   $0x1
 50b:	57                   	push   %edi
 50c:	56                   	push   %esi
 50d:	e8 a1 fd ff ff       	call   2b3 <write>
        while(*s != 0){
 512:	0f b6 03             	movzbl (%ebx),%eax
 515:	83 c4 10             	add    $0x10,%esp
 518:	84 c0                	test   %al,%al
 51a:	75 e4                	jne    500 <printf+0x110>
      state = 0;
 51c:	8b 5d d4             	mov    -0x2c(%ebp),%ebx
 51f:	31 c9                	xor    %ecx,%ecx
 521:	e9 1c ff ff ff       	jmp    442 <printf+0x52>
 526:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 52d:	8d 76 00             	lea    0x0(%esi),%esi
        printint(fd, *ap, 10, 1);
 530:	83 ec 0c             	sub    $0xc,%esp
 533:	b9 0a 00 00 00       	mov    $0xa,%ecx
 538:	6a 01                	push   $0x1
 53a:	e9 7b ff ff ff       	jmp    4ba <printf+0xca>
 53f:	90                   	nop
        putc(fd, *ap);
 540:	8b 45 d0             	mov    -0x30(%ebp),%eax
  write(fd, &c, 1);
 543:	83 ec 04             	sub    $0x4,%esp
        putc(fd, *ap);
 546:	8b 00                	mov    (%eax),%eax
  write(fd, &c, 1);
 548:	6a 01                	push   $0x1
 54a:	57                   	push   %edi
 54b:	56                   	push   %esi
        putc(fd, *ap);
 54c:	88 45 e7             	mov    %al,-0x19(%ebp)
  write(fd, &c, 1);
 54f:	e8 5f fd ff ff       	call   2b3 <write>
        ap++;
 554:	83 45 d0 04          	addl   $0x4,-0x30(%ebp)
 558:	83 c4 10             	add    $0x10,%esp
      state = 0;
 55b:	31 c9                	xor    %ecx,%ecx
 55d:	e9 e0 fe ff ff       	jmp    442 <printf+0x52>
 562:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
        putc(fd, c);
 568:	88 55 e7             	mov    %dl,-0x19(%ebp)
  write(fd, &c, 1);
 56b:	83 ec 04             	sub    $0x4,%esp
 56e:	e9 2a ff ff ff       	jmp    49d <printf+0xad>
 573:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
 577:	90                   	nop
          s = "(null)";
 578:	ba 42 07 00 00       	mov    $0x742,%edx
        while(*s != 0){
 57d:	89 5d d4             	mov    %ebx,-0x2c(%ebp)
 580:	b8 28 00 00 00       	mov    $0x28,%eax
 585:	89 d3                	mov    %edx,%ebx
 587:	e9 74 ff ff ff       	jmp    500 <printf+0x110>
 58c:	66 90                	xchg   %ax,%ax
 58e:	66 90                	xchg   %ax,%ax

00000590 <free>:
static Header base;
static Header *freep;

void
free(void *ap)
{
 590:	55                   	push   %ebp
  Header *bp, *p;

  bp = (Header*)ap - 1;
  for(p = freep; !(bp > p && bp < p->s.ptr); p = p->s.ptr)
 591:	a1 50 0a 00 00       	mov    0xa50,%eax
{
 596:	89 e5                	mov    %esp,%ebp
 598:	57                   	push   %edi
 599:	56                   	push   %esi
 59a:	53                   	push   %ebx
 59b:	8b 5d 08             	mov    0x8(%ebp),%ebx
  bp = (Header*)ap - 1;
 59e:	8d 4b f8             	lea    -0x8(%ebx),%ecx
  for(p = freep; !(bp > p && bp < p->s.ptr); p = p->s.ptr)
 5a1:	8d b4 26 00 00 00 00 	lea    0x0(%esi,%eiz,1),%esi
 5a8:	89 c2                	mov    %eax,%edx
 5aa:	8b 00                	mov    (%eax),%eax
 5ac:	39 ca                	cmp    %ecx,%edx
 5ae:	73 30                	jae    5e0 <free+0x50>
 5b0:	39 c1                	cmp    %eax,%ecx
 5b2:	72 04                	jb     5b8 <free+0x28>
    if(p >= p->s.ptr && (bp > p || bp < p->s.ptr))
 5b4:	39 c2                	cmp    %eax,%edx
 5b6:	72 f0                	jb     5a8 <free+0x18>
      break;
  if(bp + bp->s.size == p->s.ptr){
 5b8:	8b 73 fc             	mov    -0x4(%ebx),%esi
 5bb:	8d 3c f1             	lea    (%ecx,%esi,8),%edi
 5be:	39 f8                	cmp    %edi,%eax
 5c0:	74 30                	je     5f2 <free+0x62>
    bp->s.size += p->s.ptr->s.size;
    bp->s.ptr = p->s.ptr->s.ptr;
 5c2:	89 43 f8             	mov    %eax,-0x8(%ebx)
  } else
    bp->s.ptr = p->s.ptr;
  if(p + p->s.size == bp){
 5c5:	8b 42 04             	mov    0x4(%edx),%eax
 5c8:	8d 34 c2             	lea    (%edx,%eax,8),%esi
 5cb:	39 f1                	cmp    %esi,%ecx
 5cd:	74 3a                	je     609 <free+0x79>
    p->s.size += bp->s.size;
    p->s.ptr = bp->s.ptr;
 5cf:	89 0a                	mov    %ecx,(%edx)
  } else
    p->s.ptr = bp;
  freep = p;
}
 5d1:	5b                   	pop    %ebx
  freep = p;
 5d2:	89 15 50 0a 00 00    	mov    %edx,0xa50
}
 5d8:	5e                   	pop    %esi
 5d9:	5f                   	pop    %edi
 5da:	5d                   	pop    %ebp
 5db:	c3                   	ret    
 5dc:	8d 74 26 00          	lea    0x0(%esi,%eiz,1),%esi
    if(p >= p->s.ptr && (bp > p || bp < p->s.ptr))
 5e0:	39 c2                	cmp    %eax,%edx
 5e2:	72 c4                	jb     5a8 <free+0x18>
 5e4:	39 c1                	cmp    %eax,%ecx
 5e6:	73 c0                	jae    5a8 <free+0x18>
  if(bp + bp->s.size == p->s.ptr){
 5e8:	8b 73 fc             	mov    -0x4(%ebx),%esi
 5eb:	8d 3c f1             	lea    (%ecx,%esi,8),%edi
 5ee:	39 f8                	cmp    %edi,%eax
 5f0:	75 d0                	jne    5c2 <free+0x32>
    bp->s.size += p->s.ptr->s.size;
 5f2:	03 70 04             	add    0x4(%eax),%esi
 5f5:	89 73 fc             	mov    %esi,-0x4(%ebx)
    bp->s.ptr = p->s.ptr->s.ptr;
 5f8:	8b 02                	mov    (%edx),%eax
 5fa:	8b 00                	mov    (%eax),%eax
 5fc:	89 43 f8             	mov    %eax,-0x8(%ebx)
  if(p + p->s.size == bp){
 5ff:	8b 42 04             	mov    0x4(%edx),%eax
 602:	8d 34 c2             	lea    (%edx,%eax,8),%esi
 605:	39 f1                	cmp    %esi,%ecx
 607:	75 c6                	jne    5cf <free+0x3f>
    p->s.size += bp->s.size;
 609:	03 43 fc             	add    -0x4(%ebx),%eax
  freep = p;
 60c:	89 15 50 0a 00 00    	mov    %edx,0xa50
    p->s.size += bp->s.size;
 612:	89 42 04             	mov    %eax,0x4(%edx)
    p->s.ptr = bp->s.ptr;
 615:	8b 4b f8             	mov    -0x8(%ebx),%ecx
 618:	89 0a                	mov    %ecx,(%edx)
}
 61a:	5b                   	pop    %ebx
 61b:	5e                   	pop    %esi
 61c:	5f                   	pop    %edi
 61d:	5d                   	pop    %ebp
 61e:	c3                   	ret    
 61f:	90                   	nop

00000620 <malloc>:
  return freep;
}

void*
malloc(uint nbytes)
{
 620:	55                   	push   %ebp
 621:	89 e5                	mov    %esp,%ebp
 623:	57                   	push   %edi
 624:	56                   	push   %esi
 625:	53                   	push   %ebx
 626:	83 ec 1c             	sub    $0x1c,%esp
  Header *p, *prevp;
  uint nunits;

  nunits = (nbytes + sizeof(Header) - 1)/sizeof(Header) + 1;
 629:	8b 45 08             	mov    0x8(%ebp),%eax
  if((prevp = freep) == 0){
 62c:	8b 3d 50 0a 00 00    	mov    0xa50,%edi
  nunits = (nbytes + sizeof(Header) - 1)/sizeof(Header) + 1;
 632:	8d 70 07             	lea    0x7(%eax),%esi
 635:	c1 ee 03             	shr    $0x3,%esi
 638:	83 c6 01             	add    $0x1,%esi
  if((prevp = freep) == 0){
 63b:	85 ff                	test   %edi,%edi
 63d:	0f 84 9d 00 00 00    	je     6e0 <malloc+0xc0>
    base.s.ptr = freep = prevp = &base;
    base.s.size = 0;
  }
  for(p = prevp->s.ptr; ; prevp = p, p = p->s.ptr){
 643:	8b 17                	mov    (%edi),%edx
    if(p->s.size >= nunits){
 645:	8b 4a 04             	mov    0x4(%edx),%ecx
 648:	39 f1                	cmp    %esi,%ecx
 64a:	73 6a                	jae    6b6 <malloc+0x96>
 64c:	bb 00 10 00 00       	mov    $0x1000,%ebx
 651:	39 de                	cmp    %ebx,%esi
 653:	0f 43 de             	cmovae %esi,%ebx
  p = sbrk(nu * sizeof(Header));
 656:	8d 04 dd 00 00 00 00 	lea    0x0(,%ebx,8),%eax
 65d:	89 45 e4             	mov    %eax,-0x1c(%ebp)
 660:	eb 17                	jmp    679 <malloc+0x59>
 662:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
  for(p = prevp->s.ptr; ; prevp = p, p = p->s.ptr){
 668:	8b 02                	mov    (%edx),%eax
    if(p->s.size >= nunits){
 66a:	8b 48 04             	mov    0x4(%eax),%ecx
 66d:	39 f1                	cmp    %esi,%ecx
 66f:	73 4f                	jae    6c0 <malloc+0xa0>
        p->s.size = nunits;
      }
      freep = prevp;
      return (void*)(p + 1);
    }
    if(p == freep)
 671:	8b 3d 50 0a 00 00    	mov    0xa50,%edi
 677:	89 c2                	mov    %eax,%edx
 679:	39 d7                	cmp    %edx,%edi
 67b:	75 eb                	jne    668 <malloc+0x48>
  p = sbrk(nu * sizeof(Header));
 67d:	83 ec 0c             	sub    $0xc,%esp
 680:	ff 75 e4             	push   -0x1c(%ebp)
 683:	e8 93 fc ff ff       	call   31b <sbrk>
  if(p == (char*)-1)
 688:	83 c4 10             	add    $0x10,%esp
 68b:	83 f8 ff             	cmp    $0xffffffff,%eax
 68e:	74 1c                	je     6ac <malloc+0x8c>
  hp->s.size = nu;
 690:	89 58 04             	mov    %ebx,0x4(%eax)
  free((void*)(hp + 1));
 693:	83 ec 0c             	sub    $0xc,%esp
 696:	83 c0 08             	add    $0x8,%eax
 699:	50                   	push   %eax
 69a:	e8 f1 fe ff ff       	call   590 <free>
  return freep;
 69f:	8b 15 50 0a 00 00    	mov    0xa50,%edx
      if((p = morecore(nunits)) == 0)
 6a5:	83 c4 10             	add    $0x10,%esp
 6a8:	85 d2                	test   %edx,%edx
 6aa:	75 bc                	jne    668 <malloc+0x48>
        return 0;
  }
}
 6ac:	8d 65 f4             	lea    -0xc(%ebp),%esp
        return 0;
 6af:	31 c0                	xor    %eax,%eax
}
 6b1:	5b                   	pop    %ebx
 6b2:	5e                   	pop    %esi
 6b3:	5f                   	pop    %edi
 6b4:	5d                   	pop    %ebp
 6b5:	c3                   	ret    
    if(p->s.size >= nunits){
 6b6:	89 d0                	mov    %edx,%eax
 6b8:	89 fa                	mov    %edi,%edx
 6ba:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
      if(p->s.size == nunits)
 6c0:	39 ce                	cmp    %ecx,%esi
 6c2:	74 4c                	je     710 <malloc+0xf0>
        p->s.size -= nunits;
 6c4:	29 f1                	sub    %esi,%ecx
 6c6:	89 48 04             	mov    %ecx,0x4(%eax)
        p += p->s.size;
 6c9:	8d 04 c8             	lea    (%eax,%ecx,8),%eax
        p->s.size = nunits;
 6cc:	89 70 04             	mov    %esi,0x4(%eax)
      freep = prevp;
 6cf:	89 15 50 0a 00 00    	mov    %edx,0xa50
}
 6d5:	8d 65 f4             	lea    -0xc(%ebp),%esp
      return (void*)(p + 1);
 6d8:	83 c0 08             	add    $0x8,%eax
}
 6db:	5b                   	pop    %ebx
 6dc:	5e                   	pop    %esi
 6dd:	5f                   	pop    %edi
 6de:	5d                   	pop    %ebp
 6df:	c3                   	ret    
    base.s.ptr = freep = prevp = &base;
 6e0:	c7 05 50 0a 00 00 54 	movl   $0xa54,0xa50
 6e7:	0a 00 00 
    base.s.size = 0;
 6ea:	bf 54 0a 00 00       	mov    $0xa54,%edi
    base.s.ptr = freep = prevp = &base;
 6ef:	c7 05 54 0a 00 00 54 	movl   $0xa54,0xa54
 6f6:	0a 00 00 
  for(p = prevp->s.ptr; ; prevp = p, p = p->s.ptr){
 6f9:	89 fa                	mov    %edi,%edx
    base.s.size = 0;
 6fb:	c7 05 58 0a 00 00 00 	movl   $0x0,0xa58
 702:	00 00 00 
    if(p->s.size >= nunits){
 705:	e9 42 ff ff ff       	jmp    64c <malloc+0x2c>
 70a:	8d b6 00 00 00 00    	lea    0x0(%esi),%esi
        prevp->s.ptr = p->s.ptr;
 710:	8b 08                	mov    (%eax),%ecx
 712:	89 0a                	mov    %ecx,(%edx)
 714:	eb b9                	jmp    6cf <malloc+0xaf>
