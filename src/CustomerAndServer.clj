(load "NowServing")

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
(defn serveCustomer [customer now-serving]
  
  (def server (pop (get now-serving :freeServers))
  (def waitingCustomers (dissoc (get now-serving :watchingCustomers) customer)
  (def freeServers HOWTOGETfreeServersPOSTPOP?)
  (def newValue (inc (get now-serving :value)))
  
  ;(fib (rand-int 50)) ;<-- CPU intensive computation
  
  (def now-serving-new
	  (makeNowServingObject ;[watchingCustomers value freeServers]
	    waitingCustomers    ; make a new object with changed values of waiting customers
	    newValue            ;new freeservers and the incremented value, then notify all customers
	    freeServers
	  )
  )
  (notifyCustomers now-serving-new) ;do this everytime VALUE is incremented
)

;	When we start the system up, each customer should wait a random amount of time 
;	(something like (Thread/sleep (rand-int 1000)) will probably work fine) 
;	before taking a number. 

;quick example
(make-people 10 15)
(def originalFreeServers (fillQueue 0 clojure.lang.PersistentQueue/EMPTY))
;quick example
















