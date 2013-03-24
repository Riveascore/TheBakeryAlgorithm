(load "NowServing")

(def ticket-machine (atom -1))

(defn next-number [] 
  (swap! ticket-machine inc))
  
(defn take-number [customer]
  (def id (:id customer))
  (def ticket-number (next-number))
  
  (assoc customer :ticket-number ticket-number)
  (add-watch now-serving (keyword (:id customer)) now-serving-watch)
  (def ticket-log (ref (str "Customer id: " id "     ticket-number: " ticket-number)))
  (log-events ticket-log console character-log)
  (swap! now-serving identity)
)
;  cust id
;  tick numb

