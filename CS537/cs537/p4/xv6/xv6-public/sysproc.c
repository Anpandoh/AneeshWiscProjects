#include "types.h"
#include "x86.h"
#include "defs.h"
#include "date.h"
#include "param.h"
#include "memlayout.h"
#include "mmu.h"
#include "proc.h"
#include "psched.h"
#include "spinlock.h"

struct {
  struct spinlock lock;
  struct proc proc[NPROC];
} ptable;

int
sys_fork(void)
{
  return fork();
}

int
sys_exit(void)
{
  exit();
  return 0;  // not reached
}

int
sys_wait(void)
{
  return wait();
}

int
sys_kill(void)
{
  int pid;

  if(argint(0, &pid) < 0)
    return -1;
  return kill(pid);
}

int
sys_getpid(void)
{
  return myproc()->pid;
}

int
sys_sbrk(void)
{
  int addr;
  int n;

  if(argint(0, &n) < 0)
    return -1;
  addr = myproc()->sz;
  if(growproc(n) < 0)
    return -1;
  return addr;
}

int
sys_sleep(void)
{
  int n;
  uint ticks0;

  if(argint(0, &n) < 0)
    return -1;
  acquire(&tickslock);
  ticks0 = ticks;
  //May or may not be in the right place
  myproc()->sleepticks = n+ticks;
  
  while(ticks - ticks0 < n){
    if(myproc()->killed){
      release(&tickslock);
      return -1;
    }
    sleep(&ticks, &tickslock);
  }
  release(&tickslock);
  return 0;
}

// return how many clock tick interrupts have occurred
// since start.
int
sys_uptime(void)
{
  uint xticks;
  acquire(&tickslock);
  xticks = ticks;
  release(&tickslock);
  return xticks;
}

int
sys_nice(void) {
  int n;
  if (argint(0, &n) < 0) {
    return -1;
  }
  if (n > 20 || n < 0) {
    return -1;
  }
  //save prev proc nice value
  int prevnice = myproc()->nice;
  //set curr proc nice value to n
  myproc()->nice = n;
  //return prev nice
  return prevnice;

}

// /*
int
sys_getschedstate(void) {
  //return struct pschedinfo
  struct pschedinfo* pInfo;
  if (argptr(0, (void *) &pInfo, sizeof(*pInfo) < 0)) {
    return -1;
  }

  if (pInfo == 0) {
    return -1;
  }

  struct proc* p;
  int x = 0;
  acquire(&ptable.lock);
  for (p = ptable.proc; p < &ptable.proc[NPROC]; p++) {
     if (p->state == UNUSED) {
      pInfo->inuse[x] = 0;
    } else {
      pInfo->inuse[x] = 1;
      pInfo->priority[x] = p->priority;
      pInfo->nice[x] = p->nice;
      pInfo->ticks[x] = p->cpu;
      pInfo->pid[x] = p->pid;
      x++;
    }
  }
  release(&ptable.lock);
    

  return 0;

}
// */