# Molecular Mass Calculation Game

## Calculating common molecule mass used in middle school



This game is designed for **junior high school students** who just started their Chemistry class and are still not 
familiar with basic molecular mass calculation. 

There will be three animals (dog, cat, and quokka) in this game. To win this game, the player has to make all three 
animals happy, by getting each question right. After he/she gets a question right, he/she will get to choose one of the 
treats (bone, fish, leaf, apple, carrot) to feed the animal. Each treat has different points of satisfaction and 
different emotions. To win the game all animals have to get 100 percent of satisfaction.  

This project interests me because when I was a chemistry tutor back in Taiwan, students had to quickly calculate each 
molecular mass in order to finish all the questions under specific amount of time. Calculating molecular mass is the
most basic ability for students learning chemistry. By getting familiar with it, students can save multiple time during 
exams and work on harder problems. 


**Features**:

- random common molecules

- three animals (dog, cat, quokka)
 
- five treats (bone, fish, leaf, apple, carrot)


##User Stories

In the context of a molecule calculation game application:

- As a user, I want to be able to see the molecule that I will calculate.

- As a user, I want to be able to enter the molecular weight of the molecule and see if it's correct.

- As a user, I want to be able to see the animal I will be feeding.

- As a user, I want to be able to select a treat from the list of treats and give it to the animal.

- As a user, I want to be able to add points to the animal's satisfaction percentage.

- As a user, I want to be able to add and remove molecules to the file and save it in the middle of the game.

- As a user, I want to be able to load the molecules I added in the middle of the game.

- As a user, I want to be able to save and load the whole game.

- As a user, I want to be able to end the game in the middle of the game.


## Phase 4 : Task 2:

- Includes a type hierarchy: Animals as abstract class, with 3 subclasses: dogs, cats, and quokka.
 
Overriding the abstract method: sound, showing the different sounds each animal makes.


## Phase 4 : Task3:

- If I had more time to work on this project,  I would redesign my MolecularQuiz class and Molecule class. Though these
 two are working just as I expected, it doesn't satisfy all different kinds of molecules. The Molecule class only 
 calculates the ones with the combination of atom (one letter only) and number (one number only), rather than the ones 
 with two letters atom or numbers larger than 9. 
 
 I would also refactor my main GUI (the MolarMassGame class), for it has lots of functions tied together making it 
 really hard to read. If I were to redo it, I would give each panel a class and then add it to the gui container,
  so when I have to change stuff, I don't need to spend a massive amount of time just to find the code. 
  It would make the code much cleaner and easier to edit. 