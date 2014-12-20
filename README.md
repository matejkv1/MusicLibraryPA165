MusicLibraryPA165
=================

Java school project

How to run REST client:

1. Navigate to MusicLibraryRESTclient directory. It is the directory of the module with the client.
2. Run command ***mvn exec:java -Dexec.args="ARGMUENTS"

ARGUMENTS are:

METHOD  FORMAT  ENTITY  [ID]  [FILE]

We are accepting these combinations:

1. GET method:
  1. to get all entities (musicians/genres) type these arguments: **get [text|json] [musician|genre]** . 
     Choose text or json and musician or genre. Example **mvn exec:java -Dexec.args="get text musician"**
  2. to get one particular entity type these arguments: **get [text|json|xml] [musician|genre] ID**. For example if you want entity
  with ID 4 in xml, type: **mvn exec:java -Dexec.args="get xml musician 4"**


