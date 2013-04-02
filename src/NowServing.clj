(load "eventsLog")
(load "CustomerAndServer")


(def now-serving (atom 0))
;our now serving sign^

(defn serve-next [] 
  (swap! now-serving inc)
  
  ;logging now-serving changes
  (send events-log write "Now-serving: " @now-serving)
  ;logging now-serving changes
)

(defn fib
  ([n]
     (fib [0 1] n))
  ([x, n]
     (if (< (count x) n) 
       (fib (conj x (+ (last x) (nth x (- (count x) 2)))) n)
       x)))


(defn serve [serverAgent key]
  (def fibInput (rand-int 50))
  
  ;logging a customer being served
  (def serving-customer
    (str "Customer " (:id @key) " is being served by Server " (:id serverAgent) " to calculate (fib " fibInput ").")) 
  (send events-log write serving-customer)
  ;logging a customer being served
  
  
  (def result (last (fib fibInput)))
  (swap! key assoc :result result)
  ;logging that customer was finished being served
  (def customer-done-being-served
    (str "Customer " (:id @key) " was given " result))
  (send events-log write customer-done-being-served)
  ;logging that customer was finished being served
  
  (if (= (peek @freeServers) nil)
    (do
      (swap! freeServers conj (agent serverAgent))
      ;logging queuing up of a server
      (def server-queued-up 
        (str "Server " (:id serverAgent) " was added to queue"))
      (send events-log write server-queued-up)
      ;logging queuing up of a server
      
      (serve-next))
    (do
      (swap! freeServers conj (agent serverAgent))
      ;logging queuing up of a server
      (def server-queued-up 
        (str "Server " (:id serverAgent) " was added to queue"))
      (send events-log write server-queued-up)
    )
      ;logging queuing up of a server
  )
  
  serverAgent
)

(defn printIt [myAgent]
  (println myAgent)
  myAgent
)

(defn initialServe [key]
  (remove-watch now-serving key)
  (def serverAgent (dequeue! freeServers))
  ;logging dequeuing a server
  (def server-dequeued 
    (str "Server " (:id @serverAgent) " was removed from queue"))
  (send events-log write server-dequeued)
  ;logging dequeuing a server
  
  (send serverAgent serve key)
  
  (if (not= (peek @freeServers) nil)
    (serve-next)
  )
  ;if queue is not empty when we pop a server off, increment now-serving
)

(defn now-serving-watch [key identity old new]
  
  (def customerTicket (:ticket-number @key))
  
  (if (= customerTicket new)
    (initialServe key)
  )
)


