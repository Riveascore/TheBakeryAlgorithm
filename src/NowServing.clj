(load "TicketMachine")
(load "CustomerAndServer")

(def now-serving-initial
  [:watchingCustomers nil :value 0 :freeServers nil]
)

(defn makeNowServingObject [watchingCustomers value freeServers]
  [:watchingCustomers watchingCustomers :value value :freeServers freeServers]
)


(defn notifyCustomers [now-serving-object]
  (map 
    (fn [customer]
      (notifyCustomer
        customer
        (get now-serving-object :watchingCustomers) 
        (get now-serving-object :freeServers)
      ))
    (get now-serving-object :watchingCustomers)
    
  )
)

(defn notifyCustomer [customer now-serving]
;  (def waitingCustomers (get now-serving-object :watchingCustomers))
;  (def freeServers (get now-serving-object :freeServers))
  
  (if (= 0 (compare (get now-serving :value) (get customer :ticket-number)))
    (serveCustomer customer now-serving)
  )
)
  
;(defn returnNewNowServingObject [customer freeServers now-serving]
  

(compare 5 5)
;


;^needs customers (whenever customer takes a number it's added to now-serving object)

;needs a "value" number, so customers can compare their "ticket-number" to thsi to see if they're ready to be served

;needs "notify all customers" method

;customer can deque server at the top of free-servers queue

;as soon as customer is given a ticket-number it shoudl check against now-serving's value to see if it should be helped right away

;(next-number 0)