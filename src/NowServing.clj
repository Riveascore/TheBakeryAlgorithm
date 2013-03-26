(load "eventsLog")
(load "CustomerAndServer")


(def now-serving (atom 0))

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

(defn serve [key]
  (println "being served")
  (remove-watch now-serving key)
  
  (comment
  
  ;remove this customer as a watcher so it doesn't eat up cpu
  (identity)
  
  
  ;if queue is not empty when we pop a server off, increment now-serving
  
  
  (def result (last (fib (rand-int 100))))
  (swap! key update-in :result result)
  ;computes fib, and gives result to customer
  
  ;do events-log here?
    
  
  (if (= (peek @freeServers) nil)
    (serve-next)
  )
  ;if queue is empty right before we push a server on, increment now-serving
  (swap! freeServers conj server)
  )
)

(defn popAndServe [key]
  (def server (dequeue! freeServers))
  (if (not= (peek @freeServers))
    (serve-next)
  )
  (println server)
  (send  server (serve key server))
)
  

(defn now-serving-watch [key identity old new]
;  (println (:ticket-number @key))
  
  (def customerTicket (:ticket-number @key))
  
  (if (= customerTicket new)
    (popAndServe key)
  )
)


