# InventoryTracker
Inventory Tracker for DnD Campaign

## How it works
All the actual code for the project is stored under the inventory.src.main.java.roberts.inventory directory. Web services
(URL Endpoints) are in the business.services directory. These handle all of the business logic. Under the configuration
folder is a CORS Configurator that will prevent browsers from restricting access to the endpoints due to Cross-Origin Policy.
Under the data directory, there are automatically generated entity classes that roughly correspond to the database schema.
These are used to interact with data queried from the database. Under the data.repositories directory are Repository classes 
that are used to directly query the database.

The views (HTML) and their respective javascript are under src.main.resources.static.

This project uses PostgreSQL as it's database. I'll upload schema information for that eventually.

## Environment
I'm using IntelliJ IDEA (Ultimate) to develop this, so if you want to run the project from your IDE, you'll need that. Otherwise any editor
(VSCode, Atom, etc.) should be good enough for poking around. Also students get free licenses for IntelliJ, you've just gotta
google it.

I'm using Apache Maven as a build system, so if you want to be able to compile and run it without and IDE then you'll need that.

## Running
To compile and run the system without an IDE, first ensure that the database and maven have both been installed. Then,
navigate to the root project directory. From this directory, execute `mvn clean package`. Once the the command
has finished running, navigate to the target directory and run the generated .jar file (not .jar.original).

The project will not run if you do not a PostgreSql instance running, with the correct schema.