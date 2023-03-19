# Échecs casse-gueule

Projet de la mineure Création Numérique de 4ème année à l'**ECE Paris**.
Il s'agit d'un ensemble de petits jeux vidéos, chacun réalisé avec un langage de programmation différent et qui s'appelent les uns les autres à la manière de microservices applicatifs.

## Idée de départ

L'équipe d'origine ayant débuté ce projet au premier semestre avait pour idée de créer un jeu d'échecs en joueur contre joueur dans lequel un affrontement entre deux pièces de l'échiquier lancerait un duel dans un jeu de combat, permettant ainsi au vainqueur de prendre la pièce du vaincu, même si ce n'est pas lui qui attaquait à l'origine.

Ils n'ont eu le temps de réaliser que le jeu d'échecs, et nous avons décidé de continuer le projet en en changeant la suite.

Les membres de l'équipe du second semestre ayant chacun des idées différentes, nous avons décidé de créer plusieurs jeux, chacun avec un langage différent, et de les faire s'appeler les uns les autres à la manière de microservices applicatifs. Ansi, chaque membre a pu travailler sur un mini-projet qui lui tenait à coeur, et nous avons donc pris la décision de changer tout d'abord le jeu d'échecs pour du joueur contre ordinateur, puis de remplacer le jeu de combat par une série de mini-jeux se jouant contre l'ordinateur.

## Liste des jeux et langages utilisés

### Echecs

#### Présentation du jeu d'échecs

Le jeu d'échecs est réalisé en **Java** avec la librairie **JavaFX**. Il est jouable en mode joueur contre ordinateur.

La plupart des règles du jeu d'échecs sont implémentées, mais il manque encore quelques fonctionnalités comme la prise en passant, le roque, la promotion des pions, etc.

Chaque duel entre deux pièces de niveau équivalent donne lieu à un mini-jeu, qui est choisi aléatoirement parmi la liste des mini-jeux existants. Le vainqueur du duel remporte la pièce du perdant.

> Nous avons choisi de ne lancer les mini-jeux que lorsque les pièces sont de niveau équivalent, afin de ne pas rendre une partie trop longue et pour éviter d'avoir par exemple une **Dame** qui se fait battre par un **Pion** alors qu'elle l'attaquait.

#### Réalisation du jeu d'échecs

- [Abdelaziz Abdelkefi](https://github.com/Azizo27)
- [Maelys Bourgeat](https://github.com/Maelys92)
- [Nicolas Dreyfus--Laquièze](https://github.com/Nicodl05)
- [Arthur Fournier](https://github.com/space192)

### Pierre Feuille Ciseaux

#### Présentation du jeu de Pierre Feuille Ciseaux

Le jeu de Pierre Feuille Ciseaux est réalisé en **Python** avec la librairie **Tensorflow**. Il est jouable en mode joueur contre ordinateur.

La particularité de ce jeu est qu'il se joue en utilisant la caméra de l'ordinateur. Le joueur doit donc se placer devant la caméra et faire un geste de la main pour jouer. Le jeu détecte ensuite le geste et le transforme en pierre, feuille ou ciseaux.

Le jeu se joue en 3 manches, et le vainqueur est celui qui a gagné le plus de manches.

#### Réalisation du jeu de Pierre Feuille Ciseaux

- [Carole-Anne Cos](https://github.com/caro0031)

### Snake

#### Présentation du jeu du Snake

Le jeu du Snake est réalisé en **Python** avec la librairie **Pytorch**.

Au lancement du jeu, deux instances du jeu sont lancées en parallèle : une instance pour le joueur, et une instance pour l'ordinateur.

L'ordinateur a appris à jouer au jeu du Snake en utilisant une méthode d'apprentissage par renforcement. Il a donc appris à jouer en jouant des milliers de parties en boucle, et en apprenant à chaque fois à améliorer sa stratégie.

Le jeu se termine lorsque l'un des deux joueurs meurt, et le vainqueur est celui qui a survécu le plus longtemps.

#### Réalisation du jeu du Snake

- [Sarah Fages](https://github.com/SarahFages)
- [Lucas Couffin](https://github.com/)

### Jeu de Nim

#### Présentation du jeu de Nim

Le jeu de Nim est réalisé en **C#** avec la librairie **WPF**. Il est jouable en mode joueur contre ordinateur.

Le jeu de Nim est un jeu de stratégie combinatoire se jouant à deux joueurs. Il se joue avec des bâtons disposés en tas. Chaque joueur, à son tour, doit retirer un à trois bâtons du tas. Le joueur qui prend le dernier bâton perd la partie.

Le nombre de bâtons est choisi aléatoirement entre 10 et 20, et le joueur qui commence est le joueur qui a attaqué l'autre joueur dans le jeu d'échecs.

Le jeu se termine lorsque le nombre de bâtons tombe à 0, le joueur prenant le dernier bâton perdant la partie.

#### Réalisation du jeu de Nim

- [Adrien Blair](https://github.com/Ahddry)

### Tir à l'arc

#### Présentation du jeu de Tir à l'arc

Le jeu de Tir à l'arc est réalisé en **Python** avec la librairie **Tensorflow**.

Le jeu se joue en utilisant la caméra de l'ordinateur. Le joueur doit donc se placer devant la caméra et faire un geste de la main pour jouer. Le jeu détecte ensuite le geste et le transforme en tir à l'arc. Le joueur doit ensuite viser la cible et tirer. Le jeu détecte ensuite si le tir est réussi ou non. L'ordinateur quant à lui génère aléatoirement un score qui est le sien pour le tour. Le vainqueur est celui qui a le meilleur score à la fin de la partie.

#### Réalisation du jeu de Tir à l'arc

- [Sarah Fages](https://github.com/SarahFages)

## Interactions entre les jeux

Les différents mini-jeux sont appelés les uns les autres à la manière de microservices applicatifs. Chaque mini-jeu est appelé par le jeu d'échecs lors d'un duel entre deux pièces de niveau équivalent. Le jeu d'échecs entre donc les informations nécessaires au mini-jeu pour qu'il puisse se lancer dans un fichier `.txt` servant aux deux jeux à communiquer. Le jeu d'échecs lance ensuite le mini-jeu et attend que celui-ci se termine. Le mini-jeu modifie ensuite le fichier `.txt` pour indiquer au jeu d'échecs le vainqueur du duel.

## Installation

Chaque mini-jeu dispose d'un `.exe` qui permet de lancer le jeu. Les mini-jeux sont lancés par le jeu d'échecs, qui se lance lui-même soit en lançant un exécutable[^1], soit en lançant le projet en debug.

[^1]: L'exécutable n'a pas encore été créé.

## Conclusion

Cet embitieux projet nous a permis de chacun travailler sur un mini-projet qui nous tenait à coeur, et de pratiquer des langages de programmation sur lesquels nous voulions aquérir plus d'expérience. Chacun a ainsi pu faire ce qu'il voulait, tout en cherchant à faire quelque chose de cohérent avec le reste du projet.

La cohésion de l'équipe a été un facteur clé de la réussite de ce projet. Nous avons pu nous répartir les tâches de manière équitable, et nous avons pu nous aider mutuellement lorsqu'un problème se posait.

Nous sommes très satisfaits du résultat final, et heureux d'avoir pu réaliser un projet comprenant du **Java**, du **Python**, et du **C#** mais aussi de l'**apprentissage par renforcement**, de l'**intelligence artificielle**, et de la **reconnaissance de gestes**.

## Contributeurs

- [Abdelaziz Abdelkefi](https://github.com/Azizo27)
- [Adrien Blair](https://github.com/Ahddry)
- [Maelys Bourgeat](https://github.com/Maelys92)
- [Carole-Anne Cos](https://github.com/caro0031)
- [Lucas Couffin](https://github.com/)
- [Nicolas Dreyfus--Laquièze](https://github.com/Nicodl05)
- [Sarah Fages](https://github.com/SarahFages)
- [Arthur Fournier](https://github.com/space192)

## Captures d'écran

![Capture d'écran du jeu de Nim](/Captures/Nim1.png)
