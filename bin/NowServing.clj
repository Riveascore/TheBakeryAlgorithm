(load "TicketMachine")
(load "CustomerAndServer")

(def now-serving
  [:customers nil :value 0]
)


(defn notifyCustomers [customers freeServers]
  (map notifyCustomer customers freeServers now-serving)
  ;^make this pmap later
)

(defn notifyCustomer [customer freeServers now-serving]
  (if (= 0 (compare (get now-serving :value) (get customer :ticket-number)))
    (serveCustomer (pop freeServers) customer now-serving)
)

(compare 5 5)



;^needs customers (whenever customer takes a number it's added to now-serving object)

;needs a "value" number, so customers can compare their "ticket-number" to thsi to see if they're ready to be served

;needs "notify all customers" method

;customer can deque server at the top of free-servers queue

;as soon as customer is given a ticket-number it shoudl check against now-serving's value to see if it should be helped right away

;(next-number 0)