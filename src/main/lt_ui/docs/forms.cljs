(ns lt-ui.docs.forms
  (:require [lt-ui.devcards :refer-macros [defcard']]
            [lt-ui.forms :as forms]
            [lt-ui.inputs :as inputs]))

(defcard' form-group
  "Use `forms/group` to add labels to your inputs."
  (fn [data-atom]
    [forms/group {:label "Username"
            :inputs [[inputs/text (lt-ui.inputs/cursor @data-atom
                                                             #(reset! data-atom %)
                                                             [:username])]]}])
  {:username nil}
  {:inspect-data true})