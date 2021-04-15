# QBaubles
A mod to add more baubles in Minecraft. Requires the Baubles mod.
https://github.com/Azanor/Baubles

Current features include:

Craftable items:
--------------------
Shielding Belt: Deflects arrows when crouching.

Photosynthesis Amulet: Gradually restores hunger when in sunlight.

Partnership Ring: Pairs with another partnership ring. When two players wearing paired rings are near each other, they are granted additional max health. 48 block range.

Familiar Ring: Summons a ghostly wolf companion to defend you when you are attacked.


Uncraftable items:
--------------------
God Ring: Grants damage immunity.


And a wild meme appears...
--------------------
Stickbug mob: seeks out leaves to stand on. Does not yet dance.



Build instructions
--------------------
Extract the folder. Follow the readme.txt for setting up Gradle.
Use a Java 8 runtime environment. Include Baubles mod in your jar dependencies, tested to work with version 1.5.2.
Add Baubles-1.12-1.5.2-api.jar and Baubles-1.12-1.5.2-deobf.jar to a new "libs" folder in the project root.
Run "gradlew build". This will output a mod jar to build/libs/
