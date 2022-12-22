p3Heap: p3Heap.c p3Heap.h
	gcc -g -c -Wall -m32 -fpic p3Heap.c
	gcc -shared -Wall -m32 -o libheap.so p3Heap.o

clean:
	rm -rf p3Heap.o libheap.so
