#Casino
Interview d'expert :
Nous voulons créer un casino 2.0. User journey : un compte est nécessaire pour jouer, avec une mise minimum de 15€, sans
maximum. Il faut être majeur pour pouvoir créer un compte. Le compte est inactif par défaut, une vérification auprès de
la liste des personnes interdites de jeux doit être effectuée avant d'activer le compter (automatique ou manuel par un
admin ?). Le numéro de la carte d'identité est obligatoire ainsi que le nom, prénom, date de naissance. Un joueur peut
déposer de l'argent quelque soit sont solde. Le dépot minimum est de 10€. Un jour pour retirer de l'argent quand il le
souhaite, mais il doit rester minimum 15€ sur le compte. Le seul moyen de récupérer cette somme est de supprimer le
compte. Chaque joueur peut avoir une limite de jeu. Il peut définir à partir de quelle somme jouée par mois son compte
est temporairement suspendu. La suspension est levée le 1er jour du mois suivant. Le joueur peut avoir l'historique de
ses retraits et dépôts sur son compte. Le joueur peut connaitre son solde à tout moment. Le joueur peut donner un
pourboire au croupier. Le joueur peut se connecter avec son adresse mail ou son numéro de compte.

Casino journey : le casino tient un portemonnaie par joueur. Chaque dépôt crédite le portemonnaie. Chaque retrait débite
le portemonnaie. Le casino tient également une caisse. Lorsqu'un joueur engage une mise pour jouer, le montant de la
mise est transféré du portemonnaie du joueur à la caisse du casino. Si le joueur perd, la mise est définitivement
acquise au casino et reste en caisse. Si le joueur gagne, le montant du gain est transféré de la caisse du casino au
portemonnaie du joueur. Le joueur peut jouer jusqu'à ce que son portemonnaie soit vide. Le gérant peut consulter le
solde de la caisse, l'historique des mouvements, la liste des portemonnaies avec leur solde, ainsi que l'historique des
mouvements de chaque portemonnaie.

# Améliorations

X- renommer les méthodes 
X- renommer la classe de test
X- modifier les request mappings
X- gérer l'exception dans le contrôleur
- modifier les paramètres des contrôleurs
- blinder les tu si pas suffisant
- décrire la mise en place Spring
- décrire la structure Maven

# US PARTIE COMPTE
C-US1: EN TANT QU'utilisateur JE VEUX créer un compte sur www.bellagio.com AFIN DE jouer à des jeux de casino en ligne
Contexte : Un utilisateur peut créer un compte sur le site www.bellagio.com afin de jouer à des jeux de casino en ligne
Critères d'acceptance : 
- Il faut être majeur pour créer un compte

C-US2: EN TANT QU'utilisateur JE VEUX créer un compte sur www.bellagio.com AFIN DE jouer à des jeux de casino en ligne
Contexte : Un utilisateur peut créer un compte sur le site www.bellagio.com afin de jouer à des jeux de casino en ligne
Critères d'acceptance : 
- Il faut être majeur pour créer un compte

# US PARTIE CAISSE ET PORTE MONNAIE
PM-US1 : EN TANT QUE joueur JE VEUX ajouter de l'argent sur mon porte-monnaie AFIN DE jouer à des jeux
Contexte : Une fois le compte validé, le joueur dispose d'un porte-monnaie. C'est l'argent de ce porte-monnaie qu'il va pouvoir jouer. Le joueur peut ajouter de l'argent.
Critères d'acceptance : 
- Le joueur doit déposer au minimum 1€ à chaque dépôt
- Le joueur ne peut pas déposer plus de 1 500€ par dépôt
- Le joueur peut faire un dépôt par jour maximum
- Le porte-monnaie ne peut pas contenir plus de 10 000€

PM-US2 : EN TANT QUE joueur JE VEUX retirer de l'argent sur de mon porte-monnaie AFIN DE récupérer l'argent dont je dispose sur mon porte-monnaie
Contexte : Le joueur peut retirer de l'argent de son porte-monnaie pour le récupérer. 
Critères d'acceptance : 
- Le porte-monnaie ne peut pas être débiteur
- Le joueur peut faire un retrait maximum par joueur
- Le joueur peut retirer 1 500€ maximum sans validation du casino
- Le joueur doit obtenir la validation du casino pour retirer plus de 1 500€

PM-US3 : EN TANT QUE casino JE VEUX que le porte-monnaie ait un statut AFIN DE spécifier que le joueur peut utiliser son porte-monnaie pour jouer ou non
Contexte : le porte-monnaie du joueur a un statut qui suit un cycle de vie
- à la création du compte, le porte-monnaie est "à valider"
- une fois le compte approuvé, le porte-monnaie devient "valide"
- si le joueur est interdit de jeu, le porte-monnaie est "suspendu"
- lorsque l'interdiction de jeu est levée, le porte-monnaie est "valide"
- lorsque le compte est fermé, le porte-monnaie est "clos"

PM-US4 : EN TANT QUE casino JE VEUX connaître l'historique des statuts d'un porte-monnaie AFIN DE conserver son historique
Contexte : le cycle de vie du porte-monnaie doit être enregistré
- Le changement de statut doit être stocké avec la date du jour et l'heure

PM-US5 : EN TANT QUE casino JE VEUX lister les joueurs actuellement interdits de jeu AFIN DE ???
Contexte : Un joueur interdit de jeu voit son porte-monnaie passer au statut "suspendu"
- ???

# Bonnes pratiques

- TDD
- DDD
- Api Octo
- Git Flow

#Process branches
1. pull sur la branche develop
2. git checkout -b feature/<nom-us>
3. commit régulier avec message qui comment toujours pas [<nom-us>]
4. push à la fin d'une session de travail
5. merge request dans github quand terminé pour code review
6. pull sur la branche develop, puis merge --squash <branche-à-merger>
7. commit avec [<nom-us>] en dans le message, puis résumé de l'us 
8. push