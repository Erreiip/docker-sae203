# Utiliser l'image debian officielle comme image parent
FROM debian:latest


# Installer des services et des packages
RUN  apt-get update &&  \
    apt-get -y install  \
    nginx               \
    git                 \
    default-jdk


# Copier les fichiers de l'hôte vers l'image
RUN git clone https://github.com/Ciliste/saedocker-sae203.git

RUN cp -r saedocker-sae203/html /var/www/

RUN java saedocker-sae203/client/Test.java;


# Exposer le port 80
EXPOSE 80


# Lancer le service apache au démarrage du conteneur
CMD ["nginx", "-g", "daemon off;"]
