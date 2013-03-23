(defn next-number [currentNumber]
  (inc currentNumber)
)

(def counter (atom -1))
 
(defn next-value []
  (swap! counter inc))

(def customers
  '({:id 1 :ticket-number nil} {:id 2 :ticket-number nil} {:id 3 :ticket-number nil})
)


;^this calls give number to all customers at once

(defn give-number [customer]
  (assoc customer :ticket-number (next-value))
)

;(+ 5 5)
;(pmap #(give-number %) customers)
