# SyMaintenance
![GitHub issues](https://img.shields.io/github/issues/SyFizz/SyMaintenance?label=issues) ![GitHub All Releases](https://img.shields.io/github/downloads/SyFizz/SyMaintenance/total?color=light%20green) ![GitHub release (latest by date)](https://img.shields.io/github/v/release/SyFizz/SyMaintenance)

SyMaintenance est un plugin utilisant l'API Spigot afin d'offrir un mode dit de "maintenance" sur les serveurs Minecraft.

## Installation

Téléchargez la dernière version [ici](https://github.com/SyFizz/SyMaintenance/releases) pour installer le plugin.


## Utilisation

### Commandes :
La commande principale du plugin est `/maintenance`

- `/maintenance <on|off>` permet de modifier l'état actuel de la maintenance. Lors de l'activation, tous les joueurs qui ne sont pas opérateurs qui ne sont pas ajoutés à la liste blanche seront éjectés.

- `/maintenance <add|remove> <online player>` permet d'ajouter ou de supprimer un joueur de la liste blanche. 
NOTE : Le joueur spécifié doit être connecté !

- `/maintenance list` permet d'afficher la liste des joueurs ajoutés à la liste blanche

- `/maintenance schedule <delay in seconds>` permet de programmer une maintenance. Par défaut, plusieurs broadcasts sont effectués avant la maintenance : 1 heure avant, 30 minutes avant, 10 minutes avant, 5 minutes avant, 1 minute avant et 30 secondes avant.

- `/maintenance duration <duration in seconds>` permet de mettre le serveur en mode maintenance pour une durée déterminée.
Note : En cas de reload ou de redémarrage du serveur, le timer sera annulé, et la maintenance devra être levée manuellement.
### Permissions :
Le plugin ne dispose pas encore d'un système de permissions, il suffit d'être opérateur pour pouvoir effectuer les différentes commandes présentées ci-dessus.

## Contribution
Les pull requests sont les bienvenues. Pour des changements majeurs, merci d'ouvrir d'abord une issue pour discuter de ce que vous voulez modifier.

Merci de faire des tests avant d'ouvrir une pull request.

## License
[GNU GPLv3](https://choosealicense.com/licenses/gpl-3.0/)
