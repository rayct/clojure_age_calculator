## Here's an example program in Clojure that calculates a person's age and the number of days until their next birthday:

## This program will prompt the user for their date of birth, and then it will calculate current age, the day of the week you were born on, and the number of days until your next birthday.

## To use the program, simply run it in a IDE or code editor environment and follow the prompts.

# Version: 1.0.2
## Here is the optimized version of the Clojure code: Use read-line instead of (io/reader): read-line is a more concise and idiomatic way to read user input from the command line. Use clj-time.coerce/from-string instead of manual date parsing: from-string method can be used to parse a date string into a org.joda.time.DateTime object. Use (quot) instead of (/) and Math/floor: The (quot) function performs integer division, so it is more efficient and accurate than using (/) and Math/floor) to calculate the age in years. Use clj-time.coerce/to-date instead of clj-time.core/today: to-date is a faster alternative to today as it doesn't create a new date object every time it's called. The optimised code eliminates unnecessary method calls, uses more efficient operations, and eliminates redundant conversion of date objects.


# Version: 1.0
## Here's a program in Clojure that calculates your age and the number of days until your next birthday: In this program, we use the clj-time library to handle date and time calculations. We define four functions: read-date prompts the user to enter their date of birth and returns the input as a string. parse-date takes a date string and parses it into a DateTime object. age-in-years takes a birthdate and calculates the age in years using the number of days between the birthdate and the current date. days-to-next-birthday takes a birthdate and calculates the number of days until the next birthday. In the -main function, we read the date from the user input, parse it into a DateTime object, calculate the age and the number of days until the next birthday, and print out the results.

## Ray C. Turner