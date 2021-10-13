JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class
.java.class: 
		$(JC) $(JFLAGS) $*.java
CLASSES = \
	DTNode.java\
	DoubleThreadedBST.java\
	Tester.java
MAIN = Tester

default: classes
classes: $(CLASSES:.java=.class)
run: $(MAIN).class
	$(JVM) $(MAIN)
clean: 
	$(RM)*.class
