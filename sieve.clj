(use 'clojure.set)
(defn multiples-to-n
	[multiplier limit]
	(range (* 2 multiplier) (+ limit 1) multiplier)
) ; multiples-to-n
(defn get-multiple-lists
	[limit]
	(->> (range 2 (/ limit 2))
		(map #(multiples-to-n % limit)))
) ; get-multiple-lists
(defn sieve
	[limit]
	(as-> (get-multiple-lists limit) $
		(apply difference (set (range 2 limit)) $)
		(sort $))
) ; sieve
(println (sieve 100))