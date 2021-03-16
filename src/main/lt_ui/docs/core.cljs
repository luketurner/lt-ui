(ns lt-ui.docs.core
  (:require [lt-ui.devcards :as devcards]
            [lt-ui.docs.containers]
            [lt-ui.docs.forms]
            [lt-ui.docs.inputs]
            [lt-ui.docs.overview]
            [lt-ui.docs.typography]))

(defn init! []
  (devcards/init!))

