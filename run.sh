#!/bin/bash
cd /root/sae/serveur
javac -encoding UTF8 @compile.list -d /java

cd /java/serveur
java Serveur.java
