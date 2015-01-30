# JavaRPG
A console-based Role-Playing game, for a school project.

## Specification

Classic textbased RPG game. It must include the following:

**Player**
* Can raise it’s level through killing monsters.
* Somekind of healthpool
* At least tree damaging skills
* Healthpool/Skill damage scales with level

**Monster**
* Healthpool
* At least one damage skill
* Higher level monsters should hit harder/have lager healthpools

**Map/Levels**
* The player must be able to walk around a map, one step at a time
* Levels must be loaded from a textfile
* When a player steps onto a field with a mob, the game must enter combat mode

**Combat mode**
* Turn based
* If the player wins, he is awarded xp/level
* If the player dies, he’s bounced back to the previous field
