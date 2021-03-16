(ns lt-ui.forms)

(defn group [{:keys [inputs label]}]
  [:div.input-group
   [:label label]
   (into [:div.inputs] inputs)])
