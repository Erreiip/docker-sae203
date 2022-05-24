# Utiliser l'image debian officielle comme image parent
FROM debian:latest


# Installer des services et des packages
RUN  apt-get update &&  \
    apt-get -y install  \
    openjdk-17-jdk \
    git


# Copier les fichiers de l'hôte vers l'image
COPY ./ root/sae

RUN cp -r root/sae/html /var/www/

#java
RUN mv /root/sae/libs /root/libs
ENV CLASSPATH=/root/libs/gson-2.8.2.jar:.
RUN javac -encoding UTF8 /root/libs/UtilsJSON.java -d /root/libs

#env
ENV CLASSPATH=/root/libs:/root/libs/gson-2.8.2.jar:/root/libs/UtilsJSON.class:.


#Exposer le port 80
EXPOSE 80

# Lancer le service apache au démarrage du conteneur
RUN chmod +x root/sae/run.sh
CMD ["/bin/bash", "root/sae/run.sh"]
