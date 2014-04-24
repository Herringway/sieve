(use 'clojure.set)
(use 'clojure.test)

(defn multiples-to-n
  "Generates a list of multiples for a given number up to the specified limit"
  [multiplier limit]
  (range (* 2 multiplier) (+ limit 1) multiplier) ; we skip multiplier * 1 because we don't want to include primes in the result
) ; multiples-to-n
(defn get-multiple-lists
  "Generates multiple lists for all numbers between 2 and the limit given"
  [limit]
  (->> (range 2 (/ limit 2))         ;generate a range of numbers between 2 and the given limit
    (map #(multiples-to-n % limit))) ;create the list of multiples for each number in the range
) ; get-multiple-lists
(defn sieve
  "Threaded implementation of the sieve of eratosthenes. Generates a list of prime numbers up to the given limit"
  [limit]
  (as-> (get-multiple-lists limit) $           ;generate lists of all possible multiples for each number up to the limit
    (apply difference (set (range 2 limit)) $) ;remove all generated multiples from the list of numbers
    (sort $))                                  ;not really necessary, but sorted output is nice.
) ; sieve

(deftest sieve-negative-input ;check that sieve can handle negative numbers
    (is (= () (sieve -1)))
    (is (= () (sieve -10)))
)
(deftest sieve-zero-input ;sieve should also handle zero appropriately
    (is (= () (sieve 0)))
)
(deftest sieve-low-positive-input ;trivial cases
    (is (= () (sieve 1)))
    (is (= () (sieve 2)))
    (is (= '(2) (sieve 3)))
    (is (= '(2 3) (sieve 4)))
)
(deftest sieve-positive-input ;non-trivial cases
    (is (= '(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97) (sieve 100)))
    (is (= '(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97) (sieve 101)))
    (is (= '(2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 101) (sieve 102)))
)
(deftest sieve-float-input ;floats should work just as well as integers
    (is (= '() (sieve -1.2)))
    (is (= '() (sieve 0.0)))
    (is (= '() (sieve 1.9999999)))
    (is (= '() (sieve 2.0)))
    (is (= '(2) (sieve 2.000000001)))
    (is (= '(2 3 5 7) (sieve 8.3)))
)
(deftest sieve-non-numeric-input ;none of these make sense in the context of numbers
    (is (= '() (sieve :stuff)))
    (is (= '() (sieve "gogo juice")))
    (is (= '() (sieve '(1 2 3))))
    (is (= '() (sieve \a)))
)

(println (sieve 100))
