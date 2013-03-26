(load "OurQueue")

(defn createPerson [id type]
  (if (= type "customer")
    (atom {:id id :ticket-number nil :type "customer" :result nil})
    (agent {:id id :type "server"})
  )
)

(def freeServers (atom clojure.lang.PersistentQueue/EMPTY))

(defn make-people [numberOfCustomers numberOfServers]
  
  (def customer-list
    (map #(createPerson % "customer") (range 0 numberOfCustomers))
    ;we gotta add these to a list to work with them SOMEHOW!
    ;just make list of atoms, s'all good
    ;customers are now atoms instead of being stored in a map (mapception...)
  )

  (doseq [idNumber (range numberOfCustomers (+ numberOfCustomers numberOfServers))]
    (def ourServer (createPerson idNumber "server"))
    ;servers are now agents^
    
    (swap! freeServers conj ourServer)
    ;we push^ servers onto the queue of freeServers right away
  )
)


(make-people 30 6)