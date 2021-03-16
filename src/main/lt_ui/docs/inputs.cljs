(ns lt-ui.docs.inputs
  (:require [lt-ui.devcards :refer-macros [defcard']]
            [lt-ui.inputs :as inputs]))

(defcard' text-input
  ""
  (fn [data-atom _]
    [inputs/text (inputs/cursor @data-atom #(reset! data-atom %) [:value])])
  {:value nil :error nil}
  {:inspect-data true})