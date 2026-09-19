# Unite — gestion de projets étudiants (Java Swing)

Application de bureau Java Swing pour le suivi d'étudiants, de sujets, de groupes,
de tâches et de notes, adossée à une base MySQL.

---

## Aperçu

L'application gère le cycle de vie d'un projet étudiant : inscription des
étudiants, affectation à un groupe, attribution d'un sujet, découpage en tâches,
et prise de notes rattachées à une tâche.

Deux classes du paquet `POO` portent le modèle métier — `Tache` et `Note` — et
les écrans Swing (`login`, `Inscription`, `Dashboard`, `View`, `tache`, `note`)
dialoguent directement avec MySQL via JDBC.

> Ce projet partage la base de données `projet` avec
> [QtProject](https://github.com/NABIHAyman/QtProject) : il s'agit du même
> énoncé académique, traité une fois en Java Swing et une fois en C++/Qt.

---

## Stack technique

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-5382A1?style=flat-square)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white)

| Composant | Détail |
|---|---|
| Langage | Java |
| Interface | Swing, éditeur graphique NetBeans (fichiers `.form`) |
| Base de données | MySQL 8.0, accès par JDBC (`DriverManager`) |
| Build | Apache Ant (`build.xml`, projet NetBeans) |

---

## Fonctionnalités

- Écran de connexion (`login`)
- Inscription d'un étudiant (`Inscription`)
- Tableau de bord après authentification (`Dashboard`)
- Consultation des données (`View`)
- Gestion des tâches (`tache`) — entité `POO.Tache` : titre, dates de début et de
  fin, état, rattachement à un étudiant
- Gestion des notes (`note`) — entité `POO.Note` : titre, contenu, rattachement à
  une tâche

Tables utilisées : `etudiant`, `groupe`, `sujet`, `tache`, `note`.

---

## Prérequis

| Outil | Version |
|---|---|
| JDK | 8 ou supérieur |
| MySQL | 8.0 |
| Connecteur JDBC MySQL | `mysql-connector-j` |
| NetBeans | 12 ou supérieur, recommandé — le projet est au format NetBeans/Ant |

---

## Installation et lancement

```bash
git clone https://github.com/NABIHAyman/Unite-Java.git
cd Unite-Java
```

Créer la base de données :

```sql
CREATE DATABASE projet CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**Aucun script SQL n'est fourni.** Le schéma doit être reconstitué à la main à
partir des requêtes présentes dans les classes Swing.

La chaîne de connexion est écrite en dur dans `Dashboard.java` et
`Inscription.java` :

```java
private static final String dataConn = "jdbc:mysql://localhost:3306/projet";
```

Adapter cette constante, ainsi que `username` et `password`, à l'instance MySQL
locale.

### Sous NetBeans

Ouvrir le dossier `Unite-Java/` comme projet existant, ajouter le connecteur
JDBC MySQL aux bibliothèques du projet, puis lancer avec `F6`.

### En ligne de commande

```bash
cd Unite-Java
ant clean jar
java -cp "dist/Unite-Java.jar:lib/mysql-connector-j.jar" login
```

Sous Windows, remplacer `:` par `;` dans le `classpath`.

---

## Structure du projet

```
Unite-Java/
└── Unite-Java/
    ├── build.xml           # Build Ant généré par NetBeans
    ├── manifest.mf
    ├── nbproject/          # Métadonnées NetBeans
    └── src/
        ├── POO/
        │   ├── Tache.java  # Entité tâche
        │   └── Note.java   # Entité note
        ├── login.java      # Écran de connexion
        ├── Inscription.java# Inscription d'un étudiant
        ├── Dashboard.java  # Tableau de bord
        ├── View.java       # Consultation des données
        ├── tache.java      # Gestion des tâches
        ├── note.java       # Gestion des notes
        └── *.form          # Descripteurs d'interface NetBeans
```

---

## Captures d'écran

> *À compléter.* Emplacements prévus : écran de connexion, inscription,
> tableau de bord, gestion des tâches.

```
docs/screenshots/
├── login.png
├── inscription.png
├── dashboard.png
└── taches.png
```

---

## Statut

**Projet académique**, réalisé en 2025 pour un module de programmation orientée
objet et d'accès aux bases de données. Il n'a jamais été déployé.

Limites assumées pour un travail de TP : requêtes SQL et identifiants de
connexion écrits en dur dans les classes d'interface, pas de couche d'accès aux
données séparée, pas de tests automatisés, et aucun script de création de schéma.

---

## Licence

Distribué sous licence [MIT](LICENSE) — © 2026 Ayman NABIH.

---

## Auteur

**Ayman NABIH**
[github.com/NABIHAyman](https://github.com/NABIHAyman) ·
[linkedin.com/in/nabihayman](https://linkedin.com/in/nabihayman)
