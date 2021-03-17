(ns lt-ui.inputs
  (:require [reagent.core :as reagent]))

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

(defn text [opts]
  [input (assoc opts :type :text)])

(defn cursor
  ([data on-change path] (cursor data on-change path {}))
  ([data on-change path opts]
   (merge opts
          {:value (get-in data path)
           :on-change #(on-change (assoc-in data path %))})))
