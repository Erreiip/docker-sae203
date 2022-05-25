## ![Image](https://media.discordapp.net/attachments/898144992365801494/977961760772468756/unknown.png?width=580&height=580)

## Discord.java

L'objectif du projet était d'héberger un service sur un Docker.
Nous avons choisi de proposer un service de messagerie. Décomposé en 2 programmes : Un programme qui tourne sur le Docker et gère les connexions entre les différents clients; Une application téléchargeable qui sert d'interface pour chaque client.

## Technique

Nous avons pour ce projet dévellopé en java (petite référence dans le nom de l'application), nous avons utilisé la librairie java.net.Socket pour créer des connexions entre les différents et le serveur.  
Le serveur utilise un thread pour gérer les différents client qui pourraient si connecter.

### Protocole
Pour ce projet nous avons utilisé un protocole de communication :
- 002 [nom \ serveur \ mdp ] : rejoindre un serveur
- 003 [texte]                : envoyer un message
- 004 [nom \ serveur \ mdp ] : creer un serveur
- 006 [messages]             : messages du serveur
- 007 [noms]                 : noms des personnes sur le serveur
- 069 : quitter le serveur

## Utilisation
### Côté client

Le client est instalable depuis ce [lien](https://mega.nz/file/0AhWzBSI#I09liI1a0ZoIwbYFJb0RD6ZZLisH_tNjH0sdyqshafo).  
Il peut être lancé via  
`java -jar discord.jar`


Une fois installé et lancé vous apparaiterez sur cette page   
![Image](https://camo.githubusercontent.com/1e16199c9da1a90d414b0a025f82f4d4c26cd507502bfbb5bc7b876709a51fe3/68747470733a2f2f63646e2e646973636f72646170702e636f6d2f6174746163686d656e74732f3839383134343939323336353830313439342f3937383930323736383530373034373939362f756e6b6e6f776e2e706e67)  
  
  
Vous pourrez valider votre pseudo en tapant sur entrée ce qui vous amènera sur la page d'acceuil où vous pourrez créer ou rejoindre des serveurs avec vos amis !  
![Image](https://camo.githubusercontent.com/784687743c26cef861994bf6f0e723cc36ef2610779f340f2ea0cbe47e592fd2/68747470733a2f2f63646e2e646973636f72646170702e636f6d2f6174746163686d656e74732f3838373937343135373535303233353635382f3937383930343834393534303335303030322f756e6b6e6f776e2e706e67)

### Côté serveur

L'image du docker installe et lance automatiquement le script du serveur depuis ce [repo GitHub]().
Le script est capable de gérer plusieurs utilisateurs répartis sur plusieurs serveurs (appelés "guilds" dans le code source) possédant chacun leur propre discussion.
Un serveur se caractérise par son nom et un mot de passe (optionnel). 

On a utilisé une librairie pour stocker les discussions en fichier .json, si le serveur redémarre, les données sont conservées.
Un fichier par "guild" qui stocke le nom, le mot de passe (pas foufou niveau sécurité) et sa discussion représentée par une liste d'objet Message (auteur, contenu et date).

Exemple d'une "guild" :
```json
{
    "nom": "Serveur TEST",
    "messages":[
        {
            "auteur": "Théo",
            "contenu": "Salut !",
            "date": "0"
        },
        {
            "auteur": "Maxime",
            "contenu": "Bonjour.",
            "date": "0"
        },
        {
            "auteur": "Pierre",
            "contenu": "Sheeesh",
            "date": "0"
        }
    ],
    "mdp": "Mot de passe TEST"
}
```

Le fonctionnement du serveur consiste en un Thread qui accepte en boucle les nouveaux clients, puis lance pour chacun une nouvelle instance d'un Thread.

```markdown
Syntax highlighted code block

bvalblalbalblabalalabl

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```

For more details see [Basic writing and formatting syntax](https://docs.github.com/en/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/Erreiip/sae203-docker-eq1/settings/pages). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Crédits

Équipe 1 : [AIREY Théo](https://github.com/Ciliste), [LE MEUR Pierre](https://github.com/Erreiip), [SAHUC Maxime](https://github.com/ValrodClient)
