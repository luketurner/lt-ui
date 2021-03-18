(ns ulti.inputs
  (:require [reagent.core :as reagent])
  (:refer-clojure :exclude [time]))

(defn css-rules [theme]
  [[:.invalid-input {:border-color "red"}]])

(defn input [{:keys [type options value on-change validator on-error show-error] :as opts
              :or {validator #(identity true)
                   show-error #(str "Invalid value.")
                   on-error #(identity true)}}]
  
  ; initialize atoms for managing transient state that we don't want to expose to callers
  (reagent/with-let [unsaved-value (reagent/atom nil)
                     last-valid-value (reagent/atom nil)]
                    
    ; update our transient state atoms if the `value` is changed
    (when (not= value @last-valid-value)
      (reset! last-valid-value value)
      (reset! unsaved-value value))

    ; now that side effects are done, proceed with building the Reagent component
    (let [value @unsaved-value
          value-is-valid? (or (nil? value) (validator value))
          on-change #(let [v (-> % (.-target) (.-value))]
                       (if (validator v)
                         (do
                           (on-change v)
                           (on-error nil))
                         (do
                           (reset! unsaved-value v)
                           (on-error (show-error v)))))
          opts (-> opts
                   (merge {:on-change on-change
                           :value value
                           :class (str (when-not value-is-valid? "invalid-input"))})
                   (dissoc :validator :on-error :show-error))]
      (case type
        :textarea [:textarea opts value]
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
