package fr.syfizz.symaintenance.utils;


public enum Messages {

    MAINTENANCE_ENABLED("&aLa maintenance a été activée avec succès !"),
    MAINTENANCE_ENABLED_BROADCAST("&aLa maintenance a été activée sur le serveur !"),
    MAINTENANCE_DISABLED("&eLa maintenance a été désactivée avec succès !"),
    MAINTENANCE_ALREADY_ENABLED("&eDésolé, mais la maintenance est déjà active."),
    MAINTENANCE_ALREADY_DISABLED("&eDésolé, mais la maintenance est déjà inactive."),
    MAINTENANCE_SUCCESSFULLY_SCHEDULED("&aUne maintenance a été planifiée avec succès !\n&aActivation dans&e %s &asecondes !"),
    MAINTENANCE_SCHEDULED_BROADCAST_MESSAGE("&e&lLe serveur entre en maintenance dans&a %s &e&l!"),
    MAINTENANCE_SCHEDULED_END_BROADCAST_MESSAGE("&e&lLe serveur sort du mode maintenance dans&a %s &e!"),
    MAINTENANCE_SUCCESSFULLY_ENABLED_DURATION("&aLa maintenance a été activée avec succès !\n&aDurée de la maintenance :&e %s &asecondes."),
    MAINTENANCE_ENDED("&a&lLa maintenance est désormais terminée !"),
    MAINTENANCE_ALREADY_SCHEDULED("&cUne maintenance est déjà en attente d'activation !\n&cPour l'annuler, exécutez la commande &e/maintenance off&c !"),
    MAINTENANCE_SCHEDULED_CANCELLED("&aMaintenance annulée avec succès !"),
    MAINTENANCE_SCHEDULED_CANCELLED_BROADCAST("&a&lL'entrée en maintenance du serveur a été annulée !"),


    COMMAND_WRONG_ARGS("&cDésolé, mais les arguments fournis sont invalides."),
    COMMAND_TOO_MANY_ARGS("&cDésolé, mais vous avez fourni trop d'arguments."),
    COMMAND_TOO_FEW_ARGS("&cDésolé, mais vous n'avez pas fourni assez d'arguments."),
    COMMAND_NO_PERM("&cDésolé, mais vous n'avez pas le droit de faire ça."),
    COMMAND_ERROR_OCCURED("&cDésolé, une erreur est survenue lors de l'exécution de la commande.\n&cMerci de contacter le développeur du plugin."),

    ALLOWED_SUCCESSFULLY_ADDED("&aLe joueur %p a été ajouté avec succès !"),
    ALLOWED_ALREADY_ADDED("&eLe joueur %p est déja dans la liste !"),
    ALLOWED_NOT_IN_LIST("&eLe joueur %p n'est pas dans la liste !"),
    ALLOWED_SUCCESSFULLY_REMOVED("&aLe joueur %p a été supprimé de la liste !"),
    ALLOWED_LIST("&eVoici les joueurs autorisés à se connecter pendant une maintenance :"),
    ALLOWED_PLAYER_NOT_FOUND("&cLe joueur %p n'est pas connecté !"),

    PLAYER_KICK_MESSAGE("&cLe serveur est en maintenance !");

    private final String message;
    Messages(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
