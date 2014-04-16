(use 'clojure.set)
(defn multiples-to-n
	[multiplier limit]
	(->> (range (+ limit 1))
		(take-nth multiplier)
		(remove #(>= multiplier %)))
) ; multiples-to-n
(defn get-multiple-lists
	[limit]
	(->> (remove #(> 2 %) (range (/ limit 2)))
		(map #(multiples-to-n % limit)))
) ; get-multiple-lists
(defn sieve
	[limit]
	(as-> (get-multiple-lists limit) $
		(apply difference (set (remove #(> 2 %) (range limit))) $)
		(sort $))
) ; sieve
(println (sieve 10000))