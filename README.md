# egen Challenge
Build a system that works like an IoT platform – in this case, a personal weight tracker. This system is responsible for,
•	Consuming data sent from different sensors (emulators)

•	Storing the received data in MongoDB

•	Running the data through different rules to make basic predictions

## Dependency
1. Maven
2. Springframework(boot)
3. EasyRules
4. Morphia
5. Gson
6. Mongodb

## Instructions for running the application
1. Download the repository by the running this command "git clone https://github.com/cshekhar1337/egenjava.git"
2. go inside folder egenjava
3. run the command on the terminal __"mvn spring-boot:run"__

or Import the application to the IDE(like intellij or eclipse which has maven and java configured) and execute it

## Instruction to run Sensor Simulator with the application
1. You should have the application already running then only execute this 
2. go inside folder egenjava 
3. Now in the terminal run this __"java -jar -Dbase.value=150 api.url=http://localhost:8080/metrics/create/ sensor-emulator.jar"__ 



