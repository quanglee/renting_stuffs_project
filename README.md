# Renting Stuffs Project

The project help users share theirs barely used items to get some money and also get their demand items instead of buying new one.

# Mock-up database

Run database/main.sql to create all table and mock-up data

0. Need to register with 2 emails "user1@shareandget.com" (user1) and "user2@shareandget.com" (user2) to match with the mock-up data as describe below
1. user1 has 5 items (id from 1-5) in items table; user2 has 5 items (id from 6-10) in items table (Hint: the price of item divide by 10 to get its id. Ex: item id = 10 has price is 100 and its owner is user2)
2. In bookings table, there are 3 bookings of user2 booking 3 items from user1 (items that have id 1,2 and 3)

# Features
1. Users can Register new account
2. Users can Edit their info
3. Users can Add/Edit items that they want to share with others
4. Users can Browser/Search all items
5. Users can Add/Remove items to their own wishlist, then receive notification when these wishlist items are available to book
6. Users can view Item Details and Item Reviews
7. Users (as borrowers) can Book items
8. Users (as owners) can Accept/Reject requests that book their items
9. Users (as owners) can Mark the booking as Done
10. Users (as borrowers) can Review the items that they have used  

# Business rule

## Booking
1. Owners cannot book their items
2. Cannot book items that have booking status 'pending' (if an item is booking by someone, then other borrowers cannot book it until the booking is canceled by the borrower or the item's owner accept/reject the booking)
3. Cannot book items if pick-up and return-time conflict with current schedule of 'accepted' bookings
4. Borrowers can cancel their bookings if they still have status 'pending' (owners of these items do not process their requests)
5. Owners can 'reject' or 'accept' requests, after 'accept' requests they can mark the requests as 'done' (when they received their items back from borrowers)  

## Wishlist
1. Users cannot Add/Remove their own items
2. Users can Add/Remove items of others users that they want to book 
3. When bookings of these wishlist items change status from 'pending'  to either 'accepted' or 'rejected', wishlist's owner will receive notification state that these items are available to book

## Review
1. Borrower can review the item that they have used when the booking status change to 'done' by the item's owner
2. Borrower can give only one review per booking