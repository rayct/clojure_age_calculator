;; Version: 1.0
;; Here's a program in Clojure that calculates your age and the number of days until your next birthday:
;; In this program, we use the clj-time library to handle date and time calculations. We define four functions:
;; read-date prompts the user to enter their date of birth and returns the input as a string.
;; parse-date takes a date string and parses it into a DateTime object.
;; age-in-years takes a birthdate and calculates the age in years using the number of days between the birthdate and the current date.
;; days-to-next-birthday takes a birthdate and calculates the number of days until the next birthday.
;; In the -main function, we read the date from the user input, parse it into a DateTime object, calculate the age and the number of days until the next birthday, and print out the results.

(ns age
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clj-time.core :as t]))

(defn read-date []
  (println "Enter your date of birth (format: DD/MM/YYYY): ")
  (str/trim (io/reader)))

(defn parse-date [date]
  (let [[d m y] (str/split date #"/")]
    (t/date-time (Integer/parseInt y) (Integer/parseInt m) (Integer/parseInt d))))

(defn age-in-years [birthdate]
  (-> (t/days (t/interval birthdate (t/now)))
      (/ 365.25)
      (Math/floor)))

(defn days-to-next-birthday [birthdate]
  (let [today (t/today)
        this-year-birthday (t/plus (t/with-date today (:year birthdate) (:month birthdate) (:day birthdate)) (t/days 365))
        days-to-birthday (t/days (t/interval today this-year-birthday))]
    (if (< days-to-birthday 0) (+ days-to-birthday 365) days-to-birthday)))

(defn -main []
  (let [date-str (read-date)
        birthdate (parse-date date-str)
        age (age-in-years birthdate)
        days-to-birthday (days-to-next-birthday birthdate)]
    (println (format "You are %d years old." age))
    (println (format "There are %d days until your next birthday." days-to-birthday))))


;; Version: 1.0.2
;; Here is the optimized version of the Clojure code:
;; Use read-line instead of (io/reader):
;; read-line is a more concise and idiomatic way to read user input from the command line.
;; Use clj-time.coerce/from-string instead of manual date parsing:
;; from-string method can be used to parse a date string into a org.joda.time.DateTime object.
;; Use (quot) instead of (/) and Math/floor:
;; The (quot) function performs integer division, so it is more efficient and accurate than using (/) and Math/floor) to calculate the age in years.
;; Use clj-time.coerce/to-date instead of clj-time.core/today:
;; to-date is a faster alternative to today as it doesn't create a new date object every time it's called.
;; Here's the optimized code:
;; This code eliminates unnecessary method calls, uses more efficient operations, and eliminates redundant conversion of date objects.

(ns age
  (:require [clojure.string :as str]
            [clj-time.coerce :as coerce]
            [clj-time.core :as t]))

(defn read-date []
  (println "Enter your date of birth (format: DD/MM/YYYY): ")
  (str/trim (read-line)))

(defn age-in-years [birthdate]
  (-> (t/days (t/interval birthdate (coerce/to-date (t/now))))
      (quot 365.25)))

(defn days-to-next-birthday [birthdate]
  (let [today (coerce/to-date (t/now))
        this-year-birthday (t/plus (t/with-date today (:year birthdate) (:month birthdate) (:day birthdate)) (t/days 365))
        days-to-birthday (t/days (t/interval today this-year-birthday))]
    (if (< days-to-birthday 0) (+ days-to-birthday 365) days-to-birthday)))

(defn -main []
  (let [date-str (read-date)
        birthdate (coerce/from-string date-str)
        age (age-in-years birthdate)
        days-to-birthday (days-to-next-birthday birthdate)]
    (println (format "You are %d years old." age))
    (println (format "There are %d days until your next birthday." days-to-birthday))))
