
(require '[clojure.java.io :as io])


(def console (agent *out*))
(def events-log (agent (io/writer "events-log.log" :append true)))

(defn write
  [^java.io.Writer w & content]
  (doseq [x (interpose "" content)]
    (.write w (str x)))
  (doto w
    (.write "\n")
    .flush))
 
(defn log-events
  [reference & writer-agents]
  (add-watch reference :log
             (fn [_ reference old new]
               (doseq [writer-agent writer-agents]
                 (send-off writer-agent write new)))))

(def x 24)
;(def ticket-log1 (atom (str "Customer id: " 2 "     ticket-number: " 10)))
;(log-events ticket-log1 console events-log)