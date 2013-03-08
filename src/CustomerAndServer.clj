(defn createPerson [id type]
  (if (= type "customer")
    [:id id :ticket-number nil :type "customer" :result nil]
    [:id id :type "server"]
  )
)

(defn make-people [numberOfCustomers numberOfServers]
  (def customers 
    (for [x (range 0 numberOfCustomers)]
      (createPerson x "customer")
    )
  )
  (def servers 
    (for [x (range numberOfCustomers (+ numberOfCustomers numberOfServers))]
      (createPerson x "server")
    )
  )
  
  (def freeServers servers)
  (def freeServers
	  (-> (clojure.lang.PersistentQueue/EMPTY)
	          (conj () (nth servers 1))
	          peek)
  )
)

(make-people 10 15)

;(conj freeServers [:id 500 :type "server"])
;(pop freeServers)

;(def poop (conj (conj clojure.lang.PersistentQueue/EMPTY 12) 9))
;
;(conj poop 3)
;
;(peek (pop (pop poop)))

(def stuff clojure.lang.PersistentQueue/EMPTY)

(peek (conj stuff 0))