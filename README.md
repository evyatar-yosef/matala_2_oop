# matala_2_oop

# Overview: 
In this assignment we were asked to implement the observer pattern by using the undoablestringbuilder class from the previous assignment to update specific text for all the customers registered in the database. We used classes to describe the sender of the updates and the receivers of the updates and to perform various actions.

# Observer pattern:
Observer is a behavioral design pattern that lets you define a subscription mechanism to notify multiple objects about any events that happen to the object they're observing.

# GroupAdmin:
This class implements the Sender interface which describes the sender of the updates (observerable), the class contains the state pool (undoablestringbuilder type object) and a client pool (ArrayList) that should receive updates on any changes made to the state pool.
This class contains several methods whose function is to add/remove a customer from the customer database and other methods whose purpose is to make changes to the sender of the updates.

# ConcreteMember:
This class implements the Member interface which describes the recipient of the updates, the class contains a shallow copy of the sender of the updates.
This class contains a method that is used to update this specific member according to the GroupAdmin.
 
 
# Test:
We have added a test department whose role is to make sure that the functions from the various departments work as expected and in addition so that we can track the sizes of the various objects in the memory and the changes that are made in the memory during the run
