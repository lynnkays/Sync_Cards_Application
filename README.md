# Leankit Service Now Sync
This is a Java application that uses Service Now API and the Leankit API to add certain Service Now cards to our Leankit Board.

This will first pull all Incidents that are currently assigned to Application_QA and assign all those incidents to Identity Management. Then will parse through the response to create a Leankit card. It will then check the Incident number with all the cards already on the board, and will add it if the card is not there. The same process is used for Request Items, however their assignment group does not get changed. 

This application is currently running in AWS Lambda, and will run every 15 minutes. There are logs in Cloud Watch that will detail what cards are added to the board and if there are any issues with the API calls. 

## Technologies Used in this Project
* Maven 3.6.0
* Java 8
* AWS Lambda Java Core 1.1.0

## External Resources 
### Web application.services
This application uses Service Now REST Table API to get all the Service Now Data from the Incident and Request Items tables. It also uses the application API -v1 for adding all the cards to the Leankit board. 




