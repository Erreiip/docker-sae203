# Utiliser l'image debian officielle comme image parent
FROM debian:latest


# Installer des services et des packages
RUN  apt-get update &&  \
    apt-get -y install  \
    openjdk-17-jdk \
    git


# Copier les fichiers de l'hôte vers l'image
RUN git clone https://github.com/Erreiip/sae203-docker-eq1.git

RUN mv sae203-docker-eq1/ root/sae

RUN cp -r root/sae/html /var/www/

RUN mv /root/sae/class /root/

ENV CLASSPATH=/root/class/libs/gson-2.8.2.jar:.

#Exposer le port 80
EXPOSE 80
EXPOSE 6969

# Lancer le service apache au démarrage du conteneur
RUN chmod +x root/sae/run.sh
CMD ["/bin/bash", "root/sae/run.sh"]
