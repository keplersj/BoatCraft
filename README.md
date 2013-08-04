BoatCraft [![Travis CI Build Status](https://travis-ci.org/k2b6s9j/BoatCraft.png?branch=master)](https://travis-ci.org/k2b6s9j/BoatCraft)
=========

Rails had their turn, now out to the Minecraftian seas. Out there we will inteact with our boats in ways that we had only dreamed of and ways that we had never though of before. This mod will overhaul your oceans and how you will exerience them. Will you turn your sealine into an industrial outlet, or will you take advantage of your near by sea port and get all the booty you could ever wish for? So go ahead take your world out to sea and never worry about a creeper destroying your rails again, but watch out becuase one day you might turn around and see them, the pirates!

Special Thanks goes out to:
- SeargeDP, for creating ModJam and giving me the perfect oppurtunity to make this mod
- CiderCraft, for creating his CiderBoats addon for IC2 and giving me and inspiration
- Sargunster, for creating his Better than Wolves boat addon and giving me huge inspiration
- CovertJaguar, for creating Railcraft and being one of the biggest inspiractions.

## Goals (Not even begun work on, might not even happen)
- ExtraBiomesXL wood support
- Seaport Villages
- RailCraft Integration
- Forestry Integration
- IndustrialCraft2 Integration
- ThermalExpansion Integration
- GregTech Integration
- Universal Electricity Integration
- ThaumCraft integration
- Boats that hold Fluids and don't drown! (Tank Boats)
- Boat Linking
- Boats acting like tools based on material, varying in speed, durability, and yes enchantibility based on the material
- Blocks that can load and unload content into boats
- Blocks that can affect boats in the water
- And one day, STEAM BOATS!
- PIRATES!
- Proper docking
- Sails
- Weapons (Fire Cannons, Lasers)
- Galgadorian boats
- Buoys
- Much more

## Features (Currently implemented)
- Boat Items for the 4 Vanilla Woods
- API allowing other mods to add blocks to boats (Kinda. A mod support page will be in the wiki some time after ModJam)

## Half-Features (Needs work)
- Dynamic Wooden Sticks (Again for the different wood types)
- Boats that contain all the same blocks that Vanilla Minecarts can contain (Chest, Furnace, TNT, Mob Spawner)

## The Build Enviroment
When I develop with Java I try to make my enviroment as git friendly as possible. This means that it is easy for anyone to download and play around with my code. When you clone the repository onto your computer open your command line and run 'ant' inside the repository's folder on your computer, this requires [ant](http://ant.apache.org/manual/install.html) to be installed on your computer.

All the ant commands:

`ant` Downloads all the dependancies, sets up the enviroment, compiles, and if all goes well give you a binary you can use in Minecraft

`ant copySRC` Typically used to copy newly cloned code into your build directory.

`ant copy-new-code` Typically used to copy newly created/edited code from the build directory, typically when used with Eclipse, into the root directory for use with git.

`ant test` Used for continuous integration testing, like Travis CI

## Summer ModJam 2013
[The ModJam Contest homepage!](http://mcp.ocean-labs.de/page.php?7)
