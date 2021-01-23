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

-
Réponse :