(ns ulti.inputs
  (:require [reagent.core :as reagent]
            [garden.units :refer [px]]
            [garden.selectors :as s])
  (:refer-clojure :exclude [time]))

(defn css-rules [{:keys [line-height font-size]}]
  (let [m-px (-> line-height (* font-size) (js/Math.floor))]
    [[:.invalid-input {:border-color "red"}]
     [(s/> :.input-group :*) {:margin-left 0 :margin-right 0}]
     [((s/> :.input-group :*) (s/not s/last-child)) {:border-right 0}]]))

(defn group [opts & children]
  (into [:div.input-group] (map #(update % 1 merge opts) children)))

(defn input [{:keys [type options value on-change validator on-error show-error] :as opts
              :or {validator #(identity true)
                   show-error #(str "Invalid value.")
                   on-error #(identity true)}}]

  ;; initialize atoms for managing state. Maintains two versions of the value:
  ;;   internal-value-atom :: contains the current raw input (which may not be valid.)
  ;;   external-value-atom :: contains the latest value that was valid and sent out with on-change
  (reagent/with-let [internal-value-atom (reagent/atom nil)
                     external-value-atom (reagent/atom nil)]

    ;; if the caller specifies a different `value`, there was an external update --
    ;; something other than this input has changed the backing value. So, we update
    ;; our internal state to match.
    ;; (this triggers the component to re-render)
    (when (not= value @external-value-atom)
      (reset! external-value-atom value)
      (reset! internal-value-atom value))

    ;; now that side effects are done, proceed with building the Reagent component
    (let [internal-value @internal-value-atom
          value-is-valid? (or (nil? internal-value) (validator internal-value))
          on-change #(let [v (-> % (.-target) (.-value))]
                       (if (validator v)
                         (do
                           (reset! internal-value-atom v)
                           (reset! external-value-atom v)
                           (on-change v)
                           (on-error nil))
                         (do
                           (reset! internal-value-atom v)
                           (on-error (show-error v)))))
          opts (-> opts
                   (merge {:on-change on-change
                           :value internal-value
                           :class (str (when-not value-is-valid? "invalid-input"))})
                   (dissoc :validator :on-error :show-error))]
      (case type
        :textarea [:textarea opts internal-value]
        :select (into [:select opts] (for [o options]
                                       [:option {:value (:value o)} (:label o)]))
        [:input opts]))))

;; Functions for all the <input> types
(defn checkbox [opts] [input (assoc opts :type :checkbox)])
(defn color [opts] [input (assoc opts :type :color)])
(defn date [opts] [input (assoc opts :type :date)])
(defn datetime-local [opts] [input (assoc opts :type :datetime-local)])
(defn email [opts] [input (assoc opts :type :email)])
(defn file [opts] [input (assoc opts :type :file)])
(defn month [opts] [input (assoc opts :type :month)])
(defn integer [opts] [input (assoc opts :type :number)])
(defn number [opts] [input (assoc opts :type :number)])
(defn password [opts] [input (assoc opts :type :password)])
(defn radio [opts] [input (assoc opts :type :radio)]) ; TODO
(defn numeric-range [opts] [input (assoc opts :type :range)])
(defn search [opts] [input (assoc opts :type :search)]) ; TODO?
(defn tel [opts] [input (assoc opts :type :tel)])
(defn text [opts] [input (assoc opts :type :text)])
(defn time [opts] [input (assoc opts :type :time)])
(defn url [opts] [input (assoc opts :type :url)])
(defn week [opts] [input (assoc opts :type :week)])

;; functions for custom input types
(defn select [opts] [input (assoc opts :type :select)])
(defn textarea [opts] [input (assoc opts :type :textarea)])


(defn cursor
  ([data on-change path] (cursor data on-change path {}))
  ([data on-change path opts]
   (merge opts
          {:value (get-in data path)
           :on-change #(on-change (assoc-in data path %))})))
