(load "eventsLog")
(load "CustomerAndServer")


(def now-serving (atom 0))
;our now serving sign^

(defn serve-next [] 
  (swap! now-serving inc)
  (def now-serving-log (ref (str "Serving Customer Number: " @now-serving)))
  (log-events now-serving-log console events-log)
)

(defn fib
  ([n]
     (fib [0 1] n))
  ([x, n]
     (if (< (count x) n) 
       (fib (conj x (+ (last x) (nth x (- (count x) 2)))) n)
       x)))


(defn serve [key, server]

  (def result (last (fib (rand-int 50))))
  (swap! key assoc :result result)
  ;computes fib, and gives result to customer
  
  ;do events-log here?
  
  
  (if (= (peek @freeServers) nil)
    (serve-next)
  )
  ;if queue is empty right before we push a server on, increment now-serving
  (println "Customer " (:id @key) " has been served, result is: " (:result @key))
  (swap! freeServers conj server)
)



(defn initialServe [key]
  (remove-watch now-serving key)
  (def server (dequeue! freeServers))
  (if (not= (peek @freeServers))
    (println "should be serving someone new now")
    (serve-next)
  )
  ;if queue is not empty when we pop a server off, increment now-serving
  
  (def serve-customer-thread (agent 100))
  (send  serve-customer-thread (serve key server))
  ;create agent thread and have it compute the results for a customer
  ;so other customers can continue grabbin tickets
)

(defn now-serving-watch [key identity old new]
;  (println (:ticket-number @key))
  
  (def customerTicket (:ticket-number @key))
  
  (if (= customerTicket new)
    (initialServe key)
  )
)


