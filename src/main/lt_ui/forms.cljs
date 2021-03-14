(ns lt-ui.forms
  (:require [lt-ui.devcards :refer-macros [defcard']]))

(defn group [{:keys [inputs label]}]
  [:div.input-group
   [:label label]
   (into [:div.inputs] inputs)])


(defcard' form-group
  "Use `forms/group` to add labels to your inputs."
  (fn [data-atom]
    [group {:label "Username"
            :inputs [[lt-ui.inputs/text (lt-ui.inputs/cursor @data-atom
                                                             #(reset! data-atom %)
                                                             [:username])]]}])
  {:username nil}
  {:inspect-data true})