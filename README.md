BoatCraft [![Travis CI Build Status](https://travis-ci.org/k2b6s9j/BoatCraft.png?branch=master)](https://travis-ci.org/k2b6s9j/BoatCraft)
=========

Let's see if we can make Minecraft boats just a bit cooler shall we? Kepler Sticka-Jones' entry for Summer ModJam 2013.

## Goals (Not even begun work on, might now even happen)
- Dynamic Wooden Boat textures (For the different wood types)
- Initial Biomes O Plenty wood support
- Initial Forestry wood support
- Initial Natura wood support
- Initial Binnie's Mod wood support
- Seaport Villages
- Boats that contain all the same blocks that Vanilla Minecarts can contain (Chest, Furnace, TNT, Mob Spawner)
- RailCraft Integration
- Forestry Integration
- IndustrialCraft2 Integration
- ThermalExpansion Integration
- GregTech Integration
- Universal Electricity Integration
- Boats that hold Fluids and don't drown! (Tank Boats)
- Boat Linking
- API allowing other mods to add blocks to boats
- Boats acting like tools based on material, varying in speed, durability, and yes enchantibility based on the material
- Blocks that can load and unload content into boats
- Blocks that can affect boats in the water
- And one day, STEAM BOATS!

## Features (Currently implemented)
- Boat Items for the 4 Vanilla Woods

## Half-Features (Needs work)
- Dynamic Wooden Sticks (Again for the different wood types)

## The Build Enviroment
When I develop with Java I try to make my enviroment as git friendly as possible. This means that it is easy for anyone to download and play around with my code. When you clone the repository onto your computer open your command line and run 'ant' inside the repository's folder on your computer, this requires [ant](http://ant.apache.org/manual/install.html) to be installed on your computer.

All the ant commands:

`ant` Downloads all the dependancies, sets up the enviroment, compiles, and if all goes well give you a binary you can use in Minecraft

`ant copySRC` Typically used to copy newly cloned code into your build directory.

`ant copy-new-code` Typically used to copy newly created/edited code from the build directory, typically when used with Eclipse, into the root directory for use with git.

`ant test` Used for continuous integration testing, like Travis CI

## Summer ModJam 2013
[The ModJam Contest homepage!](http://mcp.ocean-labs.de/page.php?7)
