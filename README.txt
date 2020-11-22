Legends of Valor!

Author: Sandra Zhen, Ling Xie, Nathan Lauer

Please feel free to ask me any questions!

IMPORTANT: The output color will be much easier to view if using a light colored background terminal.
It will be difficult, if not almost impossible, to view the text on a dark colored background.

Description:
============
This repository contains code for a game, "Legends of Valor," in which users lead a team of Heroes 
in competition against of team of Monsters to reach the opposite Nexus. Heroes can buy/sell weapons,
armor, potions, and spells, and attack Monsters that are in range. Once a Hero kills a Monster, they
can advance past it, and the game is won once a Hero or Monster reaches the opposite Nexus.


Compilation Instructions:
=========================
$ find ./src/main -name "*.java" > sources.txt (We have already provided the sources.txt file, but feel free to regenerate it if desired)
$ javac @sources.txt
$ java -cp src main.Main


Notes:
======
A few notes to be aware of:

- There are a number of data files included, which define the Heroes, Monsters, and various GearItems that are
a part of the game. These files are included in the data directory.

- We wrote a number of unit tests, and they are included in the src/test directory. However, these tests will
not compile using the instrutions above, as the JUnit jars are not linked. The instructions above will ignore
the test directory, so this should not present a problem. However, if you'd like to run the tests, you'll 
need to link the JUnit Jupiter jar, which can be done manually, or perhaps with an IDE by loading this
repo to your preferred IDE.

CLASSES TO IGNORE:
==================
There are a number of classes that are leftover from the previous implementation of Heroes vs Monsters. These
classes are few, and they do not contribute to the implementation for Legeds of Valor. They are:
- Fight
- HeroesVsMonstersTurn
- HeroesVsMonstersRound
- PairHeroesAndMonsters
- RandomWorld
- RandomWorldBuilder

Design:
=======

We have organized this repository into a number of logical packages. They are:
1) Attributes
2) Fight
3) Games
4) Legends
5) Markets and Gear
6) Utils
7) World

Each of these packages contain a number of classes that logically fit within said package. In general, there is some
overlap between these packages, but we tried hard to keep them as independent as possible.

Details for each of these packages (which includes architectural design decisions):

1) Attributes:
Attributes represent entities that belong to a Legend - a Legend is a Hero or a Monster. These includes things like
Mana, HealthPower (Capped or Uncapped), Experience, and Level. Additionally, we have a concept called "Ability."
An Ability is basically a wrapper around a double, which represents an Ability that a Hero or a Monster have. For example,
Strength is an Ability, as is Dexterity.

- Every Ability has an AbilityType, which is an enum defining the various types of Abilities.
- HealthPower and Mana, while separate classes, are also considered Abilities, as they share many of the 
same characteristics of other Abilities.
- Abilities can be increased or decreased
- Abilities can also be cloned.

I also provide a HigherLevelComparator, which is used in a number of places to display Heroes and Monsters
by highest level first.

In general, these attributes are used in an Object composition fashion. That is, Heroes have Experience, Heroes
have a Strength Ability, etc. This makes it quite easy to compose each of these attributes and Abilities with
different Legends throughout the game.

2) Fight

In this package, we provide three types of classes:
- The classes used to implement the RoundExecutor and TurnExecutor interfaces from the games package. (NOTE: 
these only applied to the previous game, see section on classes to ignore)
- The class used to compose these together into a single Fight. (NOTE: see section on classes to ignore.)
- The FightMove class which represent the kinds of actions that Legends can take in a fight.

FightMoves are grouped into an inheritance hierarchy, with abstract class FightMove at the top. Each FightMove
concrete subclass represents an action that can be taken in a fight. These are further grouped into InternalMoves, 
and ExternalMoves. InternalMoves are moves which only affect the executing Legend, while ExternalMoves are
moves that affect Legends other than the executing Legend.

- Every fight move must implement the abstract method execute(), which allows for an easy interface to creating and
executing moves in a fight.

3) Games
This package defines the interfaces RoundExecutor and TurnExecutor, as well as the classes RoundBasedGame and
TurnBasedGame. 

- A RoundBasedGame uses the strategy pattern, by taking a RoundExecutor as input, and then calling each 
of its required functions sequentially until the game has finished.
- A TurnBasedGame also uses the strategy pattern, by taking a TurnExecutor as input, and then calling
each of its required functions sequentially until the game has finished.

We use these classes to implement the process of actually playing Legends of Valor. We do this by
implementing it as a RoundBasedGame, where each round is inherently a TurnBasedGame. This makes the 
process of running the game quite simple - the logic for the "flow" is contained in RoundBasedGame and
TurnBasedGame, while the specific implementations of each point in the game are built into the 
methods enforced by TurnExecutor and RoundExecutor.

4) Legends
Here, we define the Legends in the game, where a Legend is either a Hero or a Monster. Both Heroes and Monsters
are abstract classes, and we provide concrete subclasses for each of Heroes and Monsters. 

The concrete classes within Heroes don't really differe in behavior, which makes the inheritance tree slightly 
odd perhaps. However, the purpose is to easily differentiate between different types of Heroes. Additionally, it
is done to make the repository more easily extensible, as it is easy to imagine that certain types of Heroes
could further differentiate in their behaviors. 
- Note: different Heroes do differentiate slightly in their leveling up behavior. However, that logic is implemented
in the Experience class, as the behavior is reallt not that different - when leveling up, every type of Hero
makes gains in their HealthPower, Level, and nominal Abilities. The only difference is in the special abilities, and
each type of Hero exposes their special Abilities to clients. Thus, I decided not that it was simplest to implement
the leveling up behavior within the Experience class.

We also provide classes here to track the GearItems that a Hero owns, and the ones that are "active." That is, 
GearItems which the Hero has readily available for use during a fight. For example, a Hero may have 3 Weapons, 
where one of them is active. The active one is the Weapon that a Hero can use during a fight, and would be listed
in the ActiveGearItems class. However, the other two can only be found in the GearItemsList class, which would
also include the active Weapon; it tracks every GearItem that a Hero owns.

Finally, we provide a Singleton class LegendList, which contains every possible Legend in the game. It also
tracks the "chosenHeroes," which are the subset of Heroes that the user is playing with. These are chosen
automatically and randomly. Legends are read from the data files provided, the first time that the 
getInstance() method is invoked.

5) Markets and Gear
Here, we define two types of classes: GearItems, and the Market.

GearItems is an inheritance tree, and represents entities that Heroes can buy and sell. Those two behaviors are
the key identifier for what is considered a GearItem: any entity which can be bought from a Market, or any
entity which can be sold to an Market. We specify this by having GearItem implement the Buyable and Sellable
interface.

We also define Market clasees in this package. There are a number of important design decisions here:
- class MarketInventory contains every possible GearItem in the game. It is a Singleton class, and the
first time it is called it reads all GearItems from the data files provided.
- We provide a series of classes that construct a GearItem Factory - that is, a Factory which provides the 
Market with some GearItems. Right now, I only built a random gear item factory (a factory which provides
a random subset of Weapons, Armor, Spells, and Potions to a Market). I had hoped to build a Factory
which provides GearItems in line with the level of the relevant Heroes, but I didn't quite have time.
That would be a nice extension to this game.
- The Market itself, which takes a GearItemFactory, and gets its GearItems from the provided factory.
- Finally, a class MarketInteraction, which walks the user through the process of buying and selling
items with a certain Market.


6) Utils
This package defines some various utilities that are generic. However, there are a couple important 
architectural structures here: 

1) Outputables. Outputables are things which can be output to standard out in a standard format. 
For example, any Legend or GearItem can be output in a standard format. This makes it quite easy 
to maintain uniform output throughout the entire game.

2) User Commands: a number of classes that encapsulate the logic for getting input from the user.
This is defined as an inheritance hierarchy, with GetUserInput at the top. That class provides
a getNextLine() input, which actually retrieves the data that the user input. The point here, is
that since every other input class inherits from here, the GetUserInput class at the top allows the
for the user to enter Q/q or I/i at *any* point in the game! No matter what else is happening, 
those commands are always available.

7) World
The world uses the Factory pattern to be constructed, and right now I only provided a simple random
builder that follows the general outlines of the instructions. However, the architecture allows 
for other types of Worlds to be easily defined.

Every world is composed of a list of Cells, and Cells are instances of concrete subclasses which 
can be Common, NonAccessible, and Market Cells.

We also provide a WorldInteraction class which handles the process of allowing a user to move
throughout the world.

Finally, I provided some color when drawing the world, which should hopefully make it a bit easier
to see the board and what is going on.


