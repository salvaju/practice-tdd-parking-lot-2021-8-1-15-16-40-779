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

[X] Case5
Given a parking lot, used ticket
When fetch the car
then return no car

[X] Case6
Given a full parking lot, and a car
When park the car
then return no ticket.


# story 2
[X] Case1  
Given a parking lot, and a wrong ticket
When fetch the car  
Then return no car and error message "Unrecognized parking ticket".

[X] Case2 
Given a parking lot, and a used ticket
When fetch the car  
Then return no car and error message "Unrecognized parking ticket".

[X] Case3
Given a full parking lot, and a car
When park the car
then return error message "No available position"


# story 3
[X] Case1  
Given a parking lot, and a car
When Parking boy parks the car  
Then return a parking ticket.

[X] Case2
Given a parking lot, and a ticket
When Parking boy fetches the car
then return a car.

[X] Case3
Given a parking lot, with 2 parked cars and 2 parking tickets
When Parking boy fetches the car twice
then return the right car with each ticket

[X] Case4  
Given a parking lot, and a wrong ticket
When Parking boy fetches the car  
Then return no car and error message "Unrecognized parking ticket".

[X] Case5
Given a parking lot, and a used ticket
When Parking boy fetches the car  
Then return no car and error message "Unrecognized parking ticket".

[X] Case6
Given a full parking lot, and a car
When Parking boy parks the car
then return error message "No available position"

# story 4
[X] Case1  
Given a parkingboy, two parking lots with available position and a car
When Parking boy parks the car  
Then the car will be parked to the first parking lot.

[X] Case2
Given a parkingboy, two parking lots but the first is full, the second is available, and a car
When Parking boy parks the car  
Then the car will be parked to the second parking lot.

[X] Case3
Given a parkingboy, two parking lots but both with two parked car and two parking ticket
When Parking boy fetches the car twice 
Then return the right car with each ticket

[] Case4
Given a parkingboy, two parking lots and an unrecognized ticket
When Parking boy fetches the car
Then return no car and error message "Unrecognized parking ticket".

[] Case5
Given a parkingboy, two parking lots and a used ticket
When Parking boy fetches the car
Then return no car and error message "Unrecognized parking ticket".

[] Case6
Given a parkingboy, two parking lots both without any position and a car
When Parking boy parks the car
then return error message "No available position"