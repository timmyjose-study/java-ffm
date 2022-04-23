CC := gcc
CFLAGS := -Wall -Werror -std=c17 -O2

C_SRC := $(wildcard rational-c/src/*.c)
C_INC := $(wildcar rational-c/include/*.h)
C_LIB := librational_c.dylib

.PHONY: all clean make_c make_rust make_java \
	clean_c clean_rust clean_java

all: make_java

make_c: $(C_INC)
	$(CC) $(CFLAGS) -dynamiclib -o $(C_LIB) $(C_SRC) $< 

make_rust:
	cd rational-rs ; cargo build --release --quiet

make_java: make_c make_rust
	cd java-ffm ; mvn -q clean && mvn -q compile && mvn -q package

clean_c: 
	rm -f *.o $(C_LIB)

clean_rust:
	cd rational-rs ; cargo clean 

clean_java:
	cd java-ffm ; mvn -q clean

clean: clean_c clean_rust clean_java
