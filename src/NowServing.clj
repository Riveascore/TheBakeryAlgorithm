(load "CustomerAndServer")
(load "eventsLog")

(def now-serving (atom -1))

(defn serve-next [] 
  (swap! now-serving inc)
  ;(println (str "new value: " now-serving))
  (def now-serving-log (ref (str "Serving Customer Number: " @now-serving)))
  (log-events now-serving-log console events-log)
)


(defn now-serving-watch [key identity old new]
  (def customer
    (key customer-list))
  (if (= new (:ticket-number customer))
    ;(serve customer)
    true
  )
)




   (serve-next)
   (serve-next) 