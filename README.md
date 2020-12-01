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