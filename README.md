MusicLibraryPA165
=================

Java school project

How to run REST client:

1. Navigate to MusicLibraryRESTclient directory. It is the directory of the module with the client. RESTclient assume that REST API is deployed at the URL **http://localhost:8080/pa165/rest/** . If you run embedded tomcat, it is available at this particular URL.
2. Run command ***mvn exec:java -Dexec.args="ARGMUENTS"***

ARGUMENTS are:

METHOD  FORMAT  ENTITY  [ID]  [FILE]

We are accepting these combinations:

1. GET method:
  1. to get all entities (musicians/genres) type these arguments: **get [text|json] [musician|genre]** . To get all entities you can choose plain TEXT or JSON format.
     Choose text or json and musician or genre. Example **mvn exec:java -Dexec.args="get text musician"**
  2. to get one particular entity type these arguments: **get [text|json|xml] [musician|genre] ID**. To get one entity you can choose plain TEXT, XML or JSON format. For example if you want entity
  with ID 4 in xml, type: **mvn exec:java -Dexec.args="get xml musician 4"**
2. POST method:
  to create a new entity, you have to first create file in JSON or XML for musician or genre. Only these formats are supported. Here are the examples:
   

   XML for musician:

   ```
   <musicianDto>
   <name>Eminem</name>
   <biography>Rapper</biography>
   <musicianDto>
   ```
   JSON for musician:
   ```
   {"name":"Eminem", "biography":"Rapper"}
   ```
   XML for genre:
   ```
   <genreDto>
   <name>Rock</name>
   <description>One of the most beautiful genres in the history of a music</description>
   <genreDto>
   ```
   JSON for genre:
   ```
   {"name":"Rock", "description":"One of the most beautiful genres in the history of a music"}
   ```
  Once you have created the file, just type: **post [json|xml] [musician|genre] pathToFile**
     For example: **post xml musician newMusician.xml**  where newMusician.xml is in the directory of RESTclient module (the same from which you have to run mvn exec:java command). Your new musician/genre is created.
     
3. PUT method:
  to update a musician or genre just type as the arguments:  **put [json|xml] [musician|genre] ID newMusician.xml** where ID is the id of the musician/genre you want to update. Supported formats are again XML and JSON.

4. DELETE method:
   when you want to delete musician/genre, just type as the arguments: **delete [musician|genre] ID** where ID is the id of the musician/genre you want to remove. 
