# Near-Me
A simple Java application to test the use of NoSql database. We use Redis, in-memory key-value database.
The dataset consists of a list of restaurants with data relating to both the GPS position and the cost, evaluations, services offered, type of cuisine.
The application extracts the data from the dataset (in csv format) and uploads the data to Redis. 
The application performs some query tests by simulating the user's GPS position and extrapolating information about restaurants within a certain radius of the user's location.
The geospatial indexes offered by Redis are used to perform these queries.

Project developed during the Database Design with New Generation Models course @ [Department of Computer Science](https://www.uniba.it/ricerca/dipartimenti/informatica) @ [University of Bari "Aldo Moro"](http://www.uniba.it/)
under the supervision of dr. [Nicola Fanizzi](http://lacam.di.uniba.it:8000/people/nicola.html).

## Prerequisites
By default, requirement for compilation are:
* JDK 8+
* Maven  
Requirement for running application is:
* Redis 3+

## Built with Maven
To create a jar file with dependencies including resource files:
````
$ mvn install
````

## Using Near-Me
Application can be run through jar from root folder by typing into terminal:
````
$ java -jar target/Near-Me-1.0.jar
````

## License
This software is released under the MIT License. See the [LICENSE](LICENSE) file for details.
