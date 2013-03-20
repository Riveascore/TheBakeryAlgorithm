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

(defn fillQueue [index ourQueue] 
  (if (< index (count servers))
    (fillQueue (inc index) (conj ourQueue (nth servers index))) 
    ourQueue
  )
)

;servers need to do some sort of CPU intensive computation on behalf of the customer
(defn serveCustomer [server customer now-serving]
  ;(fib (rand-int 50)) ;<-- CPU intensive computation
  ; (assoc now-serving :value (inc (get now-serving :value))) <-- return this to everything that needs to look at "now-serving" all the time
  ; (conj freeServers server) <-- return this to everything that needs to be looking at "freeServers" all the time
  ;(inc now-serving)
)

;	When we start the system up, each customer should wait a random amount of time 
;	(something like (Thread/sleep (rand-int 1000)) will probably work fine) 
;	before taking a number. 

;quick example
(make-people 10 15)
(def originalFreeServers (fillQueue 0 clojure.lang.PersistentQueue/EMPTY))
;quick example



