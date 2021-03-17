(ns lt-ui.forms
  (:require [reagent.core :as reagent]))

(defn group [{:keys [inputs label]}]
  (reagent/with-let [error-message (reagent/atom nil)]
    [:div.input-group
     [:label label]
     [:div.error @error-message]
     (into [:div.inputs] (map (fn [x] (assoc-in x [1 :on-error] #(reset! error-message %))) inputs))]))
