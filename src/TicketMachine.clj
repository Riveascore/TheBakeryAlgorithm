(load "NowServing")
(load "CustomerAndServer")

(def ticket-machine (atom -1))

(defn sleep-inc []
  @(future (Thread/sleep 1000) inc)
)
  
(defn next-number [] 
  (swap! ticket-machine (sleep-inc)))
         

(defn take-number [customer]
  (def id (:id customer))
  (def ticket-number (next-number))
  (add-watch now-serving (keyword (str id "_" ticket-number)) now-serving-watch)
  (def ticket-log (ref (str "Customer id: " id "     ticket-number: " ticket-number)))
;  (log-events ticket-log console events-log)
  (swap! now-serving identity)
)

(def customers-as-list (vals customer-list))
;(doseq [customer customers-as-list]
;  (take-number customer)
;)
(take-number (nth customers-as-list 0))
(take-number (nth customers-as-list 1))
(take-number (nth customers-as-list 2))
(take-number (nth customers-as-list 3))
(take-number (nth customers-as-list 4))
(take-number (nth customers-as-list 5))
(take-number (nth customers-as-list 6))
(take-number (nth customers-as-list 7))
(take-number (nth customers-as-list 8))
(take-number (nth customers-as-list 9))

