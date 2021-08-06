# story 1
[X] Case1  
Given a parking lot, and a car 
When park the car  
Then return a parking ticket.

[X] Case2
Given a parking lot, and a ticket
When fetch the car
then return a car.

[X] Case3
Given a parking lot, with 2 parked cars and 2 parking tickets
When fetch the car twice
then return the right car with each ticket

[X] Case4
Given a parking lot, and a wrong ticket
When fetch the car
then return no car

[] Case5
Given a parking lot, used ticket
When fetch the car
then return no car

[] Case6
Given a full parking lot, and a car
When park the car
then return no ticket.






[] Case7
Given a parking lot, and null car
When park the car
should return exception ("You have no car")

[] Case8
Given a parking lot, and parked car
When park the car
should return exception ("Car is already parked")

