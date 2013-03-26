(load "OurQueue")

(defn createPerson [id type]
  (if (= type "customer")
    (atom {:id id :ticket-number nil :type "customer" :result nil})
    {:id id :type "server"}
  )
)

(def freeServers (atom clojure.lang.PersistentQueue/EMPTY))

(defn make-people [numberOfCustomers numberOfServers]
  
  (def customer-list
    (map #(createPerson % "customer") (range 0 numberOfCustomers))
  )

  (doseq [idNumber (range numberOfCustomers (+ numberOfCustomers numberOfServers))]
    (def ourServer (createPerson idNumber "server"))
    ;servers are now agents^
    
    (swap! freeServers conj ourServer)
    ;we push^ servers onto the queue of freeServers right away
  )
)