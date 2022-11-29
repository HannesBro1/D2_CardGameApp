# Exercise project "CardGameApp"
Playing Cards is FUN!

![Logo](logo.jpg)

## Team
- Eric Langer (Docs)
- Felix Ossmann (Testing)
- Hannes Brottrager (Data)
- Markus Hilbert (Lead/Architecture)

## Targets
Create a small but extendable project in Java using OOP concepts and prozedures.

### Prozedures
- if/else
- case
- while/for
- early return

### OOP
- Encapsulation
- Inheritance
- Abstraction
- Interfaces
- Types
  - Primitive Types
  - Complex Types
- Classes
  - Constructors
  - Setters & Getters
  - Modifiers
    - public
    - private 
    - protected
    - abstract
    - final
  - Return value Type

### Java STD Classes:
- Scanner (Terminal input)
- StringBuffer
- File I/O
- Date

### Frameworks
- Logging via java.util.logging.*
- Testing via "JUnit"
- Code versioning via GIT

### Documentation
- Write a project documentation in MarkDown
- Write a code documentation with javadoc

## NOT targets
- a (G)UI
- a Gaming AI to play against
- a production ready application

# CardGameApp
Base idea of this project is to create an extensible Base of (abstract) Types, to create and play several different Card Games.
Each Game implements it's own set of rules how

Possible Games are: ([List of Card Games by Amount of Cards](https://de.wikipedia.org/wiki/Liste_von_Kartenspielen_nach_Kartenanzahl_geordnet))
- BlackJack (17+4)
- Classic Uno
- Schnapsen (Cards could be "Französisch" or "DoppelDeutsch")
  - Schnapsen
  - BauernSchnapsen
  - TalonSchnapsen (DreierSchnapsen)
- Rommé
- etc


### Diagram
<!-- insert image here -->
![Diagram](uml-models/overview.png)

### Card
Is an abstract class to represent a gaming card in a Deck of Cards.

### Card designs
- [Poker](https://www.piatnik-individual.com/produkt/4-eckzeichen-nur-rueckseite-gestalten-hochladen/)
- [Französische Schnapskarten](https://www.piatnik-individual.com/produkt/franzoesische-25-karten-nur-rueckseite-gestalten-hochladen/)
- [Doppeldeutsche Schnapskarten](https://www.piatnik-individual.com/produkt/doppeldeutsche-36-karten-nur-rueckseite-gestalten/)

### Deck
Is a set of Cards.
Each Card is unique in **most** decks.

### Player
Is a Person with a PlayerName and a Hand of Cards.

### Hand
Is a List of Cards taken by the Player.

### Game
The specific game itself. Depending on the game, rules, decks, amount of players, ... are different.

### Possible Exceptions
#### Game
- NoPlayerSet
- TooManyPlayers
- NotEnoughPlayers
- PlayerNotOldEnough
- NoMoreCardsOnDeck
- DeckIsEmpty

### Interfaces
... still to come on a more suffisticated implementation.

### Code Structure
Use [Maven Styled Structure](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
- cardgame
  - src
    - main/java/ddb/international
      - card
        - Card.java
        - PokerCard.java
        - images
      - deck
        - Deck.java
        - PokerDeck.java
        - cards
          - uno.csv
          - schnapsen.csv|.json    
      - pile
        - 
      - person
      - game
        - Game.java
        - PokerGame.java
        - BlackJackGame.java
    - test
      - CardTest.java
      - PokerCardTest.java
    - resources
      - cards
        - uno.csv
        - schnapsen.csv|.json
      - gamerules
        - uno.md
        - blackjack.md


## Storage (optional)
- Hibernate (ORM)
