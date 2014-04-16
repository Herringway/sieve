(use 'clojure.set)
(defn multiples-to-n
	[multiplier limit]
	(remove #(>= multiplier %) (take-nth multiplier (range (+ limit 1))))
) ; multiples-to-n
(defn get-multiple-lists
	[limit]
	(map #(multiples-to-n % limit) (remove #(> 2 %) (range limit)))
)
(defn sieve
	[limit]
	(sort (apply difference (set (range limit)) (get-multiple-lists limit)))
)
(println (sieve 1000))