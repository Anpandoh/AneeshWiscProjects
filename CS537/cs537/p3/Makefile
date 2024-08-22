# Compiler
CC := gcc

# Compilation flags
CFLAGS := -Wall -Werror -pedantic -std=gnu18 -g

# Your login
LOGIN := pandoh

# Path for submission
SUBMITPATH := ~cs537-1/handin/$(LOGIN)/P3

# Targets
.PHONY: all

all: wsh

wsh: wsh.c wsh.h
	$(CC) $(CFLAGS) $^ -o $@

run: wsh
	./wsh

pack: wsh.h wsh.c Makefile README.md
	tar -czvf $(LOGIN).tar.gz $^

submit: pack
	cp $(LOGIN).tar.gz $(SUBMITPATH)
