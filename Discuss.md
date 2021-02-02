# Questions
MNS : j'ai le message suivant quand je fais un mvn install 
Failed to execute goal org.apache.maven.plugins:maven-toolchains-plugin:3.0.0:toolchain (default) on project bank-kata: Misconfigured toolchains.
Je comprends pas pourquoi, le toolchains.xml semble ok

MNS : quand on fait un delete, save, update sur l'entité jpa, on ne vérifie pas si le compte existe ?
--> Je vais l'ajouter sinon ça plante

# TODO
Regarder comment faire les tests dans la couche appli avec un mock du repo
Regarder comment améliorer la gestion des exceptions (avec moins de try catch)


# Propositions MNS : 

- Je n'aime pas comment est nommée la méthode getAccount, il faut éviter les get pour coller plus à du fonctionnel
  *Réponse : je suis d'accord*
- Je pense qu'il faudrait remplacer les paramètres par un @BodyParam ? ou autre ?
  *Réponse : je suis d'accord aussi*
- Au lieu de swagger on peut appeler les endpoints depuis postman ou insomnia
  *Réponse : chacun fait comme il veut :p*
- Pour la gestion des exceptions, dans un premier temps on peut les gérer dans le controller (gestion du response
  entity), et après on créé un controllerAdvice
  *Réponse : OK*
- Il faudrait corriger les requests mappings
  *Réponse : OK*

# Propositions CRTX : 

- Concernant AccountServiceTest, le problème est déjà de savoir si on fait un TU ou un TI. Si on fait un TU, alors
  AccountService est mocké donc pas de raison de mocker le repo (et pas de sens car c'est un mock et non le vrai
  service). Si on fait un TI, alors le problème est de lancer Spring dans un module qui n'est pas une application
  Spring. En tout cas le extendWith est à remplacer par le Spring. Dans ce deuxième cas, cela pose aussi la question de
  l'autowired sur les attributs, qui empêche l'instanciation manuelle dans le cadre du test. 
  Réponse : J'ai compris pour le TU, je vais regarder ce que tu as fait
  Pour le TI, pour moi on doit mettre l'extension SpringExtension et faire des MockBean au lieu des mock , c'est ça ?
  
- Concernant les exceptions dans les contrôleurs, il semble falloir effet passer par un advice ou une
  ResponseStatusException. Le ResponseStatusException provoque l'appel du endpoint /error, et nécessite la surcharge de
  l'ErrorController par défaut pour remplacer la WhiteLabel Page. Le type de retour est également à étudier, car
  retourner un String dans le body n'est pas vraiment RestFul. Il faudrait définir un ErrorDto qui contient le message.
  Réponse : Trop compliqué, j'ai rien compris :D
- Est ce que le AccountService (tout court) qui fait principalement du crud ne serait pas justement l'implémentation de
  l'interface repository (du domaine) par l'infra ? Réponse : oui je pense mais je ne comprends pas ce que ça implique ^^