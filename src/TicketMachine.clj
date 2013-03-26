(load "NowServing")
(load "CustomerAndServer")

(def ticket-machine (atom -1))
  
(defn next-number []
  (swap! ticket-machine inc))

(defn take-number [key]
  (def ticket (next-number))
  
  (println "customer: " (:id @key) " was given ticket: " ticket)
  (swap! key assoc :ticket-number ticket) 
  
  
  (add-watch now-serving key now-serving-watch)
  
;  (def ticket-log (ref (str "Customer id: " id "     ticket-number: " ticket-number)))
;  (log-events ticket-log console events-log)

  (swap! now-serving identity)
)

(map #(take-number %) customer-list)
;^start giving customers tickets
