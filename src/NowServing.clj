(load "CustomerAndServer")
(load "eventsLog")

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

(defn serve [customer, key]
  (def server (dequeue! freeServers))
  (remove-watch now-serving key)
  
  (if (not= (peek @freeServers) nil)
    (serve-next)
  )
  (def result (last (fib (rand-int 50))))
    ;do events-log here
    (def served-customer
      (assoc customer :result result))
    (println served-customer " was served!")
    
    (serve-next)
)
  

(defn now-serving-watch [key identity old new]
  (def id-ticket-string (clojure.string/split (name key) #"_")) 
  (def id (first id-ticket-string))
  (def ticket (Integer/parseInt (last id-ticket-string)))
  (def customer
    {:id id :ticket-number ticket :result nil}
  )
  
  (if (= new (:ticket-number customer))
    (serve customer, key)
  )
)


