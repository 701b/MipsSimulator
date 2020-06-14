#-----------------------------------------------------------
# Student Name  : 2016312021
# Student ID No.: Moon Taeui
#-----------------------------------------------------------
CLASSES_DIR = bin
SRC_DIR = source

JAVAC = javac
JAVAC_FLAGS = -d $(CLASSES_DIR)
JAVA = java
JAVA_FLAGS = -cp $(CLASSES_DIR)

SOURCES = \
	$(SRC_DIR)/simulator/*.java \
	$(SRC_DIR)/simulator/instruction/*.java \
	$(SRC_DIR)/simulator/data/*.java

CLASSES = \
	$(CLASSES_DIR)/simulator/*.class \
	$(CLASSES_DIR)/simulator/instruction/*.class \
	$(CLASSES_DIR)/simulator/data/*.class


default:
	mkdir $(CLASSES_DIR)
	$(JAVAC) $(JAVAC_FLAGS) $(SOURCES)

run:
	$(JAVA) $(JAVA_FLAGS) simulator/UserInputManager

clean:
	rm -r $(CLASSES_DIR)/
