(ns ulti.forms
  (:require [reagent.core :as reagent]
            [garden.units :refer [px]]))

(defn css-rules [{:keys [line-height font-size colors]}]
  (let [m-px (-> line-height (* font-size) (js/Math.floor))]
    [[:.form-group {:display :grid
                     :margin (px m-px)
                     :gap 0
                     :grid-template-columns "20ch minmax(min(50vw, 30ch), 1fr)"}
      [:label :.form-group-error {:grid-column "1"}]
      [:.form-group-input {:grid-column "2"}]]]))


(defn group [{:keys [inputs label]}]
  (reagent/with-let [error-message (reagent/atom nil)]
    (let [update-input (fn [x] [:div.form-group-input
                                (assoc-in x [1 :on-error] #(reset! error-message %))])
          [i & is] (map update-input inputs)]
     [:div.form-group
      [:label label] i
      [:div.form-group-error @error-message] is])))
