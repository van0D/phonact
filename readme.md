# Phonact

---

This small Java application is based on Spring, it was developed exclusively for test purposes and as a small example of use of the aforementioned framework.

## Main features
This application exposes CRUD operations via HttpREST for inserting, updating, reading and deleting South African telephone numbers.
They are integrated with the possibility of importing a list of telephone numbers from .CSV files into the application (as a test case, no mechanisms have been set up for uploading large data streams but the architecture is designed to satisfy the example case provided).
It is also possible to export the list of contacts present in the application in .CSV format.

The data entered in the application are subjected to a validation process before being persisted, if the telephone numbers entered do not comply with the standard formatting of South African telephone numbers, then an attempt will be made to automatically rectify the data.
This validation can be carried out without necessarily persisting the data through a special endpoint exposed separately.

Standard listen port on localhost:8080

### Requirements

| Conponent | version |
| ----------| ------- |
| Java      | 11+     |
| Maven     | 3.8     |

### Installation/Compiling 
Spostarsi nella cartella del progetto:

    cd [...]/phonact

Maven build:

    mvn install

Run the application:

    java -jar target/phonact-0.0.1-SNAPSHOT.jar

## Datasource
H2 in-memory database located in ./DB without authentication. The db file is already present with the configured schema

## Endpoints description
   
      [GET] /phonact-api/contacts

      Query Param: corrected (Boolean), not required

      get al contacts elements, 'corrected' query param is used to filter between auto corrected contacts and non.
      without 'corrected' query param the app will returns all persisted records.

      ---

      [GET] /phonact-api/contacts/{id}
      
      return contact with specified id number.

      ---

      [POST] /phonact-api/contacts
      
      insert new contact or update if alrady exist.
      Body: 
      {
         "id": null,
         "phoneNumber": 123456789
      }
      return the corrected phone number if corrected.

      ---

      [POST] /phonact-api/contacts/upload
      
      import .csv file formed by ID and PHONNUMBER headers.
      return te list of usable phone number (corrected and no corrected) and non usbable (non persisted) elements.

      ---

      [GET] /phonact-api/contacts/download

      export all records in a .csv file, formed by id,phoneNumber,corrected and originalPhoneNumber headers.

      ---

      [GET] /verify/{phoneNumber}
      
      this endpoint verify the phone number as path param. return detail on corrections.

      ---
