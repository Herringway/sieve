(use 'clojure.set)
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
(println (sieve 100))