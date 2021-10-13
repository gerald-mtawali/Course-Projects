JFLAGS = -g
JC = javac
JVM = java

.SUFFIXES: .java .class
.java.class: 
		$(JC) $(JFLAGS) $*.java
CLASSES = \
	Edge.java\
	Vertex.java\
	Graph.java\
	Main.java
MAIN = Main

default: classes
classes: $(CLASSES:.java=.class)
run: $(MAIN).class
	$(JVM) $(MAIN)
clean: 
	$(RM)*.class
