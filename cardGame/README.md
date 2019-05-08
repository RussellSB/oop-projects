# Card Game - An MVC Demo Program++

Card Game is a program that contains all the elements of a program with a
graphical user interface, and then some. The program is one where you draw
cards onto a discard pile, and then shuffle the cards back into the deck.

## Controls

1. Buttons: The buttons enable immediate activation of an action. Drawing
a card, shuffling all cards and starting a new game are all available this way.
2. Hotkeys: The window doesn't ask keyboard focus by default, but each button
has a hotkey. Press [Alt]+[N] for a new game, [Alt]+[D] for draw and [Alt]+[S]
for shuffling.
3. Mouse: You can drag cards to the Discard pile by pressing the left mouse
button over the top card of the deck and dragging it to the discard pile.
4. Mouse options: You can toggle the behaviour of the dragging using the check
box. If you drag your card out of bounds you can reset it with this too

## Design

Basic structure is Model-View-Controller.
 - The Model is divided into 3 packages
   ..- The Data package holds basic representations of game items
   ..- The Game package holds a game abstraction. It also contains a proxy
       which allows the model to be changed at runtime without breaking the
       other classes.
   ..- The Positions packages translates a model to a set of position-annotated
       cards for the view to draw. The positionmodel decorates the game, and is
       itself decorated to enable moving of cards
   ..- The Util package has some util classes that prevent the confusion of
       int width, int height, and passing (height, width) by giving these their
       own, incompatible type.
 - The Controller has only the basic actions and a mouse support class
 - The View has views for both model and controller, which is a delicate topic,
   because you can click buttons. The button shows the Action in the GUI, and
   is therefore mostly a view, the click is forwarded to the Action, making the
   action effectively the controller, although the Button controls the action,
   which is confusing and best not to think of too much.
   
### Game Model Structure

The game model is divided into multiple layers:
 - The Data Layer (basic game items)
 - The Game Layer (aggregation of game items with game logic)
 - The Proxy Layer (to allow the game to be swapped easily)
 - The Position Layer (to tell the view where to draw cards)
 - The Movement Layer (to enable movement of positioned game items)
   
These layers use the decorator pattern (and the proxy pattern) extensively,
allowing the individual features to be added in distinct classes, that don't
need to bother with the logic of lower layers anymore. While they through the
use of inheritance must all provide certain functionality, they do so by
forwarding calls to the actual implementation, so there is virtually no code
that's unrelated to the specific functionality of the class.

## TODOs

The PositionGenerationModel class is too large. We could move the position
information to an intermediary class since the MovementModel depends on it too.
