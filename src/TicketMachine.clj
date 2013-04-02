(load "NowServing")
(load "CustomerAndServer")
(load "eventsLog")

(def ticket-machine (atom -1))
  
(defn next-number []
  (swap! ticket-machine inc))

(defn take-number [key]
  (def ticket (next-number))
  
  (swap! key assoc :ticket-number ticket) 
  
  ;here we log ticket events
  (def ticket-message (str "Customer: " (:id @key) " was given ticket: " ticket))
  (send events-log write ticket-message) 
  ;here we log ticket events
  
  (add-watch now-serving key now-serving-watch)
  
  (swap! now-serving identity)
)


(log-reference (atom 5) events-log)
(make-people 30 6)
;^create customers and servers
(doseq [customer customer-list]
  (take-number customer)
)
;^start giving customers tickets