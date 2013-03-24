(defn createPerson [id type]
  (if (= type "customer")
    [:id id :ticket-number nil :type "customer" :result nil]
    [:id id :type "server"]
  )
)

(defn fillQueue [ourQueue] 
  (for [index (range (count servers))]
    (swap! ourQueue conj (nth servers index))
  )
)
;  (if (< index (count servers))
;    (swap! ourQueue conj (nth servers index))
;    (fillQueue (inc index) ourQueue)
;  )



(defn make-people [numberOfCustomers numberOfServers]
  (def customer-list 
    (let [s {}]
      (into s (for [i (range 0 numberOfCustomers)] 
        {(keyword (str "customer" i)) (createPerson i "customer")})))      

  )
  (def servers 
    (for [x (range numberOfCustomers (+ numberOfCustomers numberOfServers))]
      (createPerson x "server")
    )
  )
  
  (def freeServers (atom clojure.lang.PersistentQueue/EMPTY))
  (fillQueue freeServers)
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

(make-people 10 15)



