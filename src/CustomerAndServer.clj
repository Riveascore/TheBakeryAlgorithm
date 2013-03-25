(defn createPerson [id type]
  (if (= type "customer")
    {:id id :ticket-number nil :type "customer" :result nil}
    {:id id :type "server"}
  )
)

(def freeServers (atom clojure.lang.PersistentQueue/EMPTY))

(defn fillQueue [listOfServers] 
  (for [index (range (count listOfServers))]
    (swap! freeServers conj (nth listOfServers index))
  )
)

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
)

(defn add-to-queue [server]
  (swap! freeServers conj server)
)


(make-people 30 6)
;it's a giant bakery

(doseq [server servers]
  (swap! freeServers conj server)
)