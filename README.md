# Unique Word Finder

The programming exercise is to create a Java program that counts unique 
words from a text file and lists the top 10 occurrences.

Using the supplied text file (tempest.txt), English locale and treating 
hyphen and apostrophe as part of a word, it should give the following 
output:

and (514)
the (513)
i (446)
to (324)
a (310)
of (295)
my (288)
you (211)
that (188)
this (185)

## Getting Started

### Prerequisites
Java 8
Maven

### Installing
1 - Clone the repository from GibHub
2 - Run the following command on the local repository folder:
    mvn clean package

### Running the program
The program is built as a JAR file. In order to execute the program run the following 
command:
java -jar target/uniquewords-0.0.1.jar <path-to-input-file> <result-size>

result-size is an optional parameter, when not supplied a value of 10 will be assumed.

Examples:
java -jar target/uniquewords-0.0.1.jar /tmp/input.txt
java -jar target/uniquewords-0.0.1.jar /tmp/input.txt 15

## Technical notes
I've used a Stream of Strings as an input so that the class user can use it with several
types of input.
The most common examples are a List of Strings, but also Files.lines returns a Stream of
Strings making it possible to process a file with this method.

## Authors
Flavio Pezzini    