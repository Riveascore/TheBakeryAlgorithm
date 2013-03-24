(def numberOfCustomers 10)

(let [s {}]
  (into s (for [i (range 0 numberOfCustomers)] 
            {(keyword (str "customer" i)) 6})))

;{(keyword (str "customer" 5)) 5}

;(createPerson x "customer")

(let [s {}]
  (into s (for [i (range 0 numberOfCustomers)] 
            {(keyword (str "customer" i)) (createPerson i "customer")})))




(def queue (atom clojure.lang.PersistentQueue/EMPTY))
(swap! queue conj 1)
(swap! queue conj 2)
(swap! queue conj 3)
;(seq @queue)
;(dequeue! queue)
;(seq @queue)
(println (dequeue! queue))
(swap! queue conj 4)
(println (dequeue! queue))
(println (dequeue! queue))
(println (dequeue! queue))
