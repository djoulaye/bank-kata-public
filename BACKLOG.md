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

# US
X- US2.1
> un retrait négatif est impossible (fallait-il vraiment le préciser...)
> Faire cette US en TDD 
- US 4 :
> As a bank, a customer can display its account transactions history
> Réfléchir à la conception, et coder en TDD
> Semble idéale pour un test en Gherkin ?
- US 5 : 
> Afficher la liste des comptes de la banque
> Coder en TDD
- US 6 :
> Enrichir la liste des comptes avec le nom et le prénom du client, et le solde du compte
> Coder en TDD
- US 7 : 
> créer des comptes pour personnes physiques et personnes morales
>utiliser le pattern factory (enum factory ?)

# Bonnes pratiques

- TDD
- DDD
- Api Octo
- Git Flow