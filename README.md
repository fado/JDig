JDig
====

A tool for the automatic generation of LPC class files for Epitaph developers.

Huh?
====

Well, when working on a MUD, developers tend to map out areas either by hand, or by making little text files like this:

![Ascii map...](https://i.imgur.com/BKJHnRn.png)


JDig takes those ASCII map files and converts them into the individual LPC files for the area, which saves a LOT of time (hopefully).  As iterative development goes forward, I'll be introducing (-) a GUI, (-) a means of procedural generation, (-) a way to work back from existing areas into decent looking maps, to name just a few features.  A more comprehensive list of functional requirements is just below this...

Function requirements!
======================

Told you so.

- Take an ASCII representation of an area map and convert it to a set of LPC class files.
- Provide a user interface that will allow developers to draw maps within the client and subsequently convert those maps into LPC class files.
- Allow users to assign attributes to each room/node within the map.  Such attributes include:
 - Items within the room.
 - Light levels.
 - Weather.
 - Room chats, i.e. strings that are occasionally output to the user at random intervals, perhaps representing sounds or physical activities that are happening within the room.
- Procedural generation of maps.
