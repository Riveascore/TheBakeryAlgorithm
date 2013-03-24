(defn dequeue!
  [queue]
  (loop []
    (let [q     @queue
          value (peek q)
          nq    (pop q)]
      (if (compare-and-set! queue q nq)
        value
        (recur)))))

;to make 
; (def queue (atom clojure.lang.PersistentQueue/EMPTY))

;to push 
; (swap! queue conj 1)

;to pop
;(dequeue! queue)