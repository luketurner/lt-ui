(ns lt-ui.inputs
  (:require [lt-ui.devcards :refer-macros [defcard']]))

(defn input [{:keys [type options value on-change] :as opts}]
  (let [on-change #(-> % (.-target) (.-value) (on-change))
        opts (assoc opts :on-change on-change)]
   (case type
    :textarea [:textarea opts value]
    :select (into [:select opts] (for [o options]
                                   [:option {:value (:value o)} (:label o)]))
    [:input opts])))

(defn text [opts]
  [input (assoc opts :type :text)])

(defn cursor
  ([data on-change path] (cursor data on-change path {}))
  ([data on-change path opts]
   (merge opts
          {:value (get-in data path)
           :on-change #(on-change (assoc-in data path %))})))

(defcard' text-input
  ""
  (fn [data-atom _]
    [text (cursor @data-atom #(reset! data-atom %) [:value])])
  {:value nil :error nil}
  {:inspect-data true})